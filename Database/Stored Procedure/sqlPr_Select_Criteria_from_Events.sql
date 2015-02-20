DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Criteria_from_Events` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Criteria_from_Events`(IN ID Varchar(25))
BEGIN


     (Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC
     INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID
     where CE.Cul_ID = ID)
     UNION
     (Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC
     INNER JOIN Event_Category EC ON EC.Category_ID=CC.Category_ID where EC.Category_ID = ID);

END $$

DELIMITER ;