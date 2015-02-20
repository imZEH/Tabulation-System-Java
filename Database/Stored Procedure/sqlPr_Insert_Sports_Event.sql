DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Sports_Event` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Sports_Event`(IN AID Integer,
                                                          IN SPPercentage Double,
                                                          IN SPName Varchar(50),
                                                          IN SPStatus Varchar(25),
                                                          IN SPType Varchar(25),
                                                          IN SPSched VarChar(50),
                                                          IN SPDate Date)
BEGIN

      declare lastrecord integer;
      Declare totalMajor Integer;
      Declare totalMinor Integer;
      Declare total1 Integer;
      Declare total2 Integer;

      set totalMajor = (Select SUM(Sp_Percentage) from Sports_Events Where Sp_Status = 'Active' and Sp_Type = SPType);
      set lastrecord = (SELECT OP_ID FROM Overall_Points where OP_Type = 'Sports Event' and OP_Status = 'Active' ORDER BY OP_ID DESC LIMIT 1);

      set total1 = (totalMajor+SPPercentage);

       if Exists(Select 'True' from Sports_Events Where Sp_Name  = SPName and Sp_Status = 'Active')then
         Select('Event Already Exist');
       else
           if(total1 >100)then
         Select ('This Event will contain total of More than 100% this is invalid');
       else
         Select ('Successfully Saved');
       INSERT INTO Sports_Events(Acc_ID,OP_ID,SP_Percentage,SP_Name,SP_Status,SP_Type,SP_Sched,SP_Date)
       VALUES(AID,lastrecord,SPPercentage,SPName,SPStatus,SPType,SPSched,SPDate);

       end if;

      end if;
END $$

DELIMITER ;