-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-04-2023 a las 01:05:46
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `userdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `SURNAME` varchar(30) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `Active` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`ID`, `NAME`, `SURNAME`, `USERNAME`, `PASSWORD`, `Active`) VALUES
(1, 'Primer', 'Usuario', 'PrimerUsuario', '$2a$10$41NwvJKYkdaGTn9ToZS6KeSW4Lu2ZJJdyWFJXFTFDmIg3Rubrylzq', 1),
(2, 'Segundo', 'Usuario', 'Segundo Usuario', '$2a$10$eI0LmMq.Csa.GXknqrFGW.mRKuWiLFawfwwWiT7jh2.jOVAdfmccm', 1),
(3, 'Usuario', 'AAKK', 'Tercer', '$2a$10$DTVyr3.bLuApGO6Xk8nWHusKgTrq9HoA6d23LDS6qw6Su./BwdIpi', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
