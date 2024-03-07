/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import model.Account;
import model.trans_history;

/**
 *
 * @author caoth
 */
public class detailsTransHis extends HttpServlet {

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
            String id = request.getParameter("id");
            int iId = Integer.parseInt(id);
            // Thực hiện logic của bạn ở đây, ví dụ truy vấn CSDL
            // Giả sử bạn đã lấy được thông tin và muốn trả về dữ liệu
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            AccountDAO dao = new AccountDAO();
            trans_history transaction = dao.getDetailsTransById(iId); // Assuming you want to fetch by ID 0, but typically you'd get this ID from a request parameter

            request.setAttribute("listB", transaction); // Setting the transaction history object as an attribute
            request.getRequestDispatcher("TransHistory.jsp").forward(request, response); // Forwarding to JSP
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
        String id = request.getParameter("id");
        int transactionId = Integer.parseInt(id);
        
        AccountDAO dao = new AccountDAO();
        trans_history transaction = dao.getDetailsTransById(transactionId);

        // Trả về dữ liệu dưới dạng văn bản
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Chuyển đổi dữ liệu thành dạng văn bản và gửi lại cho client
        response.getWriter().print(" <tr>\n"
                + "                                                <td style=\"padding-left: 40px\">\n"
                + "                                                    Money\n"
                + "                                                </td>\n"
                + "                                                <td><div style=\"width: 100px;\"><input type=\"text\" value= \""+transaction.getMoney()+"\" readonly style=\"background-color:#cccccc; width: 615px \" ></div></td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td style=\"padding-left: 40px\">\n"
                + "                                                    Transaction type\n"
                + "                                                </td>\n"
                + "                                                <td class=\"button\">\n"
                + "                                                    <div style=\"width: 310px\">\n"
                + "                                                        <input type=\"radio\" id=\"money1\" value=\"\" name=\"+\" required \">\n"
                + "                                                        <label for=\"money1\">-</label>\n"
                + "                                                    </div>\n"
                + "                                                </td>\n"
                + "                                                <td class=\"button\">\n"
                + "                                                    <div style=\"width: 310px\">\n"
                + "                                                        <input type=\"radio\" id=\"money2\" value=\"chuyenkhoan\" name=\"+\" required checked>\n"
                + "                                                        <label for=\"money2\">+</label>\n"
                + "                                                    </div>\n"
                + "                                                </td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td style=\"padding-left: 40px\">\n"
                + "                                                    Processed\n"
                + "                                                </td>\n"
                + "                                                <td class=\"button\">\n"
                + "                                                    <div style=\"width: 310px\">\n"
                + "                                                        <input type=\"radio\" id=\"chuaxuly\" value=\"\" name=\"xuly\" readonly required style=\"\">\n"
                + "                                                        <label for=\"chuaxuly\">Unprocessed</label>\n"
                + "                                                    </div>\n"
                + "                                                </td>\n"
                + "                                                <td class=\"button\">\n"
                + "                                                    <div style=\"width: 310px\">\n"
                + "                                                        <input type=\"radio\" id=\"daxuly\" value=\"\" name=\"xuly\" readonly required checked \">\n"
                + "                                                        <label for=\"daxuly\"  >Processed</label>\n"
                + "                                                    </div>\n"
                + "                                                </td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td style=\"padding-left: 40px\">\n"
                + "                                                    Note\n"
                + "                                                </td>\n"
                + "                                                <td><div style=\"width: 100px;\"><input type=\"text\" value= \""+transaction.getNote()+"\" readonly style=\"background-color:#cccccc;width: 615px \" ></div></td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td style=\"padding-left: 40px\">\n"
                + "                                                    Transaction creator\n"
                + "                                                </td>\n"
                + "                                                <td><div style=\"width: 100px;\"><input type=\"text\" value= \""+transaction.getCreate_by()+"\" readonly style=\"background-color:#cccccc; width: 615px\" ></div></td>\n"
                + "                                            </tr>");
        
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
