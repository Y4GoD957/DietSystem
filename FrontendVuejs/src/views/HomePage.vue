<template>
  <TheHeader />

  <section id="main" class="diet-system-section section-main">
    <div class="content-wrapper">
      <div class="left-column">
        <div class="image-wrapper">
          <img src="@/assets/mainImg.jpg" alt="Imagem da Diet System" />
        </div>
      </div>
      <div class="right-column">
        <div class="text-wrapper">
          <h1>O que é Diet System?</h1>
          <br />
          <p>
            Diet System é uma empresa dedicada à regulagem dietética e à promoção da saúde
            alimentar. Nosso foco é proporcionar dietas personalizadas e orientação nutricional para
            ajudar nossos clientes a alcançar seus objetivos de saúde.
          </p>
          <p>
            Nossa abordagem é baseada em evidências científicas, combinando conhecimento
            especializado com tecnologia para oferecer soluções eficazes e personalizadas.
          </p>
          <p>
            A Diet System tem como missão principal cuidar da saúde dos seus clientes, oferecendo
            planos alimentares adequados às necessidades individuais, garantindo assim uma vida mais
            saudável e equilibrada.
          </p>
        </div>
      </div>
    </div>
  </section>

  <section id="about" class="section-about">
    <div class="heading">
      <h1>Sobre Nós</h1>
      <p>
        Oi! Somos uma equipe de desenvolvedores super entusiasmados com o mundo da tecnologia.
        Adoramos criar coisas novas e resolver problemas complexos, sempre com um sorriso no rosto e
        uma xícara de café na mão.
      </p>
    </div>
    <div class="container">
      <section class="about">
        <div class="about-image">
          <div class="image-wrapper">
            <img src="@/assets/coffee.jpg" alt="Imagem Sobre Nós" />
          </div>
        </div>
        <div class="about-content">
          <h2>Nada Como um Café Quentinho</h2>
          <p>
            Cada um de nós traz um conjunto único de habilidades para a mesa, desde a criação de
            interfaces bonitas e funcionais até a construção de sistemas backend robustos. Gostamos
            de trabalhar em um ambiente colaborativo onde todas as ideias são bem-vindas e a
            criatividade é incentivada.
          </p>
          <a href="/AboutUs" class="read-more">Leia Mais</a>
        </div>
      </section>
    </div>
  </section>

  <section id="services" class="section-services">
    <div class="heading">
      <h1>Nossos Serviços</h1>
      <p>Aqui estão alguns dos serviços que oferecemos para nossos clientes:</p>
    </div>
    <div class="container">
      <div class="card p-2" style="width: 18rem; height: 100%">
        <div class="card-body">
          <h3 class="card-title">Calcular metabolismo basal.</h3>
          <br />
          <p class="card-text">
            Nosso serviço de calcular o metabolismo basal utiliza metodologias avançadas para
            determinar a taxa metabólica basal de cada indivíduo. Isso permite uma compreensão
            precisa de quantas calorias seu corpo queima em repouso, fornecendo uma base sólida para
            ajustar sua dieta e alcançar seus objetivos de saúde.
          </p>
          <br />
          <button class="btn-service" @click="goToTMB">Ir para o Serviço</button>
        </div>
      </div>

      <div class="card p-2" style="width: 18rem; height: 100%">
        <div class="card-body">
          <h3 class="card-title">Fazer uma dieta personalizada.</h3>
          <br />
          <p class="card-text">
            Criamos dietas personalizadas que são adaptadas às suas necessidades específicas,
            levando em consideração seu metabolismo, objetivos de saúde e futuramente preferências
            alimentares. Nossa abordagem é guiada por nutricionistas qualificados para garantir que
            você receba um plano alimentar equilibrado e sustentável.
          </p>
          <br />
          <button class="btn-service" @click="goToDiet">Ir para o Serviço</button>
        </div>
      </div>

      <div class="card p-2" style="width: 18rem; height: 100%">
        <div class="card-body">
          <h3 class="card-title">Recomendação de Exercícios.</h3>
          <br />
          <p class="card-text">
            A prática regular de exercícios físicos é essencial para manter o corpo saudável e
            prevenir uma série de problemas de saúde. No entanto, não basta simplesmente se
            exercitar; é fundamental que os exercícios sejam realizados de forma adequada,
            respeitando as particularidades de cada indivíduo. Por isso, a recomendação de
            exercícios físicos personalizados desempenha um papel crucial.
          </p>
          <button class="btn-service" @click="goToExercise">Ir para o Serviço</button>
        </div>
      </div>
    </div>
  </section>

  <section id="contact" class="section-contact">
    <div class="wrapperContact">
      <h2 class="titleContact">Fale Conosco</h2>

      <form @submit.prevent="handleContact" class="form-contact">
        <div class="input-box-homepage">
          <span class="icon"><ion-icon name="person"></ion-icon></span>
          <input type="text" v-model="formDataContact.name" placeholder=" " />
          <label>Nome Completo</label>
        </div>

        <div class="input-box-homepage">
          <span class="icon"><ion-icon name="mail"></ion-icon></span>
          <input type="email" v-model="formDataContact.email" placeholder=" " />
          <label>E-mail</label>
        </div>

        <div class="input-box-homepage">
          <span class="icon"><ion-icon name="call"></ion-icon></span>
          <input type="text" v-model="formDataContact.phone" placeholder=" " />
          <label>Celular</label>
        </div>

        <div class="form-floating">
          <textarea
            class="form-control"
            v-model="formDataContact.message"
            placeholder="Sua Mensagem"
            style="height: 100px"
          ></textarea>
          <label for="floatingTextarea2">Feedback</label>
        </div>

        <button type="submit" class="btn-homepage">Enviar</button>
      </form>
    </div>
  </section>
  <ScrollToTopButton />
  <WhatsAppPopup />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'
