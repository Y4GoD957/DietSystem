<template>
  <div id="app">
    <!-- Componente principal que renderiza a rota ativa -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss">
/* Estilos globais base do projeto */
@import './assets/css/reset.css';
@import './assets/css/layout.css';
@import './assets/css/forms.css';
</style>
