-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: stream
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clubs` (
  `club_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `image` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`club_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
INSERT INTO `clubs` VALUES (1,'Arsenal','1.svg'),(2,'Bournemouth','2.svg'),(3,'Brighton','3.svg'),(4,'Burnley','0'),(5,'Chelsea','0'),(6,'Crystal Palace','0'),(7,'Everton','0'),(8,'Huddersfield','0'),(9,'Leicester','0'),(10,'Liverpool','0'),(11,'Manchester City','0'),(12,'Manchester United','0'),(13,'Newcastle','0'),(14,'Southampton','0'),(15,'Stoke','0'),(16,'Swansea','0'),(17,'Tottenham','0'),(18,'Watford','0'),(19,'West Brom','0'),(20,'West Ham','0'),(21,'Lazio','0'),(23,'Lazioa','0'),(24,'Deportivo ','0'),(25,'Malaga','0'),(26,'Alaves','0'),(27,'Getafe','0'),(28,'Celta Vigo','0'),(29,'Sevilla','0'),(30,'Real Betis','0'),(31,'Eibar','0'),(32,'Barcelona','0'),(33,'Leganes','0'),(34,'Levante','0'),(35,'Las Palmas','0'),(36,'Real Madrid','0'),(37,'Atletico Madrid','0'),(38,'Real Sociedad','0'),(39,'Girona','0'),(40,'Valencia','0'),(41,'Espanyol','0'),(42,'Athletic Bilbao','0'),(44,'Villarreal','0'),(45,'PSG','0'),(46,'Monaco','0'),(47,'Marseille','0'),(48,'Lyon','0'),(49,'Rennes','0'),(50,'Montpellier','0'),(51,'OGC Nice','0'),(52,'Nantes','0'),(53,'St-Etienne','0'),(54,'Guingamp','0'),(55,'Dijon FCO','0'),(56,'Bordeaux','0'),(57,'Angers','0'),(58,'Caen','0'),(59,'Amiens SC','0'),(60,'Strasbourg','0'),(61,'Toulouse','0'),(62,'Troyes','0'),(63,'LOSC','0'),(64,'Metz','0'),(65,'Benevento','0'),(66,'Juventus','0'),(67,'Napoli','0'),(68,'Roma','0'),(69,'Inter Milan','0'),(71,'Milan','0'),(72,'Sampdoria','0'),(73,'Atalanta','0'),(74,'Fiorentina','0'),(75,'Torino','0'),(76,'Bologna','0'),(77,'Genoa','0'),(78,'Udinese','0'),(79,'Chievo','0'),(80,'Cagliari','0'),(81,'Sassuolo','0'),(82,'SPAL','0'),(83,'Crotone','0'),(84,'Verona','0'),(86,'Bayern Munich','0'),(87,'Schalke 04','0'),(88,'Dortmund','0'),(89,'RB Leipzig','0'),(90,'Bayer Leverkusen','0'),(91,'Eintracht','0'),(92,'Hoffenheim','0'),(93,'VfB Stuttgart','0'),(94,'Monchengladbach','0'),(95,'FC Augsburg','0'),(96,'Hertha','0'),(97,'Werder Bremen','0'),(98,'Hannover 96','0'),(99,'SC Freiburg','0'),(100,'Wolfsburg','0'),(101,'Mainz','0'),(102,'FC Koln','0'),(103,'Hamburger SV','0'),(104,'Aston Villa','0'),(105,'Cardiff','0'),(106,'Bristol City','0'),(107,'Birmingham','0'),(108,'Burton Albion','0'),(109,'Hull','0'),(110,'Fulham','0'),(111,'Reading','0'),(112,'Ipswich','0'),(113,'Barnsley','0'),(114,'Nottingham Forest','0'),(115,'Brentford','0'),(116,'Preston','0'),(117,'Leeds','0'),(118,'QPR','0'),(119,'Sheffield Wednesday','0'),(120,'Sheffield United','0'),(121,'Middlesbrough','0'),(122,'Sunderland','0'),(123,'Norwich','0'),(124,'Bolton','0'),(125,'Millwall','0'),(126,'Bayern Munchen','0'),(127,'Wolverhampton','0'),(128,'Derby','0'),(129,'CSKA Moscow','0'),(130,'Sporting','0'),(131,'FC Salzburg ','0');
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-12 19:22:21
