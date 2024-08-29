<template>
  <div class="terms-container">
    <TheHeader />
    <div class="terms-content">
      <h1>Termos e Serviços</h1>

      <section>
        <h2>1. Introdução</h2>
        <p>
          Bem-vindo ao Diet System! Ao utilizar nossos serviços, você concorda com estes termos e
          condições. Por favor, leia-os cuidadosamente.
        </p>
      </section>

      <section>
        <h2>2. Uso dos Serviços</h2>
        <p>
          Nossos serviços são destinados a ajudá-lo a alcançar seus objetivos de saúde e bem-estar.
          Você concorda em usar nossos serviços de acordo com todas as leis e regulamentos
          aplicáveis.
        </p>
      </section>

      <section>
        <h2>3. Conta de Usuário</h2>
        <p>
          Para acessar nossos serviços, você pode ser solicitado a criar uma conta. Você é
          responsável por manter a confidencialidade da sua conta e senha, e por restringir o acesso
          ao seu computador.
        </p>
      </section>

      <section>
        <h2>4. Privacidade</h2>
        <p>
          Levamos sua privacidade a sério. Por favor, leia nossa
          <router-link to="/privacy-policy">Política de Privacidade</router-link> para entender como
          coletamos, usamos e protegemos suas informações.
        </p>
      </section>

      <section>
        <h2>5. Alterações nos Termos</h2>
        <p>
          Podemos modificar estes termos de tempos em tempos. As alterações serão publicadas nesta
          página, e o uso continuado dos nossos serviços após qualquer alteração constitui sua
          aceitação dos novos termos.
        </p>
      </section>

      <section>
        <h2>6. Limitação de Responsabilidade</h2>
        <p>
          Nosso objetivo é fornecer serviços de alta qualidade, mas não garantimos que os nossos
          serviços estarão livres de erros ou interrupções. Não seremos responsáveis por quaisquer
          danos diretos, indiretos, incidentais ou consequenciais resultantes do uso ou da
          incapacidade de usar nossos serviços.
        </p>
      </section>

      <section>
        <h2>7. Contato</h2>
        <p>
          Se você tiver alguma dúvida sobre estes Termos e Serviços, entre em contato conosco
          através do nosso formulário de contato.
        </p>
      </section>
    </div>
  </div>

  <TheFooter />
</template>

<script>
import TheFooter from '@/components/TheFooter.vue'
import TheHeader from '@/components/TheHeader.vue'

export default {
  name: 'TermsAndServices',
  components: {
    TheHeader,
    TheFooter
  }
}
</script>

<style scoped>
.terms-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  background: #fff;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 200px;
  margin-bottom: 200px;
}

.terms-content h1 {
  text-align: center;
  margin-bottom: 30px;
  color: #162938;
}

.terms-content h2 {
  margin-top: 20px;
  color: #162938;
}

.terms-content p {
  margin: 10px 0;
  line-height: 1.6;
  color: #333;
}
</style>
