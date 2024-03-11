/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.TransactionHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.trans_history;

/**
 *
 * @author caoth
 */
public class filter extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession ss = request.getSession();
            String id = request.getParameter("filterMaGiaoDich");
            int iId = Integer.parseInt(id);
            TransactionHistoryDAO th = new TransactionHistoryDAO();
            List<trans_history> listTransHis;
            Account account = (Account) ss.getAttribute("account");
            listTransHis = th.getTransById(account.getId(), iId);
            request.setAttribute("listA", listTransHis);
            request.setAttribute("giaodich", iId);
            request.getRequestDispatcher("TransHistory.jsp").forward(request, response);

        }
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
        try ( PrintWriter out = response.getWriter()) {
            HttpSession ss = request.getSession();
            String id = request.getParameter("filterMaGiaoDich");
            int iId = Integer.parseInt(id);
            TransactionHistoryDAO th = new TransactionHistoryDAO();
            List<trans_history> listTransHis;
            Account account = (Account) ss.getAttribute("account");

            // Thực hiện tìm kiếm dữ liệu dựa trên filterMaGiaoDich
            listTransHis = th.getTransById(account.getId(), iId);

            // Trả về kết quả dưới dạng HTML
            response.setContentType("text/html;charset=UTF-8");
            for (trans_history o : listTransHis) {
                out.println("<tr id=\"content\">");
                out.println("<td style=\"text-align: center;\">" + o.getId() + "</td>");
                out.println("<td style=\"text-align: center;\">" + o.getMoney() + "</td>");
                out.println("<td style=\"text-align: center;\">" + (o.isType() == false ? '-' : '+') + "</td>");
                out.println("<td style=\"text-align: center;\">" + (o.isWork() == false ? "Unprocessed" : "Processed") + "</td>");
                out.println("<td>" + o.getNote() + "</td>");
                out.println("<td>" + o.getCreate_by() + "</td>");
                out.println("<td>" + o.getCreate_at() + "</td>");
                out.println("<td>" + o.getUpdate_at() + "</td>");
                out.println("<td>");
                out.println("<button style=\"background-color: #4dbd74\" class=\"details\" data-id=\"" + o.getId() + "\" data-toggle=\"modal\">");
                out.println("Details");
                out.println("</button>");
                out.println("</td>");
                out.println("</tr>");
            }
        }
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
        processRequest(request, response);
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
