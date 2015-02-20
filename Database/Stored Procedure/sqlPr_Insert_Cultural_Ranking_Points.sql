DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Cultural_Ranking_Points` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Cultural_Ranking_Points`(IN CID Integer,
                                                                     IN RPPoint Double,
                                                                     IN RPRank VarChar(25))
BEGIN
       Declare  total1 Integer;
       set total1 = (Select Count(RP_Point)+RPPoint from Cultural_Ranking_Points);



       if Exists(Select 'True' from Cultural_Ranking_Points where RP_Rank = RPRank and Cul_ID = CID)then
         Select('This Rank already Exist');
        else if(total1 >100)then
         Select ('This Event will contain more than 100% this is invalid');
        else
        Select('Successfully Saved');
       INSERT INTO Cultural_Ranking_Points(Cul_ID,RP_Point,RP_Rank)
       VALUES(CID,RPPoint,RPRank);

       end if;
       end if;
END $$

DELIMITER ;