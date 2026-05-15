package com.student.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember"); // Checkbox value

        if ("student".equals(user) && "student123".equals(pass)) {
            // 1. Session Management (from Assignment 1)
            request.getSession().setAttribute("user", user);

            // 2. Cookie Management (Assignment 3)
            Cookie userCookie = new Cookie("savedUser", user);
            if (remember != null) {
                userCookie.setMaxAge(60); 
            } else {
                userCookie.setMaxAge(0); // Delete existing cookie if unchecked
            }
            response.addCookie(userCookie);
            
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}