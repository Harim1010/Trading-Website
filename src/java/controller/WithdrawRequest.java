/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.WalletDAO;
import DAO.WithdrawalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.Wallet;
import model.Withdrawal;

/**
 *
 * @author Admin
 */
@WebServlet(name = "WithdrawRequest", urlPatterns = {"/withdraw"})
public class WithdrawRequest extends HttpServlet {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        HttpSession ss = request.getSession();
        Wallet wa = (Wallet) ss.getAttribute("walletCurrent");

        String xAmount = request.getParameter("amount");
        xAmount = xAmount.substring(0, xAmount.length() - 2);
        xAmount = xAmount.replace(".", "");
        double amount = Double.parseDouble(xAmount);
        String bankName = request.getParameter("bankName");
        String bankUser = request.getParameter("bankUser");
        String bankNumber = request.getParameter("bankNumber");
        String next = "withdrawalList.jsp";
        if (amount > wa.getBalance()) {
            ss.setAttribute("errorWithdraw", "Số tiền bạn vừa yêu cầu đã vượt quá số tiền trong tài khoản, vui lòng thử lại.");
            next = "WithdrawalRequest.jsp";
        }
        if (amount <= 10000) {
            ss.setAttribute("errorWithdraw", "Số tiền yêu cầu rút phải trên 10.000 vnđ, vui lòng thử lại.");
            next = "WithdrawalRequest.jsp";
        } 
        if(amount <= wa.getBalance() && amount > 10000){
            Date currentDate = new Date();
            Timestamp time = new Timestamp(currentDate.getTime());
            WithdrawalDAO wdb = new WithdrawalDAO();
            Withdrawal w = new Withdrawal(wa.getWallet_id(), amount, time, "In process", bankUser, bankNumber, bankName, time);
            wdb.InsertWithdrawal(w);
            double newBalance = wa.getBalance() - w.getAmount();
            wa.setBalance(newBalance); //tru tien trong TK, tam thoi giu tien cho xu ly
            WalletDAO wadb = new WalletDAO();
            wadb.UpdateWalletByID(wa);

            //update balance tren thanh header.jsp
            ss.setAttribute("walletCurrent", wa);
            List<Withdrawal> list = wdb.GetAllWithdrawalByWalletID(wa.getWallet_id());
            ss.setAttribute("listWithdraw", list);
        }
        scheduler.schedule(() -> {
            ss.removeAttribute("errorWithdraw");
        }, 2, TimeUnit.SECONDS);

        request.getRequestDispatcher(next).forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
