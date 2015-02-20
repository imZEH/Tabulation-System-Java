-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 04, 2013 at 10:59 PM
-- Server version: 5.5.24
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `automated`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `slqPr_Insert_Cultural_Event`(IN CID Integer,
                                                            IN AID Integer,
                                                            IN OPID Integer,
                                                            IN CName Varchar(50),
                                                            IN CPercentage Double,
                                                            IN CStatus Varchar(25),
                                                            IN CDate Date,
                                                            IN CType VarChar(25),
                                                            IN CSched VarChar(25),
                                                            IN CJudgeType VarChar(25))
BEGIN

      INSERT INTO Cultural_Events(Cul_ID,Acc_ID,OP_ID,Cul_Name,Cul_Percentage,Cul_Status,Cul_Date,Cul_Type,Cul_Sched,Cul_Type_Judging)
      VALUES(CID,AID,OPID,CName,CPercentage,CStatus,CDate,CType,CSched,CJudgeType);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Accounts`(IN AID Integer,
                                                      IN AFname Varchar(30),
                                                      IN AMname Varchar(30),
                                                      IN ALname Varchar(30),
                                                      IN AGender Varchar(25),
                                                      IN AStatus VarChar(25),
                                                      IN AType Varchar(25),
                                                      IN AUsername VarChar(50),
                                                      IN APassword VarChar(50),
                                                      IN AImage Blob,
                                                      IN AAddress Varchar(30),
                                                      IN AContactNumber Varchar(25),
                                                      IN APath Varchar(50))
BEGIN

     INSERT INTO Accounts(Acc_ID,Acc_FName,Acc_MName,Acc_LName,Acc_Gender,Acc_Status,Acc_Type,Acc_Username,Acc_Password,Acc_Image,Acc_Address,Acc_ContactNumber,Acc_Path)
     VALUES(AID,AFname,AMname,ALname,AGender,AStatus,AType,AUsername,APassword,AImage,AAddress,AContactNumber,APath);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Cultural_Ranking_Points`(IN CID Integer,
                                                                     IN RPPoint Double,
                                                                     IN RPRank VarChar(25))
BEGIN

       INSERT INTO Cultural_Ranking_Points(Cul_ID,RP_Point,RP_Rank)
       VALUES(CID,RPPoint,RPRank);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_OverAllPoints`(IN EventType VarChar(25),  IN Percentage VarChar(25),IN EventStatus VarChar(25))
BEGIN

  Insert into OverlAll_Points(OP_Percentage,OP_Status,OP_Type) Values(Percentage,EventStatus,EventType);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Participant`(IN pID Integer,
                                                         IN TID Integer,
                                                         IN pFname VarChar(30),
                                                         IN PMname VarChar(30),
                                                         IN pLname VarChar(30),
                                                         IN pAddress Varchar(50),
                                                         IN pGender Varchar(25),
                                                         IN pContact VarChar(25),
                                                         IN pStatus Varchar(25),
                                                         IN pPicture Blob)
BEGIN

      INSERT INTO Participants(Part_ID,Team_ID,Part_FName,Part_MName,Part_LName,Part_Address,Part_Gender,Part_ContactNumber,Part_Status,Part_Pic)
      Values (pID,TID,pFname,PMname,pLname,pAddress,pGender,pContact,pStatus,pPicture);


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Sports_Event`(IN SPID Integer,
                                                          IN AID Integer,
                                                          IN OPID Integer,
                                                          IN SPPercentage Double,
                                                          IN SPName Varchar(50),
                                                          IN SPStatus Varchar(25),
                                                          IN SPType Varchar(25),
                                                          IN SPSched VarChar(25),
                                                          IN SPDate Date)
BEGIN

       INSERT INTO Sports_Events(SP_ID,Acc_ID,OP_ID,SP_Percentage,SP_Name,SP_Status,SP_Type,SP_Sched,SP_Date)
       VALUES(SPID,AID,OPID,SPPercentage,SPName,SPStatus,SPType,SPSched,SPDate);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Sports_Ranking_Points`(IN SPID Integer,
                                                                   IN SRPPoints Double,
                                                                   IN SRPRank Varchar(25))
