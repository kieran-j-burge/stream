-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema stream
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stream
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stream` DEFAULT CHARACTER SET latin1 ;
USE `stream` ;

-- -----------------------------------------------------
-- Table `stream`.`msg_b`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stream`.`msg_b` (
  `msg_b_id` INT(11) NOT NULL AUTO_INCREMENT,
  `msg` VARCHAR(200) NULL DEFAULT NULL,
  `tweet_cat` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`msg_b_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `stream`.`msg_e`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stream`.`msg_e` (
  `msg_e_id` INT(11) NOT NULL AUTO_INCREMENT,
  `msg` VARCHAR(200) NULL DEFAULT NULL,
  `tweet_cat` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`msg_e_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
