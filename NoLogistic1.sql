/*
 Navicat Premium Data Transfer

 Source Server         : jianhe
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : NoLogistic1

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 05/10/2018 17:38:45 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `truck_admin`
-- ----------------------------
DROP TABLE IF EXISTS `truck_admin`;
CREATE TABLE `truck_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(50) NOT NULL COMMENT '管理员名',
  `password` varchar(50) NOT NULL COMMENT '管理员密码，MD5加密',
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `shop_name` varchar(100) DEFAULT NULL,
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-超级管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `user_name_unique` (`admin_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_admin`
-- ----------------------------
BEGIN;
INSERT INTO `truck_admin` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '12345744489@qq.com', '1234568998', 'question', 'answer', null, '1', '2018-03-15 10:04:56', '2018-03-15 10:43:43'), ('2', 'admin234', 'E10ADC3949BA59ABBE56E057F20F883E', '12345674489@qq.com', '1234567998', 'question', 'answer', null, '0', '2018-03-15 10:04:56', '2018-03-15 10:43:43'), ('3', 'admin345', 'E10ADC3949BA59ABBE56E057F20F883E', '12356744489@qq.com', '1234567898', 'question', 'answer', null, '0', '2018-03-15 10:04:56', '2018-03-15 10:43:43');
COMMIT;

-- ----------------------------
--  Table structure for `truck_admin_shop`
-- ----------------------------
DROP TABLE IF EXISTS `truck_admin_shop`;
CREATE TABLE `truck_admin_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `admin_id` int(11) NOT NULL,
  `shop_name` varchar(100) DEFAULT NULL,
  `shop_desc` varchar(50) DEFAULT NULL,
  `shop_email` varchar(50) DEFAULT NULL,
  `shop_phone` varchar(20) DEFAULT NULL,
  `shop_address` varchar(100) DEFAULT NULL,
  `shop_headImg` varchar(255) DEFAULT NULL,
  `shop_firstImg` mediumtext,
  `shop_subImg` mediumtext,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `shop_account` text,
  `shop_tax_card` text,
  `shop_sppkp` text,
  `shop_licence` text,
  `shop_proxy_certificate` text,
  `shop_status` int(11) NOT NULL COMMENT '0审核中，1审核通过，2审核不通过',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_admin_shop`
-- ----------------------------
BEGIN;
INSERT INTO `truck_admin_shop` VALUES ('2', '2', '良品2566', null, null, null, null, null, null, null, '2018-04-13 10:55:19', '2018-04-13 10:55:19', '1', '1', '1', '1', '1', '1'), ('3', '3', '良品255', null, null, null, null, null, null, null, '2018-04-08 12:08:05', '2018-04-13 11:44:54', null, null, null, null, null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `truck_balance_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `truck_balance_alteration`;
CREATE TABLE `truck_balance_alteration` (
  `balance_alteration_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) DEFAULT NULL,
  `alteration_money` decimal(20,2) DEFAULT NULL,
  `alteration_status` int(11) DEFAULT NULL COMMENT '交易状态  收入还是支出',
  `alteration_reason` int(11) DEFAULT NULL COMMENT '变动原因(充值  下单  退单)',
  `order_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`balance_alteration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_balance_alteration`
-- ----------------------------
BEGIN;
INSERT INTO `truck_balance_alteration` VALUES ('1', '3', '5000.00', '0', '0', null, '2018-05-09 11:45:53', '2018-05-09 11:45:53'), ('2', '3', '2.00', '1', '1', '26', '2018-05-09 12:13:27', '2018-05-09 12:13:27'), ('3', '3', '1.00', '1', '1', '27', '2018-05-09 12:13:27', '2018-05-09 12:13:27'), ('4', '3', '1.00', '0', '2', '27', '2018-05-09 12:15:56', '2018-05-09 12:15:56');
COMMIT;

-- ----------------------------
--  Table structure for `truck_bank`
-- ----------------------------
DROP TABLE IF EXISTS `truck_bank`;
CREATE TABLE `truck_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bank_name` varchar(20) NOT NULL,
  `bank_address` varchar(200) DEFAULT NULL,
  `bank_account` varchar(20) NOT NULL,
  `bank_user_name` varchar(20) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `present_address` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_cart`
-- ----------------------------
DROP TABLE IF EXISTS `truck_cart`;
CREATE TABLE `truck_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `cart_user_id` int(11) NOT NULL COMMENT '用户id',
  `cart_product_id` int(11) NOT NULL COMMENT '产品id',
  `amount` int(11) NOT NULL COMMENT '数量',
  `checked` int(11) NOT NULL COMMENT '是否被选中（0否，1是）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_category`
-- ----------------------------
DROP TABLE IF EXISTS `truck_category`;
CREATE TABLE `truck_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `admin_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `Product_Status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_category`
-- ----------------------------
BEGIN;
INSERT INTO `truck_category` VALUES ('1', '2', '0', '一级', '0', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('2', '2', '1', '二级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('3', '2', '2', '三级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('4', '2', '3', '四级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('5', '2', '1', '二级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('6', '2', '2', '三级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('7', '2', '3', '四级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('8', '1', '0', 'hh', '1', null, '2018-04-09 16:32:32', '2018-04-09 16:32:32');
COMMIT;

-- ----------------------------
--  Table structure for `truck_company_address`
-- ----------------------------
DROP TABLE IF EXISTS `truck_company_address`;
CREATE TABLE `truck_company_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `company_id` int(11) NOT NULL,
  `address_name` varchar(100) NOT NULL,
  `address_desc` varchar(100) NOT NULL,
  `address_phone` varchar(50) DEFAULT NULL,
  `address_email` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_company_contact`
-- ----------------------------
DROP TABLE IF EXISTS `truck_company_contact`;
CREATE TABLE `truck_company_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人id',
  `company_id` int(11) NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `contact_position` varchar(50) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_order`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order`;
CREATE TABLE `truck_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shop_id` int(11) DEFAULT NULL,
  `order_price` decimal(20,2) DEFAULT NULL COMMENT '订单金额',
  `payment_price` decimal(20,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(11) DEFAULT NULL COMMENT '支付类型',
  `freight` decimal(20,2) DEFAULT NULL COMMENT '运费',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_order`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order` VALUES ('13', '1522649656308', '24', null, '2.00', null, '0', null, '40', null, '2018-04-02 14:14:16', '2018-04-13 18:12:50'), ('14', '1522649656309', '24', null, '2.00', null, '0', null, '10', null, '2018-04-02 14:14:16', '2018-04-02 14:14:16'), ('15', '1525773689435', '25', '2', '6.00', '6.00', null, null, '10', null, '2018-05-08 18:01:29', '2018-05-08 18:01:29'), ('16', '1525774140327', '25', '2', '6.00', '6.00', null, null, '10', null, '2018-05-08 18:09:00', '2018-05-08 18:09:00'), ('17', '1525774239486', '25', '2', '6.00', '6.00', null, null, '10', null, '2018-05-08 18:10:39', '2018-05-08 18:10:39'), ('18', '1525774338372', '25', '2', '6.00', '6.00', null, null, '10', null, '2018-05-08 18:12:18', '2018-05-08 18:12:18'), ('19', '1525774427412', '25', '2', '6.00', '6.00', '0', null, '40', null, '2018-05-08 18:13:47', '2018-05-08 19:13:48'), ('20', '1525778175443', '25', '2', '5.00', '5.00', '0', null, '10', null, '2018-05-08 19:16:15', '2018-05-08 19:16:15'), ('21', '1525778175640', '25', '3', '2.00', '2.00', '0', null, '10', null, '2018-05-08 19:16:15', '2018-05-08 19:16:15'), ('22', '1525831247483', '25', '3', '2.00', '2.00', '0', null, '10', null, '2018-05-09 10:00:47', '2018-05-09 10:00:47'), ('23', '1525831247756', '25', '2', '4.00', '4.00', '0', null, '40', null, '2018-05-09 10:00:47', '2018-05-09 10:30:02'), ('24', '1525838575568', '25', '2', '4.00', '4.00', '0', null, '10', null, '2018-05-09 12:02:55', '2018-05-09 12:02:55'), ('25', '1525838575790', '25', '3', '2.00', '2.00', '0', null, '40', null, '2018-05-09 12:02:55', '2018-05-09 12:21:17'), ('26', '1525839207064', '25', '2', '2.00', '2.00', '1', null, '40', '2018-05-09 12:13:27', '2018-05-09 12:13:27', '2018-05-09 12:19:21'), ('27', '1525839207642', '25', '3', '1.00', '0.00', '1', null, '0', '2018-05-09 12:13:28', '2018-05-09 12:13:27', '2018-05-09 12:15:56');
COMMIT;

-- ----------------------------
--  Table structure for `truck_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order_detail`;
CREATE TABLE `truck_order_detail` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_no` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) NOT NULL COMMENT '商品总价',
  `product_image` varchar(255) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) NOT NULL COMMENT '生成订单时的商品单价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `shipping_id` int(11) DEFAULT NULL,
  `order_detail_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_order_detail`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order_detail` VALUES ('14', '24', '13', '1046', null, 'hh', '80', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '2', null), ('15', '24', '14', '1046', null, 'hh', '2', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '3', null), ('16', '24', '13', '1047', null, 'hh', '80', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '2', null), ('19', '25', '19', '1046', null, 'hh', '6', '6.00', 'www', '1.00', '2018-05-08 18:13:47', '2018-05-08 19:13:48', '1', '40'), ('20', '25', '20', '1046', null, 'hh', '5', '5.00', 'www', '1.00', '2018-05-08 19:16:15', '2018-05-08 19:16:15', '1', '20'), ('21', '25', '21', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-08 19:16:15', '2018-05-08 19:16:15', '1', '20'), ('22', '25', '22', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:21:49', '1', '30'), ('23', '25', '23', '1046', null, 'hh', '2', '2.00', 'www', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:29:32', '1', '40'), ('24', '25', '23', '1048', null, 'wwwww', '2', '2.00', 'wwwwww', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:30:01', '1', '40'), ('25', '25', '24', '1046', null, 'hh', '2', '2.00', 'www', '1.00', '2018-05-09 12:02:55', '2018-05-09 12:02:55', '1', '20'), ('26', '25', '24', '1048', null, 'wwwww', '2', '2.00', 'wwwwww', '1.00', '2018-05-09 12:02:55', '2018-05-09 12:02:55', '1', '20'), ('27', '25', '25', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-09 12:02:55', '2018-05-09 12:21:17', '1', '40'), ('28', '25', '26', '1046', null, 'hh', '1', '1.00', 'www', '1.00', '2018-05-09 12:13:27', '2018-05-09 12:18:21', '1', '40'), ('29', '25', '26', '1048', null, 'wwwww', '1', '1.00', 'wwwwww', '1.00', '2018-05-09 12:13:27', '2018-05-09 12:19:21', '1', '40'), ('30', '25', '27', '1047', null, 'hh', '1', '1.00', 'hh', '1.00', '2018-05-09 12:13:27', '2018-05-09 12:15:56', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product`;
CREATE TABLE `truck_product` (
  `Product_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `admin_id` int(11) NOT NULL,
  `Product_CategoryID` int(11) DEFAULT NULL COMMENT '分类ID',
  `Product_Title` varchar(100) NOT NULL COMMENT '产品标题',
  `Product_SubTitle` varchar(100) DEFAULT NULL COMMENT '产品次标题',
  `Product_Promotion` varchar(50) DEFAULT NULL COMMENT '产品促销',
  `Product_Desc` mediumtext COMMENT '产品描述',
  `Product_Weight` decimal(20,2) NOT NULL COMMENT '产品重量,吨为单位',
  `Product_Size` varchar(50) NOT NULL COMMENT '产品尺寸',
  `Product_Price` decimal(20,2) NOT NULL,
  `Product_Status` int(2) NOT NULL,
  `Product_Stock` int(20) DEFAULT NULL,
  `Product_FirstImg` varchar(255) DEFAULT NULL,
  `Product_SubImg` mediumtext,
  `CreateTime` datetime DEFAULT NULL COMMENT '开始时间',
  `EndTime` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1049 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product` VALUES ('1046', '2', '4', 'hh', null, null, null, '2.00', '1,2,3', '1.00', '1', '351', 'www', 'www,hh,jj', '2018-05-08 17:39:17', '2018-05-09 12:13:27'), ('1047', '3', '4', 'hh', null, null, null, '2.00', '1,2,3', '1.00', '1', '25', 'hh', 'hh,jj,rr', '2018-05-08 17:39:20', '2018-05-09 12:15:56'), ('1048', '2', '1', 'wwwww', null, null, null, '2.00', '1,1,1', '1.00', '1', '45', 'wwwwww', 'wwwwww,eeee,ssssss', null, '2018-05-09 12:13:27');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product_component`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_component`;
CREATE TABLE `truck_product_component` (
  `component_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`component_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_product_conf`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_conf`;
CREATE TABLE `truck_product_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_id` int(11) NOT NULL,
  `component_id` int(11) DEFAULT NULL,
  `param_name` varchar(200) NOT NULL COMMENT '商品名称',
  `param_value` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_product_stock`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_stock`;
CREATE TABLE `truck_product_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `admin_id` int(11) NOT NULL,
  `warehouse_id` int(11) NOT NULL COMMENT '产品标题',
  `shop_id` int(11) DEFAULT NULL COMMENT '产品次标题',
  `stock_num` int(50) DEFAULT NULL COMMENT '产品促销',
  `stock_limit_num` int(50) DEFAULT NULL COMMENT '产品描述',
  `stock_status` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '开始时间',
  `update_time` datetime DEFAULT NULL COMMENT '结束时间',
  `stock_category_id` int(11) DEFAULT NULL,
  `in_stock_price` decimal(20,2) DEFAULT NULL COMMENT '入库价格',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product_stock`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product_stock` VALUES ('1', '1046', '2', '1', '2', '368', '10', '1', '2018-04-12 12:26:35', '2018-05-09 12:18:21', '46', '100.00'), ('13', '1047', '2', '1', '2', '27', '111', '0', '2018-04-13 15:20:36', '2018-05-09 12:21:17', '46', '11.00'), ('20', '1048', '2', '1', '2', '47', '20', '1', '2018-05-09 09:57:13', '2018-05-09 12:19:21', null, '8.00');
COMMIT;

-- ----------------------------
--  Table structure for `truck_shipping`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shipping`;
CREATE TABLE `truck_shipping` (
  `shipping_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(11) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shipping`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shipping` VALUES ('1', '25', 'hh', null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `truck_shop_contact`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shop_contact`;
CREATE TABLE `truck_shop_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人id',
  `shop_id` int(11) NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `contact_position` varchar(50) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shop_contact`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shop_contact` VALUES ('1', '2', 'lili', '老总', '12456@163.com', '1358752', '2018-04-12 12:28:15', '2018-04-12 12:28:17');
COMMIT;

-- ----------------------------
--  Table structure for `truck_shop_warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shop_warehouse`;
CREATE TABLE `truck_shop_warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `shop_id` int(11) NOT NULL,
  `warehouse_name` varchar(100) NOT NULL,
  `warehouse_desc` varchar(100) NOT NULL,
  `warehouse_phone` varchar(50) DEFAULT NULL,
  `warehouse_email` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shop_warehouse`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shop_warehouse` VALUES ('1', '2', 'hh', 'tt', null, null, '2018-04-12 12:26:02', '2018-04-12 12:26:05'), ('2', '2', '仓库二', '河南郑州几号', null, null, '2018-04-13 16:24:43', '2018-04-13 16:24:43');
COMMIT;

-- ----------------------------
--  Table structure for `truck_stock_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `truck_stock_alteration`;
CREATE TABLE `truck_stock_alteration` (
  `alteration_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `order_detail_id` int(11) DEFAULT NULL,
  `alteration_num` int(11) DEFAULT NULL,
  `alteration_status` int(11) DEFAULT NULL COMMENT '交易状态  收入还是支出',
  `alteration_reason` int(11) DEFAULT NULL COMMENT '变动原因(充值  下单  退单)',
  `alteration_product_price` decimal(20,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`alteration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_stock_category`
-- ----------------------------
DROP TABLE IF EXISTS `truck_stock_category`;
CREATE TABLE `truck_stock_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `admin_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_stock_category`
-- ----------------------------
BEGIN;
INSERT INTO `truck_stock_category` VALUES ('41', '2', '0', '上海仓', '1', null, '2018-04-13 15:28:33', '2018-04-13 15:28:33'), ('42', '2', '41', '浦东1号仓库', '1', null, '2018-04-13 15:29:01', '2018-04-13 15:29:01'), ('43', '2', '42', 'a区', '1', null, '2018-04-13 15:29:26', '2018-04-13 15:29:26'), ('44', '2', '43', '11号货架', '1', null, '2018-04-13 15:29:41', '2018-04-13 15:29:41'), ('45', '2', '44', '3层', '1', null, '2018-04-13 15:29:57', '2018-04-13 15:29:57'), ('46', '2', '45', '编号111', '1', null, '2018-04-13 15:30:27', '2018-04-13 15:30:27');
COMMIT;

-- ----------------------------
--  Table structure for `truck_user`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user`;
CREATE TABLE `truck_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-普通用户,1-管理员，2-VIP用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_unique` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user` VALUES ('24', 'admin', '25F9E794323B453885F5181F1B624D0B', '22222@qq.com', '12345678998', '问题', '问题的答案', '1', '2018-03-14 18:03:17', '2018-03-14 18:22:05'), ('25', 'admin234', '25F9E794323B453885F5181F1B624D0B', '2222@qq.com', '12345678998', '问题', '问题的答案', '0', '2018-03-14 18:03:17', '2018-03-14 18:22:05');
COMMIT;

-- ----------------------------
--  Table structure for `truck_user_company`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user_company`;
CREATE TABLE `truck_user_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `user_id` int(11) NOT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `company_desc` varchar(50) DEFAULT NULL,
  `company_email` varchar(50) DEFAULT NULL,
  `company_phone` varchar(20) DEFAULT NULL,
  `company_address` varchar(100) DEFAULT NULL,
  `company_headImg` varchar(255) DEFAULT NULL,
  `company_firstImg` mediumtext,
  `company_subImg` mediumtext,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `company_account` text,
  `company_tax_card` text,
  `company_sppkp` text,
  `company_licence` text,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user_company`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user_company` VALUES ('2', '2', '良品2553', null, null, null, null, null, null, null, '2018-04-08 13:14:09', '2018-04-08 13:14:09', '1', '', '1', '1'), ('25', '25', '良品255', null, null, null, null, null, null, null, '2018-04-08 12:08:05', '2018-04-08 12:08:05', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `truck_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user_info`;
CREATE TABLE `truck_user_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `company_name` varchar(100) NOT NULL COMMENT '客户公司名称',
  `duty_id` varchar(20) DEFAULT NULL COMMENT '税号',
  `cumulative_purchase` decimal(20,2) DEFAULT NULL COMMENT '累计采购额',
  `register_address` varchar(200) DEFAULT NULL COMMENT '注册地址',
  `office_address` varchar(200) DEFAULT NULL COMMENT '办公地址',
  `company_phone` varchar(20) DEFAULT NULL COMMENT '公司联系电话',
  `company_email` varchar(50) DEFAULT NULL COMMENT '公司联系邮箱',
  `company_fax` varchar(50) DEFAULT NULL COMMENT '公司传真',
  `company_contactor` varchar(20) DEFAULT NULL COMMENT '公司联系人',
  `member_level` varchar(20) DEFAULT NULL COMMENT '会员等级',
  `account_balance` decimal(20,2) DEFAULT NULL COMMENT '账户余额',
  `service_balance` decimal(20,2) DEFAULT NULL COMMENT '服务费账户余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `company_name_unique` (`company_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user_info`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user_info` VALUES ('3', '25', '上海剑展', '2500314000', '51.00', '上海', '上海浦东新区', '0221456320', 'jianhe@happy.com', '022103', '孙先生', '100', '4998.00', '0.00', '2018-03-26 17:52:00', '2018-05-09 12:15:56');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