BEGIN

       INSERT INTO Sports_Ranking_Points(SP_ID,SRP_Points,SRP_Rank)
       VALUES(SPID,SRPPoints,SRPRank);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Teams`(IN TID Integer,
                                                   IN TName Varchar(50),
                                                   IN TNumber Integer,
                                                   IN TLogo Blob,
                                                   IN TStatus Varchar(25))
BEGIN

       INSERT INTO Teams(Team_ID,Team_Name,Team_Number,Team_Logo,Team_Status)
       VALUES(TID,TName,TNumber,TLogo,TStatus);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Insert_Voters_Password`(IN VPass VarChar(50),
                                                             IN VStatus Varchar(25))
BEGIN

        INSERT INTO Voters_Password(VP_Pass,VP_Status)
        VALUES(VPass,VStatus);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Accounts`(IN ID Varchar(50),
                                                      IN Fname Varchar(50),
                                                      IN Mname VarChar(50),
                                                      IN Lname Varchar(50),
                                                      IN AStatus VarChar(25))
BEGIN

        Select *,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname from Accounts where Acc_ID LIKE CONCAT(ID,'%')
                OR Acc_FName LIKE CONCAT(Fname,'%')
                OR Acc_MName LIKE CONCAT(Mname,'%')
                OR Acc_LName LIKE CONCAT(Lname,'%')
                OR Acc_Status LIKE CONCAT(AStatus,'%') ;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Criteria_Cultural`(IN ID Integer)
BEGIN

    Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC
           INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID where CE.Cul_ID = ID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Cultural_Event`(IN CID Varchar(50),
                                                            IN CName Varchar(50),
                                                            IN CStatus VarChar(50))
BEGIN

  Select CE.Cul_ID,CE.Cul_Name,CE.Cul_Percentage,CE.Cul_Status,CE.Cul_Date,
         CONCAT(AC.Acc_LName,',',AC.Acc_FName,' ',AC.Acc_MName) fullname,CE.Cul_Sched
         from Cultural_Events CE
         INNER JOIN Accounts AC ON CE.Acc_ID = AC.Acc_ID
         Where CE.Cul_ID LIKE CONCAT(CID,'%') OR CE.Cul_Name LIKE CONCAT(CName,'%') OR CE.Cul_Status LIKE CONCAT(CStatus,'%');

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Event_Category`(IN ID Integer)
BEGIN

       Select EC.Category_ID,EC.Category_Name,EC.Category_Per from Event_Category EC
              INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where EC.Cul_ID = ID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Participants`(IN Name VarChar(30),
                                                          IN ID Varchar(25))
BEGIN

        Select *,CONCAT(Part_LName,', ',Part_FName,' ',Part_MName) fullname from Participants
        where Part_ID LIKE CONCAT(ID,'%')
        OR Part_FName LIKE CONCAT(Name,'%')
        OR Part_MName LIKE CONCAT(Name,'%')
        OR Part_LName LIKE CONCAT(Name,'%') ;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sqlPr_Select_Teams`(IN ID Varchar(25),
                                                   IN TName Varchar(50))
