DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_MajorMinor_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_MajorMinor_Event`()
BEGIN

        Select Cul_ID,Cul_Name from Cultural_Events where Cul_Status = 'Active';

END $$

DELIMITER ;