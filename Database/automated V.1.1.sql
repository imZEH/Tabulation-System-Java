-- MySQL dump 10.13  Distrib 5.5.24, for Win32 (x86)
--
-- Host: localhost    Database: automated
-- ------------------------------------------------------
-- Server version	5.5.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `Acc_ID` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attended_culturalevents`
--

DROP TABLE IF EXISTS `attended_culturalevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attended_culturalevents` (
  `ACE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Part_ID` int(11) NOT NULL,
  `Cul_ID` int(11) NOT NULL,
  `ACE_Secnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`ACE_ID`),
  KEY `Cul_ID` (`Cul_ID`),
  KEY `Part_ID` (`Part_ID`),
  CONSTRAINT `attended_culturalevents_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`),
  CONSTRAINT `attended_culturalevents_ibfk_2` FOREIGN KEY (`Part_ID`) REFERENCES `participants` (`Part_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attended_culturalevents`
--

LOCK TABLES `attended_culturalevents` WRITE;
/*!40000 ALTER TABLE `attended_culturalevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `attended_culturalevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attended_sportsevents`
--

DROP TABLE IF EXISTS `attended_sportsevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attended_sportsevents` (
  `Ap_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Sp_ID` int(11) NOT NULL,
  `Part_ID` int(11) NOT NULL,
  PRIMARY KEY (`Ap_ID`),
  KEY `Sp_ID` (`Sp_ID`),
  KEY `Part_ID` (`Part_ID`),
  CONSTRAINT `attended_sportsevents_ibfk_1` FOREIGN KEY (`Sp_ID`) REFERENCES `sports_events` (`Sp_ID`),
  CONSTRAINT `attended_sportsevents_ibfk_2` FOREIGN KEY (`Part_ID`) REFERENCES `participants` (`Part_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attended_sportsevents`
--

LOCK TABLES `attended_sportsevents` WRITE;
/*!40000 ALTER TABLE `attended_sportsevents` DISABLE KEYS */;
/*!40000 ALTER TABLE `attended_sportsevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria_cultural`
--

DROP TABLE IF EXISTS `criteria_cultural`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criteria_cultural` (
  `Cr_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cul_ID` int(11) NOT NULL,
  `Category_ID` int(11) NOT NULL,
  `Cr_Description` varchar(25) NOT NULL,
  `Cr_Percentage` varchar(25) NOT NULL,
  PRIMARY KEY (`Cr_ID`),
  KEY `Category_ID` (`Category_ID`),
  KEY `Cul_ID` (`Cul_ID`),
  CONSTRAINT `criteria_cultural_ibfk_1` FOREIGN KEY (`Category_ID`) REFERENCES `event_category` (`Category_ID`),
  CONSTRAINT `criteria_cultural_ibfk_2` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria_cultural`
--

LOCK TABLES `criteria_cultural` WRITE;
/*!40000 ALTER TABLE `criteria_cultural` DISABLE KEYS */;
/*!40000 ALTER TABLE `criteria_cultural` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria_score`
--

DROP TABLE IF EXISTS `criteria_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criteria_score` (
  `CrScore_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cr_ID` int(11) NOT NULL,
  `ACE_ID` int(11) NOT NULL,
  `VP_ID` int(11) NOT NULL,
  `CrScore` double NOT NULL,
  `Cr_Computed` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`CrScore_ID`),
  KEY `Cr_ID` (`Cr_ID`),
  KEY `ACE_ID` (`ACE_ID`),
  KEY `VP_ID` (`VP_ID`),
  CONSTRAINT `criteria_score_ibfk_1` FOREIGN KEY (`Cr_ID`) REFERENCES `criteria_cultural` (`Cr_ID`),
  CONSTRAINT `criteria_score_ibfk_2` FOREIGN KEY (`ACE_ID`) REFERENCES `attended_culturalevents` (`ACE_ID`),
  CONSTRAINT `criteria_score_ibfk_3` FOREIGN KEY (`VP_ID`) REFERENCES `voters_password` (`VP_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria_score`
--

LOCK TABLES `criteria_score` WRITE;
/*!40000 ALTER TABLE `criteria_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `criteria_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultural_events`
--

DROP TABLE IF EXISTS `cultural_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cultural_events` (
  `Cul_ID` int(11) NOT NULL AUTO_INCREMENT,
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
  KEY `OP_ID` (`OP_ID`),
  CONSTRAINT `cultural_events_ibfk_1` FOREIGN KEY (`Acc_ID`) REFERENCES `accounts` (`Acc_ID`),
  CONSTRAINT `cultural_events_ibfk_2` FOREIGN KEY (`OP_ID`) REFERENCES `overall_points` (`OP_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultural_events`
--

LOCK TABLES `cultural_events` WRITE;
/*!40000 ALTER TABLE `cultural_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `cultural_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultural_overall_score`
--

DROP TABLE IF EXISTS `cultural_overall_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cultural_overall_score` (
  `COS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACE_ID` int(11) NOT NULL,
  `COS_TotalScore` double NOT NULL,
  PRIMARY KEY (`COS_ID`),
  KEY `ACE_ID` (`ACE_ID`),
  CONSTRAINT `cultural_overall_score_ibfk_1` FOREIGN KEY (`ACE_ID`) REFERENCES `attended_culturalevents` (`ACE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultural_overall_score`
--

LOCK TABLES `cultural_overall_score` WRITE;
/*!40000 ALTER TABLE `cultural_overall_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `cultural_overall_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cultural_ranking_points`
--

DROP TABLE IF EXISTS `cultural_ranking_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cultural_ranking_points` (
  `RP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cul_ID` int(11) NOT NULL,
  `RP_Point` double NOT NULL,
  `RP_Rank` varchar(25) NOT NULL,
  PRIMARY KEY (`RP_ID`),
  KEY `Cul_ID` (`Cul_ID`),
  CONSTRAINT `cultural_ranking_points_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cultural_ranking_points`
--

LOCK TABLES `cultural_ranking_points` WRITE;
/*!40000 ALTER TABLE `cultural_ranking_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `cultural_ranking_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_category`
--

DROP TABLE IF EXISTS `event_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_category` (
  `Category_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cul_ID` int(11) NOT NULL,
  `Category_Name` varchar(25) NOT NULL,
  `Category_Per` double NOT NULL,
  PRIMARY KEY (`Category_ID`),
  KEY `Cul_ID` (`Cul_ID`),
  CONSTRAINT `event_category_ibfk_1` FOREIGN KEY (`Cul_ID`) REFERENCES `cultural_events` (`Cul_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_category`
--

LOCK TABLES `event_category` WRITE;
/*!40000 ALTER TABLE `event_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overall_points`
--

DROP TABLE IF EXISTS `overall_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `overall_points` (
  `OP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OP_Percentage` double NOT NULL,
  `OP_Status` varchar(25) NOT NULL,
  `OP_Type` varchar(25) NOT NULL,
  PRIMARY KEY (`OP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overall_points`
--

LOCK TABLES `overall_points` WRITE;
/*!40000 ALTER TABLE `overall_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `overall_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participants` (
  `Part_ID` int(11) NOT NULL AUTO_INCREMENT,
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
  KEY `Team_ID` (`Team_ID`),
  CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`Team_ID`) REFERENCES `teams` (`Team_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `Res_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COS_ID` int(11) NOT NULL,
  `SOS_ID` int(11) NOT NULL,
  `Res_Rank` varchar(25) NOT NULL,
  `Res_Date` date NOT NULL,
  `Res_Scorer` varchar(25) NOT NULL,
  PRIMARY KEY (`Res_ID`),
  KEY `SOS_ID` (`SOS_ID`),
  KEY `COS_ID` (`COS_ID`),
  CONSTRAINT `results_ibfk_1` FOREIGN KEY (`SOS_ID`) REFERENCES `sports_overall_score` (`SOS_ID`),
  CONSTRAINT `results_ibfk_2` FOREIGN KEY (`COS_ID`) REFERENCES `cultural_overall_score` (`COS_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_events`
--

DROP TABLE IF EXISTS `sports_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports_events` (
  `Sp_ID` int(11) NOT NULL AUTO_INCREMENT,
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
  KEY `OP_ID` (`OP_ID`),
  CONSTRAINT `sports_events_ibfk_1` FOREIGN KEY (`Acc_ID`) REFERENCES `accounts` (`Acc_ID`),
  CONSTRAINT `sports_events_ibfk_2` FOREIGN KEY (`OP_ID`) REFERENCES `overall_points` (`OP_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_events`
--

LOCK TABLES `sports_events` WRITE;
/*!40000 ALTER TABLE `sports_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_overall_score`
--

DROP TABLE IF EXISTS `sports_overall_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports_overall_score` (
  `SOS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Ap_ID` int(11) NOT NULL,
  `SOS_TotalScore` double NOT NULL,
  PRIMARY KEY (`SOS_ID`),
  KEY `Ap_ID` (`Ap_ID`),
  CONSTRAINT `sports_overall_score_ibfk_1` FOREIGN KEY (`Ap_ID`) REFERENCES `attended_sportsevents` (`Ap_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_overall_score`
--

LOCK TABLES `sports_overall_score` WRITE;
/*!40000 ALTER TABLE `sports_overall_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports_overall_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_ranking_points`
--

DROP TABLE IF EXISTS `sports_ranking_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports_ranking_points` (
  `SRP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SP_ID` int(11) NOT NULL,
  `SRP_Points` int(11) NOT NULL,
  `SRP_Rank` varchar(25) NOT NULL,
  PRIMARY KEY (`SRP_ID`),
  KEY `SP_ID` (`SP_ID`),
  CONSTRAINT `sports_ranking_points_ibfk_1` FOREIGN KEY (`SP_ID`) REFERENCES `sports_events` (`Sp_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_ranking_points`
--

LOCK TABLES `sports_ranking_points` WRITE;
/*!40000 ALTER TABLE `sports_ranking_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports_ranking_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_score`
--

DROP TABLE IF EXISTS `sports_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports_score` (
  `S_Score_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Ap_ID` int(11) NOT NULL,
  `S_Given_Score` double NOT NULL,
  `S_CompScore` double NOT NULL,
  PRIMARY KEY (`S_Score_ID`),
  KEY `Ap_ID` (`Ap_ID`),
  CONSTRAINT `sports_score_ibfk_1` FOREIGN KEY (`Ap_ID`) REFERENCES `attended_sportsevents` (`Ap_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_score`
--

LOCK TABLES `sports_score` WRITE;
/*!40000 ALTER TABLE `sports_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `Team_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Team_Name` varchar(25) NOT NULL,
  `Team_Number` int(11) NOT NULL,
  `Team_logo` blob,
  `Team_Status` varchar(25) NOT NULL,
  PRIMARY KEY (`Team_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voters_password`
--

DROP TABLE IF EXISTS `voters_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voters_password` (
  `VP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `VP_Pass` varchar(25) NOT NULL,
  `Vp_Status` varchar(25) NOT NULL,
  PRIMARY KEY (`VP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voters_password`
--

LOCK TABLES `voters_password` WRITE;
/*!40000 ALTER TABLE `voters_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `voters_password` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-07  8:17:36
