-- carteleraempleo.offer definition

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