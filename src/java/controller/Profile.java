/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Xuanphuc
 */
public class Profile extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
//        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
//        String email = req.getParameter("email");

        AccountDAO acc = new AccountDAO();
        acc.updateUserInformation(accountId, fullname);
        Account accountItem = acc.GetAccountByID(accountId);
        req.setAttribute("accountItem", accountItem);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String accountId = req.getParameter("accountId");
        System.out.println("accountId:" + accountId);
        AccountDAO acc = new AccountDAO();
        Account accountItem = acc.GetAccountByID(Integer.parseInt(accountId));
        req.setAttribute("accountItem", accountItem);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

}
