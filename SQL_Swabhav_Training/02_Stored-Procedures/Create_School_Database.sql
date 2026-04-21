-- 1. Create Database
CREATE DATABASE IF NOT EXISTS SchoolDB;
USE SchoolDB;

-- 2. Create Table
CREATE TABLE IF NOT EXISTS Student (
    studentid     INT PRIMARY KEY,
    rollnumber    INT UNIQUE NOT NULL,
    name          VARCHAR(100) NOT NULL,
    age           INT,
    percentage    DECIMAL(5,2)
);

-- 3. Insert Data
INSERT INTO Student (studentid, rollnumber, name, age, percentage) 
VALUES
(1, 101, 'Anjali Sharma', 18, 92.50),
(2, 102, 'Ravi Verma', 19, 85.00),
(3, 103, 'Priya Deshmukh', 20, 74.00),
(4, 104, 'Amit Patel', 17, 38.00),
(5, 105, 'Sneha Joshi', 21, 64.50),
(6, 106, 'Arjun Mehta', 22, 49.00),
(7, 107, 'Akshay Kumar', 18, 55.50),
(8, 108, 'Anaya Nair', 19, 88.00),
(9, 109, 'Bhavna Iyer', 20, 91.00),
(10, 110, 'Chetan Salunkhe', 18, 33.00),
(11, 111, 'Deepak Rawat', 20, 85.00),
(12, 112, 'Asha Pawar', 19, 67.00),
(13, 113, 'Ganesh Jadhav', 22, 75.00),
(14, 114, 'Aditya Jain', 21, 92.50),
(15, 115, 'Raj Thakur', 17, 40.00);

-- Verify the data
SELECT * FROM Student;