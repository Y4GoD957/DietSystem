<template>
  <TheHeader />

  <main class="contact-container">
    <h2 class="contact-title">Fale Conosco</h2>

    <form @submit.prevent="handleContact" class="contact-form">
      <div class="contact-input-box">
        <span class="contact-icon"><ion-icon name="person"></ion-icon></span>
        <input type="text" id="name" v-model="formDataContact.name" placeholder=" " />
        <label for="name">Nome Completo</label>
      </div>

      <div class="contact-input-box">
        <span class="contact-icon"><ion-icon name="mail"></ion-icon></span>
        <input type="email" id="email" v-model="formDataContact.email" placeholder=" " />
        <label for="email">E-mail</label>
      </div>

      <div class="contact-input-box">
        <span class="contact-icon"><ion-icon name="call"></ion-icon></span>
        <input type="text" id="phone" v-model="formDataContact.phone" placeholder=" " />
        <label for="phone">Celular</label>
      </div>

      <div class="contact-textarea-box">
        <label for="message">Feedback</label>
        <textarea id="message" v-model="formDataContact.message" placeholder="Sua mensagem"></textarea>
      </div>

      <button type="submit" class="contact-btn-submit">Enviar</button>
    </form>
  </main>

  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheFooter from '@/components/layout/AppFooter.vue'
import TheHeader from '@/components/layout/AppHeader.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'

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
        return Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'Você precisa estar logado para enviar o formulário.'
        })
      }

      const loggedInUserEmail = localStorage.getItem('userEmail')

      if (this.formDataContact.email !== loggedInUserEmail) {
        return Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'O email do formulário não corresponde ao email do usuário logado.'
        })
      }

      if (!this.validateForm()) {
        return Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Por favor, preencha todos os campos corretamente.'
        })
      }

      try {
        const { email } = this.formDataContact
        const response = await axios.get(`/users/contact/${email}`)

        if (response.status === 200 && response.data) {
          Swal.fire({
            title: 'Dados já existentes',
            text: 'Você já enviou dados de contato. Deseja alterar?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sim, alterar',
            cancelButtonText: 'Cancelar'
          }).then((result) => {
            if (result.isConfirmed) this.submitContactData()
          })
        } else {
          this.submitContactData()
        }
      } catch (error) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Não foi possível verificar os dados. Tente novamente mais tarde.'
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
      return name.trim() && email.trim() && phone.trim() && message.trim()
    }
  }
}
</script>

<style scoped>
.contact-container {
  max-width: 600px;
  margin: 10rem auto;
  padding: 2rem;
  background: #fff;
  border-radius: 20px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.15);
  border: 2px solid rgba(255, 255, 255, 0.5);
}

.contact-title {
  font-size: 2rem;
  text-align: center;
  color: #4caf50;
  margin-bottom: 1.5rem;
}

.contact-form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

.contact-input-box {
  position: relative;
}

.contact-input-box input {
  width: 100%;
  padding: 12px 12px 12px 40px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: transparent;
  outline: none;
  transition: border 0.3s;
}

.contact-input-box label {
  position: absolute;
  top: 12px;
  left: 40px;
  color: #999;
  pointer-events: none;
  transition: 0.2s ease all;
}

.contact-input-box input:focus + label,
.contact-input-box input:not(:placeholder-shown) + label {
  top: -8px;
  left: 35px;
  font-size: 0.8rem;
  background-color: #fff;
  padding: 0 4px;
  color: #4caf50;
}

.contact-icon {
  position: absolute;
  top: 50%;
  left: 10px;
  transform: translateY(-50%);
  color: #4caf50;
  font-size: 1.2rem;
}

.contact-textarea-box {
  display: flex;
  flex-direction: column;
}

.contact-textarea-box label {
  margin-bottom: 0.5rem;
  color: #555;
}

.contact-textarea-box textarea {
  resize: none;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 12px;
  min-height: 120px;
  outline: none;
  transition: border-color 0.3s;
}

.contact-textarea-box textarea:focus {
  border-color: #4caf50;
}

.contact-btn-submit {
  padding: 12px;
  background-color: #162938;
  color: #fff;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  font-size: 1rem;
  cursor: pointer;
  transition: background 0.3s ease;
}

.contact-btn-submit:hover {
  background-color: #0e1f26;
}
</style>