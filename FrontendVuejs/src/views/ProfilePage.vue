<template>
    <TheHeader />

    <div class="profile-container">
      <!-- Common Heading -->
      <div class="heading">
        <h1>{{ headingText }}</h1>
      </div>

      <!-- Navigation Tabs -->
      <div class="tabs-container">
        <div class="tabs">
          <button :class="{ active: currentTab === 'profile' }" @click="selectTab('profile')">
            Perfil
          </button>
          <button :class="{ active: currentTab === 'dieta' }" @click="selectTab('dieta')">
            Dieta
          </button>
          <button :class="{ active: currentTab === 'exercicios' }" @click="selectTab('exercicios')">
            Exercícios
          </button>
          <button
            v-if="user.position === 'admin'"
            :class="{ active: currentTab === 'usuarios' }"
            @click="selectTab('usuarios')"
          >
            Usuários
          </button>
        </div>
      </div>

      <!-- Content Display -->
      <div class="content">
        <div v-if="currentTab === 'profile'">
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
                <label for="user_id">Tipo do Usuário:</label>
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

        <div v-if="currentTab === 'dieta'">
          <!-- Dieta Content -->
          <h2>Conteúdo da Dieta</h2>
          <!-- TMB (Taxa de Metabolismo Basal) -->
          <div class="form-group">
            <label for="tmb">Taxa de Metabolismo Basal (TMB):</label>
            <input type="text" id="tmb" v-model="diet.tmb" readonly class="form-control readonly" />
          </div>

          <!-- Gasto Calórico -->
          <div class="form-group">
            <label for="caloric-expenditure">Gasto Calórico:</label>
            <input
              type="text"
              id="caloric_expenditure"
              v-model="diet.caloric_expenditure"
              readonly
              class="form-control readonly"
            />
          </div>

          <!-- IMC (Índice de Massa Corporal) -->
          <div class="form-group">
            <label for="imc">Índice de Massa Corporal (IMC):</label>
            <input type="text" id="imc" v-model="diet.imc" readonly class="form-control readonly" />
          </div>

          <!-- Peso Ideal -->
          <div class="form-group">
            <label for="ideal-weight">Peso Ideal:</label>
            <input
              type="text"
              id="ideal_weight"
              v-model="diet.ideal_weight"
              readonly
              class="form-control readonly"
            />
          </div>
        </div>

        <div v-if="currentTab === 'exercicios'">
          <!-- Exercícios Content -->
          <h2>Conteúdo de Exercícios</h2>
          <!-- Implementação futura para conteúdo de exercícios -->
        </div>

        <!-- Usuários Content -->
        <div v-if="currentTab === 'usuarios'">
  <h2>Lista de Usuários</h2>

  <!-- Condição para mostrar a tabela ou o formulário de edição -->
  <div v-if="!editingUser">
    <table class="table">
      <thead>
        <tr>
          <th>ID do Usuário</th>
          <th>Tipo do Usuário</th>
          <th>Usuário</th>
          <th>Email</th>
          <th>Nome Completo</th>
          <th>Telefone</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in allUsers" :key="user.userId">
          <td>{{ user.userId }}</td> <!-- Corrigindo a exibição do ID -->
          <td>{{ user.position }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.phone }}</td>
          <td>
            <!-- Botão de editar com ajuste no estilo -->
            <button @click="editUser(user)" class="btn btn-edit">
              <ion-icon name="create-outline"></ion-icon> <span>Editar</span>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Formulário de edição do usuário -->
  <div v-if="editingUser">
    <h2>Editando Usuário: {{ editingUser.name }}</h2>
    <div class="section">
      <div class="form-group">
        <label>Nome Completo</label>
        <input type="text" v-model="editingUser.name" class="form-control" />
      </div>
      <div class="form-group">
        <label>Email</label>
        <input type="email" v-model="editingUser.email" class="form-control" />
      </div>
      <div class="form-group">
        <label>Telefone</label>
        <input type="text" v-model="editingUser.phone" class="form-control" />
      </div>
      <div class="form-group">
        <label>Tipo do Usuário</label>
        <input type="text" v-model="editingUser.position" class="form-control" />
      </div>
    </div>

    <!-- Botões de Ação -->
    <div class="action-buttons">
      <button @click="cancelEdit" class="btn btn-cancel">Cancelar</button>
      <button @click="saveUser" class="btn btn-save">Editar Dados</button>
    </div>
  </div>
