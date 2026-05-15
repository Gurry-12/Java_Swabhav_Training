<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<form action="login-action" method="post">

		<label for="username">Username</label> 
		<input type="text" name="username" id="username" value="${username}" required> 
		<br/>
		<br/>
		<label for="password">Password</label> 
		<input type="text" name="password" id="password" required> 
		<br/>
		<br/>
		<input type="checkbox" name="rememberUsername" id="rememberUsername"> 
		<br/>
		<br/>
		<input type="submit">

	</form>

</body>
</html>