DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Sports_For_Update` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Sports_For_Update`(IN ID Integer)
BEGIN

       Select Sp_ID,Sp_Percentage,Sp_Name,Sp_Status,Sp_Type ,
       Sp_Sched from Sports_Events Where  Sp_ID = ID;

END $$

DELIMITER ;