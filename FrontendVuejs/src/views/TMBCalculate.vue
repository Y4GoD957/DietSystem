<template>
  <TheHeader />
  <section class="bmr-results">
    <div class="results-container">
      <h2 class="section-title">Resultado</h2>
      <div class="results-grid">
        <div class="stat-card">
          <div class="stat-title">TMB</div>
          <div class="stat-value">1518 kcal</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">Massa magra</div>
          <div class="stat-value">53.1 kg</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">Gasto calórico</div>
          <div class="stat-value">2087 kcal</div>
          <div class="stat-desc">Com base no nível de atividade física</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">Peso ideal</div>
          <div class="stat-value">72.1 kg</div>
        </div>
      </div>
    </div>

    <article class="info-section">
      <h2 class="info-title">Sobre a Taxa Metabólica Basal (TMB)</h2>
      <p>
        A taxa metabólica basal (TMB) refere-se à quantidade mínima de energia 
        necessária para que o corpo realize funções vitais, como respiração, circulação
         sanguínea e manutenção da temperatura corporal, enquanto em repouso. Ela corresponde
          a aproximadamente 60% a 70% do gasto energético total diário de uma pessoa, sendo 
          influenciada por fatores como sexo, idade, peso e altura.
      </p>
      <p>
        Compreender a TMB é essencial para quem deseja controlar o peso, pois permite estimar
         o número de calorias que o corpo consome diariamente, facilitando o ajuste da dieta 
         às necessidades energéticas do organismo. Além disso, conhecer a TMB pode auxiliar 
         na prevenção de doenças como obesidade, diabetes e hipertensão.
      </p>
      <p>
        Diversas fórmulas estão disponíveis para calcular a TMB, mas a equação de Harris-Benedict 
        é uma das mais utilizadas. Essa fórmula considera o sexo, idade, peso e altura da pessoa. 
        Originalmente desenvolvida em 1918, a equação foi revisada em 1984 para melhor refletir as 
        mudanças no estilo de vida da população.

        A seguir, apresentamos um exemplo de como aplicar a calculadora de TMB utilizando a equação 
        de Harris-Benedict.
      </p>
      <br>


      <h3 class="info-title">Cálculo Manual da TMB</h3>
      <p>Para calcular a TMB manualmente, você deve usar as seguintes fórmulas da equação de Harris-Benedict:</p>
      <ul>
        <li><strong>Homens:</strong> TMB = 66 + (13,7 x peso em kg) + (5 x altura em cm) - (6,8 x idade em anos)</li>
        <li><strong>Mulheres:</strong> TMB = 655 + (9,6 x peso em kg) + (1,8 x altura em cm) - (4,7 x idade em anos)</li>
      </ul>
      <br>
      <p>
        Por exemplo, para um homem de 30 anos, 90 kg e 1,75 m de altura:
        <br />
        TMB = 66 + (13,7 x 90) + (5 x 175) - (6,8 x 30) = 1970 calorias por dia.
      </p>
      <br>
      <p>
        Já no caso de uma mulher, supondo que ela tenha 20 anos, 65 kg e 1,65 m de altura:
        <br />
        TMB = 655 + (9,6 x 65) + (1,8 x 165) - (4,7 x 20) = 1482 calorias por dia.
      </p>
      <br>


      <h2 class="info-title">Fatores que Influenciam a TMB</h2>
      <p>
        A TMB é influenciada por uma variedade de fatores, incluindo:
      </p>
      <ul>
        <li><strong>Sexo:</strong>strong Homens geralmente apresentam uma TMB mais elevada do que mulheres, devido à maior quantidade de massa muscular e menor porcentagem de gordura corporal.</li>
        <li><strong>Idade:</strong> Com o envelhecimento, a TMB tende a diminuir, resultado da perda de massa muscular e aumento da gordura corporal.</li>
        <li><strong>Peso:</strong> Um peso corporal maior leva a uma TMB mais alta, já que o corpo necessita de mais energia para manter seus tecidos.</li>
        <li><strong>Altura:</strong> Pessoas mais altas geralmente têm uma TMB mais elevada, pois possuem uma maior superfície corporal, o que resulta em maior perda de calor.</li>
        <li><strong>Genética:</strong> Fatores hereditários podem fazer com que algumas pessoas tenham uma TMB naturalmente mais alta ou mais baixa do que outras.</li>
        <li><strong>Hormônios:</strong> Certos hormônios, como os da tireoide, podem influenciar a TMB, acelerando ou diminuindo o metabolismo.</li>
        <li><strong>Temperatura:</strong> A TMB pode aumentar em ambientes muito frios ou muito quentes, uma vez que o corpo precisa de mais energia para regular sua temperatura interna.</li>
        <li><strong>Jejum:</strong> Em períodos de jejum prolongado, a TMB tende a diminuir, pois o corpo entra em um estado de economia de energia, reduzindo o metabolismo.</li>
      </ul>
      <br>


      <h2 class="info-title">Como Aumentar a TMB</h2>
      <p>
        Aumentar a TMB pode ajudar a elevar o gasto energético do corpo em repouso, 
        o que contribui para o controle de peso e a melhora da saúde. Aqui estão 
        algumas estratégias para aumentar a TMB:
      </p>
      <ul>
        <li><strong>Aumentar a massa muscular:</strong> Praticar exercícios de força, como musculação, pilates ou crossfit, ajuda a aumentar a massa muscular e, consequentemente, a TMB, já que os músculos demandam mais energia do que a gordura para se manter.</li>
        <li><strong>Fazer exercícios aeróbicos:</strong> Realizar atividades que elevam a frequência cardíaca, como corrida, ciclismo ou natação, pode aumentar a TMB por algumas horas após o exercício, pois o corpo continua queimando calorias durante a recuperação.</li>
        <li><strong>Consumir alimentos termogênicos:</strong> Ingerir alimentos que aceleram o metabolismo, como pimenta, gengibre, canela, café e chá verde, contribui para o aumento da TMB, pois o organismo gasta mais energia para digeri-los.</li>
        <li><strong>Fracionar as refeições:</strong> Comer em pequenas porções várias vezes ao dia pode elevar a TMB, uma vez que o corpo utiliza mais energia para processar os alimentos. No entanto, é fundamental que essas refeições sejam saudáveis e balanceadas.</li>
        <li><strong>Dormir bem:</strong> Manter uma boa qualidade de sono regula hormônios que influenciam a TMB, como cortisol e leptina. Além disso, dormir adequadamente reduz o cansaço, facilitando a prática de atividades físicas.</li>
      </ul>
      <br>


      <h2 class="info-title">Referências</h2>
      <ul>
        <a class="links" href="https://pubmed.ncbi.nlm.nih.gov/2305711/" target="_blank"><li><strong>Mifflin MD, St Jeor ST, Hill LA, Scott BJ, Daugherty SA, Koh YO. A new predictive equation for resting energy expenditure in healthy individuals. Am J Clin Nutr. 1990 Feb;51(2):241-7. doi: 10.1093/ajcn/51.2.241.</strong></li></a>
        <a class="links" href="https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1091498/" target="_blank"><li><strong>HARRIS JA, BENEDICT FG. A Biometric Study of Human Basal Metabolism. Proc Natl Acad Sci U S A. 1918 Dec;4(12):370-3. doi: 10.1073/pnas.4.12.370.</strong></li></a>
        <a class="links" href="https://pubmed.ncbi.nlm.nih.gov/6741850/" target="_blank"><li><strong>ROZA AM, SHIZGAL HM. The Harris Benedict equation reevaluated: resting energy requirements and the body cell mass. Am J Clin Nutr. 1984 Aug;40(2):168-82. doi: 10.1093/ajcn/40.2.168.</strong></li></a>
      </ul>
      <br>
    </article>
  </section>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import ScrollToTopButton from '@/components/ScrollToTopButton.vue';
