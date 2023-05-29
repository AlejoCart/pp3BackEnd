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