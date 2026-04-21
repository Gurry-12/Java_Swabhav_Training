use SchoolDB;

-- 1. List all students from ‘Pune’.
delimiter //
create procedure GetAllStudentsFromPune() 
begin 
	select s.studentid, s.name, s.age, s.percentage, p.city, p.mobileno from student s
    join profile p
    on s.studentid = p.studentid
    where p.city like 'Pune';
end //

delimiter ;

call GetAllStudentsFromPune();

-- 2. Count how many students are in each city.
delimiter //
create procedure GetCountOfStudentsInEachCity() 
begin 
	select p.city , count(s.studentid) from profile p
    left join student s
    on p.studentid = s.studentid
    group by p.city
    order by p.city asc;
end // 

delimiter ;

-- 3. Find students with percentage > 80.
delimiter //
create procedure GetStudentsAbove80Percent() 
begin
	select name, rollnumber from student
    where percentage > 80.00;
end //
delimiter ;

-- 4. List students who are enrolled in more than one course.
delimiter //
create procedure GetStudentsEnrolledInMoreThanOneCourse() 
begin
	select s.studentid, s.name, COUNT(sc.courseid) as course_count
	from student s
	join student_course sc 
	on s.studentid = sc.studentid
	group by s.studentid, s.name
	having COUNT(sc.courseid) > 1;
end //

delimiter ;

call GetStudentsEnrolledInMoreThanOneCourse();

-- 5. Show each student's name and their subjects
delimiter //

create procedure GetStudentsWithSubjectName() 
begin 
	select s.name , sub.subname as subjectame from student s
    left join subjects sub
    on s.studentid = sub.studentid;
end //

delimiter ;

-- 6. Find students with no profile.
delimiter //

create procedure GetStudentsWithoutProfile() 
begin
	select s.studentid, s.name, s.rollnumber, s.age, s.percentage from student s 
	left join profile p
	on s.studentid = p.studentid
	where p.city is null or p.mobileno is null;
end //

delimiter ;

-- 7. List students along with their mobile number and city.
delimiter //
create procedure GetStudentWithProfile() 
begin
	select s.studentid, s.name, s.rollnumber, s.age, s.percentage, p.city, p.mobileno from student s 
	join profile p
	on s.studentid = p.studentid;
end //

delimiter ;

-- 8. List all subjects taken by students from Mumbai.
delimiter //
create procedure GetStudentsFromMumbaiTookSubject() 
begin
	select s.studentid, s.name, p.city , sub.subname from student s 
	join profile p
	on s.studentid = p.studentid
	join subjects sub
	on s.studentid = sub.studentid
	where p.city = 'Mumbai';
end //
delimiter ;

-- 9. Get average percentage of students per city.
delimiter //
create procedure GetStudentAveragePercentageCityWise()
begin
	select p.city , round(avg(percentage), 2) avarage_percent from profile p
	join student s
	on p.studentid = s.studentid
	group by p.city;
end //
delimiter ;

-- 10. Find students who are enrolled in ‘BSc Mathematics’ and live in Pune.
delimiter //
create procedure GetStudentsErolledInBScMathsLivePune()
begin
	select s.studentid, s.name, s.rollnumber, s.age, s.percentage, p.city, c.coursename from student s
	join profile p
	on s.studentid = p.studentid
	join student_course sc
	on s.studentid = sc.studentid
	join course c
	on sc.courseid = c.courseid
	where c.coursename = 'BSc Mathematics' and p.city = 'Pune';
end //

delimiter ;

-- 11. Get names of students who have taken both ‘Physics’ and ‘Mathematics’.
delimiter // 
create procedure GetStudentsWithSubjectsPhysicsAndMaths() 
begin
	select s.studentid, s.name from student s
	join subjects sub 
	on s.studentid = sub.studentid
	where sub.subname in('Physics' ,'Mathematics')
    group by s.studentid, s.name
    having count(distinct sub.subname ) = 2;
end //
delimiter ;

-- 12. Show students who are not enrolled in any course.
delimiter //
create procedure GetStudentNotEnrolledAnyCourse() 
begin
	select s.studentid, s.name from student s 
	left join student_course sc 
	on s.studentid = sc.studentid
	where sc.courseid is null;
end //
delimiter ;

-- 13. Display city-wise count of students enrolled in more than one subject.
delimiter //
create procedure GetCountOfStudentsCityWiseEnrolledMultipleSubjects() 
begin
	select p.city , count(s.studentid) student_count from profile p
	join (
		select studentid from subjects
		group by studentid
		having count(*) > 1
	) as s 
	on p.studentid = s.studentid 
	group by p.city;
end //

delimiter ;

-- 14. For each student, show their name, city, all course names (comma separated if possible), and subjects. Number them.
delimiter //

create procedure GetStudentFullReport() 
	select 
		row_number() over(ORDER BY s.name) AS 'Sr. No',
		s.name Student_name, 
		p.city,
		ifnull(group_concat(distinct c.coursename separator ','), 'No Courses') All_courses,
	ifnull(group_concat(distinct c.coursename separator ','), 'No Courses') All_subjects
	from student s
	join profile p on s.studentid = p.studentid
	join subjects sub on s.studentid = sub.studentid
	join student_course sc on s.studentid = sc.studentid
	join course c on sc.courseid = c.courseid
	group by s.studentid, s.name, p.city;
end //

delimiter ;

-- 15. Find the top 3 students with the highest percentage in each city.


-- 16. List students who have taken exactly 3 subjects.
delimiter //
create procedure GetStudentsWhoTakenExactly3Subjects() 
begin
	select s.studentid, s.name, s.rollnumber, s.age, s.percentage from student s
	join (
		select studentid from subjects
		group by studentid
		having count(*) = 3
		) sub
	on s.studentid = sub.studentid;
end //

delimiter ;

-- 17. Show courses that no student has enrolled in.
delimiter //

create procedure GetCoursesWithNoStudents()
begin
    select c.courseid, c.coursename
    from course c
    left join student_course sc on c.courseid = sc.courseid
    where sc.courseid is null;
end //

delimiter ;

-- 18. List students who share the same percentage.
delimiter //
create procedure GetStudentsWhoShareSameScore() 
begin     
	select percentage,
		count(*) total_student,
		group_concat( name order by name asc separator ', ') students_list
	from student
	group by percentage
	having count(*) > 1;
end //
delimiter ;

-- 19. Display the number of courses and subjects each student is enrolled in.
delimiter //

create procedure GetStudentTotalSubjectsAndCoursesCount()
begin
    select 
        s.name,
        (
			select COUNT(*) from student_course sc 
            where sc.studentid = s.studentid
		) as Course_Count,
        (
			select COUNT(*) from subjects sub 
            where sub.studentid = s.studentid
		) as Subject_Count
    from student s;
end //

delimiter ;


-- delimiter //

-- create procedure GetTop3StudentsPerCity()
-- begin
--     select p.city, n.name, n.percentage
--     from (
--         select
-- 			studentid, name, percentage
-- 		from student 
--         order by percentage desc
--     ) as n
--     join profile p
--     on n.studentid = p.studentid
--     group by p.city;
--     
-- END //

-- DELIMITER ;