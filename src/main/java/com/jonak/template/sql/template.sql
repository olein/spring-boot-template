-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 20, 2017 at 07:07 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `template`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(10) NOT NULL,
  `zip` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48804 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `street`, `zip`) VALUES
(48791, '123', 5),
(48792, '123', 5),
(48793, '123', 5),
(48794, '123', 5),
(48795, '123', 5),
(48796, '123', 5),
(48797, '123', 5),
(48798, '123', 5),
(48799, '123', 5),
(48800, '123', 5);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `mobile`, `address_id`) VALUES
(1, 'fahim', '01683802249', 48795),
(2, 'fahim', '01683802249', 48793),
(3, 'fahim', '01683802249', 48791),
(4, 'fahim', '01683802249', 48794),
(5, 'fahim', '01683802249', 48792),
(6, 'fahim', '01683802249', 48796),
(7, 'fahim', '01683802249', 48797),
(8, 'fahim', '01683802249', 48798),
(9, 'fahim', '01683802249', 48800),
(10, 'fahim', '01683802249', 48799);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
