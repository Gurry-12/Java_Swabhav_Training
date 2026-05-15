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
<title>New Student Course Registration</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.error-text {
	color: #dc3545;
	font-size: 0.875rem;
	margin-top: 4px;
}

.form-container {
	max-width: 650px;
	margin: 0 auto;
	background: white;
	padding: 35px;
	border-radius: 12px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body class="bg-light">
	<div class="container py-5">

		<div class="form-container">
			<h2 class="text-center mb-4">New Student Course Registration</h2>

			<!-- General Error Message -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/registration/add"
				method="post">

				<div class="mb-3">
					<label class="form-label">Student</label> <select name="studentId"
						class="form-control" required>
						<option value="">-- Select Student --</option>
						<c:forEach var="student" items="${students}">
							<option value="${student.studentId}"
								${student.studentId == selectedStudentId ? 'selected' : ''}>
								${student.studentName} (${student.email})</option>
						</c:forEach>
					</select>
					<c:if test="${not empty studentIdError}">
						<div class="error-text">${studentIdError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Course</label> <select name="courseId"
						class="form-control" required>
						<option value="">-- Select Course --</option>
						<c:forEach var="course" items="${courses}">
							<option value="${course.courseId}"
								${course.courseId == selectedCourseId ? 'selected' : ''}>
								${course.courseName} - ${course.trainerName}</option>
						</c:forEach>
					</select>
					<c:if test="${not empty courseIdError}">
						<div class="error-text">${courseIdError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Registration Date</label> <input
						type="date" name="registrationDate" class="form-control"
						value="${selectedDate}" required>
					<c:if test="${not empty registrationDateError}">
						<div class="error-text">${registrationDateError}</div>
					</c:if>
				</div>

				<div class="mb-3">
					<label class="form-label">Status</label> <select name="status"
						class="form-control" required>
						<option value="Active"
							${selectedStatus == 'Active' ? 'selected' : ''}>Active</option>
						<option value="Completed"
							${selectedStatus == 'Completed' ? 'selected' : ''}>Completed</option>
						<option value="Cancelled"
							${selectedStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
					</select>
					<c:if test="${not empty statusError}">
						<div class="error-text">${statusError}</div>
					</c:if>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center mt-4">
					<button type="submit" class="btn btn-primary px-5">Register
						Student</button>
					<a href="${pageContext.request.contextPath}/registrations"
						class="btn btn-secondary px-5">Cancel</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>