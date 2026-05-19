<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
.login-container {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
}

.login-card {
	max-width: 420px;
	width: 100%;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
	border: none;
	border-radius: 12px;
}
</style>
</head>
<body class="bg-light">

	<div class="login-container">
		<div class="card login-card">
			<div class="card-body p-5">

				<h3 class="text-center mb-4 fw-bold">Login</h3>

				<!-- Error Message -->
				<c:if test="${not empty error}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						${error}
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>

				<!-- Success Message (e.g., after logout) -->
				<c:if test="${not empty success}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						${success}
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/login-action"
					method="post">

					<div class="mb-3">
						<label for="username" class="form-label">Username</label> <input
							type="text" id="username" name="username" class="form-control"
							placeholder="Enter your username" value="${rememberedUsername}"
							required autofocus>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="Enter your password" required>
					</div>

					<div class="mb-4 form-check">
						<input type="checkbox" class="form-check-input" id="rememberMe"
							name="rememberMe"> <label class="form-check-label"
							for="rememberMe">Remember me</label>
					</div>

					<button type="submit" class="btn btn-primary w-100 py-2">Login</button>
				</form>

			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>