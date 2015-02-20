DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Accounts` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Accounts`(IN ID Varchar(50),
                                                      IN Fname Varchar(50),
                                                      IN Mname VarChar(50),
                                                      IN Lname Varchar(50),
                                                      IN AStatus VarChar(25))
BEGIN

        Select *,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname from Accounts where Acc_ID LIKE CONCAT(ID,'%')
                OR Acc_FName LIKE CONCAT(Fname,'%')
                OR Acc_MName LIKE CONCAT(Mname,'%')
                OR Acc_LName LIKE CONCAT(Lname,'%')
                OR Acc_Status LIKE CONCAT(AStatus,'%') ;

END $$

DELIMITER ;