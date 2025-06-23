import { createRouter, createWebHistory } from 'vue-router'

// Páginas públicas
import HomePage from '@/views/Home.vue'
import AboutUs from '@/views/AboutUs.vue'
import ContactPage from '@/views/ContactUs.vue'
import TermsAndServices from '@/views/TermsOfService.vue'
import PrivacyPolicy from '@/views/PrivacyPolicy.vue'

// Páginas do usuário
import ProfilePage from '@/views/ProfilePage.vue'

// Serviços
import ServicesPage from '@/views/OurServices.vue'
import TMBCalculate from '@/views/TMBCalculator.vue'
import MealsPage from '@/views/MealsOverview.vue'
import DietSelection from '@/views/SelectDietPlan.vue'

const routes = [
  // Rotas Públicas
  { path: '/', name: 'Home', component: HomePage },
  { path: '/about', name: 'AboutUs', component: AboutUs },
  { path: '/contact', name: 'Contact', component: ContactPage },
  { path: '/terms', name: 'TermsOfService', component: TermsAndServices },
  { path: '/privacy-policy', name: 'PrivacyPolicy', component: PrivacyPolicy },

  // Perfil do usuário (autenticado)
  {
    path: '/profile',
    name: 'Profile',
    component: ProfilePage,
    meta: { requiresAuth: true }
  },

  // Serviços (autenticados)
  {
    path: '/services',
    name: 'Services',
    component: ServicesPage,
    meta: { requiresAuth: true }
  },
  {
    path: '/services/tmb',
    name: 'TMBCalculator',
    component: TMBCalculate,
    meta: { requiresAuth: true }
  },
  {
    path: '/services/meals',
    name: 'MealsOverview',
    component: MealsPage,
    meta: { requiresAuth: true }
  },
  {
    path: '/diet',
    name: 'DietSelection',
    component: DietSelection,
    meta: { requiresAuth: true }
  },

  // Fallback 404 (opcional)
  {
    path: '/:catchAll(.*)',
    redirect: '/'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Middleware Global para rotas protegidas
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some((record) => record.meta.requiresAuth)
  const token = localStorage.getItem('token')

  if (requiresAuth && !token) {
    // Redireciona para login com retorno automático após login, se preferir
    next({ path: '/', query: { redirect: to.fullPath } })
  } else {
    next()
  }
})

export default router