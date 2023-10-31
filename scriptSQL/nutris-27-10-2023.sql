-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nutris
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nutris
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nutris` DEFAULT CHARACTER SET utf8 ;
USE `nutris` ;

-- -----------------------------------------------------
-- Table `nutris`.`comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nutris`.`comida` (
  `idComida` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `detalle` TEXT NOT NULL,
  `calorias` VARCHAR(10) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idComida`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nutris`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nutris`.`paciente` (
  `idPaciente` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `dni` VARCHAR(11) NOT NULL,
  `altura` VARCHAR(5) NOT NULL,
  `domicilio` VARCHAR(255) NULL DEFAULT NULL,
  `telefonoFijo` VARCHAR(20) NULL DEFAULT NULL,
  `celular` VARCHAR(30) NULL DEFAULT NULL,
  `mail` VARCHAR(100) NULL DEFAULT NULL,
  `estado` TINYINT(1) NOT NULL,
  `fechaNac` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idPaciente`),
  UNIQUE INDEX `dni` (`dni` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nutris`.`dieta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nutris`.`dieta` (
  `idDieta` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `idPaciente` INT(11) NOT NULL,
  `fechaInicio` DATE NOT NULL,
  `pesoInicial` VARCHAR(10) NULL DEFAULT NULL,
  `pesoActual` VARCHAR(10) NOT NULL,
  `fechafinal` DATE NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idDieta`),
  INDEX `idPaciente` (`idPaciente` ASC),
  CONSTRAINT `dieta_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `nutris`.`paciente` (`idPaciente`))
ENGINE = InnoDB
AUTO_INCREMENT = 301
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nutris`.`dietacomida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nutris`.`dietacomida` (
  `idDietaComida` INT(11) NOT NULL AUTO_INCREMENT,
  `idComida` INT(11) NOT NULL,
  `idDieta` INT(11) NOT NULL,
  `horario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idDietaComida`),
  INDEX `idDieta` (`idDieta` ASC),
  INDEX `idComida` (`idComida` ASC),
  CONSTRAINT `dietacomida_ibfk_1`
    FOREIGN KEY (`idDieta`)
    REFERENCES `nutris`.`dieta` (`idDieta`),
  CONSTRAINT `dietacomida_ibfk_2`
    FOREIGN KEY (`idComida`)
    REFERENCES `nutris`.`comida` (`idComida`))
ENGINE = InnoDB
AUTO_INCREMENT = 1001
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nutris`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nutris`.`historial` (
  `idHistorial` INT(11) NOT NULL AUTO_INCREMENT,
  `idPaciente` INT(11) NOT NULL,
  `idDieta` INT(11) NOT NULL,
  `pesoActual` VARCHAR(10) NULL DEFAULT NULL,
  `fechaRegistro` DATE NOT NULL,
  PRIMARY KEY (`idHistorial`),
  INDEX `idPaciente` (`idPaciente` ASC),
  INDEX `fk_historial_dieta1_idx` (`idDieta` ASC),
  CONSTRAINT `fk_historial_dieta1`
    FOREIGN KEY (`idDieta`)
    REFERENCES `nutris`.`dieta` (`idDieta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `historial_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `nutris`.`paciente` (`idPaciente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
