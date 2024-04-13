create table if not exists sys_department
(
    id          bigserial not null,
    version     bigint,
    description varchar(255),
    name        varchar(255),
    parent_id   bigint,
    primary key (id)
);
create table if not exists sys_permission
(
    id          bigserial not null,
    version     bigint,
    code        varchar(255),
    description varchar(255),
    name        varchar(255),
    parent_id   bigint,
    primary key (id)
);
create table if not exists sys_role
(
    id          bigserial not null,
    version     bigint,
    code        varchar(255),
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table if not exists sys_role_permission
(
    sys_role_id       bigint not null,
    sys_permission_id bigint not null,
    primary key (sys_role_id, sys_permission_id)
);
create table if not exists sys_user
(
    id            bigserial not null,
    version       bigint,
    email         varchar(255),
    password      varchar(255),
    status        varchar(255) check (status in ('ACTIVE', 'LOCKED', 'DISABLED')),
    username      varchar(255),
    department_id bigint,
    primary key (id)
);
create table if not exists sys_user_role
(
    sys_user_id bigint not null,
    sys_role_id bigint not null,
    primary key (sys_user_id, sys_role_id)
);
create index if not exists idx_sys_department_name on sys_department (name);
alter table if exists sys_department drop constraint if exists uc_sys_department_name;
alter table if exists sys_permission add constraint uc_sys_permission_name_code unique (name, code);
alter table if exists sys_role  drop constraint if exists uc_sys_role_name;
alter table if exists sys_role  add constraint uc_sys_role_name unique (name);
alter table if exists sys_role  drop constraint if exists uc_sys_role_code;

alter table if exists sys_role add constraint uc_sys_role_code unique (code);
create index if not exists idx_sys_user_username on sys_user (username);
create index if not exists idx_sys_user_email on sys_user (email);

alter table if exists sys_role_permission  add constraint FKmnbc71b4040rgprkv4aeu0h5p
    foreign key (sys_permission_id) references sys_permission;

alter table if exists sys_role_permission add constraint FK31whauev046d3rg8ecubxa664
    foreign key (sys_role_id) references sys_role;

alter table if exists sys_user add constraint FKgpcudn9q6i2xhnbngujxwgqij
    foreign key (department_id) references sys_department;

alter table if exists sys_user_role add constraint FK1ef5794xnbirtsnudta6p32on
    foreign key (sys_role_id) references sys_role;

alter table if exists sys_user_role add constraint FKsbjvgfdwwy5rfbiag1bwh9x2b
    foreign key (sys_user_id) references sys_user;

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
