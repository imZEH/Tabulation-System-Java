DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Criteria_Category` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Criteria_Category`(IN CID Integer,
                                                               IN Cat_ID Integer,
                                                               IN CDesc VarChar(50),
                                                               IN CPercent Double)
BEGIN


     Declare totalSub Integer;
     Declare total2 Integer;

     set totalSub = (Select SUM(Cr_Percentage) from Criteria_Cultural CC INNER JOIN Event_Category EC
                      ON EC.Category_ID = CC.Category_ID Where CC.Category_ID = Cat_ID);


     set total2 = (totalSub+CPercent);

     if Exists (Select 'True' from Criteria_Cultural CC INNER JOIN
                Event_Category EC ON EC.Category_ID = CC.Category_ID Where CC.Cr_Description = CDesc and EC.Category_ID = Cat_ID) then
          Select ('Criteria already exist');

     else  if(total2 > 100)then
          Select ('This Criteria will contain more than 100% this is invalid');

      else
          Select ('Successfully Saved');

         Insert into Criteria_Cultural(Cul_ID,Category_ID,Cr_Description,Cr_Percentage)
         Values(CID,Cat_ID,CDesc,CPercent);

         end if;
      end if;

END $$

DELIMITER ;