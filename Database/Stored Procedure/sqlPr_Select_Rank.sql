DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Rank` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Rank`(IN ID VarChar(25))
BEGIN


      (Select  CRP.RP_Point,CRP.RP_Rank from Cultural_Ranking_Points CRP
      INNER JOIN
      Cultural_Events CE
      ON CE.Cul_ID = CRP.Cul_ID Where CE.Cul_ID =  ID)
      UNION
      (Select SRP.SRP_Points,SRP.SRP_Rank from Sports_Events SE
      INNER JOIN
      Sports_Ranking_Points SRP
      ON SRP.SP_ID = SE.Sp_ID Where SE.SP_ID = ID);

END $$

DELIMITER ;