<template>
  <div class="usrp-content">
    <form @submit.prevent="updateProfile">
      <!-- Dados do Usuário -->
      <FormSection title="Dados do Usuário">
        <InputField label="ID do Usuário:" v-model="user.user_id" readonly />
        <InputField label="Tipo do Usuário:" v-model="user.position" readonly />
        <InputField label="Usuário:" v-model="user.username" placeholder="Usuário" />
        <InputField label="E-mail:" v-model="user.email" placeholder="E-mail" type="email" />
        <InputField label="Nome Completo:" v-model="user.name" placeholder="Nome Completo" />
        <InputField label="Telefone:" v-model="user.phone" placeholder="Telefone" />
      </FormSection>

      <hr class="usrp-divider" />

      <!-- Redefinir Senha -->
      <FormSection title="Redefinir Senha">
        <InputField
          :type="passwordVisible ? 'text' : 'password'"
          label="Nova Senha:"
          v-model="newPassword"
          placeholder="********"
        />
        <InputField
          :type="passwordVisible ? 'text' : 'password'"
          label="Confirmar Nova Senha:"
          v-model="confirmPassword"
          placeholder="********"
        />
        <div class="usrp-form-group">
          <button type="button" @click="togglePasswordVisibility" class="usrp-btn-secondary">
            {{ passwordVisible ? 'Ocultar Senha' : 'Mostrar Senha' }}
          </button>
        </div>
      </FormSection>

      <hr class="usrp-divider" />

      <!-- Métricas da Dieta -->
      <FormSection title="Dados das Métricas para Dieta">
        <InputField label="ID das Métricas:" v-model="diet.diet_id" readonly />

        <!-- Dropdowns personalizados -->
        <DropdownField
          label="Atividades Semanais:"
          :options="activitiesOptions"
          v-model="diet.activities"
        />
        <InputField
          label="Idade:"
          v-model="diet.age"
          type="number"
          placeholder="Idade"
          :min="10"
          :max="110"
        />
        <DropdownField label="Objetivo:" :options="objectiveOptions" v-model="diet.diet" />
        <DropdownField label="Gênero:" :options="genderOptions" v-model="diet.gender" />
        <InputField
          label="Altura (cm):"
          v-model="diet.height"
          type="number"
          placeholder="Altura"
          :min="50"
          :max="250"
        />
        <InputField
          label="Peso (kg):"
          v-model="diet.weight"
          type="number"
          placeholder="Peso"
          :min="10"
          :max="300"
          step="0.1"
        />
      </FormSection>

      <button type="submit" class="usrp-btn-primary">Atualizar Perfil</button>
    </form>
  </div>
</template>

<script>
import { defineComponent, h } from 'vue'
import axios from '@/plugins/axios'
import Swal from 'sweetalert2'

