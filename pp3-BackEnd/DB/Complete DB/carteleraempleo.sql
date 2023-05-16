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

CREATE TABLE `offer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `company` varchar(30) NOT NULL,
  `description` varchar(150) NOT NULL,
  `career` varchar(30) NOT NULL,
  `updateDate` varchar(30) NOT NULL,
  `text` varchar(30) NOT NULL,
  `modality` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `offer_ck_modality` CHECK (((`modality` = _utf8mb4'presencial') or (`modality` = _utf8mb4'hibrida') or (`modality` = _utf8mb4'remoto')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;