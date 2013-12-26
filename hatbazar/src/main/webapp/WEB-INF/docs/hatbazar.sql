-- MySQL dump 10.13  Distrib 5.1.61, for Win64 (unknown)
--
-- Host: localhost    Database: hatbazar
-- ------------------------------------------------------
-- Server version	5.1.61-community

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
-- Current Database: `hatbazar`
--

/*!40000 DROP DATABASE IF EXISTS `hatbazar`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hatbazar` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hatbazar`;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `subject` varchar(150) DEFAULT NULL,
  `message` text,
  `is_new` bit(1) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (5,'sdafdasfdas ','sejawalbraj@yahoo.com','9874545121','sdfasdfasdfsad ','sdfasdfasdfasdfasdsdafas as fasdfasfasfas saf aasf afasf ','\0','2013-06-28 11:03:21'),(6,'dsfasdfasd','sejawalbraj@yahoo.com','9874545121','sadfasdfasd','sdafasdfadsf sadfsa fasdf saf asfasfasd f','\0','2013-06-28 16:32:35'),(7,'sdfasdf','sejawalbraj@yahoo.com','9855','dsfasdfasf',' fsadfsadfasdf sadfas sadf','\0','2013-06-28 17:29:06');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `contact_person` varchar(100) DEFAULT NULL,
  `contact_phone` varchar(10) DEFAULT NULL,
  `details` text,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changed_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reserved_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'sdfasdf','CATTLE',1,5454545,'ACTIVE','sfasdf','984566322','asdfasdfasfasf','2013-06-24 19:35:01','0000-00-00 00:00:00',NULL),(2,'sdfsadfas','CATTLE',1,546454545,'ACTIVE','asfasfadf','dsafsadfas','sdafasdf sa fasfa sf asfas asf f','2013-06-25 14:49:05','0000-00-00 00:00:00',NULL),(3,'test','VEGETABLES',1,6699,'SOLD','contact Person','984125633','sdfasfasd sdfasfasdf afa a ff aa afa','2013-06-25 16:35:54','2013-06-28 18:49:36',1),(4,'sdfasfasf','VEGETABLES',1,545454545,'ACTIVE','fsafasfasf','98456666','sdfasfadsfaa s','2013-06-25 16:36:51','2013-06-25 16:57:00',NULL),(5,'sdafadsfad','CATTLE',1,5454545455,'ACTIVE','safassfasd','sdfafdaa','this is test for detail of this item you can book this item and go respective place for buy this item  contact detail is given up .','2013-06-25 16:38:32','2013-06-25 17:21:09',NULL),(6,'xcxZc','CATTLE',1,22222,'ACTIVE','9888','9555','sdfas','2013-06-25 18:29:26','0000-00-00 00:00:00',NULL),(7,'sadfafda','CATTLE',1,54545,'RESERVED','sdfdsafa','5454545','sdfasfdasfad','2013-06-25 18:30:51','2013-06-28 23:33:59',1),(8,'sdfdasf','CATTLE',1,56454545,'SOLD','sdafasfadf','6655454544','dsfsafasdfasdf sfas sadfsadfafsfsdfasa sfasfasda','2013-06-25 18:46:05','2013-06-28 18:57:27',1),(9,'test name','CATTLE',1,54545454,'SOLD','test contact prson','98745663','test details ss','2013-06-25 18:46:48','2013-06-27 19:54:53',1),(10,'test name','CATTLE',1,145879666,'RESERVED','test contact prson','98745663','test details','2013-06-25 18:47:47','2013-06-25 21:23:27',9),(11,'sdfdsaf','CATTLE',9,64654,'ACTIVE','contact Person','121212','2122sdfasdfads','2013-06-25 21:24:13','2013-06-25 21:33:45',9),(12,'test name','CATTLE',1,145222,'ACTIVE','Hari Khanal','9841729762','this test you can buy with this phone number','2013-06-28 23:33:02','0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_item_trigger` BEFORE UPDATE ON `item`
FOR EACH ROW SET NEW.`changed_on`=NOW() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_sold`
--

DROP TABLE IF EXISTS `item_sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sold` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `req_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item` (`item`),
  KEY `req_by` (`req_by`),
  CONSTRAINT `item_sold_ibfk_1` FOREIGN KEY (`item`) REFERENCES `item` (`id`),
  CONSTRAINT `item_sold_ibfk_2` FOREIGN KEY (`req_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_sold`
--

LOCK TABLES `item_sold` WRITE;
/*!40000 ALTER TABLE `item_sold` DISABLE KEYS */;
INSERT INTO `item_sold` VALUES (1,8,'2013-06-28 18:57:28',1);
/*!40000 ALTER TABLE `item_sold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changed_on` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `added_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Bhesh Raj','test tessdfashh','sejawalbraj@yahooff.com','9874545121','SUPER','bsejawal','8b785518548cdb502146c19239ebf932','2013-05-30 03:33:44','2013-06-28 23:35:21',0),(7,'test name','test address','test@test.tet','987456666','NORMAL','bhesh','633de4b0c14ca52ea2432a3c8a5c4c31','2013-06-24 16:57:50','2013-06-27 15:14:11',0),(8,'test','adf','sejawalbraj@facebook.com','9874545121','ADMIN','tssdfsfds','45c48cce2e2d7fbdea1afc51c7c6ad26','2013-06-24 21:06:26','0000-00-00 00:00:00',1),(9,'sdfdsaf','sdfsdafas','sdfsdafdsa@dsfdsf.com','98741225666','ADMIN','j','363b122c528f54df4a0446b6bab05515','2013-06-25 21:21:39','0000-00-00 00:00:00',1),(10,'test','test','testEmail@sts.com','9874545121','NORMAL','test','098f6bcd4621d373cade4e832627b4f6','2013-06-27 15:06:20','0000-00-00 00:00:00',1),(11,'Abhinayak Jung Swar','Maitidevi','aswar@gmail.com','987412563222','NORMAL','aswar','098f6bcd4621d373cade4e832627b4f6','2013-06-28 23:31:24','0000-00-00 00:00:00',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = latin1 */ ;
/*!50003 SET character_set_results = latin1 */ ;
/*!50003 SET collation_connection  = latin1_swedish_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_user_trigger` BEFORE UPDATE ON `user`
  FOR EACH ROW SET NEW.`changed_on`=NOW() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logged_in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(250) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `action` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
INSERT INTO `user_log` VALUES (1,'2013-06-28 19:21:39','h','Bhesh Raj',1,'Login'),(2,'2013-06-28 19:22:03','h','Bhesh Raj',1,'Logout'),(3,'2013-06-28 19:48:59','h','Bhesh Raj',1,'Login'),(4,'2013-06-28 19:56:24','h','Bhesh Raj',1,'Login'),(5,'2013-06-28 19:59:33','h','Bhesh Raj',1,'Login'),(6,'2013-06-28 20:01:02','h','Bhesh Raj',1,'Logout'),(7,'2013-06-28 20:01:34','h','Bhesh Raj',1,'Login'),(8,'2013-06-28 20:03:12','h','Bhesh Raj',1,'Login'),(9,'2013-06-28 20:16:36','h','Bhesh Raj',1,'Login'),(10,'2013-06-28 20:18:34','h','Bhesh Raj',1,'Login'),(11,'2013-06-28 22:26:04','h','Bhesh Raj',1,'Login'),(12,'2013-06-28 22:27:15','h','Bhesh Raj',1,'Logout'),(13,'2013-06-28 22:27:19','h','Bhesh Raj',1,'Login'),(14,'2013-06-28 22:35:53','h','Bhesh Raj',1,'Login'),(15,'2013-06-28 23:30:30','h','Bhesh Raj',1,'Login'),(16,'2013-06-28 23:34:15','h','Bhesh Raj',1,'Logout'),(17,'2013-06-28 23:34:53','h','Bhesh Raj',1,'Login');
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-29  5:21:59
