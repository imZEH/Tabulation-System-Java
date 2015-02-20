DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Assigned_Event_for_Cultural` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Assigned_Event_for_Cultural`(IN PartID Integer,IN CName VarChar(50))
BEGIN

       Declare getSequenceNumber Integer;
       Declare getCulturalID Integer;
       set getCulturalID = (Select Cul_ID from Cultural_Events where Cul_Name = CName and Cul_Status = 'Active');
       set getSequenceNumber = (Select count(ACE.ACE_Secnum)+1 from Attended_CulturalEvents ACE where ACE.Cul_ID = getCulturalID);


       if Exists (Select 'True' from Attended_CulturalEvents AC
                   INNER JOIN  Cultural_Events CE ON CE.Cul_ID = AC.Cul_ID where AC.Part_ID = PartID and AC.Cul_ID = getCulturalID)then
       Select ('This Event already assigned on this participant');
        else
          Select ('Successfully Saved');
       Insert into Attended_CulturalEvents(Part_ID,Cul_ID,ACE_Secnum)
       values(PartID,getCulturalID,getSequenceNumber);
     end if;

END $$

DELIMITER ;