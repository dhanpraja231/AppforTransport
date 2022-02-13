# Flipr2Transport

clone the project on android studio.

for deployment of backend on xampp->phpmyadmin, run the following schema. 

https://drive.google.com/file/d/1L-DUI8G8Q5aPk5EIeuL4sN7MnIHfsX_4/view?usp=sharing

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 13, 2022 at 05:08 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.23



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `FLIPR`
--

-- --------------------------------------------------------

--
-- Table structure for table `BOOKING`
--

CREATE TABLE `BOOKING` (
  `DRIVER_NAME` varchar(500) NOT NULL,
  `DEALER_NAME` varchar(500) NOT NULL,
  `DEALER_EMAIL` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `BOOKING`
--

INSERT INTO `BOOKING` (`DRIVER_NAME`, `DEALER_NAME`, `DEALER_EMAIL`) VALUES
('raja', 'hema', 'hema@gmail.com'),
('karen', 'hema', 'hema@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `DEALER`
--

CREATE TABLE `DEALER` (
  `NAME` varchar(500) DEFAULT NULL,
  `EMAIL` varchar(500) NOT NULL,
  `PASSWORD` varchar(500) DEFAULT NULL,
  `PHONE` varchar(500) DEFAULT NULL,
  `NATURE` varchar(500) DEFAULT NULL,
  `WEIGHT` varchar(500) DEFAULT NULL,
  `QUANTITY` varchar(500) DEFAULT NULL,
  `CITY` varchar(500) DEFAULT NULL,
  `STATE` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `DEALER`
--

INSERT INTO `DEALER` (`NAME`, `EMAIL`, `PASSWORD`, `PHONE`, `NATURE`, `WEIGHT`, `QUANTITY`, `CITY`, `STATE`) VALUES
('hema', 'hema@gmail.com', '1234', '6379844154', 'steel', '5', '30', 'Port Blair', 'Andaman and Nicobar Islands'),
('pam', 'pam@gmail.com', '1234', '6379844152', 'bronze', '10', '30', 'Kakinada', 'Andhra Pradesh'),
('jim', 'jim@gmail.com', '1234', '6379844133', 'sand', '1', '3000', 'Porbandar', 'Gujarat'),
('andy', 'andy@gmail.com', '1234', '6379844333', 'e-waste', '100', '10', 'Pune', 'Maharashtra'),
('angela', 'angela@gmail.com', '1234', '6379844322', 'toys', '10', '1000', 'Karaikal', 'Puducherry');

-- --------------------------------------------------------

--
-- Table structure for table `DRIVER`
--

CREATE TABLE `DRIVER` (
  `NAME` varchar(500) NOT NULL,
  `AGE` varchar(500) NOT NULL,
  `EMAIL` varchar(500) NOT NULL,
  `PHONE` varchar(500) NOT NULL,
  `TRUCK_NUM` varchar(500) NOT NULL,
  `CAPACITY` varchar(500) NOT NULL,
  `EXPERIENCE` varchar(500) NOT NULL,
  `FROM_STATE_1` varchar(900) NOT NULL,
  `FROM_CITY_1` varchar(900) NOT NULL,
  `TO_STATE_1` varchar(900) NOT NULL,
  `TO_CITY_1` varchar(900) NOT NULL,
  `FROM_STATE_2` varchar(900) NOT NULL,
  `FROM_CITY_2` varchar(900) NOT NULL,
  `TO_STATE_2` varchar(900) NOT NULL,
  `TO_CITY_2` varchar(900) NOT NULL,
  `FROM_STATE_3` varchar(900) NOT NULL,
  `FROM_CITY_3` varchar(900) NOT NULL,
  `TO_STATE_3` varchar(900) NOT NULL,
  `TO_CITY_3` varchar(900) NOT NULL,
  `PASSWORD` varchar(500) NOT NULL,
  `TRANSPORTER` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `DRIVER`
--

INSERT INTO `DRIVER` (`NAME`, `AGE`, `EMAIL`, `PHONE`, `TRUCK_NUM`, `CAPACITY`, `EXPERIENCE`, `FROM_STATE_1`, `FROM_CITY_1`, `TO_STATE_1`, `TO_CITY_1`, `FROM_STATE_2`, `FROM_CITY_2`, `TO_STATE_2`, `TO_CITY_2`, `FROM_STATE_3`, `FROM_CITY_3`, `TO_STATE_3`, `TO_CITY_3`, `PASSWORD`, `TRANSPORTER`) VALUES
('raja', '30', 'raja@gmail.com', '9150294110', '10000', '5678', '10', 'Andaman and Nicobar Islands', 'Port Blair', 'Chhattisgarh', 'Durg', 'Andhra Pradesh', 'Kakinada', 'Jharkhand', 'Deoghar', 'Gujarat', 'Anand', 'Haryana', 'Bhiwani', '1234', 'mahindra'),
('karen', '34', 'karen@gmail.com', '9167323445', '9000', '2345', '10', 'Delhi', 'Delhi', 'Bihar', 'Bhagalpur', 'Puducherry', 'Karaikal', 'Tamil Nadu', 'Tiruchirappalli', 'Jammu and Kashmir', 'Udhampur', 'Mizoram', 'Lunglei', '1234', 'mahindra'),
('franklin', '34', 'frankiln@gmail.com', '9162323445', '9078', '2345', '10', 'Maharashtra', 'Pune', 'Gujarat', 'Anand', 'Odisha', 'Barbil', 'Telangana', 'Ramagundam', 'Tripura', 'Khowai', 'Uttarakhand', 'Tehri', '1234', 'ashoka'),
('murdock', '39', 'murdock@gmail.com', '9162323488', '9078', '2311', '10', 'Puducherry', 'Karaikal', 'Himachal Pradesh', 'Palampur', 'Gujarat', 'Porbandar', 'West Bengal', 'English Bazar', 'West Bengal', 'Hugli-Chinsurah', 'Tripura', 'Khowai', '1234', 'ashoka'),
('wilson', '39', 'wilson@gmail.com', '9162488111', '9078', '2311', '10', 'Puducherry', 'Karaikal', 'Madhya Pradesh', 'Gwalior', 'Madhya Pradesh', 'Ujjain', 'Uttar Pradesh', 'Aligarh', 'West Bengal', 'Nabadwip', 'Tamil Nadu', 'Vellore', '1234', 'ashoka');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


(schema is a also populated with a few entried for each table)

    in phypmyadmin, ensure that the 
       url  : "jdbc:mysql://192.168.0.101:3306/FLIPR" where FLIPR is the name of the database created from the schema
       set user: "hema"
       set password: "1234"
       
       
       after this  run the application on android studio emulator.
       
    
