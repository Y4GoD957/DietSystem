<template>
  <div>
    <TheHeader />
    <div class="profile-container">
      <!-- Heading -->
      <div class="heading">
        <h1>{{ heading }}</h1>
      </div>

      <!-- Tabs -->
      <nav class="tabs-container" aria-label="Navegação de abas">
        <div class="tabs" role="tablist">
          <button
            role="tab"
            :aria-selected="currentTab === 'profile'"
            :class="{ active: currentTab === 'profile' }"
            @click="selectTab('profile')"
            tabindex="0"
          >
            Perfil
          </button>
          <button
            role="tab"
            :aria-selected="currentTab === 'dieta'"
            :class="{ active: currentTab === 'dieta' }"
            @click="selectTab('dieta')"
            tabindex="-1"
          >
            Dieta
          </button>
          <button
            v-if="user.position === 'admin'"
            role="tab"
            :aria-selected="currentTab === 'usuarios'"
            :class="{ active: currentTab === 'usuarios' }"
            @click="selectTab('usuarios')"
            tabindex="-1"
          >
            Usuários
          </button>
        </div>
      </nav>

      <!-- Tab Content -->
      <section
        :class="['tab-content', { 'user-content': currentTab === 'usuarios' }]"
        role="tabpanel"
      >
        <ProfileComponent v-if="currentTab === 'profile'" />
        <DietComponent v-if="currentTab === 'dieta'" />
        <UsersComponent v-if="currentTab === 'usuarios'" />
      </section>
    </div>
    <WhatsAppPopup />
    <ScrollToTopButton />
    <TheFooter />
  </div>
</template>

<script>
import axios from '@/plugins/axios.js'
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'
import ProfileComponent from '@/components/profile/UserProfile.vue'
import DietComponent from '@/components/profile/ProfileDiet.vue'
import UsersComponent from '@/components/profile/UserManagement.vue'

export default {
  name: 'ProfilePage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton,
    ProfileComponent,
    DietComponent,
    UsersComponent
  },
  data() {
    return {
      currentTab: 'profile',
      user: {}
    }
  },
  computed: {
    heading() {
      const titles = {
        profile: 'Perfil de Usuário',
        dieta: 'Dieta do Usuário',
        usuarios: 'Usuários'
      }
      return titles[this.currentTab] || 'Perfil de Usuário'
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) this.loadUser(userId)
  },
  methods: {
    selectTab(tab) {
      this.currentTab = tab
    },
    async loadUser(userId) {
      try {
        const { data } = await axios.get(`/users/${userId}`)
        this.user = data
      } catch (error) {
        console.error('Erro ao carregar o perfil:', error)
      }
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
  margin: 20px auto;
  text-align: center;
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
  bottom: 0;
  left: 50%;
  width: 65%;
  height: 4px;
  background-color: #4caf50;
  transform: translateX(-50%);
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
  transition: background-color 0.3s, color 0.3s;
  margin: 0 3px;
}

.tabs button.active {
  background-color: #162938;
  color: #fff;
  font-weight: bold;
  border-bottom: 1px solid #162938;
}

.tabs button:hover:not(.active) {
  background-color: #e0e0e0;
  color: #162938;
}

.tab-content {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 15px;
  background-color: #fff;
  margin-top: -1px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.tab-content.user-content {
  border: none;
  border-radius: 0;
  background-color: transparent;
  box-shadow: none;
}
</style>
