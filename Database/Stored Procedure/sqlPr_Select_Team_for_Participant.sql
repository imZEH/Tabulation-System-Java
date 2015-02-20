DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Team_for_Participant` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Team_for_Participant`()
BEGIN

      Select Team_Name from Teams Where Team_Status = 'Active';

END $$

DELIMITER ;