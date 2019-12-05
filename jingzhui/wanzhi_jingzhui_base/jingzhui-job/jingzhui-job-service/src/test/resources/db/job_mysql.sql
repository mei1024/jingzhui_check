drop table if exists `job_cron_timer`;
CREATE TABLE `job_cron_timer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组',
  `cron` varchar(32) NOT NULL COMMENT '任务执行时间',
  `job_data` varchar(512) DEFAULT NULL COMMENT '任务执行参数',
  `proc` varchar(128) DEFAULT NULL COMMENT '任务执行程序 url:开头为URL请求',
  `retry_max_attempts` int(11) DEFAULT NULL COMMENT '失败执行最大次数',
  `retry_max_interval` int(11) DEFAULT NULL COMMENT '重试间隔（秒）',
  `status` varchar(16) NOT NULL COMMENT '状态 READY=待加入,RUNNING=运行中,PAUSED=已暂停',
  `last_exe_start_time` bigint(20) DEFAULT NULL COMMENT '上次执行开始时间',
  `last_exe_end_time` bigint(20) DEFAULT NULL COMMENT '上次执行结束时间',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_job` (`job_group`,`job_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='任务定时器||timer||job';

drop table if exists `job_once_timer`;
CREATE TABLE `job_once_timer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组',
  `job_data` varchar(512) DEFAULT NULL COMMENT '任务执行参数',
  `proc` varchar(128) DEFAULT NULL COMMENT '任务执行程序 url:开头为URL请求',
  `status` varchar(16) NOT NULL COMMENT '状态 READY=待加入,WATTING=待处理,SUCCESS=成功,FAILED=失败',
  `next_exe_time` bigint(20) NOT NULL COMMENT '下次执行开始时间',
  `last_exe_start_time` bigint(20) DEFAULT NULL COMMENT '最后一次执行开始时间',
  `last_exe_end_time` bigint(20) DEFAULT NULL COMMENT '最后一次执行结束时间',
  `exe_start_time` bigint(20) DEFAULT NULL COMMENT '执行开始时间',
  `exe_end_time` bigint(20) DEFAULT NULL COMMENT '执行结束时间',
  `exe_count` int NOT NULL COMMENT '当前执行次数',
  `retry_max_attempts` int(11) DEFAULT NULL COMMENT '失败执行最大次数',
  `retry_max_interval` int(11) DEFAULT NULL COMMENT '重试间隔（秒）',
  `result` text DEFAULT NULL COMMENT '执行结果',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_job` (`job_group`,`job_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='一次性定时器||timer||job';


