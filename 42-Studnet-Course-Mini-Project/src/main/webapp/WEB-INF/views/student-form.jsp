<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session == null || session.getAttribute("loggedInUser") == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >
	<div >
		<h2>Add New Student</h2>

		<%
		if (request.getAttribute("error") != null) {
		%>
		<div ><%=request.getAttribute("error")%></div>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/student/add"
			method="post">
			<div >
				<label>Student Name</label> <input type="text" name="studentName"
					 required>
			</div>
			<div >
				<label>Email</label> <input type="email" name="email"
					 required>
			</div>
			<div >
				<label>Phone</label> <input type="text" name="phone"
					 required>
			</div>
			<div >
				<label>Age</label> <input type="number" name="age"
					 min="18" required>
			</div>
			<div >
				<label>City</label> <input type="text" name="city"
					 required>
			</div>
			<button type="submit" >Add Student</button>
			<a href="${pageContext.request.contextPath}/students"
				>Cancel</a>
		</form>
	</div>
</body>
