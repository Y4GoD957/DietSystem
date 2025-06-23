<template>
  <TheHeader />
  <main class="privacy-policy">
    <section class="content-privacy">
      <h1 class="title">Política de Privacidade</h1>

      <p class="intro">
        Bem-vindo à Política de Privacidade do Diet System. Esta política descreve como coletamos,
        usamos e protegemos suas informações pessoais quando você utiliza nossos serviços.
      </p>

      <PolicySection
        v-for="(section, idx) in policySections"
        :key="idx"
        :title="section.title"
        :paragraphs="section.paragraphs"
        :list="section.list"
      />

      <ContactSection :contacts="contactInfo" />
    </section>
  </main>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import { h } from 'vue'
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'

const PolicySection = {
  props: ['title', 'paragraphs', 'list'],
  setup(props) {
    return () =>
      h('section', { class: 'policy-section' }, [
        h('h2', props.title),
        props.paragraphs
          ? props.paragraphs.map((p, i) => h('p', { key: i }, p))
          : null,
        props.list
          ? h(
              'ul',
              props.list.map((item, j) =>
                h('li', { key: j, innerHTML: item })
              )
            )
          : null
      ])
  }
}

const ContactSection = {
  props: ['contacts'],
  setup(props) {
    return () =>
      h('section', { class: 'policy-section' }, [
        h('h2', 'Contato'),
        h(
          'p',
          'Se você tiver alguma dúvida sobre esta Política de Privacidade, entre em contato conosco:'
        ),
        h(
          'ul',
          props.contacts.map((contact, idx) =>
            h(
              'li',
              { key: idx },
              [
                h('strong', contact.type + ': '),
                h('span', { innerHTML: contact.value })
              ]
            )
          )
        )
      ])
  }
}

export default {
  name: 'PrivacyPolicy',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton,
    PolicySection,
    ContactSection
  },
  data() {
    return {
      policySections: [
        {
          title: '1. Informações que Coletamos',
          paragraphs: [
            'Coletamos vários tipos de informações para fornecer e melhorar nossos serviços para você:'
          ],
          list: [
            '<strong>Informações Pessoais:</strong> Nome, endereço de e-mail, número de telefone e outras informações que você fornece ao se registrar.',
            '<strong>Informações de Uso:</strong> Detalhes de como você usa nosso serviço, incluindo dados de login, histórico de navegação e preferências.'
          ]
        },
        {
          title: '2. Como Usamos Suas Informações',
          paragraphs: ['Usamos as informações coletadas para:'],
          list: [
            'Fornecer, operar e manter nossos serviços.',
            'Melhorar, personalizar e expandir nossos serviços.',
            'Entender e analisar como você usa nossos serviços.',
            'Desenvolver novos produtos, serviços, recursos e funcionalidades.',
            'Comunicar-se com você, seja diretamente ou através de um de nossos parceiros, incluindo para atendimento ao cliente, para fornecer atualizações e outras informações relacionadas ao serviço, e para fins de marketing e promoção.',
            'Processar suas transações e gerenciar suas assinaturas.',
            'Encontrar e prevenir fraudes.'
          ]
        },
        {
          title: '3. Compartilhamento de Informações',
          paragraphs: ['Podemos compartilhar suas informações pessoais nas seguintes situações:'],
          list: [
            'Com prestadores de serviços que realizam serviços em nosso nome.',
            'Para cumprir obrigações legais.',
            'Para proteger e defender nossos direitos e propriedades.',
            'Com seu consentimento.'
          ]
        },
        {
          title: '4. Segurança de Dados',
          paragraphs: [
            'Implementamos medidas de segurança para proteger suas informações pessoais contra acesso não autorizado, alteração, divulgação ou destruição.'
          ]
        },
        {
          title: '5. Seus Direitos',
          paragraphs: [
            'Você tem o direito de acessar, corrigir ou excluir suas informações pessoais que mantemos. Para exercer esses direitos, entre em contato conosco através dos meios fornecidos nesta política.'
          ]
        },
        {
          title: '6. Alterações a Esta Política de Privacidade',
          paragraphs: [
            'Podemos atualizar nossa Política de Privacidade de tempos em tempos. Notificaremos você sobre quaisquer alterações publicando a nova Política de Privacidade nesta página.'
          ]
        }
      ],
      contactInfo: [
        { type: 'Email', value: 'support@dietsystem.com' },
        { type: 'Endereço', value: 'Rua Exemplo, 123, Cidade Exemplo, País Exemplo' }
      ]
    }
  }
}
</script>

<style scoped>
.privacy-policy {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding: 60px 20px 120px;
  color: #333;
  min-height: 80vh;
  display: flex;
  justify-content: center;
}

.content-privacy {
  max-width: 900px;
  width: 100%;
  background-color: white;
  padding: 40px 50px;
  border-radius: 12px;
  box-shadow: 0 4px 18px rgba(0, 0, 0, 0.1);
}

.title {
  font-size: 3rem;
  font-weight: 700;
  text-align: center;
  color: #2d2d2d;
  margin-bottom: 30px;
  position: relative;
}

.title::after {
  content: '';
  display: block;
  width: 72%;
  height: 5px;
  background-color: #4caf50;
  margin: 12px auto 0;
  border-radius: 3px;
}

.policy-section {
  margin-bottom: 2.5rem;
}

.policy-section h2 {
  font-size: 1.75rem;
  color: #162938;
  margin-bottom: 12px;
  border-bottom: 2px solid #4caf50;
  padding-bottom: 4px;
}

.policy-section p {
  font-size: 1.1rem;
  line-height: 1.6;
  margin-bottom: 1rem;
  text-align: justify;
}

.policy-section ul {
  list-style-type: disc;
  padding-left: 1.5rem;
}

.policy-section li {
  margin-bottom: 0.7rem;
  font-size: 1rem;
  line-height: 1.5;
}

/* Responsividade */
@media (max-width: 600px) {
  .content-privacy {
    padding: 30px 20px;
  }

  .title {
    font-size: 2.2rem;
  }

  .policy-section h2 {
    font-size: 1.4rem;
  }
}
</style>