export default defineComponent({
  name: 'UserProfile',
  components: {
    FormSection: defineComponent({
      name: 'FormSection',
      props: ['title'],
      render() {
        return h('div', { class: 'usrp-section' }, [
          h('h2', this.title),
          this.$slots.default()
        ])
      }
    }),
    InputField: defineComponent({
      name: 'InputField',
      props: {
        label: String,
        modelValue: [String, Number],
        placeholder: String,
        type: { type: String, default: 'text' },
        readonly: Boolean,
        min: [String, Number],
        max: [String, Number],
        step: [String, Number]
      },
      emits: ['update:modelValue'],
      render() {
        return h('div', { class: 'usrp-form-group' }, [
          h('label', this.label),
          h('input', {
            class: ['usrp-form-control', this.readonly ? 'usrp-readonly-input' : ''],
            type: this.type,
            placeholder: this.placeholder,
            readonly: this.readonly,
            min: this.min,
            max: this.max,
            step: this.step,
            value: this.modelValue,
            onInput: e => this.$emit('update:modelValue', e.target.value)
          })
        ])
      }
    }),
    DropdownField: defineComponent({
      name: 'DropdownField',
      props: {
        label: String,
        options: Array,
        modelValue: [String, Number]
      },
      emits: ['update:modelValue'],
      data() {
        return { visible: false }
      },
      methods: {
        selectOption(option) {
          this.$emit('update:modelValue', option)
          this.visible = false
        }
      },
      render() {
        return h('div', { class: 'usrp-form-group' }, [
          h('label', this.label),
          h('div', {
            class: 'usrp-dropdown',
            onClick: () => (this.visible = !this.visible)
          }, [
            h('span', this.modelValue || 'Selecione'),
            h('ion-icon', { name: this.visible ? 'chevron-up' : 'chevron-down' })
          ]),
          this.visible
            ? h('div', { class: 'usrp-dropdown-options' },
                this.options.map((opt, i) =>
                  h('div', {
                    key: i,
                    onClick: () => this.selectOption(opt)
                  }, opt)
                )
              )
            : null
        ])
      }
    })
  },
  data() {
    return {
      user: {
        user_id: '',
        position: '',
        username: '',
        email: '',
        name: '',
        phone: '',
        password: ''
      },
      newPassword: '',
      confirmPassword: '',
      diet: {
        diet_id: '',
        activities: '',
        age: '',
        diet: '',
        gender: '',
        height: '',
        weight: ''
      },
      passwordVisible: false,
      genderOptions: ['Masculino', 'Feminino'],
      objectiveOptions: ['Bulking (Ganho de Massa)', 'Cutting (Emagrecimento)', 'Maintaining (Manter Peso)'],
      activitiesOptions: Array.from({ length: 8 }, (_, i) => i.toString())
    }
  },
  watch: {
    'diet.weight'(newVal) {
      if (newVal) this.diet.weight = newVal.toString().replace(',', '.')
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    if (userId) {
      this.loadProfile(userId)
      this.loadDietSettings(userId)
    }
  },
  methods: {
    loadProfile(userId) {
      axios.get(`/users/profile/${userId}`)
        .then(res => {
          this.user = res.data
          if (res.data.diets?.length > 0) {
            this.diet = res.data.diets[0]
          }
        })
        .catch(err => console.error('Erro ao carregar perfil:', err))
    },
    loadDietSettings(userId) {
      axios.get(`/diet/diet-settings/${userId}`)
        .then(res => this.diet = res.data)
        .catch(err => console.error('Erro ao carregar dieta:', err))
    },
    togglePasswordVisibility() {
      this.passwordVisible = !this.passwordVisible
    },
    updateProfile() {
      if (this.newPassword && this.newPassword !== this.confirmPassword) {
        return Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'As senhas não coincidem.'
        })
      }

      const payload = {
        ...this.user,
        password: this.newPassword || this.user.password
      }

      axios.put(`/users/profile/${this.user.user_id}`, payload)
        .then(() => {
          Swal.fire({
            icon: 'success',
            title: 'Perfil Atualizado!',
            text: 'O seu perfil foi atualizado com sucesso!'
          })
        })
        .catch(err => {
          console.error('Erro ao atualizar perfil:', err)
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: 'Não foi possível atualizar o perfil.'
          })
        })
    }
  }
})
</script>

<style>
.usrp-content {
  padding: 20px;
}

.usrp-section {
  margin-bottom: 30px;
}

.usrp-form-group {
  margin-bottom: 20px;
}

.usrp-form-group label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
  color: #162938;
}

.usrp-form-control {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  background-color: #fff;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.usrp-form-control:focus {
  border-color: #86b7fe;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
  background-color: #fff;
}

.usrp-readonly-input {
  background-color: #e9ecef;
  cursor: not-allowed;
}

.usrp-divider {
  border: none;
  border-top: 2px solid #ccc;
  margin: 20px 0;
}

.usrp-btn-primary {
  width: 100%;
  padding: 15px;
  background: #162938;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  transition: background 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}

.usrp-btn-primary:hover {
  background: #0e1f26;
}

.usrp-btn-secondary {
  background-color: #fff;
  color: #162938;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px 15px;
  cursor: pointer;
  transition: background 0.3s ease;
}

.usrp-btn-secondary:hover {
  background-color: #f0f0f0;
}

.usrp-dropdown {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
  background-color: #fff;
}

.usrp-dropdown-options {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  background-color: #f9f9f9;
  margin-top: 5px;
}

.usrp-dropdown-options div {
  padding: 5px;
  cursor: pointer;
}

.usrp-dropdown-options div:hover {
  background-color: #e0e0e0;
}
</style>