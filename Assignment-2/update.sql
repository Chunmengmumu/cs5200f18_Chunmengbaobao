/*Implement Updates*/

/*1 update developer */
UPDATE phones set phone='333-444-5555' WHERE phones.primary=1 AND phones.person_id =
(SELECT id FROM person WHERE username='charlie');

/*2 update widget*/
UPDATE widget set widget.order=3 WHERE widget.id=345;
UPDATE widget set widget.order=1 WHERE widget.id=456;
UPDATE widget set widget.order=2 WHERE widget.id=567;

/*3 update page*/
UPDATE page SET title = CONCAT('CNET -',title) WHERE page.website_id=
(SELECT id FROM website WHERE website.name='CNET');

/*4 update roles */
 ALTER TABLE `cs5200_fall2018_A2_Shengwei`.`pagerole` ADD COLUMN `temp` VARCHAR(45) NULL AFTER `page_id`;
 UPDATE pagerole SET temp=role WHERE pagerole.id <> 0;
 
 UPDATE pagerole SET role=(SELECT d1.temp FROM (SELECT * FROM pagerole WHERE pagerole.page_id=(SELECT id FROM page WHERE page.website_id=(SELECT id FROM website WHERE name='CNET')
 AND page.title LIKE '%Home%'))d1 WHERE d1.developer_id=(SELECT id FROM person WHERE username='bob'))
 WHERE pagerole.page_id=(SELECT id FROM page WHERE page.website_id=(SELECT id FROM website WHERE name='CNET')
 AND page.title LIKE '%Home%') AND pagerole.developer_id=(SELECT id FROM person WHERE username='charlie');
 
 UPDATE pagerole SET role=(SELECT d1.temp FROM (SELECT * FROM pagerole WHERE pagerole.page_id=(SELECT id FROM page WHERE page.website_id=(SELECT id FROM website WHERE name='CNET')
 AND page.title LIKE '%Home%'))d1 WHERE d1.developer_id=(SELECT id FROM person WHERE username='charlie'))
 WHERE pagerole.page_id=(SELECT id FROM page WHERE page.website_id=(SELECT id FROM website WHERE name='CNET')
 AND page.title LIKE '%Home%') AND pagerole.developer_id=(SELECT id FROM person WHERE username='bob');
 
ALTER TABLE `cs5200_fall2018_A2_Shengwei`.`pagerole` DROP COLUMN `temp`;
