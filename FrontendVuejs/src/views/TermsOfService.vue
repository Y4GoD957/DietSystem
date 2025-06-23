<template>
  <div>
    <TheHeader />

    <main class="terms-wrapper">
      <section class="terms-card">
        <h1 class="terms-title">Termos e Serviços</h1>

        <article v-for="(section, index) in sections" :key="index" class="terms-section">
          <template v-if="section.id !== 'privacidade'">
            <h2 class="terms-subtitle">{{ index + 1 }}. {{ section.title }}</h2>
            <p>{{ section.text }}</p>
          </template>

          <template v-else>
            <h2 class="terms-subtitle">{{ index + 1 }}. {{ section.title }}</h2>
            <p>
              Levamos sua privacidade a sério. Por favor, leia nossa
              <router-link to="/privacy-policy">Política de Privacidade</router-link>
              para entender como coletamos, usamos e protegemos suas informações.
            </p>
          </template>
        </article>
      </section>
    </main>

    <WhatsAppPopup />
    <ScrollToTopButton />
    <TheFooter />
  </div>
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'

export default {
  name: 'TermsAndServices',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      sections: [
        {
          id: 'introducao',
          title: 'Introdução',
          text: 'Bem-vindo ao Diet System! Ao utilizar nossos serviços, você concorda com estes termos e condições. Por favor, leia-os cuidadosamente.'
        },
        {
          id: 'uso',
          title: 'Uso dos Serviços',
          text: 'Nossos serviços são destinados a ajudá-lo a alcançar seus objetivos de saúde e bem-estar. Você concorda em usar nossos serviços de acordo com todas as leis e regulamentos aplicáveis.'
        },
        {
          id: 'conta',
          title: 'Conta de Usuário',
          text: 'Para acessar nossos serviços, você pode ser solicitado a criar uma conta. Você é responsável por manter a confidencialidade da sua conta e senha, e por restringir o acesso ao seu computador.'
        },
        {
          id: 'privacidade',
          title: 'Privacidade'
        },
        {
          id: 'alteracoes',
          title: 'Alterações nos Termos',
          text: 'Podemos modificar estes termos de tempos em tempos. As alterações serão publicadas nesta página, e o uso continuado dos nossos serviços após qualquer alteração constitui sua aceitação dos novos termos.'
        },
        {
          id: 'limite',
          title: 'Limitação de Responsabilidade',
          text: 'Nosso objetivo é fornecer serviços de alta qualidade, mas não garantimos que os nossos serviços estarão livres de erros ou interrupções. Não seremos responsáveis por quaisquer danos diretos, indiretos, incidentais ou consequenciais resultantes do uso ou da incapacidade de usar nossos serviços.'
        },
        {
          id: 'contato',
          title: 'Contato',
          text: 'Se você tiver alguma dúvida sobre estes Termos e Serviços, entre em contato conosco através do nosso formulário de contato.'
        }
      ]
    }
  }
}
</script>

<style scoped>
.terms-wrapper {
  display: flex;
  justify-content: center;
  padding: 6rem 1rem;
}

.terms-card {
  background: #fff;
  border-radius: 15px;
  border: 1px solid #e0e0e0;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
  padding: 2rem;
  max-width: 800px;
  width: 100%;
  box-sizing: border-box;
}

.terms-title {
  font-size: 2.5rem;
  font-weight: bold;
  color: #162938;
  text-align: center;
  margin-bottom: 2rem;
  position: relative;
}

.terms-title::after {
  content: '';
  display: block;
  width: 55%;
  height: 4px;
  background-color: #4caf50;
  margin: 10px auto 0;
  border-radius: 2px;
}

.terms-section {
  margin-bottom: 1.8rem;
}

.terms-subtitle {
  font-size: 1.25rem;
  font-weight: 600;
  color: #162938;
  margin-bottom: 0.5rem;
}

.terms-section p {
  color: #333;
  line-height: 1.6;
  margin: 0;
}
</style>
