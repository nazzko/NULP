-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lab5a
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab5a
-- -----------------------------------------------------
Drop schema if exists mylibrary;
CREATE SCHEMA IF NOT EXISTS mylibrary DEFAULT CHARACTER SET utf8 ;
USE mylibrary ;

CREATE TABLE IF NOT EXISTS Customer (
  IDCustomer BIGINT NOT NULL AUTO_INCREMENT,
  CustomerName VARCHAR(45) NOT NULL,
  Surname VARCHAR(45) NOT NULL,
  Email VARCHAR(50) NULL,
  PRIMARY KEY (IDCustomer)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Shop (
  IDShop BIGINT NOT NULL AUTO_INCREMENT,
  Shop VARCHAR(25) NOT NULL,
  PRIMARY KEY (IDShop)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS product (
  IDProduct BIGINT NOT NULL AUTO_INCREMENT,
  ProductName VARCHAR(25) NOT NULL,
  Amount BIGINT NOT NULL,
  IDShop BIGINT NULL,
  Price BIGINT NOT NULL,
  PRIMARY KEY (IDProduct),
  CONSTRAINT fk_product_Shop1
  FOREIGN KEY (IDShop)
  REFERENCES mylibrary.Shop (IDShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS productcustomer (
  IDProduct BIGINT NOT NULL,
  IDCustomer BIGINT NOT NULL,
  PRIMARY KEY (IDProduct, IDCustomer),
  CONSTRAINT productcustomer_ibfk_1
  FOREIGN KEY (IDProduct)
  REFERENCES mylibrary.product (IDProduct),
  CONSTRAINT productcustomer_ibfk_2
  FOREIGN KEY (IDCustomer)
  REFERENCES mylibrary.customer (IDCustomer)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  product VARCHAR(50) NOT NULL,
  customer VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;