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
<title>Add New Course</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.error-text {
	color: #dc3545;
	font-size: 0.875rem;
	margin-top: 4px;
}

.form-container {
	max-width: 600px;
	margin: 0 auto;
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<div class="form-container">
			<h2 class="text-center mb-4">Add New Course</h2>

			<!-- General Error -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/course/add"
				method="post">

				<div class="mb-3">
					<label class="form-label">Course Name</label> <input type="text"
						name="courseName" class="form-control" value="${courseName}"
						required>
					<c:if test="${not empty courseNameError}">
						<div class="error-text">${courseNameError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Duration (e.g., 6 Months)</label> <input
						type="text" name="duration" class="form-control"
						value="${duration}" required>
					<c:if test="${not empty durationError}">
						<div class="error-text">${durationError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Fees (₹)</label> <input type="number"
						name="fees" step="0.01" min="0" class="form-control"
						value="${fees}" required>
					<c:if test="${not empty feesError}">
						<div class="error-text">${feesError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Trainer Name</label> <input type="text"
						name="trainerName" class="form-control" value="${trainerName}"
						required>
					<c:if test="${not empty trainerNameError}">
						<div class="error-text">${trainerNameError}</div>
					</c:if>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center mt-4">
					<button type="submit" class="btn btn-primary px-4">Add
						Course</button>
					<a href="${pageContext.request.contextPath}/courses"
						class="btn btn-secondary px-4">Cancel</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>