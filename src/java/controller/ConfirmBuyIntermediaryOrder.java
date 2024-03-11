/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.IntermediaryOrderDAO;
import DAO.TransactionHistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.IntermediaryOrder;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ConfirmBuyIntermediaryOrder", urlPatterns = {"/confirmBuyIntermediaryOrder"})
public class ConfirmBuyIntermediaryOrder extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConfirmBuyIntermediaryOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmBuyIntermediaryOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession ss = request.getSession();
        Account acc = (Account) ss.getAttribute("account");

        IntermediaryOrderDAO interOrderDAO = new IntermediaryOrderDAO();
        IntermediaryOrder interOrder = new IntermediaryOrder();
        if (acc != null) {
            int oId = Integer.parseInt(request.getParameter("oId"));
            interOrder = interOrderDAO.getIntermediaryOrderById(oId);
            String orderStatus = interOrder.getOrder_status();
            if (!orderStatus.equals("checking")) {
                request.setAttribute("error", "Đơn hàng đã được mua");
                request.getRequestDispatcher("ErrorAction.jsp").forward(request, response);
            } else {
                int uBuyId = acc.getId();
                int uSellId = Integer.parseInt(request.getParameter("uSellId"));
                double price = Double.parseDouble(request.getParameter("price"));
                IntermediaryOrderDAO oDAO = new IntermediaryOrderDAO();
                oDAO.confirmBuyIntermediaryOrder(oId, uBuyId, uSellId, price);
                List<IntermediaryOrder> listIntermediaryOrder = new ArrayList<>();
                listIntermediaryOrder = oDAO.getAllMyIntermediaryOrder(uBuyId);
                request.setAttribute("listOrder", listIntermediaryOrder);
                request.setAttribute("accountId", uBuyId);
                request.getRequestDispatcher("MyIntermediaryOrder.jsp").forward(request, response);
                TransactionHistoryDAO th = new TransactionHistoryDAO();

                Timestamp createdTimestamp = Timestamp.valueOf(interOrder.getCreated_at());
                th.InsertIntoTransactionHistory(uSellId, price, true, "Complete transaction code " + oId, acc.getName(), createdTimestamp, createdTimestamp);
                th.InsertIntoTransactionHistory(uBuyId, price, false, "Complete transaction code " + oId, acc.getName(), createdTimestamp, createdTimestamp);

            }
        } else {
            response.sendRedirect("ErrorNotFound.jsp");
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
