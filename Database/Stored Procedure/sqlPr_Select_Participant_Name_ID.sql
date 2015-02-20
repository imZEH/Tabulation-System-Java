DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Participant_Name_ID` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Participant_Name_ID`()
BEGIN

      Select Part_ID,CONCAT(Part_LName,', ',Part_FName,' ',Part_MName)fullname from Participants where Part_Status = 'Active';


END $$

DELIMITER ;