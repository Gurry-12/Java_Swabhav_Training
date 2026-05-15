<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Success</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container success-banner">
		<h2>${employeeName}'s Leave Applied Successfully!</h2>
		<p>Your request has been saved to the database and is pending
			review.</p>
		<br>

		<!-- Changed to method="post" -->
		<form action="leaveReview" method="post">
			<!-- Hidden field to pass the employeeId to the next Servlet -->
			<input type="hidden" name="employeeId" value="${employeeId}">

			<input type="submit" value="Check Leave Review">
		</form>

		<br> <a href="index.html"
			style="text-decoration: none; color: #3498db;">Return to Home</a>
	</div>
</body>
</html>