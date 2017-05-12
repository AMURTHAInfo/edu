DROP DATABASE IF EXISTS sims_service;
CREATE DATABASE `sims_service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE sims_service;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `login_id` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `added_by` varchar(60) DEFAULT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` varchar(60) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL ,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `technologies` varchar(200) DEFAULT NULL,
  `designation`varchar(200) DEFAULT NULL,
  `salary` int(11) NOT NULL ,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `designation` (
  `designation_code` varchar(11) NOT NULL,
  `designation_name` varchar(11) NOT NULL ,
  PRIMARY KEY (`designation_code`),
  UNIQUE KEY `designation_code_id_UNIQUE` (`designation_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/* to inser the questiontype values*/
use sims_service;
INSERT INTO user (user_type,first_name,last_name,email,login_id,password) 
VALUES ('SuperAdmin','SuperAdmin','User','superadmin@amurtha.com','admin','admin');

INSERT INTO designation (designation_code,designation_name) 
VALUES ('L01','TSE'),('L02','ASE'),('L03','SE'),('L04','SSE'),('L05','TL'),('L06','PL');






