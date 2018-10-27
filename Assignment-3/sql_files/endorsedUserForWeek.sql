USE `cs5200_fall2018_Shengwei_Liu_jdbc`;
DROP procedure IF EXISTS `endorsedUserForWeek`;

DELIMITER $$
USE `cs5200_fall2018_Shengwei_Liu_jdbc`$$
CREATE DEFINER= CURRENT_USER PROCEDURE `endorsedUserForWeek`(IN dateStart DATE, IN dateEnd DATE)
BEGIN
   
select * from
(
select person.first_name from 
(select count(Temp2.questionId),Temp2.postedBy from (select Procedure_answer.postedBy,Procedure_answer.questionId from (Select * from cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_question where 
Procedure_question.postedOn>=dateStart and Procedure_question.postedOn<=dateEnd) Temp1
join cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_answer on Temp1.id=Procedure_answer.questionId
AND Procedure_answer.correctAnswer=true) Temp2 group by Temp2.postedBy
order by count(Temp2.questionId) desc limit 5) 
Temp3 join cs5200_fall2018_Shengwei_Liu_jdbc.person on person.id=Temp3.postedBy
)
Temp5 
order by Temp5.first_name;   
   
   
END$$

DELIMITER ;