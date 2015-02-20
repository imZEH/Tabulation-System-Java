DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Participant` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Participant`(IN TID Integer,
                                                         IN pFname VarChar(30),
                                                         IN PMname VarChar(30),
                                                         IN pLname VarChar(30),
                                                         IN pAddress Varchar(50),
                                                         IN pGender Varchar(25),
                                                         IN pContact VarChar(25),
                                                         IN pStatus Varchar(25),
                                                         IN pPicture Blob,
                                                         IN PPath VarChar(100))
BEGIN


      if Exists (Select 'True' from Participants where Part_Fname = pFname and Part_MName = PMname and Part_LName = pLname  and Part_Status = 'Active')then
        Select('Participant Already Exist');
      else
        Select('Successfully Saved');
      INSERT INTO Participants(Team_ID,Part_FName,Part_MName,Part_LName,Part_Address,Part_Gender,Part_ContactNumber,Part_Status,Part_Pic,Part_Path)
      Values (TID,pFname,PMname,pLname,pAddress,pGender,pContact,pStatus,pPicture,PPath);

      end if;

END $$

DELIMITER ;