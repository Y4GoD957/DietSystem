<template>
  <header>
    <h2 class="logo" @click="goToHomePage">
      <img src="@/assets/logo.png" width="100%" height="100%" alt="Logo Sys Diet" />
    </h2>
    <nav class="navigation">
      <a class="header-and-footer" href="/">Início</a>
      <a class="header-and-footer" href="/AboutUs">Sobre</a>
      <a class="header-and-footer" href="#services">Serviços</a>
      <a class="header-and-footer" href="/ContactPage">Contato</a>
    </nav>
    <button v-if="!isAuthenticated" class="btnLogin-popup">Login</button>
    <div v-else class="auth-buttons">
      <button class="btnProfile" @click="goToProfile">Perfil</button>
      <button class="btnLogout" @click="handleLogout">Logout</button>
    </div>
  </header>

  <section id="login">
    <div class="wrapper">
      <span class="icon-close"><ion-icon name="close-outline"></ion-icon></span>

      <div class="form-box login">
        <h2>Login</h2>
        <form @submit.prevent="handleLogin">
          <div class="input-box">
            <span class="icon"><ion-icon name="mail"></ion-icon></span>
            <input type="email" v-model="formDataLogin.email" />
            <label>E-mail</label>
          </div>
          <div class="input-box">
            <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
            <input type="password" v-model="formDataLogin.password" />
            <label>Senha</label>
          </div>

          <div class="remember-forgot">
            <label><input type="checkbox" v-model="keepLoggedIn" />Manter login</label>
            <a href="">Esqueci minha senha</a>
          </div>

          <button type="submit" class="btn">Entrar</button>

          <div class="login-register">
            <p>Não possui uma conta? <a href="#" class="register-link">Cadastrar</a></p>
          </div>
        </form>
      </div>

      <div class="form-box register">
        <h2>Cadastro</h2>
        <form @submit.prevent="handleRegister">
          <div class="input-box">
            <span class="icon"><ion-icon name="person"></ion-icon></span>
            <input
              type="text"
              v-model="formDataRegister.username"
              @input="validateName"
              :class="{ 'invalid-input': !isNameValid }"
              @focus="displayAsterisk('username')"
            />
            <label
              >Usuário<span
                :class="{ 'invalid-asterisk': !isNameValid }"
                v-if="showAsterisk.username"
                >*</span
              ></label
            >
          </div>

          <div class="input-box">
            <span class="icon"><ion-icon name="mail"></ion-icon></span>
            <input
              type="email"
              v-model="formDataRegister.email"
              @focus="displayAsterisk('email')"
            />
            <label
              >E-mail<span :class="{ 'invalid-asterisk': !isEmailValid }" v-if="showAsterisk.email"
                >*</span
              ></label
            >
          </div>

          <div class="input-box">
            <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
            <input
              type="password"
              v-model="formDataRegister.password"
              min="8"
              @input="validatePassword"
              :class="{ 'invalid-input': !isPasswordValid }"
              @focus="displayAsterisk('password')"
            />
            <label
              >Senha<span
                :class="{ 'invalid-asterisk': !isPasswordValid }"
                v-if="showAsterisk.password"
                >*</span
              ></label
            >
          </div>

          <button type="submit" class="btn">Cadastrar</button>

          <div class="login-register">
            <p>Já possui uma conta? <a class="login-link">Login</a></p>
          </div>
        </form>
      </div>
    </div>

    <UserInteractions />
  </section>
</template>

<script>
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'
import UserInteractions from '@/components/UserInteractions.vue'

