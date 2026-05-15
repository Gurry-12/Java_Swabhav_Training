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
<title>Students List</title>
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
			<h2 class="fw-bold mb-0">Students Management</h2>
			<a href="${pageContext.request.contextPath}/student/add"
				class="btn btn-success"> <strong>+ Add New Student</strong>
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

		<!-- Success Message -->
		<c:if test="${not empty success}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${success}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<!-- Students Table -->
		<div class="card shadow-sm">
			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th width="80">ID</th>
							<th>Student Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th width="80">Age</th>
							<th>City</th>
							<th width="200" class="text-center">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${students}">
							<tr>
								<td><strong>${student.studentId}</strong></td>
								<td><strong>${student.studentName}</strong></td>
								<td>${student.email}</td>
								<td>${student.phone}</td>
								<td>${student.age}</td>
								<td>${student.city}</td>
								<td class="text-center"><a
									href="${pageContext.request.contextPath}/student/edit?id=${student.studentId}"
									class="btn btn-warning btn-sm action-btn">Edit</a> <a
									href="${pageContext.request.contextPath}/student/delete?id=${student.studentId}"
									class="btn btn-danger btn-sm action-btn"
									onclick="return confirm('Are you sure you want to delete this student?')">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Empty State -->
		<c:if test="${empty students}">
			<div class="text-center py-5">
				<h5 class="text-muted">No students found.</h5>
				<a href="${pageContext.request.contextPath}/student/add"
					class="btn btn-success mt-3"> Add Your First Student </a>
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