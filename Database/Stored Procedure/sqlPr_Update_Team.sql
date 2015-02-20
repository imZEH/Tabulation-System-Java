DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Team` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Team`(IN TID INteger,
                                                  IN Tname Varchar(50),
                                                  IN TNum Integer,
                                                  IN Tlogo Blob,
                                                  IN TStatus Varchar(25),
                                                  IN Tpath Varchar(100))
BEGIN

        Update Teams set Team_ID =TID,Team_Name =Tname,Team_Number =TNum,
            Team_logo = Tlogo,Team_Path =Tpath,Team_Status =TStatus  where Team_ID = TID;

END $$

DELIMITER ;