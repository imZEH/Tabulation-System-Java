DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Event_Category` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Event_Category`(IN ID Integer)
BEGIN

       Select EC.Category_ID,EC.Category_Name,EC.Category_Per from Event_Category EC
              INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where EC.Cul_ID = ID;

END $$

DELIMITER ;