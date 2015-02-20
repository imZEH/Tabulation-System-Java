DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`slqPr_Insert_Cultural_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `slqPr_Insert_Cultural_Event`(IN AID Integer,
                                                            IN CName Varchar(50),
                                                            IN CPercentage Integer,
                                                            IN CStatus Varchar(25),
                                                            IN CDate VarChar(25),
                                                            IN CType VarChar(25),
                                                            IN CSched VarChar(50),
                                                            IN CJudgeType VarChar(25))
BEGIN
      declare lastrecord integer;
      declare totalMajor integer;
      declare totalMinor integer;
      declare totalvalue1 integer;
      declare totalvalue2 integer;

      set totalMajor = (Select SUM(Cul_Percentage) from Cultural_Events where Cul_Status = 'Active' and Cul_Type = 'Major Event');
      set totalMinor = (Select SUM(Cul_Percentage) from Cultural_Events where Cul_Status = 'Active' and Cul_Type = 'Minor Event');

      set totalvalue1 = (totalMajor+CPercentage);
      set totalvalue2 = (totalMinor+CPercentage);

      set lastrecord = (SELECT OP_ID FROM Overall_Points where OP_Type = 'Cultural Event' and OP_Status = 'Active' ORDER BY OP_ID DESC LIMIT 1);

      if Exists(Select 'True' from Cultural_Events where Cul_Name = CName and Cul_Status = 'Active')then

        Select ('Event Already Exist');

      else

          if((totalvalue1 >100) AND (totalvalue2 >100))then
            Select ('This Event will contain total of More than 100% this is invalid');
          else
            Select ('Successfully Saved');

            INSERT INTO Cultural_Events(Acc_ID,OP_ID,Cul_Name,Cul_Percentage,Cul_Status,Cul_Date,Cul_Type,Cul_Sched,Cul_Type_Judging)
            VALUES(AID,lastrecord,CName,CPercentage,CStatus,CDate,CType,CSched,CJudgeType);
        end if;
      end if;

END $$

DELIMITER ;