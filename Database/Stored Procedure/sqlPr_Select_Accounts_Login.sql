DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Accounts_Login` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Accounts_Login`(IN AUsername VarChar(50),
                                                            IN APassword VarChar(50))
BEGIN

       Select CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname,Acc_Status,Acc_Type,Acc_ID
       from Accounts
       where Acc_UserName = AUsername and Acc_Password = APassword;

END $$

DELIMITER ;