import TheFooter from '@/components/TheFooter.vue'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'

export default {
  name: 'HomePage',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      formDataContact: {
        name: '',
        email: '',
        phone: '',
        message: ''
      }
    }
  },
  methods: {
    async handleContact() {
      if (!this.isUserLoggedIn()) {
        Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'Você precisa estar logado para enviar o formulário.'
        })
        return
      }

      const loggedInUserEmail = localStorage.getItem('userEmail')

      if (this.formDataContact.email !== loggedInUserEmail) {
        Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'O email do formulário não corresponde ao email do usuário logado.'
        })
        return
      }

      if (this.validateForm()) {
        try {
          const response = await axios.get(`/users/contact/${this.formDataContact.email}`)

          if (response.status === 200 && response.data) {
            Swal.fire({
              title: 'Dados já existentes',
              text: 'Você já enviou dados de contato. Deseja alterar?',
              icon: 'warning',
              showCancelButton: true,
              confirmButtonText: 'Sim, alterar',
              cancelButtonText: 'Cancelar'
            }).then(async (result) => {
              if (result.isConfirmed) {
                this.submitContactData()
              }
            })
          } else {
            this.submitContactData()
          }
        } catch (error) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível verificar os dados existentes. Tente novamente mais tarde.'
          })
        }
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Por favor, preencha todos os campos corretamente.'
        })
      }
    },

    async submitContactData() {
      try {
        const token = localStorage.getItem('token')

        const response = await axios.post('/users/contact', this.formDataContact, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })

        if (response.status === 200) {
          localStorage.setItem('userEmail', response.data.email)
          Swal.fire({
            icon: 'success',
            title: 'Sucesso',
            text: 'Dados enviados com sucesso!'
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível enviar os dados. Tente novamente mais tarde.'
          })
        }
      } catch (error) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Ocorreu um erro ao enviar os dados. Tente novamente mais tarde.'
        })
      }
    },

    isUserLoggedIn() {
      return !!localStorage.getItem('token')
    },

    validateForm() {
      const { name, email, phone, message } = this.formDataContact
      return name && email && phone && message !== ''
    },
    goToTMB() {
      this.$router.push('/Services/TMBCalculate')
    },
    goToDiet() {
      this.$router.push('/Services/MealsPage')
    },
    goToExercise() {
      this.$router.push('/Services/ExerciseRecommendation')
    }
  }
}
</script>

<style scoped>
.btn-service {
  width: 200px;
  height: 40px;
  background: transparent;
  border: 1.5px solid #162938;
  outline: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1.1em;
  color: #162938;
  font-weight: 500;
  transition: 0.5s;
}

