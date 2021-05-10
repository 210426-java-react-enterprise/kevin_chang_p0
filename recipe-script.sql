show search_path;
set search_path to recipeapi;
drop table users cascade;
drop table ingredients cascade;
drop table recipes cascade;
drop table user_favorite_recipes cascade;

CREATE TABLE users (
	user_id serial NOT null constraint pk_user primary key,
	first_name varchar(25) NOT NULL,
	last_name varchar(25) NOT NULL,
	"password" varchar(255) NOT NULL,
	email varchar(255) unique NOT null,
	username varchar(20) unique NOT null
);

CREATE TABLE recipes (
	recipe_id serial NOT NULL,
	recipe_name varchar(255) NOT NULL,
	recipe_url varchar(255) NOT NULL,
	
	CONSTRAINT pk_recipes PRIMARY KEY (recipe_id),
	CONSTRAINT recipes_recipe_url_key UNIQUE (recipe_url)
);

CREATE table ingredients (
	ingredient_id serial NOT NULL,
	ingredient varchar(50) NOT NULL,
	recipe_id int4 NOT NULL,
	
	CONSTRAINT ingredients_ingredient_key UNIQUE (ingredient),
	CONSTRAINT pk_ingredients PRIMARY KEY (ingredient_id),
	CONSTRAINT fk_ingredient_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);


CREATE TABLE user_favorite_recipes (
	user_id int4 NOT NULL,
	recipe_id int4 NOT NULL,
	
	CONSTRAINT no_duplicate_pair PRIMARY KEY (user_id, recipe_id),
	CONSTRAINT fk_favorite_recipe_id FOREIGN KEY (recipe_id) references recipes(recipe_id)
);
