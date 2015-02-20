DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Accounts` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Accounts`(IN ACID Integer,
                                                      IN ACFname Varchar(50),
                                                      IN ACMname VarChar(50),
                                                      IN ACLname Varchar(50),
                                                      IN ACGender VarChar(25),
                                                      IN ACStatus Varchar(25),
                                                      IN ACType Varchar(25),
                                                      IN ACUser VarChar(50),
                                                      IN ACPass VarChar(50),
                                                      IN ACImage Blob,
                                                      IN ACAddress VarChar(50),
                                                      IN ACNumber Varchar(25),
                                                      IN ACPath Varchar(100))
BEGIN

Update Accounts set Acc_ID =ACID,Acc_FName=ACFname,Acc_MName=ACMname,Acc_LName=ACLname,
         Acc_Gender = ACGender,Acc_Username=ACUser,Acc_Password=ACPass,Acc_Status=ACStatus,Acc_Type=ACType,
         Acc_Image = ACImage,Acc_Address=ACAddress,Acc_ContactNumber=ACNumber,Acc_Path=ACPath where Acc_ID = ACID;

END $$

DELIMITER ;