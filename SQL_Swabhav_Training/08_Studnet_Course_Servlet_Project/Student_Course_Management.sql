create database student_course_db;
use student_course_db;

-- admin table
create table admin (
    admin_id int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(100) not null
);

-- students table
create table students (
    student_id int primary key auto_increment,
    student_name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(15) not null,
    age int not null,
    city varchar(50) not null
);

-- courses table
create table courses (
    course_id int primary key auto_increment,
    course_name varchar(100) not null,
    duration varchar(50) not null,
    fees double not null,
    trainer_name varchar(100) not null
);

-- registration table
create table registrations (
    registration_id int primary key auto_increment,
    student_id int,
    course_id int,
    registration_date date not null,
    status varchar(20) not null,
    foreign key (student_id) references students(student_id) on delete cascade,
    foreign key (course_id) references courses(course_id) on delete cascade
);

alter table registrations 
add constraint unique key (student_id, course_id);

insert into admin(username, password) 
values('admin', 'admin123');

alter table students 
add check (age > 18);

alter table courses
add check (fees > 0.0);

SELECT CONSTRAINT_NAME, CHECK_CLAUSE 
FROM information_schema.CHECK_CONSTRAINTS 
WHERE CONSTRAINT_NAME = 'students_chk_1';

ALTER TABLE students DROP CONSTRAINT students_chk_1;

select * from courses;

select * from registrations;