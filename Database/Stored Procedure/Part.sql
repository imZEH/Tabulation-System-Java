DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`Part` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Part`(in id integer)
BEGIN
       Select participants.Part_ID,CONCAT(participants.Part_FName,' ',participants.Part_MName,' ',participants.Part_LName)fullname,participants.Part_Pic,participants.Part_Path,teams.Team_Name from participants
inner join teams on participants.Team_ID = teams.Team_ID
inner join attended_culturalevents ac  on participants.Part_ID = ac.Part_ID where ac.Cul_ID= id;
END $$

DELIMITER ;