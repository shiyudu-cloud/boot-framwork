CREATE TABLE {{table_name}}  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '应用名称',
  `host` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '8080' COMMENT '主机',
  `port` int(5) NULL DEFAULT NULL COMMENT '端口号',
  `clint_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求客户端的Ip',
  `req_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求地址',
  `headers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '请求头部信息(可选择记录) 默认记录user-agent,content-type',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作类型,默认值undefined',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '方法步骤内容,默认是空,可使用LogData.step进行内容步骤记录',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求的本地java方法',
  `args` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '方法请求参数',
  `resp_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '方法响应参数',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '整个方法耗时',
  `log_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'Log产生时间,LogData对象初始化的时间',
  `thread_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '线程名称',
  `thread_id` int(5) NULL DEFAULT NULL COMMENT '线程Id',
  `success` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '执行状态,成功(true)/异常(false)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;