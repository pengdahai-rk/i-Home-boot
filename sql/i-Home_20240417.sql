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
    `nickname`    varchar(100)             NOT NULL COMMENT '昵称',
    `avatar`      varchar(100)                      DEFAULT '' comment '头像地址',
    `gender`      tinyint(1)               NOT NULL DEFAULT 0 COMMENT '性别 0保密 1男 2女 3其它',
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
    `username`        varchar(32) NOT NULL COMMENT '用户名',
    `is_active`       tinyint(1)  NOT NULL DEFAULT 0 COMMENT '是否注销 0正常 1注销 默认0',
    `user_type`       varchar(2)           DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
    `password`        char(64)    NOT NULL COMMENT '密码',
    `phone`           varchar(15)          DEFAULT '' COMMENT '手机号',
    `email`           varchar(64) NOT NULL COMMENT '邮箱',
    `sign_in_date`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最近登录时间',
    `sign_in_ip`      varchar(15) NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `pwd_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '密码最后更新时间',
    `create_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `create_by`       varchar(64)          DEFAULT '' COMMENT '创建者',
    `update_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`       varchar(64)          DEFAULT '' COMMENT '更新者',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_name` (`username`) USING BTREE COMMENT '用户名唯一索引',
    UNIQUE KEY `uk_email` (`email`) USING BTREE COMMENT '邮箱唯一索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户登录信息表'
  ROW_FORMAT = Dynamic;

INSERT INTO `i-home`.`user_login`(`id`, `username`, `is_active`, `user_type`, `password`, `phone`, `email`,
                                  `sign_in_date`, `sign_in_ip`, `create_time`, `create_by`,
                                  `update_time`, `update_by`)
VALUES (1, 'p7i', 0, '00', '$2a$10$2EJFiT7gxjQ2kLLEFngLsuvkUWGgEb1bm26KiEpFowmAQH8Qe1h9e', '18702715850',
        'pengdahai216@126.com', '2024-06-19 17:33:17', '', '2024-06-19 17:33:17', '', '2024-06-19 17:34:24', '');
INSERT INTO `i-home`.`user_login`(`id`, `username`, `is_active`, `user_type`, `password`, `phone`, `email`,
                                  `sign_in_date`, `sign_in_ip`, `create_time`, `create_by`,
                                  `update_time`, `update_by`)
VALUES (2, 'l7e', 0, '00', '$2a$10$JkhcD77zZ3Uh8stNjjNu9eEvCVWG.hwR1lTWH6h3awAE3hiBLNPQO', '18702715850',
        'pengdahai216@163.com', '2024-06-19 17:33:17', '', '2024-06-19 17:33:17', '', '2024-06-19 17:34:24', '');
