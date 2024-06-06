-- -----------------------------------
-- 新增任务服务表
-- -----------------------------------
CREATE TABLE `blade_job_server`  (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `job_server_name` varchar(50) NULL COMMENT '任务服务名称',
    `job_server_url` varchar(255) NULL COMMENT '任务服务器地址',
    `job_app_name` varchar(20) NULL COMMENT '任务应用名称',
    `job_app_password` varchar(100) NULL COMMENT '任务应用密码',
    `job_remark` varchar(255) NULL COMMENT '任务备注',
    `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `status` int(2) NULL DEFAULT NULL COMMENT '状态',
    `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
    PRIMARY KEY (`id`)
) COMMENT = '任务服务表';

INSERT INTO `blade_job_server`(`id`, `job_server_name`, `job_server_url`, `job_app_name`, `job_app_password`, `job_remark`, `create_user`, `create_dept`, `create_time`, `update_user`, `update_time`, `status`, `is_deleted`) VALUES (1741512022505590785, '任务调度服务器', '127.0.0.1:7700', 'blade-job', 'blade-job', '基础服务', 1123598821738675201, 1123598813738675201, '2024-01-01 01:29:57', 1123598821738675201, '2024-01-01 01:29:57', 1, 0);

-- -----------------------------------
-- 新增任务信息表
-- -----------------------------------
CREATE TABLE `blade_job_info`  (
     `id` bigint(20) NOT NULL COMMENT '主键',
     `job_server_id` bigint(20) NULL DEFAULT NULL COMMENT '任务服务ID',
     `job_id` bigint(20) NULL DEFAULT NULL COMMENT '任务 ID，可选，null 代表创建任务，否则填写需要修改的任务 ID',
     `job_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
     `job_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务描述',
     `job_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '任务参数，Processor#process 方法入参 TaskContext 对象的 jobParams 字段',
     `time_expression_type` int(2) NULL DEFAULT NULL COMMENT '时间表达式类型，枚举值',
     `time_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时间表达式，填写类型由 timeExpressionType 决定，比如 CRON 需要填写 CRON 表达式',
     `execute_type` int(2) NULL DEFAULT NULL COMMENT '执行类型，枚举值',
     `processor_type` int(2) NULL DEFAULT NULL COMMENT '处理器类型，枚举值',
     `processor_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理器参数，填写类型由 processorType 决定，如Java 处理器需要填写全限定类名，如：com.github.kfcfans.oms.processors.demo.MapReduceProcessorDemo',
     `max_instance_num` int(11) NULL DEFAULT NULL COMMENT '最大实例数，该任务同时执行的数量（任务和实例就像是类和对象的关系，任务被调度执行后被称为实例）',
     `concurrency` int(11) NULL DEFAULT NULL COMMENT '单机线程并发数，表示该实例执行过程中每个Worker 使用的线程数量',
     `instance_time_limit` bigint(20) NULL DEFAULT NULL COMMENT '任务实例运行时间限制，0 代表无任何限制，超时会被打断并判定为执行失败',
     `instance_retry_num` int(11) NULL DEFAULT NULL COMMENT 'instanceRetryNum	任务实例重试次数，整个任务失败时重试，代价大，不推荐使用',
     `task_retry_num` int(11) NULL DEFAULT NULL COMMENT 'taskRetryNum	Task 重试次数，每个子 Task 失败后单独重试，代价小，推荐使用',
     `min_cpu_cores` double NULL DEFAULT NULL COMMENT 'minCpuCores	最小可用 CPU 核心数，CPU 可用核心数小于该值的 Worker 将不会执行该任务，0 代表无任何限制',
     `min_memory_space` double NULL DEFAULT NULL COMMENT '最小内存大小（GB），可用内存小于该值的Worker 将不会执行该任务，0 代表无任何限制',
     `min_disk_space` double NULL DEFAULT NULL COMMENT '最小磁盘大小（GB），可用磁盘空间小于该值的Worker 将不会执行该任务，0 代表无任何限制',
     `designated_workers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指定机器执行，设置该参数后只有列表中的机器允许执行该任务，空代表不指定机器',
     `max_worker_count` int(2) NULL DEFAULT NULL COMMENT '最大执行机器数量，限定调动执行的机器数量，0代表无限制',
     `notify_user_ids` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收报警的用户 ID 列表',
     `enable` int(2) NULL DEFAULT NULL COMMENT '是否启用该任务，未启用的任务不会被调度',
     `dispatch_strategy` int(2) NULL DEFAULT NULL COMMENT '调度策略，枚举，目前支持随机（RANDOM）和 健康度优先（HEALTH_FIRST）',
     `lifecycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'lifecycle	生命周期（预留，用于指定定时调度任务的生效时间范围）',
     `alert_threshold` int(2) NULL DEFAULT NULL COMMENT '错误阈值，0代表不限制',
     `statistic_window_len` int(2) NULL DEFAULT NULL COMMENT '统计的窗口长度(s)，0代表不限制',
     `silence_window_len` int(2) NULL DEFAULT NULL COMMENT '沉默时间窗口(s)，0代表不限制',
     `log_type` int(2) NULL DEFAULT NULL COMMENT '日志配置',
     `log_level` int(2) NULL DEFAULT NULL COMMENT '日志级别',
     `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段（供开发者使用，用于功能扩展，powerjob 自身不会使用该字段）',
     `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
     `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
     `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
     `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
     `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
     `status` int(2) NULL DEFAULT NULL COMMENT '状态',
     `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
     PRIMARY KEY (`id`)
) COMMENT = '日志信息表';

-- -----------------------------------
-- 新增任务菜单信息
-- -----------------------------------
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `component`, `remark`, `is_deleted`)
VALUES ('1164733399669962401', 0, 'job', '任务管理', 'job', '/job', 'iconfont iconicon_cspace', 5, 1, 0, 1, '', NULL, 0);

INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962501', '1164733399669962401', 'jobserver', '任务应用', 'menu', '/job/jobserver', 'iconfont icon-shouji', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962502', '1164733399669962501', 'jobserver_add', '新增', 'add', '/job/jobserver/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962503', '1164733399669962501', 'jobserver_edit', '修改', 'edit', '/job/jobserver/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962504', '1164733399669962501', 'jobserver_delete', '删除', 'delete', '/api/blade-job/jobserver/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962505', '1164733399669962501', 'jobserver_view', '查看', 'view', '/job/jobserver/view', 'file-text', 4, 2, 2, 1, NULL, 0);


INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962601', '1164733399669962401', 'jobinfo', '任务配置', 'menu', '/job/jobinfo', 'iconfont icon-dongtai', 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962602', '1164733399669962601', 'jobinfo_add', '新增', 'add', '/job/jobinfo/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962603', '1164733399669962601', 'jobinfo_edit', '修改', 'edit', '/job/jobinfo/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962604', '1164733399669962601', 'jobinfo_delete', '删除', 'delete', '/api/blade-job/jobinfo/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1164733399669962605', '1164733399669962601', 'jobinfo_view', '查看', 'view', '/job/jobinfo/view', 'file-text', 4, 2, 2, 1, NULL, 0);

