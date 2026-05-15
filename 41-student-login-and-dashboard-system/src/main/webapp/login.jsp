<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String savedUser = "";
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie c : cookies) {
		if ("savedUser".equals(c.getName())) {
	savedUser = c.getValue();
		}
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Login | Remember Me</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-page">
	<div class="login-card">
		<h2>Student Login</h2>
		<form action="login" method="POST">
			<div class="form-group">
				<label>Username</label> <input type="text" name="username"
					value="<%=savedUser%>" required>
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" name="password"
					required>
			</div>

			<!-- Remember Username Checkbox -->
			<div class="checkbox-group">
				<input type="checkbox" name="remember" id="remember"
					<%=!savedUser.isEmpty() ? "checked" : ""%>> <label
					for="remember">Remember Username</label>
			</div>

			<button type="submit" class="btn-primary">Login</button>
		</form>

		<%
		if (!savedUser.isEmpty()) {
		%>
		<div class="cookie-action">
			<a href="deleteCookie">Forget saved username</a>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>