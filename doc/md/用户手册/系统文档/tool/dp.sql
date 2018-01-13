/*
Navicat MySQL Data Transfer

Source Server         : emm
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-13 07:43:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  `bankAccount` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `remaining` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'Account00000001', '1', '1234567890', '公司账户A', '23333');
INSERT INTO `account` VALUES ('2', 'Account00000002', '1', '0987654321', '公司账户B', '8620');

-- ----------------------------
-- Table structure for `accountrecord`
-- ----------------------------
DROP TABLE IF EXISTS `accountrecord`;
CREATE TABLE `accountrecord` (
  `InitialID` varchar(20) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `bankAccount` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `remaining` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accountrecord
-- ----------------------------
INSERT INTO `accountrecord` VALUES ('Initial00000002', 'Account00000001', '1234567890', '公司账户A', '23333');
INSERT INTO `accountrecord` VALUES ('Initial00000002', 'Account00000002', '0987654321', '公司账户B', '8620');
INSERT INTO `accountrecord` VALUES ('Initial00000003', 'Account00000001', '1234567890', '公司账户A', '23333');
INSERT INTO `accountrecord` VALUES ('Initial00000003', 'Account00000002', '0987654321', '公司账户B', '8620');

-- ----------------------------
-- Table structure for `cashbill`
-- ----------------------------
DROP TABLE IF EXISTS `cashbill`;
CREATE TABLE `cashbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `total` double NOT NULL,
  `accountID` varchar(20) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cashbill
-- ----------------------------
INSERT INTO `cashbill` VALUES ('1', 'XJFYD-20171224-00001', '审批通过', '2017-12-24 15:33:53', 'User00000005', '有钱，真好', '5000', 'Account00000002');
INSERT INTO `cashbill` VALUES ('2', 'XJFYD-20180105-00001', '草稿', '2018-01-05 15:22:44', 'User00000005', '', '1200', 'Account00000001');

-- ----------------------------
-- Table structure for `cashitem`
-- ----------------------------
DROP TABLE IF EXISTS `cashitem`;
CREATE TABLE `cashitem` (
  `site_ID` varchar(30) NOT NULL,
  `itemName` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  `comment` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cashitem
-- ----------------------------
INSERT INTO `cashitem` VALUES ('XJFYD-20171224-00001', '出差费用报销', '2000', '无');
INSERT INTO `cashitem` VALUES ('XJFYD-20171224-00001', '公关费用报销', '3000', '无');
INSERT INTO `cashitem` VALUES ('XJFYD-20180105-00001', 'A', '1200', '无');

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  `category` varchar(10) NOT NULL,
  `level` int(1) NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `post` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `receivable` double NOT NULL,
  `payable` double NOT NULL,
  `receivableLimit` double NOT NULL,
  `salesmanID` varchar(20) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', 'Client00000001', '0', '销售商', '2', 'fds', 'fds', 'fds', 'fsd', 'fds', '0', '0', '34', 'User00000002');
INSERT INTO `client` VALUES ('2', 'Client00000002', '1', '供应商', '4', '宋抟', '13218046986', '南京大学仙林校区', '210046', '161250233@smail.nju.edu.cn', '4500', '9800', '10000', 'User00000002');
INSERT INTO `client` VALUES ('3', 'Client00000003', '1', '销售商', '4', '刘钦', '13219075278', '南京大学鼓楼校区', '123456', 'cx@gmail.com', '8700', '4200', '5000', 'User00000002');

-- ----------------------------
-- Table structure for `clientrecord`
-- ----------------------------
DROP TABLE IF EXISTS `clientrecord`;
CREATE TABLE `clientrecord` (
  `InitialID` varchar(20) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `category` varchar(10) NOT NULL,
  `level` int(1) NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `post` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `receivable` double NOT NULL,
  `payable` double NOT NULL,
  `receivableLimit` double NOT NULL,
  `salesmanID` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientrecord
-- ----------------------------
INSERT INTO `clientrecord` VALUES ('Initial00000002', 'Client00000002', '供应商', '4', '宋抟', '13218046986', '南京大学仙林校区', '210046', '161250233@smail.nju.edu.cn', '4500', '9800', '10000', 'User00000002');
INSERT INTO `clientrecord` VALUES ('Initial00000002', 'Client00000003', '销售商', '4', '刘钦', '13219075278', '南京大学鼓楼校区', '123456', 'cx@gmail.com', '3900', '4200', '5000', 'User00000002');
INSERT INTO `clientrecord` VALUES ('Initial00000003', 'Client00000002', '供应商', '4', '宋抟', '13218046986', '南京大学仙林校区', '210046', '161250233@smail.nju.edu.cn', '4500', '9800', '10000', 'User00000002');
INSERT INTO `clientrecord` VALUES ('Initial00000003', 'Client00000003', '销售商', '4', '刘钦', '13219075278', '南京大学鼓楼校区', '123456', 'cx@gmail.com', '3900', '4200', '5000', 'User00000002');

-- ----------------------------
-- Table structure for `datahelper`
-- ----------------------------
DROP TABLE IF EXISTS `datahelper`;
CREATE TABLE `datahelper` (
  `today` date NOT NULL,
  `CashBill` int(11) NOT NULL,
  `PaymentBill` int(11) NOT NULL,
  `ReceiptBill` int(11) NOT NULL,
  `InventoryGiftBill` int(11) NOT NULL,
  `InventoryLossOverBill` int(11) NOT NULL,
  `PurchaseRefundBill` int(11) NOT NULL,
  `PurchaseTradeBill` int(11) NOT NULL,
  `SaleRefundBill` int(11) NOT NULL,
  `SaleTradeBill` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datahelper
-- ----------------------------
INSERT INTO `datahelper` VALUES ('2018-01-07', '2', '11', '5', '4', '2', '1', '2', '1', '4');

-- ----------------------------
-- Table structure for `giftitem`
-- ----------------------------
DROP TABLE IF EXISTS `giftitem`;
CREATE TABLE `giftitem` (
  `site_ID` varchar(20) NOT NULL,
  `goodsID` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of giftitem
-- ----------------------------
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00003', 'Goods00000004', '3', '20');
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00003', 'Goods00000002', '3', '50');
INSERT INTO `giftitem` VALUES ('Promotion00000001', 'Goods00000002', '2', '50');
INSERT INTO `giftitem` VALUES ('Promotion00000002', 'Goods00000004', '1', '20');
INSERT INTO `giftitem` VALUES ('Promotion00000004', 'Goods00000003', '2', '30');
INSERT INTO `giftitem` VALUES ('Promotion00000004', 'Goods00000004', '1', '20');
INSERT INTO `giftitem` VALUES ('KCZSD-20171225-00002', 'Goods00000003', '1', '30');
INSERT INTO `giftitem` VALUES ('KCZSD-20171225-00001', 'Goods00000002', '1', '0');
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00002', 'Goods00000002', '1', '0');
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00002', 'Goods00000003', '1', '0');
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00001', 'Goods00000001', '4', '40');
INSERT INTO `giftitem` VALUES ('KCZSD-20171224-00001', 'Goods00000003', '1', '30');
INSERT INTO `giftitem` VALUES ('Promotion00000008', 'Goods00000004', '5', '20');
INSERT INTO `giftitem` VALUES ('Promotion00000009', 'Goods00000003', '5', '30');
INSERT INTO `giftitem` VALUES ('Promotion00000009', 'Goods00000004', '5', '20');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(25) DEFAULT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `name` varchar(20) NOT NULL,
  `goodsSortID` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  `cost` double NOT NULL,
  `retail` double NOT NULL,
  `latestCost` double NOT NULL,
  `latestRetail` double NOT NULL,
  `alarmNum` int(11) NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'Goods00000001', '1', '蓝光二极管', 'GoodsSort00000005', '大号', '1600', '40', '50', '0', '0', '10', '无');
INSERT INTO `goods` VALUES ('2', 'Goods00000002', '1', 'LED节能灯', 'GoodsSort00000006', '中号', '3000', '50', '60', '0', '60', '10', '无');
INSERT INTO `goods` VALUES ('3', 'Goods00000003', '1', '钨丝灯泡', 'GoodsSort00000004', '中号', '3200', '30', '50', '0', '50', '10', '无');
INSERT INTO `goods` VALUES ('4', 'Goods00000004', '1', '台灯', 'GoodsSort00000007', '大号', '4300', '20', '30', '0', '0', '10', '无');

-- ----------------------------
-- Table structure for `goodsitem`
-- ----------------------------
DROP TABLE IF EXISTS `goodsitem`;
CREATE TABLE `goodsitem` (
  `site_ID` varchar(20) NOT NULL,
  `goodsID` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsitem
-- ----------------------------
INSERT INTO `goodsitem` VALUES ('Promotion00000005', 'Goods00000003', '2', '50');
INSERT INTO `goodsitem` VALUES ('Promotion00000005', 'Goods00000004', '2', '30');
INSERT INTO `goodsitem` VALUES ('JHD-20171225-00001', 'Goods00000002', '2', '50');
INSERT INTO `goodsitem` VALUES ('JHD-20171225-00001', 'Goods00000003', '2', '30');
INSERT INTO `goodsitem` VALUES ('JHTHD-20171225-00001', 'Goods00000002', '2', '50');
INSERT INTO `goodsitem` VALUES ('JHTHD-20171225-00001', 'Goods00000004', '3', '20');
INSERT INTO `goodsitem` VALUES ('JHTHD-20171225-00001', 'Goods00000001', '2', '40');
INSERT INTO `goodsitem` VALUES ('XSTHD-20171225-00001', 'Goods00000003', '1', '50');
INSERT INTO `goodsitem` VALUES ('XSTHD-20171225-00001', 'Goods00000001', '2', '50');
INSERT INTO `goodsitem` VALUES ('Promotion00000007', 'Goods00000003', '1', '50');
INSERT INTO `goodsitem` VALUES ('Promotion00000007', 'Goods00000004', '1', '30');
INSERT INTO `goodsitem` VALUES ('XSD-20171226-00001', 'Goods00000003', '2', '50');
INSERT INTO `goodsitem` VALUES ('XSD-20171226-00001', 'Goods00000004', '2', '30');
INSERT INTO `goodsitem` VALUES ('XSD-20171227-00001', 'Goods00000003', '4', '50');
INSERT INTO `goodsitem` VALUES ('XSD-20171227-00001', 'Goods00000002', '4', '60');
INSERT INTO `goodsitem` VALUES ('JHD-20180104-00001', 'Goods00000002', '20', '50');
INSERT INTO `goodsitem` VALUES ('JHD-20180104-00001', 'Goods00000004', '10', '20');
INSERT INTO `goodsitem` VALUES ('XSD-20180104-00001', 'Goods00000003', '100', '50');
INSERT INTO `goodsitem` VALUES ('XSD-20180106-00001', 'Goods00000002', '20', '60');
INSERT INTO `goodsitem` VALUES ('XSD-20180106-00001', 'Goods00000001', '30', '50');

-- ----------------------------
-- Table structure for `goodsrecord`
-- ----------------------------
DROP TABLE IF EXISTS `goodsrecord`;
CREATE TABLE `goodsrecord` (
  `InitialID` varchar(20) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `name` varchar(25) NOT NULL,
  `goodsSortID` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  `cost` double NOT NULL,
  `retail` double NOT NULL,
  `latestCost` double NOT NULL DEFAULT '0',
  `latestRetail` double NOT NULL DEFAULT '0',
  `alarmNum` int(11) NOT NULL,
  `comment` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsrecord
-- ----------------------------
INSERT INTO `goodsrecord` VALUES ('Initial00000002', 'Goods00000001', '蓝光二极管', 'GoodsSort00000005', '大号', '16', '40', '50', '0', '0', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000002', 'Goods00000002', 'LED节能灯', 'GoodsSort00000006', '中号', '46', '50', '60', '0', '60', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000002', 'Goods00000003', '钨丝灯泡', 'GoodsSort00000004', '中号', '33', '30', '50', '0', '50', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000002', 'Goods00000004', '台灯', 'GoodsSort00000007', '大号', '43', '20', '30', '0', '0', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000003', 'Goods00000001', '蓝光二极管', 'GoodsSort00000005', '大号', '16', '40', '50', '0', '0', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000003', 'Goods00000002', 'LED节能灯', 'GoodsSort00000006', '中号', '46', '50', '60', '0', '60', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000003', 'Goods00000003', '钨丝灯泡', 'GoodsSort00000004', '中号', '33', '30', '50', '0', '50', '10', '无');
INSERT INTO `goodsrecord` VALUES ('Initial00000003', 'Goods00000004', '台灯', 'GoodsSort00000007', '大号', '43', '20', '30', '0', '0', '10', '无');

-- ----------------------------
-- Table structure for `goodssort`
-- ----------------------------
DROP TABLE IF EXISTS `goodssort`;
CREATE TABLE `goodssort` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  `name` varchar(20) NOT NULL,
  `fatherID` varchar(20) DEFAULT NULL,
  `comment` varchar(50) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodssort
-- ----------------------------
INSERT INTO `goodssort` VALUES ('1', 'GoodsSort00000001', '1', '所有商品', null, '');
INSERT INTO `goodssort` VALUES ('2', 'GoodsSort00000002', '1', '白炽灯', 'GoodsSort00000001', '无');
INSERT INTO `goodssort` VALUES ('3', 'GoodsSort00000003', '1', 'LED', 'GoodsSort00000001', '无');
INSERT INTO `goodssort` VALUES ('4', 'GoodsSort00000004', '1', '强光灯', 'GoodsSort00000002', '无');
INSERT INTO `goodssort` VALUES ('5', 'GoodsSort00000005', '1', '蓝色LED', 'GoodsSort00000003', '无');
INSERT INTO `goodssort` VALUES ('6', 'GoodsSort00000006', '1', '节能灯管', 'GoodsSort00000003', '无');
INSERT INTO `goodssort` VALUES ('7', 'GoodsSort00000007', '1', '弱光灯', 'GoodsSort00000002', '无');

-- ----------------------------
-- Table structure for `initial`
-- ----------------------------
DROP TABLE IF EXISTS `initial`;
CREATE TABLE `initial` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of initial
-- ----------------------------
INSERT INTO `initial` VALUES ('1', 'Initial00000001', '2014');
INSERT INTO `initial` VALUES ('2', 'Initial00000002', '2016');
INSERT INTO `initial` VALUES ('3', 'Initial00000003', '2017');

-- ----------------------------
-- Table structure for `inventorygiftbill`
-- ----------------------------
DROP TABLE IF EXISTS `inventorygiftbill`;
CREATE TABLE `inventorygiftbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `clientID` varchar(20) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventorygiftbill
-- ----------------------------
INSERT INTO `inventorygiftbill` VALUES ('1', 'KCZSD-20171224-00001', '审批通过', '2017-12-24 23:56:51', 'User00000007', '无', 'Client00000002', '0');
INSERT INTO `inventorygiftbill` VALUES ('2', 'KCZSD-20171224-00002', '草稿', '2017-12-25 00:03:03', 'User00000007', '', 'Client00000003', '0');
INSERT INTO `inventorygiftbill` VALUES ('3', 'KCZSD-20171225-00001', '草稿', '2017-12-25 00:04:53', 'User00000007', '', 'Client00000003', '0');
INSERT INTO `inventorygiftbill` VALUES ('4', 'KCZSD-20171225-00002', '草稿', '2017-12-25 00:05:00', 'User00000007', '', 'Client00000003', '0');

-- ----------------------------
-- Table structure for `inventorylossoverbill`
-- ----------------------------
DROP TABLE IF EXISTS `inventorylossoverbill`;
CREATE TABLE `inventorylossoverbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventorylossoverbill
-- ----------------------------
INSERT INTO `inventorylossoverbill` VALUES ('1', 'KCYSD-20171224-00001', '审批不通过', '2017-12-24 21:42:06', 'User00000007', 'no comment');
INSERT INTO `inventorylossoverbill` VALUES ('2', 'KCYSD-20171225-00001', '草稿', '2017-12-25 10:06:09', 'User00000007', '');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `operatorID` varchar(20) NOT NULL,
  `action` varchar(50) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：XSD-20171226-00001', '2017-12-27 15:10:42');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：KCZSD-20171224-00001', '2017-12-27 23:31:44');
INSERT INTO `log` VALUES ('User00000002', '提交销售单，ID：XSD-20171227-00001', '2017-12-27 23:34:05');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：XSD-20171227-00001', '2017-12-27 23:35:28');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：FKD-20171225-00001', '2017-12-28 14:07:32');
INSERT INTO `log` VALUES ('User00000005', '提交收款单，ID：SKD-20171228-00001', '2017-12-28 14:09:58');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：SKD-20171228-00001', '2017-12-28 14:10:35');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00001', '2017-12-28 14:54:09');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：SKD-20171228-00001', '2017-12-28 15:04:21');
INSERT INTO `log` VALUES ('User00000005', '提交收款单，ID：SKD-20171228-00002', '2017-12-28 15:05:14');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：SKD-20171228-00002', '2017-12-28 15:05:41');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00002', '2017-12-28 15:16:35');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：FKD-20171228-00002', '2017-12-28 15:22:33');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00003', '2017-12-28 15:22:52');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00004', '2017-12-28 15:27:52');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00005', '2017-12-28 15:28:46');
INSERT INTO `log` VALUES ('User00000005', '提交收款单，ID：SKD-20171228-00003', '2017-12-28 15:30:40');
INSERT INTO `log` VALUES ('User00000005', '提交付款单，ID：FKD-20171228-00006', '2017-12-28 15:35:55');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：XJFYD-20171224-00001', '2017-12-29 14:45:08');
INSERT INTO `log` VALUES ('User00000002', '提交进货单，ID：JHD-20180104-00001', '2018-01-04 13:49:20');
INSERT INTO `log` VALUES ('User00000002', '提交销售单，ID：XSD-20180104-00001', '2018-01-04 14:28:01');
INSERT INTO `log` VALUES ('User00000006', '单据审批通过：XSD-20180104-00001', '2018-01-04 14:29:18');

-- ----------------------------
-- Table structure for `lossoveritem`
-- ----------------------------
DROP TABLE IF EXISTS `lossoveritem`;
CREATE TABLE `lossoveritem` (
  `site_ID` varchar(30) NOT NULL,
  `goodsID` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `goodsNumber` int(11) NOT NULL,
  `actualNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lossoveritem
-- ----------------------------
INSERT INTO `lossoveritem` VALUES ('KCYSD-20171224-00001', 'Goods00000003', '50', '40', '43');
INSERT INTO `lossoveritem` VALUES ('KCYSD-20171224-00001', 'Goods00000004', '30', '45', '43');
INSERT INTO `lossoveritem` VALUES ('KCYSD-20171225-00001', 'Goods00000002', '60', '50', '52');
INSERT INTO `lossoveritem` VALUES ('KCYSD-20171225-00001', 'Goods00000003', '50', '40', '37');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `receiverID` varchar(20) NOT NULL,
  `senderID` varchar(20) NOT NULL,
  `message` varchar(200) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('2', 'User00000007', 'User00000002', '给销售商发货：\r\n---钨丝灯泡：100件\r\n销售商信息：\r\n---刘钦（Client00000003）\r\n', '2018-01-04 14:29:18');
INSERT INTO `message` VALUES ('3', 'User00000007', 'User00000002', '制定库存赠送单：\r\n---台灯：5件\r\n赠送对象：\r\n---刘钦（Client00000003）\r\n', '2018-01-04 14:29:18');
INSERT INTO `message` VALUES ('4', 'User00000005', 'User00000002', '制定收款单：\r\n---总额：4800.0元\r\n收款对象：\r\n---刘钦（Client00000003）\r\n', '2018-01-04 14:29:18');

-- ----------------------------
-- Table structure for `paymentbill`
-- ----------------------------
DROP TABLE IF EXISTS `paymentbill`;
CREATE TABLE `paymentbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `total` double NOT NULL,
  `clientID` varchar(20) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paymentbill
-- ----------------------------
INSERT INTO `paymentbill` VALUES ('1', 'FKD-20171224-00001', '审批不通过', '2017-12-24 23:03:21', 'User00000005', '', '0', '');
INSERT INTO `paymentbill` VALUES ('2', 'FKD-20171224-00002', '待审批', '2017-12-24 23:16:40', 'User00000005', '45y', '20', 'Client00000003');
INSERT INTO `paymentbill` VALUES ('3', 'FKD-20171224-00003', '草稿', '2017-12-24 23:39:32', 'User00000005', '', '0', '');
INSERT INTO `paymentbill` VALUES ('4', 'FKD-20171225-00001', '审批通过', '2017-12-25 23:37:53', 'User00000005', '无', '200', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('5', 'FKD-20171226-00001', '草稿', '2017-12-26 08:46:41', 'User00000005', '', '0', '');
INSERT INTO `paymentbill` VALUES ('6', 'FKD-20171228-00001', '待审批', '2017-12-28 14:54:09', 'User00000005', '用于红冲编号为FKD-20171225-00001的单据', '-200', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('7', 'FKD-20171228-00002', '审批通过', '2017-12-28 15:16:35', 'User00000005', '没有', '300', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('8', 'FKD-20171228-00003', '待审批', '2017-12-28 15:22:52', 'User00000005', '用于红冲编号为FKD-20171228-00002的单据', '-300', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('9', 'FKD-20171228-00004', '待审批', '2017-12-28 15:27:52', 'User00000005', '用于红冲编号为FKD-20171225-00001的单据', '-200', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('10', 'FKD-20171228-00005', '待审批', '2017-12-28 15:28:46', 'User00000005', '用于红冲编号为FKD-20171228-00002的单据', '-300', 'Client00000002');
INSERT INTO `paymentbill` VALUES ('11', 'FKD-20171228-00006', '待审批', '2017-12-28 15:35:55', 'User00000005', '用于红冲编号为FKD-20171225-00001的单据', '-200', 'Client00000002');

-- ----------------------------
-- Table structure for `promotion`
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `name` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `clientLevel` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `voucher` double DEFAULT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES ('1', 'Promotion00000001', '1', '总价促销A', '总价促销策略', '2017-12-08 00:00:00', '2017-12-25 00:00:00', null, '3000', null, '400');
INSERT INTO `promotion` VALUES ('2', 'Promotion00000002', '0', '342', '总价促销策略', '2017-12-06 00:00:00', '2017-12-25 00:00:00', null, '3444', null, '0');
INSERT INTO `promotion` VALUES ('3', 'Promotion00000003', '0', '商品促销策略A', '商品促销策略', '2017-12-25 00:00:00', '2017-12-25 23:59:59', null, null, '200', null);
INSERT INTO `promotion` VALUES ('4', 'Promotion00000004', '1', '客户促销A', '客户促销策略', '2017-12-25 00:00:00', '2017-12-25 23:59:59', '4', null, '200', '400');
INSERT INTO `promotion` VALUES ('5', 'Promotion00000005', '1', '商品促销B', '商品促销策略', '2017-12-25 00:00:00', '2017-12-25 23:59:59', null, null, '20', null);
INSERT INTO `promotion` VALUES ('6', 'Promotion00000006', '0', 'vds', '商品促销策略', '2017-12-25 00:00:00', '2017-12-25 23:59:59', null, null, '0', null);
INSERT INTO `promotion` VALUES ('7', 'Promotion00000007', '1', '年末商品促销', '商品促销策略', '2017-12-26 00:00:00', '2017-12-29 23:59:59', null, null, '10', null);
INSERT INTO `promotion` VALUES ('8', 'Promotion00000008', '1', '2018客户促销', '客户促销策略', '2018-01-04 00:00:00', '2018-01-04 23:59:59', '2', null, '100', '100');
INSERT INTO `promotion` VALUES ('9', 'Promotion00000009', '1', '2018总价促销', '总价促销策略', '2018-01-01 00:00:00', '2018-01-30 23:59:59', null, '200', null, '200');

-- ----------------------------
-- Table structure for `purchaserefundbill`
-- ----------------------------
DROP TABLE IF EXISTS `purchaserefundbill`;
CREATE TABLE `purchaserefundbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `clientID` varchar(20) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchaserefundbill
-- ----------------------------
INSERT INTO `purchaserefundbill` VALUES ('1', 'JHTHD-20171225-00001', '草稿', '2017-12-25 23:04:54', 'User00000002', '备注', 'Client00000002', '240');

-- ----------------------------
-- Table structure for `purchasetradebill`
-- ----------------------------
DROP TABLE IF EXISTS `purchasetradebill`;
CREATE TABLE `purchasetradebill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `clientID` varchar(20) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchasetradebill
-- ----------------------------
INSERT INTO `purchasetradebill` VALUES ('1', 'JHD-20171225-00001', '草稿', '2017-12-25 22:55:46', 'User00000002', '', 'Client00000002', '160');
INSERT INTO `purchasetradebill` VALUES ('2', 'JHD-20180104-00001', '待审批', '2018-01-04 13:49:20', 'User00000002', '演示用', 'Client00000002', '1200');

-- ----------------------------
-- Table structure for `receiptbill`
-- ----------------------------
DROP TABLE IF EXISTS `receiptbill`;
CREATE TABLE `receiptbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `total` double NOT NULL,
  `clientID` varchar(20) NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receiptbill
-- ----------------------------
INSERT INTO `receiptbill` VALUES ('1', 'SKD-20171224-00001', '草稿', '2017-12-24 13:17:32', 'User00000005', '无', '100', 'Client00000003');
INSERT INTO `receiptbill` VALUES ('2', 'SKD-20171224-00002', '草稿', '2017-12-24 23:05:00', 'User00000005', '无', '0', 'Client00000003');
INSERT INTO `receiptbill` VALUES ('3', 'SKD-20171228-00001', '审批通过', '2017-12-28 14:09:58', 'User00000005', '无', '500', 'Client00000002');
INSERT INTO `receiptbill` VALUES ('4', 'SKD-20171228-00002', '审批通过', '2017-12-28 15:05:14', 'User00000005', '用于红冲编号为SKD-20171228-00001的单据', '-500', 'Client00000002');
INSERT INTO `receiptbill` VALUES ('5', 'SKD-20171228-00003', '待审批', '2017-12-28 15:30:40', 'User00000005', '用于红冲编号为SKD-20171228-00001的单据', '-500', 'Client00000002');

-- ----------------------------
-- Table structure for `salerefundbill`
-- ----------------------------
DROP TABLE IF EXISTS `salerefundbill`;
CREATE TABLE `salerefundbill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `clientID` varchar(20) NOT NULL,
  `salesmanID` varchar(20) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salerefundbill
-- ----------------------------
INSERT INTO `salerefundbill` VALUES ('1', 'XSTHD-20171225-00001', '草稿', '2017-12-25 23:33:37', 'User00000002', '', 'Client00000003', 'User00000002', '150');

-- ----------------------------
-- Table structure for `saletradebill`
-- ----------------------------
DROP TABLE IF EXISTS `saletradebill`;
CREATE TABLE `saletradebill` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(30) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `operatorID` varchar(20) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `clientID` varchar(20) NOT NULL,
  `salesmanID` varchar(20) NOT NULL,
  `promotionID` varchar(20) DEFAULT NULL,
  `totalBeforeDiscount` double NOT NULL,
  `discount` double NOT NULL,
  `amountOfVoucher` double NOT NULL,
  `totalAfterDiscount` double NOT NULL,
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of saletradebill
-- ----------------------------
INSERT INTO `saletradebill` VALUES ('1', 'XSD-20171226-00001', '审批通过', '2017-12-26 14:39:23', 'User00000002', '备注', 'Client00000003', 'User00000002', 'Promotion00000007', '160', '40', '30', '70');
INSERT INTO `saletradebill` VALUES ('2', 'XSD-20171227-00001', '审批通过', '2017-12-27 23:34:05', 'User00000002', '无', 'Client00000003', 'User00000002', 'Promotion00000007', '440', '200', '10', '230');
INSERT INTO `saletradebill` VALUES ('3', 'XSD-20180104-00001', '审批通过', '2018-01-04 14:28:01', 'User00000002', '无', 'Client00000003', 'User00000002', 'Promotion00000008', '5000', '100', '0', '4800');
INSERT INTO `saletradebill` VALUES ('4', 'XSD-20180106-00001', '草稿', '2018-01-06 23:52:03', 'User00000002', '', 'Client00000003', 'User00000002', 'Promotion00000009', '2700', '100', '200', '2400');

-- ----------------------------
-- Table structure for `transitem`
-- ----------------------------
DROP TABLE IF EXISTS `transitem`;
CREATE TABLE `transitem` (
  `site_ID` varchar(30) NOT NULL,
  `accountID` varchar(20) NOT NULL,
  `transAmount` double NOT NULL,
  `comment` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transitem
-- ----------------------------
INSERT INTO `transitem` VALUES ('SKD-20171224-00001', 'Account00000002', '100', '无');
INSERT INTO `transitem` VALUES ('FKD-20171223-00002', 'Account00000002', '4500', '无');
INSERT INTO `transitem` VALUES ('FKD-20171223-00002', 'Account00000001', '200', '无');
INSERT INTO `transitem` VALUES ('FKD-20171223-00001', 'Account00000002', '5000', '无');
INSERT INTO `transitem` VALUES ('FKD-20171224-00002', 'Account00000002', '20', '无');
INSERT INTO `transitem` VALUES ('FKD-20171225-00001', 'Account00000002', '200', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00001', 'Account00000002', '200', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00001', 'Account00000001', '300', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00002', 'Account00000002', '-200', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00002', 'Account00000001', '-300', '无');
INSERT INTO `transitem` VALUES ('FKD-20171228-00002', 'Account00000002', '300', '无');
INSERT INTO `transitem` VALUES ('FKD-20171228-00003', 'Account00000002', '-300', '无');
INSERT INTO `transitem` VALUES ('FKD-20171228-00004', 'Account00000002', '200', '无');
INSERT INTO `transitem` VALUES ('FKD-20171228-00005', 'Account00000002', '300', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00003', 'Account00000002', '200', '无');
INSERT INTO `transitem` VALUES ('SKD-20171228-00003', 'Account00000001', '300', '无');
INSERT INTO `transitem` VALUES ('FKD-20171228-00006', 'Account00000002', '-200', '无');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `keyID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(20) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `jobName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(5) NOT NULL,
  `top` tinyint(1) NOT NULL,
  `login` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`keyID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'User00000001', '1', '陈道蓄', '管理员', 'admin', 'admin', '70', '1', '0');
INSERT INTO `user` VALUES ('2', 'User00000002', '1', '陈骁', '进货销售人员', 'cx', '123', '19', '1', '0');
INSERT INTO `user` VALUES ('3', 'User00000003', '0', '测试', '总经理', 'test', 'test', '10', '0', '0');
INSERT INTO `user` VALUES ('4', 'User00000004', '0', '菜鸡', '总经理', 'c', '123', '12', '0', '0');
INSERT INTO `user` VALUES ('5', 'User00000005', '1', '王宁', '财务人员', 'wn', '123', '19', '1', '0');
INSERT INTO `user` VALUES ('6', 'User00000006', '1', '陈思彤', '总经理', 'cst', '123', '19', '0', '0');
INSERT INTO `user` VALUES ('7', 'User00000007', '1', '王颀涵', '库存管理人员', 'wqh', '123', '19', '0', '0');
