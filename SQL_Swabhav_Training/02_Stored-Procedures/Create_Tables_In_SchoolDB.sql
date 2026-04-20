use SchoolDB;

CREATE TABLE Profile (
studentid INT PRIMARY KEY,
city VARCHAR(50),
mobileno VARCHAR(15),
FOREIGN KEY (studentid) REFERENCES Student(studentid)
);

CREATE TABLE Subjects (
subid INT PRIMARY KEY,
studentid INT,
subname VARCHAR(50),
FOREIGN KEY (studentid) REFERENCES Student(studentid)
);

CREATE TABLE Course (
courseid INT PRIMARY KEY,
coursename VARCHAR(100)
);

CREATE TABLE Student_Course (
studentid INT,
courseid INT,
PRIMARY KEY (studentid, courseid),
FOREIGN KEY (studentid) REFERENCES Student(studentid),
FOREIGN KEY (courseid) REFERENCES Course(courseid)
);

INSERT INTO Profile VALUES
(1, 'Pune', '9876543210'),
(2, 'Mumbai', '9823456780'),
(3, 'Nagpur', '9988776655'),
(4, 'Delhi', '9911223344'),
(5, 'Pune', '9876501234');

INSERT INTO Subjects VALUES
(1, 1, 'Mathematics'),
(2, 1, 'Physics'),
(3, 2, 'Biology'),
(4, 3, 'Chemistry'),
(5, 3, 'Mathematics'),
(6, 4, 'History'),
(7, 5, 'Economics');

INSERT INTO Course VALUES
(1, 'BSc Computer Science'),
(2, 'BSc Mathematics'),
(3, 'BA History'),
(4, 'BCom Finance');

INSERT INTO Course VALUES
(5, 'B.Com Honours');

INSERT INTO Student_Course VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 3),
(4, 3),
(5, 4),
(2, 4);