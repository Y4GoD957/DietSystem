<template>
    <div>
      <form @submit.prevent="updateProfile">
        <!-- User Settings -->
        <div class="section">
          <h2>Dados do Usuário</h2>
          <div class="form-group">
            <label for="user_id">ID do Usuário:</label>
            <input type="text" id="user_id" v-model="user.user_id" readonly class="form-control readonly" />
          </div>
  
          <div class="form-group">
            <label for="position">Tipo do Usuário:</label>
            <input type="text" id="position" v-model="user.position" readonly class="form-control readonly" />
          </div>
  
          <!-- Restante dos campos do perfil -->
        </div>
  
        <button type="submit" class="btn btn-primary">Atualizar Perfil</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    props: ['user'],
    methods: {
      updateProfile() {
        // Lógica de atualização do perfil
      }
    }
  }
  </script>
  
  <style scoped>
  /* Adicione os estilos específicos aqui */
  </style>
  