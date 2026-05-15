<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.employee.model.LeaveRequest"%>
<!DOCTYPE html>
<html>
<head>
<title>View Leaves</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<h2>Leave Applications Log</h2>

		<!-- Added 'leave-table' class for better CSS targeting -->
		<table class="leave-table">
			<thead>
				<tr>
					<th>Employee Name</th>
					<th>Employee ID</th>
					<th>Leave Type</th>
					<th>Duration (Days)</th>
					<th>Current Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
				if (leaves != null && !leaves.isEmpty()) {
					for (LeaveRequest leave : leaves) {
				%>
				<tr>
					<td><%=leave.getName()%></td>
					<td><%=leave.getId()%></td>
					<td><%=leave.getType()%></td>
					<td><%=leave.getDays()%></td>
					<td class="status-cell"><%=leave.getStatus()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5" class="no-data">No records found in the
						database.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

		<div class="footer-actions">
			<a href="index.html"><button class="btn-back">Return to
					Dashboard</button></a>
		</div>
	</div>
</body>
</html>