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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `permissions` int(11) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'mrknees@streams.com','Mrdonkey97',1);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

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
  `club_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `image` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`club_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
INSERT INTO `clubs` VALUES (1,'Arsenal','1'),(2,'Bournemouth','2'),(3,'Brighton','3'),(4,'Burnley','4'),(5,'Chelsea','5'),(6,'Crystal Palace','6'),(7,'Everton','7'),(8,'Huddersfield','8'),(9,'Leicester','9'),(10,'Liverpool','10'),(11,'Manchester City','11'),(12,'Manchester United','12'),(13,'Newcastle','13'),(14,'Southampton','14'),(15,'Stoke','15'),(16,'Swansea','16'),(17,'Tottenham','17'),(18,'Watford','18'),(19,'West Brom','19'),(20,'West Ham','20'),(21,'Lazio','909'),(23,'Lazioa','909');
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
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
  `league_id` int(11) DEFAULT NULL,
  `ko_time` datetime DEFAULT NULL,
  `visable` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `game_id_UNIQUE` (`game_id`),
  KEY `fk_game_league1_idx` (`league_id`),
  KEY `fk_game_clubs1_idx` (`home`),
  KEY `fk_game_clubs2_idx` (`away`),
  CONSTRAINT `fk_game_clubs1` FOREIGN KEY (`home`) REFERENCES `clubs` (`club_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_clubs2` FOREIGN KEY (`away`) REFERENCES `clubs` (`club_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_league1` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`league_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_cat_gen_stream`
--

DROP TABLE IF EXISTS `gen_cat_gen_stream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_cat_gen_stream` (
  `gen_stream_id` int(11) NOT NULL,
  `gen_stream_cat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_stream_id`),
  KEY `fk_gen_stream_cat_id_idx` (`gen_stream_cat_id`),
  CONSTRAINT `fk_gen_stream_cat_id` FOREIGN KEY (`gen_stream_cat_id`) REFERENCES `generic_stream_cat` (`generic_stream_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gen_stream_id` FOREIGN KEY (`gen_stream_id`) REFERENCES `gen_streams` (`gen_stream_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_cat_gen_stream`
--

LOCK TABLES `gen_cat_gen_stream` WRITE;
/*!40000 ALTER TABLE `gen_cat_gen_stream` DISABLE KEYS */;
INSERT INTO `gen_cat_gen_stream` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(22,1),(12,2),(13,2),(14,2),(23,2),(8,3),(9,3),(10,3),(11,3),(24,3),(15,4),(16,4),(17,4),(25,4),(18,5),(19,5),(26,5),(20,6),(21,6),(27,6),(36,6),(37,6),(28,7),(38,7),(39,7),(29,8),(40,8),(41,8),(42,8),(43,8),(30,9),(44,9),(45,9),(46,9),(47,9),(48,9),(31,10),(49,10),(50,10),(51,10),(52,10),(32,11),(53,11),(54,11),(55,11),(56,11),(57,11),(33,12),(58,12),(59,12),(60,12),(34,13),(61,13),(62,13),(63,13),(64,13),(35,14),(65,14),(66,14),(67,14),(68,14),(69,14);
/*!40000 ALTER TABLE `gen_cat_gen_stream` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_streams`
--

DROP TABLE IF EXISTS `gen_streams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_streams` (
  `gen_stream_id` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `vote` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_stream_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_streams`
--

LOCK TABLES `gen_streams` WRITE;
/*!40000 ALTER TABLE `gen_streams` DISABLE KEYS */;
INSERT INTO `gen_streams` VALUES (1,'http://premierleague-lives.com/watch-free-1/',0),(2,'https://www.warpfootball.com/',0),(3,'http://cricfree.sc/sky-sports-premier-league-live-streaming',0),(4,'http://live-premierleague.com/games/',0),(5,'https://www.livesoccertv.com/competitions/england/premier-league/',0),(6,'http://www.eplsite.com/',0),(7,'http://www.ronaldo7.net/video/watch-football-live.html',0),(8,'http://cricfree.sc/english-championship-live-streming',0),(9,'https://www.vipleague.bz/league/english-championship.html',0),(10,'https://www.livefootballol.me/streaming/championship/',0),(11,'http://www.live-footballontv.com/live-championship-football-on-tv.html',0),(12,'https://www.warpfootball.com/',0),(13,'http://www.ronaldo7.net/video/watch-football-live.html',0),(14,'http://soccerlegacy.net/liga-bbva-live-stream/',0),(15,'https://www.livesoccertv.com/competitions/england/footbal-league-one/',0),(16,'http://www.wheresthematch.com/live-league-one-football-on-tv/',0),(17,'http://worldsoccertalk.com/championship-tv-schedule/',0),(18,'https://www.livesoccertv.com/competitions/england/league-two/',0),(19,'http://www.wheresthematch.com/live-league-two-football-on-tv/',0),(20,'https://www.mlssoccer.com/tv',0),(21,'https://www.reddit.com/r/soccerstreams/',0),(22,'https://www.reddit.com/r/soccerstreams/',0),(23,'https://www.reddit.com/r/soccerstreams/',0),(24,'https://www.reddit.com/r/soccerstreams/',0),(25,'https://www.reddit.com/r/soccerstreams/',0),(26,'https://www.reddit.com/r/soccerstreams/',0),(27,'https://www.reddit.com/r/soccerstreams/',0),(28,'https://www.reddit.com/r/soccerstreams/',0),(29,'https://www.reddit.com/r/soccerstreams/',0),(30,'https://www.reddit.com/r/soccerstreams/',0),(31,'https://www.reddit.com/r/soccerstreams/',0),(32,'https://www.reddit.com/r/soccerstreams/',0),(33,'https://www.reddit.com/r/soccerstreams/',0),(34,'https://www.reddit.com/r/soccerstreams/',0),(35,'http://mls-streams.com/next-matches/',0),(36,'https://www.fubo.tv/lp/MLS-Soccer/',0),(37,'http://www.vipboxtv.se/mls-stream-live',0),(38,'http://www.live-footballontv.com/live-scottish-football-on-tv.html',0),(39,'http://cricfree.sc/',0),(40,'http://bundesliga-streams.net/live/simulcast-live-stream-2/channel-1/',0),(41,'http://www.laola1.tv/en-int/channel/football-german-bundesliga-free-video',0),(42,'https://www.batmanstream.net/bundesliga-live-stream.html',0),(43,'https://www.warpfootball.com/',0),(44,'https://www.livesoccertv.com/competitions/italy/serie-a/',0),(45,'https://www.batmanstream.net/serie-a-live-stream-2016-1.html',0),(46,'http://cricfree.sc/italian-serie-a-live-streaming',0),(47,'http://sportslens.com/serie-a-live/',0),(48,'https://www.warpfootball.com/',0),(49,'https://www.vipbox.st/competition/italy-serie-b-live-stream.html',0),(50,'http://sportslens.com/serie-a-live/',0),(51,'http://m.live3s.com/live-tv/italy/italy-serie-b',0),(52,'https://www.warpfootball.com/',0),(53,'http://cricfree.sc/french-ligue-1-live-streaming',0),(54,'https://www.livefootballol.me/streaming/ligue1/',0),(55,'http://www.vipboxtv.se/ligue-1-stream-live',0),(56,'http://sportslens.com/ligue-1-live/',0),(57,'https://www.warpfootball.com/',0),(58,'http://www.laola1.tv/en-int/channel/football-portugal-second-segunda-liga-livestream-video-for-free',0),(59,'https://www.livesoccertv.com/competitions/portugal/liga-sagres/',0),(60,'https://www.warpfootball.com/',0),(61,'http://eredivisie-stream.net/spelen/',0),(62,'http://www.sebn.me/',0),(63,'http://cricfree.sc/dutch-eredivisie-ive-streaming',0),(64,'https://www.warpfootball.com/',0),(65,'http://m.live3s.com/live-tv/russia/russia-premier-league',0),(66,'http://wizhdsports.fi/live_streaming/stream60',0),(67,'http://www.footballstreamings.com/league-tables/12/Russian-Premier-League.html',0),(68,'https://livesport.ws/en/live-football',0),(69,'https://www.warpfootball.com/',0);
/*!40000 ALTER TABLE `gen_streams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generic_stream_cat`
--

DROP TABLE IF EXISTS `generic_stream_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generic_stream_cat` (
  `generic_stream_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `league_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`generic_stream_id`),
  KEY `fk_gen_stream_league1_idx` (`league_id`),
  CONSTRAINT `fk_gen_stream_league1` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`league_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generic_stream_cat`
--

LOCK TABLES `generic_stream_cat` WRITE;
/*!40000 ALTER TABLE `generic_stream_cat` DISABLE KEYS */;
INSERT INTO `generic_stream_cat` VALUES (1,'Premier League Streams',1),(2,'Liga Santander',2),(3,'Championship',3),(4,'League One',4),(5,'League Two',5),(6,'MLS',6),(7,'Scottish Premier League',7),(8,'Bundasliga',8),(9,'Serie A',9),(10,'Serie B',10),(11,'Ligue 1',11),(12,'Primeira Liga',12),(13,'Eredivisie',13),(14,'Russian Premier League',14);
/*!40000 ALTER TABLE `generic_stream_cat` ENABLE KEYS */;
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
INSERT INTO `leagues` VALUES (1,'Premier League','1.png'),(2,'Liga Santander','2.png'),(3,'Championship','3.png'),(4,'League One','4.png'),(5,'League Two','5.png'),(6,'MLS','6.png'),(7,'Scottish Premier League','7.png'),(8,'Bundasliga','8.png'),(9,'Serie A','9.png'),(10,'Serie B','10.png'),(11,'Ligue 1','11.png'),(12,'Primeira Liga','12.png'),(13,'Eredivisie','13.png'),(14,'Russian Premier League','14.png');
/*!40000 ALTER TABLE `leagues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `page_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'home',3),(2,'game_page',1),(3,'stream_page',0);
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `stream_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `streams`
--

DROP TABLE IF EXISTS `streams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `streams` (
  `stream_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(70) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL,
  `vote` int(11) NOT NULL,
  PRIMARY KEY (`stream_id`),
  KEY `fk_game_streams1_idx` (`game_id`),
  CONSTRAINT `fk_game_streams1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `streams`
--

LOCK TABLES `streams` WRITE;
/*!40000 ALTER TABLE `streams` DISABLE KEYS */;
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

-- Dump completed on 2018-04-05 23:00:04
