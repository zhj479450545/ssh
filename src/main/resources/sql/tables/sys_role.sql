DROP TABLE IF EXISTS sys_role;

CREATE TABLE `sys_role` (
        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
        `parent_id` int(11) DEFAULT NULL COMMENT '父级角色id',
        `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
        PRIMARY KEY (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;