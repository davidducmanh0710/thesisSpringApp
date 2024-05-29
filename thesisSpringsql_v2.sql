-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: thesisspringapp
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `committee`
--

DROP TABLE IF EXISTS `committee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `committee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee`
--

LOCK TABLES `committee` WRITE;
/*!40000 ALTER TABLE `committee` DISABLE KEYS */;
INSERT INTO `committee` VALUES (1,'Hội đồng 1',_binary ''),(3,'Hội đồng 2',_binary ''),(4,'Hội đồng 3',_binary '\0'),(5,'Hội đồng 4',_binary '\0'),(6,'Hội đồng 5',_binary '');
/*!40000 ALTER TABLE `committee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committee_user`
--

DROP TABLE IF EXISTS `committee_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `committee_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(50) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `committee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_COMMITTEEUSER` (`user_id`),
  KEY `FK_COMMITTEE_COMMITTEEUSER` (`committee_id`),
  CONSTRAINT `FK_COMMITTEE_COMMITTEEUSER` FOREIGN KEY (`committee_id`) REFERENCES `committee` (`id`),
  CONSTRAINT `FK_USER_COMMITTEEUSER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committee_user`
--

LOCK TABLES `committee_user` WRITE;
/*!40000 ALTER TABLE `committee_user` DISABLE KEYS */;
INSERT INTO `committee_user` VALUES (1,'Chủ tịch',4,1),(2,'Thư kí',5,1),(3,'Phản biện',6,1),(4,'Chủ tịch',4,3),(5,'Thư kí',5,3),(6,'Phản biện',6,3),(7,'Chủ tịch',4,4),(8,'Thư kí',6,4),(9,'Phản biện',5,4),(10,'Thành viên',9,4),(11,'Chủ tịch',4,5),(12,'Thư kí',6,5),(13,'Phản biện',9,5),(14,'Thành viên',5,5),(15,'Chủ tịch',4,6),(16,'Thư kí',5,6),(17,'Phản biện',6,6);
/*!40000 ALTER TABLE `committee_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria`
--

DROP TABLE IF EXISTS `criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria`
--

LOCK TABLES `criteria` WRITE;
/*!40000 ALTER TABLE `criteria` DISABLE KEYS */;
INSERT INTO `criteria` VALUES (1,'Tiêu chí 1',NULL),(2,'Tiêu chí 2',NULL),(3,'Tiêu chí 3',NULL),(4,'Tiêu chí 4',NULL),(5,'Tiêu chí 5',NULL),(6,'Tiêu chí 6',NULL),(7,'Tiêu chí 7',NULL),(8,'Tiêu chí 8',NULL);
/*!40000 ALTER TABLE `criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Công nghệ thông tin');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `otp_code` varchar(255) NOT NULL,
  `expiry_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `otp_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentvnpaydetail`
--

DROP TABLE IF EXISTS `paymentvnpaydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentvnpaydetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) NOT NULL,
  `amount` float NOT NULL,
  `order_desc` varchar(255) DEFAULT NULL,
  `vnp_TransactionNo` varchar(255) DEFAULT NULL,
  `vnp_ResponseCode` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VNPAY_USER` (`user_id`),
  CONSTRAINT `FK_VNPAY_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentvnpaydetail`
--

LOCK TABLES `paymentvnpaydetail` WRITE;
/*!40000 ALTER TABLE `paymentvnpaydetail` DISABLE KEYS */;
INSERT INTO `paymentvnpaydetail` VALUES (18,'86028822',10000,'Thanh toan tien photo cho sinh vien co ma so : THESISSINHVIEN23344238','14434811','00',11),(19,'14465862',10000,'Thanh toan tien photo cho sinh vien co ma so : THESISSINHVIEN23344238','14434928','00',11),(20,'71549767',10000,'Thanh toan tien photo cho sinh vien co ma so : THESISSINHVIEN23344238','14434934','00',11),(21,'33408973',10000,'Thanh toan tien photo cho sinh vien co ma so : THESISSINHVIEN23344238','14434937','00',11),(22,'63429580',10000,'1 THESISSINHVIEN23344238','14434998','00',11),(23,'14870979',10000,'Thanh toán phí download file PDF khóa luận có mã 1 của sinh vien THESISSINHVIEN23344238','14435572','00',11),(24,'25628886',10000,'Thanh toán phí download file PDF khóa luận có mã 1 của sinh vien THESISSINHVIEN23344238','14435635','00',11);
/*!40000 ALTER TABLE `paymentvnpaydetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_GIAOVU'),(3,'ROLE_GIANGVIEN'),(4,'ROLE_SINHVIEN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` float NOT NULL,
  `thesis_id` int DEFAULT NULL,
  `criteria_id` int DEFAULT NULL,
  `committee_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_THESIS_SCORE` (`thesis_id`),
  KEY `FK_CRITERIA_SCORE` (`criteria_id`),
  KEY `FK_COMMITTEEUSER_SCORE` (`committee_user_id`),
  CONSTRAINT `FK_COMMITTEEUSER_SCORE` FOREIGN KEY (`committee_user_id`) REFERENCES `committee_user` (`id`),
  CONSTRAINT `FK_CRITERIA_SCORE` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`id`),
  CONSTRAINT `FK_THESIS_SCORE` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,9,1,1,10),(2,9,1,2,10),(3,9,1,3,10),(4,8,3,1,13),(5,8,3,2,13),(6,8,3,3,13),(7,8,3,4,13);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `score` float DEFAULT NULL,
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
INSERT INTO `thesis` VALUES (1,'Khóa luận 1','2024-05-24 10:04:04','2024-05-25 13:16:54',2.25,_binary '\0'),(3,'Khóa luận 2','2024-05-25 14:06:58','2024-05-25 14:14:42',2,_binary '\0'),(9,'Khóa luận 3','2024-05-25 23:33:38','2024-05-26 00:18:33',0,_binary '\0');
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis_committee_rate`
--

