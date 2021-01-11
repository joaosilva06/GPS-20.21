SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BugTrackerDataBase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BugTrackerDataBase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BugTrackerDataBase` DEFAULT CHARACTER SET utf8 ;
USE `BugTrackerDataBase` ;

-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `dateCreateAccount` DATE NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Project` (
  `idProject` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dateCreation` DATE NOT NULL,
  PRIMARY KEY (`idProject`),
  UNIQUE INDEX `idProject_UNIQUE` (`idProject` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE INDEX `idRole_UNIQUE` (`idRole` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Member` (
  `idMember` INT NOT NULL AUTO_INCREMENT,
  `Role_idRole` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  `Project_idProject` INT NOT NULL,
  PRIMARY KEY (`idMember`),
  UNIQUE INDEX `idMember_UNIQUE` (`idMember` ASC),
  CONSTRAINT `fk_Member_Role1`
    FOREIGN KEY (`Role_idRole`)
    REFERENCES `BugTrackerDataBase`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Member_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `BugTrackerDataBase`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Member_Project1`
    FOREIGN KEY (`Project_idProject`)
    REFERENCES `BugTrackerDataBase`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idRole_UNIQUE` (`idStatus` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Priority` (
  `idPriority` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPriority`),
  UNIQUE INDEX `idRole_UNIQUE` (`idPriority` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Type` (
  `idType` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idType`),
  UNIQUE INDEX `idRole_UNIQUE` (`idType` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Module` (
  `idModule` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dateCreation` DATE NOT NULL,
  `Project_idProject` INT NOT NULL,
  PRIMARY KEY (`idModule`),
  UNIQUE INDEX `idModule_UNIQUE` (`idModule` ASC),
  CONSTRAINT `fk_Module_Project1`
    FOREIGN KEY (`Project_idProject`)
    REFERENCES `BugTrackerDataBase`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BugTrackerDataBase`.`Bug`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BugTrackerDataBase`.`Bug` (
  `idBug` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(512) NOT NULL,
  `dateCreation` DATE NOT NULL,
  `dateSolved` DATE NULL,
  `Status_idStatus` INT NOT NULL,
  `Priority_idPriority` INT NOT NULL,
  `Type_idType` INT NOT NULL,
  `Module_idModule` INT NULL,
  `Project_idProject` INT NOT NULL,
  `User_idCreator` INT NOT NULL,
  PRIMARY KEY (`idBug`),
  UNIQUE INDEX `idBug_UNIQUE` (`idBug` ASC),
  CONSTRAINT `fk_Bug_Status1`
    FOREIGN KEY (`Status_idStatus`)
    REFERENCES `BugTrackerDataBase`.`Status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bug_Priority1`
    FOREIGN KEY (`Priority_idPriority`)
    REFERENCES `BugTrackerDataBase`.`Priority` (`idPriority`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bug_Type1`
    FOREIGN KEY (`Type_idType`)
    REFERENCES `BugTrackerDataBase`.`Type` (`idType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bug_Module1`
    FOREIGN KEY (`Module_idModule`)
    REFERENCES `BugTrackerDataBase`.`Module` (`idModule`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bug_Project1`
    FOREIGN KEY (`Project_idProject`)
    REFERENCES `BugTrackerDataBase`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bug_User1`
    FOREIGN KEY (`User_idCreator`)
    REFERENCES `BugTrackerDataBase`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

Insert into Role(description) values("Owner");
Insert into Role(description) values("Admin");
Insert into Role(description) values("Developer");
Insert into Role(description) values("Tester");
                                
Insert into Priority(description) values("High");
Insert into Priority(description) values("Medium");
Insert into Priority(description) values("Low");
                                
Insert into Status(description) values("Solved");
Insert into Status(description) values("InProgress");
Insert into Status(description) values("ToSolve");

Insert into Type(description) values("CompilationError");
Insert into Type(description) values("RuntimeError");
Insert into Type(description) values("LogicMalfunction");
                                
                                
                                
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
