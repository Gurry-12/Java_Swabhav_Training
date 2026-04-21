use SchoolDB;

-- 🔹 1. Display all columns for all students in the table.
delimiter //
create procedure GetAllStudents()
begin
    select * from student;
end//

-- 🔹 13. Create a view that contains details of all students who failed (percentage less than 40).
delimiter //
create procedure CreateFailedStudentsView()
begin
    create or replace view failed_students as
    select * from student where percentage < 40;
    -- Note: To failed_studentssee the results, you'd select from the view itself afterward
    select * from failed_students;
end//
delimiter ;

call CreateFailedStudentsView();

-- 🔹 14. Display the rank of each student based on their percentage using a window function.
delimiter //
create procedure GetStudentRanks()
begin
    select name, percentage, 
           rank() over(order by percentage desc) as StudentRank
    from student;
end//
delimiter ;

call GetStudentRanks();

-- 🔹 15. Find the top 3 students with the highest percentage using a subquery and window function.
delimiter //
create procedure GetTopThreeStudents()
begin
    select * from (
        select name, percentage, 
               dense_rank() over(order by percentage desc) as rnk
        from student
    ) as ranked_table
    where rnk <= 3;
end//
delimiter ;

call GetTopThreeStudents();