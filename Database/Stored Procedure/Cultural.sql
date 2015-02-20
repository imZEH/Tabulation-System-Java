DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`Cultural` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cultural`(in name varchar(50))
BEGIN
 SELECT t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(cr.Cr_Computed) as total, co.COS_TotalScore from participants p
inner join teams t on p.Team_ID=t.Team_ID
inner join attended_culturalevents ac on p.Part_ID = ac.Part_ID
inner join criteria_score cr on ac.ACE_ID = cr.ACE_ID
inner join cultural_overall_score co on ac.ACE_ID = co.ACE_ID
inner join cultural_events ce on ac.Cul_ID = ce.Cul_ID where ce.Cul_Name = name
group by fullname ;


END $$

DELIMITER ;