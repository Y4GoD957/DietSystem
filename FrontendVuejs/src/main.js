import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/reset.css'
import './assets/css/layout.css'
import './assets/css/forms.css'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

const instance = axios.create({
  baseURL: 'http://localhost:8080/'
})

createApp(App).use(router).mount('#app')

export default instance
