<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// === SESSION PROTECTION ===
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.dashboard-card {
	border: none;
	border-radius: 10px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.08);
}

.stat-number {
	font-size: 2.8rem;
	font-weight: 700;
}

.nav-btn {
	height: 70px;
	font-size: 1.1rem;
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 10px;

}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<!-- Welcome Header -->
		<div class="d-flex justify-content-between align-items-center mb-5">
			<h2 class="fw-bold mb-0">
				Welcome, <span class="text-primary"><%=username%></span>!
			</h2>
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-outline-danger">Logout</a>
		</div>

		<!-- Summary Statistics -->
		<h4 class="mb-4 text-muted">Overview</h4>
		<div class="row g-4 mb-5">
			<div class="col-md-4">
				<div class="card dashboard-card text-center h-100">
					<div class="card-body">
						<h5 class="text-muted mb-2">Total Students</h5>
						<h1 class="stat-number text-primary mb-0">${totalStudents}</h1>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card dashboard-card text-center h-100">
					<div class="card-body">
						<h5 class="text-muted mb-2">Total Courses</h5>
						<h1 class="stat-number text-success mb-0">${totalCourses}</h1>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card dashboard-card text-center h-100">
					<div class="card-body">
						<h5 class="text-muted mb-2">Total Registrations</h5>
						<h1 class="stat-number text-info mb-0">${totalRegistrations}</h1>
					</div>
				</div>
			</div>
		</div>

		<!-- Quick Navigation -->
		<h4 class="mb-4 text-muted">Quick Navigation</h4>
		<div class="row g-4">
			<div class="col-md-4">
				<a href="${pageContext.request.contextPath}/students"
					class="btn btn-primary btn-lg w-100 nav-btn"> Manage
					Students </a>
			</div>
			<div class="col-md-4">
				<a href="${pageContext.request.contextPath}/courses"
					class="btn btn-success btn-lg w-100 nav-btn"> Manage Courses
				</a>
			</div>
			<div class="col-md-4">
				<a href="${pageContext.request.contextPath}/registrations"
					class="btn btn-info btn-lg w-100 nav-btn text-white"> Manage
					Registrations </a>
			</div>
		</div>

	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>