<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Result Preview</title></head>
<body>
    <h2>Exam Result Preview</h2>
    <table border="1">
        <tr><td>Student Name:</td><td>${studentName}</td></tr>
        <tr><td>Roll Number:</td><td>${rollNumber}</td></tr>
        <tr><td>Total Marks:</td><td>${total} / 300</td></tr>
        <tr><td>Percentage:</td><td>${percentage}%</td></tr>
        <tr><td><strong>Result Status:</strong></td><td><strong>${status}</strong></td></tr>
    </table>
    <br>
    <a href="examForm.jsp"><button>Check another result </button></a>
</body>
</html>