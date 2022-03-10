# 部门表
drop table if exists dept;
create table dept(
                     deptno int primary key,
                     dname varchar(255),
                     loc varchar(255)
);
insert into dept(deptno, dname, loc) values(10, '销售部', '北京');
insert into dept(deptno, dname, loc) values(20, '研发部', '上海');
insert into dept(deptno, dname, loc) values(30, '技术部', '天津');
insert into dept(deptno, dname, loc) values(40, '媒体部', '深圳');
commit;
select * from dept;