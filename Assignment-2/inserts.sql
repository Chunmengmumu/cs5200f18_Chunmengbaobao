/*1. Create the following developers and users. Insert into the correct tables depending on the type*/
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`person`(id,username,password,first_name,last_name,email) VALUE
(12,'alice','alice','Alice','Wonder','alice@wonder.com'),
(23,'bob','bob','Bob','Marley','bob@marley.com'),
(34,'charlie','charlie','Charles','Garcia','chuch@garcia.com'),
(45,'dan','dan','Dan','Martin','dan@martin.com'),
(56,'ed','ed','Ed','Karaz','ed@kar.com');

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`developer`(person_id,developerkey) VALUE
(12,'4321rewq'),
(23,'5432trew'),
(34,'6543ytre');

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`user`(person_id,user_key) VALUE
(45,'7654fda'),
(56,'678dfgh');


/*2. Create the following web sites for the developers above. For both the created field and updated field, use the date your assignment will be graded, e.g., do not hardcode it*/
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`website`(id,name,description,created,updated,visits,developer_id) VALUE
(123,'Facebook','an online social media and social networking service',curdate(),curdate(),1234234,12),
(234,'Twitter','an online news and social networking service',curdate(),curdate(),4321543,23),
(345,'Wikipedia','a free online encyclopedia',curdate(),curdate(),3456654,34),
(456,'CNN','an American basic cable and satellite television news channel',curdate(),curdate(),6543345,12),
(567,'CNET','an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',curdate(),curdate(),5433455,23),
(678,'Gizmodo','a design, technology, science and science fiction website that also writes articles on politics',curdate(),curdate(),4322345,34);

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`websiterole`(role,developer_id,website_id) VALUE
('owner',12,123),('editor',23,123),('admin',34,123),
('owner',23,234),('editor',34,234),('admin',12,234),
('owner',34,345),('editor',12,345),('admin',23,345),
('owner',12,456),('editor',23,456),('admin',34,456),
('owner',23,567),('editor',34,567),('admin',12,567),
('owner',34,678),('editor',12,678),('admin',23,678);


/*3.Create the following pages for the web sites above. Use the semester's start date for the created field. Use the assignment's due date for the updated field.*/
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`page`(id,title,description,created,updated,views,website_id) VALUES
(123,'Home','Landing page','2018-09-05','2018-10-08',123434,567),
(234,'About','Website description','2018-09-05','2018-10-08',234545,678),
(345,'Contact','Addresses,phones,and contact info','2018-09-05','2018-10-08',345656,345),
(456,'Perferences','Where users can configure their preferences','2018-09-05','2018-10-08',456776,456),
(567,'Profile','Users can configure their personal information','2018-09-05','2018-10-08',567878,567);

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`pagerole`(role,developer_id,page_id) VALUES
('editor',12,123),('reviewer',23,123),('writer',34,123),
('editor',23,234),('reviewer',34,234),('writer',12,234),
('editor',34,345),('reviewer',12,345),('writer',23,345),
('editor',12,456),('reviewer',23,456),('writer',34,456),
('editor',23,567),('reviewer',34,567),('writer',12,567);


/*4.Create the following widgets for the pages shown.*/
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`widget`(`id`,`name`,`dtype`,`text`,`order`,`page_id`) VALUES 
(123,'head123','heading','Welcome',0,123),
(234,'post234','html','<p>Lorem</p>',0,234),
(345,'head345','heading','Hi',1,345),
(456,'intro456','html','<h1>Hi</h1>',2,345);

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`widget`(`id`,`name`,`dtype`,`order`,`width`,`height`,`youtube_url`,`page_id`) VALUES
(567,'image345','image',3,50,100,'/img/567.png',345),
(678,'video456','youtube',0,400,300,'https://youtu.be/h67VX51QXiQ',456);

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`page`(`id`,`title`) VALUES
(123,'Home'),
(234,'About'),
(345,'Contact'),
(456,'Contact'),
(567,'Contact'),
(678,'Preferences');

/*5.Create the following phones and addresses for the users or developers shown*/
INSERT INTO `cs5200_fall2018_A2_Shengwei`.`phones`(`phone`,`primary`,`person_id`) VALUES 
('123-234-3456',1,12),('234-345-4566',0,12),
('345-456-5677',1,23),
('321-432-5435',1,34),('432-432-5433',0,34),('543-543-6544',0,34);

INSERT INTO `cs5200_fall2018_A2_Shengwei`.`address`(`street1`,`city`,`zip`,`primary`,`person_id`) VALUES 
('123 Adam St.','Alton','01234',1,12),('234 Birch St.','Boston','02345',0,12),
('345 Charles St.','Chelms','03455',1,23),('456 Down St.','Dalton','04566',0,23),('543 East St.','Everett','01112',0,23),
('654 Frank St.','Foulton','04322',1,34);
