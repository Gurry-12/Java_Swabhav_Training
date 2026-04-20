use schoolDB;

-- SET FOREIGN_KEY_CHECKS = 0;
-- alter table student 
-- modify studentid int auto_increment;
-- SET FOREIGN_KEY_CHECKS = 1;

-- 1. Write a stored procedure to insert a new student using parameters: roll number, name, age, and percentage.
delimiter //
create procedure InsertNewStudent(
	in s_rollnumber integer,
    in s_name varchar(100),
    in s_age integer,
    in s_percentage decimal(5,2)
    )
begin 
	insert into student(rollnumber, name, age, percentage)
    values(s_rollnumber, s_name, s_age, s_percentage);
end //
delimiter ;

call InsertNewStudent(117, 'Rohan Mishra', 20, 78.2);

select * from student;

-- 2. Write a procedure that takes a student’s roll number as input and returns the full student record.
delimiter //
create procedure GetStudentWithRollNumber(
	in s_rollnumber integer
    )
begin 
	select * from student 
    where rollnumber = s_rollnumber;
end //
delimiter ;

call GetStudentWithRollNumber(112);

-- 3. Create a procedure that updates the percentage of a student using student ID and new percentage as input.
delimiter //
create procedure UpdatePercentageOfStudentUsingStudentId(
	in s_id int,
    in s_percentage decimal(5,2)
    )
begin 
	update  student
    set percentage = s_percentage
    where studentid = s_id;
end //
delimiter ;

-- 4. Write a procedure to return all subjects opted by a student whose student ID is given.
delimiter //
create procedure GetStudentSubjects(in s_id int)
begin
    select subname from subjects where studentid = s_id;
end //
delimiter ;

-- 5. Write a procedure that accepts student ID and returns (via OUT parameters) 
-- the name and percentage of that student.
delimiter //
create procedure GetStudentBasicInfo(
    in s_id int, 
    out s_name varchar(100), 
    out s_perc decimal(5,2)
)
begin
    select name, percentage into s_name, s_perc 
    from student where studentid = s_id;
end //
delimiter ;

-- 6. Create a procedure that returns the name, city, and
-- mobile number of all students by joining students and profile tables.
delimiter //
create procedure GetAllStudentContactDetails()
begin
    select s.name, p.city, p.mobilenumber
    from student s
    join profile p on s.studentid = p.studentid;
end //
delimiter ;

-- 7. Write a procedure that returns all students who live
--  in a specific city (input parameter).
delimiter //
create procedure GetStudentsByCity(in p_city varchar(100))
begin
    select s.* from student s
    join profile p on s.studentid = p.studentid
    where p.city = p_city;
end //
delimiter ;

-- 8. Write a procedure that takes student ID as input and
-- returns the total number of courses enrolled using an OUT parameter.
delimiter //
create procedure GetCourseEnrollmentCount(in s_id int, out course_count int)
begin
    select count(*) into course_count from student_course where studentid = s_id;
end //
delimiter ;

-- 9. Write a procedure that returns the average percentage of students grouped by city.
delimiter //
create procedure GetAveragePercentageByCity()
begin
    select p.city, avg(s.percentage) as avg_percentage
    from student s
    join profile p on s.studentid = p.studentid
    group by p.city;
end //
delimiter ;
-- 10. Write a procedure to return the student IDs of students who are enrolled 
-- in more than one course.
delimiter //
create procedure GetMultiCourseStudentIDs()
begin
    select studentid from student_course 
    group by studentid having count(courseid) > 1;
end //
delimiter ;

-- 11. Create a procedure that accepts student ID and
-- age as input. Use the age as an INOUT parameter: 
-- update the student’s age, then return the updated value back.
delimiter //
create procedure UpdateAndReturnAge(inout s_age int, in s_id int)
begin
    update student set age = s_age where studentid = s_id;
    -- The variable s_age retains the value to be read by the caller
end //
delimiter ;

-- 12. Write a procedure that uses INOUT parameter to 
-- insert a new subject for a student only if it doesn’t 
-- already exist. If the subject exists, return a message
--  like "Already exists" via the same parameter.
delimiter //
create procedure CheckAndInsertSubject(inout sub_name varchar(100), in s_id int)
begin
    if exists (select 1 from subjects where studentid = s_id and subname = sub_name) then
        set sub_name = 'Already exists';
    else
        insert into subjects (studentid, subname) values (s_id, sub_name);
    end if;
end //
delimiter ;

-- 13. Create a procedure that accepts student and profile 
-- details as input and inserts them into the students and
--  profile tables. Ensure the student ID from the first insert 
-- is reused for the profile record.
delimiter //
create procedure RegisterStudentFull(
    in r_num int, in s_name varchar(100), in s_age int, 
    in s_perc decimal(5,2), in p_city varchar(100), in p_mob varchar(15)
)
begin
    insert into student (rollnumber, name, age, percentage) 
    values (r_num, s_name, s_age, s_perc);
    
    -- LAST_INSERT_ID() captures the ID of the student we just created
    insert into profile (studentid, city, mobileno) 
    values (LAST_INSERT_ID(), p_city, p_mob);
end //
delimiter ;

-- 14. Design an audit table percentage_audit(student_id, old_percentage, 
-- new_percentage, updated_at) and create a procedure that updates a 
-- student’s percentage and logs the old and new value into the audit table.
-- Step 1: Create the Audit Table
create table if not exists percentage_audit (
    audit_id int auto_increment primary key,
    student_id int,
    old_percentage decimal(5,2),
    new_percentage decimal(5,2),
    updated_at timestamp default current_timestamp
);

-- Step 2: Create the Procedure
delimiter //
create procedure UpdatePercentageWithAudit(in s_id int, in n_perc decimal(5,2))
begin
    declare o_perc decimal(5,2);
    select percentage into o_perc from student where studentid = s_id;
    
    update student set percentage = n_perc where studentid = s_id;
    
    insert into percentage_audit (student_id, old_percentage, new_percentage) 
    values (s_id, o_perc, n_perc);
end //
delimiter ;

-- 15. Write a procedure that deletes a student’s record from all related tables:
--  student_course, subjects, profile, and finally students table.
delimiter //
create procedure DeleteStudentComplete(in s_id int)
begin
    -- Delete from child tables first to respect foreign keys
    delete from student_course where studentid = s_id;
    delete from subjects where studentid = s_id;
    delete from profile where studentid = s_id;
    -- Finally, delete from the parent table
    delete from student where studentid = s_id;
end //
delimiter ;

