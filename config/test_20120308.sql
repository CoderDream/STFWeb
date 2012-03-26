/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-03-08 12:45:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `acl`
-- ----------------------------
DROP TABLE IF EXISTS `acl`;
CREATE TABLE `acl` (
  `role_function` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'role_function = role_name + "_" + function_name',
  `role_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `function_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'the name of function',
  PRIMARY KEY (`role_function`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of acl
-- ----------------------------
INSERT INTO `acl` VALUES ('ROLE_admindoAdministration', 'ROLE_admin', 'doAdministration');
INSERT INTO `acl` VALUES ('ROLE_admindoBankPayment', 'ROLE_admin', 'doBankPayment');
INSERT INTO `acl` VALUES ('ROLE_admindoBankProfileSetup', 'ROLE_admin', 'doBankProfileSetup');
INSERT INTO `acl` VALUES ('ROLE_admindoBankRegistration', 'ROLE_admin', 'doBankRegistration');
INSERT INTO `acl` VALUES ('ROLE_admindoFileUploadTransaction', 'ROLE_admin', 'doAdminFileUploadTransaction');
INSERT INTO `acl` VALUES ('ROLE_admindoLogin', 'ROLE_admin', 'doLogin');
INSERT INTO `acl` VALUES ('ROLE_admindoSystemSettings', 'ROLE_admin', 'doSystemSettings');
INSERT INTO `acl` VALUES ('ROLE_normaldoFileUpload', 'ROLE_normal', 'doFileUpload');
INSERT INTO `acl` VALUES ('ROLE_normaldoFileUploadTransaction', 'ROLE_normal', 'doReportFileUploadTransaction');
INSERT INTO `acl` VALUES ('ROLE_normaldoLogin', 'ROLE_normal', 'doLogin');
INSERT INTO `acl` VALUES ('ROLE_normaldoReportEntity', 'ROLE_normal', 'doReportEntity');

-- ----------------------------
-- Table structure for `demo`
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `username` varchar(30) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for `function`
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `function_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'the name of function',
  `function_desc` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'detail information',
  `function_display` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'content of display on the GUI or menu',
  `function_path` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the access path to this function',
  `function_parent` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'parent function',
  `function_order` varchar(6) COLLATE utf8_bin NOT NULL COMMENT 'use to organize menu in order\r\n            the top function should be a integer as such 100000, 140000\r\n            the first 2 bit is for top function',
  `function_enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`function_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='define system functions & path';

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('doAdminFileUploadTransaction', 'doFileUploadTransaction', 'File Upload Transaction', '/admin/doFileUploadTransaction.action*', 'doAdministration', '020400', '1');
INSERT INTO `function` VALUES ('doAdministration', 'doAdministration', 'Administration', '/login/doBLogin.action*', null, '020000', '1');
INSERT INTO `function` VALUES ('doBankPayment', 'doBankPayment', 'Bank Payment', '/admin/doBankPayment.action*', 'doAdministration', '020300', '1');
INSERT INTO `function` VALUES ('doBankProfileSetup', 'doBankProfileSetup', 'Bank Profile Setup', '/admin/doBankProfileSetup.action*', 'doAdministration', '020200', '1');
INSERT INTO `function` VALUES ('doBankRegistration', 'doBankRegistration', 'Bank Registration', '/admin/doBankRegistration.action*', 'doAdministration', '020100', '1');
INSERT INTO `function` VALUES ('doFileUpload', 'doFileUpload', 'File Upload', '/reportentity/doFileUpload.action*', 'doReportEntity', '010100', '1');
INSERT INTO `function` VALUES ('doLogin', 'doLogin', 'Login ', '/login/doLogin.action*', null, '000000', '1');
INSERT INTO `function` VALUES ('doReportEntity', 'doReportEntity', 'Report Entity', '/login/doALogin.action*', null, '010000', '1');
INSERT INTO `function` VALUES ('doReportFileUploadTransaction', 'doFileUploadTransaction', 'File Upload Transaction', '/reportentity/doFileUploadTransaction.action*', 'doReportEntity', '010200', '1');
INSERT INTO `function` VALUES ('doSystemSettings', 'doSystemSettings', 'System Settings', '/admin/doSystemSettings.action*', 'doAdministration', '020500', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ROLE_admin', 'admin', '1');
INSERT INTO `role` VALUES ('ROLE_normal', 'normal', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `report_entity_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_enable` tinyint(1) NOT NULL,
  `user_register_date` date NOT NULL,
  `user_expiration_date` date DEFAULT NULL,
  `user_passwd_exp_date` date DEFAULT NULL,
  `user_email` varchar(64) COLLATE utf8_bin NOT NULL,
  `user_question1` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer1` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_question2` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer2` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_question3` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer3` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='user''s basic information';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'ceb4f32325eda6142bd65215f4c0f371', null, '1', '2012-02-22', '2012-09-29', '2012-09-08', 'admin@hp.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('test', '889255f1c9c8f12a353be255f78a848b', null, '1', '2012-02-22', '2012-09-29', '2012-09-08', 'linx@hp.com', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('001', 'spring', '25', '1', '成都市', 'spring');
INSERT INTO `users` VALUES ('903b17e135a3c1ef0135a3c34ac30000', 'xulin', '123', '1', '123', '123');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'user_role = user_name + "_" + role_name',
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('adminROLE_admin', 'admin', 'ROLE_admin');
INSERT INTO `user_role` VALUES ('normalROLE_test', 'test', 'ROLE_normal');
