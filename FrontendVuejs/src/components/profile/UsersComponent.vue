<template>
  <div class="user-container">
    <h2>Lista de Usuários</h2>

    <!-- Condição para mostrar a tabela ou o formulário de edição -->
    <div v-if="!editingUser">
      <table class="table">
        <thead>
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
              <button @click="editUser(user)" class="btn btn-edit">
                <ion-icon name="create-outline"></ion-icon> <span>Editar</span>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Formulário de edição do usuário -->
    <div v-if="editingUser">
      <h2>Editando Usuário: {{ editingUser.name }}</h2>
      <div class="section">
        <div class="form-group">
          <label>Nome Completo</label>
          <input type="text" v-model="editingUser.name" class="form-control" />
        </div>
        <div class="form-group">
          <label>Email</label>
          <input type="email" v-model="editingUser.email" class="form-control" />
        </div>
        <div class="form-group">
          <label>Telefone</label>
          <input type="text" v-model="editingUser.phone" class="form-control" />
        </div>
        <div class="form-group">
          <label>Tipo do Usuário</label>
          <input type="text" v-model="editingUser.position" class="form-control" />
        </div>
      </div>

      <!-- Botões de Ação -->
      <div class="action-buttons">
        <button @click="cancelEdit" class="btn btn-cancel">Cancelar</button>
        <button @click="saveUser" class="btn btn-save">Editar Dados</button>
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
    // Funções para gerenciar usuários na aba "Usuários"
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
      // Lógica para salvar as alterações
      axios
        .put(`/users/${this.editingUser.userId}`, this.editingUser) // Corrigido para userId
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
  max-width: 800px;
  margin: 6.25rem auto;
}

.user-container h2 {
  text-align: center;
  margin-bottom: 20px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.table thead {
  background-color: #f4f4f4;
}

.table th,
.table td {
  padding: 12px;
  border: 1px solid #ddd;
  text-align: left;
  word-wrap: break-word; /* Quebra o texto em várias linhas, se necessário */
}

.table th {
  color: #333;
}

.btn-edit {
  display: flex;
  align-items: center; /* Centraliza verticalmente */
  justify-content: center; /* Centraliza horizontalmente */
  background-color: #162938; /* Cor do site */
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  white-space: nowrap; /* Evita que o texto quebre */
}

.btn-edit ion-icon {
  margin-right: 5px; /* Espaçamento entre o ícone e o texto */
}

.btn-edit:hover {
  background-color: #0e1f26;
}

.section {
  margin-bottom: 30px;
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

.form-group input[type='text'],
.form-group input[type='email'] {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.action-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.btn-cancel {
  color: #3a383f;
  background-color: #fff;
  box-shadow: inset 0 0 0 1px #bfbfc3;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.btn-cancel:hover {
  box-shadow: inset 0 0 0 1px #a9a9a9;
}

.btn-save {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 10px 20px;
  background-color: #162938; /* Cor do site */
  color: #fff;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.btn-save:hover {
  background-color: #0e1f26; /* Efeito hover */
}
</style>
