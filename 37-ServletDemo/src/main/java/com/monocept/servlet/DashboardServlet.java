package com.monocept.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("fullname");
        String email = request.getParameter("email");

        out.println("<h2>Welcome to Dashboard</h2>");
        out.println("<p>Name: " + (name != null ? name : "Guest") + "</p>");
        out.println("<p>Email: " + (email != null ? email : "N/A") + "</p>");
        out.println("<a href='home'>← Back to Home</a>");
    }
}