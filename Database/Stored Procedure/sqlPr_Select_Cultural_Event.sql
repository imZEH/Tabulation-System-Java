DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Cultural_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Cultural_Event`(IN CID Varchar(50),
                                                            IN CName Varchar(50),
                                                            IN CStatus VarChar(50))
BEGIN

  Select CE.Cul_ID,CE.Cul_Name,CE.Cul_Percentage,CE.Cul_Status,CE.Cul_Date,
         CONCAT(AC.Acc_LName,',',AC.Acc_FName,' ',AC.Acc_MName) fullname,CE.Cul_Sched
         from Cultural_Events CE
         INNER JOIN Accounts AC ON CE.Acc_ID = AC.Acc_ID
         Where CE.Cul_Status = 'Active' and CE.Cul_ID LIKE CONCAT(CID,'%') OR CE.Cul_Name LIKE CONCAT(CName,'%');

END $$

DELIMITER ;