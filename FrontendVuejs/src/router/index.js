import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import DietSelection from '../views/DietSelection.vue'
import TermsAndServices from '../views/TermsAndServices.vue'
import PrivacyPolicy from '../views/PrivacyPolicy.vue'
import AboutUs from '@/views/AboutUs.vue'
import ContactPage from '@/views/ContactPage.vue'
import ProfilePage from '@/views/ProfilePage.vue'
import ServicesPage from '@/views/ServicesPage.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/AboutUs',
    name: 'About Us',
    component: AboutUs
  },
  {
    path: '/Services',
    name: 'Services Page',
    component: ServicesPage
  },
  {
    path: '/ContactPage',
    name: 'Contact Page',
    component: ContactPage
  },
  {
    path: '/Profile',
    name: 'Profile Page',
    component: ProfilePage
  },
  {
    path: '/diet',
    name: 'Diet',
    component: DietSelection,
    meta: { requiresAuth: true }
  },
  {
    path: '/terms',
    name: 'Terms And Services',
    component: TermsAndServices
  },
  {
    path: '/privacy-policy',
    name: 'PrivacyPolicy',
    component: PrivacyPolicy
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some((record) => record.meta.requiresAuth)
  const isAuthenticated = localStorage.getItem('token')

  if (requiresAuth && !isAuthenticated) {
    next('/')
  } else {
    next()
  }
})

export default router
