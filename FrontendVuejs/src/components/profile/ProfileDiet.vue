<template>
  <div class="diet-container">
    <!-- Dieta Content -->
    <h2>Conteúdo da Dieta</h2>

    <!-- TMB (Taxa de Metabolismo Basal) -->
    <div class="form-group">
      <label for="tmb">Taxa de Metabolismo Basal (TMB):</label>
      <input type="text" id="tmb" v-model="diet.tmb" readonly class="form-control readonly" />
    </div>

    <!-- Gasto Calórico -->
    <div class="form-group">
      <label for="caloric-expenditure">Gasto Calórico:</label>
      <input
        type="text"
        id="caloric_expenditure"
        v-model="diet.caloric_expenditure"
        readonly
        class="form-control readonly"
      />
    </div>

    <!-- IMC (Índice de Massa Corporal) -->
    <div class="form-group">
      <label for="imc">Índice de Massa Corporal (IMC):</label>
      <input type="text" id="imc" v-model="diet.imc" readonly class="form-control readonly" />
    </div>

    <!-- Peso Ideal -->
    <div class="form-group">
      <label for="ideal-weight">Peso Ideal:</label>
      <input
        type="text"
        id="ideal_weight"
        v-model="diet.ideal_weight"
        readonly
        class="form-control readonly"
      />
    </div>
  </div>
</template>

<script>
import axios from '@/plugins/axios.js'

export default {
  name: 'DietComponent',
  data() {
    return {
      diet: {
        tmb: '',
        imc: '',
        caloric_expenditure: '',
        ideal_weight: ''
      }
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) {
      this.loadDietSettings(userId)
    } else {
      console.error('ID do usuário não encontrado no localStorage')
    }
  },
  methods: {
    loadDietSettings(userId) {
      axios
        .get(`/diet/diet-settings/${userId}`)
        .then((response) => {
          this.diet = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar a dieta:', error)
        })
    }
  }
}
</script>

<style scoped>
.diet-container {
  margin: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
  color: #162938;
}

.form-control {
  border-radius: 4px;
  border: 1px solid #ccc;
  padding: 8px;
  width: 100%;
}

.readonly {
  background-color: #e9ecef;
  cursor: not-allowed;
}
</style>
