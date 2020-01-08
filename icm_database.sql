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
  `AnalysisReportDuration` varchar(100) DEFAULT NULL,
  `AnalysisReportConstraints` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`AnalysisStepID`),
  UNIQUE KEY `AnalysisStepID_UNIQUE` (`AnalysisStepID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis_step`
--

LOCK TABLES `analysis_step` WRITE;
/*!40000 ALTER TABLE `analysis_step` DISABLE KEYS */;
INSERT INTO `analysis_step` VALUES (1,1,'lior','2019-11-19','CLOSED','2019-11-26','2019-11-25','moodle changing','make the moodle beter','a','2 week','aa'),(2,2,'itay','2019-11-19','CLOSED','2019-11-25','2019-11-26','website changing','fix a bug ','b','2 week','bb'),(3,3,'itayz','2019-12-18','CLOSED','2019-12-19','2019-12-19','Moodle System changing','make it better','c','5 days','cc'),(4,4,'ido','2019-12-18','CLOSED','2019-12-19','2019-12-18','College Website changing ','fix the problem','d','6 days','dd'),(5,5,'itayz','2019-12-19','CLOSED','2019-12-21','2019-12-20','Employee Station ','fix a bug ','e','4 days','ee'),(6,6,'gilad','2019-12-19','CLOSED','2019-12-21','2019-12-22','Employee Station ','make it better','f','4 days','ff'),(7,71,'zeev','2019-12-20','CLOSED','2019-12-23','2019-12-23','Employee Station ','fix the problem','g','3 days','gg'),(8,72,'lior','2019-12-20','CLOSED','2019-12-22','2019-12-21','Employee Station ','fix a bug ','h','2 days','hh'),(9,73,'itay','2019-12-20','CLOSED','2019-12-22','2019-12-23','Employee Station ','fix the problem','i','5 days','ii'),(10,74,'ido','2019-12-21','CLOSED','2019-12-22','2019-12-22','Library System changing','make it better','j','3 days','jj'),(11,75,'ido','2019-12-22','CLOSED','2019-12-23','2019-12-23','Student Information Station changing','fix the problem','k','3 days','kk'),(12,76,'lior','2019-12-23','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,77,'itay','2019-12-23','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,78,'lior','2019-12-23','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,79,'ido','2019-12-24','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,80,'itay','2019-12-24','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,81,'itay','2019-12-29','ACTIVE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,82,'ido','2019-12-24','CLOSED','2019-12-25','2019-12-26','Moodle System changing','fix a bug ','r','3 days','rr');
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
  `SelectedSubsystem` varchar(45) NOT NULL,
  `CurrentStateDescription` varchar(45) NOT NULL,
  `DesiredChangeDescription` varchar(45) NOT NULL,
  `DesiredChangeExplanation` varchar(45) NOT NULL,
  `DesiredChangeComments` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `CurrentStep` varchar(45) NOT NULL,
  `HandlerUserName` varchar(45) NOT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`ChangeRequestID`),
  UNIQUE KEY `ChangeRequestID_UNIQUE` (`ChangeRequestID`),
  KEY `UserName_idx` (`InitiatorUserName`,`HandlerUserName`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `change_request`
--

LOCK TABLES `change_request` WRITE;
/*!40000 ALTER TABLE `change_request` DISABLE KEYS */;
INSERT INTO `change_request` VALUES (1,'raviv','2019-11-19','Moodle','Bad','Make it good','it is not working properly','make the color red','CLOSED','CLOSING_STEP','lior','2019-12-07'),(2,'lee','2019-11-19','Website','Very Bad','Make it better','loading is very slow','make the loading faster','CLOSED','CLOSING_STEP','-','2019-01-02'),(3,'ido','2019-12-18','Moodle System','bad','asd','asd','asd','DENIED','DENY_STEP','lior','2019-12-22'),(4,'ido','2019-12-18','College Website','abcdefg','abcd','abcde','abced','ACTIVE','TESTING_WORK','ido',NULL),(5,'raviv','2019-12-19','Employee Information Station','dfs','sfd','asdf','fasd','SUSPEND','COMMITTEE_WORK','lior',NULL),(6,'raviv','2019-12-19','Employee Information Station','sdaf','afsd','asfd','sdfa','ACTIVE','EXECUTION_WORK','ido',NULL),(71,'raviv','2019-12-20','Employee Information Station','asfdasfd','sadffffdsa','asdffff','sfad','ACTIVE','EXECUTION_LEADEAR_SUPERVISOR_APPOINT','',NULL),(72,'raviv','2019-12-20','Employee Information Station','safdasdf','fasd','fasdafsdfdsa','sdfaasdffdas','CLOSED','CLOSING_STEP','lior','2019-12-30'),(73,'raviv','2019-12-20','Employee Information Station','sfdaafdsdasf','fdasfasd','fdasfadsafsd','fdsaadfs','ACTIVE','TESTING_WORK','lior',NULL),(74,'raviv','2019-12-21','Library System','fsdafasdfads','fads','asdfafdsfdas','fdsa','DENIED','Deny_Step','itay','2019-12-23'),(75,'raviv','2019-12-22','Student Information Station','sfdasdaf','fdsafsda','fadsfsdasdfa','fdsaasdffdasfdas','ACTIVE','COMMITTEE_WORK','ido',NULL),(76,'raviv','2019-12-23','Library System','dfsaafds','asfddsfa','sfdaafsdfdas','sadffasd','ACTIVE','ANALAYZER_AUTO_APPOINT','itay',NULL),(77,'raviv','2019-12-23','Laboratory','asdf','fdsa','asdffdsafdsa','fsdafadsasfdfsda','ACTIVE','ANALAYZER_AUTO_APPOINT','itay',NULL),(78,'raviv','2019-12-23','Laboratory','sadfa','sddfas','adfsdfas','fsdaadsffda','ACTIVE','ANALAYZER_AUTO_APPOINT','lior',NULL),(79,'raviv','2019-12-24','Class Rooms With Computers','sdf','sdffds','fdssdf','fds','ACTIVE','ANALAYZER_AUTO_APPOINT','itayz',NULL),(80,'lee','2019-12-24','Moodle System','a','a','a','','ACTIVE','ANALAYZER_AUTO_APPOINT','lior',NULL),(81,'raviv','2019-12-29','Moodle System','dsfdsf','dfsd','sfdsfdsf','sdffsddfs','ACTIVE','ANALAYZER_AUTO_APPOINT','itay',NULL),(82,'itayz','2019-12-24','Moodle System','aaa','aaa\naaa\naaa','aaa','','ACTIVE','COMMITTEE_WORK','itayz',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `closing_step`
--

LOCK TABLES `closing_step` WRITE;
/*!40000 ALTER TABLE `closing_step` DISABLE KEYS */;
INSERT INTO `closing_step` VALUES (1,1,'2019-12-07','2019-12-07','CLOSED'),(2,2,'2020-01-02','2020-01-02','CLOSED'),(3,3,'2019-12-21','2019-12-22','CLOSED'),(4,72,'2019-12-28','2019-12-30','CLOSED');
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
  `requestId` int(11) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`commentId`),
  UNIQUE KEY `commentId_UNIQUE` (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_comment`
--

LOCK TABLES `committee_comment` WRITE;
/*!40000 ALTER TABLE `committee_comment` DISABLE KEYS */;
INSERT INTO `committee_comment` VALUES (1,1,'ido','need to approve'),(2,1,'gilad','it\'s important'),(3,2,'gilad','approve'),(4,3,'ido','need to deny'),(5,4,'ido','good idea'),(6,71,'ido','good idea'),(7,72,'gilad','important request');
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
  PRIMARY KEY (`CommitteeStepId`),
  UNIQUE KEY `CommitteeStepId_UNIQUE` (`CommitteeStepId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_step`
--

LOCK TABLES `committee_step` WRITE;
/*!40000 ALTER TABLE `committee_step` DISABLE KEYS */;
INSERT INTO `committee_step` VALUES (1,1,'itayz','2019-11-25','2019-11-30','2019-12-02','CLOSE'),(2,2,'gilad','2019-11-26','2019-11-30','2019-12-01','CLOSED'),(3,3,'zeev','2019-12-19','2019-12-26','2019-12-21','CLOSED'),(4,4,'lior','2019-12-19','2019-12-26','2019-12-20','CLOSED'),(5,5,'ido','2019-12-20','2019-12-27',NULL,'SUSPEND'),(6,6,'gilad','2019-12-22','2019-12-29','2019-12-25','CLOSED'),(7,71,'ido','2019-12-23','2019-12-25','2019-12-24','CLOSED'),(8,72,'itayz','2019-12-21','2019-12-28','2019-12-25','CLOSED'),(9,73,'gilad','2019-12-23','2019-12-30','2019-12-26','CLOSED'),(10,74,'zeev','2019-12-22','2019-12-29','2019-12-23','CLOSED'),(11,75,'ido','2019-12-23','2019-12-30',NULL,'ACTIVE'),(12,82,'itayz','2019-12-26','2020-01-02',NULL,'ACTIVE');
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
  PRIMARY KEY (`ExecutionStepID`),
  UNIQUE KEY `ExecutionStepID_UNIQUE` (`ExecutionStepID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_step`
--

LOCK TABLES `execution_step` WRITE;
/*!40000 ALTER TABLE `execution_step` DISABLE KEYS */;
INSERT INTO `execution_step` VALUES (1,2,'itay','CLOSED','2019-12-01','2019-12-31','2019-12-31'),(2,1,'lior','CLOSED','2019-12-02','2019-12-04','2019-12-05'),(3,4,'ido','CLOSED','2019-12-20','2019-12-25','2019-12-24'),(4,6,'gilad','ACTIVE','2019-12-25','2019-01-01',NULL),(5,73,'gilad','CLOSED','2019-12-26','2020-01-01','2020-01-01'),(6,71,'lior','ACTIVE','2019-12-24','2019-12-30',NULL),(7,72,'zeev','CLOSED','2019-12-25','2019-12-28','2019-12-27');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'79','jpg'),(2,'80','png'),(3,'81','jpg');
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
  `TesterFailReport` varchar(45) DEFAULT NULL,
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`TesterStepId`),
  UNIQUE KEY `TesterStepId_UNIQUE` (`TesterStepId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_step`
--

LOCK TABLES `tester_step` WRITE;
/*!40000 ALTER TABLE `tester_step` DISABLE KEYS */;
INSERT INTO `tester_step` VALUES (1,1,'ido','CLOSED',NULL,'2019-12-05','2019-12-08','2019-12-07'),(2,2,'itayz','CLOSED',NULL,'2019-12-31','2020-01-01','2020-01-02'),(3,4,'itayz','ACTIVE',NULL,'2019-12-24','2019-12-26',NULL),(5,72,'gilad','CLOSED',NULL,'2019-12-27','2019-12-28','2019-12-28'),(6,73,'ido','ACTIVE',NULL,'2020-01-01',NULL,NULL);
/*!40000 ALTER TABLE `tester_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_extension`
--

DROP TABLE IF EXISTS `time_extension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_extension` (
  `TimeExtensionID` int(11) NOT NULL,
  `StepID` int(11) NOT NULL,
  `StepType` varchar(45) NOT NULL,
  `OldDate` date NOT NULL,
  `NewDate` date NOT NULL,
  `Reason` varchar(45) DEFAULT NULL,
  `Status` varchar(45) NOT NULL DEFAULT 'NEW' COMMENT 'ENUM',
  PRIMARY KEY (`TimeExtensionID`),
  UNIQUE KEY `TimeExtensionID_UNIQUE` (`TimeExtensionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_extension`
--

LOCK TABLES `time_extension` WRITE;
/*!40000 ALTER TABLE `time_extension` DISABLE KEYS */;
INSERT INTO `time_extension` VALUES (1,4,'Committee','2019-12-30','2020-01-11','I need more time','APPROVED'),(2,5,'Analysis','2019-12-30','2020-01-11','fgdfg','APPROVED');
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
INSERT INTO `user` VALUES (1,'raviv','1234','Raviv','Komem','ravivkomem@gmail.com','Software Engineering','Student','BASIC_USER','0546848161',0),(2,'lior','1234','Lior','Kauffman','ravivravivraviv@gmail.com','Information Tech','Information Engineer','INFORMATION_ENGINEER','0540123121',0),(3,'itay','1234','Itay','David','itaydavid22@gmail.com','Information Tech','Supervisor','SUPERVISOR','0239872341',0),(4,'lee','1234','Lee','Hugi','leehugi93@gmail.com','Information Tech','Tech Manager','INFORMATION_ENGINEERING_DEPARTMENT_HEAD','1230911821',0),(5,'ido','1234','Ido','Kadosh','idokadosh@gmail.com','Information Tech','Committee Member','COMMITTEE_MEMBER','3214891123',0),(6,'itayz','1234','Itay','Ziv','itayziv@gmail.com','Information Tech','Committee Director','COMMITTEE_DIRECTOR','3333333333',0),(7,'nir','1234','Nir','Asolin','nirasolin@gmail.com','Software Engineering','Lecturer','BASIC_USER','2222222222',0),(8,'gilad','1234','Gilad','Eviatar','giladeviatar@gmail.com','Information Tech','Committee Member','COMMITTEE_MEMBER','1111111111',0),(9,'shira','1234','Shira','Noah','shiranoah@gmail.com','Math','Student','BASIC_USER','1212121212',0),(10,'moti','1234','Moti','Ben Hamo','notiben@gmail.com','Math','Worker','BASIC_USER','2121212121',0),(11,'zeev','1234','Zeev','Imber','zeev2@gmail.com','Information Tech','Information Engineer','INFORMATION_ENGINEER','3131313131',0);
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

-- Dump completed on 2020-01-08 15:40:33
