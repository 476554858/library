/*
 Navicat MySQL Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : 101.34.188.104:3305
 Source Schema         : library_db

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 12/05/2022 19:40:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL COMMENT '书名',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `press` varchar(255) DEFAULT NULL COMMENT '出版社',
  `category` int(10) DEFAULT NULL COMMENT '分类',
  `status` tinyint(1) DEFAULT NULL COMMENT '图书状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES (9, '神墓', '辰东', '神木出版社', 0, 0);
INSERT INTO `t_book` VALUES (10, '不死不灭', '辰东', '不灭出版社', 0, 0);
INSERT INTO `t_book` VALUES (11, '长生界', '辰东', '长生出版社', 0, 0);
INSERT INTO `t_book` VALUES (12, '遮天', '辰东', '这天出版社', 0, 1);
INSERT INTO `t_book` VALUES (13, '完美世界', '陈东', '完美出版社', 0, 1);
INSERT INTO `t_book` VALUES (14, '圣墟', '陈东', '圣墟出版社', 0, 0);
INSERT INTO `t_book` VALUES (15, '元尊', '天蚕土豆', '元尊出版社', 1, 0);
INSERT INTO `t_book` VALUES (16, '斗破苍穹', '天蚕土豆', '斗破出版社', 1, 0);
INSERT INTO `t_book` VALUES (17, '武动乾坤', '天蚕土豆', '武动出版社', 1, 0);
INSERT INTO `t_book` VALUES (18, '大主宰', '天蚕土豆', '大出版社', 1, 0);
INSERT INTO `t_book` VALUES (19, '星峰传说', '西红柿', '传说出版社', 2, 0);
INSERT INTO `t_book` VALUES (20, '春忙', '西红柿', '春忙出版社', 2, 2);
INSERT INTO `t_book` VALUES (21, '星辰变', '西红柿', '星辰出版社', 2, 0);
INSERT INTO `t_book` VALUES (22, '盘龙', '西红柿', '盘龙出版社', 2, 0);
INSERT INTO `t_book` VALUES (23, '慌忙集', '西红柿', '慌忙出版社', 2, 0);
INSERT INTO `t_book` VALUES (24, '逆天', '耳根', '逆天出版社', 3, 0);
INSERT INTO `t_book` VALUES (25, '仙逆', '耳根', '仙逆出版社', 3, 0);
INSERT INTO `t_book` VALUES (26, '求魔', '耳根', '求魔出版社', 3, 0);
INSERT INTO `t_book` VALUES (27, '一念永恒', '耳根', '永恒出版社', 3, 0);
INSERT INTO `t_book` VALUES (28, '朱雀集', '猫腻', '朱雀集出版社', 4, 0);
INSERT INTO `t_book` VALUES (29, '庆余年', '猫腻', '玉面出版社', 4, 0);
INSERT INTO `t_book` VALUES (30, '讲野', '猫腻', '讲野出版社', 4, 0);
INSERT INTO `t_book` VALUES (31, '择天记', '猫腻', '择天出版社', 4, 0);
INSERT INTO `t_book` VALUES (32, '大道朝天', '猫腻', '大道出版社', 4, 0);
INSERT INTO `t_book` VALUES (33, '淄川', '老朱', '淄川出版社', 4, 0);
INSERT INTO `t_book` VALUES (34, '我家的大明局长', '老朱', '大名出版社', 4, 0);
INSERT INTO `t_book` VALUES (35, '鬼吹灯', '张牧野', '吹灯出版社', 4, 0);
INSERT INTO `t_book` VALUES (36, '此间少年', '江南', '少年出版社', 3, 0);
INSERT INTO `t_book` VALUES (37, '九州缥缈', '江南', '飘渺出版社', 4, 0);
INSERT INTO `t_book` VALUES (38, '上海堡垒', '江南', '堡垒出版社', 4, 0);
INSERT INTO `t_book` VALUES (39, '天龙八部', '金庸', '天龙出版社', 0, 0);
INSERT INTO `t_book` VALUES (40, '倚天屠龙记', '金庸', '屠龙出版社', 0, 0);
INSERT INTO `t_book` VALUES (41, '笑傲江湖', '金庸', '江湖出版社', 0, 0);
INSERT INTO `t_book` VALUES (42, '雪山飞狐', '金庸', '飞狐出版社', 0, 0);
INSERT INTO `t_book` VALUES (43, '神雕侠侣', '金庸', '神雕出版社', 0, 0);
INSERT INTO `t_book` VALUES (44, '射雕英雄传', '金庸', '射雕出版社', 0, 0);
INSERT INTO `t_book` VALUES (46, '多余的', '多余的', '多余的', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_lend_book_record
-- ----------------------------
DROP TABLE IF EXISTS `t_lend_book_record`;
CREATE TABLE `t_lend_book_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL COMMENT '图书id',
  `user_id` int(11) NOT NULL COMMENT '读者id',
  `lend_date` date DEFAULT NULL COMMENT '借书日期',
  `revert_date` date DEFAULT NULL COMMENT '待归还日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lend_book_record
-- ----------------------------
BEGIN;
INSERT INTO `t_lend_book_record` VALUES (6, 12, 7, '2022-05-12', '2022-06-01');
INSERT INTO `t_lend_book_record` VALUES (7, 13, 6, '2022-05-12', '2022-06-11');
INSERT INTO `t_lend_book_record` VALUES (8, 20, 6, '2022-05-12', '2022-06-11');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '借书号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `native_place` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `role` tinyint(4) DEFAULT NULL COMMENT '角色,0:管理员,1:读者',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `regist_date` date DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (5, 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '汉', 1, '管理员', '2022-05-12');
INSERT INTO `t_user` VALUES (6, 'zjx123', 'E10ADC3949BA59ABBE56E057F20F883E', '汉', 0, '虾米', '2022-05-12');
INSERT INTO `t_user` VALUES (7, 'zjx321', 'E10ADC3949BA59ABBE56E057F20F883E', '汉', 0, '夏米', '2022-05-12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
