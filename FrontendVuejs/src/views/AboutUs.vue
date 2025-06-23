<template>
  <TheHeader />

  <main class="about-section">
    <h1 class="about-title">Sobre Nós</h1>

    <div class="about-hero">
      <img
        class="about-image"
        src="@/assets/imgs/FachadaLoja.jpg"
        alt="Imagem da Fachada Diet System"
      />
    </div>

    <section class="about-company">
      <h2>Sobre a Diet System</h2>
      <p>
        Na Diet System, nossa missão é transformar vidas promovendo saúde e equilíbrio. Oferecemos soluções personalizadas com foco em dietas adaptadas, exercícios eficazes e cálculos precisos do metabolismo basal. Todos os serviços foram desenhados para ajudar você a atingir e manter seus objetivos de saúde e bem-estar.
      </p>
      <p>
        Nossos nutricionistas e especialistas em fitness criam planos considerando preferências alimentares, restrições e metas pessoais. Através da nossa plataforma de cálculo metabólico, nossos clientes compreendem melhor suas necessidades energéticas, facilitando estratégias alimentares e físicas mais eficazes.
      </p>
      <p>
        Por trás de tudo isso, está uma equipe dedicada de desenvolvedores apaixonados por tecnologia e usabilidade. Cada funcionalidade foi criada com dedicação, visando uma plataforma moderna, intuitiva e repleta de recursos úteis.
      </p>
    </section>

    <section class="about-developers">
      <h2>Nossos Desenvolvedores</h2>
      <div class="developer-list">
        <div class="developer-card">
          <a href="https://www.linkedin.com/in/yago-s-porto" target="_blank" rel="noopener">
            <img src="@/assets/imgs/Developer.jpg" alt="Yago Santos Porto" />
          </a>
          <h3>Yago Santos Porto</h3>
        </div>
      </div>
    </section>
  </main>

  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'

export default {
  name: 'AboutUs',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  }
}
</script>

<style scoped>
.about-section {
  padding: 2rem;
  margin-top: 100px;
  color: #333;
  font-family: Arial, sans-serif;
}

.about-title {
  font-size: 2.5rem;
  color: #4caf50;
  text-align: center;
  margin-bottom: 1rem;
}

.about-hero {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
}

.about-image {
  width: 40%;
  height: auto;
  border-radius: 15px;
  border: 2px solid #242424;
}

.about-company {
  max-width: 1000px;
  margin: 0 auto 3rem;
  text-align: justify;
}

.about-company h2 {
  font-size: 2rem;
  color: #4caf50;
  text-align: center;
  margin-bottom: 1.5rem;
}

.about-company p {
  font-size: 1.2rem;
  line-height: 1.6;
  margin-bottom: 1.5rem;
}

.about-developers {
  text-align: center;
  margin-bottom: 3rem;
}

.about-developers h2 {
  font-size: 2rem;
  color: #4caf50;
  margin-bottom: 2rem;
}

.developer-list {
  display: flex;
  justify-content: center;
  gap: 2rem;
  flex-wrap: wrap;
}

.developer-card {
  text-align: center;
}

.developer-card img {
  width: 12.5rem;
  height: 12.5rem;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.developer-card img:hover {
  transform: scale(1.1);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.developer-card h3 {
  margin-top: 0.75rem;
  font-size: 1.2rem;
  color: #333;
}
</style>
