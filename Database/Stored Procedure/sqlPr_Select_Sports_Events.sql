DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Sports_Events` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Sports_Events`( IN Name Varchar(50),
                                                                          IN ID Varchar(50))
BEGIN

      Select SE.Sp_ID,SE.Sp_Percentage,SE.Sp_Name,SE.Sp_Status,SE.Sp_Type,SE.SP_Date ,
      CONCAT(A.Acc_LName,', ',A.Acc_FName,' ',A.Acc_MName) fullname,SE.Sp_Sched from Sports_Events SE
      INNER JOIN Accounts A ON A.Acc_ID = SE.ACC_ID Where SE.Sp_Name LIKE CONCAT(Name,'%') OR SE.Sp_ID LIKE CONCAT(ID,'%');

END $$

DELIMITER ;