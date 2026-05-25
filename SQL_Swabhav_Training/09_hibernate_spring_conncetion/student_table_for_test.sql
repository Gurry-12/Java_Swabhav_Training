create database if not exists student_db;

use student_db;

create table student(
id int primary key,
name varchar(100)
);

select * from student;
