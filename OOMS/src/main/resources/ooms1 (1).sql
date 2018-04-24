-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2017 at 12:05 PM
-- Server version: 5.5.27
-- PHP Version: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ooms1`
--

-- --------------------------------------------------------

--
-- Table structure for table `ooms_address`
--

CREATE TABLE `ooms_address` (
  `id` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_address`
--

INSERT INTO `ooms_address` (`id`, `city`, `pincode`, `state`, `street`) VALUES
(1, 'Gurgoan', '458214', 'Maharastra', 'Rajuveer road'),
(2, 'Gurgoan', '458214', 'Maharastra', 'Rajuveer road'),
(3, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(4, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(5, 'Puna', '4556585', 'Maharastra', 'Lal road'),
(6, 'Puna', '4556585', 'Maharastra', 'Lal road'),
(7, 'Pune', '4220103', 'maharastra', 'MG Road'),
(8, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(9, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(10, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(11, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(12, 'Sinnar', '422103', 'Maharastra', 'Shivaji road'),
(13, 'Puna', '4556585', 'Maharastra', 'Lal road'),
(14, 'Gurgoan1', '4582141', 'Maharastra1', 'Rajuveer road1'),
(15, 'Gurgoan1', '4582141', 'Maharastra1', 'Rajuveer road1'),
(16, 'Pune1', '42201031', 'maharastra1', 'MG Road1'),
(17, 'Nashik', '4220103', 'maharastra', 'Mahaveer Road'),
(18, 'Delhi', '422568', 'maharastra', 'Lohgoan road'),
(19, 'Solapur', '4220103', 'maharastra', 'MG Road'),
(20, 'Delhi', '422568', 'maharastra', 'asdsad');

-- --------------------------------------------------------

--
-- Table structure for table `ooms_auth_token`
--

CREATE TABLE `ooms_auth_token` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `time_of_token_issued` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_valid_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_auth_token`
--

INSERT INTO `ooms_auth_token` (`id`, `email`, `time_of_token_issued`, `token`, `token_valid_time`) VALUES
(1, 'achievers.nitin@gmail.com', '2017-11-30 16:01:01', 'ad88c3b2-8550-4f93-be31-0fb176dc80fa', 100000),
(2, 'achievers.nitin@gmail.com', '2017-11-30 17:48:06', 'c5027b44-5915-4946-95ae-e52096823899', 100000),
(3, 'achievers.nitin@gmail.com', '2017-11-30 17:49:40', '93b6152d-54b9-454c-bf8a-472676a5ca54', 100000),
(4, 'achievers.nitin@gmail.com', '2017-12-01 11:56:14', '7fe2fbd9-c01f-436f-a416-69ed6b49f5a7', 100000),
(5, 'achievers.nitin@gmail.com', '2017-12-01 13:15:14', 'd8cfbe53-c15f-4158-bad5-27e928a6cfa9', 100000),
(6, 'achievers.nitin@gmail.com', '2017-12-01 13:16:48', 'a3b7d01c-71a3-4a27-a78f-04dc5b6a1dc7', 100000),
(7, 'achievers.nitin@gmail.com', '2017-12-01 13:19:06', '4ad2c58a-7904-414d-a78d-a263cf696806', 100000),
(8, 'achievers.nitin@gmail.com', '2017-12-01 13:20:55', 'b5f60c39-feb2-4048-9e66-388dfc983015', 100000),
(9, 'achievers.nitin@gmail.com', '2017-12-01 13:23:28', '2d5c9796-ddfd-4fb5-99c0-f9a5a6c6ef9d', 100000),
(10, 'achievers.nitin@gmail.com', '2017-12-01 13:25:06', '83489d69-b544-479e-83cd-28a2acb74122', 100000),
(11, 'achievers.nitin@gmail.com', '2017-12-01 13:33:52', 'a428f6ed-15ea-40fe-abd1-26e12b6cf785', 100000),
(12, 'achievers.nitin@gmail.com', '2017-12-01 13:37:03', '7841c730-134e-4586-a1b7-5064201247d4', 100000),
(13, 'achievers.nitin@gmail.com', '2017-12-01 16:07:52', '70d6665a-2c6d-4837-b405-84e75c6be08f', 100000),
(14, 'achievers.nitin@gmail.com', '2017-12-01 16:11:03', '729cf40d-f1bb-4658-b5ec-6138e420c36b', 100000),
(15, 'achievers.nitin@gmail.com', '2017-12-01 16:25:43', '215768ba-f374-4535-91ce-4b1b3348a40c', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_catalog`
--

CREATE TABLE `ooms_catalog` (
  `id` int(11) NOT NULL,
  `date_of_creation` datetime DEFAULT NULL,
  `catalog_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_catalog`
--

INSERT INTO `ooms_catalog` (`id`, `date_of_creation`, `catalog_name`, `status`) VALUES
(1, '2017-11-08 11:50:47', 'Medical Catalog', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_catalog_items`
--

CREATE TABLE `ooms_catalog_items` (
  `catalog_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_catalog_items`
--

INSERT INTO `ooms_catalog_items` (`catalog_id`, `item_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_configuration`
--

CREATE TABLE `ooms_configuration` (
  `id` int(11) NOT NULL,
  `orderreadytodispatchemailsetting` bit(1) DEFAULT NULL,
  `orderreadytodispatchsmssetting` bit(1) DEFAULT NULL,
  `receivedorderemailsetting` bit(1) DEFAULT NULL,
  `receivedordersmssetting` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ooms_customer`
--

CREATE TABLE `ooms_customer` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `custname` varchar(255) DEFAULT NULL,
  `onboarddate` datetime DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_customer`
--

INSERT INTO `ooms_customer` (`id`, `email`, `mobile`, `custname`, `onboarddate`, `address_id`) VALUES
(1, 'sachidate123@yahoo.com', '8975874154', 'Sachin Date', '2017-11-30 15:37:29', 1),
(2, 'sachidate123@yahoo.com1', NULL, 'Sachin Date1', NULL, 15),
(3, 'nitin@gmail.com1', '89758748951', 'Nitin Pawar1', '2017-11-30 15:42:05', 16),
(4, 'sagarhase@yahoo.com', '789854785698', 'Sagar Hase', '2017-12-01 13:39:38', 17),
(5, 'ajay@zycus.com', '784554878754', 'Ajay DDhawale', '2017-12-01 13:43:04', 18),
(6, 'sagarhase33@yahoo.com', '8975874895', 'Sachin Hapase', '2017-12-01 16:34:38', 19),
(7, 'achievers.nitin@gmail.com', '1256756756', 'amol bagul', '2017-12-01 16:35:08', 20);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_customer_orders`
--

CREATE TABLE `ooms_customer_orders` (
  `customer_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_customer_orders`
--

INSERT INTO `ooms_customer_orders` (`customer_id`, `order_id`) VALUES
(1, 1),
(1, 2),
(5, 8),
(6, 11),
(7, 12),
(5, 13);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_order`
--

CREATE TABLE `ooms_order` (
  `id` int(11) NOT NULL,
  `advanceamount` double DEFAULT NULL,
  `dateoforder` datetime DEFAULT NULL,
  `remainingbalance` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalcost` double DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_order`
--

INSERT INTO `ooms_order` (`id`, `advanceamount`, `dateoforder`, `remainingbalance`, `status`, `totalcost`, `customer_id`) VALUES
(1, 200, '2017-11-30 15:37:41', 800, 'NEW', 1000, 1),
(2, 0, '2017-11-30 15:41:30', 0, 'NEW', 540, 1),
(3, 500, '2017-11-30 15:42:06', 520, 'NEW', 1020, 3),
(4, 5000, '2017-11-30 15:42:26', 99535, 'NEW', 104535, 3),
(8, 0, '2017-12-01 16:17:43', 684, 'NEW', 684, 5),
(11, 0, '2017-12-01 16:34:38', 800, 'NEW', 800, 6),
(12, 0, '2017-12-01 16:35:08', 14416, 'NEW', 14416, 7),
(13, 0, '2017-12-01 16:35:25', 10530, 'NEW', 10530, 5);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_orderitem`
--

CREATE TABLE `ooms_orderitem` (
  `id` int(11) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `productitemid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_orderitem`
--

INSERT INTO `ooms_orderitem` (`id`, `count`, `productitemid`) VALUES
(1, 10, 1),
(2, 12, 4),
(3, 10, 3),
(4, 10, 6),
(5, 10, 1),
(6, 2323, 1),
(7, 10, 1),
(8, 10, 2),
(9, 10, 2),
(10, 23, 2),
(11, 23, 5),
(12, 23, 6),
(13, 23, 3),
(19, 12, 3),
(20, 12, 6),
(23, 10, 3),
(24, 10, 10),
(25, 10, 6),
(26, 212, 12),
(27, 212, 1),
(28, 234, 4);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_order_items`
--

CREATE TABLE `ooms_order_items` (
  `order_id` int(11) NOT NULL,
  `orderitem_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_order_items`
--

INSERT INTO `ooms_order_items` (`order_id`, `orderitem_id`) VALUES
(2, 2),
(3, 3),
(3, 4),
(3, 5),
(4, 6),
(8, 19),
(8, 20),
(11, 23),
(11, 24),
(11, 25),
(12, 26),
(12, 27),
(13, 28);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_productitem`
--

CREATE TABLE `ooms_productitem` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `itemname` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `uom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_productitem`
--

INSERT INTO `ooms_productitem` (`id`, `brand`, `description`, `imageurl`, `itemname`, `price`, `status`, `uom`) VALUES
(1, 'COX', 'tablet for headche', NULL, 'Combiflame', 45, 'INACTIVE', 'NOs'),
(2, 'COX', 'tablet for headche', NULL, 'Combiflame', 45, 'INACTIVE', 'NOs'),
(3, 'COX', 'tablet for headche', NULL, 'Combiflame', 45, 'INACTIVE', 'NOs'),
(4, 'COX', 'tablet for headche', NULL, 'Combiflame', 45, 'INACTIVE', 'NOs'),
(5, 'Mylin', 'tablet for Body pain', NULL, 'Nicip plus', 12, 'INACTIVE', 'NOs'),
(6, 'Mylin', 'tablet for Body pain', NULL, 'Nicip plus', 12, 'INACTIVE', 'NOs'),
(7, 'dfg', 'fdg', NULL, 'rfgdfg', 23, 'INACTIVE', '232'),
(8, 'sdf', 'sdfsdf', NULL, 'sf', 23, 'sdf', 'sdf'),
(9, 'sdf', 'sdaf', NULL, 'sdfsdf', 23, 'INACTIVE', 'sdfsd'),
(10, 'sdf', 'sdfsdf', NULL, 'sdfsdf', 23, 'INACTIVE', 'r'),
(11, 'sdf', 'sdf', NULL, 'sdf', 34, 'INACTIVE', 'erd'),
(12, 'sdf', 'sdf', NULL, 'sdfsd', 23, 'Active', 'fggsdf');

-- --------------------------------------------------------

--
-- Table structure for table `ooms_purchase_order`
--

CREATE TABLE `ooms_purchase_order` (
  `id` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalcost` double DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_purchase_order`
--

INSERT INTO `ooms_purchase_order` (`id`, `order_date`, `status`, `totalcost`, `supplier_id`) VALUES
(1, '2017-11-30 14:45:27', NULL, 4500, 1),
(2, '2017-11-30 14:45:27', NULL, 4500, 1),
(3, '2017-11-30 17:33:17', 'NEW', 4500, 2),
(4, '2017-11-30 17:43:29', 'NEW', 7878, 2),
(5, '2017-11-30 18:53:37', 'NEW', 1035, 1),
(6, '2017-11-30 18:57:36', 'NEW', 276, 1),
(7, '2017-11-30 18:58:14', 'NEW', 276, 3),
(8, '2017-11-30 19:06:44', 'NEW', 1035, 3);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_purchase_order_items`
--

CREATE TABLE `ooms_purchase_order_items` (
  `purchase_order_id` int(11) NOT NULL,
  `orderitem_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_purchase_order_items`
--

INSERT INTO `ooms_purchase_order_items` (`purchase_order_id`, `orderitem_id`) VALUES
(1, 1),
(2, 7),
(3, 8),
(4, 9),
(5, 10),
(6, 11),
(7, 12),
(8, 13);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_supplier`
--

CREATE TABLE `ooms_supplier` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `supp_name` varchar(255) DEFAULT NULL,
  `onboarddate` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_supplier`
--

INSERT INTO `ooms_supplier` (`id`, `email`, `supp_name`, `onboarddate`, `phone`, `address_id`) VALUES
(1, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 15:37:54', '9856214758', 3),
(2, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 15:38:02', '9856214758', 4),
(3, 'balubodake@gmail.com', 'Sagar Bodake', '2017-11-30 15:38:05', '78965874575', 5),
(4, 'balubodake@gmail.com', 'Sagar Bodake', '2017-11-30 15:38:10', '78965874575', 6),
(5, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 18:40:56', NULL, 8),
(6, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 18:48:23', NULL, 9),
(7, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 18:50:26', NULL, 10),
(8, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 18:53:36', '9856214758', 11),
(9, 'npnitinpawar47@gmail.com', 'Ajay Dhawale', '2017-11-30 18:57:36', '9856214758', 12),
(10, 'balubodake@gmail.com', 'Sagar Bodake', '2017-11-30 18:58:14', '78965874575', 13);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_suppliers_orders`
--

CREATE TABLE `ooms_suppliers_orders` (
  `supplier_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_suppliers_orders`
--

INSERT INTO `ooms_suppliers_orders` (`supplier_id`, `order_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(1, 5),
(1, 6),
(3, 7),
(3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_user`
--

CREATE TABLE `ooms_user` (
  `id` int(11) NOT NULL,
  `dateofregistration` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `catalog_id` int(11) DEFAULT NULL,
  `configuaration_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_user`
--

INSERT INTO `ooms_user` (`id`, `dateofregistration`, `email`, `user_name`, `password`, `phone`, `status`, `address_id`, `catalog_id`, `configuaration_id`) VALUES
(1, '2017-11-30 15:37:06', 'achievers.nitin@gmail.com', 'Nitin Pawar', 'Pass@123', '8975972716', 1, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_user_customers`
--

CREATE TABLE `ooms_user_customers` (
  `user_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_user_customers`
--

INSERT INTO `ooms_user_customers` (`user_id`, `customer_id`) VALUES
(1, 5),
(1, 6),
(1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `ooms_user_suppliers`
--

CREATE TABLE `ooms_user_suppliers` (
  `user_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ooms_user_suppliers`
--

INSERT INTO `ooms_user_suppliers` (`user_id`, `supplier_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ooms_address`
--
ALTER TABLE `ooms_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_auth_token`
--
ALTER TABLE `ooms_auth_token`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_catalog`
--
ALTER TABLE `ooms_catalog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_catalog_items`
--
ALTER TABLE `ooms_catalog_items`
  ADD PRIMARY KEY (`catalog_id`,`item_id`),
  ADD UNIQUE KEY `UK_69n47qqpk2qir6a4e874ppknl` (`item_id`);

--
-- Indexes for table `ooms_configuration`
--
ALTER TABLE `ooms_configuration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_customer`
--
ALTER TABLE `ooms_customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3r1cd0g0o5mx1kxncc08d4cu7` (`address_id`);

--
-- Indexes for table `ooms_customer_orders`
--
ALTER TABLE `ooms_customer_orders`
  ADD PRIMARY KEY (`customer_id`,`order_id`),
  ADD UNIQUE KEY `UK_ip9drlgth8owb37vvm3l3gurv` (`order_id`);

--
-- Indexes for table `ooms_order`
--
ALTER TABLE `ooms_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1qqbc4on5wnojvmgy3tm7mba9` (`customer_id`);

--
-- Indexes for table `ooms_orderitem`
--
ALTER TABLE `ooms_orderitem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_order_items`
--
ALTER TABLE `ooms_order_items`
  ADD UNIQUE KEY `UK_ljol31hb0cmpood14pjppgp4c` (`orderitem_id`),
  ADD KEY `FKblldxfbprgmwn4n9lahwb52rt` (`order_id`);

--
-- Indexes for table `ooms_productitem`
--
ALTER TABLE `ooms_productitem`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ooms_purchase_order`
--
ALTER TABLE `ooms_purchase_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKksal5t1l2tg5wr37hay6x46kd` (`supplier_id`);

--
-- Indexes for table `ooms_purchase_order_items`
--
ALTER TABLE `ooms_purchase_order_items`
  ADD UNIQUE KEY `UK_5vug02d45mc0brjawc1qcoyga` (`orderitem_id`),
  ADD KEY `FKi6448flilxn2wq04lcdhwrjed` (`purchase_order_id`);

--
-- Indexes for table `ooms_supplier`
--
ALTER TABLE `ooms_supplier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4o6omh5qk736d22dyexn52mv6` (`address_id`);

--
-- Indexes for table `ooms_suppliers_orders`
--
ALTER TABLE `ooms_suppliers_orders`
  ADD PRIMARY KEY (`supplier_id`,`order_id`),
  ADD UNIQUE KEY `UK_g1gvbkc7ox3yd2vdjkxq4sabg` (`order_id`);

--
-- Indexes for table `ooms_user`
--
ALTER TABLE `ooms_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqgpu4tv1e7s2scv758n03w9xw` (`address_id`),
  ADD KEY `FKmddx1g7qt7ckl1lhw5pooxr0c` (`catalog_id`),
  ADD KEY `FKp586wv3dxqgot08ixt3gc6uqp` (`configuaration_id`);

--
-- Indexes for table `ooms_user_customers`
--
ALTER TABLE `ooms_user_customers`
  ADD UNIQUE KEY `UK_l7ksg057vpy2g5iymwp6mncka` (`customer_id`),
  ADD KEY `FKnj4ifqtoraii4q0jpy6seyp5f` (`user_id`);

--
-- Indexes for table `ooms_user_suppliers`
--
ALTER TABLE `ooms_user_suppliers`
  ADD UNIQUE KEY `UK_el4tenlbnes0xyw8fj3329pwi` (`supplier_id`),
  ADD KEY `FK2mh4idr4asdf9vt17wckomdhg` (`user_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ooms_address`
--
ALTER TABLE `ooms_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `ooms_auth_token`
--
ALTER TABLE `ooms_auth_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `ooms_catalog`
--
ALTER TABLE `ooms_catalog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `ooms_configuration`
--
ALTER TABLE `ooms_configuration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ooms_customer`
--
ALTER TABLE `ooms_customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `ooms_order`
--
ALTER TABLE `ooms_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `ooms_orderitem`
--
ALTER TABLE `ooms_orderitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `ooms_productitem`
--
ALTER TABLE `ooms_productitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `ooms_purchase_order`
--
ALTER TABLE `ooms_purchase_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `ooms_supplier`
--
ALTER TABLE `ooms_supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `ooms_user`
--
ALTER TABLE `ooms_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `ooms_catalog_items`
--
ALTER TABLE `ooms_catalog_items`
  ADD CONSTRAINT `FK8tbj5xjyvsf4d9op5ubonk8x2` FOREIGN KEY (`catalog_id`) REFERENCES `ooms_catalog` (`id`),
  ADD CONSTRAINT `FKe6cvoledma1icdv89po903hc0` FOREIGN KEY (`item_id`) REFERENCES `ooms_productitem` (`id`);

--
-- Constraints for table `ooms_customer`
--
ALTER TABLE `ooms_customer`
  ADD CONSTRAINT `FK3r1cd0g0o5mx1kxncc08d4cu7` FOREIGN KEY (`address_id`) REFERENCES `ooms_address` (`id`);

--
-- Constraints for table `ooms_customer_orders`
--
ALTER TABLE `ooms_customer_orders`
  ADD CONSTRAINT `FKj41sm7862ut8uk9oln4l7j9id` FOREIGN KEY (`customer_id`) REFERENCES `ooms_customer` (`id`),
  ADD CONSTRAINT `FKppd6n27plj0rbg4grqgj9d8ha` FOREIGN KEY (`order_id`) REFERENCES `ooms_order` (`id`);

--
-- Constraints for table `ooms_order`
--
ALTER TABLE `ooms_order`
  ADD CONSTRAINT `FK1qqbc4on5wnojvmgy3tm7mba9` FOREIGN KEY (`customer_id`) REFERENCES `ooms_customer` (`id`);

--
-- Constraints for table `ooms_order_items`
--
ALTER TABLE `ooms_order_items`
  ADD CONSTRAINT `FKblldxfbprgmwn4n9lahwb52rt` FOREIGN KEY (`order_id`) REFERENCES `ooms_order` (`id`),
  ADD CONSTRAINT `FKcvwl5d4jl7743ml0mms5a4oih` FOREIGN KEY (`orderitem_id`) REFERENCES `ooms_orderitem` (`id`);

--
-- Constraints for table `ooms_purchase_order`
--
ALTER TABLE `ooms_purchase_order`
  ADD CONSTRAINT `FKksal5t1l2tg5wr37hay6x46kd` FOREIGN KEY (`supplier_id`) REFERENCES `ooms_supplier` (`id`);

--
-- Constraints for table `ooms_purchase_order_items`
--
ALTER TABLE `ooms_purchase_order_items`
  ADD CONSTRAINT `FKi6448flilxn2wq04lcdhwrjed` FOREIGN KEY (`purchase_order_id`) REFERENCES `ooms_purchase_order` (`id`),
  ADD CONSTRAINT `FKkclvc4s7a0jfeajcbs2t6hs3e` FOREIGN KEY (`orderitem_id`) REFERENCES `ooms_orderitem` (`id`);

--
-- Constraints for table `ooms_supplier`
--
ALTER TABLE `ooms_supplier`
  ADD CONSTRAINT `FK4o6omh5qk736d22dyexn52mv6` FOREIGN KEY (`address_id`) REFERENCES `ooms_address` (`id`);

--
-- Constraints for table `ooms_suppliers_orders`
--
ALTER TABLE `ooms_suppliers_orders`
  ADD CONSTRAINT `FK5c47wn6jsxyryrwdx6ivomp20` FOREIGN KEY (`order_id`) REFERENCES `ooms_purchase_order` (`id`),
  ADD CONSTRAINT `FKcabkrfd2dhsdk031wjj6qpvf7` FOREIGN KEY (`supplier_id`) REFERENCES `ooms_supplier` (`id`);

--
-- Constraints for table `ooms_user`
--
ALTER TABLE `ooms_user`
  ADD CONSTRAINT `FKmddx1g7qt7ckl1lhw5pooxr0c` FOREIGN KEY (`catalog_id`) REFERENCES `ooms_catalog` (`id`),
  ADD CONSTRAINT `FKp586wv3dxqgot08ixt3gc6uqp` FOREIGN KEY (`configuaration_id`) REFERENCES `ooms_configuration` (`id`),
  ADD CONSTRAINT `FKqgpu4tv1e7s2scv758n03w9xw` FOREIGN KEY (`address_id`) REFERENCES `ooms_address` (`id`);

--
-- Constraints for table `ooms_user_customers`
--
ALTER TABLE `ooms_user_customers`
  ADD CONSTRAINT `FKgm3ysjxbwiotmy6s6uge0tpag` FOREIGN KEY (`customer_id`) REFERENCES `ooms_customer` (`id`),
  ADD CONSTRAINT `FKnj4ifqtoraii4q0jpy6seyp5f` FOREIGN KEY (`user_id`) REFERENCES `ooms_user` (`id`);

--
-- Constraints for table `ooms_user_suppliers`
--
ALTER TABLE `ooms_user_suppliers`
  ADD CONSTRAINT `FK2mh4idr4asdf9vt17wckomdhg` FOREIGN KEY (`user_id`) REFERENCES `ooms_user` (`id`),
  ADD CONSTRAINT `FK31c6lmqi9hc7v9dfad9f88jei` FOREIGN KEY (`supplier_id`) REFERENCES `ooms_supplier` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
