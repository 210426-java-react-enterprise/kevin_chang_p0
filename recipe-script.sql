/*
 * grant all privileges on all tables in schema recipeapi to postgres;
 * select current_user;
 */
select current_user;
grant all privileges on all tables in schema recipeapi to postgres;
grant select, delete, insert, update on all tables in schema recipeapi to postgres;
grant usage on schema recipeapi to postgres;

show search_path;
set search_path to recipeapi;

/*
drop table users cascade;
drop table ingredients cascade;
drop table recipes cascade;
drop table user_favorite_recipes cascade;
drop table recipe_ingredient_table cascade;
*/

CREATE TABLE users (
	user_id serial NOT null constraint pk_user primary key,
	first_name varchar(25) NOT NULL,
	last_name varchar(25) NOT NULL,
	username varchar(20) unique NOT null,
	"password" varchar(255) NOT NULL,
	email varchar(255) unique NOT null
);

insert into users (user_id, first_name, last_name, username, "password", email)
	values (1, 'Administrator', 'Administrator', 'admin_user', 'adminpassword', 'admin@company.net');

select * from users where username = 'admin_user' and password = 'adminpassword';  

select * from users;
select * from ingredients;
select * from recipes;
select * from recipe_ingredient_table;


delete from users;
delete from ingredients;
delete from recipes;
delete from recipe_ingredient_table;


delete from users where first_name = 'Administrator';

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
	
	CONSTRAINT ingredients_ingredient_key UNIQUE (ingredient),
	CONSTRAINT pk_ingredients PRIMARY KEY (ingredient_id)
);

CREATE TABLE recipe_ingredient_table (
	recipe_id int4 NOT NULL,
	ingredient_id int4 NOT NULL,
	CONSTRAINT no_duplicate_recipe_ingredient_pair PRIMARY KEY (recipe_id, ingredient_id),
	CONSTRAINT fk_ingredient_to_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id),
	CONSTRAINT fk_recipe_to_recipe FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);


CREATE TABLE user_favorite_recipes (
	user_id int4 NOT NULL,
	recipe_id int4 NOT NULL,
	
	CONSTRAINT no_duplicate_pair PRIMARY KEY (user_id, recipe_id),
	CONSTRAINT fk_favorite_recipe_id FOREIGN KEY (recipe_id) references recipes(recipe_id)
);





