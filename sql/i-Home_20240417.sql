# demo信息表
DROP TABLE IF EXISTS `home_demo`;
CREATE TABLE `home_demo`
(
    `id`           bigint(0)   NOT NULL COMMENT '主键id',
    `demo_name`    varchar(32) NOT NULL COMMENT 'demo名称',
    `demo_decimal` decimal(10, 8)       DEFAULT NULL COMMENT 'demo小数',
    `demo_blob`    blob COMMENT 'demo二进制对象',
    `demo_text`    text COLLATE utf8mb4_general_ci COMMENT 'demo文本',
    `demo_status`  tinyint(1)  NOT NULL DEFAULT 0 COMMENT 'demo  status 0正常 1注销 默认0',
    `create_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `create_by`    varchar(64)          DEFAULT '' COMMENT '创建者',
    `update_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`    varchar(64)          DEFAULT '' COMMENT '更新者',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'demo表格'
  ROW_FORMAT = Dynamic;

# 用户信息表
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `id`          bigint(0) AUTO_INCREMENT NOT NULL COMMENT '主键id',
    `user_id`     bigint(0)                NOT NULL COMMENT '用户id',
    `avatar`      varchar(100)                      DEFAULT '' comment '头像地址',
    `gender`      tinyint(1)               NOT NULL DEFAULT 0 COMMENT '是否注销 0保密 1男 2女 3其它',
    `birthday`    date COMMENT '生日',
    `hometown`    varchar(500)                      DEFAULT '' COMMENT '家乡',
    `about_me`    varchar(500)                      DEFAULT '' COMMENT '关于我',
    `create_time` datetime(0)              NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `create_by`   varchar(64)                       DEFAULT '' COMMENT '创建者',
    `update_time` datetime(0)              NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(64)                       DEFAULT '' COMMENT '更新者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE COMMENT '用户id唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

# 用户登录信息表
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`
(
    `id`              bigint(0)   NOT NULL COMMENT '主键id',
    `user_name`       varchar(32) NOT NULL COMMENT '用户名',
    `is_active`       tinyint(1)  NOT NULL DEFAULT 0 COMMENT '是否注销 0正常 1注销 默认0',
    `user_type`       varchar(2)           DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
    `password`        char(32)    NOT NULL COMMENT '密码',
    `phone`           varchar(15)          DEFAULT '' COMMENT '手机号',
    `email`           varchar(64) NOT NULL COMMENT '邮箱',
    `salt`            char(6)     NOT NULL COMMENT '盐值',
    `sign_in_date`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最近登录时间',
    `sign_in_ip`      varchar(15) NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `pwd_update_date` datetime(0) COMMENT '密码最后更新时间',
    `create_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `create_by`       varchar(64)          DEFAULT '' COMMENT '创建者',
    `update_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`       varchar(64)          DEFAULT '' COMMENT '更新者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_name` (`user_name`) USING BTREE COMMENT '用户名唯一索引',
    UNIQUE KEY `uk_email` (`email`) USING BTREE COMMENT '邮箱唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户登录信息表'
  ROW_FORMAT = Dynamic;

