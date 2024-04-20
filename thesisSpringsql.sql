USE thesisspringapp;

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

create table faculty(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    avatar VARCHAR(255),
    useruniversityid varchar(10) unique not null,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    firstName VARCHAR(40),
    lastName VARCHAR(40),
    gender VARCHAR(10) ,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(10) unique ,
    birthday Datetime,
    active BIT default 0,
	role_id INT not null,
	FOREIGN KEY (role_id) REFERENCES role(id),
    faculty_id INT not null , 
    FOREIGN KEY (faculty_id) REFERENCES faculty(id)
);


create table thesis(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    create_date DATETIME,
    update_date DATETIME,
    score float ,
    active BIT default 0
);

create table thesis_user(
	id INT AUTO_INCREMENT PRIMARY KEY,
	thesis_id INT ,
	FOREIGN KEY (thesis_id) REFERENCES thesis(id),
    user_id INT,
	FOREIGN KEY (user_id) REFERENCES user(id)
);

create table committee (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50)
);

create table thesis_committee_rate(
	id INT AUTO_INCREMENT PRIMARY KEY,
	thesis_id INT ,
	FOREIGN KEY (thesis_id) REFERENCES thesis(id),
    committee_id INT,
	FOREIGN KEY (committee_id) REFERENCES committee(id),
    status VARCHAR(50)
);

create table member(
	id INT AUTO_INCREMENT PRIMARY KEY,
	role VARCHAR(50),
    user_id INT ,
	FOREIGN KEY (user_id) REFERENCES user(id),
    committee_id INT, 
	FOREIGN KEY (committee_id) REFERENCES committee(id)
);

create table criteria(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
    active BIT default 0
);

create table score( 
	id INT AUTO_INCREMENT PRIMARY KEY,
    thesis_id INT,
	FOREIGN KEY (thesis_id) REFERENCES thesis(id),
    criteria_id INT,
	FOREIGN KEY (criteria_id) REFERENCES criteria(id),
    user_id INT,
	FOREIGN KEY (user_id) REFERENCES member(user_id),
    committee_id INT,
	FOREIGN KEY (committee_id) REFERENCES committee(id)
);
