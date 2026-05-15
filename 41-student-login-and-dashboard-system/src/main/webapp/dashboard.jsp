<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// Rule: Redirect if session does not exist
if (session == null || session.getAttribute("user") == null) {
	response.sendRedirect("login.html");
	return;
}
String studentName = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class="dashboard-page">
	<nav class="navbar">
		<div class="nav-brand">Student Portal</div>
		<div class="nav-user">
			<span>Welcome, <strong><%=studentName%></strong></span> <a
				href="logout" class="btn-logout">Logout</a>
		</div>
	</nav>

	<main class="content">
		<div class="welcome-card">
			<h1>Welcome Message</h1>
			<p>You have successfully logged into the Student Dashboard.</p>
			<div class="status-badge">Session Active</div>
		</div>

		<div class="info-grid">
			<div class="info-item">
				<h3>Username</h3>
				<p><%=studentName%></p>
			</div>
			<div class="info-item">
				<h3>Access Level</h3>
				<p>Standard Student</p>
			</div>
		</div>
	</main>
</body>
</html>