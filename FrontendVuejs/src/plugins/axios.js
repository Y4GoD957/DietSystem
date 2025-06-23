// src/plugins/axios.js
import axios from 'axios'

// Criação da instância
const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

// Interceptor de requisição: adiciona o token no header
instance.interceptors.request.use(
  async (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

// Interceptor de resposta: trata erros globais
instance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      const status = error.response.status

      // Se o token estiver inválido ou expirado
      if (status === 401 || status === 403) {
        console.warn('Token inválido ou expirado. Redirecionando para login...')
        localStorage.removeItem('token')
        localStorage.removeItem('userId')

        // Redireciona para a tela de login, se estiver no contexto do navegador
        if (window && window.location) {
          window.location.href = '/login'
        }
      }
    }

    return Promise.reject(error)
  }
)

export default instance