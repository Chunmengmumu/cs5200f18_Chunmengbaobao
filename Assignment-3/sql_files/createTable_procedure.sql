CREATE TABLE `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `wname` VARCHAR(45) NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` VARCHAR(145) NULL,
  `order` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_user` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `approvedUser` TINYINT NULL,
  `userAgreement` TINYINT NULL,
  PRIMARY KEY (`person_id`));
  
  CREATE TABLE `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_module` (
  `Module` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`module`));

INSERT INTO `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_module` (`module`) VALUES 
('Project1'),('Project2'),('Assignment1'),('Assignment2'),('Quiz1'),('Exam'),('Logistics');

CREATE TABLE `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `askedBy` INT NULL,
  `postedOn` DATE NULL,
  `length` INT NULL,
  `views` INT NULL,
  `endorsedByInstructor` TINYINT NULL,
  `module` VARCHAR(45) NULL,
  `qtext` VARCHAR(145) CHARACTER SET 'ascii' NULL,
  `widgetId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `question_user_association_idx` (`askedBy` ASC),
  INDEX `question_module_enumeration_idx` (`module` ASC),
   INDEX `question_widget_generation_idx` (`widgetId` ASC),
  CONSTRAINT `question_user_association`
    FOREIGN KEY (`askedBy`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_user` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `question_module_enumeration`
    FOREIGN KEY (`module`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_module` (`Module`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `question_widget_generation`
    FOREIGN KEY (`widgetId`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_widget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
    CREATE TABLE `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `postedby` INT NULL,
  `postedOn` DATE NULL,
  `upVotes` INT NULL,
  `correctAnswer` TINYINT NULL,
  `downVotes` INT NULL,
  `questionId` INT NULL,
  `widgetId` INT NULL,
  `atext` VARCHAR(145) NULL,
  PRIMARY KEY (`id`),
  INDEX `answer_user_association_idx` (`postedby` ASC),
  INDEX `answer_question_association_idx` (`questionId` ASC),
  INDEX `answer_widget_generation_idx` (`widgetId` ASC),
  CONSTRAINT `answer_user_association`
    FOREIGN KEY (`postedby`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_user` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `answer_question_association`
    FOREIGN KEY (`questionId`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `answer_widget_generation`
    FOREIGN KEY (`widgetId`)
    REFERENCES `cs5200_fall2018_Shengwei_Liu_jdbc`.`Procedure_widget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);