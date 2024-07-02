package model

type Users struct {
	UserID     string  `json:"user_id,omitempty" bson:"user_id"`
	Name       string  `json:"name,omitempty" bson:"name"`
	Email      string  `json:"email,omitempty" bson:"email"`
	Password   string  `json:"password,omitempty" bson:"password"`
	Weight     float64 `json:"weight,omitempty" bson:"weight"`
	Age        int     `json:"age,omitempty" bson:"age"`
	Height     float64 `json:"height,omitempty" bson:"height"`
	Gender     string  `json:"gender,omitempty" bson:"gender"`
	Activities int     `json:"activities,omitempty" bson:"activities"`
	Metabolism float64 `json:"metabolism,omitempty" bson:"metabolism"`
}
