DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Accounts` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Accounts`(IN AFname Varchar(30),
                                                      IN AMname Varchar(30),
                                                      IN ALname Varchar(30),
                                                      IN AGender Varchar(25),
                                                      IN AStatus VarChar(25),
                                                      IN AType Varchar(25),
                                                      IN AUsername VarChar(50),
                                                      IN APassword VarChar(500),
                                                      IN AImage Blob,
                                                      IN AAddress Varchar(30),
                                                      IN AContactNumber Varchar(25),
                                                      IN APath Varchar(500))
BEGIN

     if Exists(Select 'True' from Accounts Where Acc_Username = AUsername)then

         Select 'Username Already Exist';

     else

       Select ('Successfully Saved');

     INSERT INTO Accounts(Acc_FName,Acc_MName,Acc_LName,Acc_Gender,Acc_Status,Acc_Type,Acc_Username,Acc_Password,Acc_Image,Acc_Address,Acc_ContactNumber,Acc_Path)
     VALUES(AFname,AMname,ALname,AGender,AStatus,AType,AUsername,APassword,AImage,AAddress,AContactNumber,APath);

     end if;
END $$

DELIMITER ;