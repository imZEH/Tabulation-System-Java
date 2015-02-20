DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`sqlPr_Select_Participants` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Participants`(IN Name VarChar(30),
                                                          IN ID Varchar(25))
BEGIN

        Select P.PArt_ID,CONCAT(Part_LName,', ',Part_FName,' ',Part_MName)fullname,
        P.Part_Gender,P.Part_ContactNumber,P.Part_Address,p.Part_Status,T.Team_Name  from Participants P
        INNER JOIN  Teams T
        ON T.Team_ID = P.Team_ID
        where Part_status = 'Active' and Part_ID LIKE CONCAT(ID,'%')
        OR Part_FName LIKE CONCAT(Name,'%')
        OR Part_MName LIKE CONCAT(Name,'%')
        OR Part_LName LIKE CONCAT(Name,'%') ;

END $$

DELIMITER ;