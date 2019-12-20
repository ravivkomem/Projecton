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
-- Table structure for table `analysis_report`
--

DROP TABLE IF EXISTS `analysis_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `analysis_report` (
  `AnalysisReportID` int(11) NOT NULL,
  `ChangeRequestID` varchar(45) NOT NULL,
  `Header` varchar(45) NOT NULL,
  `ChangeSpecifications` varchar(45) NOT NULL,
  `Comments` varchar(45) NOT NULL,
  PRIMARY KEY (`AnalysisReportID`,`ChangeRequestID`),
  UNIQUE KEY `AnalysisReportID_UNIQUE` (`AnalysisReportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis_report`
--

LOCK TABLES `analysis_report` WRITE;
/*!40000 ALTER TABLE `analysis_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `analysis_report` ENABLE KEYS */;
UNLOCK TABLES;

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
  `EstimatedEndDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `AnalysisReportHeader` varchar(45) DEFAULT NULL,
  `AnalysisReportSpecification` varchar(45) DEFAULT NULL,
  `AnalysisReportComments` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AnalysisStepID`),
  UNIQUE KEY `AnalysisStepID_UNIQUE` (`AnalysisStepID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analysis_step`
--

LOCK TABLES `analysis_step` WRITE;
/*!40000 ALTER TABLE `analysis_step` DISABLE KEYS */;
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
  `UploadedFiles` varchar(45) NOT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`ChangeRequestID`),
  UNIQUE KEY `ChangeRequestID_UNIQUE` (`ChangeRequestID`),
  KEY `UserName_idx` (`InitiatorUserName`,`HandlerUserName`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `change_request`
--

LOCK TABLES `change_request` WRITE;
/*!40000 ALTER TABLE `change_request` DISABLE KEYS */;
INSERT INTO `change_request` VALUES (1,'raviv','2017-12-19','Moodle','Bad','Make it good','it is not working properly','make the color red','Active','ANALYSIS_SET_TIME','lior','blabla','2020-12-19'),(2,'lee','2016-12-19','Website','Very Bad','Make it better','loading is very slow','make the loading faster','Active','ANALYSIS_APPROVE_TIME','lior','blablabla','2021-12-19'),(3,'ido','2019-12-18','Moodle System','bad','asd','asd','asd','Active','ANALYSIS_WORK','lior','Analysis',NULL),(4,'ido','2019-12-18','College Website','abcdefg','abcd','abcde','abced','Active','EXECUTION_SET_TIME','lior','',NULL),(69,'raviv','2019-12-19','Employee Information Station','dfs','sfd','asdf','fasd','Active','EXECUTION_APPROVE_TIME','lior','sfd',NULL),(70,'raviv','2019-12-19','Employee Information Station','sdaf','afsd','asfd','sdfa','Active','EXECUTION_WORK','ido','afsd',NULL),(71,'raviv','2019-12-19','Employee Information Station','asfdasfd','sadffffdsa','asdffff','sfad','Active','TESTING_WORK','lior','afsd',NULL),(72,'raviv','2019-12-19','Employee Information Station','safdasdf','fasd','fasdafsdfdsa','sdfaasdffdas','Active','TESTING_WORK','lior','fdasafsdfdas',NULL),(73,'raviv','2019-12-19','Employee Information Station','sfdaafdsdasf','fdasfasd','fdasfadsafsd','fdsaadfs','Active','TESTING_WORK','lior','fsdafdasdfas',NULL),(74,'raviv','2019-12-19','Library System','fsdafasdfads','fads','asdfafdsfdas','fdsa','Active','COMMITTEE_WORK','ido','fdsadfsa',NULL),(75,'raviv','2019-12-19','Student Information Station','sfdasdaf','fdsafsda','fadsfsdasdfa','fdsaasdffdasfdas','Active','COMMITTEE_WORK','IDO','fdssadfasfd',NULL),(76,'raviv','2019-12-19','Library System','dfsaafds','asfddsfa','sfdaafsdfdas','sadffasd','Active','ANALAYZER_AUTO_APPOINT','itay','afdssdfasfadsdfa',NULL),(77,'raviv','2019-12-19','Laboratory','asdf','fdsa','asdffdsafdsa','fsdafadsasfdfsda','Active','ANALAYZER_AUTO_APPOINT','itay','fsdafdasfdsa',NULL),(78,'raviv','2019-12-19','Laboratory','sadfa','sddfas','adfsdfas','fsdaadsffda','Active','ANALAYZER_AUTO_APPOINT','lior','sfdasfdsafsda',NULL);
/*!40000 ALTER TABLE `change_request` ENABLE KEYS */;
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
  `employeeId` int(11) NOT NULL,
  `comment` varchar(45) NOT NULL,
  PRIMARY KEY (`commentId`),
  UNIQUE KEY `commentId_UNIQUE` (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_comment`
--

LOCK TABLES `committee_comment` WRITE;
/*!40000 ALTER TABLE `committee_comment` DISABLE KEYS */;
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
  PRIMARY KEY (`CommitteeStepId`),
  UNIQUE KEY `CommitteeStepId_UNIQUE` (`CommitteeStepId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_step`
--

LOCK TABLES `committee_step` WRITE;
/*!40000 ALTER TABLE `committee_step` DISABLE KEYS */;
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
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date NOT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`ExecutionStepID`),
  UNIQUE KEY `ExecutionStepID_UNIQUE` (`ExecutionStepID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `execution_step`
--

LOCK TABLES `execution_step` WRITE;
/*!40000 ALTER TABLE `execution_step` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirements` (
  `ChangeRequestID` int(11) NOT NULL,
  `InitaitorName` varchar(45) DEFAULT NULL,
  `Subsystem` varchar(45) DEFAULT NULL,
  `CurrentState` varchar(45) DEFAULT NULL,
  `ChangeDescription` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `HandlerName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ChangeRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
INSERT INTO `requirements` VALUES (1,'Raviv','Moodle','Bad','Make it Good','Active','Lee'),(2,'Ido','ClassBoost','Delays with video','Add loading buffer','Suspended','Lior');
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
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
  `StartDate` date NOT NULL,
  `EstimatedEndDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `TesterFailReport` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TesterStepId`),
  UNIQUE KEY `TesterStepId_UNIQUE` (`TesterStepId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tester_step`
--

LOCK TABLES `tester_step` WRITE;
/*!40000 ALTER TABLE `tester_step` DISABLE KEYS */;
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
  `StepID` varchar(45) NOT NULL,
  `StepType` varchar(45) NOT NULL,
  `NewDate` varchar(45) NOT NULL,
  `Reason` varchar(45) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`TimeExtensionID`),
  UNIQUE KEY `TimeExtensionID_UNIQUE` (`TimeExtensionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_extension`
--

LOCK TABLES `time_extension` WRITE;
/*!40000 ALTER TABLE `time_extension` DISABLE KEYS */;
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
  `IsLogged` tinyint(3) unsigned zerofill NOT NULL,
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
INSERT INTO `user` VALUES (1,'raviv','1234','Raviv','Komem','ravivkomem@gmail.com','Software Engineering','Student','BASIC_USER','0546848161',000),(2,'lior','1234','Lior','Kauffman','liorkauffman@gmail.com','Software Engineering','Information Engineer','INFORMATION_ENGINEER','0540123121',000),(3,'itay','1234','Itay','David','itaydavid@gmail.com','Industrial Engineering','Supervisor','SUPERVISOR','0239872341',000),(4,'lee','1234','Lee','Hugi','leehugi@gmail.com','Mathmatics','MATAM','INFORMATION_ENGINEERING_DEPARTMENT_HEAD','1230911821',000),(5,'ido','1234','Ido','Kadosh','idokadosh@gmail.com','Electricity','Committee member','COMMITTEE_MEMBER','3214891123',000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-20  9:55:30
