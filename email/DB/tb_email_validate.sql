/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Schema         : program

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 11/11/2019 14:47:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_email_validate
-- ----------------------------
DROP TABLE IF EXISTS `tb_email_validate`;
CREATE TABLE `tb_email_validate`  (
  `id` bigint(64) NOT NULL,
  `user_id` bigint(64) NOT NULL COMMENT 'user_id为用户表用户id',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reset_token` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'reset_token由UUID生成',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'type默认为resetPassword',
  `is_del` int(4) NOT NULL DEFAULT 0 COMMENT '状态 0：正常 ，1：已删除 默认 0',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
