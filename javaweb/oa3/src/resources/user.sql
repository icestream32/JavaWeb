# 用户表

drop table if exists t_user;
create table t_user(
    id int primary key auto_increment,
    username varchar(255) unique ,
    password varchar(255)
);

insert into t_user(id, username, password) VALUES (1,'admin','123456');
insert into t_user(id, username, password) VALUES (2,'zhangsan','123');