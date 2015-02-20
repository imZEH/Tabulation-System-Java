DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Participant_and_Assigned` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Participant_and_Assigned`(IN PID Integer,IN ASID Integer)
BEGIN

    Select P.Part_ID,P.Part_FName,P.Part_MName,P.Part_LName,P.Part_Gender,
        P.Part_Address,P.Part_ContactNumber,P.Part_Path,P.Part_Pic,P.Part_Status, T.Team_Name from Participants P
        INNER JOIN Teams T ON
        P.Team_ID = T.Team_ID
        INNER JOIN Attended_CulturalEvents AC
        ON AC.Sp_ID = P.SP_ID  where P.Part_ID =PID and AC.Cul_ID = ASID;


END $$

DELIMITER ;