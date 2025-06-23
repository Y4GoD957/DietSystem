<template>
  <TheHeader />
  <div class="content-container">
    <div class="heading">
      <h1>Saúde e Bem-Estar</h1>
    </div>

    <div class="section">
      <h2>O que é Metabolismo Basal?</h2>
      <p>
        O metabolismo basal é a quantidade de energia que seu corpo precisa para manter as funções
        vitais enquanto você está em repouso. Entender seu metabolismo basal é essencial para a
        formulação de dietas adequadas e para alcançar seus objetivos de saúde.
      </p>
      <p>
        Manter um metabolismo basal saudável é importante para a saúde geral e bem-estar, pois ajuda
        a regular o peso corporal e fornece a energia necessária para as funções diárias.
      </p>
      <div class="button-container">
        <label>Deseja calcular seu metabolismo basal?</label>
        <button class="action-button" @click="navigateToTMB">Calcular Metabolismo Basal</button>
      </div>
    </div>

    <div class="section">
      <h2>Importância de uma Dieta Bem Estruturada</h2>
      <p>
        Uma dieta equilibrada e bem planejada fornece os nutrientes necessários para o corpo
        funcionar corretamente, ajuda a manter um peso saudável e previne diversas doenças.
      </p>
      <p>
        Dietas saudáveis são fundamentais para o bem-estar a longo prazo e para manter um bom nível
        de energia, humor estável e saúde mental.
      </p>
      <div class="button-container">
        <label>Deseja formular uma dieta personalizada?</label>
        <button class="action-button" @click="navigateToDiet">Formular Dieta</button>
      </div>
    </div>
  </div>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'

export default {
  name: 'ServicesPage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      diet: null
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) {
      this.loadDietSettings(userId)
    }
  },
  methods: {
    loadDietSettings(userId) {
      const token = localStorage.getItem('token')
      if (!token) {
        this.alertError('Token de autenticação não encontrado. Faça login novamente.')
        return
      }

      axios
        .get(`/diet/diet-settings/${userId}`, {
          headers: { Authorization: `Bearer ${token}` }
        })
        .then((res) => {
          this.diet = res.data
        })
        .catch((err) => {
          console.error('Erro ao carregar a dieta:', err)
          this.alertError('Ocorreu um erro ao carregar as configurações de dieta.')
        })
    },

    validateUserAndDiet(callback) {
      const token = localStorage.getItem('token')
      const userId = localStorage.getItem('userId')

      if (!token || !userId) {
        return this.alertError('Você precisa estar logado para acessar esta funcionalidade.')
      }

      axios
        .get(`/diet/diet-settings/${userId}`, {
          headers: { Authorization: `Bearer ${token}` }
        })
        .then((res) => {
          if (res.data) {
            this.diet = res.data
            callback()
          } else {
            Swal.fire({
              icon: 'warning',
              title: 'Atenção',
              text: 'Não existem dados de dieta associados a este usuário.'
            })
          }
        })
        .catch((err) => {
          console.error('Erro ao verificar dados de dieta:', err)
          this.alertError('Erro ao verificar os dados de dieta. Tente novamente mais tarde.')
        })
    },

    alertError(msg) {
      Swal.fire({
        icon: 'error',
        title: 'Erro',
        text: msg
      })
    },

    navigateToTMB() {
      this.validateUserAndDiet(() => {
        Swal.fire({
          icon: 'success',
          title: 'Sucesso',
          text: 'Metabolismo Basal calculado com sucesso!'
        }).then(() => {
          this.$router.push('/services/tmb')
        })
      })
    },

    navigateToDiet() {
      this.validateUserAndDiet(() => {
        Swal.fire({
          icon: 'success',
          title: 'Sucesso',
          text: 'Dieta personalizada criada com sucesso!'
        }).then(() => {
          this.$router.push('/services/meals')
        })
      })
    }
  }
}
</script>

<style scoped>
.content-container {
  max-width: 1000px;
  margin: 7rem auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.heading {
  text-align: center;
  margin-bottom: 30px;
}

.heading h1 {
  font-size: 36px;
  color: #4caf50;
  position: relative;
}

.heading h1::after {
  content: '';
  position: absolute;
  width: 60%;
  height: 3px;
  background-color: #4caf50;
  left: 50%;
  transform: translateX(-50%);
  bottom: -10px;
}

.section {
  background-color: #ffffff;
  padding: 25px;
  border-radius: 10px;
  border: 1px solid #ddd;
  margin-bottom: 40px;
}

.section h2 {
  font-size: 28px;
  color: #162938;
  margin-bottom: 15px;
}

.section p {
  font-size: 16px;
  line-height: 1.6;
  color: #333;
  margin-bottom: 10px;
}

.button-container {
  margin-top: 20px;
  text-align: center;
}

.button-container label {
  display: block;
  font-weight: bold;
  color: #162938;
  margin-bottom: 10px;
}

.action-button {
  background-color: #4caf50;
  color: white;
  padding: 10px 22px;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
  font-weight: 600;
  transition: background-color 0.3s;
}

.action-button:hover {
  background-color: #45a049;
}
</style>
