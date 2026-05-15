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
<title>Courses List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >

	<div >

		<div >
			<h2>📚 Courses Management</h2>

			<a href="${pageContext.request.contextPath}/course/add"
				> + Add New Course </a>
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
					<th>Course Name</th>
					<th>Duration</th>
					<th>Fees</th>
					<th>Trainer</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="course" items="${courses}">
					<tr>
						<td>${course.courseId}</td>
						<td>${course.courseName}</td>
						<td>${course.duration}</td>
						<td>₹ ${course.fees}</td>
						<td>${course.trainerName}</td>
						<td><a
							href="${pageContext.request.contextPath}/course/edit?id=${course.courseId}"
							>Edit</a> <a
							href="${pageContext.request.contextPath}/course/delete?id=${course.courseId}"
							
							onclick="return confirm('Delete this course?')">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="${pageContext.request.contextPath}/dashboard"
			>← Back to Dashboard</a>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>