/*Implement Queries*/

/*1. Retrieve developers*/
/*1.a*/
SELECT person.username FROM developer JOIN person ON developer.person_id=person.id;

/*1.b*/
SELECT person.username  FROM developer JOIN person ON 
developer.person_id=person.id AND developer.person_id=34;

/*1.c*/
SELECT person.username FROM (SELECT websiterole.developer_id FROM (SELECT * FROM website WHERE website.name='Twitter') a JOIN websiterole 
ON a.id=websiterole.website_id AND websiterole.role <> 'owner') b JOIN
person WHERE b.developer_id=person.id;

/*1.d*/
SELECT person.username FROM (SELECT pagerole.developer_id FROM (SELECT * FROM page WHERE views < 300000) d1 
JOIN pagerole ON d1.id=pagerole.page_id AND pagerole.role = 'reviewer') d2 JOIN person
ON d2.developer_id=person.id;

/*1.e*/
SELECT person.username FROM person JOIN (SELECT pagerole.developer_id FROM (SELECT page.id FROM (SELECT * FROM website WHERE name='CNET') e1 JOIN page
ON e1.id = page.website_id AND page.title='Home') e2 JOIN pagerole ON
e2.id=pagerole.page_id AND pagerole.role='writer') e3 ON person.id=e3.developer_id;


/*2. Retrieve websites*/
/*2.a*/
SELECT website.name FROM website WHERE website.visits=
(SELECT MIN(website.visits) FROM website);

/*2.b*/
SELECT website.name FROM website WHERE website.id=678;

/*2.c*/
SELECT website.name FROM (SELECT page.website_id FROM  (SELECT widget.page_id FROM 
(SELECT pagerole.page_id FROM (SELECT id FROM person WHERE person.username='bob') wb1
JOIN pagerole ON pagerole.developer_id=wb1.id AND pagerole.role='reviewer') wb2 
JOIN widget ON wb2.page_id=widget.page_id AND widget.dtype='youtube') wb3 
JOIN page ON wb3.page_id=page.id) wb4 
JOIN website ON wb4.website_id=website.id;

/*2.d*/
SELECT website.name FROM (SELECT websiterole.website_id FROM (SELECT id FROM person WHERE person.username='alice') wb5
JOIN websiterole ON wb5.id=websiterole.developer_id AND websiterole.role='owner') wb6
JOIN website ON wb6.website_id=website.id;

/*2.e*/
SELECT website.name FROM (SELECT websiterole.website_id FROM (SELECT id FROM person WHERE person.username='charlie') wb7
JOIN websiterole ON wb7.id=websiterole.developer_id AND websiterole.role='admin') wb8
JOIN website ON wb8.website_id=website.id AND website.visits > 6000000;


/*3.Retrieve pages*/
/*3.a*/
SELECT page.title FROM page WHERE page.views=
(SELECT MAX(page.views) FROM page);

/*3.b*/
SELECT page.title FROM page WHERE page.id=234;

/*3.c*/
SELECT page.title FROM (SELECT pagerole.page_id FROM (SELECT id FROM person WHERE person.username='alice') pg1
JOIN pagerole ON pg1.id=pagerole.developer_id AND pagerole.role='editor') pg2
JOIN page ON pg2.page_id=page.id;

/*3.d*/
SELECT SUM(pg4.views) FROM (SELECT page.views FROM (SELECT website.id FROM website WHERE website.name='CNET') pg3 
JOIN page ON pg3.id=page.website_id) pg4;

/*3.e*/
SELECT AVG(pg6.views) FROM (SELECT page.views FROM (SELECT website.id FROM website WHERE website.name='Wikipedia') pg5
JOIN page ON pg5.id=page.website_id) pg6;


/*4. (3pts.) Retrieve widgets*/
/*4.a*/
SELECT widget.name from (SELECT page.id from (SELECT website.id FROM website WHERE website.name='CNET') wg1
JOIN page ON wg1.id=page.website_id AND page.title='Home') wg2 JOIN
widget on wg2.id=widget.page_id;

/*4.b*/
SELECT widget.name from (SELECT page.id from (SELECT website.id FROM website WHERE website.name='CNN') wg3
JOIN page ON wg3.id=page.website_id) wg4 JOIN
widget on wg4.id=widget.page_id AND widget.dtype='youtube';

/*4.c*/
SELECT widget.name FROM (SELECT pagerole.page_id FROM (SELECT id FROM person WHERE username='alice') wg5
JOIN pagerole ON wg5.id=pagerole.developer_id AND pagerole.role='reviewer') wg6 JOIN
widget ON wg6.page_id=widget.page_id;

/*4.d*/
SELECT COUNT(wg9.widget_name) FROM (SELECT widget.name AS widget_name FROM widget JOIN 
(SELECT page.id FROM (SELECT website.id FROM website WHERE name='Wikipedia') wg7
JOIN page ON wg7.id=page.website_id) wg8 ON widget.page_id=wg8.id) wg9;


/*5.To verify the page and website triggers written earlier function properly:*/
/*5.a*/
SELECT website.name FROM (SELECT websitepriviledge.website_id FROM 
(SELECT id FROM person WHERE person.first_name='Bob') v1 JOIN websitepriviledge
ON v1.id=websitepriviledge.developer_id AND websitepriviledge.priviledge='delete') v2
JOIN website ON v2.website_id=website.id;

/*5.b*/
SELECT page.title FROM (SELECT pagepriviledge.page_id FROM 
(SELECT id FROM person WHERE person.username='charlie') v3 JOIN pagepriviledge
ON v3.id=pagepriviledge.developer_id AND pagepriviledge.priviledge='create') v4
JOIN page ON v4.page_id=page.id;
