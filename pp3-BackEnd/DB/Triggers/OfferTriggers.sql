--Nota: MySQL solo soporta triggers de un solo evento.

CREATE DEFINER=`root`@`localhost` TRIGGER `trg_offer_updateDate_for_insert` AFTER INSERT ON `offer` FOR EACH ROW update offer
set update_Date = current_date();

CREATE DEFINER=`root`@`localhost` TRIGGER `trg_offer_updateDate_for_update` AFTER UPDATE ON `offer` FOR EACH ROW update offer set offer.update_Date =current_timestamp();
