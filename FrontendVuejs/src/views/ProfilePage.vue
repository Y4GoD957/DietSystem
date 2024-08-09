<template>
    <div>
      <TheHeader />
  
      <div class="profile-container">
        <div class="heading">
          <h1>Perfil de Usuário</h1>
        </div>
  
        <form @submit.prevent="updateProfile">
          <!-- User Settings -->
          <div class="section">
            <h2>Configurações do Usuário</h2>
            <div class="form-group">
              <label for="user_id">ID do Usuário:</label>
              <input type="text" id="user_id" v-model="user.user_id" readonly class="form-control readonly">
            </div>
  
            <div class="form-group">
              <label for="username">Usuário:</label>
              <input type="text" id="username" v-model="user.username" autocomplete="off" placeholder="Usuário" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="email">E-mail:</label>
              <input type="email" id="email" v-model="user.email" autocomplete="off" placeholder="E-mail" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="name">Nome Completo:</label>
              <input type="text" id="name" v-model="user.name" autocomplete="off" placeholder="Nome Completo" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="phone">Telefone:</label>
              <input type="text" id="phone" v-model="user.phone" autocomplete="off" placeholder="Telefone" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="password">Senha:</label>
              <div class="password-wrapper">
                <input :type="passwordVisible ? 'text' : 'password'" id="password" autocomplete="off" v-model="user.password" placeholder="********" class="form-control">
                <span class="icon" @click="togglePasswordVisibility">
                  <ion-icon :name="passwordVisible ? 'eye' : 'eye-off'"></ion-icon>
                </span>
              </div>
            </div>
          </div>
  
          <!-- Divider -->
          <hr class="divider">
  
          <!-- Diet Settings -->
          <div class="section">
            <h2>Configurações de Dieta</h2>
            <div class="form-group">
              <label for="diet_id">ID de Dieta:</label>
              <input type="text" id="diet_id" v-model="diet.diet_id" readonly class="form-control readonly">
            </div>
  
            <div class="form-group">
              <label for="activities">Atividades Semanais:</label>
              <input type="number" id="activities" v-model="diet.activities" placeholder="Atividades Semanais" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="age">Idade:</label>
              <input type="number" id="age" v-model="diet.age" autocomplete="off" placeholder="Idade" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="diet_style">Objetivo:</label>
              <input type="text" id="diet" v-model="diet.diet" placeholder="Objetivo" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="gender">Gênero:</label>
              <input type="text" id="gender" v-model="diet.gender" placeholder="Gênero" class="form-control">
            </div>
  
            <div class="form-group">
              <label for="height">Altura (m):</label>
              <input type="number" id="height" v-model="diet.height" autocomplete="off" placeholder="Altura" class="form-control">
            </div>
          </div>
  
          <button type="submit" class="btn btn-primary">Atualizar Perfil</button>
        </form>
      </div>
  
      <TheFooter />
    </div>
  </template>
  
  <script>
  import TheHeader from '@/components/TheHeader.vue';
  import TheFooter from '@/components/TheFooter.vue';
  import axios from '@/plugins/axios.js';
  import Swal from 'sweetalert2';
  
  export default {
    name: 'ProfilePage',
    components: {
      TheHeader,
      TheFooter
    },
    data() {
      return {
        user: {
          user_id: '',
          username: '',
          email: '',
          name: '',
          phone: '',
          password: ''
        },
        diet: {
          diet_id: '',
          activities: '',
          age: '',
          diet: '',
          gender: '',
          height: ''
        },
        passwordVisible: false
      };
    },
    created() {
      this.loadProfile();
      this.loadDietSettings();
    },
    methods: {
      loadProfile() {
        axios.get('/api/profile')
          .then(response => {
            this.user = response.data.user;
          })
          .catch(error => {
            console.error('Erro ao carregar o perfil:', error);
          });
      },
      loadDietSettings() {
        axios.get('/api/diet-settings')
          .then(response => {
            this.diet = response.data.diet;
          })
          .catch(error => {
            console.error('Erro ao carregar a dieta:', error);
          });
      },
      updateProfile() {
        axios.put('/api/profile', this.user)
            .then(() => {
                Swal.fire({
                    icon: 'success',
                    title: 'Perfil Atualizado!',
                    text: 'O seu perfil foi atualizado com sucesso!',
                });
            })
            .catch(error => {
                console.error('Erro ao atualizar o perfil:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Erro!',
                    text: 'Tivemos um problema ao atualizar o seu perfil. Por favor, tente novamente mais tarde.',
                });
            });
      },

      togglePasswordVisibility() {
        this.passwordVisible = !this.passwordVisible;
      }
    }
  };
  </script>
  
  <style scoped>
  .profile-container {
    max-width: 800px;
    margin: 6.25rem auto;
  }
  
  .heading {
    width: 90%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    text-align: center;
    margin: 20px auto;
  }
  
  .heading h1 {
    font-size: 50px;
    color: #000;
    margin-bottom: 25px;
    position: relative;
  }
  
  .heading h1::after {
    content: "";
    position: absolute;
    width: 100%;
    height: 4px;
    display: block;
    margin: 0 auto;
    background-color: #4caf50;
  }
  
  .section {
    margin-bottom: 30px;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
    color: #162938;
  }
  
  .form-group input[type="text"],
  .form-group input[type="number"],
  .form-group input[type="email"],
  .form-group input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
  }
  
  .form-group input.readonly {
    background-color: #e9ecef;
    cursor: not-allowed;
  }
  
  .password-wrapper {
    position: relative;
  }
  
  .password-wrapper input {
    padding-right: 40px;
  }
  
  .password-wrapper .icon {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 1.2em;
    color: #162938;
    cursor: pointer;
  }
  
  .divider {
    border: none;
    border-top: 2px solid #ccc;
    margin: 20px 0;
  }
  
  .btn-primary {
  width: 100%;
  padding: 15px;
  background: #162938;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  line-height: 1.5; /* Ajusta o alinhamento vertical do texto */
  text-align: center; /* Garante que o texto esteja centralizado horizontalmente */
  transition: background 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn-primary:hover {
  background: #0e1f26;
}
  </style>
  