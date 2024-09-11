<template>
  <div>
    <TheHeader />
    <div class="profile-container">
      <!-- Common Heading -->
      <div class="heading">
        <h1>{{ heading }}</h1>
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

      <!-- Tab Content -->
      <div :class="['tab-content', { 'user-content': currentTab === 'usuarios' }]">
        <ProfileComponent v-if="currentTab === 'profile'" />
        <DietComponent v-if="currentTab === 'dieta'" />
        <ExerciseComponent v-if="currentTab === 'exercicios'" />
        <UsersComponent v-if="currentTab === 'usuarios'" />
      </div>
    </div>
    <WhatsAppPopup />
    <ScrollToTopButton />
    <TheFooter />
  </div>
</template>

<script>
import axios from '@/plugins/axios.js'
import TheHeader from '@/components/TheHeader.vue'
import TheFooter from '@/components/TheFooter.vue'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'
import ProfileComponent from '@/components/profile/ProfileComponent.vue'
import DietComponent from '@/components/profile/DietComponent.vue'
import ExerciseComponent from '@/components/profile/ExerciseComponent.vue'
import UsersComponent from '@/components/profile/UsersComponent.vue'

export default {
  name: 'ProfilePage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton,
    ProfileComponent,
    DietComponent,
    ExerciseComponent,
    UsersComponent
  },
  data() {
    return {
      currentTab: 'profile', // Tab inicial
      user: {} // Usuário logado
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) {
      this.loadUser(userId)
    }
  },
  methods: {
    selectTab(tab) {
      this.currentTab = tab
      this.updateHeading(tab)
    },
    loadUser(userId) {
      // Carregar dados do usuário
      axios
        .get(`/users/${userId}`)
        .then((response) => {
          this.user = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar o perfil:', error)
        })
    },
    updateHeading(tab) {
      switch (tab) {
        case 'profile':
          this.heading = 'Perfil de Usuário'
          break
        case 'dieta':
          this.heading = 'Dieta do Usuário'
          break
        case 'exercicios':
          this.heading = 'Exercícios do Usuário'
          break
        case 'usuarios':
          this.heading = 'Usuários'
          break
        default:
          this.heading = 'Perfil de Usuário'
      }
    }
  },
  mounted() {
    // Atualiza o título inicial com base na aba padrão
    this.updateHeading(this.currentTab)
  }
}
</script>

<style scoped>
/* Ajustar o fundo do site para o container principal */
:root {
  --site-background: #f5f5f5; /* Ajuste a cor de fundo aqui */
}

div {
  background-color: var(--site-background);
}

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
  border-bottom: 1px solid #ccc;
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

/* Aba ativa */
.tabs button.active {
  background-color: #162938;
  color: #fff;
  font-weight: bold;
  border-bottom: 1px solid #162938;
}

/* Hover nas abas */
.tabs button:hover {
  background-color: #e0e0e0;
  color: #162938;
}

/* Estilo para o conteúdo ativo da aba */
.tab-content {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 15px 15px 15px 15px;
  background-color: var(--site-background);
  margin-top: -1px;
}

/* Estilo específico para a aba "Usuários" */
.tab-content.user-content {
  border: none;
  border-radius: 0;
  background-color: transparent;
}
</style>