</div>

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
      currentTab: 'profile', // Aba selecionada inicialmente
      user: {
        user_id: '',
        position: '',
        username: '',
        email: '',
        name: '',
        phone: '',
        password: ''
      },
      allUsers: [], // Armazena todos os usuários para a aba "Usuários"
      newPassword: '',
      confirmPassword: '',
      diet: {
        diet_id: '',
        activities: '',
        age: '',
        diet: '',
        gender: '',
        height: '',
        weight: '',
        tmb: '',
        imc: '',
        caloric_expenditure: '',
        ideal_weight: ''
      },
      passwordVisible: false,
      activitiesVisible: false, // Controle para dropdown de atividades
      genderVisible: false, // Controle para dropdown de gênero
      objectiveVisible: false, // Controle para dropdown de objetivo
      headingText: 'Perfil de Usuário', // Texto do cabeçalho
      isEditingUser: false, // Controla se estamos editando um usuário
      selectedUser: null, // Armazena o usuário selecionado para edição
    }
  },
  watch: {
    // Observa mudanças no valor do peso e faz a conversão
    'diet.weight': function (newValue) {
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

    // Carrega todos os usuários ao criar o componente
    this.loadAllUsers()
  },
  methods: {
    selectTab(tab) {
      this.currentTab = tab
      this.updateHeadingText()
    },
    updateHeadingText() {
      switch (this.currentTab) {
        case 'profile':
          this.headingText = 'Perfil de Usuário'
          break
        case 'dieta':
          this.headingText = 'Conteúdo da Dieta'
          break
        case 'exercicios':
          this.headingText = 'Conteúdo de Exercícios'
          break
        case 'usuarios':
          this.headingText = 'Conteúdo de Usuários'
          break
        default:
          this.headingText = 'Perfil de Usuário'
      }
    },
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

      // Prepare os dados para envio
      const updateData = {
        ...this.user,
        password: this.newPassword || this.user.password // Usa a nova senha se fornecida
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
    },

    // Funções para gerenciar usuários na aba "Usuários"
    loadAllUsers() {
      axios
        .get('/users/all')
        .then((response) => {
          this.allUsers = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar usuários:', error)
        })
    },
    editUser(user) {
      this.editingUser = { ...user } // Clona o objeto para evitar modificações diretas
    },
    cancelEdit() {
      this.editingUser = null // Cancela a edição e retorna à tabela de usuários
    },
    saveUser() {
      // Lógica para salvar as alterações
      axios.put(`/users/${this.editingUser.user_id}`, this.editingUser)
        .then(() => {
          this.editingUser = null
          this.loadAllUsers() // Recarrega a lista de usuários
        })
        .catch(error => {
          console.error('Erro ao salvar o usuário', error)
        })
    },
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

/* Estilos para inputs */
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

/* Container das abas */
.tabs-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

/* Estilo das abas */
.tabs {
  display: flex;
  justify-content: space-around;
  width: 100%;
  max-width: 600px; /* Reduzindo a largura */
  border-bottom: none; /* Remover linha abaixo das tabs */
}

/* Estilo dos botões das abas */
.tabs button {
  flex: 1;
  padding: 10px 15px; /* Reduzindo o padding */
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px 5px 0 0;
  cursor: pointer;
  font-size: 14px; /* Reduzindo a fonte */
  color: #162938;
  transition:
    background-color 0.3s,
    color 0.3s;
  margin: 0 3px; /* Reduzindo o espaço entre as abas */
}

/* Aba ativa */
.tabs button.active {
  background-color: #162938;
  color: #fff;
  font-weight: bold;
  border-bottom: 1px solid #162938; /* Completar a borda inferior da aba ativa */
}

/* Hover nas abas */
.tabs button:hover {
  background-color: #e0e0e0;
  color: #162938;
}

/* Estilo para o conteúdo ativo da aba */
.tab-content {
  padding: 15px; /* Reduzindo o padding do conteúdo */
  border: 1px solid #ccc;
  border-radius: 0 5px 5px 5px;
  background-color: #fff;
  margin-top: -1px; /* Ajusta o alinhamento para conectar ao botão da aba */
}

.tab-content.hidden {
  display: none;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  table-layout: fixed; /* Garante que a largura das células seja uniforme */
}

.table td, .table th {
  padding: 12px;
  border: 1px solid #ddd;
  text-align: left;
  word-wrap: break-word; /* Quebra o texto em várias linhas, se necessário */
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

.btn-cancel {
  color: #3a383f;
  background-color: #fff;
  box-shadow: inset 0 0 0 1px #bfbfc3;
  padding: 10px 20px;
  border-radius: 5px;
}

.btn-save {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 10px 20px;
  background-color: #162938; /* Cor do site */
  color: #fff;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.btn-save:hover {
  background-color: #0e1f26; /* Efeito hover */
}

.btn-edit {
  display: flex;
  align-items: center; /* Centraliza verticalmente */
  justify-content: center; /* Centraliza horizontalmente */
  background-color: #162938; /* Cor do site */
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  white-space: nowrap; /* Evita que o texto quebre */
}

.btn-edit ion-icon {
  margin-right: 5px; /* Espaçamento entre o ícone e o texto */
}

.btn-edit:hover {
  background-color: #0e1f26;
}

.btn-cancel:hover {
  box-shadow: inset 0 0 0 1px #a9a9a9;
}
</style>
