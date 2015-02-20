DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_getPass` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_getPass`(IN Pass Varchar(50),IN Fulname VarChar(50))
BEGIN

      Declare getPart_ID Integer;
      Declare getPass Varchar(50);
      Declare getCriteria_ID Integer;
      Declare getCul_ID Integer;
      Declare getACE_ID Integer;

      set getPart_ID = (Select Part_ID from Participants Where CONCAT(Part_LName,', ',Part_FName,' ',Part_MName) = Fulname group by Part_ID);
      set getCul_ID = (Select CE.Cul_ID from Cultural_Events CE INNER JOIN Event_Category EC
	                      ON CE.Cul_ID = EC.Cul_ID INNER JOIN Criteria_Cultural CC
	                      ON CC.Category_ID = EC.Category_ID Where EC.Category_JudgeType = 'ONLINE' and CE.Cul_Status = 'Active'
                        group by Cul_ID);

      set getACE_ID = (Select ACE.ACE_ID from Attended_CulturalEvents ACE
	                INNER JOIN Participants P ON P.Part_ID = ACE.Part_ID
	                INNER JOIN Cultural_Events CE ON CE.Cul_ID = ACE.Cul_ID
	                Where P.Part_ID = getPart_ID and CE.Cul_ID = getCul_ID group by ACE_ID
                  );


       set getCriteria_ID = (Select C.Cr_ID from Event_Category EC
          INNER JOIN Criteria_Cultural C ON C.Category_ID = EC.Category_ID
          INNER JOIN Cultural_Events CE ON CE.Cul_ID = EC.Cul_ID
          Where EC.Category_JudgeType = 'ONLINE' and CE.Cul_Status = 'Active' group by Cr_ID);


      set getPass = (Select VP_ID from Voters_Password Where VP_Pass = Pass group by VP_ID);

      if exists(Select 'True' from Voters_Password where VP_Pass = Pass and VP_Status = 'Active')then
        Select('Successfully Voted') prompt;
      Insert into criteria_score(Cr_ID,ACE_ID,VP_ID,CrScore,Cr_Computed)
      Values(getCriteria_ID,8000,getPass,1,1);
      Update Voters_Password set  Vp_Status = 'InActive' Where VP_Pass = Pass;

      else
          Select ('Invalid Password') prompt;

  end if;

END $$

DELIMITER ;