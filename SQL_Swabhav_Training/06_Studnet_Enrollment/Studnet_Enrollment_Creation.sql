create database EnrollmentSystemDB;

use EnrollmentSystemDB;

create table student (
id INT PRIMARY KEY, 
name VARCHAR(50), 
age INT,
branch VARCHAR(50) 
);

create table registration (
reg_id INT PRIMARY KEY AUTO_INCREMENT, 
student_id INT,
course_name VARCHAR(50), 
fees_paid DOUBLE,
FOREIGN KEY (student_id) REFERENCES student(id)
);

select * from student;

select s.id, s.name, s.age , s.branch , count(r.course_name) course_count, sum(fees_paid) total_fees
from student s
join registration r
on s.id = r.student_id
group by s.id, s.name, s.age, s.branch
having sum(fees_paid) > 10000;

create table department(
	id int primary key auto_increment,
    department_name varchar(100)
    );

create table course(
	id int primary key auto_increment,
    course_name varchar(100)
    );

alter table student 
drop branch;

alter table registration drop course_name;

alter table student 
add department_id int,
add foreign key (department_id) references department(id);

alter table registration 
add course_id int,
add foreign key (course_id) references course(id);

INSERT INTO department (department_name) VALUES
('CSE'),
('IT'),
('ECE'),
('ME');

INSERT INTO course (course_name) VALUES
('JAVA'),
('DBMS'),
('PYTHON'),
('OS'),
('CN');

TRUNCATE TABLE registration;
set foreign_key_checks = 0;
TRUNCATE TABLE student;
set foreign_key_checks = 1;
TRUNCATE TABLE course;
TRUNCATE TABLE department;

select * from student;
select * from registration;
select * from department;
select * from course;

select * from registration r
join course c
on r.course_id = c.id
