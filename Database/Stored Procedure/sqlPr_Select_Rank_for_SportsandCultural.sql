DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Rank_for_SportsandCultural` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Rank_for_SportsandCultural`(IN ID VarChar(25),
                                                                        IN Name Varchar(50))
BEGIN

      Select Cul_ID,Cul_Name,'Cultural Event' from Cultural_Events where Cul_ID LIKE CONCAT(ID,'%')
      OR Cul_Name LIKE CONCAT(Name,'%')  and Cul_Status = 'Active'
      UNION
      Select Sp_ID,SP_Name,'Sport Event' From Sports_Events where SP_ID LIKE CONCAT(ID,'%')
      OR SP_Name LIKE CONCAT(Name,'%') and Sp_Status = 'Active';

END $$

DELIMITER ;