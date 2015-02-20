DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_History` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_History`()
BEGIN

      Select Acc_ID,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName),Acc_Status from Accounts Where Acc_Status = 'MARK AS DELETION' OR Acc_Status = 'InActive'
      UNION ALL
      Select Cul_ID,Cul_Name,Cul_Status from Cultural_Events Where Cul_Status = 'MARK AS DELETION' Or Cul_Status = 'InActive'
      UNION ALL
      Select OP_ID,OP_Type,OP_Status from Overall_Points Where OP_Status = 'MARK AS DELETION' OR OP_Status = 'InActive'
      UNION ALL
      Select Part_ID,CONCAT(Part_LName,', ',Part_FName,' ',Part_MName),Part_Status from Participants Where Part_Status = 'MARK AS DELETION' OR Part_Status = 'InActive'
      UNION ALL
      Select Sp_ID,Sp_Name,Sp_Status From Sports_Events Where Sp_Status = 'MARK AS DELETION' OR Sp_Status = 'InActive'
      UNION ALL
      Select Team_ID,Team_Name,Team_Status from Teams Where Team_Status = 'MARK AS DELETION' OR Team_Status = 'InActive';

END $$

DELIMITER ;