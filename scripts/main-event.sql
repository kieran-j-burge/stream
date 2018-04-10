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
-- Table `stream`.`main_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stream`.`main_event` (
  `main_event_id` INT(11) NOT NULL AUTO_INCREMENT,
  `game_id` INT(11) NULL DEFAULT NULL,
  `live` INT(11) NULL DEFAULT NULL,
  `code` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`main_event_id`),
  INDEX `fk_main_event_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_main_event_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `stream`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;