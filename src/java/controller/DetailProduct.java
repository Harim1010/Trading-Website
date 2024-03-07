/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MySaleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.*;

/**
 *
 * @author Xuanphuc
 */
public class DetailProduct extends HttpServlet {

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
            out.println("<title>Servlet DetailProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProduct at " + request.getContextPath() + "</h1>");
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
        MySaleDao msd = new MySaleDao();
        Intermediary_order io = msd.GetOrderByID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("io", io);
        request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
        Double price = Double.parseDouble(request.getParameter("price"));
        String feetype = request.getParameter("feetype");
        String descrip = request.getParameter("description");
        String orderstatus = request.getParameter("ttgd");
        String contact = request.getParameter("contact");
        String hide = request.getParameter("hide");
        String publicstatus = request.getParameter("publicstatus");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String updatedtime = dateFormat.format(currentDate);
        MySaleDao msd = new MySaleDao();
        Intermediary_order io = msd.GetOrderByID(Integer.parseInt(request.getParameter("id")));
        io.setTitle(title);
        io.setPrice(price);
        io.setFee_type(feetype.equals("1")?true:false);
        io.setDescription(descrip);
        io.setContact(contact);
        io.setHide_description(hide);
        io.setPublic_status(publicstatus.equals("1")?true:false);
        io.setUpdated_at(updatedtime);
        msd.UpdateProduct(io);
        request.setAttribute("io", io);
        request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
