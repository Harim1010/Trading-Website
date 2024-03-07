/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "editAccount", urlPatterns = {"/editAc"})
public class editAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("pid");
        int iId = Integer.parseInt(id);
        AccountDAO dao = new AccountDAO();
        Account acc = dao.GetAccountByID(iId);
        request.setAttribute("accEdit", acc);
        request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String sID, xUser, xPass, sRoleID, sName;
        sID = request.getParameter("id");
        sName = request.getParameter("name");
        xUser = request.getParameter("user");
        xPass = request.getParameter("pass");
        sRoleID = request.getParameter("roleId");
        int iId = Integer.parseInt(sID);
        int xID = Integer.parseInt(sRoleID);
        Account account = new Account(iId, sName, xUser, xPass, xID);
        AccountDAO dao = new AccountDAO();
        String mess = "";
        if (dao.checkAccountExist(xUser) == null) {
            dao.updateAccountById(account);
            response.sendRedirect("managerAc");
        } else {
            mess = "User " + xUser + " is exist!";
            request.setAttribute("mess", mess);
            Account acc = dao.GetAccountByID(iId);
            request.setAttribute("accEdit", acc);
            request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
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
