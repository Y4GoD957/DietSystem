<template>
  <TheHeader />
  <div class="password-recovery">
    <h2>Recuperação de Senha</h2>
    <form @submit.prevent="handlePasswordRecovery">
      <div class="form-group">
        <label for="email">E-mail</label>
        <input
          type="email"
          id="email"
          v-model="email"
          required
          autocomplete="off"
        />
      </div>
      <div class="form-group">
        <label for="confirmEmail">Confirmar E-mail</label>
        <input
          type="email"
          id="confirmEmail"
          v-model="confirmEmail"
          required
          autocomplete="off"
        />
      </div>
      <button type="submit">Enviar</button>
    </form>
  </div>
</template>

<script>
import TheHeader from '@/components/TheHeader.vue';
import Swal from 'sweetalert2';
import axios from '@/plugins/axios.js';

export default {
  name: 'PasswordRecovery',
  components: {
    TheHeader
  },
  data() {
    return {
      email: '',
      confirmEmail: ''
    };
  },
  methods: {
    async handlePasswordRecovery() {
      if (this.email !== this.confirmEmail) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Os e-mails não coincidem. Por favor, verifique e tente novamente.',
        });
        return;
      }

      try {
        const response = await axios.post('/users/recover-password', {
          email: this.email
        });

        if (response.status === 200) {
          Swal.fire({
            icon: 'success',
            title: 'Sucesso',
            text: 'Um e-mail de recuperação de senha foi enviado.',
          });
        this.$router.push('/verify-code');
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível enviar o e-mail de recuperação. Tente novamente mais tarde.',
          });
        }
      } catch (error) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Ocorreu um erro ao enviar o e-mail de recuperação. Tente novamente mais tarde.',
        });
      }
    }
  }
};
</script>

<style scoped>
.password-recovery {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
