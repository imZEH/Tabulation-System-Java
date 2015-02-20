DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Event_Category` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Event_Category`(IN CID Integer,
                                                            IN CName VarChar(50),
                                                            IN CPercent Integer)
BEGIN

    declare totalvalue Integer;
    declare total Integer;
    set totalvalue = (Select Sum(Category_Per) from Event_Category EC INNER JOIN Cultural_Events CE
                      ON EC.Cul_ID = CE.Cul_ID where CE.Cul_ID = CID);

    set total = (totalvalue+CPercent);

    if Exists (Select  'True' from Event_Category EC INNER JOIN Cultural_Events CE
                ON CE.Cul_ID = EC.Cul_ID where EC.Category_Name = CName and CE.Cul_ID = CID)then
      Select ('Sub Event already exist');
    else
        if(total >'100') then
      Select ('This Sub Event will contain total of more than 100% this is invalid');
    else
      Select ('Successfully Saved');
      Insert into Event_Category(Cul_ID,Category_Name,Category_Per)
      Values(CID,CName,CPercent);

      end if;
    end if;

END $$

DELIMITER ;