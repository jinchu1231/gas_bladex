-- -----------------------------------
-- 新增任务表
-- -----------------------------------
CREATE TABLE "blade_job_server" (
   "id" int8 NOT NULL,
   "job_server_name" varchar(50) COLLATE "pg_catalog"."default",
   "job_server_url" varchar(255) COLLATE "pg_catalog"."default",
   "job_app_name" varchar(20) COLLATE "pg_catalog"."default",
   "job_app_password" varchar(100) COLLATE "pg_catalog"."default",
   "job_remark" varchar(255) COLLATE "pg_catalog"."default",
   "create_user" int8,
   "create_dept" int8,
   "create_time" timestamp(6),
   "update_user" int8,
   "update_time" timestamp(6),
   "status" int4,
   "is_deleted" int4,
   PRIMARY KEY ("id")
);

COMMENT ON COLUMN "blade_job_server"."id" IS '主键';

COMMENT ON COLUMN "blade_job_server"."job_server_name" IS '任务服务名称';

COMMENT ON COLUMN "blade_job_server"."job_server_url" IS '任务服务器地址';

COMMENT ON COLUMN "blade_job_server"."job_app_name" IS '任务应用名称';

COMMENT ON COLUMN "blade_job_server"."job_app_password" IS '任务应用密码';

COMMENT ON COLUMN "blade_job_server"."job_remark" IS '任务备注';

COMMENT ON COLUMN "blade_job_server"."create_user" IS '创建人';

COMMENT ON COLUMN "blade_job_server"."create_dept" IS '创建部门';

COMMENT ON COLUMN "blade_job_server"."create_time" IS '创建时间';

COMMENT ON COLUMN "blade_job_server"."update_user" IS '修改人';

COMMENT ON COLUMN "blade_job_server"."update_time" IS '修改时间';

COMMENT ON COLUMN "blade_job_server"."status" IS '状态';

COMMENT ON COLUMN "blade_job_server"."is_deleted" IS '是否已删除';

COMMENT ON TABLE "blade_job_server" IS '任务服务表';

INSERT INTO "blade_job_server"("id", "job_server_name", "job_server_url", "job_app_name", "job_app_password", "job_remark", "create_user", "create_dept", "create_time", "update_user", "update_time", "status", "is_deleted") VALUES (1741512022505590785, '任务调度服务器', '127.0.0.1:7700', 'blade-job', 'blade-job', '基础服务', 1123598821738675201, 1123598813738675201, '2024-01-01 01:29:57', 1123598821738675201, '2024-01-01 01:29:57', 1, 0);

-- -----------------------------------
-- 新增任务信息表
-- -----------------------------------
CREATE TABLE "blade_job_info" (
   "id" int8 NOT NULL,
   "job_server_id" int8,
   "job_id" int8,
   "job_name" varchar(50) COLLATE "pg_catalog"."default",
   "job_description" varchar(255) COLLATE "pg_catalog"."default",
   "job_params" text COLLATE "pg_catalog"."default",
   "time_expression_type" int4,
   "time_expression" varchar(255) COLLATE "pg_catalog"."default",
   "execute_type" int4,
   "processor_type" int4,
   "processor_info" varchar(255) COLLATE "pg_catalog"."default",
   "max_instance_num" int4,
   "concurrency" int4,
   "instance_time_limit" int8,
   "instance_retry_num" int4,
   "task_retry_num" int4,
   "min_cpu_cores" float8,
   "min_memory_space" float8,
   "min_disk_space" float8,
   "designated_workers" varchar(255) COLLATE "pg_catalog"."default",
   "max_worker_count" int4,
   "notify_user_ids" varchar(2000) COLLATE "pg_catalog"."default",
   "enable" int4,
   "dispatch_strategy" int4,
   "lifecycle" varchar(255) COLLATE "pg_catalog"."default",
   "alert_threshold" int4,
   "statistic_window_len" int4,
   "silence_window_len" int4,
   "log_type" int4,
   "log_level" int4,
   "extra" varchar(255) COLLATE "pg_catalog"."default",
   "create_user" int8,
   "create_dept" int8,
   "create_time" timestamp(6),
   "update_user" int8,
   "update_time" timestamp(6),
   "status" int4,
   "is_deleted" int4,
   PRIMARY KEY ("id")
);

COMMENT ON COLUMN "blade_job_info"."id" IS '主键';

COMMENT ON COLUMN "blade_job_info"."job_server_id" IS '任务服务ID';

COMMENT ON COLUMN "blade_job_info"."job_id" IS '任务 ID，可选，null 代表创建任务，否则填写需要修改的任务 ID';

COMMENT ON COLUMN "blade_job_info"."job_name" IS '任务名称';

COMMENT ON COLUMN "blade_job_info"."job_description" IS '任务描述';

COMMENT ON COLUMN "blade_job_info"."job_params" IS '任务参数，Processor#process 方法入参 TaskContext 对象的 jobParams 字段';

COMMENT ON COLUMN "blade_job_info"."time_expression_type" IS '时间表达式类型，枚举值';

COMMENT ON COLUMN "blade_job_info"."time_expression" IS '时间表达式，填写类型由 timeExpressionType 决定，比如 CRON 需要填写 CRON 表达式';

COMMENT ON COLUMN "blade_job_info"."execute_type" IS '执行类型，枚举值';

COMMENT ON COLUMN "blade_job_info"."processor_type" IS '处理器类型，枚举值';

COMMENT ON COLUMN "blade_job_info"."processor_info" IS '处理器参数，填写类型由 processorType 决定，如Java 处理器需要填写全限定类名，如：com.github.kfcfans.oms.processors.demo.MapReduceProcessorDemo';

