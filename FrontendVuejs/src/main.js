import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// Estilos globais
import './assets/css/reset.css'
import './assets/css/layout.css'
import './assets/css/forms.css'

// Bootstrap CSS e JS
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

// Configuração do Axios com baseURL
import axios from 'axios'

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/',
  // Você pode adicionar headers, timeout, interceptors aqui se precisar
})

// Torna a instância axios globalmente acessível via app.config.globalProperties
const app = createApp(App)
app.config.globalProperties.$axios = axiosInstance

// Usa o roteador e monta o app
app.use(router).mount('#app')

// Exporta a instância axios para uso em outros arquivos
export default axiosInstance