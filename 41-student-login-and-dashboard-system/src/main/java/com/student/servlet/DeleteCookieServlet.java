package com.student.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteCookie")
public class DeleteCookieServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Remove the cookie by setting age to 0
        Cookie killCookie = new Cookie("savedUser", "");
        killCookie.setMaxAge(0);
        response.addCookie(killCookie);
        
        response.sendRedirect("login.jsp");
    }
}
