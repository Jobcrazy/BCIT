SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `timesheet` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `timesheet`;

CREATE USER IF NOT EXISTS 'stock'@'localhost' IDENTIFIED BY 'check';
CREATE USER IF NOT EXISTS 'stock'@'%' IDENTIFIED BY 'check';
GRANT ALL ON timesheet.* TO 'stock'@'localhost';
GRANT ALL ON timesheet.* TO 'stock'@'%';

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `admin` int(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`number`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `employee` (`number`, `username`, `password`, `name`, `admin`) VALUES
(1, 'admin', 'admin', NULL, 1),
(2, 'cst', 'cst', NULL, 0);

DROP TABLE IF EXISTS `timesheet`;
CREATE TABLE IF NOT EXISTS `timesheet` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `employeeNumber` int(10) UNSIGNED NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `timesheetrow`;
CREATE TABLE IF NOT EXISTS `timesheetrow` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `timesheetId` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `projectId` int(10) UNSIGNED NOT NULL,
  `workPackageId` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `packedHours` bigint(20) NOT NULL DEFAULT '0',
  `notes` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `timesheetId` (`timesheetId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
