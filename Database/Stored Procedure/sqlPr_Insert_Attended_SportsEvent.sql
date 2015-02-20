DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Insert_Attended_SportsEvent` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Attended_SportsEvent`(IN SPID Integer)
BEGIN

      Declare getparticipant Integer;
      set getparticipant = (Select Part_ID from Participants ORDER BY Part_ID DESC LIMIT 1);

      if Exists (Select 'True' from Attended_Sportsevents AP
                  INNER JOIN  Sports_Events SE ON SE.Sp_ID = AP.Sp_ID where AP.Part_ID = getparticipant and AP.Sp_ID = SPID)then
        Select ('This Event already assigned on this participant');
      else
        Select('Successfully Saved');
      Insert into Attended_SportsEvents(Sp_ID,Part_ID)
      values(SPID,getparticipant);
     end if;
END $$

DELIMITER ;