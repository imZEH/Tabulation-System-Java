DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_all_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_all_Event`()
BEGIN

      Select Cul_Name from Cultural_Events where Cul_Status = 'Active'
      UNION
      Select SP_Name From Sports_Events where Sp_Status = 'Active';

END $$

DELIMITER ;