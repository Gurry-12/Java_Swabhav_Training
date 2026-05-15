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
<title>Add Course</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >
	<div >
		<h2>Add New Course</h2>

		<%
		if (request.getAttribute("error") != null) {
		%>
		<div ><%=request.getAttribute("error")%></div>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/course/add"
			method="post">
			<div >
				<label>Course Name</label> <input type="text" name="courseName"
					 required>
			</div>
			<div >
				<label>Duration</label> <input type="text" name="duration"
					 required>
			</div>
			<div >
				<label>Fees</label> <input type="number" name="fees"
					 step="0.01" min="0" required>
			</div>
			<div >
				<label>Trainer Name</label> <input type="text" name="trainerName"
					 required>
			</div>
			<button type="submit" >Add Course</button>
			<a href="${pageContext.request.contextPath}/courses"
				>Cancel</a>
		</form>
	</div>
</body>
</html>