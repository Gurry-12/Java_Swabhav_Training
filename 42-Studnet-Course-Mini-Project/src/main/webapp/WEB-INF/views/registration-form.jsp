<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>New Registration</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >
	<div >
		<h2>New Student Course Registration</h2>

		<%
		if (request.getAttribute("error") != null) {
		%>
		<div ><%=request.getAttribute("error")%></div>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/registration/add"
			method="post">
			<div >
				<label>Student</label> <select name="studentId" 
					required>
					<option value="">-- Select Student --</option>
					<c:forEach var="student" items="${students}">
						<option value="${student.studentId}">
							${student.studentName} (${student.email})</option>
					</c:forEach>
				</select>
			</div>
			<div >
				<label>Course</label> <select name="courseId" 
					required>
					<option value="">-- Select Course --</option>
					<c:forEach var="course" items="${courses}">
						<option value="${course.courseId}">${course.courseName}-
							${course.trainerName}</option>
					</c:forEach>
				</select>
			</div>
			<div >
				<label>Registration Date</label> <input type="date"
					name="registrationDate"  required>
			</div>
			<div >
				<label>Status</label> <select name="status" 
					required>
					<option value="Active">Active</option>
					<option value="Completed">Completed</option>
					<option value="Cancelled">Cancelled</option>
				</select>
			</div>
			<button type="submit" >Register
				Student</button>
			<a href="${pageContext.request.contextPath}/registrations"
				>Cancel</a>
		</form>
	</div>
</body>
</html>