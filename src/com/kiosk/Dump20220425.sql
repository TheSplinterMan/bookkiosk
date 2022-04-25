CREATE DATABASE  IF NOT EXISTS `bookkioskdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookkioskdb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: bookkioskdb
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `BookID` int NOT NULL,
  `bookTitle` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `ISNnum` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `checkedOutBy` int DEFAULT '0',
  `reservedBy` int DEFAULT '0',
  PRIMARY KEY (`BookID`),
  KEY `checkedOutBy_idx` (`checkedOutBy`),
  KEY `reservedBy_idx` (`reservedBy`),
  CONSTRAINT `checkedOutBy` FOREIGN KEY (`checkedOutBy`) REFERENCES `univmember` (`id`),
  CONSTRAINT `reservedBy` FOREIGN KEY (`reservedBy`) REFERENCES `univmember` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'The Cat in the Hat','Dr. Suess','978-0-7172-6059-1','Children',NULL,NULL),(2,'Computer Science: An Overview','Gleen Brookshear, Dennis Brylow','9780134875460','Computer Science',NULL,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `univmember`
--

DROP TABLE IF EXISTS `univmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `univmember` (
  `id` int NOT NULL DEFAULT '0',
  `Fname` varchar(45) NOT NULL,
  `Lname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isStaff` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `univmember`
--

LOCK TABLES `univmember` WRITE;
/*!40000 ALTER TABLE `univmember` DISABLE KEYS */;
INSERT INTO `univmember` VALUES (900111111,'Jordan','Doe','password123',0),(900123456,'Tom','Sawyer','lost',0),(900202111,'Michael','Weston','bnotice',0),(900343434,'Kim','Possible','ron',0),(900639032,'Denzell','Moss','password',0),(900999999,'Admin','One','admin',1);
/*!40000 ALTER TABLE `univmember` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-25 14:29:07
