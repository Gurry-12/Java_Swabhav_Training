<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Registration</title>
</head>
<body>
	
	<%
	    String error = (String) session.getAttribute("errorMessage");
	    if (error != null) {
	%>
	    <div >
 	       <%= error %>
	    </div>
	<%
 	       // Clear the message so it doesn't persist on next reload
	        session.removeAttribute("errorMessage");
	    }
	%>
    <h2>Course Registration Form</h2>
    
    <form action="register" method="post">
        <label>Student Name:</label>
        <input type="text" name="studentName"><br><br>
        
        <label>Email:</label>
        <input type="email" name="email" ><br><br>
        
        <label>Age:</label>
        <input type="number" name="age" ><br><br>
        
        <label>Course Name:</label>
        <select name="courseName" required>
            <option value="">-- Select Course --</option>
            <option value="Java Full Stack">Java Full Stack</option>
            <option value="Python Full Stack">Python Full Stack</option>
            <option value="MERN Stack">MERN Stack</option>
            <option value="Data Analytics">Data Analytics</option>
        </select><br><br>
        
        <label>Preferred Batch Time:</label>
        <select name="batchTime" >
            <option value="">-- Select Batch --</option>
            <option value="Morning (9 AM - 12 PM)">Morning (9 AM - 12 PM)</option>
            <option value="Afternoon (2 PM - 5 PM)">Afternoon (2 PM - 5 PM)</option>
            <option value="Evening (6 PM - 9 PM)">Evening (6 PM - 9 PM)</option>
        </select><br><br>
        
        <input type="submit" value="Submit Registration">
    </form>
    <br/>
    <a href="index.html"> <button>Back to home </button></a>
</body>
</html>