BEGIN

      Select * from Teams where Team_ID LIKE CONCAT(ID,'%') OR Team_Name LIKE CONCAT(TName,'%');

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `Acc_ID` int(11) NOT NULL,
  `Acc_FName` varchar(25) NOT NULL,
  `Acc_MName` varchar(25) NOT NULL,
  `Acc_LName` varchar(25) NOT NULL,
  `Acc_Gender` varchar(25) NOT NULL,
  `Acc_Status` varchar(25) NOT NULL,
  `Acc_Type` varchar(25) NOT NULL,
  `Acc_Username` varchar(25) NOT NULL,
  `Acc_Password` varchar(25) NOT NULL,
  `Acc_Image` blob,
  `Acc_Address` varchar(45) NOT NULL,
  `Acc_ContactNumber` varchar(45) NOT NULL,
  `Acc_Path` varchar(100) NOT NULL,
  PRIMARY KEY (`Acc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`Acc_ID`, `Acc_FName`, `Acc_MName`, `Acc_LName`, `Acc_Gender`, `Acc_Status`, `Acc_Type`, `Acc_Username`, `Acc_Password`, `Acc_Image`, `Acc_Address`, `Acc_ContactNumber`, `Acc_Path`) VALUES
(1, 'wqe', 'we', 'we', 'wew', 'qwe', 'qweq', 'qwe', 'qwewq', NULL, 'qwe', '123', 'wqewqeqw');

-- --------------------------------------------------------

--
-- Table structure for table `attended_culturalevents`
--

CREATE TABLE IF NOT EXISTS `attended_culturalevents` (
  `ACE_ID` int(11) NOT NULL,
  `Part_ID` int(11) NOT NULL,
  `Cul_ID` int(11) NOT NULL,
  `ACE_Secnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`ACE_ID`),
  KEY `Cul_ID` (`Cul_ID`),
  KEY `Part_ID` (`Part_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attended_sportsevents`
--

CREATE TABLE IF NOT EXISTS `attended_sportsevents` (
  `Ap_ID` int(11) NOT NULL,
  `Sp_ID` int(11) NOT NULL,
  `Part_ID` int(11) NOT NULL,
  PRIMARY KEY (`Ap_ID`),
  KEY `Sp_ID` (`Sp_ID`),
  KEY `Part_ID` (`Part_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `criteria_cultural`
--

CREATE TABLE IF NOT EXISTS `criteria_cultural` (
  `Cr_ID` int(11) NOT NULL,
  `Cul_ID` int(11) NOT NULL,
  `Category_ID` int(11) NOT NULL,
  `Cr_Description` varchar(25) NOT NULL,
  `Cr_Percentage` varchar(25) NOT NULL,
  PRIMARY KEY (`Cr_ID`),
  KEY `Category_ID` (`Category_ID`),
  KEY `Cul_ID` (`Cul_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `criteria_score`
--

CREATE TABLE IF NOT EXISTS `criteria_score` (
  `CrScore_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cr_ID` int(11) NOT NULL,
  `ACE_ID` int(11) NOT NULL,
  `VP_ID` int(11) NOT NULL,
  `CrScore` double NOT NULL,
  `Cr_Computed` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`CrScore_ID`),
  KEY `Cr_ID` (`Cr_ID`),
  KEY `ACE_ID` (`ACE_ID`),
  KEY `VP_ID` (`VP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cultural_events`
--

CREATE TABLE IF NOT EXISTS `cultural_events` (
  `Cul_ID` int(11) NOT NULL,
  `Acc_ID` int(11) NOT NULL,
  `OP_ID` int(11) NOT NULL,
  `Cul_Name` varchar(25) NOT NULL,
  `Cul_Percentage` varchar(25) NOT NULL,
  `Cul_Status` varchar(25) NOT NULL,
  `Cul_Date` varchar(25) NOT NULL,
  `Cul_Type` varchar(25) NOT NULL,
  `Cul_Sched` date NOT NULL,
  `Cul_Type_Judging` varchar(25) NOT NULL,
  PRIMARY KEY (`Cul_ID`),
  KEY `Acc_ID` (`Acc_ID`),
  KEY `OP_ID` (`OP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cultural_events`
--

INSERT INTO `cultural_events` (`Cul_ID`, `Acc_ID`, `OP_ID`, `Cul_Name`, `Cul_Percentage`, `Cul_Status`, `Cul_Date`, `Cul_Type`, `Cul_Sched`, `Cul_Type_Judging`) VALUES
(1, 1, 1, 'dq', '23', 'dsad', 'asda', 'adas', '2010-01-01', 'asdsad');

-- --------------------------------------------------------

--
-- Table structure for table `cultural_overall_score`
--

CREATE TABLE IF NOT EXISTS `cultural_overall_score` (
  `COS_ID` int(11) NOT NULL,
  `ACE_ID` int(11) NOT NULL,
  `COS_TotalScore` double NOT NULL,
  PRIMARY KEY (`COS_ID`),
  KEY `ACE_ID` (`ACE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cultural_ranking_points`
--

CREATE TABLE IF NOT EXISTS `cultural_ranking_points` (
  `RP_ID` int(11) NOT NULL,
  `Cul_ID` int(11) NOT NULL,
  `RP_Point` double NOT NULL,
  `RP_Rank` varchar(25) NOT NULL,
  PRIMARY KEY (`RP_ID`),
  KEY `Cul_ID` (`Cul_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event_category`
--

CREATE TABLE IF NOT EXISTS `event_category` (
  `Category_ID` int(11) NOT NULL,
  `Cul_ID` int(11) NOT NULL,
  `Category_Name` varchar(25) NOT NULL,
  `Category_Per` double NOT NULL,
  PRIMARY KEY (`Category_ID`),
  KEY `Cul_ID` (`Cul_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `overall_points`
--

CREATE TABLE IF NOT EXISTS `overall_points` (
  `OP_ID` int(11) NOT NULL,
  `OP_Percentage` double NOT NULL,
  `OP_Status` varchar(25) NOT NULL,
  `OP_Type` varchar(25) NOT NULL,
  PRIMARY KEY (`OP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `overall_points`
--

INSERT INTO `overall_points` (`OP_ID`, `OP_Percentage`, `OP_Status`, `OP_Type`) VALUES
(1, 12, 'asdas', 'sdas');

-- --------------------------------------------------------

--
-- Table structure for table `participants`
--

CREATE TABLE IF NOT EXISTS `participants` (
  `Part_ID` int(11) NOT NULL,
  `Team_ID` int(11) NOT NULL,
  `Part_FName` varchar(25) NOT NULL,
  `Part_MName` varchar(25) NOT NULL,
  `Part_LName` varchar(25) NOT NULL,
  `Part_Address` varchar(25) NOT NULL,
  `Part_Gender` varchar(25) NOT NULL,
  `Part_ContactNumber` varchar(25) NOT NULL,
  `Part_Status` varchar(25) NOT NULL,
  `Part_Pic` blob,
  PRIMARY KEY (`Part_ID`),
  KEY `Team_ID` (`Team_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE IF NOT EXISTS `results` (
  `Res_ID` int(11) NOT NULL,
  `COS_ID` int(11) NOT NULL,
  `SOS_ID` int(11) NOT NULL,
  `Res_Rank` varchar(25) NOT NULL,
  `Res_Date` date NOT NULL,
  `Res_Scorer` varchar(25) NOT NULL,
  PRIMARY KEY (`Res_ID`),
  KEY `SOS_ID` (`SOS_ID`),
  KEY `COS_ID` (`COS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sports_events`
--

CREATE TABLE IF NOT EXISTS `sports_events` (
  `Sp_ID` int(11) NOT NULL DEFAULT '0',
  `Acc_ID` int(11) NOT NULL,
  `OP_ID` int(11) NOT NULL,
  `Sp_Percentage` varchar(25) NOT NULL,
  `Sp_Name` varchar(25) NOT NULL,
  `Sp_Status` varchar(25) NOT NULL,
  `Sp_Type` varchar(25) NOT NULL DEFAULT '',
  `Sp_Sched` varchar(25) NOT NULL DEFAULT '',
  `Sp_Date` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`Sp_ID`),
  KEY `Acc_ID` (`Acc_ID`),
  KEY `OP_ID` (`OP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sports_overall_score`
--

CREATE TABLE IF NOT EXISTS `sports_overall_score` (
  `SOS_ID` int(11) NOT NULL,
  `Ap_ID` int(11) NOT NULL,
  `SOS_TotalScore` double NOT NULL,
  PRIMARY KEY (`SOS_ID`),
  KEY `Ap_ID` (`Ap_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sports_ranking_points`
--

CREATE TABLE IF NOT EXISTS `sports_ranking_points` (
  `SRP_ID` int(11) NOT NULL,
  `SP_ID` int(11) NOT NULL,
  `SRP_Points` int(11) NOT NULL,
  `SRP_Rank` varchar(25) NOT NULL,
  PRIMARY KEY (`SRP_ID`),
  KEY `SP_ID` (`SP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sports_score`
--

CREATE TABLE IF NOT EXISTS `sports_score` (
  `S_Score_ID` int(11) NOT NULL,
  `Ap_ID` int(11) NOT NULL,
  `S_Given_Score` double NOT NULL,
  `S_CompScore` double NOT NULL,
  PRIMARY KEY (`S_Score_ID`),
  KEY `Ap_ID` (`Ap_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `Team_ID` int(11) NOT NULL,
  `Team_Name` varchar(25) NOT NULL,
  `Team_Number` int(11) NOT NULL,
  `Team_logo` blob,
  `Team_Status` varchar(25) NOT NULL,
  PRIMARY KEY (`Team_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `voters_password`
--

CREATE TABLE IF NOT EXISTS `voters_password` (
  `VP_ID` int(11) NOT NULL,
  `VP_Pass` varchar(25) NOT NULL,
  `Vp_Status` varchar(25) NOT NULL,
  PRIMARY KEY (`VP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attended_culturalevents`
--
ALTER TABLE `attended_culturalevents`
  ADD CONSTRAINT `attended_culturalevents_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`),
  ADD CONSTRAINT `attended_culturalevents_ibfk_2` FOREIGN KEY (`Part_ID`) REFERENCES `participants` (`Part_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attended_sportsevents`
--
ALTER TABLE `attended_sportsevents`
  ADD CONSTRAINT `attended_sportsevents_ibfk_1` FOREIGN KEY (`Sp_ID`) REFERENCES `sports_events` (`Sp_ID`),
  ADD CONSTRAINT `attended_sportsevents_ibfk_2` FOREIGN KEY (`Part_ID`) REFERENCES `participants` (`Part_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `criteria_cultural`
--
ALTER TABLE `criteria_cultural`
  ADD CONSTRAINT `criteria_cultural_ibfk_1` FOREIGN KEY (`Category_ID`) REFERENCES `event_category` (`Category_ID`),
  ADD CONSTRAINT `criteria_cultural_ibfk_2` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `criteria_score`
--
ALTER TABLE `criteria_score`
  ADD CONSTRAINT `criteria_score_ibfk_1` FOREIGN KEY (`Cr_ID`) REFERENCES `criteria_cultural` (`Cr_ID`),
  ADD CONSTRAINT `criteria_score_ibfk_2` FOREIGN KEY (`ACE_ID`) REFERENCES `attended_culturalevents` (`ACE_ID`),
  ADD CONSTRAINT `criteria_score_ibfk_3` FOREIGN KEY (`VP_ID`) REFERENCES `voters_password` (`VP_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cultural_events`
--
ALTER TABLE `cultural_events`
  ADD CONSTRAINT `cultural_events_ibfk_1` FOREIGN KEY (`Acc_ID`) REFERENCES `accounts` (`Acc_ID`),
  ADD CONSTRAINT `cultural_events_ibfk_2` FOREIGN KEY (`OP_ID`) REFERENCES `overall_points` (`OP_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cultural_overall_score`
--
ALTER TABLE `cultural_overall_score`
  ADD CONSTRAINT `cultural_overall_score_ibfk_1` FOREIGN KEY (`ACE_ID`) REFERENCES `attended_culturalevents` (`ACE_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cultural_ranking_points`
--
ALTER TABLE `cultural_ranking_points`
  ADD CONSTRAINT `cultural_ranking_points_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `event_category`
--
ALTER TABLE `event_category`
  ADD CONSTRAINT `event_category_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`Team_ID`) REFERENCES `teams` (`Team_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`SOS_ID`) REFERENCES `sports_overall_score` (`SOS_ID`),
  ADD CONSTRAINT `results_ibfk_2` FOREIGN KEY (`COS_ID`) REFERENCES `cultural_overall_score` (`COS_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sports_events`
--
ALTER TABLE `sports_events`
  ADD CONSTRAINT `sports_events_ibfk_1` FOREIGN KEY (`Acc_ID`) REFERENCES `accounts` (`Acc_ID`),
  ADD CONSTRAINT `sports_events_ibfk_2` FOREIGN KEY (`OP_ID`) REFERENCES `overall_points` (`OP_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sports_overall_score`
--
ALTER TABLE `sports_overall_score`
  ADD CONSTRAINT `sports_overall_score_ibfk_1` FOREIGN KEY (`Ap_ID`) REFERENCES `attended_sportsevents` (`Ap_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sports_ranking_points`
--
ALTER TABLE `sports_ranking_points`
  ADD CONSTRAINT `sports_ranking_points_ibfk_1` FOREIGN KEY (`SP_ID`) REFERENCES `sports_events` (`Sp_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sports_score`
--
ALTER TABLE `sports_score`
  ADD CONSTRAINT `sports_score_ibfk_1` FOREIGN KEY (`Ap_ID`) REFERENCES `attended_sportsevents` (`Ap_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
