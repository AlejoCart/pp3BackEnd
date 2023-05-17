Create database carteleraempleo;

        --User table Definition--

CREATE TABLE `User` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `SURNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT
  NULL,
  `USERNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `ROLE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IMG` varchar(100) DEFAULT NULL,
  `Career` varchar(100) NOT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--Offer Table Definition--

-- carteleraempleo.offer definition

CREATE TABLE `offer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `company` varchar(30) NOT NULL,
  `description` varchar(150) NOT NULL,
  `career` varchar(30) NOT NULL,
  `update_Date` timestamp NOT NULL,
  `text` varchar(30) NOT NULL,
  `modality` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `offer_check_modality` CHECK ((`modality` in (_utf8mb4'REMOTO',_utf8mb4'PRESENCIAL',_utf8mb4'HIBRIDO')))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;