DROP TABLE IF EXISTS `thesis_committee_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis_committee_rate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thesis_id` int DEFAULT NULL,
  `committee_id` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_THESIS_THESISCOMMITTEERATE` (`thesis_id`),
  KEY `FK_COMMITTEE_THESISCOMMITTEERATE` (`committee_id`),
  KEY `FK_STATUS_THESISCOMMITTEERATE` (`status_id`),
  CONSTRAINT `FK_COMMITTEE_THESISCOMMITTEERATE` FOREIGN KEY (`committee_id`) REFERENCES `committee` (`id`),
  CONSTRAINT `FK_STATUS_THESISCOMMITTEERATE` FOREIGN KEY (`status_id`) REFERENCES `thesis_status` (`id`),
  CONSTRAINT `FK_THESIS_THESISCOMMITTEERATE` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis_committee_rate`
--

LOCK TABLES `thesis_committee_rate` WRITE;
/*!40000 ALTER TABLE `thesis_committee_rate` DISABLE KEYS */;
INSERT INTO `thesis_committee_rate` VALUES (1,1,1,3),(3,3,5,3),(4,9,1,3);
/*!40000 ALTER TABLE `thesis_committee_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis_status`
--

DROP TABLE IF EXISTS `thesis_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis_status`
--

LOCK TABLES `thesis_status` WRITE;
/*!40000 ALTER TABLE `thesis_status` DISABLE KEYS */;
INSERT INTO `thesis_status` VALUES (1,'Hoạt động'),(2,'Đang chấm'),(3,'Đã chấm');
/*!40000 ALTER TABLE `thesis_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis_user`
--

DROP TABLE IF EXISTS `thesis_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thesis_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_THESIS_THESISUSER` (`thesis_id`),
  KEY `FK_USER_THESISUSER` (`user_id`),
  CONSTRAINT `FK_THESIS_THESISUSER` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`),
  CONSTRAINT `FK_USER_THESISUSER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis_user`
--

LOCK TABLES `thesis_user` WRITE;
/*!40000 ALTER TABLE `thesis_user` DISABLE KEYS */;
INSERT INTO `thesis_user` VALUES (1,1,4),(2,1,11),(5,3,9),(6,3,4),(7,3,7),(18,9,4),(19,9,10);
/*!40000 ALTER TABLE `thesis_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `useruniversityid` varchar(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(40) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `birthday` datetime NOT NULL,
  `active` bit(1) DEFAULT b'0',
  `role_id` int NOT NULL,
  `faculty_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `useruniversityid` (`useruniversityid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  KEY `FK_ROLE_USER` (`role_id`),
  KEY `FK_FACULTY_USER` (`faculty_id`),
  CONSTRAINT `FK_FACULTY_USER` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `FK_ROLE_USER` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000000','THESISQUANTRI000000','$2a$10$EPMd/Vy40lKRA1c6lpqi1Osu2o707vayiUU4rolU6n5SY6DqMbbVW','Lâu','Ngô Văn','male','ngovanlau2003@gmail.com','0393131096','2003-06-29 00:00:00',_binary '',1,1),(3,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000001','THESISGIAOVU000001','$2a$2a$10$ZBCdvTEwxjY7es1auZpZ.OHTFa/bpM.wevDb4rSseZJI33l.Cc14e','A','Ngô Văn','male','giaovu1@gmail.com','0393131091','2024-05-01 00:00:00',_binary '',2,1),(4,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000002','THESISGIANGVIEN000002','$2a$2a$10$ZBCdvTEwxjY7es1auZpZ.OHTFa/bpM.wevDb4rSseZJI33l.Cc14e','B','Ngô Văn','male','giangvien1@gmail.com','0393131092','2024-05-01 00:00:00',_binary '\0',3,1),(5,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000003','THESISGIANGVIEN000003','$2a$2a$10$ZBCdvTEwxjY7es1auZpZ.OHTFa/bpM.wevDb4rSseZJI33l.Cc14e','C','Ngô Văn','male','giangvien2@gmail.com','0393131093','2024-05-01 00:00:00',_binary '\0',3,1),(6,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000004','THESISGIANGVIEN000004','$2a$10$ZBCdvTEwxjY7es1auZpZ.OHTFa/bpM.wevDb4rSseZJI33l.Cc14e','D','Ngô Văn','male','giangvien3@gmail.com','0393131094','2024-05-08 00:00:00',_binary '\0',3,1),(7,'https://res.cloudinary.com/diwxda8bi/image/upload/v1713088939/yyplfcrhzurbla0t8vy4.jpg','000005','THESISSINHVIEN000005','$2a$2a$10$ZBCdvTEwxjY7es1auZpZ.OHTFa/bpM.wevDb4rSseZJI33l.Cc14e','E','Ngô Văn','male','sinhvien1@gmail.com','0393131095','2024-05-02 00:00:00',_binary '\0',4,1),(8,'https://res.cloudinary.com/diwxda8bi/image/upload/v1716620720/wfffase26nxer56xvw2z.png','000006','THESISGIAOVU000006','$2a$10$UeCsg6YyEeeVCN.Vgx6NZezt7yoYwnONGTOJVrRLFmGElLDaMSS4m','F','Ngô Văn','male','vanlau.luzy@gmail.com','0393131098','2024-05-01 00:00:00',_binary '',2,1),(9,'https://res.cloudinary.com/diwxda8bi/image/upload/v1716625084/fl1kxgpubvzxmwox7hrc.png','000007','THESISGIANGVIEN000007','$2a$10$wnQljQ8A0AxoF0Sk6gwAzu5X18LOM.SzGGTaijQG9fd7VvIWW2YtO','G','Ngô Văn','male','2151053034lau@ou.edu.vn','0393131099','2024-05-01 00:00:00',_binary '',3,1),(10,'https://res.cloudinary.com/diwxda8bi/image/upload/v1716484867/xqzxvql9dqv474ewrcuq.png','000008','THESISSINHVIEN000008','$2a$2a$10$wi.x6R8TtbH1O0IFDzjJO.rQgtjRf5sy.vZBSvjB9.VN/rPgbC.1S','H','Ngô Văn','male','yu.vanu@gmail.com','0393131023','2024-05-02 00:00:00',_binary '\0',4,1),(11,'https://res.cloudinary.com/diwxda8bi/image/upload/v1716702786/iuiddhjkd11ryz9werap.png','23344238','THESISSINHVIEN23344238','$2a$10$Z3/u/6XMKFnGwFp.4cTgvusOzUd3rk0xgOpmzpd33htDq0EN7qq9u','J','Ngô Văn','male','yu.vanlau@gmail.com','0393134098','2024-05-02 00:00:00',_binary '',4,1);
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

-- Dump completed on 2024-05-30  1:34:41
