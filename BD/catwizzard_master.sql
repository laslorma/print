/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : catwizzard_master

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2017-01-24 02:40:37
*/
CREATE DATABASE IF NOT EXISTS `catwizzard_master` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `catwizzard_master`;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `app_id` int(11) NOT NULL auto_increment,
  `domain` varchar(200) NOT NULL,
  `s3_location` varchar(21844) default NULL,
  `license_hashkey` varchar(21844) NOT NULL,
  `init_payment_date` timestamp NULL default NULL,
  `payment_period` enum('monthly','yearly','quarterly') default NULL,
  `online_date` timestamp NULL default NULL,
  `catw_instance_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  PRIMARY KEY  (`app_id`),
  UNIQUE KEY `app_id_UNIQUE` (`app_id`),
  KEY `fk_catw_instance_app_idx` (`catw_instance_id`),
  KEY `fk_company_app_idx` (`company_id`),
  CONSTRAINT `fk_catw_instance_app` FOREIGN KEY (`catw_instance_id`) REFERENCES `catw_instance` (`catw_instance_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_app` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for catw_instance
-- ----------------------------
DROP TABLE IF EXISTS `catw_instance`;
CREATE TABLE `catw_instance` (
  `catw_instance_id` int(11) NOT NULL auto_increment,
  `process_id` int(11) default NULL,
  `port` int(11) default NULL,
  `database` varchar(45) default NULL,
  `folder_root_location` varchar(200) default NULL,
  `memory_usage` int(11) default NULL,
  `server_id` int(11) NOT NULL,
  PRIMARY KEY  (`catw_instance_id`),
  UNIQUE KEY `catw_instance_id_UNIQUE` (`catw_instance_id`),
  KEY `fk_server_catw_instance_idx` (`server_id`),
  CONSTRAINT `fk_server_catw_instance` FOREIGN KEY (`server_id`) REFERENCES `server` (`server_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for catw_product
-- ----------------------------
DROP TABLE IF EXISTS `catw_product`;
CREATE TABLE `catw_product` (
  `catw_product_id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `type` enum('directive','module','service') default 'directive',
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`catw_product_id`),
  UNIQUE KEY `catw_product_id_UNIQUE` (`catw_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL auto_increment,
  `name` varchar(200) NOT NULL,
  `address` varchar(2000) default NULL,
  `phone` varchar(45) default NULL,
  `legal_identity_number` varchar(45) default NULL,
  PRIMARY KEY  (`company_id`),
  UNIQUE KEY `company_id_UNIQUE` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(65) default NULL,
  `address` varchar(200) default NULL,
  `phone` varchar(45) default NULL,
  `company_id` int(11) NOT NULL,
  PRIMARY KEY  (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`),
  KEY `fk_company_contact_idx` (`company_id`),
  CONSTRAINT `fk_company_contact` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contract_id` int(11) NOT NULL auto_increment,
  `json_setting` text,
  `price` decimal(10,2) default NULL,
  `init_payment` timestamp NULL default NULL,
  `payment_period` enum('monthly','yearly','quartely','one time') default NULL,
  `online_date` timestamp NULL default NULL,
  `final_date` timestamp NULL default NULL,
  `description` text,
  `app_id` int(11) NOT NULL,
  `catw_product_id` int(11) NOT NULL,
  PRIMARY KEY  (`contract_id`),
  UNIQUE KEY `contract_id_UNIQUE` (`contract_id`),
  KEY `fk_app_contract_idx` (`app_id`),
  KEY `fk_catw_product_contract_idx` (`catw_product_id`),
  CONSTRAINT `fk_app_contract` FOREIGN KEY (`app_id`) REFERENCES `app` (`app_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_catw_product_contract` FOREIGN KEY (`catw_product_id`) REFERENCES `catw_product` (`catw_product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `number_confirmation_op` varchar(20) default NULL,
  `bank_name` varchar(45) default NULL,
  `payment_date` timestamp NULL default NULL,
  `invoice_number` varchar(45) default NULL,
  `approved` bit(1) default NULL,
  `payer_name` varchar(45) default NULL,
  `app_name` varchar(45) default NULL,
  `json` text,
  `app_id` int(11) NOT NULL,
  PRIMARY KEY  (`payment_id`),
  UNIQUE KEY `payment_id_UNIQUE` (`payment_id`),
  KEY `fk_app_payment_idx` (`app_id`),
  CONSTRAINT `fk_app_payment` FOREIGN KEY (`app_id`) REFERENCES `app` (`app_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
  `server_id` int(11) NOT NULL auto_increment,
  `server_name` varchar(20) default NULL,
  `ip` varchar(16) default NULL,
  `capacity` int(11) default NULL,
  PRIMARY KEY  (`server_id`),
  UNIQUE KEY `server_id_UNIQUE` (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
