DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Overll_Points` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Overll_Points`(IN OPID Varchar(25),
                                                           IN OPType Varchar(25),
                                                           IN OPStatus Varchar(25))
BEGIN

      Select * from Overall_Points
      where OP_ID LIKE CONCAT(OPID,'%')
      or
      OP_Type LIKE CONCAT(OPType,'%')
      or
      OP_Status LIKE CONCAT(OPStatus,'%');

END $$

DELIMITER ;