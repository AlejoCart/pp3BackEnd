--Nota: MySQL solo soporta triggers de un solo evento.

CREATE DEFINER=`root`@`localhost` TRIGGER `trg_insert_user_update_date` BEFORE INSERT ON `offer` FOR EACH ROW set new.update_Date =current_timestamp();

CREATE DEFINER=`root`@`localhost` TRIGGER `trg_update_current_Date` BEFORE UPDATE ON `offer` FOR EACH ROW set new.update_Date = current_timestamp();

