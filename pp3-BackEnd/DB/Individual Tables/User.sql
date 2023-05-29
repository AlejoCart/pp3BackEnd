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