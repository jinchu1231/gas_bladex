-- -----------------------------------
-- 新增任务服务表
-- -----------------------------------
CREATE TABLE [dbo].[blade_job_server] (
    [id] bigint NOT NULL,
    [job_server_name] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_server_url] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_app_name] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_app_password] nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_remark] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [create_user] bigint NULL,
    [create_dept] bigint NULL,
    [create_time] datetime2(0) NULL,
    [update_user] bigint NULL,
    [update_time] datetime2(0) NULL,
    [status] int NULL,
    [is_deleted] int NULL,
    PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    )
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务服务名称',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'job_server_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务服务器地址',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'job_server_url'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务应用名称',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'job_app_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务应用密码',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'job_app_password'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务备注',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'job_remark'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'create_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建部门',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'create_dept'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'create_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'update_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'update_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'状态',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'status'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否已删除',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server',
    'COLUMN', N'is_deleted'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务服务表',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_server';

INSERT INTO [dbo].[blade_job_server]([id], [job_server_name], [job_server_url], [job_app_name], [job_app_password], [job_remark], [create_user], [create_dept], [create_time], [update_user], [update_time], [status], [is_deleted]) VALUES (1741512022505590785, N'任务调度服务器', N'127.0.0.1:7700', N'blade-job', N'blade-job', N'基础服务', 1123598821738675201, 1123598813738675201, N'2024-01-01 01:29:57', 1123598821738675201, N'2024-01-01 01:29:57', 1, 0);

-- -----------------------------------
-- 新增任务信息表
-- -----------------------------------
CREATE TABLE [dbo].[blade_job_info] (
    [id] bigint NOT NULL,
    [job_server_id] bigint NULL,
    [job_id] bigint NULL,
    [job_name] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_description] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [job_params] nvarchar(max) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [time_expression_type] int NULL,
    [time_expression] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [execute_type] int NULL,
    [processor_type] int NULL,
    [processor_info] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [max_instance_num] int NULL,
    [concurrency] int NULL,
    [instance_time_limit] bigint NULL,
    [instance_retry_num] int NULL,
    [task_retry_num] int NULL,
    [min_cpu_cores] float(53) NULL,
    [min_memory_space] float(53) NULL,
    [min_disk_space] float(53) NULL,
    [designated_workers] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [max_worker_count] int NULL,
    [notify_user_ids] nvarchar(2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [enable] int NULL,
    [dispatch_strategy] int NULL,
    [lifecycle] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [alert_threshold] int NULL,
    [statistic_window_len] int NULL,
    [silence_window_len] int NULL,
    [log_type] int NULL,
    [log_level] int NULL,
    [extra] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    [create_user] bigint NULL,
    [create_dept] bigint NULL,
    [create_time] datetime2(0) NULL,
    [update_user] bigint NULL,
    [update_time] datetime2(0) NULL,
    [status] int NULL,
    [is_deleted] int NULL,
    PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    )
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务服务ID',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'job_server_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务 ID，可选，null 代表创建任务，否则填写需要修改的任务 ID',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'job_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务名称',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'job_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务描述',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'job_description'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务参数，Processor#process 方法入参 TaskContext 对象的 jobParams 字段',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'job_params'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'时间表达式类型，枚举值',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'time_expression_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'时间表达式，填写类型由 timeExpressionType 决定，比如 CRON 需要填写 CRON 表达式',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'time_expression'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'执行类型，枚举值',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'execute_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'处理器类型，枚举值',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'processor_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'处理器参数，填写类型由 processorType 决定，如Java 处理器需要填写全限定类名，如：com.github.kfcfans.oms.processors.demo.MapReduceProcessorDemo',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'processor_info'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'最大实例数，该任务同时执行的数量（任务和实例就像是类和对象的关系，任务被调度执行后被称为实例）',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'max_instance_num'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'单机线程并发数，表示该实例执行过程中每个Worker 使用的线程数量',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'concurrency'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务实例运行时间限制，0 代表无任何限制，超时会被打断并判定为执行失败',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'instance_time_limit'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'instanceRetryNum	任务实例重试次数，整个任务失败时重试，代价大，不推荐使用',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'instance_retry_num'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'taskRetryNum	Task 重试次数，每个子 Task 失败后单独重试，代价小，推荐使用',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'task_retry_num'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'minCpuCores	最小可用 CPU 核心数，CPU 可用核心数小于该值的 Worker 将不会执行该任务，0 代表无任何限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'min_cpu_cores'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'最小内存大小（GB），可用内存小于该值的Worker 将不会执行该任务，0 代表无任何限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'min_memory_space'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'最小磁盘大小（GB），可用磁盘空间小于该值的Worker 将不会执行该任务，0 代表无任何限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'min_disk_space'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'指定机器执行，设置该参数后只有列表中的机器允许执行该任务，空代表不指定机器',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'designated_workers'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'最大执行机器数量，限定调动执行的机器数量，0代表无限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'max_worker_count'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'接收报警的用户 ID 列表',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'notify_user_ids'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否启用该任务，未启用的任务不会被调度',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'enable'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'调度策略，枚举，目前支持随机（RANDOM）和 健康度优先（HEALTH_FIRST）',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'dispatch_strategy'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'lifecycle	生命周期（预留，用于指定定时调度任务的生效时间范围）',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'lifecycle'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'错误阈值，0代表不限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'alert_threshold'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'统计的窗口长度(s)，0代表不限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'statistic_window_len'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'沉默时间窗口(s)，0代表不限制',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'silence_window_len'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'日志配置',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'log_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'日志级别',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'log_level'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'扩展字段（供开发者使用，用于功能扩展，powerjob 自身不会使用该字段）',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'extra'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'create_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建部门',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'create_dept'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'create_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'update_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'update_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'状态',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'status'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否已删除',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info',
    'COLUMN', N'is_deleted'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'任务信息表',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_job_info';

