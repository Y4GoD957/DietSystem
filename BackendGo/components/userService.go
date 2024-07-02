package components

import (
	"encoding/json"
	"log"
	"net/http"
	"ps-backend-pedro-souza/model"
	"ps-backend-pedro-souza/repository"

	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/mongo"
)

type UserService struct {
	MongoCollection *mongo.Collection
	JwtSecret       []byte // Chave secreta para assinar o JWT
}

type Response struct {
	Data    interface{} `json:"data,omitempty"`
	Error   string      `json:"error,omitempty"`
	Success bool        `json:"success,omitempty"`
}

// CADASTRO DE USUÁRIO

func (svc *UserService) RegisterUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	var newUser model.Users
	err := json.NewDecoder(r.Body).Decode(&newUser)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		res.Error = err.Error()
		return
	}

	// Validação dos dados do usuário (opcional)

	// Exemplo de inserção no banco de dados
	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}
	insertID, err := repo.InsertUsers(&newUser)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		res.Error = err.Error()
		return
	}

	res.Data = map[string]interface{}{
		"message": "Usuário registrado com sucesso",
		"userID":  insertID, // ou newUser.ID, dependendo da estrutura
	}
	res.Success = true
	w.WriteHeader(http.StatusOK)
}

// FUNÇÃO PARA LOGIN E GERAÇÃO DO TOKEN JWT

func (svc UserService) Login(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	var loginData struct {
		Email    string `json:"email"`
		Password string `json:"password"`
	}
	if err := json.NewDecoder(r.Body).Decode(&loginData); err != nil {
		w.WriteHeader(http.StatusBadRequest)
		res.Error = err.Error()
		return
	}

	if loginData.Email != "admin" || loginData.Password != "password" {
		w.WriteHeader(http.StatusUnauthorized)
		res.Error = "credenciais inválidas"
		return
	}

	tokenString, err := svc.generateJWT(loginData.Email)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		res.Error = "erro ao gerar token JWT"
		return
	}

	res.Data = map[string]interface{}{
		"message": "login realizado com sucesso",
		"token":   tokenString,
	}
	w.WriteHeader(http.StatusOK)
}

// INSERÇÃO DE DADOS DO USUÁRIO APÓS O LOGIN

func (svc *UserService) RegisterUsers(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	var newUser model.Users
	err := json.NewDecoder(r.Body).Decode(&newUser)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		res.Error = err.Error()
		return
	}

	// Validação dos dados do usuário (opcional)

	// Exemplo de inserção no banco de dados
	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}
	insertID, err := repo.InsertUsers(&newUser)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		res.Error = err.Error()
		return
	}

	res.Data = map[string]interface{}{
		"message": "Usuário registrado com sucesso",
		"userID":  insertID, // ou newUser.ID, dependendo da estrutura
	}
	res.Success = true
	w.WriteHeader(http.StatusOK)
}

// PESQUISAR O USUÁRIO PELO ID CORRESPONDENTE

func (svc *UserService) GetUserByID(w http.ResponseWriter, r *http.Request) {
	w.Header().Add("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	userID := mux.Vars(r)["id"]
	log.Println("user id", userID)

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}

	user, err := repo.FindUsersByID(userID)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		log.Println("error: ", err)
		res.Error = err.Error()
		return
	}
	res.Data = user
	res.Success = true // Indicate success
	w.WriteHeader(http.StatusOK)
}

// PESQUISA TODOS OS USUÁRIOS NO BANCO DE DADOS

func (svc *UserService) GetAllUser(w http.ResponseWriter, r *http.Request) {
	w.Header().Add("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}

	users, err := repo.FindAllUsers()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		log.Println("error: ", err)
		res.Error = err.Error()
		return
	}
	res.Data = users
	w.WriteHeader(http.StatusOK)
}

// ATUALIZA O USUÁRIO PELO ID CORRESPONDENTE

func (svc *UserService) UpdateUserByID(w http.ResponseWriter, r *http.Request) {
	w.Header().Add("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	userID := mux.Vars(r)["id"]
	log.Println("user id", userID)

	if userID == "" {
		w.WriteHeader(http.StatusBadRequest)
		log.Println("invalid user id")
		res.Error = "invalid user id"
		return
	}

	var user model.Users
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		log.Println("invalid body", err)
		res.Error = err.Error()
		return
	}
	user.UserID = userID

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}
	count, err := repo.UpdateUserByID(userID, &user)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		log.Println("error: ", err)
		res.Error = err.Error()
		return
	}

	res.Data = count
	res.Success = true // Indicate success
	w.WriteHeader(http.StatusOK)
}

// DELETA UM USUÁRIO PELO ID CORRESPONDENTE

func (svc *UserService) DeleteUsersByID(w http.ResponseWriter, r *http.Request) {
	w.Header().Add("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	userID := mux.Vars(r)["id"]
	log.Println("user id", userID)

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}

	count, err := repo.DeleteUsersByID(userID)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		log.Println("error: ", err)
		res.Error = err.Error()
		return
	}
	res.Data = count
	res.Success = true // Indicate success
	w.WriteHeader(http.StatusOK)
}

// DELETA TODOS OS USUÁRIOS DO BANCO DE DADOS

func (svc *UserService) DeleteAllUsers(w http.ResponseWriter, r *http.Request) {
	w.Header().Add("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}

	count, err := repo.DeleteAllUsers()
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		log.Println("error: ", err)
		res.Error = err.Error()
		return
	}
	res.Data = count
	res.Success = true // Indicate success
	w.WriteHeader(http.StatusOK)
}

// ATUALIZA OS DADOS

func (svc *UserService) UpdateUserMetabolism(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	res := &Response{}
	defer json.NewEncoder(w).Encode(res)

	userID := mux.Vars(r)["id"]

	var data struct {
		Weight     float64 `json:"weight"`
		Age        int     `json:"age"`
		Height     float64 `json:"height"`
		Gender     string  `json:"gender"`
		Activities int     `json:"activities"`
	}
	if err := json.NewDecoder(r.Body).Decode(&data); err != nil {
		w.WriteHeader(http.StatusBadRequest)
		res.Error = err.Error()
		return
	}

	repo := repository.UsersRepo{MongoCollection: svc.MongoCollection}
	metabolism, err := repo.UpdateUserMetabolism(userID, data.Weight, data.Age, data.Height, data.Gender, data.Activities)
	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)
		res.Error = err.Error()
		return
	}

	res.Data = map[string]interface{}{
		"message":    "Metabolismo basal atualizado com sucesso",
		"metabolism": metabolism,
	}
	res.Success = true // Indicate success
	w.WriteHeader(http.StatusOK)
}
