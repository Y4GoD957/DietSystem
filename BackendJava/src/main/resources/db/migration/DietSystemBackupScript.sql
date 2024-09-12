use system_diet;

select * from usuarios;
select * from diets;
select * from meals;
select * from meal_items;
select * from exercise_plans;
select * from exercises;

ALTER TABLE usuarios
ADD COLUMN name VARCHAR(255) NOT NULL,
ADD COLUMN phone VARCHAR(20),
ADD COLUMN message TEXT;

ALTER TABLE usuarios
ADD COLUMN position VARCHAR(255) NOT NULL;

ALTER TABLE usuarios
MODIFY COLUMN position VARCHAR(255) NOT NULL;

ALTER TABLE usuarios
MODIFY COLUMN name VARCHAR(255) NULL,
MODIFY COLUMN phone VARCHAR(20) NULL,
MODIFY COLUMN message TEXT NULL;

ALTER TABLE usuarios MODIFY message TEXT NOT NULL DEFAULT '';

UPDATE usuarios SET message = '' WHERE message IS NULL;

UPDATE diets SET weight = '67,3' WHERE user_id = 15;

UPDATE usuarios 
SET position = 'common' 
WHERE user_id IN (15, 16, 17, 18, 18, 19, 21, 23, 24);

SET SQL_SAFE_UPDATES = 0;

SET SQL_SAFE_UPDATES = 1;

ALTER TABLE usuarios
DROP COLUMN verification_code,
DROP COLUMN verification_code_expiration;

DELETE FROM diets
WHERE diet_id = 2;

DROP TABLE IF EXISTS diets;

CREATE TABLE usuarios (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    phone VARCHAR(20),
    message VARCHAR(255),
    position VARCHAR(255) NOT NULL
);

CREATE TABLE diets (
    diet_id INT AUTO_INCREMENT PRIMARY KEY,
    height INT NOT NULL,
    weight DOUBLE NOT NULL,
    gender VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    activities INT NOT NULL,
    diet VARCHAR(255) NOT NULL,
    tmb DOUBLE,
    imc DOUBLE,
    caloric_expenditure DOUBLE,
    ideal_weight DOUBLE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuarios(user_id),
    UNIQUE(user_id)
);

CREATE TABLE meals (
    meal_id INT AUTO_INCREMENT PRIMARY KEY,
    diet_id INT NOT NULL,
    meal_name VARCHAR(255) NOT NULL,  -- Ex: 'Café da manhã', 'Almoço'
    meal_time TIME NOT NULL,  -- Hora da refeição
    total_calories DOUBLE NOT NULL,  -- Calorias totais da refeição
    total_protein DOUBLE NOT NULL,  -- Proteína total
    total_carbs DOUBLE NOT NULL,  -- Carboidratos totais
    total_fat DOUBLE NOT NULL,  -- Gorduras totais
    FOREIGN KEY (diet_id) REFERENCES diets(diet_id) ON DELETE CASCADE
);

CREATE TABLE meal_items (
    meal_item_id INT AUTO_INCREMENT PRIMARY KEY,
    meal_id INT NOT NULL,
    food_name VARCHAR(255) NOT NULL,  -- Nome do alimento, Ex: 'Arroz'
    quantity DOUBLE NOT NULL,  -- Quantidade em gramas ou porções
    calories DOUBLE NOT NULL,  -- Calorias do alimento
    protein DOUBLE NOT NULL,  -- Proteínas do alimento
    carbs DOUBLE NOT NULL,  -- Carboidratos do alimento
    fat DOUBLE NOT NULL,  -- Gorduras do alimento
    FOREIGN KEY (meal_id) REFERENCES meals(meal_id) ON DELETE CASCADE
);

CREATE TABLE exercise_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    diet_id INT NOT NULL,
    objective VARCHAR(255) NOT NULL,  -- Ex: 'Ganho de massa', 'Emagrecimento'
    duration_weeks INT NOT NULL,  -- Duração do plano em semanas
    total_calories_burned DOUBLE,  -- Total de calorias queimadas estimadas
    FOREIGN KEY (diet_id) REFERENCES diets(diet_id) ON DELETE CASCADE
);

CREATE TABLE exercises (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_id INT NOT NULL,
    exercise_name VARCHAR(255) NOT NULL,  -- Nome do exercício, Ex: 'Agachamento'
    sets INT NOT NULL,  -- Número de séries
    reps INT NOT NULL,  -- Número de repetições por série
    calories_burned_per_set DOUBLE NOT NULL,  -- Calorias queimadas por série
    difficulty VARCHAR(50),  -- Ex: 'Fácil', 'Moderado', 'Difícil'
    FOREIGN KEY (plan_id) REFERENCES exercise_plans(plan_id) ON DELETE CASCADE
);