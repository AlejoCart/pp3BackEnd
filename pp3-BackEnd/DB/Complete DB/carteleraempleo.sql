Create database carteleraempleo;

        --User table Definition--

-- carteleraempleo.`user` definition

CREATE TABLE `user` (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `Surname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `USERNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `ROLE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IMG` varchar(100) DEFAULT NULL,
  `Career` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--Offer Table Definition--

-- carteleraempleo.offer definition

CREATE TABLE `offer` (
  `id_offer` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `id_company` bigint NOT NULL,
  `description` varchar(150) NOT NULL,
  `offer_career` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_Date` timestamp NULL DEFAULT NULL,
  `text` varchar(30) NOT NULL,
  `modality` varchar(30) NOT NULL,
  PRIMARY KEY (`id_offer`),
  KEY `offer_company_IDX` (`id_company`) USING BTREE,
  CONSTRAINT `offer_FK` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`),
  CONSTRAINT `offer_check_modality` CHECK ((`modality` in (_utf8mb4'REMOTO',_utf8mb4'PRESENCIAL',_utf8mb4'HIBRIDO')))
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- carteleraempleo.company definition

CREATE TABLE `company` (
  `id_company` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id_company`),
  UNIQUE KEY `company_ID_IDX` (`id_company`) USING BTREE,
  UNIQUE KEY `company_un` (`name`),
  KEY `companies_name_IDX` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- carteleraempleo.user_offer definition

CREATE TABLE `user_offer` (
  `id_user` bigint NOT NULL,
  `id_offer` bigint NOT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`,`id_offer`),
  KEY `user_offer_user_ID_IDX` (`id_user`,`id_offer`) USING BTREE,
  KEY `user_offer_FK_1` (`id_offer`),
  CONSTRAINT `user_offer_FK` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `user_offer_FK_1` FOREIGN KEY (`id_offer`) REFERENCES `offer` (`id_offer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;