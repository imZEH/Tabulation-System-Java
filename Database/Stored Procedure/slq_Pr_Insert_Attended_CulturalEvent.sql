DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`slq_Pr_Insert_Attended_CulturalEvent` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `slq_Pr_Insert_Attended_CulturalEvent`(IN CName VarChar(50))
BEGIN

       Declare getparticipant Integer;
       Declare getCulturalID Integer;
       Declare getSequenceNumber Integer;
       set getparticipant = (Select Part_ID from Participants ORDER BY Part_ID DESC LIMIT 1);
       set getCulturalID = (Select Cul_ID from Cultural_Events where Cul_Name = CName and Cul_Status = 'Active');
       set getSequenceNumber = (Select count(ACE.ACE_Secnum)+1 from Attended_CulturalEvents ACE where ACE.Cul_ID = getCulturalID);


        if Exists (Select 'True' from Attended_CulturalEvents AC
                   INNER JOIN  Cultural_Events CE ON CE.Cul_ID = AC.Cul_ID where AC.Part_ID = getparticipant and AC.Cul_ID = getCulturalID)then
          Select ('This Event already assigned on this participant');
        else
          Select('Successfully Saved');
       Insert into Attended_CulturalEvents(Part_ID,Cul_ID,ACE_Secnum)
       values(getparticipant,getCulturalID,getSequenceNumber);
     end if;

END $$

DELIMITER ;