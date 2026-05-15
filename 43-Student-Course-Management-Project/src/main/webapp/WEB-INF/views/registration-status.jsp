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
<title>Update Registration Status</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.form-container {
	max-width: 500px;
	margin: 0 auto;
	background: white;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.error-text {
	color: #dc3545;
	font-size: 0.875rem;
	margin-top: 4px;
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<div class="form-container">
			<h3 class="text-center mb-4">Update Registration Status</h3>

			<!-- General Error -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/registration/status"
				method="post">
				<input type="hidden" name="id" value="${param.id}">

				<div class="mb-3">
					<label class="form-label">Current Status: <strong>${currentStatus}</strong>
					</label>
				</div>

				<div class="mb-3">
					<label class="form-label">New Status</label> <select name="status"
						class="form-control" required>
						<option value="Active"
							${currentStatus == 'Active' ? 'selected' : ''}>Active</option>
						<option value="Completed"
							${currentStatus == 'Completed' ? 'selected' : ''}>Completed</option>
						<option value="Cancelled"
							${currentStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
					</select>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center mt-4">
					<button type="submit" class="btn btn-primary px-4">Update
						Status</button>
					<a href="${pageContext.request.contextPath}/registrations"
						class="btn btn-secondary px-4">Cancel</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>