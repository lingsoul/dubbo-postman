
CREATE TABLE `t_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zk_id` bigint(20) NOT NULL,
  `app_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_zid` (`zk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_scene
# ------------------------------------------------------------

CREATE TABLE `t_scene` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `scene_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `scene_data` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_service
# ------------------------------------------------------------

CREATE TABLE `t_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zk_id` bigint(20) NOT NULL COMMENT 'zk id',
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `service_info` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务信息',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_zkappid` (`zk_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='应用服务信息表';



# Dump of table t_service_copy
# ------------------------------------------------------------

CREATE TABLE `t_service_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zk_id` bigint(20) NOT NULL COMMENT 'zk id',
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `service_info` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务信息',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_zkappid` (`zk_id`,`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='应用服务信息表';



# Dump of table t_test_case
# ------------------------------------------------------------

CREATE TABLE `t_test_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `case_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `case_data` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_test_case_group
# ------------------------------------------------------------

CREATE TABLE `t_test_case_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_zk_address
# ------------------------------------------------------------

CREATE TABLE `t_zk_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zk_address` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

