DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Criteria_Cultural` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Criteria_Cultural`(IN CID Integer,
                                                               IN Cat_ID Integer,
                                                               IN CDesc VarChar(50),
                                                               IN CPercent Double)
BEGIN

      Declare totalCultural Integer;
      Declare totalSub Integer;
      Declare total1 Integer;
      Declare total2 Integer;


      set totalCultural = (Select SUM(Cr_Percentage) from Criteria_Cultural CC INNER JOIN Cultural_Events CE
                           ON CE.Cul_ID = CC.Cul_ID Where CE.Cul_ID = CID );


      set total1 = (totalCultural+CPercent);


      if Exists (Select 'True' from Criteria_Cultural CC INNER JOIN
                Cultural_Events CE ON CE.Cul_ID = CC.Cul_ID Where CC.Cr_Description = CDesc and CE.Cul_ID = CID) then
          Select ('Criteria already exist');

          else  if(total1 > 100)then
          Select ('This Criteria will contain more than 100% this is invalid');

      else
          Select ('Successfully Saved');

         Insert into Criteria_Cultural(Cul_ID,Category_ID,Cr_Description,Cr_Percentage)
         Values(CID,Cat_ID,CDesc,CPercent);


      end if;

  end if;

END $$

DELIMITER ;