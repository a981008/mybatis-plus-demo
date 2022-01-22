CREATE DATABASE IF NOT EXISTS project;
use project;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `account_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密盐',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modified_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`account_id`) USING BTREE
) COMMENT = '账号表';


INSERT INTO `account` VALUES (1, 1, 'mp', '17a1640916cfa8356adc4336a72ac75d', 'ecbe5fac60d1499595fbb98dfa854501', '程序牛人', '男', 'mp@126.com', '2020-11-10 13:46:32', '2020-11-15 17:09:28', NULL, 1, 0);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `age` tinyint(0) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modified_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`customer_id`) USING BTREE
) COMMENT = '客户表';


DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `resource_id` bigint(0) UNSIGNED NOT NULL COMMENT '主键',
  `parent_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '父id',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源名称',
  `resource_type` tinyint(0) NULL DEFAULT NULL COMMENT '资源类型(0、目录 1、菜单 2、按钮)',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识码',
  `sort` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`resource_id`) USING BTREE
) COMMENT = '资源表';

INSERT INTO `resource` VALUES (1, NULL, '系统管理', 0, NULL, NULL, 1);
INSERT INTO `resource` VALUES (2, NULL, '客户管理', 0, NULL, NULL, 2);
INSERT INTO `resource` VALUES (11, 1, '角色管理', 1, 'role/toList', NULL, 1);
INSERT INTO `resource` VALUES (12, 1, '账号管理', 1, 'account/toList', NULL, 2);
INSERT INTO `resource` VALUES (21, 2, '客户管理', 1, 'customer/toList', 'customer', 1);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modified_account_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`role_id`) USING BTREE
) COMMENT = '角色表';

INSERT INTO `role` VALUES (1, '练习角色', '练习角色', NULL, NULL, NULL, NULL, 0);

DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `role_resource_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`role_resource_id`) USING BTREE
) COMMENT = '角色资源表';


INSERT INTO `role_resource` VALUES (1, 1, 1);
INSERT INTO `role_resource` VALUES (2, 1, 2);
INSERT INTO `role_resource` VALUES (3, 1, 11);
INSERT INTO `role_resource` VALUES (4, 1, 12);
INSERT INTO `role_resource` VALUES (5, 1, 21);

alter  table account add constraint FK_account_role_id foreign key (role_id) references `role`(role_id);
alter  table role_resource add constraint FK_role_resource_role_id foreign key (role_id) references `role`(role_id);
alter  table role_resource add constraint FK_role_rerce_resourc_idD2D9 foreign key (resource_id) references `resource`(resource_id);

SET FOREIGN_KEY_CHECKS = 1;
