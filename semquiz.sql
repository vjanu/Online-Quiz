-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2018 at 02:50 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `semquiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `question` varchar(1100) NOT NULL,
  `answer` varchar(11) NOT NULL,
  `mark` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `qna`
--

CREATE TABLE `qna` (
  `id` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `question` varchar(1000) NOT NULL,
  `answer1` varchar(100) NOT NULL,
  `answer2` varchar(100) NOT NULL,
  `answer3` varchar(100) NOT NULL,
  `answer4` varchar(100) NOT NULL,
  `correctanswer` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qna`
--

INSERT INTO `qna` (`id`, `category`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correctanswer`) VALUES
(5, 'Science', 'In anatomy, what is the name given for wing bone in the human skeleton?', 'Clavicle', 'Scapula', 'Thoracic', 'Humerus', '1'),
(2, 'Science', 'What is Geiger counter used for?', 'To detect electromagnetic waves', 'To measure electrostatic charge', 'To detect gamma rays and beta particles', 'To measure electric current', '2'),
(3, 'Science', 'Which organ produces insulin in the body?', 'Liver', 'Small Intestine', 'Kidneys', 'Pancreas', '4'),
(4, 'Science', 'Which of the following is a large blood vessel that carries blood away from the heart?', 'Vein', 'Artery', 'Capillary', 'Nerve', '1'),
(1, 'Science', 'When total internal reflection occurs from a prism, ____ of incident light is reflected back to denser medium. Fill on the blank.', '20%', '50%', '100%', '70%', '1'),
(6, 'Science', 'Which element does not conduct electricity?', 'Carbon', 'Manganese', 'Mercury', 'Calcium', '4'),
(7, 'Science', 'Brass gets discoloured in air because of the presence of which of the following gases in air?', 'Oxygen', 'Hydrogen sulphide', 'Carbon dioxide', 'Nitrogen', '2'),
(8, 'Science', 'Which of the following is used in pencils?', 'Graphite', 'Silicon', 'Charcoal', 'Phosphorous', '1'),
(9, 'Science', 'Chemical formula for salt is?', 'NaAlO2', 'H2O', 'NaCl', 'CaSiO3', '3'),
(10, 'Science', 'Chemical formula for water is?', 'H30', 'H2O', 'H2SO4', 'H2S', '2'),
(11, 'Sports', 'Which team has won the most world cups in cricket?', 'India', 'England', 'Sri Lanka', 'Australia', '4'),
(12, 'Sports', 'Who is the leading run scorer in ODI cricket?', 'Ricky ponting', 'Bryan Lara', 'Sachin Tendulkar', 'Virat Kholi', '3'),
(13, 'Sports', 'Which team has won the most world cups in football?', 'Germany', 'Argentina', 'France', 'Brazil', '4'),
(14, 'Sports', 'Who has scored the highest number of goals in one match?', 'Cristiano Ronaldo', 'Lionel Messi', 'Kaka', 'Archie Thompson', '1'),
(15, 'Sports', 'Who has the most clay court titles in Tennis?', 'Guillermo Vilas', 'Rafael Nadal', 'Ivan Lendi', 'Thomas Muster', '2'),
(16, 'Sports', 'In the 1994 Winter Olympics, how many athletes represented Israel?', 'One', 'Two', 'None', 'Three', '1'),
(17, 'Sports', 'Which racket sport made its Olympic debut in 1992?', 'Cricket', 'Tennis', 'Badminton', 'Water Polo', '3'),
(18, 'Sports', 'Which sport is played on the largest pitch?', 'Cricket', 'Rugby', 'Tennis', 'Polo', '4'),
(19, 'Sports', 'What significant sporting event took place on November 22nd 2003?', 'Rugby World Cup Final', 'Cricket World Cup Final', 'Base Ball World Cup Final', 'Volley Ball World Cup Final', '1'),
(20, 'Sports', 'Who was the first cricketer to achieve 10,000 runs in test cricket?', 'Sanath Jayasuriya', 'Sunil Gavasker', 'Sachin Tendulkar', 'Virat Kholi', '2'),
(45490027, 'Sports', 'Who is the 1st person to score T20 hundred?', 'Chris Gayle', 'Virat Kohli', 'Ricky Ponting', 'Brendon McCullum', '1'),
(4182693, 'Sports', 'Which country scored the lowest score in ODI?', 'Canada', 'Kenya', 'Netherland', 'UAE', '1');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`id`, `username`) VALUES
(1, 'root');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `type` varchar(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `type`) VALUES
('admin', '123', 'Admin'),
('root', 'sdf', 'Student'),
('pns', 'pns', 'Teacher'),
('tea', 'tea', 'Teacher'),
('viraj', '123', 'Admin'),
('poo', '123', 'Student'),
('savi', 'savi', 'Student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `qna`
--
ALTER TABLE `qna`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
