/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31 : Database - shiro-test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro-test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro-test`;

/*Table structure for table `tbl_perm` */

DROP TABLE IF EXISTS `tbl_perm`;

CREATE TABLE `tbl_perm` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `perm_name` varchar(100) NOT NULL COMMENT '权限名',
  `parent_id` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '父权限id，无父权限为0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_perm` */

insert  into `tbl_perm`(`id`,`perm_name`,`parent_id`) values (1,'管理员权限1',0),(2,'1-1',1),(3,'1-1-1',2),(4,'1-2',1),(5,'用户权限2',0),(6,'2-1',5);

/*Table structure for table `tbl_role` */

DROP TABLE IF EXISTS `tbl_role`;

CREATE TABLE `tbl_role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_role` */

insert  into `tbl_role`(`id`,`role_name`) values (1,'admin'),(2,'user');

/*Table structure for table `tbl_role_perm` */

DROP TABLE IF EXISTS `tbl_role_perm`;

CREATE TABLE `tbl_role_perm` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int(20) unsigned NOT NULL,
  `perm_id` int(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`),
  UNIQUE KEY `perm_id` (`perm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_role_perm` */

insert  into `tbl_role_perm`(`id`,`role_id`,`perm_id`) values (1,1,1),(2,2,5);

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '用户密码',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `salt` varchar(200) DEFAULT NULL COMMENT '密码盐',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`username`,`password`,`real_name`,`salt`,`create_time`,`update_time`) values (1,'nihao','1231','陈浩','2131','2020-09-18 20:36:04','2020-09-18 20:36:04'),(2,'lihua','12314','李华','12312','2020-09-19 00:10:27','2020-09-19 00:10:27'),(3,'chenhua','12313','陈华','2132','2020-09-19 12:50:47','2020-09-19 12:50:47'),(4,'jsonUser','23123','测试Json','2312','2020-09-19 13:39:00','2020-09-19 13:39:00'),(5,'jsonUser22','23123','测试Json222','2312','2020-09-19 13:39:00','2020-09-19 13:39:00'),(6,'jsonUser22','23123','测试Json222','2312','2020-09-19 13:39:00','2020-09-19 13:39:00');

/*Table structure for table `tbl_user_role` */

DROP TABLE IF EXISTS `tbl_user_role`;

CREATE TABLE `tbl_user_role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户权限关联id',
  `user_id` int(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(20) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user_role` */

insert  into `tbl_user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
