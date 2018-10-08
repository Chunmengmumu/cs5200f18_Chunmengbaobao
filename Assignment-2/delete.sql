/*Implement Deletes*/

/*1.delete developer*/
DELETE FROM address WHERE 
person_id=(SELECT id FROM person WHERE first_name='Alice')
AND address.primary=1;

/*2.delete widget*/
DELETE FROM widget WHERE id=
(SELECT a2.id FROM (SELECT * FROM widget WHERE widget.page_id=(SELECT id FROM page WHERE title='Contact')) a2 
WHERE a2.order=(SELECT MAX(a1.order) FROM (SELECT * FROM widget WHERE widget.page_id=(SELECT id FROM page WHERE title='Contact')) a1)
);

/*3.delete page*/
DELETE FROM pagerole WHERE page_id=(SELECT a4.id FROM (SELECT * FROM page WHERE website_id=(SELECT id FROM website WHERE name='Wikipedia'))a4
WHERE a4.updated=(SELECT MAX(a3.updated) FROM 
(SELECT * FROM page WHERE website_id=(SELECT id FROM website WHERE name='Wikipedia')) a3));

DELETE FROM page
WHERE id=(SELECT a4.id FROM (SELECT * FROM page WHERE website_id=(SELECT id FROM website WHERE name='Wikipedia'))a4
WHERE a4.updated=(SELECT MAX(a3.updated) FROM 
(SELECT * FROM page WHERE website_id=(SELECT id FROM website WHERE name='Wikipedia')) a3));

/*4.delete website*/
DELETE FROM website WHERE id=
(SELECT a8.id FROM (SELECT * FROM website WHERE name='CNET') a8);
