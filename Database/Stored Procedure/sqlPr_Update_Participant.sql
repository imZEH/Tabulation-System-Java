DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Participant` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Participant`(IN PID Integer,
                                                         IN TID Integer,
                                                         IN PFname Varchar(50),
                                                         IN PMname VarChar(50),
                                                         IN PLname Varchar(50),
                                                         IN Padd VarChar(50),
                                                         IN PGender Varchar(25),
                                                         IN PContact Varchar(25),
                                                         IN PStatus Varchar(25),
                                                         IN PPic Blob,
                                                         IN PPath Varchar(100))
BEGIN

        Update Participants set Part_ID = PID,Team_ID = TID,Part_FName = PFname,Part_MName =PMname,
                    Part_LName =PLname ,Part_Address = Padd,Part_Gender = PGender,Part_ContactNumber =PContact
               ,Part_Status = PStatus,Part_Pic =PPic,Part_Path = PPath where Part_ID =PID;
END $$

DELIMITER ;