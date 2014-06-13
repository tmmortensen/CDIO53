CREATE DATABASE  IF NOT EXISTS `weight` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `weight`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: weight
-- ------------------------------------------------------
-- Server version	5.5.34

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` INT(11) NOT NULL DEFAULT '0',
  `user_name` varchar(20) DEFAULT NULL,
  `ini` varchar(4) DEFAULT NULL,
  `cpr` varchar(10) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `user_type` int(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productbatch`
--

DROP TABLE IF EXISTS `productbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productbatch` (
  `pb_id` INT(11) NOT NULL,
  `prescription_id` INT(11) DEFAULT NULL,
  `status` int(3) DEFAULT NULL,
  PRIMARY KEY (`pb_id`),
  KEY `pbatch_prescription_id_idx` (`prescription_id`),
  CONSTRAINT `pbatch_prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `productbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productbatchcomponent`
--

DROP TABLE IF EXISTS `productbatchcomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productbatchcomponent` (
  `pb_id` INT(11) NOT NULL,
  `commoditybatch_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `tara` decimal(7,4) DEFAULT NULL,
  `netto` decimal(7,4) DEFAULT NULL,
  KEY `pbcomponent_pb_idx` (`pb_id`),
  KEY `pbcomponent_commoditybatch_id_idx` (`commoditybatch_id`),
  KEY `pbcomponent_user_id_idx` (`user_id`),
  CONSTRAINT `pbcomponent_pb_id` FOREIGN KEY (`pb_id`) REFERENCES `productbatch` (`pb_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pbcomponent_commoditybatch_id` FOREIGN KEY (`commoditybatch_id`) REFERENCES `commoditybatch` (`commoditybatch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pbcomponent_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatchkomponent`
--

LOCK TABLES `productbatchcomponent` WRITE;
/*!40000 ALTER TABLE `productbatchcomponent` DISABLE KEYS */;
/*!40000 ALTER TABLE `productbatchcomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity` (
  `commodity_id` INT(11) NOT NULL,
  `commodity_name` varchar(20) DEFAULT NULL,
  `supplier` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_batch`
--

DROP TABLE IF EXISTS `commoditybatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commoditybatch` (
  `commoditybatch_id` INT(11) NOT NULL,
  `commodity_id` INT(11) NOT NULL,
  `amount` decimal(12,4) DEFAULT NULL,
  PRIMARY KEY (`commoditybatch_id`),
  KEY `commodity_id_idx` (`commodity_id`),
  CONSTRAINT `commodity_id` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`commodity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_batch`
--

LOCK TABLES `commoditybatch` WRITE;
/*!40000 ALTER TABLE `commoditybatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `commoditybatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `prescription_id` INT(11) NOT NULL,
  `prescription_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescriptionkomponent`
--

DROP TABLE IF EXISTS `prescriptioncomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescriptioncomponent` (
  `prescription_id` INT(11) DEFAULT NULL,
  `commodity_id` INT(11) DEFAULT NULL,
  `nom_netto` decimal(7,4) DEFAULT NULL,
  `tolerance` decimal(7,4) DEFAULT NULL,
  KEY `prescription_id_idx` (`prescription_id`),
  KEY `commodity_id_idx` (`commodity_id`),
  CONSTRAINT `rckomponent_commodity_id` FOREIGN KEY (`commodity_id`) REFERENCES `commodity` (`commodity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rckomponent_prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriptionkomponent`
--

LOCK TABLES `prescriptioncomponent` WRITE;
/*!40000 ALTER TABLE `prescriptioncomponent` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescriptioncomponent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-04 13:57:44
