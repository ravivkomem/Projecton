-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: icm
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `analysis_step`
--

DROP TABLE IF EXISTS `analysis_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `analysis_step` (
  `AnalysisStepID` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestID` int(11) NOT NULL,
  `HandlerUserName` varchar(45) NOT NULL,
  `StartDate` date NOT NULL,
  `Status` varchar(45) NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `AnalysisReportHeader` varchar(100) DEFAULT NULL,
  `AnalysisReportDescription` varchar(100) DEFAULT NULL,
  `AnalysisReportAdvantages` varchar(100) DEFAULT NULL,
  `AnalysisReportDuration` date DEFAULT NULL,
  `AnalysisReportConstraints` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AnalysisStepID`),
  UNIQUE KEY `AnalysisStepID_UNIQUE` (`AnalysisStepID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis_step`
--

LOCK TABLES `analysis_step` WRITE;
/*!40000 ALTER TABLE `analysis_step` DISABLE KEYS */;
INSERT INTO `analysis_step` VALUES (1,1,'lior','2019-11-19','CLOSED','2019-11-26','2019-11-25','moodle changing','make the moodle beter','a','2020-01-01','aa'),(2,2,'itay','2019-11-19','CLOSED','2019-11-25','2019-11-26','website changing','fix a bug ','b','2020-01-01','bb'),(3,3,'itayz','2019-12-18','CLOSED','2019-12-19','2019-12-19','Moodle System changing','make it better','c','2020-01-01','cc'),(4,4,'ido','2019-12-18','CLOSED','2019-12-19','2019-12-18','College Website changing ','fix the problem','d','2020-01-01','dd'),(5,5,'itayz','2019-12-19','CLOSED','2019-12-21','2019-12-20','Employee Station ','fix a bug ','e','2020-01-01','ee'),(6,6,'gilad','2019-12-19','CLOSED','2019-12-21','2019-12-22','Employee Station ','make it better','f','2020-01-01','ff'),(7,71,'zeev','2019-12-20','CLOSED','2019-12-23','2019-12-23','Employee Station ','fix the problem','g','2020-01-01','gg'),(8,72,'lior','2019-12-20','CLOSED','2019-12-22','2019-12-21','Employee Station ','fix a bug ','h','2020-01-01','hh'),(9,73,'itay','2019-12-20','CLOSED','2019-12-22','2019-12-23','Employee Station ','fix the problem','i','2020-01-01','ii'),(10,74,'ido','2019-12-21','CLOSED','2019-12-22','2019-12-22','Library System changing','make it better','j','2020-01-01','jj'),(11,75,'ido','2019-12-22','CLOSED','2019-12-23','2019-12-23','Student Information Station changing','fix the problem','k','2020-01-01','kk'),(12,76,'lior','2019-12-23','ACTIVE','2020-01-11',NULL,NULL,NULL,NULL,NULL,NULL),(13,77,'itay','2019-12-23','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,78,'lior','2019-12-23','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,79,'ido','2019-12-24','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,80,'itay','2019-12-24','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,81,'itay','2019-12-29','ACTIVE','2020-01-16',NULL,NULL,NULL,NULL,NULL,NULL),(18,82,'ido','2019-12-24','CLOSED','2019-12-25','2019-12-26','Moodle System changing','fix a bug ','r','2020-01-01','rr'),(20,84,'gilad','2020-01-10','CLOSED','2020-01-14','2020-01-10','moodle work slow','the moodle work slow, need to make it faster','non','2020-01-01','non'),(21,83,'lior','2020-01-10','CLOSED','2020-01-14','2020-01-13','a','a','a','2020-01-01','a'),(22,85,'lior','2020-01-13','CLOSED','2020-01-16','2020-01-13','1','1','1','2020-01-01','1'),(23,85,'gilad','2020-01-13','CLOSED','2020-01-17','2020-01-13','1','1','1','2020-01-01','1'),(24,83,'gilad','2020-01-13','CLOSED','2020-01-15','2020-01-13','a','a','a','2020-01-01','a'),(26,86,'ido','2020-01-15','CLOSED','2020-01-17','2020-01-15','aa','a','a','2020-01-23','a'),(27,87,'gilad','2020-01-16','CLOSED','2020-01-18','2020-01-16','library bags','need to show books that taken','non','2020-01-22','a'),(28,76,'lior','2020-01-16','ACTIVE','2020-01-18',NULL,NULL,NULL,NULL,NULL,NULL),(29,88,'zeev','2020-01-16','CLOSED','2020-01-18','2020-01-16','college web site down','the web site do not upload,\nprobably server problems.','non','2020-01-31','n'),(30,80,'lior','2020-01-16','ACTIVE','2020-01-18',NULL,NULL,NULL,NULL,NULL,NULL),(31,88,'ido','2020-01-16','CLOSED','2020-01-24','2020-01-16','college web site down','the web site do not upload,\nprobably server problems.','non','2020-01-31','n'),(32,89,'itayz','2020-01-16','CLOSED','2020-01-23','2020-01-16','11','11','11','2020-01-31','11'),(33,90,'lior','2020-01-16','CLOSED','2020-01-18','2020-01-16','Add feacher to the Moodle','a','a','2020-01-31','a'),(34,91,'itayz','2020-01-16','CLOSED','2020-01-17','2020-01-16','Moodle work slowly','The moodle work slowly so we need\nto chack if the server work well','non','2020-01-20','non');
/*!40000 ALTER TABLE `analysis_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `change_request`
--

