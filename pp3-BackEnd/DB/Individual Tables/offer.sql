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