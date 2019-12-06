/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Schema         : program

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 05/12/2019 16:37:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_log`;
CREATE TABLE `tb_user_log`  (
  `id` bigint(64) NOT NULL COMMENT '主键ID',
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `type_id` int(4) NOT NULL COMMENT '操作类型 1:登录',
  `source_id` int(4) NULL DEFAULT NULL COMMENT '设备来源 1:PC 2:WX_APP 3:IOS 4:ANDROID 5:WAP',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `relative_id` bigint(64) NULL DEFAULT NULL COMMENT '相关ID',
  `is_del` int(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0：未删除，1： 已删除',
  `operator` bigint(64) NOT NULL COMMENT '操作者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `row_version` bigint(64) NULL DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`  (
  `id` bigint(64) NOT NULL COMMENT '主键ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `gender` int(4) NOT NULL DEFAULT 0 COMMENT '性别 0：未设定 1：男  2：女，默认0',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `avatar_url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `state` int(4) NOT NULL DEFAULT 0 COMMENT '状态 0：正常 1：禁用， 默认 0',
  `is_del` int(4) NOT NULL DEFAULT 0 COMMENT '状态 0：正常 ，1：已删除 默认 0',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `operator` bigint(64) NOT NULL COMMENT '操作者ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `last_login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
