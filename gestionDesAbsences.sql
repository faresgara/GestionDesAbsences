-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionabsences
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `absence`
--

DROP TABLE IF EXISTS `absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absence` (
  `idAbsence` int NOT NULL AUTO_INCREMENT,
  `idEtudiant` int NOT NULL,
  `idEnseignant` int NOT NULL,
  `idMatiere` int NOT NULL,
  `idClasse` int NOT NULL,
  `numSeance` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idAbsence`),
  KEY `fk_Absence_Etudiant_idx` (`idEtudiant`),
  KEY `fk_Absence_Classe1_idx` (`idClasse`),
  KEY `fk_Absence_matiere1_idx` (`idMatiere`),
  KEY `fk_Absence_Enseignant1_idx` (`idEnseignant`),
  CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`idEtudiant`),
  CONSTRAINT `absence_ibfk_2` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignant` (`idEnseignant`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absence`
--

LOCK TABLES `absence` WRITE;
/*!40000 ALTER TABLE `absence` DISABLE KEYS */;
INSERT INTO `absence` VALUES (1,2,1,2,1,2,'2018-01-04'),(2,4,5,3,2,6,'2018-01-05'),(5,1,1,1,2,1,'2018-01-09'),(6,1,1,1,2,1,'2018-01-02'),(7,1,1,1,1,1,'2018-01-03'),(8,1,2,4,1,1,'2018-01-09'),(10,1,1,1,1,2,'2018-01-10'),(11,2,1,5,8,4,'2018-01-09'),(16,5,2,2,3,1,'2018-01-15'),(18,9,1,1,3,2,'2018-01-15'),(19,5,2,2,3,1,'2018-01-15'),(21,9,1,1,3,1,'2018-01-15');
/*!40000 ALTER TABLE `absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classe` (
  `idClasse` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) DEFAULT NULL,
  `niveau` int DEFAULT NULL,
  `filiere` varchar(45) DEFAULT 'Ingénierie Des Connaissances et Des Données',
  PRIMARY KEY (`idClasse`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` VALUES (1,'1 Année / A',1,'Ingénierie Des Connaissances et Des Données'),(2,'1 Année / B',1,'Ingénierie Des Connaissances et Des Données'),(3,'2 Année / A',2,'Ingénierie Des Connaissances et Des Données'),(4,'2 Année / B',2,'Ingénierie Des Connaissances et Des Données'),(5,'BigData/BI',3,'Ingénierie Des Connaissances et Des Données'),(6,'DataMining/IngenieurieDocumentaire',3,'Ingénierie Des Connaissances et Des Données');
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `correspondance`
--

DROP TABLE IF EXISTS `correspondance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correspondance` (
  `idMatiere` int NOT NULL,
  `idClasse` int NOT NULL,
  `idEnseignant` int NOT NULL,
  KEY `idMatiere` (`idMatiere`),
  KEY `idClasse` (`idClasse`),
  KEY `idEnseignant` (`idEnseignant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correspondance`
--

LOCK TABLES `correspondance` WRITE;
/*!40000 ALTER TABLE `correspondance` DISABLE KEYS */;
INSERT INTO `correspondance` VALUES (1,3,1),(1,4,1),(2,3,1),(2,3,2),(3,5,5),(4,6,6),(5,3,2),(5,4,2),(6,3,1),(6,4,1),(7,1,5),(7,2,5),(100,100,100),(1,1,9);
/*!40000 ALTER TABLE `correspondance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enseignant` (
  `idEnseignant` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEnseignant`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enseignant`
--

LOCK TABLES `enseignant` WRITE;
/*!40000 ALTER TABLE `enseignant` DISABLE KEYS */;
INSERT INTO `enseignant` VALUES (1,'Youssfi','Sihame','Sihame','0000'),(2,'Malika','Hanane','Malika','0000'),(3,'Sennouni','Amine','Sennouni','0000'),(4,'Rhoule','Hanaae','Rhoule','0000'),(5,'Bachr','Ahmed','BachrB','0000'),(6,'Amimi','Med Oussama','AmimiM','0000'),(8,'Abdalaoui','Samir','AbdalaouiS','0000');
/*!40000 ALTER TABLE `enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etudiant` (
  `idEtudiant` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `idClasse` int NOT NULL,
  `login` varchar(45) NOT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `niveau` int NOT NULL,
  PRIMARY KEY (`idEtudiant`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` VALUES (1,'El Meslouhi','Mouaad',1,'Mouaad','0000',1),(2,'Khalouq','Mounir',1,'Mounir','0000',1),(3,'Zrhal','Oumaima',4,'Oumaima','0000',1),(4,'Zarud','Najwa',1,'Najwa','0000',1),(5,'Chahma','Oumaima',3,'OumaimaC','0000',2),(6,'Souhail','Idrissi',5,'Idrissi','0000',3),(7,'Soufi','Ali',6,'AliS','0000',3),(8,'El hajouli','samia',2,'SamiaH','0000',2),(9,'Tagnaouiti','Ghita',3,'GhitaT','0000',3),(10,'Safwani','Khalid',4,'KhalidS','0000',1),(11,'Kawtari','Hamid',5,'KawtariH','0000',2),(12,'Smati','Hichame',6,'HichameS','0000',2),(13,'EL Bsabssi ','Imane',1,'ImaneB','0000',2),(14,'Amimi','Mohamed Oussama',2,'MedOussamaA','0000',3),(15,'Garnaoui','Ahmed',2,'GarA','0000',1),(16,'Ghali','Ali',1,'AliG','0000',1);
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matiere` (
  `idMatiere` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMatiere`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere`
--

LOCK TABLES `matiere` WRITE;
/*!40000 ALTER TABLE `matiere` DISABLE KEYS */;
INSERT INTO `matiere` VALUES (1,'JAVA'),(2,'UML'),(3,'BIG DATA'),(4,'DATA MINING'),(5,'GED'),(6,'SAE'),(7,'PHP'),(8,'RESEAUX'),(9,'UNIMARC'),(10,'GOUVERNANCE DOCUMENTAIRE'),(11,'Droit');
/*!40000 ALTER TABLE `matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsable` (
  `idResponsable` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idResponsable`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable`
--

LOCK TABLES `responsable` WRITE;
/*!40000 ALTER TABLE `responsable` DISABLE KEYS */;
INSERT INTO `responsable` VALUES (1,'ZAKKA','Fatima Zahra','FatimaZ','0000'),(2,'Chawki','Ilham','ChawkiI','0000'),(3,'Faysali','Hamza','FaysaliH','0000'),(4,'Ouatabe','Zakaria','OuatabeZ','0000'),(5,'Kaddami','Basma','KaddamiB','0000');
/*!40000 ALTER TABLE `responsable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09 21:31:13
