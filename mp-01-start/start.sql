-- 创建用户表
CREATE TABLE `mp_user`
(
    `user_id`     bigint(20) PRIMARY KEY COMMENT '主键',
    `name`        varchar(30) DEFAULT NULL COMMENT '姓名',
    `age`         int(11)     DEFAULT NULL COMMENT '年龄',
    `email`       varchar(50) DEFAULT NULL COMMENT '邮箱',
    `manager_id`  bigint(20)  DEFAULT NULL COMMENT '直属上级id',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 初始化数据
INSERT INTO mp_user ( user_id, name, age, email, manager_id
                    , create_time)
VALUES ( 1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL
       , '2019-01-11 14:20:20'),
       ( 1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553
       , '2019-02-05 11:12:22'),
       ( 1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385
       , '2019-02-14 08:31:16'),
       ( 1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385
       , '2019-01-14 09:15:15'),
       ( 1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385
       , '2019-01-14 09:48:16'),
       ( 1484112380493766657, '卢本伟', 22, NULL, 1088248166370832385
       , NULL),
       ( 1484118030976262146, '卢本伟', 34, 'lbw@baomidou.com', 1088248166370832385
       , NULL),
       ( 1484112973270581249, '王雷', 32, 'wl@baomidou.com', 1088248166370832385
       , '2019-01-09 01:42:11');


-- 创建学生表
CREATE TABLE `student`
(
    `id`   bigint(20) PRIMARY KEY COMMENT '主键',
    `name` varchar(30) DEFAULT NULL COMMENT '姓名',
    `age`  int(11)     DEFAULT NULL COMMENT '年龄'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- 初始化数据
INSERT INTO student (id, name, age)
VALUES (1088248166370832385, '王天风', 25),
       (1088250446457389058, '李艺伟', 28),
       (1094590409767661570, '张雨琪', 31),
       (1094592041087729666, '刘红雨', 32),
       (1484112380493766657, '卢本伟', 22),
       (1484118030976262146, '卢本伟', 34),
       (1484112973270581249, '王雷', 32);
