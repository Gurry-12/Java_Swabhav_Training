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
<title>Registrations</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body >

	<div >

		<div >
			<h2>📝 Student Course Registrations</h2>
			<a href="${pageContext.request.contextPath}/registration/add"
				> + New Registration </a>
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
					<th>Reg ID</th>
					<th>Student Name</th>
					<th>Course Name</th>
					<th>Registration Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reg" items="${registrations}">
					<tr>
						<td>${reg.registrationId}</td>
						<td>${reg.studentName}</td>
						<td>${reg.courseName}</td>
						<td>${reg.registrationDate}</td>
						<td><span
							>
								${reg.status} </span></td>
						<td><a
							href="${pageContext.request.contextPath}/registration/status?id=${reg.registrationId}"
							>Update Status</a> <a
							href="${pageContext.request.contextPath}/registration/delete?id=${reg.registrationId}"
							
							onclick="return confirm('Delete this registration?')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="${pageContext.request.contextPath}/dashboard"
			>Back to Dashboard</a>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>