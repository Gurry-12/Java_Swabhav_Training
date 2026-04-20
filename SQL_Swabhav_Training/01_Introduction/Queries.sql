--  show details of all employee
select * from emp;

-- #1. Display all employee names in ascending order
select * from emp 
order by ENAME asc;

-- #2. Display all employees(all columns) in department 20 and 30
select * from emp 
where DEPTNO in (20, 30);

-- #3. Display all the employees who are managers
SELECT * 
FROM emp 
WHERE empno IN (SELECT DISTINCT MGR FROM emp);

select * from emp where JOB = 'MANAGER';

-- #4. Display all the employees whose salary is between 2000 and 5000
select * from emp 
where SAL between 2000 and 5000;

-- #5. Display all the employees whose commission is null
select * from emp 
where COMM is null;

-- #6. Display emp_name,salary,comission,ctc(calculated column)
select ENAME emp_name, SAL salary, COMM comission, (SAL + ifnull(COMM, 0)) ctc from emp;

select ENAME emp_name, SAL salary, COMM comission, (SAL + coalesce(COMM, 0)) ctc from emp;

-- #7. Display hire_date, current_date, tenure(calculated col) of the empl
select hiredate hire_date, current_date as today, datediff(current_date, hiredate) tenure from emp;

-- #8. Display all the employees whose name starts with s
select * from emp 
where ENAME like 's%';

-- #9. Display unique department numbers from the employee table
select distinct DEPTNO department_number from emp
order by DEPTNO;

--  #10. Display emp_name and job in lower case
select lower(ENAME) emp_name, lower(JOB) job from emp;

-- 11. Select top 3 salary earning employee
select * from emp
 order by SAL desc
 limit 3;

-- #12. Select clerks and Managers in department 10
select * from emp 
where (JOB = 'CLERK' or JOB = 'MANAGER') and DEPTNO = 10; 

-- #13. Display all clerks in asscending order of the department number 
select * from emp 
where JOB = 'CLERK'
order by DEPTNO desc;

-- #16. Display All employees in the same dept of 'SCOTT' 
select * 
from emp 
where DEPTNO = (
    select DEPTNO 
    from emp 
    where ENAME = 'SCOTT'
) ;

-- #17. Employees having same designation of SMITH
select * from emp 
where JOB = (select JOB from emp where ENAME = 'SMITH') ;

-- #18. Employee who are reproting under KING
select * from emp 
where MGR = ( select EMPNO from emp where ENAME = 'KING');

-- #19. Employees who have same salary of BLAKE
select * from emp 
where SAL = ( select SAL from emp where ENAME = 'BLAKE');

-- #20. Display departmentwise number of employees
select d.DNAME , count(e.EMPNO) from dept d
join emp e
on d.DEPTNO = e.DEPTNO
group by d.DEPTNO, d.DNAME;

select DEPTNO , count(EMPNO) from emp 
group by DEPTNO;

-- #21. Display jobwise number of employees
select OB , count(MPNO) from emp 
group by JOB;

-- #22. Display deptwise jobwise number of employees
select DEPTNO, JOB, count(EMPNO) from emp
group by DEPTNO, JOB
order by DEPTNO;

-- #23. Display deptwise  employees greater than  3 
select DEPTNO , count(EMPNO) from emp 
group by DEPTNO
having count(EMPNO) > 3;

-- #24. Display designation wise employees count greater than 3 
select JOB , count(EMPNO) from emp
group by JOB
having count(EMPNO) > 3;

-- #25. Display Employee name,deptname and location
select e.ENAME , d.DNAME, d.LOC from emp e
join dept d
on e.DEPTNO = d.DEPTNO;

-- 26. display all deptnames and corresponding employees if ANY
select d.DNAME , e.ENAME from dept d
left join emp e
on d.DEPTNO = e.DEPTNO;

-- #27. dipslay all deptnames where there are no employees
select d.DNAME , e.ENAME from dept d
left join emp e
on d.DEPTNO = e.DEPTNO
where e.ENAME is null;

-- #28. display deptname wise employee count greater than 3, display in descending order of deptname
select d.DNAME , count(e.EMPNO) from dept d
join emp e
on d.DEPTNO = e.DEPTNO
group by d.DNAME
having count(EMPNO) > 3
order by d.DNAME desc;

-- #29. Display all the empname and their manager names
SELECT  
	e.ENAME as Employee,
    m.ENAME as Manager
FROM emp e
left join emp m
on e.MGR = m.EMPNO;

-- 30. Display empname,deptname and manager name as bossname , order by bossname
SELECT 
    e.ENAME AS employee_name,
    d.DNAME AS department_name,
    m.ENAME AS bossname
FROM emp e
JOIN dept d ON e.DEPTNO = d.DEPTNO
LEFT JOIN emp m ON e.MGR = m.EMPNO
ORDER BY bossname, e.ENAME;

-- #31. Display Dname, employee name and names of their managers
SELECT 
    d.DNAME AS department_name,
    e.ENAME AS employee_name,
    m.ENAME AS manager_name
FROM dept d
LEFT JOIN emp e ON d.DEPTNO = e.DEPTNO
LEFT JOIN emp m ON e.MGR = m.EMPNO;