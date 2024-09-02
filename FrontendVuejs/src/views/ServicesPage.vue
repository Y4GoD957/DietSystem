<template>
  <TheHeader />
  <div class="content-container">
    <div class="heading">
      <h1>Saúde e Bem-Estar</h1>
    </div>

    <!-- Introdução ao Metabolismo Basal -->
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
        <button class="action-button" @click="calculateBMR">Calcular Metabolismo Basal</button>
      </div>
    </div>

    <!-- Introdução à Formulação de Dietas -->
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
        <button class="action-button" @click="createDiet">Formular Dieta</button>
      </div>
    </div>

    <!-- Introdução à Recomendação de Exercícios -->
    <div class="section">
      <h2>Recomendações de Exercícios Físicos</h2>
      <p>
        Exercícios físicos regulares são essenciais para manter a saúde do coração, melhorar a força
        muscular, a flexibilidade e proporcionar um bem-estar geral. Além disso, ajudam a controlar
        o peso e melhoram o humor e a saúde mental.
      </p>
      <p>
        Incorporar uma rotina de exercícios na sua vida diária pode trazer inúmeros benefícios para
        a sua saúde física e mental.
      </p>
      <div class="button-container">
        <label>Deseja obter uma recomendação de exercícios?</label>
        <button class="action-button" @click="getExerciseRecommendation">
          Gerar Recomendação de Exercícios
        </button>
      </div>
    </div>
  </div>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'
import TheFooter from '@/components/TheFooter.vue'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'

export default {
  name: 'ServicesPage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  methods: {
    calculateBMR() {
      // Lógica para calcular o metabolismo basal
      alert('Função para calcular o metabolismo basal não implementada.')
    },
    createDiet() {
      // Lógica para criar uma dieta personalizada
      alert('Função para formular uma dieta não implementada.')
    },
    getExerciseRecommendation() {
      // Lógica para obter uma recomendação de exercícios
      alert('Função para recomendação de exercícios não implementada.')
    }
  }
}
</script>

<style scoped>
.content-container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 7rem;
  margin-top: 7rem;
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
  margin-bottom: 40px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  border: 1px solid #ddd;
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
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.action-button:hover {
  background-color: #45a049;
}
</style>
