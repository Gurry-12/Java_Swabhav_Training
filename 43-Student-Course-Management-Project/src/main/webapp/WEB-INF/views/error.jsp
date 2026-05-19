<%@ page language="java" contentType="text/html; charset=UTF-8" 
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Student Course Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
        .error-container {
            max-width: 600px;
            margin: 100px auto;
            text-align: center;
        }
        .error-icon {
            font-size: 5rem;
            color: #dc3545;
            margin-bottom: 20px;
        }
        .error-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            padding: 40px 30px;
        }
    </style>
</head>
<body class="bg-light">
<div class="container">
    <div class="error-container">
        <div class="error-card">
            <h2 class="text-danger mb-3">Something Went Wrong</h2>
            
            <c:if test="${not empty errorMessage}">
                <p class="fs-5 text-muted">${errorMessage}</p>
            </c:if>
            <c:if test="${empty errorMessage}">
                <p class="fs-5 text-muted">An unexpected error occurred. Please try again later.</p>
            </c:if>

            <hr class="my-4">
            
            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                <a href="${pageContext.request.contextPath}/dashboard" 
                   class="btn btn-primary btn-lg px-4">
                    Go to Dashboard
                </a>
                <a href="${pageContext.request.contextPath}/students" 
                   class="btn btn-outline-secondary btn-lg px-4">
                    View Students
                </a>
            </div>
        </div>
        
        <p class="text-muted mt-4 small">
            If the problem persists, please contact the administrator.
        </p>
    </div>
</div>
</body>
</html>