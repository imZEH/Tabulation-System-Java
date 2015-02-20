DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Teams` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Teams`(IN TName Varchar(50),
                                                   IN TNumber Integer,
                                                   IN TLogo Blob,
                                                   IN TStatus Varchar(25),
                                                   IN TPath VarChar(100))
BEGIN

       INSERT INTO Teams(Team_Name,Team_Number,Team_Logo,Team_Status,Team_Path)
       VALUES(TName,TNumber,TLogo,TStatus,TPath);

END $$

DELIMITER ;