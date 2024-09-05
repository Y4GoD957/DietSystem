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
            <input
              type="text"
              id="user_id"
              v-model="user.user_id"
              readonly
              class="form-control readonly"
            />
          </div>

          <div class="form-group">
            <label for="username">Usuário:</label>
            <input
              type="text"
              id="username"
              v-model="user.username"
              autocomplete="off"
              placeholder="Usuário"
              class="form-control"
            />
          </div>

          <div class="form-group">
            <label for="email">E-mail:</label>
            <input
              type="email"
              id="email"
              v-model="user.email"
              autocomplete="off"
              placeholder="E-mail"
              class="form-control"
            />
          </div>

          <div class="form-group">
            <label for="name">Nome Completo:</label>
            <input
              type="text"
              id="name"
              v-model="user.name"
              autocomplete="off"
              placeholder="Nome Completo"
              class="form-control"
            />
          </div>

          <div class="form-group">
            <label for="phone">Telefone:</label>
            <input
              type="text"
              id="phone"
              v-model="user.phone"
              autocomplete="off"
              placeholder="Telefone"
              class="form-control"
            />
          </div>
        </div>

        <!-- Divider -->
        <hr class="divider" />

        <!-- Password Reset Section -->
        <div class="section">
          <h2>Redefinir Senha</h2>
          <div class="form-group">
            <label for="password">Nova Senha:</label>
            <input
              :type="passwordVisible ? 'text' : 'password'"
              id="password"
              v-model="newPassword"
              placeholder="********"
              class="form-control"
            />
          </div>

          <div class="form-group">
            <label for="confirm_password">Confirmar Nova Senha:</label>
            <input
              :type="passwordVisible ? 'text' : 'password'"
              id="confirm_password"
              v-model="confirmPassword"
              placeholder="********"
              class="form-control"
            />
          </div>

          <div class="form-group">
            <button type="button" @click="togglePasswordVisibility" class="btn btn-secondary">
              {{ passwordVisible ? 'Ocultar Senha' : 'Mostrar Senha' }}
            </button>
          </div>
        </div>

        <!-- Divider -->
        <hr class="divider" />

        <!-- Diet Settings -->
        <div class="section">
          <h2>Configurações de Dieta</h2>
          <div class="form-group">
            <label for="diet_id">ID de Dieta:</label>
            <input
              type="text"
              id="diet_id"
              v-model="diet.diet_id"
              readonly
              class="form-control readonly"
            />
          </div>

          <!-- Dropdown para Atividades Semanais -->
          <div class="form-group">
            <label for="activities">Atividades Semanais:</label>
            <div class="dropdown" @click="toggleActivitiesVisibility">
              <span>{{ diet.activities || 'Selecione' }}</span>
              <ion-icon :name="activitiesVisible ? 'chevron-up' : 'chevron-down'"></ion-icon>
            </div>
            <div v-if="activitiesVisible" class="dropdown-options">
              <div
                v-for="i in 8"
                :key="i"
                @click="
                  () => {
                    diet.activities = i - 1
                    toggleActivitiesVisibility()
                  }
                "
              >
                {{ i - 1 }}
              </div>
            </div>
          </div>

          <div class="form-group">
            <label for="age">Idade:</label>
            <input
              type="number"
              id="age"
              v-model="diet.age"
              autocomplete="off"
              placeholder="Idade"
              class="form-control"
              step="1"
              min="10"
              max="110"
            />
          </div>

          <!-- Dropdown para Objetivo -->
          <div class="form-group">
            <label for="diet_style">Objetivo:</label>
            <div class="dropdown" @click="toggleObjectiveVisibility">
              <span>{{ diet.diet || 'Selecione' }}</span>
              <ion-icon :name="objectiveVisible ? 'chevron-up' : 'chevron-down'"></ion-icon>
            </div>
            <div v-if="objectiveVisible" class="dropdown-options">
              <div
                @click="
                  () => {
                    diet.diet = 'Bulking (Ganho de Massa)'
                    toggleObjectiveVisibility()
                  }
                "
              >
                Bulking (Ganho de Massa)
              </div>
              <div
                @click="
                  () => {
                    diet.diet = 'Cutting (Emagrecimento)'
                    toggleObjectiveVisibility()
                  }
                "
              >
                Cutting (Emagrecimento)
              </div>
              <div
                @click="
                  () => {
                    diet.diet = 'Maintaining (Manter Peso)'
                    toggleObjectiveVisibility()
                  }
                "
              >
                Maintaining (Manter Peso)
              </div>
            </div>
          </div>

          <!-- Dropdown para Gênero -->
          <div class="form-group">
            <label for="gender">Gênero:</label>
            <div class="dropdown" @click="toggleGenderVisibility">
              <span>{{ diet.gender || 'Selecione' }}</span>
              <ion-icon :name="genderVisible ? 'chevron-up' : 'chevron-down'"></ion-icon>
            </div>
            <div v-if="genderVisible" class="dropdown-options">
              <div
                @click="
                  () => {
                    diet.gender = 'Masculino'
                    toggleGenderVisibility()
                  }
                "
              >
                Masculino
              </div>
              <div
                @click="
                  () => {
                    diet.gender = 'Feminino'
                    toggleGenderVisibility()
                  }
                "
              >
                Feminino
              </div>
            </div>
          </div>

          <div class="form-group">
            <label for="height">Altura (m):</label>
            <input
              type="number"
              id="height"
              v-model="diet.height"
              autocomplete="off"
              placeholder="Altura"
              class="form-control"
              step="1"
              min="50"
              max="250"
            />
          </div>

          <div class="form-group">
            <label for="weight">Peso (kg):</label>
            <input
              type="number"
              id="weight"
              v-model="diet.weight"
              autocomplete="off"
              placeholder="Peso"
              class="form-control"
              step="0.1"
              min="10"
              max="300"
            />
          </div>
        </div>

        <button type="submit" class="btn btn-primary">Atualizar Perfil</button>
      </form>
    </div>
  </div>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'
