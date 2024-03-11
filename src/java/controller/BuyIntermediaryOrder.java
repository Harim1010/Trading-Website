/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.IntermediaryOrderDAO;
import DAO.WalletDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.IntermediaryOrder;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "BuyIntermediaryOrder", urlPatterns = {"/buyIntermediaryOrder"})
public class BuyIntermediaryOrder extends HttpServlet {

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
            out.println("<title>Servlet BuyIntermediaryOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyIntermediaryOrder at " + request.getContextPath() + "</h1>");
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
        WalletDAO wDAO = new WalletDAO();
        
        IntermediaryOrderDAO interOrderDAO = new IntermediaryOrderDAO();
        IntermediaryOrder interOrder = new IntermediaryOrder();
        if (acc != null) {
            int oId = Integer.parseInt(request.getParameter("oId"));

            interOrder = interOrderDAO.getIntermediaryOrderById(oId);
            String orderStatus = interOrder.getOrder_status();
            if (!orderStatus.equals("ready to trade")) {
                request.setAttribute("error", "Đơn hàng đã được mua");
                request.getRequestDispatcher("ErrorAction.jsp").forward(request, response);
            } else {
                int uId = acc.getId();
                double price = Double.parseDouble(request.getParameter("price"));
                IntermediaryOrderDAO oDAO = new IntermediaryOrderDAO();
                if (oDAO.buyIntermediaryOrder(oId, uId, price)) {
                    ss.setAttribute("walletCurrent", wDAO.GetWalletByID(uId));
                }
                List<IntermediaryOrder> listIntermediaryOrder = new ArrayList<>();
                listIntermediaryOrder = oDAO.getAllMyIntermediaryOrder(uId);
                request.setAttribute("listOrder", listIntermediaryOrder);
                request.setAttribute("accountId", uId);
                request.getRequestDispatcher("MyIntermediaryOrder.jsp").forward(request, response);
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
