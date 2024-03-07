/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import model.Wallet;

/**
 *
 * @author Xuanphuc
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Captcha captcha = new Captcha();
        String captchaText = captcha.generateCaptchaText();
        HttpSession session = request.getSession();
        session.setAttribute("captchaText", captchaText);
        BufferedImage image = captcha.generateCaptchaImage(captchaText);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
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
        PrintWriter out = response.getWriter();
        AccountDAO accdao = new AccountDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        String captcha = request.getParameter("captchaInput");
        String captchagene = (String) ss.getAttribute("captchaText");

        Cookie cu = new Cookie("cu", username);
        Cookie cp = new Cookie("cp", password);
        Cookie cr = new Cookie("cr", remember);
        if (remember != null) {
            cu.setMaxAge(60 * 60 * 24 * 7);
            cp.setMaxAge(60 * 60 * 24 * 7);
            cr.setMaxAge(60 * 60 * 24 * 7);
        } else {
            cu.setMaxAge(60 * 60 * 24 * 7);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);
        int IDacc = accdao.UserAcountExist(username, accdao.toSHA1(password));
        Account acc = accdao.GetAccountByID(IDacc);
        if (IDacc != 0 && acc.getId() != 0 && captcha.equals(captchagene)) {
            WalletDAO wdb = new WalletDAO();
            Wallet w = wdb.GetWalletByUserID(acc.getId());
            ss.setAttribute("walletCurrent", w);
            ss.setAttribute("account", acc);
            ss.setAttribute("wallet_id", acc.getId());
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            String err = "";
            if (!captcha.equals(captchagene)) {
                err = "Invalid captcha";
                request.setAttribute("err", err);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }        
            err = "Wrong Username or password";
            request.setAttribute("err", err);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
