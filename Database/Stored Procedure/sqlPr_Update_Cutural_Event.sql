DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Cutural_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Cutural_Event`(IN CID Integer,
                                                           IN AID Integer,
                                                           IN CName Varchar(50),
                                                           IN CPercentage Integer,
                                                           IN CStatus Varchar(25),
                                                           IN CDate VarChar(25),
                                                           IN CType VarChar(25),
                                                           IN CSched VarChar(50),
                                                           IN CJudgeType VarChar(25))
BEGIN

      Declare lastrecord Integer;
      set lastrecord = (SELECT OP_ID FROM Overall_Points where OP_Type = 'Cultural Event' and OP_Status = 'Active' ORDER BY OP_ID DESC LIMIT 1);


      Update Cultural_Events set Cul_ID =CID,Acc_ID = AID,OP_ID = lastrecord ,Cul_Name =CName,Cul_Percentage =CPercentage,Cul_Status =CStatus,
                Cul_Type = CType,Cul_Date = CDate, Acc_Id = AID,Cul_Sched =CSched ,Cul_Type_Judging = CJudgeType Where Cul_ID = CID;
      Select('Successfully Save');

END $$

DELIMITER ;