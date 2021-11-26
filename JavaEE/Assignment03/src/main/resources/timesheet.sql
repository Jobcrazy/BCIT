SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `timesheet`;
CREATE DATABASE IF NOT EXISTS `timesheet` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `timesheet`;

CREATE USER IF NOT EXISTS 'stock'@'localhost' IDENTIFIED BY 'check';
CREATE USER IF NOT EXISTS 'stock'@'%' IDENTIFIED BY 'check';
GRANT ALL ON timesheet.* TO 'stock'@'localhost';
GRANT ALL ON timesheet.* TO 'stock'@'%';

CREATE TABLE `employee` (
  `number` int(10) UNSIGNED NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `admin` int(10) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `employee` (`number`, `username`, `password`, `name`, `admin`) VALUES
<<<<<<< HEAD
(1, 'admin', 'admin', NULL, 1),
(2, 'cst', 'cst', NULL, 0);

CREATE TABLE `timesheet` (
  `id` int(10) UNSIGNED NOT NULL,
  `employeeNumber` int(10) UNSIGNED NOT NULL,
  `endDate` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `timesheetrow` (
  `id` int(10) UNSIGNED NOT NULL,
  `timesheetId` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `projectId` int(10) UNSIGNED NOT NULL,
  `workPackageId` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `packedHours` bigint(20) NOT NULL DEFAULT '0',
  `notes` varchar(120) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `token` (
  `id` int(10) UNSIGNED NOT NULL,
  `employeeNumber` int(10) UNSIGNED NOT NULL,
  `token` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE `employee`
  ADD PRIMARY KEY (`number`),
  ADD UNIQUE KEY `username` (`username`);

ALTER TABLE `timesheet`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `timesheetrow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `timesheetId` (`timesheetId`);

ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employeeNumber` (`employeeNumber`),
  ADD KEY `token` (`token`);


ALTER TABLE `employee`
  MODIFY `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

ALTER TABLE `timesheet`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE `timesheetrow`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE `token`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
