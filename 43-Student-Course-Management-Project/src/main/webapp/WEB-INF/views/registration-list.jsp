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
<title>Registrations</title>
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

.status-badge {
	font-size: 0.95rem;
	padding: 6px 12px;
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<!-- Page Header -->
		<div
			class="page-header d-flex justify-content-between align-items-center">
			<h2 class="fw-bold mb-0">Student Course Registrations</h2>
			<a href="${pageContext.request.contextPath}/registration/add"
				class="btn btn-success"> <strong>+ New Registration</strong>
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

		<!-- Registrations Table -->
		<div class="card shadow-sm">
			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th width="90">Reg ID</th>
							<th>Student Name</th>
							<th>Course Name</th>
							<th>Registration Date</th>
							<th width="130">Status</th>
							<th width="220" class="text-center">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reg" items="${registrations}">
							<tr>
								<td><strong>${reg.registrationId}</strong></td>
								<td>${reg.studentName}</td>
								<td>${reg.courseName}</td>
								<td>${reg.registrationDate}</td>
								<td><span
									class="badge status-badge bg-${reg.status == 'Active' ? 'success' : 
                                                 reg.status == 'Completed' ? 'primary' : 
                                                 reg.status == 'Cancelled' ? 'danger' : 'warning'}">
										${reg.status} </span></td>
								<td class="text-center"><a
									href="${pageContext.request.contextPath}/registration/status?id=${reg.registrationId}"
									class="btn btn-info btn-sm action-btn">Update Status</a> <a
									href="${pageContext.request.contextPath}/registration/delete?id=${reg.registrationId}"
									class="btn btn-danger btn-sm action-btn"
									onclick="return confirm('Are you sure you want to delete this registration?')">
										Delete </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Empty State -->
		<c:if test="${empty registrations}">
			<div class="text-center py-5">
				<h5 class="text-muted">No registrations found.</h5>
				<a href="${pageContext.request.contextPath}/registration/add"
					class="btn btn-success mt-3"> Create New Registration </a>
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