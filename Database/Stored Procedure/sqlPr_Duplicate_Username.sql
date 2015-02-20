DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Duplicate_Username` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Duplicate_Username`(IN AUsername VarChar(50))
BEGIN

        if Exists(Select 'True' from Accounts Where Acc_Username = AUsername)then
          Select('Username Already Exist');

        else
          Select('');
        end if;

END $$

DELIMITER ;