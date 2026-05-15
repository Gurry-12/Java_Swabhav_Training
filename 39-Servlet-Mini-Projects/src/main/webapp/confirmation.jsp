<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Confirmation</title>
</head>
<body>
    <h2>Registration Successful!</h2>
    
    <p><strong>Student Name:</strong> ${studentName}</p>
    <p><strong>Email:</strong> ${email}</p>
    <p><strong>Age:</strong> ${age}</p>
    <p><strong>Course:</strong> ${courseName}</p>
    <p><strong>Preferred Batch Time:</strong> ${batchTime}</p>
    
    <br>
    <a href="register.jsp"> <button>Back to Form </button></a>
</body>
</html>