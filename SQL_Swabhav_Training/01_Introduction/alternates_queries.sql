use organization;

-- #8. Display all the employees whose name starts with s
select * from emp 
where ENAME like 's%';

-- #9. Display unique department numbers from the employee table
select distinct DEPTNO department_number from emp
order by DEPTNO;

--  #10. Display emp_name and job in lower case
select lower(ENAME) emp_name, lower(JOB) job from emp;

select deptno , count(deptno) from emp 
where comm is null
group by deptno;

select dname, count(e.deptno) as nullcount from dept d 
join emp e 
on d.deptno = e.deptno 
where e.comm is null group by dname;

select ENAME, JOB, HIREDATE, TIMESTAMPDIFF(YEAR, HireDate, CURDATE()) as TENURE from emp;