<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body >
	<div >
		<div  style="max-width: 500px;">
			<div >
				<h3 >Update Registration Status</h3>

				<%
				if (request.getAttribute("error") != null) {
				%>
				<div ><%=request.getAttribute("error")%></div>
				<%
				}
				%>

				<form
					action="${pageContext.request.contextPath}/registration/status"
					method="post">
					<input type="hidden" name="id" value="${param.id}">

					<div >
						<label >Current Status: <strong>${currentStatus}</strong></label>
					</div>

					<div >
						<label >New Status</label> <select name="status"
							 required>
							<option value="Active"
								${currentStatus == 'Active' ? 'selected' : ''}>Active</option>
							<option value="Completed"
								${currentStatus == 'Completed' ? 'selected' : ''}>Completed</option>
							<option value="Cancelled"
								${currentStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
						</select>
					</div>

					<div >
						<button type="submit" >Update
							Status</button>
						<a href="${pageContext.request.contextPath}/registrations"
							>Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>