DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`Module_Sports` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Module_Sports`(in id integer)
BEGIN
  Delete from results;
  Delete from sport_generated_rankingpoints;


  SELECT se.Sp_ID,t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(ss.S_CompScore) as total, so.SOS_TotalScore from participants p
      inner join teams t on p.Team_ID=t.Team_ID
      inner join attended_sportsevents ase on p.Part_ID = ase.Part_ID
      inner join sports_score ss on ase.Ap_ID = ss.Ap_ID
      inner join sports_overall_score so on ase.Ap_ID = so.Ap_ID
      inner join sports_events se on ase.Sp_ID = se.Sp_ID where se.Sp_ID = id
    group by fullname
    order by S_CompScore desc;

END $$

DELIMITER ;