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
<title>Add New Student</title>
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
			<h2 class="text-center mb-4">Add New Student</h2>

			<!-- General Error -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/student/add"
				method="post">

				<div class="mb-3">
					<label class="form-label">Student Name</label> <input type="text"
						name="studentName" class="form-control" value="${studentName}"
						required>
					<c:if test="${not empty studentNameError}">
						<div class="error-text">${studentNameError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Email</label> <input type="email"
						name="email" class="form-control" value="${email}" required>
					<c:if test="${not empty emailError}">
						<div class="error-text">${emailError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Phone (10 digits)</label> <input
						type="text" name="phone" class="form-control" value="${phone}"
						required>
					<c:if test="${not empty phoneError}">
						<div class="error-text">${phoneError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Age</label> <input type="number"
						name="age" class="form-control" min="18" max="100" value="${age}"
						required>
					<c:if test="${not empty ageError}">
						<div class="error-text">${ageError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">City</label> <input type="text"
						name="city" class="form-control" value="${city}" required>
					<c:if test="${not empty cityError}">
						<div class="error-text">${cityError}</div>
					</c:if>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center mt-4">
					<button type="submit" class="btn btn-primary px-4">Add
						Student</button>
					<a href="${pageContext.request.contextPath}/students"
						class="btn btn-secondary px-4">Cancel</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>