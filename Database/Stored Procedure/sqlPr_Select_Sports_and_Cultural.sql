DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Sports_and_Cultural` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Sports_and_Cultural`()
BEGIN

       Select Cul_Name,Cul_ID,'Cultural Event' from Cultural_Events where Cul_Status = 'Active'
      UNION
      Select SP_Name,Sp_ID,'Sport Event' From Sports_Events where Sp_Status = 'Active';

END $$

DELIMITER ;