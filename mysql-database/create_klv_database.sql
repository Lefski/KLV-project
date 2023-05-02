CREATE DATABASE  IF NOT EXISTS `klv_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `klv_database`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Database: klv_database
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `sn_post`
--

DROP TABLE IF EXISTS `sn_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_post` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `post_author_id` bigint unsigned NOT NULL,
  `post_status` varchar(255) NOT NULL,
  `date_posted` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  `post_title` varchar(255) NOT NULL,
  `post_description` text NOT NULL,
  `post_content` text NOT NULL,
  `post_type` varchar(255) NOT NULL,
  `post_image_url` blob NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sn_post_post_author_id_foreign` (`post_author_id`),
  CONSTRAINT `sn_post_post_author_id_foreign` FOREIGN KEY (`post_author_id`) REFERENCES `sn_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sn_post_categories`
--

DROP TABLE IF EXISTS `sn_post_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_post_categories` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint unsigned NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sn_post_categories_post_id_foreign` (`post_id`),
  KEY `sn_post_categories_category_id_foreign` (`category_id`),
  CONSTRAINT `sn_post_categories_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `sn_post_category` (`id`),
  CONSTRAINT `sn_post_categories_post_id_foreign` FOREIGN KEY (`post_id`) REFERENCES `sn_post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sn_post_category`
--

DROP TABLE IF EXISTS `sn_post_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_post_category` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sn_user`
--

DROP TABLE IF EXISTS `sn_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `last_login` datetime NOT NULL,
  `date_joined` datetime NOT NULL,
  `user_image_url` blob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sn_user_username_unique` (`username`),
  KEY `sn_user_username_user_email_index` (`username`,`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sn_user_passport`
--

DROP TABLE IF EXISTS `sn_user_passport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_user_passport` (
  `user_id` bigint unsigned NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `sn_user_passport_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `sn_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sn_user_role`
--

DROP TABLE IF EXISTS `sn_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_name` enum('ROLE_USER','ROLE_MODERATOR','ROLE_ADMIN') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sn_user_role`
--

LOCK TABLES `sn_user_role` WRITE;
/*!40000 ALTER TABLE `sn_user_role` DISABLE KEYS */;
INSERT INTO `sn_user_role` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `sn_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sn_user_roles`
--

DROP TABLE IF EXISTS `sn_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sn_user_roles` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `role_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sn_user_roles_role_id_foreign` (`role_id`),
  KEY `sn_user_roles_user_id_foreign` (`user_id`),
  CONSTRAINT `sn_user_roles_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `sn_user_role` (`id`),
  CONSTRAINT `sn_user_roles_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `sn_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-02 20:08:50
