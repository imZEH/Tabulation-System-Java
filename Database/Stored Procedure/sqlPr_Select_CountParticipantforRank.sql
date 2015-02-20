DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_CountParticipantforRank` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_CountParticipantforRank`()
BEGIN

      Select Count(Part_ID) from Participants where Part_Status = 'Active';

END $$

DELIMITER ;