import TheFooter from '@/components/TheFooter.vue'
import axios from '@/plugins/axios.js'
import Swal from 'sweetalert2'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'

export default {
  name: 'ProfilePage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
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
      newPassword: '',
      confirmPassword: '',
      diet: {
        diet_id: '',
        activities: '',
        age: '',
        diet: '',
        gender: '',
        height: '',
        weight: ''
      },
      passwordVisible: false,
      activitiesVisible: false, // Controle para dropdown de atividades
      genderVisible: false, // Controle para dropdown de gênero
      objectiveVisible: false // Controle para dropdown de objetivo
    }
  },
  watch: {
    // Observa mudanças no valor do peso e faz a conversão
    'formDataDiet.weight': function (newValue) {
      // Substitui a vírgula por ponto se houver
      if (newValue) {
        this.diet.weight = newValue.toString().replace(',', '.')
      }
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) {
      this.loadProfile(userId)
      this.loadDietSettings(userId)
    } else {
      console.error('ID do usuário não encontrado no localStorage')
    }
  },
  methods: {
    loadProfile(userId) {
      axios
        .get(`/users/profile/${userId}`)
        .then((response) => {
          this.user = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar o perfil:', error)
        })
    },
    loadDietSettings(userId) {
      axios
        .get(`/users/diet-settings/${userId}`)
        .then((response) => {
          this.diet = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar a dieta:', error)
        })
    },
    updateProfile() {
      if (this.user.password && this.user.password !== this.confirmPassword) {
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'As senhas não coincidem. Por favor, tente novamente.'
        })
        return
      }

      // Prepare os dados para envio
      const updateData = {
        ...this.user,
        password: this.newPassword || this.user.password, // Usa a nova senha se fornecida
        confirmPassword: this.confirmPassword || this.user.confirmPassword, // Usa a confirmação de senha se fornecida
        diets: [this.diet] // Envia a dieta como um array
      }

      axios
        .put(`/users/profile/${this.user.user_id}`, updateData)
        .then(() => {
          Swal.fire({
            icon: 'success',
            title: 'Perfil Atualizado!',
            text: 'O seu perfil foi atualizado com sucesso!'
          })
        })
        .catch((error) => {
          console.error('Erro ao atualizar o perfil:', error)
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: 'Tivemos um problema ao atualizar o seu perfil. Por favor, tente novamente mais tarde.'
          })
        })
    },
    togglePasswordVisibility() {
      this.passwordVisible = !this.passwordVisible
    },
    toggleActivitiesVisibility() {
      this.activitiesVisible = !this.activitiesVisible
    },
    toggleGenderVisibility() {
      this.genderVisible = !this.genderVisible
    },
    toggleObjectiveVisibility() {
      this.objectiveVisible = !this.objectiveVisible
    }
  }
}
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
  content: '';
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

.form-group input[type='text'],
.form-group input[type='number'],
.form-group input[type='email'],
.form-group input[type='password'] {
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

.dropdown {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
  background-color: #fff;
}

.dropdown-options {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  background-color: #f9f9f9;
  margin-top: 5px;
}

.dropdown-options div {
  padding: 5px;
  cursor: pointer;
}

.dropdown-options div:hover {
  background-color: #e0e0e0;
}
</style>
