DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Assigned_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Assigned_Event`(IN ID VarChar(25))
BEGIN

      (Select ATT.Cul_ID,CE.Cul_Name,'Cultural_Event' from Attended_CulturalEvents ATT
      INNER JOIN Cultural_Events CE ON CE.Cul_ID = ATT.Cul_ID Where ATT.Part_ID = ID)
      UNION
      (Select ATT.Sp_ID,SE.Sp_Name,'Sport_Event' from Attended_SportsEvents ATT
      INNER JOIN Sports_Events SE ON SE.Sp_ID = ATT.Sp_ID Where ATT.Part_ID = ID) ;

END $$

DELIMITER ;