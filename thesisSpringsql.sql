drop schema  if exists `thesisspringapp`;

create schema `thesisspringapp`;

USE `thesisspringapp`;



CREATE TABLE `role` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

create table `faculty`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `user` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `avatar` VARCHAR(255),
    `useruniversityid` varchar(10) unique not null,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `firstName` VARCHAR(40),
    `lastName` VARCHAR(40),
    `gender` VARCHAR(10) ,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `phone` VARCHAR(10) unique ,
    `birthday` datetime not null,
    `active` BIT default 0,
	`role_id` INT not null,
	CONSTRAINT `FK_ROLE_USER` FOREIGN KEY (`role_id`) REFERENCES `role`(`id`),
    `faculty_id` INT not null , 
    CONSTRAINT `FK_FACULTY_USER` FOREIGN KEY (`faculty_id`) REFERENCES `faculty`(`id`)
);


create table `thesis`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    `create_date` DATETIME DEFAULT NULL,
    `update_date` DATETIME DEFAULT NULL,
    `score` float ,
    `active` BIT DEFAULT b'0'
);

create table `thesis_user`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`thesis_id` INT ,
	CONSTRAINT `FK_THESIS_THESISUSER` FOREIGN KEY (`thesis_id`) REFERENCES `thesis`(`id`),
    `user_id` INT,
	CONSTRAINT `FK_USER_THESISUSER` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

create table `committee` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(50) not null unique,
    active BIT DEFAULT b'1'
);

create table `thesis_status`(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
	`status_name` varchar(20) not null
);

create table `thesis_committee_rate`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`thesis_id` INT ,
	CONSTRAINT `FK_THESIS_THESISCOMMITTEERATE` FOREIGN KEY (`thesis_id`) REFERENCES `thesis`(`id`),
    `committee_id` INT,
	CONSTRAINT `FK_COMMITTEE_THESISCOMMITTEERATE` FOREIGN KEY (`committee_id`) REFERENCES `committee`(`id`),
    `status_id` int,
    CONSTRAINT `FK_STATUS_THESISCOMMITTEERATE` foreign key (`status_id`) references `thesis_status`(`id`)
);

create table `committee_user`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`role` VARCHAR(50),
    `user_id` INT ,
	CONSTRAINT `FK_USER_COMMITTEEUSER` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
    `committee_id` INT, 
	CONSTRAINT `FK_COMMITTEE_COMMITTEEUSER` FOREIGN KEY (`committee_id`) REFERENCES `committee`(`id`)
);

create table `criteria`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100),
    `active` BIT default b'1'
);

create table `score`( 
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `score` float not null,
    `user_id` INT,
    CONSTRAINT `FK_USER_SCORE` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
    `thesis_id` INT,
	CONSTRAINT `FK_THESIS_SCORE` FOREIGN KEY (`thesis_id`) REFERENCES `thesis`(`id`),
    `criteria_id` INT,
	CONSTRAINT `FK_CRITERIA_SCORE` FOREIGN KEY (`criteria_id`) REFERENCES `criteria`(`id`),
    `committee_user_id` int,
    CONSTRAINT `FK_COMMITTEEUSER_SCORE` Foreign key (`committee_user_id`) References `committee_user`(`id`)
);

create table `paymentvnpaydetail`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`order_id` VARCHAR(20) not null,
    `amount` float not null,
    `order_desc` varchar(255),
    `vnp_TransactionNo` varchar(255),
    `vnp_ResponseCode` varchar(255),
    `user_id` INT,
    CONSTRAINT `FK_VNPAY_USER` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
)


