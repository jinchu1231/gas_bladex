INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801430054055124994', 1123598815738675201, 'listedPrice', '加气站挂牌价格', 'menu', '/listedPrice/listedPrice', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801430054055124995', '1801430054055124994', 'listedPrice_add', '新增', 'add', '/listedPrice/listedPrice/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801430054055124996', '1801430054055124994', 'listedPrice_edit', '修改', 'edit', '/listedPrice/listedPrice/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801430054055124997', '1801430054055124994', 'listedPrice_delete', '删除', 'delete', '/api/blade-listedPrice/listedPrice/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801430054055124998', '1801430054055124994', 'listedPrice_view', '查看', 'view', '/listedPrice/listedPrice/view', 'file-text', 4, 2, 2, 1, NULL, 0);
