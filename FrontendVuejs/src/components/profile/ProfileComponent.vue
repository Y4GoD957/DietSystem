<template>
  <!-- Content Display -->
  <div class="content">
    <!-- Profile Content -->
    <form @submit.prevent="updateProfile">
      <!-- User Settings -->
      <div class="section">
        <h2>Dados do Usuário</h2>
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
          <label for="position">Tipo do Usuário:</label>
          <input
            type="text"
            id="position"
            v-model="user.position"
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
        <h2>Dados das Métricas para Dieta</h2>
        <div class="form-group">
          <label for="diet_id">ID das Métricas:</label>
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
            <div v-for="i in 8" :key="i" @click="setActivities(i - 1)">
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
            <div @click="setObjective('Bulking (Ganho de Massa)')">Bulking (Ganho de Massa)</div>
            <div @click="setObjective('Cutting (Emagrecimento)')">Cutting (Emagrecimento)</div>
            <div @click="setObjective('Maintaining (Manter Peso)')">Maintaining (Manter Peso)</div>
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
            <div @click="setGender('Masculino')">Masculino</div>
            <div @click="setGender('Feminino')">Feminino</div>
          </div>
        </div>

        <div class="form-group">
          <label for="height">Altura (cm):</label>
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
</template>

<script>
import axios from '@/plugins/axios.js'
import Swal from 'sweetalert2'

export default {
  name: 'ProfileComponent',
  data() {
    return {
      user: {
        user_id: '',
        position: '',
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
    'diet.weight': function (newValue) {
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
        .get(`/diet/diet-settings/${userId}`)
        .then((response) => {
          this.diet = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar a dieta:', error)
        })
    },
    updateProfile() {
      if (this.newPassword && this.newPassword !== this.confirmPassword) {
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'As senhas não coincidem. Por favor, tente novamente.'
        })
        return
      }

      const updateData = {
        ...this.user,
        password: this.newPassword || this.user.password
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

.form-control {
  border-radius: 4px;
  border: 1px solid #ccc;
  padding: 8px;
  width: 100%;
}

.readonly-input {
  background-color: #e9ecef;
  cursor: not-allowed;
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
  text-align: center;
  transition: background 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn-primary:hover {
  background: #0e1f26;
}

/* Estilos de Dropdown */
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

/* Estilo das abas */
.tabs-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.tabs {
  display: flex;
  justify-content: space-around;
  width: 100%;
  max-width: 600px;
  border-bottom: none;
}

.tabs button {
  flex: 1;
  padding: 10px 15px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px 5px 0 0;
  cursor: pointer;
  font-size: 14px;
  color: #162938;
  transition:
    background-color 0.3s,
    color 0.3s;
  margin: 0 3px;
}

.tabs button.active {
  background-color: #162938;
  color: #fff;
  font-weight: bold;
  border-bottom: 1px solid #162938;
}

.tabs button:hover {
  background-color: #e0e0e0;
  color: #162938;
}

/* Conteúdo das abas */
.tab-content {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 0 5px 5px 5px;
  background-color: #fff;
  margin-top: -1px;
}

.tab-content.hidden {
  display: none;
}

/* Estilo da tabela */
.table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  table-layout: fixed;
}

.table td,
.table th {
  padding: 12px;
  border: 1px solid #ddd;
  text-align: left;
  word-wrap: break-word;
}

.table th {
  background-color: #f4f4f4;
  color: #333;
}

.action-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

/* Botões de ação */
.btn-cancel {
  color: #3a383f;
  background-color: #fff;
  box-shadow: inset 0 0 0 1px #bfbfc3;
  padding: 10px 20px;
  border-radius: 5px;
}

.btn-cancel:hover {
  box-shadow: inset 0 0 0 1px #a9a9a9;
}

.btn-save {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 10px 20px;
  background-color: #162938;
  color: #fff;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.btn-save:hover {
  background-color: #0e1f26;
}

.btn-edit {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #162938;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  white-space: nowrap;
}

.btn-edit:hover {
  background-color: #0e1f26;
}

.btn-edit ion-icon {
  margin-right: 5px;
}
</style>
