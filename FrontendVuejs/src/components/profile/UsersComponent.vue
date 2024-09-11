<template>
  <div class="user-container container">
    <h2 class="text-center my-4">Lista de Usuários</h2>

    <!-- Condição para mostrar a tabela ou o formulário de edição -->
    <div v-if="!editingUser">
      <table class="table table-hover">
        <thead class="table-dark">
          <tr>
            <th>ID do Usuário</th>
            <th>Tipo do Usuário</th>
            <th>Usuário</th>
            <th>Email</th>
            <th>Nome Completo</th>
            <th>Telefone</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in allUsers" :key="user.userId">
            <td>{{ user.userId }}</td>
            <td>{{ user.position }}</td>
            <td>{{ user.username }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.name }}</td>
            <td>{{ user.phone }}</td>
            <td>
              <button @click="editUser(user)" class="btn btn-primary btn-sm">
                <ion-icon name="create-outline"></ion-icon> Editar
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Formulário de edição do usuário -->
    <div v-if="editingUser">
      <h2>Editando Usuário: {{ editingUser.name }}</h2>
      <div class="mb-3">
        <label>Nome Completo</label>
        <input type="text" v-model="editingUser.name" class="form-control" />
      </div>
      <div class="mb-3">
        <label>Email</label>
        <input type="email" v-model="editingUser.email" class="form-control" />
      </div>
      <div class="mb-3">
        <label>Telefone</label>
        <input type="text" v-model="editingUser.phone" class="form-control" />
      </div>
      <div class="mb-3">
        <label>Tipo do Usuário</label>
        <input type="text" v-model="editingUser.position" class="form-control" />
      </div>

      <!-- Botões de Ação -->
      <div class="d-flex justify-content-between">
        <button @click="cancelEdit" class="btn btn-secondary">Cancelar</button>
        <button @click="saveUser" class="btn btn-success">Salvar Alterações</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/plugins/axios.js'
import Swal from 'sweetalert2'

export default {
  name: 'UsersComponent',
  data() {
    return {
      allUsers: [], // Armazena todos os usuários para a aba "Usuários"
      editingUser: null // Controla o usuário que está sendo editado
    }
  },
  created() {
    // Carrega todos os usuários ao criar o componente
    this.loadAllUsers()
  },
  methods: {
    loadAllUsers() {
      axios
        .get('/users/all')
        .then((response) => {
          this.allUsers = response.data
        })
        .catch((error) => {
          console.error('Erro ao carregar usuários:', error)
        })
    },
    editUser(user) {
      this.editingUser = { ...user } // Clona o objeto para evitar modificações diretas
    },
    cancelEdit() {
      Swal.fire({
        title: 'Cancelar edição?',
        text: 'Você perderá as alterações não salvas!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim, cancelar',
        cancelButtonText: 'Não, continuar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.editingUser = null // Cancela a edição e retorna à tabela de usuários
        }
      })
    },
    saveUser() {
      axios
        .put(`/users/${this.editingUser.userId}`, this.editingUser)
        .then(() => {
          Swal.fire('Sucesso!', 'O usuário foi editado com sucesso.', 'success')
          this.editingUser = null
          this.loadAllUsers() // Recarrega a lista de usuários
        })
        .catch((error) => {
          Swal.fire(
            'Erro!',
            'Não foi possível editar o usuário. Tente novamente mais tarde.',
            'error'
          )
          console.error('Erro ao salvar o usuário', error)
        })
    }
  }
}
</script>

<style scoped>
.user-container {
  margin: 6.25rem auto;
}

.table {
  margin-bottom: 20px;
}

.btn-edit ion-icon {
  margin-right: 5px;
}
</style>
