<template>
  <TheHeader />

  <div class="wrapper-diet">
    <form @submit.prevent="handleDiet">
      <label for="altura">Altura (cm):</label>
      <input
        type="number"
        id="altura"
        v-model="formDataDiet.height"
        min="50"
        max="250"
        required
      />

      <fieldset>
        <legend>Sexo:</legend>
        <div class="radio-group center">
          <label>
            <input type="radio" name="sexo" value="Masculino" v-model="formDataDiet.gender" />
            Masculino
          </label>
          <label>
            <input type="radio" name="sexo" value="Feminino" v-model="formDataDiet.gender" />
            Feminino
          </label>
        </div>
      </fieldset>

      <label for="idade">Idade:</label>
      <input
        type="number"
        id="idade"
        v-model="formDataDiet.age"
        min="10"
        max="110"
        required
      />

      <label for="peso">Peso (kg):</label>
      <input
        type="number"
        id="peso"
        v-model="formDataDiet.weight"
        step="0.1"
        min="10"
        max="300"
        required
      />

      <fieldset>
        <legend>Atividades por semana:</legend>
        <div class="radio-grid">
          <label v-for="n in 8" :key="n">
            <input
              type="radio"
              :value="n - 1"
              name="atividades"
              v-model="formDataDiet.activities"
            />
            {{ n - 1 }}
          </label>
        </div>
      </fieldset>

      <fieldset>
        <legend>Objetivo:</legend>
        <div class="column-radio">
          <label>
            <input
              type="radio"
              name="dieta"
              value="Bulking (Ganho de Massa)"
              v-model="formDataDiet.diet"
            />
            Bulking (Ganho de Massa)
          </label>
          <label>
            <input
              type="radio"
              name="dieta"
              value="Cutting (Emagrecimento)"
              v-model="formDataDiet.diet"
            />
            Cutting (Emagrecimento)
          </label>
          <label>
            <input
              type="radio"
              name="dieta"
              value="Maintaining (Manter Peso)"
              v-model="formDataDiet.diet"
            />
            Maintaining (Manter Peso)
          </label>
        </div>
      </fieldset>

      <button type="submit" class="submit-btn">Gerar Dieta</button>
    </form>
  </div>

  <WhatsAppPopup />
  <ScrollToTopButton />
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/layout/AppHeader.vue'
import TheFooter from '@/components/layout/AppFooter.vue'
import WhatsAppPopup from '@/components/interactions/WhatsAppFloatingButton.vue'
import ScrollToTopButton from '@/components/layout/ScrollToTopButton.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'

export default {
  name: 'DietSelection',
  components: {
    TheHeader,
    TheFooter,
    WhatsAppPopup,
    ScrollToTopButton
  },
  data() {
    return {
      formDataDiet: {
        height: '',
        gender: '',
        age: '',
        weight: '',
        activities: '',
        diet: '',
        user_id: ''
      }
    }
  },
  watch: {
    'formDataDiet.weight'(newValue) {
      if (newValue) {
        this.formDataDiet.weight = newValue.toString().replace(',', '.')
      }
    }
  },
  methods: {
    validateForm() {
      const { height, gender, age, weight, activities, diet } = this.formDataDiet
      return height && gender && age && weight && activities && diet
    },
    handleHttpError(status) {
      const messages = {
        400: 'Requisição inválida.',
        401: 'Não autorizado.',
        403: 'Acesso proibido.',
        404: 'Recurso não encontrado.',
        500: 'Erro no servidor.'
      }
      Swal.fire({
        icon: 'error',
        title: 'Erro',
        text: messages[status] || 'Erro inesperado. Tente novamente.'
      })
    },
    handleDiet() {
      if (!this.validateForm()) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Preencha todos os campos corretamente.'
        })
        return
      }

      const token = localStorage.getItem('token')
      const userId = localStorage.getItem('userId')

      if (!token || !userId) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Faça login novamente.'
        })
        return
      }

      this.formDataDiet.user_id = userId

      axios
        .post('/diet/save-diet', this.formDataDiet, {
          headers: { Authorization: `Bearer ${token}` }
        })
        .then((response) => {
          if ([200, 201].includes(response.status)) {
            Swal.fire({
              icon: 'success',
              title: 'Perfeito!',
              text: 'Dados enviados com sucesso!'
            })
            this.$router.push('/Services')
          } else {
            Swal.fire({
              icon: 'error',
              title: 'Erro',
              text: 'Erro ao enviar os dados.'
            })
          }
        })
        .catch((err) => this.handleHttpError(err.response?.status))
    }
  }
}
</script>

<style scoped>
.wrapper-diet {
  display: flex;
  justify-content: center;
  padding: 6rem 1rem;
}

form {
  background: #fff;
  padding: 2rem;
  border-radius: 16px;
  max-width: 600px;
  width: 100%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.08);
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #162938;
}

input[type='number'],
input[type='text'] {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 6px;
  border: 1px solid #ccc;
  box-sizing: border-box;
  transition: border-color 0.2s, box-shadow 0.2s;
}

input[type='number']:focus,
input[type='text']:focus {
  border-color: #86b7fe;
  outline: 0;
  box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
}

fieldset {
  border: none;
  margin-bottom: 20px;
}

legend {
  font-weight: bold;
  color: #162938;
  margin-bottom: 8px;
}

.radio-group,
.column-radio {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
}

.center {
  justify-content: center;
}

.column-radio {
  flex-direction: column;
}

.radio-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px;
  justify-items: center;
  margin-top: 10px;
}

.submit-btn {
  width: 100%;
  padding: 14px;
  font-size: 16px;
  font-weight: bold;
  background-color: #162938;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s;
}

.submit-btn:hover {
  background-color: #0e1f26;
}
</style>
