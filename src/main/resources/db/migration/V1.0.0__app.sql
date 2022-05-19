create sequence hibernate_sequence start 1 increment 1;

create table sys_user
(
    id       int8 not null,
    email    varchar(255),
    username varchar(255),
    primary key (id)
);
