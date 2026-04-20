-- Get Full Name Label
-- Write a function get_student_label that takes a student ID and returns a string in the format:
-- 'Roll: [rollnumber] - Name: [name]' from the students table.
delimiter //
create function get_student_label(s_id int) 
returns varchar(255)
deterministic
begin
    declare label varchar(255);
    select concat('Roll: ', rollnumber, ' - Name: ', name) into label 
    from student where studentid = s_id;
    return label;
end //
delimiter ;

select get_student_label(1) as student_label;

-- 2. Calculate Percentage Grade
-- Write a function get_grade that takes a percentage as input and returns:
-- 'A' if percentage ≥ 90
-- 'B' if 75–89
-- 'C' if 50–74
-- 'D' otherwise

delimiter //
create function get_grade(
	s_percentage int
    )
returns char(1)
deterministic
begin
	if s_percentage >= 90 then return 'A';
    elseif s_percentage >= 75 then return 'B';
    elseif s_percentage >= 50 then return 'C';
    else return 'D';
    end if;
end //
delimiter ;

select get_grade(91);

-- 3. Get Age Category
-- Write a function get_age_category that takes a student's age and returns 'Teen', 'Adult', or 'Senior' based on:
-- Teen: age < 20
-- Adult: age 20–40
-- Senior: age > 40
delimiter //
create function get_age_category( s_age int)
returns varchar(100)
deterministic 
begin 
	if s_age > 40 then return 'Senior';
    elseif s_age >= 20 then return 'Adult';
    else return 'Teen';
    end if;
end //
delimiter ;

select get_age_category(41);

-- 4. Check Pass or Fail
-- Write a function is_passed that takes a percentage and returns 'Pass' if ≥ 40, otherwise 'Fail'.
delimiter //
create function is_passed(s_percentage decimal(5,2))
returns varchar(100)
deterministic 
begin 
	if s_percentage >= 40 then return 'Pass';
    else return 'Fail';
    end if;
end //
delimiter ;

select is_passed(44.0);

-- 5. Get Subject Count for a Student
-- Write a function subject_count that takes a student ID 
-- and returns how many subjects the student is enrolled in (from the subjects table).
delimiter //
create function subject_count(s_id int) 
returns int
deterministic
begin
    declare total int;
    select count(*) into total from subjects where studentid = s_id;
    return total;
end //
delimiter ;

select subject_count(1);
-- 6. Get Course Count
-- Write a function course_count that takes a student ID and 
-- returns the number of courses the student is enrolled in (from the student_course table).
delimiter //
create function course_count(s_id int) 
returns int
deterministic
begin
    declare total int;
    select count(*) into total from student_course where studentid = s_id;
    return total;
end //
delimiter ;

select course_count(1);

-- 7. Get Mobile Number
-- Write a function get_mobile_by_student that takes a student ID and 
-- returns their mobile number from the profile table.
delimiter //
create function get_mobile_by_student(s_id int)
returns varchar(15)
deterministic 
begin 
	declare s_mobile varchar(15);
    select mobileno into s_mobile from profile 
    where studentid = s_id;
    return s_mobile;
end //
delimiter ;

select get_mobile_by_student(2);

-- 8. Average Percentage by City
-- Write a function average_percentage_by_city that takes a city name and 
-- returns the average percentage of all students living in that city (using join with profile).
delimiter //
create function average_percentage_by_city(p_city varchar(100)) 
returns decimal(5,2)
deterministic
begin
    declare avg_perc decimal(5,2);
    select avg(s.percentage) into avg_perc 
    from student s
    join profile p on s.studentid = p.studentid
    where p.city = p_city;
    return avg_perc;
end //
delimiter ;

select average_percentage_by_city('Delhi');
select * from profile;

-- 9. Get Highest Percentage Among All Students Write a 
-- function get_top_percentage that 
-- returns the highest percentage score from the students table.
delimiter //
create function get_top_percentage() 
returns decimal(5,2)
deterministic
begin
    declare max_perc decimal(5,2);
    select max(percentage) into max_perc from student;
    return max_perc;
end //
delimiter ;

select get_top_percentage();

-- 10. Get Student Status
-- Write a function get_student_status that takes a student ID and returns:
-- 'Excellent' if percentage ≥ 90
-- 'Good' if between 75–89
-- 'Average' if between 40–74
-- 'Poor' if below 40
-- -- (Use a SELECT with conditional logic)
delimiter //
create function get_student_status(s_id int)
returns varchar(100)
deterministic 
begin 
	declare s_percentage decimal(5,2);
	select percentage into s_percentage from student 
    where studentid = s_id;
    if s_percentage >= 90 then return 'Excellent';
    elseif s_percentage >= 75 then return 'Good';
    elseif s_percentage >= 40 then return 'Average';
    else return 'Poor';
    end if;
end //
delimiter ; 

select get_student_status(1);