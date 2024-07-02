package database

import (
	"context"
	"log"
	"os"

	"github.com/joho/godotenv"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"go.mongodb.org/mongo-driver/mongo/readpref"
)

var MongoClient *mongo.Client

func InitMongoDB() *mongo.Client {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file:", err)
	}

	log.Println("Environment file loaded")
	mongoURI := os.Getenv("MONGO_URI")
	if mongoURI == "" {
		log.Fatal("MONGO_URI environment variable not set")
	}

	clientOptions := options.Client().ApplyURI(mongoURI)
	MongoClient, err = mongo.Connect(context.Background(), clientOptions)
	if err != nil {
		log.Fatal("MongoDB connection error:", err)
	}

	err = MongoClient.Ping(context.Background(), readpref.Primary())
	if err != nil {
		log.Fatal("MongoDB ping error:", err)
	}

	log.Println("MongoDB connected successfully")
	return MongoClient
}
