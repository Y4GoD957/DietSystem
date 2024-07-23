<template>
    <TheHeader />
    <div class="reset-password">
      <h2>Redefinição de Senha</h2>
      <form @submit.prevent="resetPassword">
        <div class="form-group">
          <label for="newPassword">Nova Senha</label>
          <input
            type="password"
            id="newPassword"
            v-model="newPassword"
            required
          />
        </div>
        <div class="form-group">
          <label for="confirmPassword">Confirmar Nova Senha</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="confirmPassword"
            required
          />
        </div>
        <button type="submit">Alterar Senha</button>
      </form>
    </div>
  </template>
  
  <script>
  import TheHeader from '@/components/TheHeader.vue';
  import Swal from 'sweetalert2';
  import axios from '@/plugins/axios.js';
  
  export default {
    name: 'ResetPassword',
    components: {
      TheHeader
    },
    data() {
      return {
        newPassword: '',
        confirmPassword: ''
      };
    },
    methods: {
      async resetPassword() {
        if (this.newPassword !== this.confirmPassword) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'As senhas não coincidem. Por favor, tente novamente.',
          });
          return;
        }
  
        try {
          const response = await axios.post('/users/reset-password', {
            newPassword: this.newPassword
          });
  
          if (response.status === 200) {
            Swal.fire({
              icon: 'success',
              title: 'Sucesso',
              text: 'Senha alterada com sucesso.',
            });
            this.$router.push('/');
            location.reload();
          } else {
            Swal.fire({
              icon: 'error',
              title: 'Erro',
              text: response.data,
            });
          }
        } catch (error) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Ocorreu um erro ao alterar a senha. Tente novamente mais tarde.',
          });
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .reset-password {
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