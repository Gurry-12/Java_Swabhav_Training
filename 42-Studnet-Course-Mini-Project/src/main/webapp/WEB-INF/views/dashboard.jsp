<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// === SESSION PROTECTION (Project Requirement 10.2) ===
if (session == null || session.getAttribute("loggedInUser") == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}

String username = (String) session.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

</head>
<body >

	<div >

		<!-- Welcome Message -->
		<div >
			<h2 >
				Welcome, <span ><%=username%></span>!
			</h2>
			<a href="${pageContext.request.contextPath}/logout"
				> Logout </a>
		</div>

		<!-- Summary Counts -->
		<div >

			<div >
				<div >
					<div >
						<h5 >Total Students</h5>
						<h1 >
							${totalStudents}</h1>
					</div>
				</div>
			</div>

			<div >
				<div >
					<div >
						<h5 >Total Courses</h5>
						<h1 >
							${totalCourses}</h1>
					</div>
				</div>
			</div>

			<div >
				<div >
					<div >
						<h5 >Total Registrations</h5>
						<h1 >
							${totalRegistrations}</h1>
					</div>
				</div>
			</div>

		</div>

		<!-- Navigation Links -->
		<h4 >Quick Navigation</h4>
		<div >
			<div >
				<a href="${pageContext.request.contextPath}/students"
					> 👨‍🎓 Manage
					Students </a>
			</div>
			<div >
				<a href="${pageContext.request.contextPath}/courses"
					> 📚 Manage Courses </a>
			</div>
			<div >
				<a href="${pageContext.request.contextPath}/registrations"
					> 📝 Manage Registrations
				</a>
			</div>
		</div>

	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>