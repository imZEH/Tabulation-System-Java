DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Overall_Points` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Overall_Points`(IN ID VarChar(25),
                                                            IN Percentage VarChar(25),
                                                            IN EventStatus VarChar(25),
                                                            IN OPType VarChar(25))
BEGIN
        /*Declare value1 Integer;
        Declare Value2 Integer;

        set value1 = (Select SUM(OP_Percentage) from Overall_Points where OP_Status = 'Active');
        set Value2 = value1+Percentage;
        if Exists(Select 'True' from overall_points where OP_Type = OPType and OP_Status = 'Active') then
          Select ('Data already exist otherwise Deactivate old data');
        else if(Value2>100)then
            Select ('The Overall Points will contain total of More than 100% this is invalid');
        else   */

       UPDATE Overall_Points set OP_Percentage = Percentage,OP_Status = EventStatus ,OP_Type = OPType Where OP_ID = ID;
       Select('Successfully Saved');
       /*end if;
       end if;*/
END $$

DELIMITER ;