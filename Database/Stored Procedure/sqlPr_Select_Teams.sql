DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Teams` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Teams`(IN ID Varchar(25),
                                                                 IN TName Varchar(50))
BEGIN

      Select Team_ID,Team_Name,Team_Number,Team_Status from Teams
      where Team_ID LIKE CONCAT(ID,'%') OR Team_Name LIKE CONCAT(TName,'%');

END $$

DELIMITER ;