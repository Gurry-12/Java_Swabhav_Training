<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Apply for Leave</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Leave Application Form</h2>

        <%-- Displays error from session if validation failed in Servlet --%>
        <% if (session.getAttribute("error") != null) { %>
            <div class="error-msg"><%= session.getAttribute("error") %></div>
            <% session.removeAttribute("error"); %>
        <% } %>

        <form action="applyLeave" method="post">
            <label>Employee Name:</label>
            <input type="text" name="employeeName">

            <label>Employee ID:</label>
            <input type="text" name="employeeId">

            <label>Department:</label>
            <input type="text" name="department">

            <label>Leave Type:</label>
            <select name="leaveType">
                <option value="">-- Select Type --</option>
                <option value="Sick Leave">Sick Leave</option>
                <option value="Casual Leave">Casual Leave</option>
                <option value="Emergency Leave">Emergency Leave</option>
                <option value="Work From Home">Work From Home</option>
            </select>

            <label>Number of Leave Days (1-10):</label>
            <input type="number" name="leaveDays">

            <label>Reason (Min 10 characters):</label>
            <textarea name="reason"></textarea>

            <input type="submit" value="Submit Application">
        </form>
        <br/>
        <a href="index.html"><button>Back to home</button></a>
    </div>
</body>
</html>