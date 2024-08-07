<template>
  <TheHeader />
    <div class="containerContact">
      <form @submit.prevent="handleContact">
        <h1>Página de Contato</h1>
        <input type="text" id="Name" placeholder="Nome Completo:" required autocomplete="off" v-model="formDataContact.name"/>
        <input type="email" id="email" placeholder="Email:" required autocomplete="off" v-model="formDataContact.email"/>
        <input type="text" id="phone" placeholder="Celular:" required autocomplete="off" v-model="formDataContact.phone"/>
        <h4>Digite sua mensagem aqui...</h4>
        <textarea required autocomplete="off" v-model="formDataContact.message"></textarea>
        <input type="submit" value="Enviar" id="button" />
      </form>
    </div>
  <TheFooter />
</template>

<script>
import TheFooter from '@/components/TheFooter.vue';
import Swal from 'sweetalert2';
import axios from '@/plugins/axios.js';
import TheHeader from '@/components/TheHeader.vue';

export default {
  name: 'ContactPage',
  components: { 
    TheHeader,
    TheFooter
   },
   data() {
    return {
      formDataContact: {
        name: '',
        email: '',
        phone: '',
        message: ''
      }
    };
  },
  methods: {
    async handleContact() {
      if (!this.isUserLoggedIn()) {
        Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'Você precisa estar logado para enviar o formulário.',
        });
        return;
      }

      const loggedInUserEmail = localStorage.getItem('userEmail');

      if (this.formDataContact.email !== loggedInUserEmail) {
        Swal.fire({
          icon: 'warning',
          title: 'Atenção',
          text: 'O email do formulário não corresponde ao email do usuário logado.',
        });
        return;
      }

      if (this.validateForm()) {
        try {
          const response = await axios.get(`/users/contact/${this.formDataContact.email}`);

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
                this.submitContactData();
              }
            });
          } else {
            this.submitContactData();
          }
        } catch (error) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível verificar os dados existentes. Tente novamente mais tarde.',
          });
        }
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Por favor, preencha todos os campos corretamente.',
        });
      }
    },

    async submitContactData() {
      try {
        const token = localStorage.getItem('token');

        const response = await axios.post('/users/contact', this.formDataContact, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });

        if (response.status === 200) {
          localStorage.setItem('userEmail', response.data.email);
          Swal.fire({
            icon: 'success',
            title: 'Sucesso',
            text: 'Dados enviados com sucesso!',
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível enviar os dados. Tente novamente mais tarde.',
          });
        }
      } catch (error) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Ocorreu um erro ao enviar os dados. Tente novamente mais tarde.',
        });
      }
    },

    isUserLoggedIn() {
      return !!localStorage.getItem('token');
    },

    validateForm() {
      const { name, email, phone, message } = this.formDataContact;
      return name && email && phone && message !== '';
    }
  }
}
</script>

<style>
.containerContact {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.containerContact form {
  width: 670px;
  display: flex;
  flex-direction: column; /* Coloca os elementos em colunas */
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  background: rgba(255, 255, 255, 0.8); /* Fundo branco com opacidade */
  backdrop-filter: blur(20px); /* Blur mais perceptível */
  padding: 20px;
  border: 2px solid rgba(255, 255, 255, 0.5); /* Borda com opacidade */
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1); /* Sombra */
  box-sizing: border-box;
}

.containerContact form h1 {
  color: #162938; /* Título escuro */
  font-weight: 700;
  margin-bottom: 20px;
  text-align: center;
}

.containerContact form input,
.containerContact form textarea {
  width: 100%; /* Ajusta a largura para 100% do contêiner */
  height: 50px;
  padding: 10px;
  margin-bottom: 20px;
  outline: none;
  border: none;
  font-size: 20px;
  background: none;
  border-bottom: 2px solid #162938; /* Linha inferior escura */
  color: #162938; /* Texto escuro */
}

.containerContact form input::placeholder,
.containerContact form textarea::placeholder {
  color: #162938; /* Placeholder escuro */
}

.containerContact form textarea {
  min-height: 100px; /* Altura mínima do textarea */
  max-height: 100px;
  resize: none; /* Remove o redimensionamento do textarea */
}

.containerContact form #button {
  border: none;
  background: #162938; /* Botão escuro */
  border-radius: 5px;
  margin-top: 20px;
  font-weight: 600;
  font-size: 24px;
  color: #fff; /* Texto do botão branco */
  width: 200px;
  height: 60px;
  padding: 0;
  margin-bottom: 30px;
  transition: 0.3s;
}

.containerContact form #button:hover {
  background: #0f1d27; /* Botão com uma tonalidade mais escura ao passar o mouse */
  color: #fff; /* Texto do botão continua branco */
  transition: 0.3s ease-in-out;
}
</style>