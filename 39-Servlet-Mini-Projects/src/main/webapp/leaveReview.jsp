<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Leave Review</title>
</head>
<body>
	<h2>Leave Application Review</h2>
	<hr>
	<p>
		<strong>Employee Name:</strong> ${employeeName}
	</p>
	<p>
		<strong>Employee ID:</strong> ${employeeId}
	</p>
	<p>
		<strong>Department:</strong> ${department}
	</p>
	<p>
		<strong>Leave Type:</strong> ${leaveType}
	</p>
	<p>
		<strong>Days Requested:</strong> ${leaveDays}
	</p>
	<p>
		<strong>Reason:</strong> ${reason}
	</p>

	<div>
		<strong>Status:</strong> ${approvalMessage}
	</div>

	<br>
	<a href="leaveForm.jsp"> <button>Back to Form </button></a>
</body>
</html>