CREATE TABLE user (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	firstName VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL,
	birthDay DATETIME NOT NULL,
	login VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	phone VARCHAR(13)
);

CREATE TABLE car(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	year INT NOT NULL,
	licensePlate VARCHAR(7),
	model VARCHAR(60),
	color VARCHAR(60),
	id_user BIGINT NOT NULL,
	
	FOREIGN KEY (id_user) REFERENCES user(id))
	
	