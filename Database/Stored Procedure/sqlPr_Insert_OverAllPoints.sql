DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_OverAllPoints` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_OverAllPoints`(IN Percentage VarChar(25),IN EventStatus VarChar(25),IN EventType VarChar(25))
BEGIN

  Declare value1 Integer;
  Declare value2 Integer;
  set value1 = (Select SUM(OP_Percentage) from Overall_Points where OP_Status = 'Active');
  set value2 = value1+Percentage;
  if Exists(Select 'True' from overall_points where OP_Type = EventType and OP_Status = 'Active') then
    Select ('Data already exist otherwise Delete old data');

  else  if(value2>100)then
       Select ('The Overall Points will contain total of More than 100% this is invalid');
  else
    Select ('Successfully Saved');
      Insert into overall_points(OP_Percentage,OP_Status,OP_Type) Values(Percentage,EventStatus,EventType);

  end if;
end if;
END $$

DELIMITER ;