import TheFooter from '@/components/TheFooter.vue';
import TheHeader from '@/components/TheHeader.vue';
import WhatsAppPopup from '@/components/WhatsAppPopup.vue';

export default {
  name: "TMBCalculate",
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
};
</script>

<style scoped>
.bmr-results {
  padding: 20px;
  margin: 0 auto;
  max-width: 62.5rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 7rem;
  margin-bottom: 7rem;
}

.results-container {
  text-align: center;
}

.section-title {
  font-size: 2em;
  margin-bottom: 20px;
  color: #333;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  margin-bottom: 20px;
}

.stat-card {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-title {
  font-size: 1.2em;
  color: #555;
}

.stat-value {
  font-size: 2em;
  color: #222;
  margin-top: 10px;
}

.stat-desc {
  font-size: 0.9em;
  color: #777;
  margin-top: 5px;
}

.info-section {
  margin-top: 40px;
  line-height: 1.6;
}

.info-title {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #333;
}

.info-section p {
  margin-bottom: 20px;
}

.info-section ul {
  list-style-type: disc;
  margin-left: 20px;
}

.info-section ul li {
  margin-bottom: 10px;
}

.links {
            color: inherit; /* Define a cor dos links para herdar a cor do texto */
            text-decoration: none; /* Remove o sublinhado padrão */
        }

        .links:visited {
            color: inherit; /* Mantém a mesma cor após o link ser visitado */
        }

        .links:hover, .links:focus, .links:active {
            text-decoration: underline; /* Adiciona sublinhado ao passar o mouse ou focar */
        }
</style>