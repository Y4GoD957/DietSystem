import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/styles.css'
import './components/interactions.js'
import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:4444/'
  })

createApp(App).use(router).mount('#app')

export default instance;