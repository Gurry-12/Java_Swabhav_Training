package com.monocept.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class ServletLifeCycle extends HttpServlet {

	@Override
	public void init() throws ServletException {

		System.out.println("Servlet initialized. ");
	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("Request handled successfully");
//		PrintWriter out = resp.getWriter();
//		out.println("Request Handled suceessfully");
//	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");  // comes from URL

	    // THINK: where does data come from? DB? service?
	    String userData = "User ID: " + id;

	    response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();
	    out.println(userData);
	}

	@Override
	public void destroy() {
		System.out.println("Servlet Destroy ");
	}

}
