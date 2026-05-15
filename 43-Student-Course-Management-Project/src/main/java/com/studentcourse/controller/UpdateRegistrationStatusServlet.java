package com.studentcourse.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registration/status")
public class UpdateRegistrationStatusServlet extends HttpServlet {

    private RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/registrations");
            return;
        }

        request.setAttribute("currentStatus", request.getParameter("status")); // optional

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-status.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String newStatus = request.getParameter("status");

        try (Connection connection = DBConnection.getConnection()) {

            registrationDAO.updateRegistrationStatus(connection, id, newStatus);
            response.sendRedirect(request.getContextPath() + "/registrations");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update status!");
            doGet(request, response);   // Show form again
        }
    }
}