-- -----------------------------------
-- 新增任务菜单信息
-- -----------------------------------
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [component], [remark], [is_deleted])
VALUES (N'1164733399669962401', 0, N'job', N'任务管理', N'job', N'/job', N'iconfont iconicon_cspace', 5, 1, 0, 1, N'', NULL, 0);

INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962501', N'1164733399669962401', N'jobserver', N'任务应用', N'menu', N'/job/jobserver', N'iconfont icon-shouji', 1, 1, 0, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962502', N'1164733399669962501', N'jobserver_add', N'新增', N'add', N'/job/jobserver/add', N'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962503', N'1164733399669962501', N'jobserver_edit', N'修改', N'edit', N'/job/jobserver/edit', N'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962504', N'1164733399669962501', N'jobserver_delete', N'删除', N'delete', N'/api/blade-job/jobserver/remove', N'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962505', N'1164733399669962501', N'jobserver_view', N'查看', N'view', N'/job/jobserver/view', N'file-text', 4, 2, 2, 1, NULL, 0);


INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962601', N'1164733399669962401', N'jobinfo', N'任务配置', N'menu', N'/job/jobinfo', N'iconfont icon-dongtai', 1, 1, 0, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962602', N'1164733399669962601', N'jobinfo_add', N'新增', N'add', N'/job/jobinfo/add', N'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962603', N'1164733399669962601', N'jobinfo_edit', N'修改', N'edit', N'/job/jobinfo/edit', N'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962604', N'1164733399669962601', N'jobinfo_delete', N'删除', N'delete', N'/api/blade-job/jobinfo/remove', N'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [remark], [is_deleted])
VALUES (N'1164733399669962605', N'1164733399669962601', N'jobinfo_view', N'查看', N'view', N'/job/jobinfo/view', N'file-text', 4, 2, 2, 1, NULL, 0);

