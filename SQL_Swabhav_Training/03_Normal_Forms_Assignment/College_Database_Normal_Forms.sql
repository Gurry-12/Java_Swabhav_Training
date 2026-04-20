CREATE DATABASE CollegeDB;
USE CollegeDB;

--  UNNORMALIZED TABLE (UNF)

CREATE TABLE Student_UNF (
    StudentID INT,
    StudentName VARCHAR(50),
    Course VARCHAR(50),
    Subject1 VARCHAR(50),
    Subject2 VARCHAR(50),
    Subject3 VARCHAR(50),
    FacultyName VARCHAR(50),
    FacultyPhone VARCHAR(15)
);

INSERT INTO Student_UNF VALUES
(101, 'Rahul', 'BTech', 'DBMS', 'OS', 'CN', 'Sharma', '9876543210'),
(102, 'Aman', 'BCA', 'DBMS', 'Java', NULL, 'Verma', '9123456780');

INSERT INTO Student_UNF VALUES
(103, 'Priya', 'BTech', 'DBMS', 'OS', 'CN', 'Sharma', '9876543210'),
(104, 'Sneha', 'BCA', 'Java', 'DBMS', NULL, 'Verma', '9123456780'),
(105, 'Rohit', 'BTech', 'CN', NULL, NULL, 'Sharma', '9876543210'),
(106, 'Karan', 'BBA', 'Accounts', 'Economics', 'Statistics', 'Gupta', '9988776655'),
(107, 'Neha', 'BBA', 'Economics', NULL, NULL, 'Gupta', '9988776655'),
(108, 'Amit', 'BTech', 'DBMS', 'OS', 'CN', 'Sharma', '9876543210'),
(109, 'Pooja', 'BCA', 'Java', 'DBMS', NULL, 'Verma', '9123456780');


SELECT * FROM Student_UNF; -- I WILL EXPLAIN THIS PART 

-- FIRST NORMAL FORM (1NF) --> SHIVANSH WILL EXPLAIN THIS

CREATE TABLE Student_1NF (
    StudentID INT,
    StudentName VARCHAR(50),
    Course VARCHAR(50),
    Subject VARCHAR(50),
    FacultyName VARCHAR(50),
    FacultyPhone VARCHAR(15)
);

INSERT INTO Student_1NF VALUES
(101, 'Rahul', 'BTech', 'DBMS', 'Sharma', '9876543210'),
(101, 'Rahul', 'BTech', 'OS', 'Sharma', '9876543210'),
(101, 'Rahul', 'BTech', 'CN', 'Sharma', '9876543210'),
(102, 'Aman', 'BCA', 'DBMS', 'Verma', '9123456780'),
(102, 'Aman', 'BCA', 'Java', 'Verma', '9123456780');

-- SECOND NORMAL FORM (2NF) --> GURPREET WILL EXPALIN THIS

-- Student Table
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,
    StudentName VARCHAR(50),
    Course VARCHAR(50)
);

INSERT INTO Student VALUES
(101, 'Rahul', 'BTech'),
(102, 'Aman', 'BCA');



-- Student-Subject Mapping

CREATE TABLE Student_Subject (
    StudentID INT,
    Subject VARCHAR(50),
    PRIMARY KEY (StudentID, Subject),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

INSERT INTO Student_Subject VALUES
(101, 'DBMS'),
(101, 'OS'),
(101, 'CN'),
(102, 'DBMS'),
(102, 'Java');

-- Faculty Table (Still partial issue)
CREATE TABLE Faculty_2NF (
    Subject VARCHAR(50),
    FacultyName VARCHAR(50),
    FacultyPhone VARCHAR(15)
);

INSERT INTO Faculty_2NF VALUES
('DBMS', 'Sharma', '9876543210'),
('OS', 'Sharma', '9876543210'),
('CN', 'Sharma', '9876543210'),
('Java', 'Verma', '9123456780');

-- THIRD NORMAL FORM (3NF) ---> AMITESH WILL EXPLAIN THIS

-- Subject Table
CREATE TABLE Subject (
    SubjectID INT PRIMARY KEY,
    SubjectName VARCHAR(50)
);

INSERT INTO Subject VALUES
(1, 'DBMS'),
(2, 'OS'),
(3, 'CN'),
(4, 'Java');

-- Faculty Table (Final)
CREATE TABLE Faculty (
    FacultyID INT PRIMARY KEY,
    FacultyName VARCHAR(50),
    Phone VARCHAR(15)
);

INSERT INTO Faculty VALUES
(1, 'Sharma', '9876543210'),
(2, 'Verma', '9123456780');

-- Student-Subject Mapping (Updated)
CREATE TABLE Student_Subject_3NF (
    StudentID INT,
    SubjectID INT,
    PRIMARY KEY (StudentID, SubjectID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (SubjectID) REFERENCES Subject(SubjectID)
);

INSERT INTO Student_Subject_3NF VALUES
(101, 1),
(101, 2),
(101, 3),
(102, 1),
(102, 4);

-- Subject-Faculty Mapping
CREATE TABLE Subject_Faculty (
    SubjectID INT,
    FacultyID INT,
    PRIMARY KEY (SubjectID, FacultyID),
    FOREIGN KEY (SubjectID) REFERENCES Subject(SubjectID),
    FOREIGN KEY (FacultyID) REFERENCES Faculty(FacultyID)
);

INSERT INTO Subject_Faculty VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2);