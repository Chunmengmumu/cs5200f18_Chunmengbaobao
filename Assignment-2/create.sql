/*create schema*/
CREATE SCHEMA `cs5200_fall2018_A2_Shengwei`;

/*create person*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`id`));

/*create developer*/ 
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`developer` (
  `person_id` INT NOT NULL,
  `developerkey` VARCHAR(45) NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `developer_person_generation`
    FOREIGN KEY (`person_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create user*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`user` (
  `person_id` INT NOT NULL,
  `user_key` VARCHAR(45) NULL,
   `useragreement` TINYINT(1) NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `user_person_generation`
    FOREIGN KEY (`person_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create address*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `primary` TINYINT NULL,
  `person_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_person_composition_idx` (`person_id` ASC),
  CONSTRAINT `address_person_composition`
    FOREIGN KEY (`person_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create phones*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`phones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `primary` TINYINT(1) NOT NULL,
  `person_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `phone_person_composition_idx` (`person_id` ASC),
  CONSTRAINT `phone_person_composition`
    FOREIGN KEY (`person_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create website*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`website` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  `developer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `website_developer_aggregation_idx` (`developer_id` ASC),
  CONSTRAINT `website_developer_aggregation`
    FOREIGN KEY (`developer_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`developer` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*create page*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `views` INT NULL,
  `website_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `page_website_composition_idx` (`website_id` ASC),
  CONSTRAINT `page_website_composition`
    FOREIGN KEY (`website_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create dtype and insert the for type*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`dtype` (
  `dtype` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dtype`));

INSERT INTO `cs5200_fall2018_A2_ni`.`dtype` (`dtype`) VALUES ('heading');
INSERT INTO `cs5200_fall2018_A2_ni`.`dtype` (`dtype`) VALUES ('html');
INSERT INTO `cs5200_fall2018_A2_ni`.`dtype` (`dtype`) VALUES ('youtube');
INSERT INTO `cs5200_fall2018_A2_ni`.`dtype` (`dtype`) VALUES ('image');

/*create widget*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `order` INT NULL,
  `heading_size` INT NOT NULL DEFAULT 2,
  `html` VARCHAR(45) NULL,
  `youtube_url` VARCHAR(45) NULL,
  `youtube_shareble` TINYINT(1) NULL,
  `youtube_expandable` TINYINT(1) NULL,
  `dtype` VARCHAR(45) NOT NULL,
  `page_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `dtype_widget_enumeration_idx` (`dtype` ASC),
  INDEX `widget_dtype_composition_idx` (`page_id` ASC),
  CONSTRAINT `dtype_widget_enumeration`
    FOREIGN KEY (`dtype`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`dtype` (`dtype`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `widget_page_composition`
    FOREIGN KEY (`page_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create role and implement enumerations*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`role` (
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role`));

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`role` (`role`) VALUES ('owner'),('admin'),('writer'),('editor'),('reviewer');

/*create websiterole*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`websiterole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `developer_id` INT NULL,
  `website_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `websitrole_role_enumeration_idx` (`role` ASC),
  INDEX `websiterole_develop_association_idx` (`developer_id` ASC),
  INDEX `websiterole_web_association_idx` (`website_id` ASC),
  CONSTRAINT `websitrole_role_enumeration`
    FOREIGN KEY (`role`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`role` (`role`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_develop_association`
    FOREIGN KEY (`developer_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`developer` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_web_association`
    FOREIGN KEY (`website_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create priviledge and implement enumerations*/ 
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`priviledge` (
  `priviledge` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`priviledge`));
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`) VALUES ('create');
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`) VALUES ('read');
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`) VALUES ('update');
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`) VALUES ('delete');

/*create websitepriviledge*/
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`websitepriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `priviledge` VARCHAR(45) NULL,
  `website_id` INT NULL,
  `developer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `webpriviledge_priviledge_enumeration_idx` (`priviledge` ASC),
  INDEX `webpriviledge_developer_association_idx` (`developer_id` ASC),
  INDEX `webpriviledge_web_association_idx` (`website_id` ASC),
  CONSTRAINT `webpriviledge_priviledge_enumeration`
    FOREIGN KEY (`priviledge`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `webpriviledge_developer_association`
    FOREIGN KEY (`developer_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`developer` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `webpriviledge_web_association`
    FOREIGN KEY (`website_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create pagepriviledge*/   
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`pagepriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `priviledge` VARCHAR(45) NULL,
  `developer_id` INT NULL,
  `page_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pagepriviledge_priviledge_enumeration_idx` (`priviledge` ASC),
  INDEX `pagepriviledge_developer_association_idx` (`developer_id` ASC),
  INDEX `pagepriviledge_page_association_idx` (`page_id` ASC),
  CONSTRAINT `pagepriviledge_priviledge_enumeration`
    FOREIGN KEY (`priviledge`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`priviledge` (`priviledge`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_developer_association`
    FOREIGN KEY (`developer_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`developer` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_page_association`
    FOREIGN KEY (`page_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*create pagerole*/   
CREATE TABLE `cs5200_fall2018_A2_Shengwei`.`pagerole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `developer_id` INT NULL,
  `page_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pagerole_role_enumeration_idx` (`role` ASC),
  INDEX `pagerole_developer_association_idx` (`developer_id` ASC),
  INDEX `pagerole_page_association_idx` (`page_id` ASC),
  CONSTRAINT `pagerole_role_enumeration`
    FOREIGN KEY (`role`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`role` (`role`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_developer_association`
    FOREIGN KEY (`developer_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`developer` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_page_association`
    FOREIGN KEY (`page_id`)
    REFERENCES `cs5200_fall2018_A2_Shengwei`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
