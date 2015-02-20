DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Update_Sport_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Update_Sport_Event`(IN SID Integer,
                                                         IN AID Integer,
                                                         IN SName Varchar(50),
                                                         IN SPercent Double,
                                                         IN Sstatus Varchar(25),
                                                         IN sType Varchar(25),
                                                         IN sSched VarChar(50),
                                                         IN sDate Date)
BEGIN

  Declare lastrecord Integer;
  set lastrecord = (SELECT OP_ID FROM Overall_Points
                   where OP_Type = 'Sports Event' and OP_Status = 'Active' ORDER BY OP_ID DESC LIMIT 1);

    Update Sports_Events set Sp_ID =SID,Acc_ID = AID,OP_ID = lastrecord,Sp_Percentage = SPercent,Sp_Name =SName ,
                Sp_Status = Sstatus,Sp_Type = sType,Sp_Sched =sSched ,Sp_Date = sDate
                where Sp_ID = SID;

END 