DROP TABLE IF EXISTS `change_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `change_request` (
  `ChangeRequestID` int(11) NOT NULL AUTO_INCREMENT,
  `InitiatorUserName` varchar(45) NOT NULL,
  `StartDate` date NOT NULL,
  `SelectedSubsystem` varchar(105) NOT NULL,
  `CurrentStateDescription` varchar(105) NOT NULL,
  `DesiredChangeDescription` varchar(105) NOT NULL,
  `DesiredChangeExplanation` varchar(105) NOT NULL,
  `DesiredChangeComments` varchar(105) NOT NULL,
  `Status` varchar(105) NOT NULL,
  `CurrentStep` varchar(105) NOT NULL,
  `HandlerUserName` varchar(105) NOT NULL,
  `EndDate` date DEFAULT NULL,
  `Email` varchar(105) NOT NULL,
  `JobDescription` varchar(105) NOT NULL,
  `FullName` varchar(105) NOT NULL,
  `EstimatedDate` date DEFAULT NULL,
  PRIMARY KEY (`ChangeRequestID`),
  UNIQUE KEY `ChangeRequestID_UNIQUE` (`ChangeRequestID`),
  KEY `UserName_idx` (`InitiatorUserName`,`HandlerUserName`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `change_request`
--

LOCK TABLES `change_request` WRITE;
/*!40000 ALTER TABLE `change_request` DISABLE KEYS */;
INSERT INTO `change_request` VALUES (1,'raviv','2019-11-19','Moodle System','The font is too small','Please make the font a bit larger','I can\'t see the font case it\'s too small','make the font larger','CLOSED','FINISH','lior','2019-12-07','ravivkomem@gmail.com','Student','Raviv Komem','2019-12-08'),(2,'lee','2019-11-19','College Website','It\'s hard to find what you are looking for','Please make it more comfortable','It\'s hard to find the location of things','make the better','CLOSED','FINISH','lior','2020-01-02','leehugi93@gmail.com','Tech Manager','Lee Hugi','2019-12-29'),(3,'ido','2019-12-18','Moodle System','boring look','Make it more interesting','I think the site looks ugly','-','DENIED','FINISH','lior','2019-12-22','idokadosh@gmail.com','Committee Member','Ido Kadosh','2020-01-01'),(4,'ido','2019-12-18','College Website','The site work slowly','Please make the site faster','It\'s very hard to work on the site now','-','SUSPEND','TESTING_WORK','ido',NULL,'idokadosh@gmail.com','Committee Member','Ido Kadosh','2020-01-16'),(5,'raviv','2019-12-19','Employee Information Station','There are no images of the employees','Add images for every employee','I want to see who are the workers','Ask for a photo from every employee','SUSPEND','COMMITTEE_WORK','lior',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-14'),(6,'raviv','2019-12-19','Employee Information Station','There are no emails of employees','Add emails to all the employees','I need to send an email to another employee','-','ACTIVE','EXECUTION_WORK','ido',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(71,'raviv','2019-12-20','Employee Information Station','asfdasfd','sadffffdsa','asdffff','sfad','ACTIVE','EXECUTION_SET_TIME','lior',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-18'),(72,'raviv','2019-12-20','Employee Information Station','safdasdf','fasd','fasdafsdfdsa','sdfaasdffdas','CLOSED','FINISH','lior','2019-12-30','ravivkomem@gmail.com','Student','Raviv Komem','2019-12-27'),(73,'raviv','2019-12-20','Employee Information Station','sfdaafdsdasf','fdasfasd','fdasfadsafsd','fdsaadfs','ACTIVE','CLOSING_STEP','',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(74,'raviv','2019-12-21','Library System','fsdafasdfads','fads','asdfafdsfdas','fdsa','DENIED','FINISH','itay','2019-12-23','ravivkomem@gmail.com','Student','Raviv Komem','2020-01-01'),(75,'raviv','2019-12-22','Student Information Station','sfdasdaf','fdsafsda','fadsfsdasdfa','fdsaasdffdasfdas','ACTIVE','COMMITTEE_WORK','ido',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-20'),(76,'raviv','2019-12-23','Library System','dfsaafds','asfddsfa','sfdaafsdfdas','sadffasd','ACTIVE','ANALYSIS_WORK','lior',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(77,'raviv','2019-12-23','Laboratory','asdf','fdsa','asdffdsafdsa','fsdafadsasfdfsda','ACTIVE','ANALYZER_AUTO_APPOINT','itay',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(78,'raviv','2019-12-23','Laboratory','sadfa','sddfas','adfsdfas','fsdaadsffda','ACTIVE','ANALYZER_AUTO_APPOINT','lior',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-22'),(79,'raviv','2019-12-24','Class Rooms With Computers','sdf','sdffds','fdssdf','fds','ACTIVE','ANALYZER_AUTO_APPOINT','itayz',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(80,'lee','2019-12-24','Moodle System','a','a','a','','ACTIVE','ANALYSIS_WORK','lior',NULL,'leehugi93@gmail.com','Tech Manager','Lee Hugi','2020-01-16'),(81,'raviv','2019-12-29','Moodle System','dsfdsf','dfsd','sfdsfdsf','sdffsddfs','ACTIVE','ANALYSIS_APPROVE_TIME','itay',NULL,'ravivkomem@gmail.com','Student','Raviv Komem','2020-01-21'),(82,'itayz','2019-12-24','Moodle System','aaa','aaa\naaa\naaa','aaa','','CLOSED','FINISH','','2020-01-16','itayziv8@gmail.com','Committee Director','Itay Ziv ','2020-01-16'),(83,'lee','2020-01-10','Moodle System','work very bad 	','fix the bug		','because i said so ','do it fast ','ACTIVE','COMMITTEE_WORK','-',NULL,'leehugi93@gmail.com','Tech Manager','Lee Hugi','2020-01-23'),(84,'lee','2020-01-10','Moodle System','work very slow','make it fast','it\'s slow','','ACTIVE','TESTER_COMMITTEE_DIRECTOR_APPOINT','itayz',NULL,'leehugi93@gmail.com','Tech Manager','Lee Hugi','2020-01-16'),(85,'raviv','2020-01-13','Moodle System','moodle stucks  ','fix the bug ','so i can learn to my tests. ','','CLOSED','FINISH','','2020-01-13','ravivkomem@gmail.com','Student','Raviv Komem','2020-01-16'),(86,'itay','2020-01-15','Employee Information Station','a','a','a','','ACTIVE','CLOSING_STEP','',NULL,'itaydavid22@gmail.com','Supervisor','Itay David','2020-01-23'),(87,'lee','2020-01-16','Library System','not show the books i was take','please fix the bag','i have to know which books i was take','','CLOSED','FINISH','','2020-01-16','leehugi93@gmail.com','Tech Manager','Lee Hugi','2020-01-22'),(88,'moti','2020-01-16','College Website','the web site do not work','fix the web site bags','no one can enter the web site','','CLOSED','FINISH','','2020-01-16','notiben@gmail.com','Worker','Moti Ben Hamo','2020-01-31'),(89,'ido','2020-01-16','Moodle System','asdf	asf	','asd','asd','asd','CLOSED','FINISH','','2020-01-16','idokadosh1@gmail.com','Committee Member','Ido Kadosh','2020-01-31'),(90,'raviv','2020-01-16','Moodle System','the page show the courses with out\norder by','i want the moodle show\nmy courses alphabetically.','i think it\'s a good idea','','CLOSED','FINISH','','2020-01-16','ravivkomem@gmail.com','Student','Raviv Komem','2020-01-31'),(91,'lee','2020-01-16','Moodle System','The moodle work slow','Please make it faster','it\'s took alot of time to load\nthe moodle page','','ACTIVE','COMMITTEE_WORK','-',NULL,'leehugi93@gmail.com','Tech Manager','Lee Hugi','2020-01-20');
/*!40000 ALTER TABLE `change_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `closing_step`
--

