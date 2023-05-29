-- carteleraempleo.company definition

CREATE TABLE `company` (
  `id_company` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id_company`),
  UNIQUE KEY `company_ID_IDX` (`id_company`) USING BTREE,
  UNIQUE KEY `company_un` (`name`),
  KEY `companies_name_IDX` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;