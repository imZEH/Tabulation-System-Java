DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`Sports` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sports`(in name Varchar(60))
BEGIN
      SELECT t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(cr.S_CompScore) as total, co.SOS_TotalScore from participants p
    inner join teams t on p.Team_ID=t.Team_ID
    inner join attended_sportsevents ac on p.Part_ID = ac.Part_ID
    inner join sports_score cr on ac.Ap_ID = cr.Ap_ID
    inner join sports_overall_score co on ac.Ap_ID = co.Ap_ID
    inner join sports_events ce on ac.Sp_ID = ce.Sp_ID where ce.Sp_Name = name
    group by fullname;
END $$

DELIMITER ;