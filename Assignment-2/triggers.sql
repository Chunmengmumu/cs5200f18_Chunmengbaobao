/*These are the triggers for websiterole*/

/*Insert*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_INSERT`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_INSERT` AFTER INSERT ON `websiterole` FOR EACH ROW
BEGIN
IF NEW.role =  'owner'  THEN
	INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('create',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('delete',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role =  'admin' THEN
	INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('create',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('delete',NEW.developer_id,NEW.website_id);
 ELSEIF NEW.role =  'writer' THEN
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('create',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role =  'editor' THEN
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role =  'reviewer' THEN
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
END IF;
END$$
DELIMITER ;

/*update*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_UPDATE`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_UPDATE` AFTER UPDATE ON `websiterole` FOR EACH ROW
BEGIN
    DELETE FROM websitepriviledge WHERE website_id=OLD.website_id AND developer_id=OLD.developer_id;
IF NEW.role='owner' OR 'admin' THEN
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('create',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('delete',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role='writer' THEN
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('create',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role='editor' THEN
	INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('update',NEW.developer_id,NEW.website_id);
    INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
ELSEIF NEW.role='reviewer' THEN
	INSERT into websitepriviledge (priviledge,developer_id,website_id) value ('read',NEW.developer_id,NEW.website_id);
END IF;
END$$
DELIMITER ;

/*delete*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_DELETE`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`websiterole_AFTER_DELETE` AFTER DELETE ON `websiterole` FOR EACH ROW
BEGIN
DELETE FROM websitepriviledge WHERE website_id=OLD.website_id AND developer_id=OLD.developer_id;
END$$
DELIMITER ;


/*These are the triggers for pagerole*/

/*insert*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_INSERT`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_INSERT` AFTER INSERT ON `pagerole` FOR EACH ROW
BEGIN
IF NEW.role =  'owner' OR 'admin'  THEN
	INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('create',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('delete',NEW.developer_id,NEW.page_id);
 ELSEIF NEW.role =  'writer' THEN
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('create',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
ELSEIF NEW.role =  'editor' THEN
   INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
ELSEIF NEW.role =  'reviewer' THEN
   INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
END IF;
END$$
DELIMITER ;

/*update*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_UPDATE`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_UPDATE` AFTER UPDATE ON `pagerole` FOR EACH ROW
BEGIN
    DELETE FROM pagepriviledge WHERE page_id=OLD.page_id AND developer_id=OLD.developer_id;
IF NEW.role =  'owner' OR 'admin'  THEN
	INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('create',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('delete',NEW.developer_id,NEW.page_id);
 ELSEIF NEW.role =  'writer' THEN
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('create',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
ELSEIF NEW.role =  'editor' THEN
   INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('update',NEW.developer_id,NEW.page_id);
    INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
ELSEIF NEW.role =  'reviewer' THEN
   INSERT into pagepriviledge (priviledge,developer_id,page_id) value ('read',NEW.developer_id,NEW.page_id);
END IF;
END$$
DELIMITER ;

/*delete*/
DROP TRIGGER IF EXISTS `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_DELETE`;
DELIMITER $$
USE `cs5200_fall2018_A2_Shengwei`$$
CREATE DEFINER = CURRENT_USER TRIGGER `cs5200_fall2018_A2_Shengwei`.`pagerole_AFTER_DELETE` AFTER DELETE ON `pagerole` FOR EACH ROW
BEGIN
    DELETE FROM pagepriviledge WHERE page_id=OLD.page_id AND developer_id=OLD.developer_id;
END$$
DELIMITER ;
