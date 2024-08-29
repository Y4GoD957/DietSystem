<template>
  <TheHeader />

  <div class="wrapperDiet">
    <form @submit.prevent="handleDiet">
      <label for="altura">Altura (m):</label>
      <input
        type="number"
        id="altura"
        name="altura"
        v-model="formDataDiet.height"
        step="0.01"
        min="0"
        max="2.50"
      />

      <fieldset>
        <legend>Sexo:</legend>
        <div class="label-centered">
          <label
            ><input type="radio" name="sexo" v-model="formDataDiet.gender" value="Masculino" />
            Masculino</label
          >
          <label
            ><input type="radio" name="sexo" v-model="formDataDiet.gender" value="Feminino" />
            Feminino</label
          >
        </div>
      </fieldset>

      <label for="idade">Idade:</label>
      <input
        type="number"
        id="idade"
        name="idade"
        v-model="formDataDiet.age"
        step="1"
        min="0"
        max="110"
      />

      <fieldset>
        <legend>Número de atividades por semana:</legend>
        <div class="inline">
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="0" />
            0</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="1" />
            1</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="2" />
            2</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="3" />
            3</label
          >
        </div>
        <div class="inline">
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="4" />
            4</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="5" />
            5</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="6" />
            6</label
          >
          <label
            ><input type="radio" name="atividades" v-model="formDataDiet.activities" value="7" />
            7</label
          >
        </div>
      </fieldset>

      <fieldset>
        <legend>Objetivo:</legend>
        <div class="column">
          <label
            ><input
              type="radio"
              name="dieta"
              v-model="formDataDiet.diet"
              value="Bulking (Ganho de Massa)"
            />
            Bulking (Ganho de Massa)</label
          >
          <label
            ><input
              type="radio"
              name="dieta"
              v-model="formDataDiet.diet"
              value="Cutting (Emagrecimento)"
            />
            Cutting (Emagrecimento)</label
          >
          <label
            ><input
              type="radio"
              name="dieta"
              v-model="formDataDiet.diet"
              value="Maintaining (Manter Peso)"
            />
            Maintaining (Manter Peso)</label
          >
        </div>
      </fieldset>

      <input type="submit" value="Gerar Dieta" />
    </form>
  </div>
  <TheFooter />
</template>

<script>
import TheHeader from '@/components/TheHeader.vue'
import Swal from 'sweetalert2'
import axios from '@/plugins/axios.js'
import TheFooter from '@/components/TheFooter.vue'

export default {
  name: 'DietSelection',
  components: {
    TheHeader,
    TheFooter
  },
  data() {
    return {
      formDataDiet: {
        height: '',
        gender: '',
        age: '',
        activities: '',
        diet: '',
        user_id: ''
      }
    }
  },
  methods: {
    handleDiet() {
      if (this.validateForm()) {
        const token = localStorage.getItem('token')
        const userId = localStorage.getItem('userId')

        if (!userId) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível obter o ID do usuário. Faça o login novamente.'
          })
          return
        }

        if (!token) {
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Não foi possível obter o token de autenticação. Faça o login novamente.'
          })
          return
        }

        // Adiciona o user_id ao formDataDiet
        this.formDataDiet.user_id = userId

        // Envia a solicitação para salvar a dieta
        axios
          .post('/diet/save-diet', this.formDataDiet, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            if (response.status === 200 || response.status === 201) {
              Swal.fire({
                icon: 'success',
                title: 'Perfeito!',
                text: 'Dados enviados com sucesso!'
              })
              this.$router.push('/diet/calculator')
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Erro',
                text: 'Não foi possível enviar os dados. Tente novamente mais tarde.'
              })
            }
          })
          .catch((error) => {
            if (error.response) {
              this.handleHttpError(error.response.status)
            } else {
              console.error('Erro ao enviar dados da dieta:', error)
              Swal.fire({
                icon: 'error',
                title: 'Erro',
                text: 'Ocorreu um erro ao enviar os dados da dieta. Tente novamente mais tarde.'
              })
            }
          })
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Por favor, preencha todos os campos corretamente.'
        })
      }
    },
    validateForm() {
      const { height, gender, age, activities, diet } = this.formDataDiet
      return height && gender && age && activities !== '' && diet !== ''
    },
    handleHttpError(status) {
      switch (status) {
        case 400:
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Requisição inválida. Por favor, verifique os dados enviados.'
          })
          break
        case 401:
          Swal.fire({
            icon: 'error',
            title: 'Não autorizado',
            text: 'Você não tem permissão para realizar esta ação.'
          })
          break
        case 403:
          Swal.fire({
            icon: 'error',
            title: 'Proibido',
            text: 'Acesso proibido. Verifique suas credenciais.'
          })
          break
        case 404:
          Swal.fire({
            icon: 'error',
            title: 'Não encontrado',
            text: 'Recurso não encontrado. Verifique a URL.'
          })
          break
        case 500:
          Swal.fire({
            icon: 'error',
            title: 'Erro no servidor',
            text: 'Ocorreu um erro no servidor. Tente novamente mais tarde.'
          })
          break
        default:
          Swal.fire({
            icon: 'error',
            title: 'Erro',
            text: 'Ocorreu um erro inesperado. Tente novamente mais tarde.'
          })
      }
    }
  }
}
</script>

<style>
.wrapperDiet {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 6.25rem;
  margin-bottom: 6.25rem;
}

.wrapperDiet form {
  background: #fff;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
  padding: 20px;
  max-width: 600px;
  width: 100%;
  box-sizing: border-box;
}

.wrapperDiet form label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
  color: #162938;
}

.wrapperDiet form input[type='number'] {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.wrapperDiet fieldset {
  border: none;
  margin-bottom: 20px;
}

.wrapperDiet legend {
  font-weight: bold;
  color: #162938;
}

.label-centered {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 10px;
}

.label-centered label {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #162938;
}

.inline {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 10px;
}

.inline label {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #162938;
}

.column {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 10px;
  margin-top: 10px;
}

.column label {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #162938;
}

input[type='submit'] {
  width: 100%;
  padding: 15px;
  background: #162938;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  transition: background 0.3s ease;
}

input[type='submit']:hover {
  background: #0e1f26;
}

@media (min-width: 768px) {
  .inline {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 10px;
  }
}
</style>
