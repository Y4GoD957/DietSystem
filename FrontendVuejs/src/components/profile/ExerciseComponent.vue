<template>
  <div class="exercise-container">
    <!-- Exercícios Content -->
    <h2>Conteúdo de Exercícios</h2>
    <p>Aqui você verá informações sobre os exercícios. Esta é uma seção fictícia para testes.</p>

    <!-- Adicione mais conteúdo fictício ou elementos conforme necessário -->
    <div class="exercise-info">
      <h3>Exercício 1</h3>
      <p>Descrição do Exercício 1.</p>

      <h3>Exercício 2</h3>
      <p>Descrição do Exercício 2.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ExerciseComponent',
  props: {
    exercises: {
      type: Array,
      default: () => []
    }
  }
}
</script>

<style scoped>
.exercise-container {
  margin: 20px;
}

.exercise-info h3 {
  margin-top: 10px;
  color: #162938;
}

.exercise-info p {
  margin-bottom: 10px;
  color: #333;
}
</style>
