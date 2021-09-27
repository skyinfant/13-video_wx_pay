/*
Navicat MySQL Data Transfer

Source Server         : 阿里云-小鱼
Source Server Version : 50717
Source Host           : 182.92.161.227:3306
Source Database       : 13-WX_Pay

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-09-27 17:51:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
  `title` varchar(128) DEFAULT NULL COMMENT '章节名称',
  `ordered` int(11) DEFAULT NULL COMMENT '章节顺序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `user_id` int(11) DEFAULT NULL,
  `head_img` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `point` double(5,2) DEFAULT NULL COMMENT '评分，10分满分',
  `up` int(11) DEFAULT NULL COMMENT '点赞数',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `video_id` int(11) DEFAULT NULL COMMENT '视频id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for episode
-- ----------------------------
DROP TABLE IF EXISTS `episode`;
CREATE TABLE `episode` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(524) DEFAULT NULL COMMENT '集标题',
  `num` int(10) DEFAULT NULL COMMENT '第几集',
  `duration` varchar(64) DEFAULT NULL COMMENT '时长 分钟，单位',
  `cover_img` varchar(524) DEFAULT NULL COMMENT '封面图',
  `video_id` int(10) DEFAULT NULL COMMENT '视频id',
  `summary` varchar(256) DEFAULT NULL COMMENT '集概述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `chapter_id` int(11) DEFAULT NULL COMMENT '章节主键id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of episode
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `head_img` varchar(524) DEFAULT NULL COMMENT '头像',
  `phone` varchar(64) DEFAULT '' COMMENT '手机号',
  `sign` varchar(524) DEFAULT '全栈工程师' COMMENT '用户签名',
  `sex` tinyint(2) DEFAULT '-1' COMMENT '0表示女，1表示男',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '55', '55', '55', '55', '全栈工程师', '-1', '99', '2021-06-30 18:27:57');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(524) DEFAULT NULL COMMENT '视频标题',
  `summary` varchar(1026) DEFAULT NULL COMMENT '概述',
  `cover_img` varchar(524) DEFAULT NULL COMMENT '封面图',
  `view_num` int(10) DEFAULT '0' COMMENT '观看数',
  `price` int(11) DEFAULT NULL COMMENT '价格,分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `online` int(5) DEFAULT '0' COMMENT '0表示未上线，1表示上线',
  `point` double(11,2) DEFAULT '8.70' COMMENT '默认8.7，最高10分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', 'kafka教程', 'good good study', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '12', '10', '2021-06-01 18:47:54', '0', '8.70');
INSERT INTO `video` VALUES ('2', 'redis6.x', '这是概要', 'https://file.xdclass.net/video/2021/64-redis6/cover.jpeg', '43', '20', '2021-06-01 18:47:54', '0', '9.70');
INSERT INTO `video` VALUES ('3', 'postman', '这是概要', 'https://file.xdclass.net/video/2021/59-Postman/Postman.png', '53', '123', '2021-06-01 18:47:54', '0', '8.70');
INSERT INTO `video` VALUES ('4', '设计模式', '这是概要', 'https://file.xdclass.net/video/2020-12%20%E5%AE%98%E7%BD%91%E8%B6%85100k%E4%B8%BB%E5%9B%BE%E6%9B%B4%E6%96%B0/%E4%B8%BB%E5%9B%BE/58-sjms.jpg', '23', '199', '2021-06-01 18:47:54', '0', '6.20');
INSERT INTO `video` VALUES ('5', 'alibacloud', '这是概要', 'https://file.xdclass.net/video/2020-12%20%E5%AE%98%E7%BD%91%E8%B6%85100k%E4%B8%BB%E5%9B%BE%E6%9B%B4%E6%96%B0/%E4%B8%BB%E5%9B%BE/57-alibabacloud.jpg', '64', '10', '2021-06-01 18:47:54', '0', '9.10');
INSERT INTO `video` VALUES ('6', 'linux，docker', '这是概要', 'https://file.xdclass.net/video/2020-12%20%E5%AE%98%E7%BD%91%E8%B6%85100k%E4%B8%BB%E5%9B%BE%E6%9B%B4%E6%96%B0/%E4%B8%BB%E5%9B%BE/45-docker.jpg', '12', '10', '2021-06-01 18:47:54', '0', '6.70');
INSERT INTO `video` VALUES ('7', 'springboot', '这是概要', 'https://file.xdclass.net/video/2020/SSM/zt-ssm.png', '52', '23', '2021-06-01 18:47:54', '0', '5.10');
INSERT INTO `video` VALUES ('8', 'javascript', '这是概要', 'https://file.xdclass.net/video/2020/2020js/zt-js.jpg', '54', '442', '2021-06-01 18:47:54', '0', '8.70');

-- ----------------------------
-- Table structure for video_order
-- ----------------------------
DROP TABLE IF EXISTS `video_order`;
CREATE TABLE `video_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) DEFAULT NULL COMMENT '用户标示',
  `out_trade_no` varchar(64) DEFAULT NULL COMMENT '订单唯一标识',
  `state` int(11) DEFAULT NULL COMMENT '0表示未支付，1表示已支付',
  `create_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `notify_time` datetime DEFAULT NULL COMMENT '支付回调时间',
  `total_fee` int(11) DEFAULT NULL COMMENT '支付金额，单位分',
  `nickname` varchar(32) DEFAULT NULL COMMENT '微信昵称',
  `head_img` varchar(128) DEFAULT NULL COMMENT '微信头像',
  `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
  `video_title` varchar(128) DEFAULT NULL COMMENT '视频名称',
  `video_img` varchar(256) DEFAULT NULL COMMENT '视频图片',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `ip` varchar(64) DEFAULT NULL COMMENT '用户ip地址',
  `del` int(5) DEFAULT '0' COMMENT '0表示未删除，1表示已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_order
-- ----------------------------
INSERT INTO `video_order` VALUES ('145', 'oiNKG01g0MuqLZ34Yv7mnQtcU3QI', 'b0ac92959a954fdababf4232ba62f998', '1', '2021-09-17 11:20:06', '2021-09-17 11:20:28', '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('146', 'oiNKG01g0MuqLZ34Yv7mnQtcU3QI', 'b5b48e5ed3c740bbb5f2e44fbc5fc692', '1', '2021-09-17 12:47:12', '2021-09-17 12:47:39', '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('147', null, 'ab9fc5337dd44d80bacad6870fe1c62f', '0', '2021-09-17 12:52:59', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('148', null, 'bb3e424731be48f8825b67eaf63e2240', '0', '2021-09-17 12:53:03', null, '20', '55', '55', '2', 'redis6.x', 'https://file.xdclass.net/video/2021/64-redis6/cover.jpeg', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('149', null, 'fd1b00eb693c4afd9de5c4a0a935d989', '0', '2021-09-17 12:53:06', null, '123', '55', '55', '3', 'postman', 'https://file.xdclass.net/video/2021/59-Postman/Postman.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('150', 'oiNKG01g0MuqLZ34Yv7mnQtcU3QI', '24cf87906e5f406d875f4b17bc32ebde', '1', '2021-09-17 12:53:16', '2021-09-17 12:53:33', '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('151', null, '03724c086ea5414bb0ff7a5352182fc0', '0', '2021-09-17 12:54:12', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('152', null, '56152c5ac54c447dbe0f8869a7f8682c', '0', '2021-09-17 12:56:17', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('153', null, '56562948f7104f85903401410035be1e', '0', '2021-09-17 12:56:25', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('154', null, 'fd88ec03bd78495fbac13df1bc53014f', '0', '2021-09-17 13:01:47', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('155', null, 'a969212ba8704d6f9f721043c9f8aa28', '0', '2021-09-17 14:17:30', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('156', null, '0791fc7e62f3423b946e6774ef5dbdeb', '0', '2021-09-17 17:09:55', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('157', null, '048fc86bcb444f6d91ef592159fe305b', '0', '2021-09-17 17:10:41', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('158', null, 'f4708f2d1a5c4b2e80f0293040e9bb9f', '0', '2021-09-17 18:57:30', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('159', null, '5adb78991add4609899840dfa69c3a9d', '0', '2021-09-17 19:57:08', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('160', null, 'a55f8364d5d44e8f9c0d5833fdf3700a', '0', '2021-09-18 09:54:43', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.50', '0');
INSERT INTO `video_order` VALUES ('161', null, '0fa341c5db8b49c9b66829ed2b1cbe7b', '0', '2021-09-19 08:39:29', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '192.168.0.1', '0');
INSERT INTO `video_order` VALUES ('162', null, '637f92fb73514ba0984387ddd6a11102', '0', '2021-09-19 08:40:07', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.98.142', '0');
INSERT INTO `video_order` VALUES ('163', null, '004d5b9aa109425ea45361a329d92286', '0', '2021-09-20 17:33:10', null, '10', '55', '55', '1', 'kafka教程', 'https://file.xdclass.net/video/2021/65-kafka/xzt.png', '1', '112.94.97.97', '0');
INSERT INTO `video_order` VALUES ('164', null, '089c359cc69d4229a09784d38418dddf', '0', '2021-09-20 18:55:02', null, '20', '55', '55', '2', 'redis6.x', 'https://file.xdclass.net/video/2021/64-redis6/cover.jpeg', '1', '112.94.97.97', '0');
INSERT INTO `video_order` VALUES ('165', null, 'db0b2de035a64ee3956d967217431796', '0', '2021-09-20 18:55:05', null, '123', '55', '55', '3', 'postman', 'https://file.xdclass.net/video/2021/59-Postman/Postman.png', '1', '112.94.97.97', '0');
