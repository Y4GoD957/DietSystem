// File: main.go
package main

import (
	"ps-backend-pedro-souza/database" // ajuste o caminho conforme necessário
	"ps-backend-pedro-souza/server"   // ajuste o caminho conforme necessário
)

func main() {
	database.InitMongoDB()
	server.StartServer()
}
