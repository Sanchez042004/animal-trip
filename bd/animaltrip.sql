-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-06-2024 a las 03:40:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `animaltrip`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `documento_cliente` varchar(50) NOT NULL,
  `nombre_cliente` varchar(25) NOT NULL,
  `direccion_cliente` text NOT NULL,
  `telefono_cliente` varchar(10) NOT NULL,
  `tipo_documento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor`
--

CREATE TABLE `conductor` (
  `id_conductor` int(11) NOT NULL,
  `nombre_conductor` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destino`
--

CREATE TABLE `destino` (
  `id_destino` int(11) NOT NULL,
  `nombre_destino` varchar(25) NOT NULL,
  `id_ruta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `descripcion_servicio` text NOT NULL,
  `precio_total` int(11) NOT NULL,
  `documento_cliente` varchar(50) NOT NULL,
  `id_reserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `limitacion`
--

CREATE TABLE `limitacion` (
  `id_limitacion` int(11) NOT NULL,
  `tipo_limitacion` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `id_mascota` int(11) NOT NULL,
  `nombre_mascota` varchar(25) NOT NULL,
  `edad_mascota` int(11) NOT NULL,
  `sexo_mascota` char(1) NOT NULL,
  `peso_mascota` float NOT NULL,
  `carnet_vacuna` char(1) NOT NULL,
  `id_raza` int(11) NOT NULL,
  `documento_cliente` varchar(50) NOT NULL,
  `id_tipo_mascota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota_limitacion`
--

CREATE TABLE `mascota_limitacion` (
  `id_mascota` int(11) NOT NULL,
  `id_limitacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesional`
--

CREATE TABLE `profesional` (
  `id_profesional` int(11) NOT NULL,
  `nombre_profesional` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `raza`
--

CREATE TABLE `raza` (
  `id_raza` int(11) NOT NULL,
  `nombre_raza` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `fecha_reserva` date NOT NULL,
  `hora_reserva` time NOT NULL,
  `id_tipo_viaje` int(11) NOT NULL,
  `id_ruta` int(11) NOT NULL,
  `documento_cliente` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_destino`
--

CREATE TABLE `reserva_destino` (
  `id_reserva` int(11) NOT NULL,
  `id_destino` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_mascota`
--

CREATE TABLE `reserva_mascota` (
  `id_reserva` int(11) NOT NULL,
  `id_mascota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `id_ruta` int(11) NOT NULL,
  `nombre_ruta` varchar(25) NOT NULL,
  `id_profesional` int(11) NOT NULL,
  `id_conductor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `id_tipo_documento` int(11) NOT NULL,
  `nombre_documento` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_mascota`
--

CREATE TABLE `tipo_mascota` (
  `id_tipo_mascota` int(11) NOT NULL,
  `tipo_mascota` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_viaje`
--

CREATE TABLE `tipo_viaje` (
  `id_tipo_viaje` int(11) NOT NULL,
  `tipo_viaje` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`documento_cliente`),
  ADD KEY `tipo_documento` (`tipo_documento`);

--
-- Indices de la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD PRIMARY KEY (`id_conductor`);

--
-- Indices de la tabla `destino`
--
ALTER TABLE `destino`
  ADD PRIMARY KEY (`id_destino`),
  ADD KEY `id_ruta` (`id_ruta`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `documento_cliente` (`documento_cliente`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `limitacion`
--
ALTER TABLE `limitacion`
  ADD PRIMARY KEY (`id_limitacion`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id_mascota`),
  ADD KEY `id_raza` (`id_raza`),
  ADD KEY `id_tipo_mascota` (`id_tipo_mascota`),
  ADD KEY `documento_cliente` (`documento_cliente`);

--
-- Indices de la tabla `mascota_limitacion`
--
ALTER TABLE `mascota_limitacion`
  ADD PRIMARY KEY (`id_mascota`,`id_limitacion`),
  ADD KEY `id_limitacion` (`id_limitacion`);

--
-- Indices de la tabla `profesional`
--
ALTER TABLE `profesional`
  ADD PRIMARY KEY (`id_profesional`);

--
-- Indices de la tabla `raza`
--
ALTER TABLE `raza`
  ADD PRIMARY KEY (`id_raza`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_tipo_viaje` (`id_tipo_viaje`),
  ADD KEY `documento_cliente` (`documento_cliente`),
  ADD KEY `id_ruta` (`id_ruta`);

--
-- Indices de la tabla `reserva_destino`
--
ALTER TABLE `reserva_destino`
  ADD PRIMARY KEY (`id_reserva`,`id_destino`),
  ADD KEY `id_destino` (`id_destino`);

--
-- Indices de la tabla `reserva_mascota`
--
ALTER TABLE `reserva_mascota`
  ADD PRIMARY KEY (`id_reserva`,`id_mascota`),
  ADD KEY `id_mascota` (`id_mascota`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`id_ruta`),
  ADD KEY `id_profesional` (`id_profesional`),
  ADD KEY `id_conductor` (`id_conductor`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`id_tipo_documento`);

--
-- Indices de la tabla `tipo_mascota`
--
ALTER TABLE `tipo_mascota`
  ADD PRIMARY KEY (`id_tipo_mascota`);

--
-- Indices de la tabla `tipo_viaje`
--
ALTER TABLE `tipo_viaje`
  ADD PRIMARY KEY (`id_tipo_viaje`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `conductor`
--
ALTER TABLE `conductor`
  MODIFY `id_conductor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `limitacion`
--
ALTER TABLE `limitacion`
  MODIFY `id_limitacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id_mascota` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `profesional`
--
ALTER TABLE `profesional`
  MODIFY `id_profesional` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `raza`
--
ALTER TABLE `raza`
  MODIFY `id_raza` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_ruta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_mascota`
--
ALTER TABLE `tipo_mascota`
  MODIFY `id_tipo_mascota` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_viaje`
--
ALTER TABLE `tipo_viaje`
  MODIFY `id_tipo_viaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`);

--
-- Filtros para la tabla `destino`
--
ALTER TABLE `destino`
  ADD CONSTRAINT `destino_ibfk_1` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`documento_cliente`) REFERENCES `cliente` (`documento_cliente`),
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`);

--
-- Filtros para la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`id_raza`) REFERENCES `raza` (`id_raza`),
  ADD CONSTRAINT `mascota_ibfk_2` FOREIGN KEY (`id_tipo_mascota`) REFERENCES `tipo_mascota` (`id_tipo_mascota`),
  ADD CONSTRAINT `mascota_ibfk_3` FOREIGN KEY (`documento_cliente`) REFERENCES `cliente` (`documento_cliente`);

--
-- Filtros para la tabla `mascota_limitacion`
--
ALTER TABLE `mascota_limitacion`
  ADD CONSTRAINT `mascota_limitacion_ibfk_1` FOREIGN KEY (`id_mascota`) REFERENCES `mascota` (`id_mascota`),
  ADD CONSTRAINT `mascota_limitacion_ibfk_2` FOREIGN KEY (`id_limitacion`) REFERENCES `limitacion` (`id_limitacion`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_tipo_viaje`) REFERENCES `tipo_viaje` (`id_tipo_viaje`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`documento_cliente`) REFERENCES `cliente` (`documento_cliente`),
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`);

--
-- Filtros para la tabla `reserva_destino`
--
ALTER TABLE `reserva_destino`
  ADD CONSTRAINT `reserva_destino_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`),
  ADD CONSTRAINT `reserva_destino_ibfk_2` FOREIGN KEY (`id_destino`) REFERENCES `destino` (`id_destino`);

--
-- Filtros para la tabla `reserva_mascota`
--
ALTER TABLE `reserva_mascota`
  ADD CONSTRAINT `reserva_mascota_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`),
  ADD CONSTRAINT `reserva_mascota_ibfk_2` FOREIGN KEY (`id_mascota`) REFERENCES `mascota` (`id_mascota`);

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `ruta_ibfk_1` FOREIGN KEY (`id_profesional`) REFERENCES `profesional` (`id_profesional`),
  ADD CONSTRAINT `ruta_ibfk_2` FOREIGN KEY (`id_conductor`) REFERENCES `conductor` (`id_conductor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
