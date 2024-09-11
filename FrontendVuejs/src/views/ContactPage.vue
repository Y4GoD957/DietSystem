<template>
  <TheHeader />
  <div class="wrapperContact">
    <h2 class="display-6">Fale Conosco</h2>

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
  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheFooter from '@/components/TheFooter.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'
import TheHeader from '@/components/TheHeader.vue'
import WhatsAppPopup from '@/components/WhatsAppPopup.vue'
import ScrollToTopButton from '@/components/ScrollToTopButton.vue'

export default {
  name: 'ContactPage',
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
    }
  }
}
</script>

<style>
.wrapperContact {
  max-width: 600px; /* Aumenta a largura conforme necessário */
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
  margin-top: 10rem;
  margin-bottom: 10rem;
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
