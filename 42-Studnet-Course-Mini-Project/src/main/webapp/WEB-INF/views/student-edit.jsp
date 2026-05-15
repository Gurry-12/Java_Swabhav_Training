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
<title>Edit Student</title>

</head>
<body>
	<div>
		<h2>Edit Student</h2>

		<%
		if (request.getAttribute("error") != null) {
		%>
		<div><%=request.getAttribute("error")%></div>
		<%
		}
		%>

		<form action="${pageContext.request.contextPath}/student/update"
			method="post">
			<input type="hidden" name="studentId" value="${student.studentId}">

			<div>
				<label>Student Name</label> <input type="text" name="studentName"
					value="${student.studentName}" required>
			</div>
			<div>
				<label>Email</label> <input type="email" name="email"
					value="${student.email}" required>
			</div>
			<div>
				<label>Phone</label> <input type="text" name="phone"
					value="${student.phone}" required>
			</div>
			<div>
				<label>Age</label> <input type="number" name="age"
					value="${student.age}" min="18" required>
			</div>
			<div>
				<label>City</label> <input type="text" name="city"
					value="${student.city}" required>
			</div>
			<button type="submit">Update Student</button>
			<a href="${pageContext.request.contextPath}/students">Cancel</a>
		</form>
	</div>
</body>
</html>