<template>
  <TheHeader />

  <section class="bmr-section">
    <div class="bmr-wrapper">
      <h2 class="section-title">Resultado</h2>
      <div class="results-grid">
        <div class="stat-card" v-for="(value, key) in statMap" :key="key">
          <div class="stat-title">{{ value.title }}</div>
          <div class="stat-value">{{ metrics[key] }} {{ value.suffix }}</div>
          <div class="stat-desc" v-if="value.description">{{ value.description }}</div>
        </div>
      </div>

      <article class="info-section">
        <h2 class="info-title">Sobre a Taxa Metabólica Basal (TMB)</h2>
        <p>
          A taxa metabólica basal (TMB) é a energia mínima que o corpo precisa para manter funções vitais em repouso. Representa 60% a 70% do gasto energético diário e varia conforme sexo, idade, peso e altura.
        </p>
        <p>
          Entender sua TMB ajuda a planejar a alimentação de forma equilibrada, contribuindo para o controle de peso e prevenção de doenças como obesidade e diabetes.
        </p>
        <p>
          A fórmula mais usada para esse cálculo é a Equação de Harris-Benedict, revisada em 1984. Veja como calcular manualmente:
        </p>

        <h3 class="info-subtitle">Cálculo Manual da TMB</h3>
        <ul>
          <li><strong>Homens:</strong> TMB = 66 + (13,7 × peso kg) + (5 × altura cm) − (6,8 × idade)</li>
          <li><strong>Mulheres:</strong> TMB = 655 + (9,6 × peso kg) + (1,8 × altura cm) − (4,7 × idade)</li>
        </ul>

        <p><strong>Exemplo (Homem):</strong> 66 + (13,7 × 90) + (5 × 175) − (6,8 × 30) = 1970 kcal</p>
        <p><strong>Exemplo (Mulher):</strong> 655 + (9,6 × 65) + (1,8 × 165) − (4,7 × 20) = 1482 kcal</p>

        <h2 class="info-title">Fatores que Influenciam a TMB</h2>
        <ul>
          <li><strong>Sexo:</strong> Homens tendem a ter TMB mais alta.</li>
          <li><strong>Idade:</strong> Envelhecimento reduz a TMB.</li>
          <li><strong>Peso:</strong> Quanto maior o peso, maior a TMB.</li>
          <li><strong>Altura:</strong> Pessoas mais altas tendem a ter TMB maior.</li>
          <li><strong>Genética:</strong> Pode influenciar significativamente.</li>
          <li><strong>Hormônios:</strong> Especialmente os da tireoide.</li>
          <li><strong>Temperatura:</strong> Ambientes extremos afetam a TMB.</li>
          <li><strong>Jejum:</strong> Reduz temporariamente o metabolismo.</li>
        </ul>

        <h2 class="info-title">Como Aumentar a TMB</h2>
        <ul>
          <li><strong>Ganhar massa muscular:</strong> Aumenta o metabolismo basal.</li>
          <li><strong>Exercícios aeróbicos:</strong> Elevam o gasto calórico pós-treino.</li>
          <li><strong>Alimentos termogênicos:</strong> Pimenta, café e chá verde, por exemplo.</li>
          <li><strong>Fracionar refeições:</strong> Ajuda na digestão contínua.</li>
          <li><strong>Dormir bem:</strong> Regula os hormônios do metabolismo.</li>
        </ul>

        <h2 class="info-title">Referências</h2>
        <ul class="references">
          <li><a href="https://pubmed.ncbi.nlm.nih.gov/2305711/" target="_blank">Mifflin MD et al. – Am J Clin Nutr (1990)</a></li>
          <li><a href="https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1091498/" target="_blank">Harris JA & Benedict FG – PNAS (1918)</a></li>
          <li><a href="https://pubmed.ncbi.nlm.nih.gov/6741850/" target="_blank">Roza AM & Shizgal HM – Am J Clin Nutr (1984)</a></li>
        </ul>
      </article>
    </div>
  </section>

  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'
import axios from '@/plugins/axios.js'

export default {
  name: 'TMBCalculate',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      metrics: {
        tmb: '',
        caloric_expenditure: '',
        imc: '',
        ideal_weight: ''
      },
      statMap: {
        tmb: { title: 'TMB', suffix: 'kcal' },
        imc: { title: 'IMC', suffix: '' },
        caloric_expenditure: { title: 'Gasto calórico', suffix: 'kcal', description: 'Com base no nível de atividade física' },
        ideal_weight: { title: 'Peso ideal', suffix: 'kg' }
      }
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) this.loadMetrics(userId)
  },
  methods: {
    loadMetrics(userId) {
      axios.get(`/diet/metrics/${userId}`)
        .then(({ data }) => { this.metrics = data })
        .catch((error) => {
          console.error('Erro ao carregar as métricas:', error)
        })
    }
  }
}
</script>

<style scoped>
.bmr-section {
  padding: 80px 20px;
}

.bmr-wrapper {
  background: #ffffff;
  padding: 40px 30px;
  border-radius: 16px;
  max-width: 960px;
  margin: 0 auto;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.section-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 30px;
  color: #162938;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* 2 cards por linha */
  gap: 30px;
  margin-bottom: 40px;
}

.stat-card {
  background-color: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  padding: 24px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  text-align: center;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.stat-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 18px rgba(0, 0, 0, 0.12);
}

.stat-title {
  font-weight: 600;
  font-size: 1.2rem;
  color: #162938;
}

.stat-value {
  font-size: 2rem;
  font-weight: bold;
  color: #162938;
  margin-top: 10px;
}

.stat-desc {
  font-size: 0.9rem;
  color: #555;
  margin-top: 5px;
}

.info-section {
  margin-top: 40px;
  line-height: 1.7;
  color: #333;
}

.info-title {
  font-size: 1.5rem;
  margin-top: 30px;
  color: #162938;
}

.info-subtitle {
  font-size: 1.3rem;
  margin-top: 20px;
  font-weight: 600;
  color: #333;
}

.info-section p {
  margin-bottom: 16px;
}

.info-section ul {
  list-style-type: disc;
  padding-left: 1.2rem;
  margin-bottom: 24px;
}

.references li {
  margin-bottom: 10px;
}

.references a {
  color: #4caf50;
  text-decoration: underline;
}

.references a:hover {
  color: #388e3c;
}

@media (max-width: 600px) {
  .results-grid {
    grid-template-columns: 1fr;
  }
}
</style>