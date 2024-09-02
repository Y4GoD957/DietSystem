<template>
  <TheHeader />

  <div class="about-us">
    <h1 class="page-title">Sobre Nós</h1>
    <div class="hero">
      <img src="@/assets/FachadaLoja.jpg" alt="Diet System" />
    </div>
    <section class="company-info">
      <h2>Sobre a Diet System</h2>
      <p>
        Na Diet System, nossa missão é transformar a vida de nossos clientes, promovendo uma saúde
        melhor e um estilo de vida equilibrado. Fundada com o objetivo de oferecer soluções
        personalizadas para aqueles que buscam uma vida mais saudável, nossa empresa se especializa
        em fornecer dietas adaptadas às necessidades individuais, exercícios de aquecimento
        eficientes e cálculos precisos do metabolismo basal. Cada um de nossos serviços é desenhado
        com o propósito de apoiar nossos clientes em suas jornadas de saúde e bem-estar, ajudando-os
        a alcançar e manter seus objetivos.
      </p>
      <br />
      <p>
        A Diet System se orgulha de oferecer um atendimento de alta qualidade e uma abordagem
        personalizada para cada cliente. Nossos nutricionistas e especialistas em fitness trabalham
        em conjunto para criar planos que atendam às necessidades específicas de cada pessoa,
        levando em consideração suas preferências alimentares, restrições e metas de saúde. Além
        disso, nossa plataforma de cálculo de metabolismo basal permite que nossos clientes
        compreendam melhor suas necessidades energéticas, facilitando a criação de estratégias de
        dieta e exercício mais eficazes e personalizadas.
      </p>
      <br />
      <p>
        Por trás de cada inovação e serviço oferecido pela Diet System está uma equipe dedicada de
        desenvolvedores que trabalha incansavelmente para garantir a excelência do nosso projeto.
        Nossos desenvolvedores são apaixonados por tecnologia e comprometidos com a criação de uma
        experiência de usuário intuitiva e eficiente. Cada recurso e funcionalidade do nosso site é
        o resultado do trabalho árduo e da dedicação dessa equipe talentosa, que se empenha para
        garantir que cada cliente tenha acesso a uma plataforma fácil de usar e repleta de
        funcionalidades úteis. A Diet System é, portanto, não apenas um reflexo de nossa paixão pela
        saúde, mas também da competência e dedicação dos nossos desenvolvedores, que estão sempre em
        busca de aprimorar e inovar para melhor servir nossos clientes.
      </p>
    </section>
    <section class="developers">
      <h2>Nossos Desenvolvedores</h2>
      <div class="developers-list">
        <div class="developer">
            <a href="https://www.linkedin.com/in/yago-santos-porto/" target="_blank">
             <img src="@/assets/Developer.jpg" alt="Desenvolvedor 1" />
            </a>
          <h3>Yago Santos Porto</h3>
        </div>
      </div>
    </section>
  </div>

  <TheFooter />
</template>

<script>
import TheFooter from '@/components/TheFooter.vue'
import TheHeader from '@/components/TheHeader.vue'

export default {
  name: 'AboutUs',
  components: {
    TheHeader,
    TheFooter
  }
}
</script>

<style scoped>
.about-us {
  font-family: Arial, sans-serif;
  color: #333;
  padding: 20px;
  margin-top: 100px;
}

.page-title {
  margin-bottom: 10px;
  text-align: center;
  font-size: 2.5em;
  color: #4caf50;
}

.hero {
  text-align: center;
}

.hero img {
  width: 40%;
  height: auto;
  border-radius: 15px;
  border: 2px solid #242424;
}

.company-info,
.developers {
  margin: 40px 0;
}

.company-info h2,
.developers h2 {
  font-size: 2em;
  margin-bottom: 20px;
  text-align: center;
  color: #4caf50;
}

.company-info p {
  font-size: 1.2em;
  line-height: 1.6;
  margin: 0 20px;
  text-align: justify;
}

.developers-list {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 40px;
  margin: 40px 0;
}

.developer {
  text-align: center;
  margin: 20px;
}

.developer img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.developer h3 {
  margin-top: 10px;
  font-size: 1.2em;
  color: #333;
}
</style>
