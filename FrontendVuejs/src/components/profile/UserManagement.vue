<template>
  <div class="user-container">
    <div class="page-container">
      <div class="users-wrapper" v-if="!editingUser">
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
            <tr v-for="user in paginatedUsers" :key="user.userId">
              <td>{{ user.userId }}</td>
              <td>{{ user.position }}</td>
              <td>{{ user.username }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.phone }}</td>
              <td>
                <button @click="editUser(user)" class="btn btn-secondary btn-sm">
                  <ion-icon name="create-outline"></ion-icon> Editar
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="pagination-controls">
          <button type="button" class="btn btn-sm" @click="prevPage" :disabled="currentPage === 1">
            Anterior
          </button>
          <span>Página {{ currentPage }} de {{ totalPages }}</span>
          <button
            type="button"
            class="btn btn-sm"
            @click="nextPage"
            :disabled="currentPage === totalPages"
          >
            Próxima
          </button>
        </div>
      </div>
    </div>

    <div class="edit-content" v-if="editingUser">
      <h2>Editando Usuário: {{ editingUser.name }}</h2>

      <div class="mb-3">
        <label>Nome Completo</label>
        <input type="text" v-model="editingUser.name" class="form-control" />
      </div>

      <div class="mb-3">
        <label>Nome de Usuário</label>
        <input type="text" v-model="editingUser.username" class="form-control" />
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
        <div class="dropdown" @click="toggleUserTypeVisibility">
          <span>{{ editingUser.position || 'Selecione' }}</span>
          <ion-icon :name="userTypeVisible ? 'chevron-up' : 'chevron-down'"></ion-icon>
        </div>
        <div v-if="userTypeVisible" class="dropdown-options">
          <div v-for="type in userTypes" :key="type" @click.stop="setUserType(type)">
            {{ type }}
          </div>
        </div>
      </div>

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
      allUsers: [],
      editingUser: null,
      currentPage: 1,
      usersPerPage: 10,
      userTypeVisible: false,
      userTypes: ['admin', 'common', 'nutritionist']
    }
  },
  computed: {
    paginatedUsers() {
      const start = (this.currentPage - 1) * this.usersPerPage
      return this.allUsers.slice(start, start + this.usersPerPage)
    },
    totalPages() {
      return Math.ceil(this.allUsers.length / this.usersPerPage)
    }
  },
  created() {
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
      this.editingUser = JSON.parse(JSON.stringify(user))
    },
    cancelEdit() {
      Swal.fire({
        title: 'Cancelar edição?',
        text: 'Você perderá as alterações não salvas!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim, cancelar',
        cancelButtonText: 'Não, continuar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.editingUser = null
        }
      })
    },
    saveUser() {
      axios
        .put(`/users/${this.editingUser.userId}`, this.editingUser)
        .then(() => {
          Swal.fire('Sucesso!', 'O usuário foi editado com sucesso.', 'success')
          this.editingUser = null
          this.loadAllUsers()
        })
        .catch((error) => {
          console.error('Erro ao salvar o usuário:', error)
          Swal.fire('Erro!', 'Não foi possível editar o usuário.', 'error')
        })
    },
    prevPage() {
      if (this.currentPage > 1) this.currentPage--
    },
    nextPage() {
      if (this.currentPage < this.totalPages) this.currentPage++
    },
    toggleUserTypeVisibility() {
      this.userTypeVisible = !this.userTypeVisible
    },
    setUserType(type) {
      this.editingUser.position = type
      this.userTypeVisible = false
    }
  }
}
</script>

<style scoped>
/* Mantido 100% seu estilo original para visual intacto */

.page-container {
  display: flex;
  justify-content: center;
}

.users-wrapper {
  width: 75rem;
  padding: 1.5rem;
  border: solid #ccc;
  border-width: 1px;
  border-radius: 1rem;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
  overflow: hidden;
}

.pagination-controls {
  margin-top: 1rem;
  text-align: center;
}

.pagination-controls button {
  margin: 0 0.5rem;
  background-color: #162938;
  color: #fff;
}

.btn-edit ion-icon {
  margin-right: 5px;
}

thead th {
  background-color: #162938;
  color: #fff;
}

thead th:first-child {
  border-top-left-radius: 0.7rem;
}

thead th:last-child {
  border-top-right-radius: 0.7rem;
}

.edit-content {
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 1rem;
  background-color: #fff;
  margin-top: -1px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* Dropdown */
.dropdown {
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

.dropdown-options {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  background-color: #f9f9f9;
  margin-top: 5px;
}

.dropdown-options div {
  padding: 5px;
  cursor: pointer;
}

.dropdown-options div:hover {
  background-color: #e0e0e0;
}
</style>