export default {
  name: 'TheHeader',
  components: { UserInteractions },
  data() {
    return {
      formDataLogin: {
        email: '',
        password: ''
      },
      formDataRegister: {
        username: '',
        email: '',
        password: ''
      },
      isAuthenticated: !!localStorage.getItem('token'),
      isNameValid: true,
      isEmailValid: true,
      isPasswordValid: true,
      showAsterisk: {
        username: false,
        email: false,
        password: false
      },
      keepLoggedIn: false // Nova variável para a checkbox
    }
  },
  mounted() {
    // Carrega o e-mail salvo como 'lastUsedEmail' (se houver)
    const savedLastEmail = localStorage.getItem('lastUsedEmail')
    if (savedLastEmail) {
      this.formDataLogin.email = savedLastEmail
    }
  },
  methods: {
    goToHomePage() {
      this.$router.push('/').then(() => {
        window.location.reload()
        window.scrollTo(0, 0)
      })
    },
    handleLogin() {
      axios
        .post('/auth/login', this.formDataLogin)
        .then((response) => {
          if (response.data.token) {
            // Salva os dados do token no localStorage
            localStorage.setItem('token', response.data.token)
            localStorage.setItem('userEmail', response.data.email)
            localStorage.setItem('userId', response.data.userId)

            // Se a checkbox "Manter login" estiver marcada, salvar o e-mail em 'lastUsedEmail'
            if (this.keepLoggedIn) {
              localStorage.setItem('lastUsedEmail', this.formDataLogin.email)
            } else {
              localStorage.removeItem('lastUsedEmail')
            }

            Swal.fire({
              icon: 'success',
              title: 'Sucesso!',
              text: 'Seus dados foram validados!'
            })

            this.isAuthenticated = true

            // Redireciona para o endpoint adequado com base na resposta do backend
            const redirectEndpoint = response.data.redirectEndpoint || '/diet'
            this.$router.push(redirectEndpoint)
          } else {
            Swal.fire({
              icon: 'error',
              title: 'Erro!',
              text: 'Não foi possível autenticar. Tente novamente.'
            })
          }
        })
        .catch((error) => {
          console.error('Erro ao fazer login:', error)
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: 'Credenciais inválidas. Por favor, tente novamente.'
          })
        })
    },
    handleRegister() {
      if (this.validateName() && this.validatePassword()) {
        axios
          .post('/users/register', this.formDataRegister)
          .then((response) => {
            if (response.status === 201) {
              Swal.fire({
                icon: 'success',
                title: 'Parabéns!',
                text: 'O seu cadastro foi realizado com sucesso!'
              })
              this.formDataRegister = {
                username: '',
                email: '',
                password: ''
              }
            }
          })
          .catch((error) => {
            let message = 'Não foi possível realizar o cadastro. Tente novamente.'
            if (error.response && error.response.status === 400) {
              message = error.response.data
            }
            Swal.fire({
              icon: 'error',
              title: 'Erro!',
              text: message
            })
          })
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'Por favor, certifique-se de que não há caracteres especiais e espaços no nome e a senha contenha no mínimo 8 caracteres.'
        })
      }
    },
    validateName() {
      const nameRegex = /^[a-zA-Z0-9]+$/
      this.isNameValid = nameRegex.test(this.formDataRegister.username)
      return this.isNameValid
    },
    validatePassword() {
      this.isPasswordValid = this.formDataRegister.password.length >= 8
      return this.isPasswordValid
    },
    displayAsterisk(field) {
      this.showAsterisk[field] = true
    },
    hideAsterisk(field) {
      this.showAsterisk[field] = false
    },
    handleLogout() {
      localStorage.removeItem('token')
      localStorage.removeItem('userEmail')
      localStorage.removeItem('userId')
      this.isAuthenticated = false
      window.location.href = 'http://localhost:5173'
    },
    goToProfile() {
      this.$router.push('/profile')
    }
  }
}
</script>

<style scoped>
.logo {
  font-size: 2em;
  color: #fff;
  user-select: none;
  cursor: pointer;
}

.btnLogin-popup {
  width: 130px;
  height: 50px;
  background: transparent;
  border: 2px solid #fff;
  outline: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1.1em;
  color: #fff;
  font-weight: 500;
  margin-left: 40px;
  transition: 0.5s;
}

.btnLogin-popup:hover {
  background: #fff;
  color: #162938;
}

.auth-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.btnProfile,
.btnLogout {
  width: 130px;
  height: 40px; /* Altura reduzida para dividir o espaço */
  background: transparent;
  border: 2px solid #fff;
  outline: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1.1em;
  color: #fff;
  font-weight: 500;
  margin: 5px 0; /* Espaçamento vertical entre os botões */
  transition: 0.5s;
}

.btnProfile:hover {
  background: #fff;
  color: #162938;
}

.btnLogout:hover {
  background: #fff;
  color: red;
}

.wrapper {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) scale(0);
  width: 400px;
  height: 440px;
  background: #fff;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 1);
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  transition:
    transform 0.5s ease,
    height 0.2s ease;
  z-index: 1000;
}

.wrapper.active-popup {
  transform: translate(-50%, -50%) scale(1);
}

.wrapper.active {
  height: 520px;
}

.wrapper .form-box {
  width: 100%;
  padding: 40px;
}

.wrapper .form-box.login {
  transition: transform 0.18s ease;
  transform: translateX(0);
}

.wrapper.active .form-box.login {
  transition: none;
  transform: translateX(-400px);
}

.wrapper .form-box.register {
  position: absolute;
  transition: none;
  transform: translateX(400px);
}

.wrapper.active .form-box.register {
  transition: transform 0.18s ease;
  transform: translateX(0);
}

.wrapper .icon-close {
  position: absolute;
  top: 0;
  right: 0;
  width: 45px;
  height: 45px;
  background: #162938;
  font-size: 2em;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom-left-radius: 20px;
  cursor: pointer;
  z-index: 1;
}

.form-box h2 {
  font-size: 2em;
  color: #162938;
  text-align: center;
}

.login-register {
  font-size: 0.9em;
  color: #162938;
  text-align: center;
  font-weight: 500;
  margin: 25px 0 10px;
}

.login-register p a {
  color: #162938;
  text-decoration: none;
  font-weight: 600;
  cursor: pointer;
}

.login-register p a:hover {
  text-decoration: underline;
}

.remember-forgot {
  font-size: 0.9em;
  color: #162938;
  font-weight: 500;
  margin: -15px 0 15px;
  display: flex;
  justify-content: space-between;
}

.remember-forgot label input {
  accent-color: #162938;
  margin-right: 3px;
}

.remember-forgot a {
  color: #162938;
  text-decoration: none;
}

.remember-forgot a:hover {
  text-decoration: underline;
}

.invalid-input {
  border-color: red;
}

.invalid-asterisk {
  color: red;
}

.input-box label span {
  margin-left: 5px;
  font-size: 1.2em;
}
</style>
