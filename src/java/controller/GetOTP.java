/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Xuanphuc
 */
public class GetOTP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("EnterOTP");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int value = Integer.parseInt(request.getParameter("otp"));
            HttpSession session = request.getSession();
            int otp = (int) session.getAttribute("otp");
            PrintWriter out = response.getWriter();
            if (value == otp) {
                request.setAttribute("email", request.getParameter("email"));
                request.getRequestDispatcher("Newpassword.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "wrong otp");
                request.getRequestDispatcher("EnterOTP.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "wrong otp");
            request.getRequestDispatcher("EnterOTP.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