DROP TABLE IF EXISTS `closing_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `closing_step` (
  `ClosingStepID` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestID` int(11) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date DEFAULT NULL,
  `Status` varchar(45) NOT NULL COMMENT 'Close_Active\nClose_Finish\nDeny_Active\nDeny_Finish',
  PRIMARY KEY (`ClosingStepID`),
  UNIQUE KEY `ClosingStepID_UNIQUE` (`ClosingStepID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `closing_step`
--

LOCK TABLES `closing_step` WRITE;
/*!40000 ALTER TABLE `closing_step` DISABLE KEYS */;
INSERT INTO `closing_step` VALUES (1,1,'2019-12-07','2019-12-07','CLOSED'),(2,2,'2020-01-02','2020-01-02','CLOSED'),(3,3,'2019-12-21','2019-12-22','CLOSED'),(4,72,'2019-12-28','2019-12-30','CLOSED'),(6,82,'2020-01-13','2020-01-16','CLOSED'),(8,87,'2020-01-16','2020-01-16','CLOSED'),(9,88,'2020-01-16','2020-01-16','CLOSED'),(10,88,'2020-01-16','2020-01-16','CLOSED'),(11,89,'2020-01-16','2020-01-16','CLOSED'),(12,90,'2020-01-16','2020-01-16','CLOSED'),(13,73,'2020-01-16',NULL,'ACTIVE'),(14,86,'2020-01-16',NULL,'ACTIVE');
/*!40000 ALTER TABLE `closing_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee_comment`
--

DROP TABLE IF EXISTS `committee_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `committee_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `committeeStepId` int(11) NOT NULL,
  `requestId` int(11) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `comment` varchar(202) NOT NULL,
  PRIMARY KEY (`commentId`),
  UNIQUE KEY `commentId_UNIQUE` (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_comment`
--

LOCK TABLES `committee_comment` WRITE;
/*!40000 ALTER TABLE `committee_comment` DISABLE KEYS */;
INSERT INTO `committee_comment` VALUES (1,1,1,'ido','need to approve'),(2,1,1,'gilad','it\'s important'),(3,2,2,'gilad','approve'),(4,3,3,'ido','need to deny'),(5,4,4,'ido','good idea'),(6,7,71,'ido','good idea'),(7,8,72,'gilad','important request'),(8,13,84,'gilad','have to do it, need to approve'),(9,12,82,'itayz','i think it\'s a good idea'),(10,21,85,'gilad','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),(11,22,85,'itayz','DDSA'),(12,20,83,'itayz','aaa'),(13,20,83,'itayz','bbb'),(14,25,87,'ido','have to handle fast'),(15,26,88,'ido','need more information'),(16,27,88,'ido','need to fix fast'),(17,29,90,'ido','good idea.\nbut we do not have to do this changes');
/*!40000 ALTER TABLE `committee_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee_step`
--

DROP TABLE IF EXISTS `committee_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `committee_step` (
  `CommitteeStepId` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestId` int(11) NOT NULL,
  `HandlerUserName` varchar(45) NOT NULL,
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  `DenyComment` varchar(102) DEFAULT NULL,
  PRIMARY KEY (`CommitteeStepId`),
  UNIQUE KEY `CommitteeStepId_UNIQUE` (`CommitteeStepId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_step`
--

LOCK TABLES `committee_step` WRITE;
/*!40000 ALTER TABLE `committee_step` DISABLE KEYS */;
INSERT INTO `committee_step` VALUES (1,1,'itayz','2019-11-25','2019-11-30','2019-12-02','CLOSED',NULL),(2,2,'gilad','2019-11-26','2019-11-30','2019-12-01','CLOSED',NULL),(3,3,'zeev','2019-12-19','2019-12-26','2019-12-21','CLOSED','not necessary'),(4,4,'lior','2019-12-19','2019-12-26','2019-12-20','CLOSED',NULL),(5,5,'ido','2019-12-20','2019-12-27',NULL,'SUSPEND',NULL),(6,6,'gilad','2019-12-22','2019-12-29','2019-12-25','CLOSED',NULL),(7,71,'ido','2019-12-23','2019-12-25','2019-12-24','CLOSED',NULL),(8,72,'itayz','2019-12-21','2019-12-28','2019-12-25','CLOSED',NULL),(9,73,'gilad','2019-12-23','2019-12-30','2019-12-26','CLOSED',NULL),(10,74,'zeev','2019-12-22','2019-12-29','2019-12-23','CLOSED','not important request'),(11,75,'ido','2019-12-23','2019-12-30',NULL,'ACTIVE',NULL),(12,82,'itayz','2019-12-26','2020-01-02','2020-01-13','CLOSED','ASD'),(13,84,'itayz','2020-01-10','2020-01-17','2020-01-10','CLOSED',''),(20,83,'itayz','2020-01-12','2020-01-19','2020-01-13','CLOSED',''),(21,85,'itayz','2020-01-13','2020-01-20','2020-01-13','CLOSED',''),(22,85,'itayz','2020-01-13','2020-01-20','2020-01-13','CLOSED',''),(23,83,'itayz','2020-01-13','2020-01-20',NULL,'ACTIVE',NULL),(24,86,'itayz','2020-01-15','2020-01-22','2020-01-16','CLOSED',''),(25,87,'itayz','2020-01-16','2020-01-23','2020-01-16','CLOSED','it\'s not a bag.\nThe web work fine'),(26,88,'itayz','2020-01-16','2020-01-23','2020-01-16','CLOSED',''),(27,88,'itayz','2020-01-16','2020-01-23','2020-01-16','CLOSED',''),(28,89,'itayz','2020-01-16','2020-01-23','2020-01-16','CLOSED',''),(29,90,'itayz','2020-01-16','2020-01-23','2020-01-16','CLOSED',''),(30,91,'itayz','2020-01-16','2020-01-23',NULL,'ACTIVE',NULL);
/*!40000 ALTER TABLE `committee_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `execution_step`
--

DROP TABLE IF EXISTS `execution_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `execution_step` (
  `ExecutionStepID` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestID` int(11) NOT NULL,
  `HandlerUserName` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `ExecutionComment` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`ExecutionStepID`),
  UNIQUE KEY `ExecutionStepID_UNIQUE` (`ExecutionStepID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_step`
--

LOCK TABLES `execution_step` WRITE;
/*!40000 ALTER TABLE `execution_step` DISABLE KEYS */;
INSERT INTO `execution_step` VALUES (1,2,'itay','CLOSED','2019-12-01','2019-12-31','2019-12-31',NULL),(2,1,'lior','CLOSED','2019-12-02','2019-12-04','2019-12-05',NULL),(3,4,'ido','CLOSED','2019-12-20','2019-12-25','2019-12-24',NULL),(4,6,'gilad','ACTIVE','2019-12-25','2020-01-01',NULL,NULL),(5,73,'gilad','CLOSED','2019-12-26','2020-01-01','2020-01-01',NULL),(6,71,'lior','ACTIVE','2019-12-24','2019-12-30',NULL,NULL),(7,72,'zeev','CLOSED','2019-12-25','2019-12-28','2019-12-27',NULL),(15,84,'lior','CLOSE','2020-01-10','2020-01-14','2020-01-10','fix the problem'),(16,85,'ido','CLOSE','2020-01-13','2020-01-16','2020-01-13','ASDFGHJKL'),(17,85,'ido','CLOSE','2020-01-13','2020-01-16','2020-01-13','sad'),(18,88,'lior','ACTIVE','2020-01-16','2020-01-20',NULL,NULL),(19,88,'lior','ACTIVE','2020-01-16','2020-01-18',NULL,NULL),(20,89,'lior','ACTIVE','2020-01-16','2020-01-21',NULL,NULL),(21,90,'lior','ACTIVE','2020-01-16','2020-01-17',NULL,NULL),(22,86,'lior','ACTIVE','2020-01-16','2020-01-24',NULL,NULL),(23,71,'lior','ACTIVE','2020-01-17',NULL,NULL,NULL);
/*!40000 ALTER TABLE `execution_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `FileID` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestID` varchar(45) NOT NULL,
  `FileEnding` varchar(45) NOT NULL,
  PRIMARY KEY (`FileID`),
  UNIQUE KEY `FileID_UNIQUE` (`FileID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'79','jpg'),(2,'80','png'),(3,'81','jpg'),(4,'83','png'),(5,'84','png'),(6,'85','jpg'),(7,'87','txt');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `mergred_steps`
--

DROP TABLE IF EXISTS `mergred_steps`;
/*!50001 DROP VIEW IF EXISTS `mergred_steps`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `mergred_steps` AS SELECT 
 1 AS `StepType`,
 1 AS `AnalysisStepID`,
 1 AS `ChangeRequestID`,
 1 AS `HandlerUserName`,
 1 AS `StartDate`,
 1 AS `Status`,
 1 AS `EstimatedEndDate`,
 1 AS `EndDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `repeating_analysis`
--

DROP TABLE IF EXISTS `repeating_analysis`;
/*!50001 DROP VIEW IF EXISTS `repeating_analysis`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `repeating_analysis` AS SELECT 
 1 AS `StepType`,
 1 AS `AnalysisStepID`,
 1 AS `ChangeRequestID`,
 1 AS `HandlerUserName`,
 1 AS `StartDate`,
 1 AS `Status`,
 1 AS `EstimatedEndDate`,
 1 AS `EndDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `repeating_committee`
--

DROP TABLE IF EXISTS `repeating_committee`;
/*!50001 DROP VIEW IF EXISTS `repeating_committee`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `repeating_committee` AS SELECT 
 1 AS `StepType`,
 1 AS `CommitteeStepID`,
 1 AS `ChangeRequestID`,
 1 AS `HandlerUserName`,
 1 AS `StartDate`,
 1 AS `Status`,
 1 AS `EstimatedEndDate`,
 1 AS `EndDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `repeating_execution`
--

DROP TABLE IF EXISTS `repeating_execution`;
/*!50001 DROP VIEW IF EXISTS `repeating_execution`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `repeating_execution` AS SELECT 
 1 AS `StepType`,
 1 AS `ExecutionStepID`,
 1 AS `ChangeRequestID`,
 1 AS `HandlerUserName`,
 1 AS `StartDate`,
 1 AS `Status`,
 1 AS `EstimatedEndDate`,
 1 AS `EndDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `repeating_tester`
--

DROP TABLE IF EXISTS `repeating_tester`;
/*!50001 DROP VIEW IF EXISTS `repeating_tester`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `repeating_tester` AS SELECT 
 1 AS `StepType`,
 1 AS `TesterStepId`,
 1 AS `ChangeRequestID`,
 1 AS `HandlerUserName`,
 1 AS `StartDate`,
 1 AS `Status`,
 1 AS `EstimatedEndDate`,
 1 AS `EndDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `subsystem_support`
--

DROP TABLE IF EXISTS `subsystem_support`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subsystem_support` (
  `Subsystem` varchar(45) NOT NULL,
  `ResponsibleUserName` varchar(45) NOT NULL,
  PRIMARY KEY (`Subsystem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subsystem_support`
--

LOCK TABLES `subsystem_support` WRITE;
/*!40000 ALTER TABLE `subsystem_support` DISABLE KEYS */;
INSERT INTO `subsystem_support` VALUES ('Class Rooms With Computers','zeev'),('College Website','ido'),('Computer Farm','itay'),('Employee Information Station','ido'),('Laboratory','lior'),('Lecturer Information Station','lior'),('Library System','gilad'),('Moodle System','itayz'),('Student Information Station','itay');
/*!40000 ALTER TABLE `subsystem_support` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor_update`
--

DROP TABLE IF EXISTS `supervisor_update`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supervisor_update` (
  `UpdateId` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestId` int(11) NOT NULL,
  `SupervisorUserName` varchar(45) NOT NULL,
  `Essence` varchar(45) NOT NULL,
  `Update_Date` date NOT NULL,
  `SupervisorFullName` varchar(45) NOT NULL,
  PRIMARY KEY (`UpdateId`),
  UNIQUE KEY `UpdateId_UNIQUE` (`UpdateId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor_update`
--

LOCK TABLES `supervisor_update` WRITE;
/*!40000 ALTER TABLE `supervisor_update` DISABLE KEYS */;
INSERT INTO `supervisor_update` VALUES (1,71,'itay','Appoint execution','2020-01-17','Itay David');
/*!40000 ALTER TABLE `supervisor_update` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tester_step`
--

DROP TABLE IF EXISTS `tester_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tester_step` (
  `TesterStepId` int(11) NOT NULL AUTO_INCREMENT,
  `ChangeRequestId` int(11) NOT NULL,
  `HandlerUserName` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `TesterFailReport` varchar(600) DEFAULT NULL,
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`TesterStepId`),
  UNIQUE KEY `TesterStepId_UNIQUE` (`TesterStepId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_step`
--

LOCK TABLES `tester_step` WRITE;
/*!40000 ALTER TABLE `tester_step` DISABLE KEYS */;
INSERT INTO `tester_step` VALUES (1,1,'ido','CLOSED',NULL,'2019-12-05','2019-12-08','2019-12-07'),(2,2,'itayz','CLOSED',NULL,'2019-12-31','2020-01-01','2020-01-02'),(3,4,'itayz','ACTIVE',NULL,'2019-12-24','2019-12-26',NULL),(5,72,'gilad','CLOSED',NULL,'2019-12-27','2019-12-28','2019-12-28'),(6,73,'ido','CLOSED','','2020-01-01','2020-01-10','2020-01-16'),(7,85,'ido','CLOSED','','2020-01-13','2020-01-20','2020-01-13'),(8,85,'gilad','CLOSED','','2020-01-13','2020-01-20','2020-01-13'),(9,88,'ido','CLOSED','Failed Tests: \n2.  Check requirement coverage\n','2020-01-16','2020-01-23','2020-01-16'),(10,88,'ido','CLOSED','','2020-01-16','2020-01-23','2020-01-16'),(11,89,'ido','CLOSED','','2020-01-16','2020-01-23','2020-01-16'),(12,90,'ido','CLOSED','','2020-01-16','2020-01-23','2020-01-16'),(13,86,'ido','CLOSED','','2020-01-16','2020-01-23','2020-01-16');
/*!40000 ALTER TABLE `tester_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_extension`
--

DROP TABLE IF EXISTS `time_extension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_extension` (
  `TimeExtensionID` int(11) NOT NULL AUTO_INCREMENT,
  `StepID` int(11) NOT NULL,
  `StepType` varchar(45) NOT NULL,
  `OldDate` date NOT NULL,
  `NewDate` date NOT NULL,
  `Reason` varchar(200) DEFAULT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'NEW' COMMENT 'ENUM',
  PRIMARY KEY (`TimeExtensionID`),
  UNIQUE KEY `TimeExtensionID_UNIQUE` (`TimeExtensionID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_extension`
--

LOCK TABLES `time_extension` WRITE;
/*!40000 ALTER TABLE `time_extension` DISABLE KEYS */;
INSERT INTO `time_extension` VALUES (1,4,'Committee','2019-12-30','2020-01-11','I need more time','APPROVED'),(2,5,'Analysis','2019-12-30','2020-01-11','fgdfg','APPROVED'),(3,22,'Analysis','2020-01-16','2020-01-15','1','DENY'),(4,27,'Analysis','2020-01-17','2020-01-18','i need more time','APPROVED'),(5,19,'Execution','2020-01-18','2020-01-18','a','NEW'),(6,34,'Analysis','2020-01-17','2020-01-18','Need more time to this work','CLOSED'),(7,30,'Analysis','2020-01-18','2020-01-19','DDGFDGF','NEW');
/*!40000 ALTER TABLE `time_extension` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `JobDescription` varchar(45) NOT NULL,
  `Permission` varchar(45) NOT NULL,
  `PhoneNumber` varchar(45) NOT NULL,
  `IsLogged` tinyint(1) unsigned zerofill NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserID_UNIQUE` (`UserID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'raviv','1234','Raviv','Komem','ravivkomem@gmail.com','Software Engineering','Student','BASIC_USER','0546848161',0),(2,'lior','1234','Lior','Kauffman','ravivravivraviv@gmail.com','Information Tech','Information Engineer','INFORMATION_ENGINEER','0540123121',0),(3,'itay','1234','Itay','David','itaydavid22@gmail.com','Information Tech','Supervisor','SUPERVISOR','0239872341',0),(4,'lee','1234','Lee','Hugi','leehugi93@gmail.com','Information Tech','Tech Manager','INFORMATION_ENGINEERING_DEPARTMENT_HEAD','1230911821',0),(5,'ido','1234','Ido','Kadosh','idokadosh1@gmail.com','Information Tech','Committee Member','COMMITTEE_MEMBER','3214891123',0),(6,'itayz','1234','Itay','Ziv','itayziv8@gmail.com','Information Tech','Committee Director','COMMITTEE_DIRECTOR','3333333333',0),(7,'nir','1234','Nir','Asolin','nirasolin@gmail.com','Software Engineering','Lecturer','BASIC_USER','2222222222',0),(8,'gilad','1234','Gilad','Eviatar','giladeviatar@gmail.com','Information Tech','Committee Member','COMMITTEE_MEMBER','1111111111',0),(9,'shira','1234','Shira','Noah','shiranoah@gmail.com','Math','Student','BASIC_USER','1212121212',0),(10,'moti','1234','Moti','Ben Hamo','notiben@gmail.com','Math','Worker','BASIC_USER','2121212121',0),(11,'zeev','1234','Zeev','Imber','zeev2@gmail.com','Information Tech','Information Engineer','INFORMATION_ENGINEER','3131313131',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `mergred_steps`
--

/*!50001 DROP VIEW IF EXISTS `mergred_steps`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mergred_steps` AS select 'Analysis' AS `StepType`,`a`.`AnalysisStepID` AS `AnalysisStepID`,`a`.`ChangeRequestID` AS `ChangeRequestID`,`a`.`HandlerUserName` AS `HandlerUserName`,`a`.`StartDate` AS `StartDate`,`a`.`Status` AS `Status`,`a`.`EstimatedEndDate` AS `EstimatedEndDate`,`a`.`EndDate` AS `EndDate` from `analysis_step` `a` union select 'Committee' AS `StepType`,`c`.`CommitteeStepId` AS `CommitteeStepID`,`c`.`ChangeRequestId` AS `ChangeRequestID`,`c`.`HandlerUserName` AS `HandlerUserName`,`c`.`StartDate` AS `StartDate`,`c`.`Status` AS `Status`,`c`.`EstimatedEndDate` AS `EstimatedEndDate`,`c`.`EndDate` AS `EndDate` from `committee_step` `c` union select 'Execution' AS `StepType`,`e`.`ExecutionStepID` AS `ExecutionStepID`,`e`.`ChangeRequestID` AS `ChangeRequestID`,`e`.`HandlerUserName` AS `HandlerUserName`,`e`.`StartDate` AS `StartDate`,`e`.`Status` AS `Status`,`e`.`EstimatedEndDate` AS `EstimatedEndDate`,`e`.`EndDate` AS `EndDate` from `execution_step` `e` union select 'Tester' AS `StepType`,`t`.`TesterStepId` AS `TesterStepId`,`t`.`ChangeRequestId` AS `ChangeRequestID`,`t`.`HandlerUserName` AS `HandlerUserName`,`t`.`StartDate` AS `StartDate`,`t`.`Status` AS `Status`,`t`.`EstimatedEndDate` AS `EstimatedEndDate`,`t`.`EndDate` AS `EndDate` from `tester_step` `t` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `repeating_analysis`
--

/*!50001 DROP VIEW IF EXISTS `repeating_analysis`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `repeating_analysis` AS select 'Analysis' AS `StepType`,`a1`.`AnalysisStepID` AS `AnalysisStepID`,`a1`.`ChangeRequestID` AS `ChangeRequestID`,`a1`.`HandlerUserName` AS `HandlerUserName`,`a1`.`StartDate` AS `StartDate`,`a1`.`Status` AS `Status`,`a1`.`EstimatedEndDate` AS `EstimatedEndDate`,`a1`.`EndDate` AS `EndDate` from `analysis_step` `a1` where exists(select `a2`.`ChangeRequestID` from `analysis_step` `a2` where (`a2`.`ChangeRequestID` = `a1`.`ChangeRequestID`) group by `a2`.`ChangeRequestID` having (count(`a2`.`ChangeRequestID`) >= 2)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `repeating_committee`
--

/*!50001 DROP VIEW IF EXISTS `repeating_committee`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `repeating_committee` AS select 'Committee' AS `StepType`,`c1`.`CommitteeStepId` AS `CommitteeStepID`,`c1`.`ChangeRequestId` AS `ChangeRequestID`,`c1`.`HandlerUserName` AS `HandlerUserName`,`c1`.`StartDate` AS `StartDate`,`c1`.`Status` AS `Status`,`c1`.`EstimatedEndDate` AS `EstimatedEndDate`,`c1`.`EndDate` AS `EndDate` from `committee_step` `c1` where exists(select `c2`.`ChangeRequestId` from `committee_step` `c2` where (`c2`.`ChangeRequestId` = `c1`.`ChangeRequestId`) group by `c2`.`ChangeRequestId` having (count(`c2`.`ChangeRequestId`) >= 2)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `repeating_execution`
--

/*!50001 DROP VIEW IF EXISTS `repeating_execution`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `repeating_execution` AS select 'Execution' AS `StepType`,`e1`.`ExecutionStepID` AS `ExecutionStepID`,`e1`.`ChangeRequestID` AS `ChangeRequestID`,`e1`.`HandlerUserName` AS `HandlerUserName`,`e1`.`StartDate` AS `StartDate`,`e1`.`Status` AS `Status`,`e1`.`EstimatedEndDate` AS `EstimatedEndDate`,`e1`.`EndDate` AS `EndDate` from `execution_step` `e1` where exists(select `e2`.`ChangeRequestID` from `execution_step` `e2` where (`e2`.`ChangeRequestID` = `e1`.`ChangeRequestID`) group by `e2`.`ChangeRequestID` having (count(`e2`.`ChangeRequestID`) >= 2)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `repeating_tester`
--

/*!50001 DROP VIEW IF EXISTS `repeating_tester`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `repeating_tester` AS select 'Testing' AS `StepType`,`t1`.`TesterStepId` AS `TesterStepId`,`t1`.`ChangeRequestId` AS `ChangeRequestID`,`t1`.`HandlerUserName` AS `HandlerUserName`,`t1`.`StartDate` AS `StartDate`,`t1`.`Status` AS `Status`,`t1`.`EstimatedEndDate` AS `EstimatedEndDate`,`t1`.`EndDate` AS `EndDate` from `tester_step` `t1` where exists(select `t2`.`ChangeRequestId` from `tester_step` `t2` where (`t2`.`ChangeRequestId` = `t1`.`ChangeRequestId`) group by `t2`.`ChangeRequestId` having (count(`t2`.`ChangeRequestId`) >= 2)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-18 11:38:58
