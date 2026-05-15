<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Review Application</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Leave Application Review</h2>
        <div class="review-item"><strong>Employee:</strong> ${employeeName} (${employeeId})</div>
        <div class="review-item"><strong>Department:</strong> ${department}</div>
        <div class="review-item"><strong>Leave Type:</strong> ${leaveType}</div>
        <div class="review-item"><strong>Days:</strong> ${leaveDays}</div>
        <div class="review-item"><strong>Reason:</strong> ${reason}</div>

        <div class="status-box">
            <strong>Approval Status:</strong> ${approvalMessage}
        </div>

        <br>
        <a href="leaveForm.jsp">Edit Details</a>
    </div>
</body>
</html>