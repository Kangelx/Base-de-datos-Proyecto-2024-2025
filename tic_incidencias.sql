-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: tic_incidencias
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `aulas`
--

DROP TABLE IF EXISTS `aulas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aulas` (
  `num` int NOT NULL,
  `codigo` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `planta` int NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aulas`
--

LOCK TABLES `aulas` WRITE;
/*!40000 ALTER TABLE `aulas` DISABLE KEYS */;
INSERT INTO `aulas` VALUES (1,'IF01','Aula DAM2',7000),(2,'IF02','Aula DAM1',2),(3,'IF03','Aula DAW1',2),(4,'IF04','Aula DAW2',1),(5,'d','d',1),(10,'12345678','API',1),(11,'12345678','API',1),(20,'IF01','Aula 20000',1),(55,'IF01','Aula DAM2',70),(100,'IF01','Aula DAM2',9),(200,'IF01','Aulaaaa DAM2',1),(201,'IF01','si',1),(500,'IF01','Aula DAM2',71000),(1050,'IF01','Aula DAM2',9);
/*!40000 ALTER TABLE `aulas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `texto` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `fechahora` datetime NOT NULL,
  `incidencia_num` int NOT NULL,
  `personal_id` int NOT NULL,
  `adjunto_url` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id`),
  KEY `fk_comentarios_incidencias1_idx` (`incidencia_num`),
  KEY `fk_comentarios_personal1_idx` (`personal_id`),
  CONSTRAINT `fk_comentarios_incidencias1` FOREIGN KEY (`incidencia_num`) REFERENCES `incidencias` (`num`),
  CONSTRAINT `fk_comentarios_personal1` FOREIGN KEY (`personal_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (1,'Este es un comentario inicial','2024-02-01 10:30:00',1,3,'archivosAdjuntos/foto.jpg_20240226185137'),(2,'Incidencia en proceso de resolución','2024-02-02 12:45:00',1,5,''),(3,'Adjunto de evidencia enviado','2024-02-03 15:20:00',1,3,'adjunto1.jpg'),(4,'Resuelto satisfactoriamente','2024-02-04 09:10:00',1,5,''),(5,'Solicitud de más información al profesor','2024-02-05 11:05:00',2,8,''),(6,'Esperando respuesta del profesor','2024-02-06 14:30:00',2,5,''),(7,'Incidencia cerrada','2024-02-07 08:45:00',2,8,''),(8,'Comentario sin adjunto','2024-02-08 10:20:00',3,7,''),(9,'Incidencia reabierta para revisión','2024-02-09 13:15:00',3,3,''),(10,'Holka','2024-02-01 10:30:00',2,10,NULL),(11,'za','2024-02-01 10:30:00',1,1,NULL),(12,'Este es un comentario inicial','2024-02-01 10:30:00',1,1,''),(13,'Este es un comentario inicial','2024-02-01 10:30:00',1,1,''),(14,'a','2024-02-01 10:30:00',1,1,''),(15,'a','2024-02-01 10:30:00',1,1,''),(16,'bbbbb','2024-02-01 10:30:00',1,1,''),(17,'bbbbb','2024-02-01 10:30:00',1,1,''),(18,'aaaaa','2024-02-01 10:30:00',1,1,'');
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cod` char(6) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  `jefedep_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_departamentos_personal1_idx` (`jefedep_id`),
  CONSTRAINT `fk_departamentos_personal1` FOREIGN KEY (`jefedep_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'DEP004','AAAAbbbbbbbbbbA',1,5),(2,'DEP004','AAAAA',1,NULL),(3,'DEP004','AAAAA',1,NULL),(4,'DEP004','ooooooA',1,4),(5,'DEP005','Departamento de Educación Física',1,5),(6,'DEP004','ooooooA',1,4),(7,'DEP004','ooooooA',1,4),(8,'DEP004','PLOP',1,4),(9,'DEP004','PLOP',1,4),(10,'DEP004','<<<',1,4),(11,'DEP004','<<<',1,1),(12,'DEP004','<<<',1,20),(13,'DEP004','AAAAA',1,9),(14,'DEP004','AAAAA',1,20),(15,'DEP004','AAAAA',1,19),(16,'DEP004','AAAAA',1,1),(17,'DEP004','AAAAA',1,12),(19,'DEP004','AAAAA',1,12),(20,'DEP004','AAAAA',1,12),(21,'DEP004','AAAAA',1,12),(22,'DEP004','AAAAA',1,NULL),(23,'DEP004','AAAAA',1,NULL),(24,'DEP004','AAAAA',1,NULL),(25,'DEP004','AAAAA',1,NULL),(26,'DEP004','AAAAbbbbbbbbbbA',1,2),(27,'DEP004','AAAAbbbbbbbbbbA',1,2),(28,'DEP004','AAAAbbbbbbbbbbA',1,NULL),(29,'DEP004','AAAAbbbbbbbbbbA',1,2),(30,'DEP004','AAAAbbbbbbbbbbA',1,4);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_equipo` enum('ALTAVOCES','IMPRESORA','MONITOR','PANTALLA_INTERACTIVA','PORTATIL_DE_AULA','PORTATIL_CONSEJERIA','PROYECTOR') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `fecha_adquisicion` date DEFAULT NULL,
  `etiqueta` char(8) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `marca` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `modelo` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci,
  `baja` tinyint DEFAULT '0',
  `aula_num` int NOT NULL,
  `puesto` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipos_aulas1_idx` (`aula_num`),
  CONSTRAINT `fk_equipos_aulas1` FOREIGN KEY (`aula_num`) REFERENCES `aulas` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (1,'ALTAVOCES','2022-03-01','01','JJJ','aa','Estación de trabajo potente para diseño gráfico',1,1,1),(2,'MONITOR','2022-03-05','02','Epson','EcoTank ET-2720','Impresora de inyección de tinta con tanque de tinta',0,2,NULL),(3,'ALTAVOCES','2022-03-10','03','Sony','VPL-VW295ES','Proyector de cine en casa con resolución 4K',0,1,NULL),(4,'IMPRESORA','2022-03-15','04','04r','Aspire TC-895','Computadora de escritorio para uso general',0,1,2),(5,'ALTAVOCES','2022-03-20','05','Canon','imageCLASS MF743Cdw','Impresora láser color con funciones multifunción',1,1,NULL),(6,'ALTAVOCES','2022-03-25','06','BenQ','JAAA','Proyector para juegos y entretenimiento en casa',0,2,NULL),(7,'IMPRESORA','2022-04-10','07','Dell','Inspiron 3880','Ordenador para tareas cotidianas',0,2,1),(8,'ALTAVOCES','2022-04-15','08','Samsung','Xpress C430W','Impresora láser color compacta',0,3,NULL),(9,'ALTAVOCES','2022-04-20','09','Optoma','HD28HDR','Proyector de cine en casa con HDR',0,4,NULL),(10,'ALTAVOCES','2022-03-01','01','HP','aa','Estación de trabajo potente para diseño gráfico',1,20,1),(11,'ALTAVOCES','2022-03-01','01','JJJ','aa','Estación de trabajo potente para diseño gráfico',1,1,1),(12,'ALTAVOCES','2022-03-25','06','BenQ','HOLA','Proyector para juegos y entretenimiento en casa',0,2,NULL),(20,'ALTAVOCES','2022-03-01','01','HP','aa','Estación de trabajo potente para diseño gráfico',1,20,1),(30,'ALTAVOCES','2022-03-01','01','HP','aa','Estación de trabajo potente para diseño gráfico',1,20,1),(31,'ALTAVOCES','2022-03-01','01','HP','EliteDesk 800 G5','Estación de trabajo potente para diseño gráfico',1,20,1),(32,'ALTAVOCES','2022-03-01','01','HP','KAAAAA','Estación de trabajo potente para diseño gráfico',1,20,1),(33,'ALTAVOCES','2022-03-01','01','HP','KAAAAA','Estación de trabajo potente para diseño gráfico',1,20,1),(34,'ALTAVOCES','2022-03-01','01','HP','KAAAAA','Estación de trabajo potente para diseño gráfico',1,20,1),(35,'ALTAVOCES','2022-03-01','01','HP','gg','Estación de trabajo potente para diseño gráfico',1,1,1),(36,'ALTAVOCES','2022-03-01','01','HP','gg','Estación de trabajo potente para diseño gráfico',1,1,1),(38,'ALTAVOCES','2022-03-01','01','JJJ','g','Estación de trabajo potente para diseño gráfico',1,1,2),(39,'ALTAVOCES','2022-03-01','01','JJJ','gg','Estación de trabajo potente para diseño gráfico',1,1,2);
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencias`
--

DROP TABLE IF EXISTS `incidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidencias` (
  `num` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('EQUIPOS','CUENTAS','WIFI','INTERNET','SOFTWARE') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `subtipo_id` int NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_cierre` datetime DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `estado` enum('abierta','asignada','en_proceso','enviada_a_Infortec','resuelta','cerrada') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `adjunto_url` text CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci,
  `creador_id` int NOT NULL,
  `responsable_id` int DEFAULT NULL,
  `equipo_id` int DEFAULT NULL,
  `prioridad` enum('baja','media','alta','critica') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `tiempo` time DEFAULT NULL,
  PRIMARY KEY (`num`),
  KEY `fk_incidencias_incidencias_subtipos1_idx` (`subtipo_id`),
  KEY `fk_incidencias_personal1_idx` (`creador_id`),
  KEY `fk_incidencias_personal2_idx` (`responsable_id`),
  KEY `fk_incidencias_equipo_idx` (`equipo_id`),
  CONSTRAINT `fk_incidencias_equipo` FOREIGN KEY (`equipo_id`) REFERENCES `equipos` (`id`),
  CONSTRAINT `fk_incidencias_incidencias_subtipos1` FOREIGN KEY (`subtipo_id`) REFERENCES `incidencias_subtipos` (`id`),
  CONSTRAINT `fk_incidencias_personal1` FOREIGN KEY (`creador_id`) REFERENCES `personal` (`id`),
  CONSTRAINT `fk_incidencias_personal2` FOREIGN KEY (`responsable_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias`
--

LOCK TABLES `incidencias` WRITE;
/*!40000 ALTER TABLE `incidencias` DISABLE KEYS */;
INSERT INTO `incidencias` VALUES (1,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta','archivosAdjuntos/foto1.jpg',1,1,1,'alta','00:10:24'),(2,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,2,2,'alta','01:20:15'),(3,'WIFI',3,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,2,3,NULL,'media','10:10:10'),(4,'INTERNET',1,'2022-03-07 11:20:00',NULL,'Problema de conexión a Internet','abierta','https://ejemplo.com/adjunto4.jpg',1,3,NULL,'critica','07:08:10'),(5,'SOFTWARE',2,'2022-03-08 13:45:00','2022-03-10 14:00:00','Actualización de software requerida','cerrada',NULL,3,3,NULL,'baja','01:01:01'),(6,'EQUIPOS',3,'2022-03-30 08:30:00',NULL,'Problema con el proyector','abierta','https://ejemplo.com/adjunto5.pdf',3,4,7,'alta',NULL),(7,'CUENTAS',1,'2022-03-31 10:15:00',NULL,'Solicitud de creación de cuenta','asignada',NULL,4,5,NULL,'media',NULL),(8,'WIFI',2,'2022-04-01 12:00:00','2022-04-02 14:45:00','Interrupción intermitente en la conexión Wi-Fi','cerrada',NULL,1,6,NULL,'baja',NULL),(33,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(34,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(35,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(36,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(37,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(38,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(39,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(40,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(41,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(42,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(43,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(44,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(45,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(46,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(47,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'media',NULL),(48,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,1,1,'baja',NULL),(49,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(50,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(51,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(52,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(53,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(54,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(55,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,1,'baja',NULL),(56,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,NULL,'baja',NULL),(57,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,NULL,'baja',NULL),(58,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(59,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(60,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(61,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(62,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(63,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,2,'baja',NULL),(64,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,NULL,'baja',NULL),(65,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,NULL,'baja',NULL),(66,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,10,'baja',NULL),(67,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,10,10,'baja',NULL),(68,'WIFI',1,'2022-03-06 09:45:00','2022-03-07 10:30:00','Problema con la señal Wi-Fi','resuelta',NULL,1,NULL,NULL,'baja',NULL);
/*!40000 ALTER TABLE `incidencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencias_subtipos`
--

DROP TABLE IF EXISTS `incidencias_subtipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidencias_subtipos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('EQUIPOS','CUENTAS','WIFI','INTERNET','SOFTWARE') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `subtipo_nombre` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `sub_subtipo` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencias_subtipos`
--

LOCK TABLES `incidencias_subtipos` WRITE;
/*!40000 ALTER TABLE `incidencias_subtipos` DISABLE KEYS */;
INSERT INTO `incidencias_subtipos` VALUES (1,'EQUIPOS','HOSTIAS',NULL),(2,'EQUIPOS','PC','RATÓN'),(3,'EQUIPOS','PC','TECLADO'),(4,'EQUIPOS','ALTAVOCES',NULL),(5,'EQUIPOS','MONITOR',NULL),(6,'EQUIPOS','PROYECTOR',NULL),(7,'EQUIPOS','PANTALLA',NULL),(8,'EQUIPOS','PANTALLA',NULL),(9,'EQUIPOS','PORTÁTIL','DE AULA'),(10,'EQUIPOS','IMPRESORA',NULL),(11,'CUENTAS','EDUCANTABRIA',NULL),(12,'CUENTAS','GOOGLE CLASSROOM',NULL),(13,'CUENTAS','DOMINIO',NULL),(14,'CUENTAS','YEDRA','GESTIONA J.EST.'),(15,'WIFI','iesmiguelherrero',NULL),(16,'WIFI','WIECAN',NULL),(17,'INTERNET','INSTALACIÓN',NULL),(18,'INTERNET','ACTUALIZACIÓN',NULL),(19,'EQUIPOS','PC','ZAP'),(20,'EQUIPOS','A','ORDENADOR'),(21,'EQUIPOS','A','ORDENADOR'),(22,'EQUIPOS','PANTALLA',NULL),(23,'EQUIPOS','PANTALLA',NULL),(24,'EQUIPOS','PANTALLA',NULL),(25,'EQUIPOS','PANTALLA',NULL),(26,'EQUIPOS','PANTALLA',NULL),(27,'EQUIPOS','PANTALLA',NULL),(28,'EQUIPOS','PANTALLA','aaaa'),(29,'EQUIPOS','PANTALLA','aaaa'),(30,'EQUIPOS','PANTALLA','aaaa'),(31,'EQUIPOS','PANTALLA','aaaa'),(32,'EQUIPOS','PANTALLA','bbbbbbb'),(33,'EQUIPOS','PANTALLA','bbbbbbb'),(34,'EQUIPOS','PANTALLA',NULL),(35,'EQUIPOS','PANTALLA',NULL),(36,'EQUIPOS','PANTALLA',NULL),(37,'EQUIPOS','PANTALLA',NULL),(38,'EQUIPOS','P',NULL),(39,'EQUIPOS','P',NULL),(40,'EQUIPOS','AAAA',NULL),(41,'EQUIPOS','AAAA',NULL);
/*!40000 ALTER TABLE `incidencias_subtipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `personal_id` int NOT NULL,
  `dominio` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `educantabria` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `perfil` enum('administrador','profesor','otros') CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL DEFAULT 'profesor',
  PRIMARY KEY (`personal_id`),
  UNIQUE KEY `educantabria_UNIQUE` (`educantabria`),
  CONSTRAINT `fk_perfiles_personal` FOREIGN KEY (`personal_id`) REFERENCES `personal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,NULL,'m','$2a$10$AC6jkMWKSXjPsfoa0hPq6uCG7UG0se39duSLiK0ey9jw2r4K514t6','administrador'),(2,NULL,'b','$2a$10$WJcL2V7MiEDCTc/d4BRSNuIqoidTUgyOYJMyV3/NCexLkuJOa60Xq','profesor'),(3,'dom003','carlos.fernandez@educantabria.es','90fe2049445178a1840bd71dc6c07ce8','administrador'),(4,'dom004','elena.gutierrez@educantabria.es','9794230d6e317739e0d2a1be87becb94','profesor'),(5,'dom005','javier.sanchez@educantabria.es','6bf1c4df57af5ade48b354bf959b4464','administrador'),(6,'dom006','laura.sanchez@educantabria.es','91a81c79944c294500eca88bd906ed13','profesor'),(7,'dom007','sergio.martinez@educantabria.es','1abc9603f106657665bcdd608f302b0b','profesor'),(8,'dom008','isabel.fernandez@educantabria.es','8b07327223bcdd1a7c4e16fd9bf04831','profesor'),(9,'dom009','pablo.gutierrez@educantabria.es','39bcf9ff587fa61fef01735543b25e60','profesor'),(10,NULL,'a','$2a$10$r6Ebw6DEFBD0CZu.VpBpHeI4DF3etnzVud4l7gx.06ZD2YgnJEc9i','administrador');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` char(9) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `direccion` varchar(80) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `localidad` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `cp` char(5) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `tlf` char(9) CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci DEFAULT NULL,
  `activo` tinyint DEFAULT '1',
  `departamento_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  KEY `fk_personal_departamentos1_idx` (`departamento_id`),
  CONSTRAINT `fk_personal_departamentos1` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'00349999q','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,4),(2,'22349678l','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,NULL),(3,'34567890C','Carlos','Fernández','García','Plaza Central 789','Torrelavega','28003','456789012',0,3),(4,'45678901D','Elena','Gutiérrez','Hernández','Ronda de la Montaña 567','Torrelavega','28004','789012345',1,1),(5,'12349678A','Juan','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,1),(6,'67890123G','Laura','Sánchez','Pérez','Ronda de la Montaña 567','Torrelavega','28004','789012345',1,2),(7,'78901234H','Sergio','Martínez','Gómez','Avenida Principal 789','Torrelavega','28005','123456789',0,3),(8,'89012345I','Isabel','Fernández','García','Plaza Central 012','Reinosa','28006','234567890',1,1),(9,'90123456J','Pablo','Gutiérrez','Hernández','Ronda Secundaria 345','Torrelavega','28007','345678901',1,1),(10,'01234567K','Eva','Ruiz','Sánchez','Camino del Bosque 678','Cabezón de la Sal','28008','456789012',1,2),(12,'23456789M','Natalia','Hernández','Gómez','Plaza del Pueblo 234','Castro Urdiales','28010','678901234',0,5),(13,'34567890N','Alejandro','Gómez','Ruiz','Sendero del Río 567','Torrelavega','28011','789012345',1,5),(14,'45678901O','Clara','López','Fernández','Calle Principal 789','Santander','28012','890123456',1,4),(15,'56789012P','Alberto','Ruiz','Martínez','Avenida Secundaria 012','Torrelavega','28013','901234567',1,4),(16,'67890123Q','Sara','Martín','Gómez','Plaza Central 345','Santander','28014','012345678',0,4),(17,'78901234R','Diego','Sánchez','Hernández','Ronda del Bosque 678','Torrelavega','28015','123456789',1,2),(18,'89012345S','Lucía','Hernández','Ruiz','Camino del Río 901','Torrelavega','28016','234567890',1,3),(19,'90123456T','Jorge','Ruiz','Gómez','Sendero Secundario 234','Santander','28017','345678901',1,3),(20,'22349678q','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,NULL),(22,'12345978A','Juan','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,2),(23,'12345178A','AAAA','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,2),(26,'92349678A','Juan','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,1),(27,'22349678A','Juan','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,1),(28,'22349678Y','Juan','Gómez','López','Calle Principal 123','Torrelavega','28001','123456789',1,1),(30,'22349678g','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(31,'22349678h','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(32,'22349678b','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(34,'22349678ñ','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(36,'22349678t','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,NULL),(37,'99349999q','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(39,'99349909q','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,1),(41,'99349900q','Juan','Gómez','López','Calle Principal 123','Torrelavega',NULL,'123456789',1,NULL);
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_api`
--

DROP TABLE IF EXISTS `usuarios_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_api` (
  `username` varchar(50) NOT NULL,
  `password` varchar(600) NOT NULL,
  `rol` enum('administrador','profesor') NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_api`
--

LOCK TABLES `usuarios_api` WRITE;
/*!40000 ALTER TABLE `usuarios_api` DISABLE KEYS */;
INSERT INTO `usuarios_api` VALUES ('a','$2a$10$yZaHf0x9ZEyQtYmcZZh3nOSxi2Nm99rWwZJqBD1ecOUME1.dHPBEi','administrador'),('b','$2a$10$NIMna9DVqog5NqrvszSvpei0dLqCNiXJcvgMVLneeel.w6OmDIS1W','profesor'),('m','$2a$10$ZYWkm8sanOSQV5XR8E3mU.Y/5kaQnxxdciY3nAN9A80Fsvnvepqly','administrador'),('z','$2a$10$4aycNgvIDVNyt6BZaZuR9.3KQjdbu8tjKJdiQYqY0g7SQzrriPV9W','profesor');
/*!40000 ALTER TABLE `usuarios_api` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-26 19:21:13
