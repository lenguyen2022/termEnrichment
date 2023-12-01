-- -----------------------------------------------------
-- Schema termdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `termdb` ;

-- -----------------------------------------------------
-- Schema termdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `termdb` DEFAULT CHARACTER SET utf8 ;
USE `termdb` ;

-- -----------------------------------------------------
-- Table `termdb`.`term`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `termdb`.`term` ;

CREATE TABLE IF NOT EXISTS `termdb`.`term` (
  `term_id` BIGINT NOT NULL AUTO_INCREMENT,
  `species` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NULL,
  `t_year` VARCHAR(45) NULL,
  PRIMARY KEY (`term_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `termdb`.`enriched_term`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `termdb`.`enriched_term` ;

CREATE TABLE IF NOT EXISTS `termdb`.`enriched_term` (
  `enriched_term_id` BIGINT NOT NULL AUTO_INCREMENT,
  `species` VARCHAR(45) NOT NULL,
  `term_id` BIGINT NOT NULL,
  PRIMARY KEY (`enriched_term_id`),
  INDEX `fk_enriched_term_term_idx` (`term_id` ASC) VISIBLE,
  CONSTRAINT `fk_enriched_term`
    FOREIGN KEY (`term_id`)
    REFERENCES `termdb`.`term` (`term_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;