COMMENT ON COLUMN "blade_job_info"."max_instance_num" IS '最大实例数，该任务同时执行的数量（任务和实例就像是类和对象的关系，任务被调度执行后被称为实例）';

COMMENT ON COLUMN "blade_job_info"."concurrency" IS '单机线程并发数，表示该实例执行过程中每个Worker 使用的线程数量';

COMMENT ON COLUMN "blade_job_info"."instance_time_limit" IS '任务实例运行时间限制，0 代表无任何限制，超时会被打断并判定为执行失败';

COMMENT ON COLUMN "blade_job_info"."instance_retry_num" IS 'instanceRetryNum	任务实例重试次数，整个任务失败时重试，代价大，不推荐使用';

COMMENT ON COLUMN "blade_job_info"."task_retry_num" IS 'taskRetryNum	Task 重试次数，每个子 Task 失败后单独重试，代价小，推荐使用';

COMMENT ON COLUMN "blade_job_info"."min_cpu_cores" IS 'minCpuCores	最小可用 CPU 核心数，CPU 可用核心数小于该值的 Worker 将不会执行该任务，0 代表无任何限制';

COMMENT ON COLUMN "blade_job_info"."min_memory_space" IS '最小内存大小（GB），可用内存小于该值的Worker 将不会执行该任务，0 代表无任何限制';

COMMENT ON COLUMN "blade_job_info"."min_disk_space" IS '最小磁盘大小（GB），可用磁盘空间小于该值的Worker 将不会执行该任务，0 代表无任何限制';

COMMENT ON COLUMN "blade_job_info"."designated_workers" IS '指定机器执行，设置该参数后只有列表中的机器允许执行该任务，空代表不指定机器';

COMMENT ON COLUMN "blade_job_info"."max_worker_count" IS '最大执行机器数量，限定调动执行的机器数量，0代表无限制';

COMMENT ON COLUMN "blade_job_info"."notify_user_ids" IS '接收报警的用户 ID 列表';

COMMENT ON COLUMN "blade_job_info"."enable" IS '是否启用该任务，未启用的任务不会被调度';

COMMENT ON COLUMN "blade_job_info"."dispatch_strategy" IS '调度策略，枚举，目前支持随机（RANDOM）和 健康度优先（HEALTH_FIRST）';

COMMENT ON COLUMN "blade_job_info"."lifecycle" IS 'lifecycle	生命周期（预留，用于指定定时调度任务的生效时间范围）';

COMMENT ON COLUMN "blade_job_info"."alert_threshold" IS '错误阈值，0代表不限制';

COMMENT ON COLUMN "blade_job_info"."statistic_window_len" IS '统计的窗口长度(s)，0代表不限制';

COMMENT ON COLUMN "blade_job_info"."silence_window_len" IS '沉默时间窗口(s)，0代表不限制';

COMMENT ON COLUMN "blade_job_info"."log_type" IS '日志配置';

COMMENT ON COLUMN "blade_job_info"."log_level" IS '日志级别';

COMMENT ON COLUMN "blade_job_info"."extra" IS '扩展字段（供开发者使用，用于功能扩展，powerjob 自身不会使用该字段）';

COMMENT ON COLUMN "blade_job_info"."create_user" IS '创建人';

COMMENT ON COLUMN "blade_job_info"."create_dept" IS '创建部门';

COMMENT ON COLUMN "blade_job_info"."create_time" IS '创建时间';

COMMENT ON COLUMN "blade_job_info"."update_user" IS '修改人';

COMMENT ON COLUMN "blade_job_info"."update_time" IS '修改时间';

COMMENT ON COLUMN "blade_job_info"."status" IS '状态';

COMMENT ON COLUMN "blade_job_info"."is_deleted" IS '是否已删除';

COMMENT ON TABLE "blade_job_info" IS '任务信息表';


-- -----------------------------------
-- 新增任务菜单信息
-- -----------------------------------
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "component", "remark", "is_deleted")
VALUES ('1164733399669962401', 0, 'job', '任务管理', 'job', '/job', 'iconfont iconicon_cspace', 5, 1, 0, 1, '', NULL, 0);

INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962501', '1164733399669962401', 'jobserver', '任务应用', 'menu', '/job/jobserver', 'iconfont icon-shouji', 1, 1, 0, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962502', '1164733399669962501', 'jobserver_add', '新增', 'add', '/job/jobserver/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962503', '1164733399669962501', 'jobserver_edit', '修改', 'edit', '/job/jobserver/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962504', '1164733399669962501', 'jobserver_delete', '删除', 'delete', '/api/blade-job/jobserver/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962505', '1164733399669962501', 'jobserver_view', '查看', 'view', '/job/jobserver/view', 'file-text', 4, 2, 2, 1, NULL, 0);


INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962601', '1164733399669962401', 'jobinfo', '任务配置', 'menu', '/job/jobinfo', 'iconfont icon-dongtai', 1, 1, 0, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962602', '1164733399669962601', 'jobinfo_add', '新增', 'add', '/job/jobinfo/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962603', '1164733399669962601', 'jobinfo_edit', '修改', 'edit', '/job/jobinfo/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962604', '1164733399669962601', 'jobinfo_delete', '删除', 'delete', '/api/blade-job/jobinfo/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "remark", "is_deleted")
VALUES ('1164733399669962605', '1164733399669962601', 'jobinfo_view', '查看', 'view', '/job/jobinfo/view', 'file-text', 4, 2, 2, 1, NULL, 0);

