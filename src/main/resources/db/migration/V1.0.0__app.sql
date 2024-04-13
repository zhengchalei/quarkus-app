INSERT INTO sys_department (parent_id, description, name, version)
VALUES (null, '官网: https://zhengchalei.github.io', '总部', 1);
INSERT INTO sys_department (parent_id, description, name, version)
VALUES (1, null, '上海分公司', 1);
INSERT INTO sys_department (parent_id, description, name, version)
VALUES (2, null, '上海分公司开发部', 1);

INSERT INTO sys_user(email, status, password, username, department_id, version)
VALUES ('stone981023@gmail', 'ACTIVE', '123456', 'admin', 1, 0);

INSERT INTO sys_role(code, description, name, version)
VALUES ('admin', '超级管理员', '超级管理员', 1);

insert into sys_permission (parent_id, code, description, name, version)
values (0, 'sys', '系统管理资源', '系统管理', 1);

insert into sys_role_permission (sys_role_id, sys_permission_id)
values (1, 1);

INSERT INTO sys_user_role(sys_user_id, sys_role_id)
VALUES (1, 1);
