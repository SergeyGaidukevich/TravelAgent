-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema travel_agent
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel_agent
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_agent` DEFAULT CHARACTER SET utf8 ;
USE `travel_agent` ;

-- -----------------------------------------------------
-- Table `travel_agent`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`countries` (
  `country_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE INDEX `Id_UNIQUE` (`country_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agent`.`hotels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`hotels` (
  `hotel_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `country_id` BIGINT NOT NULL,
  `stars` INT NOT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE INDEX `Id_UNIQUE` (`hotel_id` ASC) VISIBLE,
  INDEX `fk_hotel_country_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_hotel_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `travel_agent`.`countries` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agent`.`tours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`tours` (
  `tour_id` BIGINT NOT NULL,
  `photo` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `duration` VARCHAR(45) NOT NULL,
  `country_id` BIGINT NOT NULL,
  `hotel_id` BIGINT NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `cost` DOUBLE NOT NULL,
  PRIMARY KEY (`tour_id`),
  UNIQUE INDEX `id_UNIQUE` (`tour_id` ASC) VISIBLE,
  INDEX `fk_tour_hotel_idx` (`hotel_id` ASC) VISIBLE,
  INDEX `fk_tour_country_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_tour_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `travel_agent`.`countries` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tour_hotel`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `travel_agent`.`hotels` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agent`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`users` (
  `user_id` BIGINT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `Id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agent`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`reviews` (
  `review_id` BIGINT NOT NULL,
  `tour_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE INDEX `Id_UNIQUE` (`review_id` ASC) VISIBLE,
  INDEX `fk_review_tour_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_revoew_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_tour`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel_agent`.`tours` (`tour_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agent`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agent`.`user_tours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agent`.`user_tours` (
  `user_id` BIGINT NOT NULL,
  `tour_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `tour_id`),
  INDEX `fk_user_tour_tour_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_user_tour_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_tour_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agent`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_tour_tour`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel_agent`.`tours` (`tour_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