.btn-service:hover {
  background: #162938;
  color: #fff;
}

.container {
  display: flex;
  justify-content: center;
  gap: 80px; /* Espaçamento entre os cartões */
}

h1 {
  font-weight: bold;
}

h2 {
  font-weight: bold;
}

#main {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 100px;
  margin-bottom: 200px;
  padding: 0 20px;
  position: relative; /* Adicionado para posicionamento relativo */
  left: 50%; /* Move o elemento para a direita em 50% do próprio elemento */
  transform: translateX(-50%); /* Realiza o ajuste para a esquerda */
  text-align: center; /* Centraliza todo o conteúdo */
}

#about {
  margin-top: 250px;
  margin-bottom: 100px;
}

#services {
  margin-top: 250px;
}

#contact {
  padding: 80px 4%;
}

#contact h2 {
  font-size: 2em;
  color: #162938;
  text-align: center;
  font-weight: 800;
  margin-bottom: 30px;
}

.diet-system-section {
  width: 80%;
  max-width: 1200px;
}

.content-wrapper {
  display: flex;
  flex-wrap: wrap; /* Permite que os itens quebrem para a próxima linha */
  justify-content: center;
  align-items: flex-start; /* Alinha itens no topo verticalmente */
}

.left-column {
  flex: 1; /* Coluna da imagem */
  margin-right: 20px; /* Espaço entre a imagem e o texto */
  overflow: hidden;
  border-radius: 20px;
}

.right-column {
  flex: 1; /* Coluna do texto */
}

.image-wrapper {
  width: 100%;
}

.image-wrapper img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
}

.text-wrapper {
  text-align: justify;
}

.text-wrapper h1 {
  font-size: 40px; /* Tamanho menor para ajustar conforme desejado */
  color: #000;
  margin-bottom: 15px;
  position: relative;
}

.text-wrapper h1::after {
  content: '';
  position: absolute;
  width: 75%;
  height: 2px;
  display: block;
  margin-top: 8px; /* Ajuste para alinhar com o texto */
  background-color: #4caf50;
}

.text-wrapper p {
  font-size: 16px; /* Tamanho menor para ajustar conforme desejado */
  color: #555;
  line-height: 1.6;
}

.titleContact h2 {
  font-size: 2em;
  color: #162938;
  text-align: center;
}

.heading {
  width: 90%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  margin: 20px auto;
}

.heading h1 {
  font-size: 50px;
  color: #000;
  margin-bottom: 25px;
  position: relative;
}

.heading h1::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 4px;
  display: block;
  margin: 0 auto;
  background-color: #4caf50;
}

.heading p {
  font-size: 18px;
  color: #000;
  margin-bottom: 35px;
}

.container {
  width: 90%;
  margin: 0 auto;
  padding: 10px 20px;
}

.about {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.about-image {
  flex: 1;
  margin-right: 40px;
  overflow: hidden;
  border-radius: 20px;
}

.image-wrapper img {
  max-width: 100%;
  height: auto;
  display: block;
  transition: 0.5s ease;
}

.image-wrapper:hover img {
  transform: scale(1.1);
}

.about-content {
  flex: 1;
}

.about-content h2 {
  font-size: 23px;
  margin-bottom: 15px;
  color: #000;
}

.about-content p {
  font-size: 18px;
  line-height: 1.5;
  color: #000;
}

.about-content .read-more {
  display: inline-block;
  padding: 10px 20px;
  background-color: #fff;
  color: #000;
  font-size: 19px;
  text-decoration: none;
  border: 2px solid #162938;
  border-radius: 25px;
  margin-top: 15px;
  transition: 0.3s ease;
}

.about-content .read-more:hover {
  background-color: #162938;
  color: #fff;
}

.wrapperContact {
  max-width: 600px; /* Aumenta a largura conforme necessário */
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
  margin-top: 300px;
  margin-bottom: 100px;
}

.form-contact {
  display: flex;
  flex-direction: column;
  gap: 15px; /* Espaço entre os elementos */
}

.form-contact textarea {
  resize: none;
  height: 150px; /* Ajuste a altura conforme necessário */
}
</style>
