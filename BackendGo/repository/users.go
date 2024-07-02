package repository

import (
	"context"
	"fmt"
	"ps-backend-pedro-souza/model"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
)

type UsersRepo struct {
	MongoCollection *mongo.Collection
}

func (r *UsersRepo) InsertUsers(user *model.Users) (interface{}, error) {
	result, err := r.MongoCollection.InsertOne(context.Background(), user)
	if err != nil {
		return nil, err
	}
	return result.InsertedID, nil
}

func (r *UsersRepo) FindUsersByID(userID string) (*model.Users, error) {
	var user model.Users

	err := r.MongoCollection.FindOne(context.Background(),
		bson.D{{Key: "user_id", Value: userID}}).Decode(&user)

	if err != nil {
		return nil, err
	}

	return &user, nil
}

func (r *UsersRepo) FindUserByEmail(email string) (*model.Users, error) {
	var user model.Users

	err := r.MongoCollection.FindOne(context.Background(),
		bson.D{{Key: "email", Value: email}}).Decode(&user)

	if err != nil {
		return nil, err
	}

	return &user, nil
}

func (r *UsersRepo) FindAllUsers() ([]model.Users, error) {
	results, err := r.MongoCollection.Find(context.Background(), bson.D{})

	if err != nil {
		return nil, err
	}
	var users []model.Users
	err = results.All(context.Background(), &users)
	if err != nil {
		return nil, fmt.Errorf("results decode error %s", err.Error())
	}
	return users, nil
}

func (r *UsersRepo) UpdateUserByID(userID string, updateUser *model.Users) (int64, error) {
	result, err := r.MongoCollection.UpdateOne(context.Background(),
		bson.D{{Key: "user_id", Value: userID}},
		bson.D{{Key: "$set", Value: updateUser}})

	if err != nil {
		return 0, err
	}
	return result.ModifiedCount, nil
}

func (r *UsersRepo) DeleteUsersByID(userID string) (int64, error) {
	result, err := r.MongoCollection.DeleteOne(context.Background(),
		bson.D{{Key: "user_id", Value: userID}})
	if err != nil {
		return 0, err
	}
	return result.DeletedCount, nil
}

func (r *UsersRepo) DeleteAllUsers() (int64, error) {
	result, err := r.MongoCollection.DeleteMany(context.Background(), bson.D{})
	if err != nil {
		return 0, err
	}
	return result.DeletedCount, nil
}

func (r *UsersRepo) UpdateUserMetabolism(userID string, weight float64, age int, height float64, gender string, activities int) (float64, error) {
	// Cálculo do metabolismo basal
	var metabolism float64

	if gender == "masculino" {
		metabolism = 66.5 + (13.75 * weight) + (5.003 * height * 100) - (6.755 * float64(age))
	} else {
		metabolism = 655.1 + (9.563 * weight) + (1.85 * height * 100) - (4.676 * float64(age))
	}

	metabolism *= float64(activities)

	// Ajuste para somar com a quantidade de dias multiplicada por 250
	metabolism += float64(activities) * 250

	// Atualiza os dados no banco de dados
	_, err := r.MongoCollection.UpdateOne(context.Background(),
		bson.D{{Key: "user_id", Value: userID}},
		bson.D{
			{Key: "$set", Value: bson.D{
				{Key: "weight", Value: weight},
				{Key: "age", Value: age},
				{Key: "height", Value: height},
				{Key: "gender", Value: gender},
				{Key: "activities", Value: activities},
				{Key: "metabolism", Value: metabolism},
			}},
		},
	)

	if err != nil {
		return 0, err
	}

	return metabolism, nil
}
