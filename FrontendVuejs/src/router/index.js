import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import DietSelection from '../views/DietSelection.vue'
import DietCalculator from '../views/DietCalculator.vue'
import TermsAndServices from '../views/TermsAndServices.vue'
import PrivacyPolicy from '../views/PrivacyPolicy.vue'
import PasswordRecovery from '../components/PasswordRecovery.vue'
import VerifyCode from '@/components/VerifyCode.vue'
import ResetPassword from '@/components/ResetPassword.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/password-recovery',
    name: 'Password Recovery',
    component: PasswordRecovery
  },
  {
    path: '/verify-code',
    name: 'VerifyCode',
    component: VerifyCode
  },
  {
    path: '/reset-password',
    name: 'ResetPassword',
    component: ResetPassword
  },
  {
    path:'/diet',
    name: 'Diet',
    component: DietSelection,
    meta: { requiresAuth: true}
  },
  {
    path:'/diet/calculator',
    name:'Calculator',
    component: DietCalculator,
    meta: { requiresAuth: true}
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
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const isAuthenticated = localStorage.getItem('token');

  if (requiresAuth && !isAuthenticated) {
    next('/');
  } else {
    next();
  }
});

export default router
