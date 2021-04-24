-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: kioskappdb_dev
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
-- Table structure for table `ItemProduct`
--

DROP TABLE IF EXISTS `ItemProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ItemProduct` (
  `ItemProduct` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(300) NOT NULL,
  `Price` float NOT NULL,
  PRIMARY KEY (`ItemProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemProduct`
--

LOCK TABLES `ItemProduct` WRITE;
/*!40000 ALTER TABLE `ItemProduct` DISABLE KEYS */;
INSERT INTO `ItemProduct` VALUES (1,'McChicken	',8.1),(2,'Ayam Goreng McD Spicy (2pcs)	',11.9),(3,'Ayam Goreng McD Spicy (5pcs)',30.2),(4,'Spicy Chicken McDeluxe	',11.9),(5,'Chicken McNuggets (6pcs)',9.4),(6,'Double Cheeseburger	',9.45),(7,'Big Mac	',10.4),(8,'Filet-O-Fish	',8.45),(9,'McChicken Meal (Medium)',13.2),(10,'Chicken McNuggets 6pcs Meal (Medium)',13.2),(11,'Filet-O-Fish Meal (Medium)',13),(13,'Strawberry Sundae',4.15),(14,'Chocolate Sundae',4.15);
/*!40000 ALTER TABLE `ItemProduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Order` (
  `OrderId` int NOT NULL AUTO_INCREMENT,
  `TotalAmount` float NOT NULL,
  `OrderReferenceNumber` int NOT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderedItem`
--

DROP TABLE IF EXISTS `OrderedItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderedItem` (
  `OrderedItemId` int NOT NULL AUTO_INCREMENT,
  `ItemProduct` int NOT NULL,
  `Quantity` int NOT NULL,
  `SubTotalAmount` float NOT NULL,
  `Order` int DEFAULT NULL,
  PRIMARY KEY (`OrderedItemId`),
  KEY `OrderedItem_ItemProduct_FK_idx` (`ItemProduct`),
  KEY `OrderedItem_Order_FK_idx` (`Order`),
  CONSTRAINT `OrderedItem_ItemProduct_FK` FOREIGN KEY (`ItemProduct`) REFERENCES `ItemProduct` (`ItemProduct`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `OrderedItem_Order_FK` FOREIGN KEY (`Order`) REFERENCES `Order` (`OrderId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderedItem`
--

LOCK TABLES `OrderedItem` WRITE;
/*!40000 ALTER TABLE `OrderedItem` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderedItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderTransaction`
--

DROP TABLE IF EXISTS `OrderTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderTransaction` (
  `OrderTransactionId` int NOT NULL AUTO_INCREMENT,
  `TransactionDate` datetime NOT NULL,
  `Order` int NOT NULL,
  `AmountCharged` float NOT NULL,
  `TransactionStatus` int NOT NULL DEFAULT '0',
  `Last4Digits` int NOT NULL,
  `OrderMode` varchar(20) NOT NULL DEFAULT 'Eat-In',
  PRIMARY KEY (`OrderTransactionId`),
  KEY `OrderTransaction_Order_FK_idx` (`Order`),
  CONSTRAINT `OrderTransaction_Order_FK` FOREIGN KEY (`Order`) REFERENCES `Order` (`OrderId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderTransaction`
--

LOCK TABLES `OrderTransaction` WRITE;
/*!40000 ALTER TABLE `OrderTransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderTransaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-18 10:53:57
