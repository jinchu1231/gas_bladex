INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801527159218221057', 1123598815738675201, 'listedPrice', '液厂挂牌价格', 'menu', '/listedPrice/listedPrice', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801527159218221058', '1801527159218221057', 'listedPrice_add', '新增', 'add', '/listedPrice/listedPrice/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801527159218221059', '1801527159218221057', 'listedPrice_edit', '修改', 'edit', '/listedPrice/listedPrice/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801527159218221060', '1801527159218221057', 'listedPrice_delete', '删除', 'delete', '/api/blade-listedPrice/listedPrice/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1801527159218221061', '1801527159218221057', 'listedPrice_view', '查看', 'view', '/listedPrice/listedPrice/view', 'file-text', 4, 2, 2, 1, NULL, 0);
