<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Apply for Leave</title>
</head>
<body>
	<h2>Employee Leave Application</h2>

	<%-- Error Block for invalid submissions --%>
	<% if (session.getAttribute("error") != null) { %>
	<p><%=session.getAttribute("error")%></p>
	
	<% session.removeAttribute("error"); 
	} %>

	<form action="applyLeave" method="post">
	
		<label>Employee Name: </label> 
		<input type="text" name="employeeName"><br>
		<br> 
		<label>Employee ID: </label> 
		 <input type="text" name="employeeId"><br>
		<br>
		<label> Department: </label> 
		 <input type="text" name="department"><br>
		<br> 
		<label>Leave Type: </label> 
		 <select name="leaveType">
			<option value="Sick Leave">Sick Leave</option>
			<option value="Casual Leave">Casual Leave</option>
			<option value="Emergency Leave">Emergency Leave</option>
			<option value="Work From Home">Work From Home</option>
		</select><br>
		<br> 
		<label>Number of Leave Days: </label> 
		 <input type="number" name="leaveDays"><br>
		<br> 
		<label>Reason: </label> 
		
		<textarea name="reason"></textarea>
		<br>
		<br> <input type="submit" value="Submit Application">
	</form>
	<br/>
	 <a href="index.html"> <button>Back to home </button></a>
</body>
</html>