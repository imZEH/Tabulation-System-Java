DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`Module_Judge_generate_rank` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Module_Judge_generate_rank`(in id integer)
BEGIN
  Delete from results;
  Delete from cultural_generated_ranking_points;

  SELECT COS_ID,t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(cr.Cr_Computed) as total, co.COS_TotalScore from participants p
                inner join teams t on p.Team_ID=t.Team_ID
                inner join attended_culturalevents ac on p.Part_ID = ac.Part_ID
                inner join criteria_score cr on ac.ACE_ID = cr.ACE_ID
                inner join cultural_overall_score co on ac.ACE_ID = co.ACE_ID
                inner join cultural_events ce on ac.Cul_ID = ce.Cul_ID where ce.Cul_ID = id
                group by fullname
                order by COS_TotalScore desc;

END $$

DELIMITER ;