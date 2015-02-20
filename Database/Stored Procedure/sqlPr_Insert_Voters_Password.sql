DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Voters_Password` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Voters_Password`(IN VPass VarChar(50))
BEGIN


        INSERT INTO Voters_Password(VP_Pass,VP_Status)
        VALUES(VPass,'Active');




END $$

DELIMITER ;