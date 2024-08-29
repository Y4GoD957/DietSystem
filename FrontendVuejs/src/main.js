import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/styles.css'
import './components/UserInteractions.vue'
import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080/'
})

createApp(App).use(router).mount('#app')

export default instance
