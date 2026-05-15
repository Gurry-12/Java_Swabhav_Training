create database EmployeeLeaveManagement;

use EmployeeLeaveManagement;

CREATE TABLE leave_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL, --
    employee_id VARCHAR(50) NOT NULL,   --
    department VARCHAR(100) NOT NULL,    --
    leave_type VARCHAR(50) NOT NULL,     --
    leave_days INT NOT NULL,             -- 
    reason TEXT NOT NULL,                --
    approval_status VARCHAR(100),        -- To store the business logic message
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from leave_applications;