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
<title>Students List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >

	<div >

		<div >
			<h2>👨‍🎓 Students Management</h2>
			<a href="${pageContext.request.contextPath}/student/add"
				> + Add New Student </a>
		</div>
		<%
		if (request.getAttribute("error") != null) {
		%>
		<div ><%=request.getAttribute("error")%></div>
		<%
		}
		%>
		<table >
			<thead >
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Age</th>
					<th>City</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.studentId}</td>
						<td>${student.studentName}</td>
						<td>${student.email}</td>
						<td>${student.phone}</td>
						<td>${student.age}</td>
						<td>${student.city}</td>
						<td><a
							href="${pageContext.request.contextPath}/student/edit?id=${student.studentId}"
							>Edit</a> <a
							href="${pageContext.request.contextPath}/student/delete?id=${student.studentId}"
							
							onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="${pageContext.request.contextPath}/dashboard"
			>← Back to Dashboard</a>
	</div>

	
</body>
</html>