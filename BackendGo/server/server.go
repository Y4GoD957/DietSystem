package server

import (
	"context"
	"log"
	"net/http"
	"os"
	"ps-backend-pedro-souza/components"
	"ps-backend-pedro-souza/database" // ajuste o caminho conforme necessário

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/mongo"
)

func StartServer() {
	// Inicialização do MongoDB
	mongoClient := database.InitMongoDB()
	defer mongoClient.Disconnect(context.Background())

	// Configuração das rotas
	coll := mongoClient.Database(os.Getenv("DB_NAME")).Collection(os.Getenv("COLLECTION_NAME"))
	r := setupRoutes(coll)

	// Configuração do servidor HTTP
	log.Println("Server listening on :4444")

	// Adicionar middleware CORS
	corsHandler := handlers.CORS(
		handlers.AllowedOrigins([]string{"http://localhost:5173"}),
		handlers.AllowedMethods([]string{"GET", "POST", "PUT", "DELETE", "OPTIONS"}),
		handlers.AllowedHeaders([]string{"Content-Type", "Authorization"}),
	)

	http.ListenAndServe(":4444", corsHandler(r))
}

func setupRoutes(coll *mongo.Collection) *mux.Router {
	r := mux.NewRouter()
	svc := &components.UserService{MongoCollection: coll}

	r.HandleFunc("/register", svc.RegisterUser).Methods("POST")
	r.HandleFunc("/login", svc.Login).Methods("POST")
	r.HandleFunc("/users/{id}", svc.GetUserByID).Methods("GET")
	r.HandleFunc("/users", svc.GetAllUser).Methods("GET")
	r.HandleFunc("/users/{id}", svc.UpdateUserByID).Methods("PUT")
	r.HandleFunc("/users/{id}", svc.DeleteUsersByID).Methods("DELETE")
	r.HandleFunc("/users", svc.DeleteAllUsers).Methods("DELETE")
	r.HandleFunc("/users/{id}/metabolism", svc.UpdateUserMetabolism).Methods("PUT")

	return r
}
