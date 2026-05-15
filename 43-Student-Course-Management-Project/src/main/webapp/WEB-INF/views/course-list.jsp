<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
// === SESSION PROTECTION ===
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
<style>
.table th {
	font-weight: 600;
}

.action-btn {
	margin-right: 6px;
}

.page-header {
	border-bottom: 2px solid #f1f1f1;
	padding-bottom: 15px;
	margin-bottom: 25px;
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<!-- Page Header -->
		<div
			class="page-header d-flex justify-content-between align-items-center">
			<h2 class="fw-bold mb-0">Courses Management</h2>
			<a href="${pageContext.request.contextPath}/course/add"
				class="btn btn-success"> <strong>+ Add New Course</strong>
			</a>
		</div>

		<!-- Error Message -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<!-- Success Message (Optional) -->
		<c:if test="${not empty success}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${success}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<!-- Courses Table -->
		<div class="card shadow-sm">
			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th width="80">ID</th>
							<th>Course Name</th>
							<th>Duration</th>
							<th width="150">Fees</th>
							<th>Trainer Name</th>
							<th width="180" class="text-center">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="course" items="${courses}">
							<tr>
								<td>${course.courseId}</td>
								<td><strong>${course.courseName}</strong></td>
								<td>${course.duration}</td>
								<td><strong>₹ ${course.fees}</strong></td>
								<td>${course.trainerName}</td>
								<td class="text-center"><a
									href="${pageContext.request.contextPath}/course/edit?id=${course.courseId}"
									class="btn btn-warning btn-sm action-btn">Edit</a> <a
									href="${pageContext.request.contextPath}/course/delete?id=${course.courseId}"
									class="btn btn-danger btn-sm action-btn"
									onclick="return confirm('Are you sure you want to delete this course?')">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Empty State -->
		<c:if test="${empty courses}">
			<div class="text-center py-5">
				<h5 class="text-muted">No courses found.</h5>
				<a href="${pageContext.request.contextPath}/course/add"
					class="btn btn-success mt-3"> Add Your First Course </a>
			</div>
		</c:if>

		<!-- Back Button -->
		<div class="mt-4">
			<a href="${pageContext.request.contextPath}/dashboard"
				class="btn btn-secondary"> Back to Dashboard </a>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>