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
import model.Wallet;
import model.Withdrawal;

/**
 *
 * @author Admin
 */
@WebServlet(name = "WithdrawHandle", urlPatterns = {"/withdrawHandle"})
public class WithdrawHandle extends HttpServlet {

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
        HttpSession ss = request.getSession();
        String xWithdrawID = request.getParameter("withdrawID");
        int withdrawID = Integer.parseInt(xWithdrawID);
        WithdrawalDAO wdb = new WithdrawalDAO();
        Withdrawal w = wdb.GetWithdrawalByID(withdrawID);

        WalletDAO wadb = new WalletDAO();
        Wallet wa = wadb.GetWalletByWithdrawal(w);

        String action = request.getParameter("action");
        if (action.equals("accept")) {
            double newBalance = wa.getBalance() - w.getAmount();
            wa.setBalance(newBalance); //tru tien trong TK, coi nhu da rut
            wadb.UpdateWalletByID(wa);
            w.setStatus("Finished");
        } else if (action.equals("deny")) {
            w.setStatus("Denied");
        }
        Date currentDate = new Date();
        Timestamp time = new Timestamp(currentDate.getTime());
        w.setUpdate_datetime(time);
        wdb.UpdateWithdrawalByID2(w);
        
        //update balance tren thanh header.jsp
        Wallet wa2 = (Wallet) ss.getAttribute("walletCurrent");
        if(wa.getWallet_id() == wa2.getWallet_id()){
            ss.setAttribute("walletCurrent", wa);
        }

        List<Withdrawal> list = wdb.GetAllWithdrawal();
        ss.setAttribute("listWithdraw", list);
        request.getRequestDispatcher("WithdrawalListAdmin.jsp").forward(request, response);

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
        WithdrawalDAO wdb = new WithdrawalDAO();
        Withdrawal w = (Withdrawal) ss.getAttribute("withdrawCurrent");

        WalletDAO wadb = new WalletDAO();
        Wallet wa = wadb.GetWalletByWithdrawal(w);

        String action = request.getParameter("withdrawAction");
        if (action.equals("accept")) {
            wa.setBalance(wa.getBalance() - w.getAmount()); //tru tien trong TK, coi nhu da rut
            wadb.UpdateWalletByID(wa);
            w.setStatus("Finished");
        } else if (action.equals("deny")) {
            w.setStatus("Denied");
        } else if (action.equals("error")) {
            w.setStatus("Error");
        }
        Date currentDate = new Date();
        Timestamp time = new Timestamp(currentDate.getTime());
        w.setUpdate_datetime(time);
        wdb.UpdateWithdrawalByID2(w);
        
        //update balance tren thanh header.jsp
        Wallet wa2 = (Wallet) ss.getAttribute("walletCurrent");
        if(wa.getWallet_id() == wa2.getWallet_id()){
            ss.setAttribute("walletCurrent", wa);
        }

        List<Withdrawal> list = wdb.GetAllWithdrawal();
        ss.setAttribute("listWithdraw", list);
        request.getRequestDispatcher("WithdrawalListAdmin.jsp").forward(request, response);
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
