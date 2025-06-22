<template>
  <div id="app">
    <!-- Renderiza a rota atual -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
@import './assets/css/reset.css';
@import './assets/css/layout.css';
@import './assets/css/forms.css';
</style>
