-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: localhost    Database: stream
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `channel_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'Sky Sports Main Event','1.svg'),(2,'Sky Sports Premier','2.svg'),(3,'Sky Sports Football','3.svg'),(4,'BT Sport 1','4.png'),(5,'BT Sport 2','5.png'),(6,'BT Sport 3','6.png'),(7,'BT Sport ESPN','7.png'),(8,'Premier League','8.png');
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clubs` (
  `club_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `image` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`club_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
INSERT INTO `clubs` VALUES (1,'Arsenal','1'),(2,'Bournemouth','2'),(3,'Brighton','3'),(4,'Burnley','4'),(5,'Chelsea','5'),(6,'Crystal Palace','6'),(7,'Everton','7'),(8,'Huddersfield Town','8'),(9,'Leicester City','9'),(10,'Liverpool','10'),(11,'Manchester City','11'),(12,'Manchester United','12'),(13,'Newcastle United','13'),(14,'Southampton','14'),(15,'Stoke City','15'),(16,'Swansea City','16'),(17,'Tottenham Hotspur','17'),(18,'Watford','18'),(19,'West Bromwich Albion','19'),(20,'West Ham United','20'),(21,'Barcelona','901'),(22,'Athletico Madrid','901'),(23,'Real Madrid','901'),(24,'Valencia','901'),(25,'Villarreal','901'),(26,'Sevilla','901'),(27,'Girona','901'),(28,'Real Betis','901'),(29,'Getafe CF','901'),(30,'Celta Vigo','901'),(31,'Eibar','901'),(32,'Leganes','901'),(33,'Athletico Bilbao','901'),(34,'Espanyol','901'),(35,'Real Sociedad','901'),(36,'Alaves','901'),(37,'Levante','901'),(38,'Las Palmes','901'),(39,'Deportivo','901'),(40,'Malaga','901');
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `home` int(11) NOT NULL,
  `away` int(11) DEFAULT NULL,
  `ko_time` varchar(10) NOT NULL,
  `league_id` int(11) DEFAULT NULL,
  `ko_time_time` datetime DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `game_id_UNIQUE` (`game_id`),
  KEY `fk_game_league1_idx` (`league_id`),
  KEY `fk_game_club2_idx` (`home`),
  KEY `fk_game_club1_idx` (`away`),
  CONSTRAINT `fk_game_club1` FOREIGN KEY (`away`) REFERENCES `clubs` (`club_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_club2` FOREIGN KEY (`home`) REFERENCES `clubs` (`club_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_league1` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`league_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,6,10,'12:30',1,'2018-03-31 12:30:00'),(2,3,9,'3:00',1,'2018-03-31 15:00:00'),(3,12,16,'3:00',1,'2018-03-31 15:00:00'),(4,13,8,'3:00',1,'2018-03-31 15:00:00'),(5,18,2,'3:00',1,'2018-03-31 15:00:00'),(6,19,4,'3:00',1,'2018-03-31 15:00:00'),(7,20,14,'3:00',1,'2018-03-31 15:00:00'),(8,7,11,'3:00',1,'2018-03-31 15:00:00'),(9,33,30,'4:15',2,'2018-03-31 16:15:00'),(10,27,37,'1:00',2,'2018-03-31 13:00:00'),(11,38,23,'6:30',2,'2018-03-31 18:30:00'),(12,26,21,'8:45',2,'2018-03-31 20:45:00'),(13,33,39,'8:45',2,'2018-03-31 20:45:00'),(14,31,35,'6:30',2,'2018-03-31 18:30:00'),(15,34,36,'12:00',2,'2018-03-31 12:00:00'),(16,32,24,'6:15',2,'2018-03-31 18:15:00'),(17,40,25,'6:30',2,'2018-03-31 18:30:00'),(18,29,28,'9:00',2,'2018-03-31 21:00:00');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leagues`
--

DROP TABLE IF EXISTS `leagues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leagues` (
  `league_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`league_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leagues`
--

LOCK TABLES `leagues` WRITE;
/*!40000 ALTER TABLE `leagues` DISABLE KEYS */;
INSERT INTO `leagues` VALUES (1,'Premier League','1.png'),(2,'Liga Santander','2.png');
/*!40000 ALTER TABLE `leagues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream_channel`
--

DROP TABLE IF EXISTS `stream_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_channel` (
  `stream_id` int(11) NOT NULL,
  `channel_id` int(11) NOT NULL,
  PRIMARY KEY (`stream_id`,`channel_id`),
  KEY `stream_channel2_idx` (`channel_id`),
  CONSTRAINT `stream_channel1` FOREIGN KEY (`stream_id`) REFERENCES `streams` (`stream_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stream_channel2` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`channel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stream_channel`
--

LOCK TABLES `stream_channel` WRITE;
/*!40000 ALTER TABLE `stream_channel` DISABLE KEYS */;
INSERT INTO `stream_channel` VALUES (17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,4),(26,4),(27,4),(28,4),(29,4),(30,4),(31,4),(32,4),(1,8),(2,8),(3,8),(4,8),(5,8),(6,8),(7,8),(8,8);
/*!40000 ALTER TABLE `stream_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `streams`
--

DROP TABLE IF EXISTS `streams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `streams` (
  `stream_id` int(11) NOT NULL,
  `url` varchar(70) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL,
  `vote` int(11) NOT NULL,
  PRIMARY KEY (`stream_id`),
  KEY `fk_game_streams1_idx` (`game_id`),
  CONSTRAINT `fk_game_streams1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `streams`
--

LOCK TABLES `streams` WRITE;
/*!40000 ALTER TABLE `streams` DISABLE KEYS */;
INSERT INTO `streams` VALUES (1,'http://premierleague-lives.com/watch-free-1/',1,12),(2,'http://premierleague-lives.com/watch-free-1/',2,9),(3,'http://premierleague-lives.com/watch-free-1/',3,7),(4,'http://premierleague-lives.com/watch-free-1/',4,9),(5,'http://premierleague-lives.com/watch-free-1/',5,6),(6,'http://premierleague-lives.com/watch-free-1/',6,8),(7,'http://premierleague-lives.com/watch-free-1/',7,9),(8,'http://premierleague-lives.com/watch-free-1/',8,4),(9,'https://www.warpfootball.com/',1,0),(10,'https://www.warpfootball.com/',2,0),(11,'https://www.warpfootball.com/',3,0),(12,'https://www.warpfootball.com/',4,0),(13,'https://www.warpfootball.com/',5,0),(14,'https://www.warpfootball.com/',6,0),(15,'https://www.warpfootball.com/',7,0),(16,'https://www.warpfootball.com/',8,0),(17,'http://cricfree.sc/sky-sports-premier-league-live-streaming',1,0),(18,'http://cricfree.sc/sky-sports-premier-league-live-streaming',2,0),(19,'http://cricfree.sc/sky-sports-premier-league-live-streaming',3,0),(20,'http://cricfree.sc/sky-sports-premier-league-live-streaming',4,0),(21,'http://cricfree.sc/sky-sports-premier-league-live-streaming',5,0),(22,'http://cricfree.sc/sky-sports-premier-league-live-streaming',6,0),(23,'http://cricfree.sc/sky-sports-premier-league-live-streaming',7,0),(24,'http://cricfree.sc/sky-sports-premier-league-live-streaming',8,0),(25,'http://www.ronaldo7.net/video/watch-football-live.html',1,0),(26,'http://www.ronaldo7.net/video/watch-football-live.html',2,0),(27,'http://www.ronaldo7.net/video/watch-football-live.html',3,0),(28,'http://www.ronaldo7.net/video/watch-football-live.html',4,0),(29,'http://www.ronaldo7.net/video/watch-football-live.html',5,0),(30,'http://www.ronaldo7.net/video/watch-football-live.html',6,0),(31,'http://www.ronaldo7.net/video/watch-football-live.html',7,0),(32,'http://www.ronaldo7.net/video/watch-football-live.html',8,0),(33,'http://www.eplsite.com/',1,0),(34,'http://www.eplsite.com/',2,0),(35,'http://www.eplsite.com/',3,0),(36,'http://www.eplsite.com/',4,0),(37,'http://www.eplsite.com/',5,0),(38,'http://www.eplsite.com/',6,0),(39,'http://www.eplsite.com/',7,0),(40,'http://www.eplsite.com/',8,0),(41,'http://www.hotstar.com/sports/football',1,0),(42,'http://www.hotstar.com/sports/football',2,0),(43,'http://www.hotstar.com/sports/football',3,0),(44,'http://www.hotstar.com/sports/football',4,0),(45,'http://www.hotstar.com/sports/football',5,0),(46,'http://www.hotstar.com/sports/football',6,0),(47,'http://www.hotstar.com/sports/football',7,0),(48,'http://www.hotstar.com/sports/football',8,0),(49,'http://neolive.info/',1,0),(50,'http://neolive.info/',2,0),(51,'http://neolive.info/',3,0),(52,'http://neolive.info/',4,0),(53,'http://neolive.info/',5,0),(54,'http://neolive.info/',6,0),(55,'http://neolive.info/',7,0),(56,'http://neolive.info/',8,0),(57,'http://soccerlegacy.net/premier-league-live-stream-1/',1,0),(58,'http://soccerlegacy.net/premier-league-live-stream-1/',2,0),(59,'http://soccerlegacy.net/premier-league-live-stream-1/',3,0),(60,'http://soccerlegacy.net/premier-league-live-stream-1/',4,0),(61,'http://soccerlegacy.net/premier-league-live-stream-1/',5,0),(62,'http://soccerlegacy.net/premier-league-live-stream-1/',6,0),(63,'http://soccerlegacy.net/premier-league-live-stream-1/',7,0),(64,'http://soccerlegacy.net/premier-league-live-stream-1/',8,0),(65,'http://live-premierleague.com/games/',1,0),(66,'http://live-premierleague.com/games/',2,0),(67,'http://live-premierleague.com/games/',3,0),(68,'http://live-premierleague.com/games/',4,0),(69,'http://live-premierleague.com/games/',5,0),(70,'http://live-premierleague.com/games/',6,0),(71,'http://live-premierleague.com/games/',7,0),(72,'http://live-premierleague.com/games/',8,0),(73,'https://www.livesoccertv.com/competitions/england/premier-league/',1,0),(74,'https://www.livesoccertv.com/competitions/england/premier-league/',2,0),(75,'https://www.livesoccertv.com/competitions/england/premier-league/',3,0),(76,'https://www.livesoccertv.com/competitions/england/premier-league/',4,0),(77,'https://www.livesoccertv.com/competitions/england/premier-league/',5,0),(78,'https://www.livesoccertv.com/competitions/england/premier-league/',6,0),(79,'https://www.livesoccertv.com/competitions/england/premier-league/',7,0),(80,'https://www.livesoccertv.com/competitions/england/premier-league/',8,0),(81,'http://soccerlegacy.net/liga-bbva-live-stream/',9,0),(82,'http://soccerlegacy.net/liga-bbva-live-stream/',10,0),(83,'http://soccerlegacy.net/liga-bbva-live-stream/',11,0),(84,'http://soccerlegacy.net/liga-bbva-live-stream/',12,0),(85,'http://soccerlegacy.net/liga-bbva-live-stream/',13,0),(86,'http://soccerlegacy.net/liga-bbva-live-stream/',14,0),(87,'http://soccerlegacy.net/liga-bbva-live-stream/',15,0),(88,'http://soccerlegacy.net/liga-bbva-live-stream/',16,0),(89,'http://soccerlegacy.net/liga-bbva-live-stream/',17,0),(90,'http://soccerlegacy.net/liga-bbva-live-stream/',18,0),(91,'https://www.warpfootball.com/',9,0),(92,'https://www.warpfootball.com/',10,0),(93,'https://www.warpfootball.com/',11,0),(94,'https://www.warpfootball.com/',12,0),(95,'https://www.warpfootball.com/',13,0),(96,'https://www.warpfootball.com/',14,0),(97,'https://www.warpfootball.com/',15,0),(98,'https://www.warpfootball.com/',16,0),(99,'https://www.warpfootball.com/',17,0),(100,'https://www.warpfootball.com/',18,0),(101,'http://neolive.info/',9,0),(102,'http://neolive.info/',10,0),(103,'http://neolive.info/',11,0),(104,'http://neolive.info/',12,0),(105,'http://neolive.info/',13,0),(106,'http://neolive.info/',14,0),(107,'http://neolive.info/',15,0),(108,'http://neolive.info/',16,0),(109,'http://neolive.info/',17,0),(110,'http://neolive.info/',18,0),(111,'http://www.ronaldo7.net/video/watch-football-live.html',9,0),(112,'http://www.ronaldo7.net/video/watch-football-live.html',10,0),(113,'http://www.ronaldo7.net/video/watch-football-live.html',11,0),(114,'http://www.ronaldo7.net/video/watch-football-live.html',12,0),(115,'http://www.ronaldo7.net/video/watch-football-live.html',13,0),(116,'http://www.ronaldo7.net/video/watch-football-live.html',14,0),(117,'http://www.ronaldo7.net/video/watch-football-live.html',15,0),(118,'http://www.ronaldo7.net/video/watch-football-live.html',16,0),(119,'http://www.ronaldo7.net/video/watch-football-live.html',17,0),(120,'http://www.ronaldo7.net/video/watch-football-live.html',18,0);
/*!40000 ALTER TABLE `streams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-30 16:11:58
