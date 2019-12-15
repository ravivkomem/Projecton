-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: icm
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
-- Table structure for table `change_request`
--

DROP TABLE IF EXISTS `change_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `change_request` (
  `ChangeRequestID` int(11) NOT NULL,
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
  KEY `UserName_idx` (`InitiatorUserName`,`HandlerUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `change_request`
--

LOCK TABLES `change_request` WRITE;
/*!40000 ALTER TABLE `change_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `change_request` ENABLE KEYS */;
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
INSERT INTO `user` VALUES (1,'raviv','1234','Raviv','Komem','ravivkomem@gmail.com','Software Engineering','Student','BASIC_USER','0546848161'),(2,'lior','1234','Lior','Kauffman','liorkauffman@gmail.com','Software Engineering','Information Engineer','INFORMATION_ENGINEER','0540123121'),(3,'itay','1234','Itay','Daviv','itaydavid@gmail.com','Industrial Engineering','Supervisor','SUPERVISOR','0239872341'),(4,'lee','1234','Lee','Hogi','leehogi@gmail.com','Mathmatics','Worker','BASIC_USER','1230911821'),(5,'ido','1234','Ido','Kadosh','idokadosh@gmail.com','Electricity','Committee member','COMMITTE_MEMBER','3214891123');
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

-- Dump completed on 2019-12-15 21:14:16
