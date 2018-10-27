USE `cs5200_fall2018_Shengwei_Liu_jdbc`;
DROP procedure IF EXISTS `getUnansweredQuestions`;

DELIMITER $$
USE `cs5200_fall2018_Shengwei_Liu_jdbc`$$
CREATE DEFINER=CURRENT_USER PROCEDURE `getUnansweredQuestions`(IN moduleSet VARCHAR(45))
BEGIN

select Procedure_widget.wname,temp5.qtext,temp5.nuOfa_id 
from 
(
select Procedure_question.widgetId,temp4.q_id,temp4.qtext,temp4.nuOfa_id from cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_question join
(select count(temp3.a_id) as nuOfa_id,temp3.qtext,temp3.q_id from (
select temp2.q_id,temp2.qtext,Procedure_answer.id as a_id from (select id as q_id,qtext from (select id,qtext from cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_question where module=moduleSet) temp1 where temp1.id not in
(select questionId from cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_answer where correctAnswer=true)) temp2
join cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_answer on temp2.q_id=Procedure_answer.questionId
) temp3 group by temp3.q_id
order by count(temp3.a_id) desc limit 1) temp4 on
Procedure_question.id=temp4.q_id
) 
temp5 join cs5200_fall2018_Shengwei_Liu_jdbc.Procedure_widget on
temp5.widgetId=Procedure_widget.id;

END$$

DELIMITER ;