USE `cs5200_fall2018_A2_Shengwei`;
/*DROP  VIEW  `developer_roles_and_priviledges`;*/
CREATE  VIEW `web1` AS SELECT websiterole.role, websitepriviledge.priviledge,
       websiterole.developer_id,websiterole.website_id
FROM   websiterole
JOIN   websitepriviledge
ON     websiterole.developer_id=websitepriviledge.developer_id
AND    websiterole.website_id=websitepriviledge.website_id;

CREATE VIEW `web2` AS SELECT 
web1.developer_id,web1.website_id,web1.role, web1.priviledge,
website.name,website.updated,website.visits
FROM  web1
JOIN website
ON web1.website_id=website.id;

CREATE  VIEW `page1` AS SELECT pagerole.role, pagepriviledge.priviledge,
       pagerole.developer_id,pagerole.page_id
FROM   pagerole
JOIN   pagepriviledge
ON     pagerole.developer_id=pagepriviledge.developer_id
AND    pagerole.page_id=pagepriviledge.page_id;

CREATE VIEW `page2` AS SELECT 
page.website_id,page1.role, page1.priviledge,page.title,page.updated,page.views
FROM  page1
JOIN page
ON page1.page_id=page.id;

CREATE VIEW `webpage` AS SELECT 
web2.developer_id,
web2.name,web2.visits,web2.updated AS web_updated,web2.role AS web_role,web2.priviledge AS web_priviledge,
page2.title, page2.views,page2.updated AS page_updated,page2.role AS page_role, page2.priviledge AS page_priviledge
FROM  page2
JOIN web2
ON page2.website_id=web2.website_id;

CREATE VIEW `person_d` AS SELECT 
person.first_name,person.last_name,person.username,person.email,person.id
FROM  person
JOIN developer
ON person.id=developer.person_id;

CREATE VIEW `developer_roles_and_priviledges` AS SELECT 
webpage.developer_id,
person_d.first_name,person_d.last_name,person_d.username,person_d.email,
webpage.name,webpage.visits,webpage.web_updated,webpage.web_role,webpage.web_priviledge,
webpage.title, webpage.views,webpage.page_updated,webpage.page_role,webpage.page_priviledge
FROM  person_d
JOIN webpage
ON person_d.id=webpage.developer_id;
