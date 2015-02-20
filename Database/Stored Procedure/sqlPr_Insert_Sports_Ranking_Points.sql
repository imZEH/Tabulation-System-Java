DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Sports_Ranking_Points` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Sports_Ranking_Points`(IN SPID Integer,
                                                                   IN SRPPoints Double,
                                                                   IN SRPRank Varchar(25))
BEGIN

       Declare  total1 Integer;
       set total1 = (Select Count(SRP_Points)+SRPPoints from Sports_Ranking_Points);


       if Exists(Select 'True' from Sports_Ranking_Points Where SRP_Rank = SRPRank and SP_ID = SPID)then
         Select ('This Rank already Exist');
       else if(total1 >100)then
         Select ('This Event will contain more than 100% this is invalid');
       else
          Select('Successfully Saved');
       INSERT INTO Sports_Ranking_Points(SP_ID,SRP_Points,SRP_Rank)
       VALUES(SPID,SRPPoints,SRPRank);

       end if;
        end if;
END $$

DELIMITER ;