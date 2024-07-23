<template>
    <TheHeader />
    <div class="verify-code">
      <h2>Verificação de Código</h2>
      <form @submit.prevent="verifyCode">
        <div class="form-group">
          <label for="code">Código de Verificação</label>
          <input
            type="text"
            id="code"
            v-model="code"
            required
          />
        </div>
        <button type="submit">Verificar Código</button>
      </form>
    </div>
  </template>
  
  <script>
  import TheHeader from '@/components/TheHeader.vue';
  import Swal from 'sweetalert2';
  import axios from '@/plugins/axios.js';
  
  export default {
    name: 'VerifyCode',
    components: {
      TheHeader
    },
    data() {
      return {
        code: ''
      };
    },
    methods: {
      async verifyCode() {
        try {
          const response = await axios.post('/users/verify-code', {
            code: this.code
          });
  
          if (response.status === 200) {
            Swal.fire({
              icon: 'success',
              title: 'Sucesso',
              text: 'Código verificado com sucesso. Redirecionando para alterar a senha...',
            });
            this.$router.push('/reset-password');
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
            text: 'Ocorreu um erro ao verificar o código. Tente novamente mais tarde.',
          });
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .verify-code {
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