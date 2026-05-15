<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Exam Result Form</title></head>
<body>
    <h2>Online Exam Result Entry</h2>
    
    <%-- Error message display --%>
    <% if (session.getAttribute("errorMessage") != null) { %>
        <p ><%= session.getAttribute("errorMessage") %></p>
        <% session.removeAttribute("errorMessage"); %>
    <% } %>

    <form action="calculateResult" method="post">
        <label>Student Name:</label> 
        <input type="text" name="studentName" required><br><br>
        
        <label>Roll Number:</label> 
        <input type="text" name="rollNumber" required><br><br>
        
        <label>Subject 1 Marks (0-100):</label> 
        <input type="number" name="marks1" required><br><br>
        
        <label>Subject 2 Marks (0-100):</label> 
        <input type="number" name="marks2" required><br><br>
        
        <label>Subject 3 Marks (0-100):</label> 
        <input type="number" name="marks3" required><br><br>
        
        <input type="submit" value="Calculate Result">
    </form>
</body>
</html>