/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MySaleDao;
import DAO.TransactionHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Intermediary_order;

/**
 *
 * @author Xuanphuc
 */
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        String title = request.getParameter("title");
        String priceParam = request.getParameter("price");
        String feeTypeParam = request.getParameter("feetype");
        String description = request.getParameter("description");
        String contact = request.getParameter("contact");
        String hideDescription = request.getParameter("hide");
        String publicStatusParam = request.getParameter("publicstatus");
        HttpSession ss = request.getSession();
        Account acc = (Account) ss.getAttribute("account");

        double price = Double.parseDouble(priceParam);
        double intermediary_fee = price * 0.05;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String createdDate = dateFormat.format(currentDate);
        MySaleDao msd = new MySaleDao();
        Intermediary_order io = new Intermediary_order();
        io.setTitle(title);
        io.setOrder_status("ready to trade");
        io.setPrice(price);
        io.setIntermediary_fee(intermediary_fee);
        io.setFee_type(feeTypeParam.equals("1") ? true : false);
        io.setDescription(description);
        io.setContact(contact);
        io.setAccount_sold_id(acc.getId());
        io.setAccount_buy_id(0);
        io.setHide_description(hideDescription);
        io.setPublic_status(publicStatusParam.equals("1") ? true : false);
        io.setCreated_at(createdDate);
        io.setUpdated_at(createdDate);
        msd.InsertProduct(io);
        String link = "http://localhost:9999/DemoSWP391/detailproduct?id=" + msd.GetLastID();
        msd.UpDateLink(link, msd.GetLastID());
        TransactionHistoryDAO th = new TransactionHistoryDAO();
        Timestamp createdTimestamp = Timestamp.valueOf(createdDate);
// Sử dụng createdTimestamp thay vì createdDate trong hàm InsertIntoTransactionHistory
        th.InsertIntoTransactionHistory(io.getAccount_sold_id(), intermediary_fee, false, "Collect fees for making intermediary requests", acc.getName(), createdTimestamp, createdTimestamp);

        response.sendRedirect("mysale");
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
