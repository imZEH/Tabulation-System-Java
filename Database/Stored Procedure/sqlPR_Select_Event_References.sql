DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPR_Select_Event_References` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPR_Select_Event_References`()
BEGIN

    Select Cul_ID,Cul_Name from Cultural_Events where Cul_Status = 'Active'
    UNION
    Select Category_ID,Category_Name from Event_Category;



END $$

DELIMITER ;