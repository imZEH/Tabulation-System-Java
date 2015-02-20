DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Assigned_Event_for_Sports` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Assigned_Event_for_Sports`(IN PartID Integer,IN SPID Integer)
BEGIN



      if Exists (Select 'True' from Attended_Sportsevents AP
                  INNER JOIN  Sports_Events SE ON SE.Sp_ID = AP.Sp_ID where AP.Part_ID = PartID and AP.Sp_ID = SPID)then
        Select ('This Event already assigned on this participant');
      else
        Select('Successfully Saved');
      Insert into Attended_SportsEvents(Sp_ID,Part_ID)
      values(SPID,PartID);
     end if;

END $$

DELIMITER ;