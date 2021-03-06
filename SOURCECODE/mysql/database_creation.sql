-- MySQL Script generated by MySQL Workbench
-- Fri Oct 29 12:13:20 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydbrecipe
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rechefdb` DEFAULT CHARACTER SET utf8 ;
USE `rechefdb` ;

-- -----------------------------------------------------
-- Table `rechefdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rechefdb`.`user` ;

CREATE TABLE IF NOT EXISTS `rechefdb`.`user` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `username` TEXT NOT NULL,
  `password` TEXT NOT NULL,
  `email` TEXT NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rechefdb`.`recipe` ;

CREATE TABLE IF NOT EXISTS `rechefdb`.`recipe` (
  `recipeID` INT NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `url` TEXT NOT NULL,
  `servings` TEXT NOT NULL,
  `rating` TEXT NOT NULL,
  `imageurl` TEXT NOT NULL,
  `type` TEXT NOT NULL,
  `instructions` TEXT NOT NULL,
  `ingredients` TEXT NOT NULL,
  PRIMARY KEY (`recipeID`))
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `rechefdb`.`savedRecipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rechefdb`.`savedRecipe` ;

CREATE TABLE IF NOT EXISTS `rechefdb`.`savedRecipe` (
  `idsavedRecipe` INT NOT NULL AUTO_INCREMENT,
  `user_userid` INT NOT NULL,
  `recipe_recipeID` INT NOT NULL,
  PRIMARY KEY (`idsavedRecipe`, `recipe_recipeID`),
  UNIQUE INDEX `idsavedRecipe_UNIQUE` (`idsavedRecipe` ASC) VISIBLE,
  INDEX `fk_savedRecipe_user1_idx` (`user_userid` ASC) VISIBLE,
  INDEX `fk_savedRecipe_recipe1_idx` (`recipe_recipeID` ASC) VISIBLE,
  CONSTRAINT `fk_savedRecipe_user1`
    FOREIGN KEY (`user_userid`)
    REFERENCES `mydb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_savedRecipe_recipe1`
    FOREIGN KEY (`recipe_recipeID`)
    REFERENCES `mydb`.`recipe` (`recipeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
