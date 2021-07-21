-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_medical_appointments
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id_appointment` int NOT NULL AUTO_INCREMENT,
  `username_p` char(50) NOT NULL,
  `username_d` char(50) NOT NULL,
  `time_appointment` text NOT NULL,
  `date_appointment` text NOT NULL,
  `description` text NOT NULL,
  `status_appointment` text NOT NULL,
  PRIMARY KEY (`id_appointment`),
  KEY `username_p` (`username_p`),
  KEY `username_d` (`username_d`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`username_p`) REFERENCES `users` (`username`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`username_d`) REFERENCES `doctors` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (6,'joshbrowns','bolbistroganowski','17:30','17/9/21','annual checkup','active'),(7,'joshbrowns','totowolf','11:00','4/8/21','annual prescription of medicine','active'),(8,'joshbrowns','lewishamilton','15:20','18/9/21','acne scars treatment','active'),(10,'hannahbaker','totowolf','12:00','12/7/21','annual checkup','cancelled'),(11,'hannahbaker','lewishamilton','12:00','30/10/21','prescription medicine','cancelled');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `username` char(50) NOT NULL,
  `department` text NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES ('bolbistroganowski','Neurology'),('charlesleclerc','Gynecology'),('giorgosbogdanos','Neurology'),('jameskafetzis','Nephrology'),('jimmynewtron','Gynecology'),('lewishamilton','Dermatology'),('totowolf','Dermatology');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlog` (
  `username` char(50) NOT NULL,
  `property` char(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
INSERT INTO `userlog` VALUES ('hannahbaker','Patient');
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` char(50) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `password` text NOT NULL,
  `property` char(50) NOT NULL,
  `ssn` int NOT NULL,
  `phonenumber` char(10) NOT NULL,
  `address` text NOT NULL,
  `postalcode` int NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('bolbistroganowski','Bolbi','Stroganowski','1234','Doctor',56878964,'6903768278','Feros Hills 457',11223,'bolbi@email.com'),('charlesleclerc','Charles','Leclerc','1234','Doctor',56793480,'683546789','Herilongs Beach 567',11356,'charles@email.com'),('example','example','example','12345','Patient',457896800,'567898786','example',11456,'example@mail.com'),('giorgosbogdanos','Giorgos','Bogdanos','1234','Doctor',679473660,'6904718422','St. Garfields 567',11223,'giorgosb@email.com'),('hannahbaker','Hannah','Baker','1234','Patient',34687696,'697856377','Rodeo Drive 444',11356,'hannah@email.com'),('jameskafetzis','James','Kafetzis','1234','Doctor',54565680,'6903487611','Dominicians 345',11223,'james@email.com'),('jimmynewtron','Jimmy','Newtron','1234','Doctor',45467784,'690457896','Rodeo Drive 456',11356,'newtron@email.com'),('joshbrowns','Josh','Browns','234','Patient',567898750,'698456789','Jeffreson\'s 345',11223,'joshb@email.com'),('lewishamilton','Lewis','Hamilton','1234','Doctor',693785920,'698367856','St. Jekings 130',11356,'lewis@email.com'),('oliviarodrigo','Olivia','Rodrigo','1234','Patient',567876540,'667876785','St. Parks 234',11223,'olivia@email.com'),('peterjohnson','Peter','Johnson','1234','Patient',675498690,'698794768','St. Avenue 45',11356,'peterj@email.com'),('totowolf','Toto','Wolf','1234','Doctor',698736260,'6904839677','St. Kerlings 34',11356,'toto@email.com'),('tylergreens','Tyler','Greens','1234','Patient',569216580,'6903728469','St. Jonas 90',11356,'tyler@email.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19 19:08:09
