<template>
  <TheHeader />
  <div class="privacy-policy">
    <div class="contentPrivacy">
      <h1>Política de Privacidade</h1>
      <br />
      <br />
      <p>
        Bem-vindo à Política de Privacidade do Diet System. Esta política descreve como coletamos,
        usamos e protegemos suas informações pessoais quando você utiliza nossos serviços.
      </p>

      <h2>1. Informações que Coletamos</h2>
      <p>
        Coletamos vários tipos de informações para fornecer e melhorar nossos serviços para você:
      </p>
      <ul>
        <li>
          <strong>Informações Pessoais:</strong> Nome, endereço de e-mail, número de telefone e
          outras informações que você fornece ao se registrar.
        </li>
        <li>
          <strong>Informações de Uso:</strong> Detalhes de como você usa nosso serviço, incluindo
          dados de login, histórico de navegação e preferências.
        </li>
      </ul>

      <h2>2. Como Usamos Suas Informações</h2>
      <p>Usamos as informações coletadas para:</p>
      <ul>
        <li>Fornecer, operar e manter nossos serviços.</li>
        <li>Melhorar, personalizar e expandir nossos serviços.</li>
        <li>Entender e analisar como você usa nossos serviços.</li>
        <li>Desenvolver novos produtos, serviços, recursos e funcionalidades.</li>
        <li>
          Comunicar-se com você, seja diretamente ou através de um de nossos parceiros, incluindo
          para atendimento ao cliente, para fornecer atualizações e outras informações relacionadas
          ao serviço, e para fins de marketing e promoção.
        </li>
        <li>Processar suas transações e gerenciar suas assinaturas.</li>
        <li>Encontrar e prevenir fraudes.</li>
      </ul>

      <h2>3. Compartilhamento de Informações</h2>
      <p>Podemos compartilhar suas informações pessoais nas seguintes situações:</p>
      <ul>
        <li>Com prestadores de serviços que realizam serviços em nosso nome.</li>
        <li>Para cumprir obrigações legais.</li>
        <li>Para proteger e defender nossos direitos e propriedades.</li>
        <li>Com seu consentimento.</li>
      </ul>

      <h2>4. Segurança de Dados</h2>
      <p>
        Implementamos medidas de segurança para proteger suas informações pessoais contra acesso não
        autorizado, alteração, divulgação ou destruição.
      </p>

      <h2>5. Seus Direitos</h2>
      <p>
        Você tem o direito de acessar, corrigir ou excluir suas informações pessoais que mantemos.
        Para exercer esses direitos, entre em contato conosco através dos meios fornecidos nesta
        política.
      </p>

      <h2>6. Alterações a Esta Política de Privacidade</h2>
      <p>
        Podemos atualizar nossa Política de Privacidade de tempos em tempos. Notificaremos você
        sobre quaisquer alterações publicando a nova Política de Privacidade nesta página.
      </p>

      <h2>7. Contato</h2>
      <p>
        Se você tiver alguma dúvida sobre esta Política de Privacidade, entre em contato conosco:
      </p>
      <ul>
        <li><strong>Email:</strong> support@dietsystem.com</li>
        <li><strong>Endereço:</strong> Rua Exemplo, 123, Cidade Exemplo, País Exemplo</li>
      </ul>
    </div>
  </div>
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'
import TheFooter from '@/components/TheFooter.vue'
import TheHeader from '@/components/TheHeader.vue'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'

export default {
  name: 'PrivacyPolicy',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  }
}
</script>

<style scoped>
.privacy-policy {
  padding: 20px;
  font-family: Arial, sans-serif;
  color: #333;
  margin-top: 200px;
  margin-bottom: 200px;
}

.contentPrivacy {
  max-width: 1000px; /* Ajuste a largura conforme necessário */
  margin: 0 auto; /* Centraliza o conteúdo */
}

.contentPrivacy h1 {
  font-size: 40px; /* Tamanho menor para ajustar conforme desejado */
  text-align: center;
  color: #000;
  margin-bottom: 15px;
  position: relative;
}

.contentPrivacy h1::after {
  content: '';
  position: absolute;
  width: 50%;
  height: 4px;
  display: block;
  margin-left: 250px;
  margin-top: 8px; /* Ajuste para alinhar com o texto */
  background-color: #4caf50;
}

.contentPrivacy h2 {
  font-size: 1.5em;
  margin-top: 1em;
}

.contentPrivacy p {
  line-height: 1.6;
  margin: 0.5em 0;
  text-align: justify; /* Justifica o texto */
}

.contentPrivacy ul {
  margin: 0.5em 0;
  padding-left: 20px;
}

.contentPrivacy li {
  margin-bottom: 0.5em;
}
</style>
