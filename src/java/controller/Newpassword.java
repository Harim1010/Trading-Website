/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import model.Account;

/**
 *
 * @author Xuanphuc
 */
public class Newpassword extends HttpServlet {
   
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ss = request.getSession();
        
        Boolean check = false;
        String newpass1 = request.getParameter("newpassword1");
        String newpass2 = request.getParameter("newpassword2");       
        String captcha = request.getParameter("captchaInput");
        String captchagene = (String) ss.getAttribute("captchaText"); 
        String username=(String) ss.getAttribute("username");
        
        AccountDAO acd = new AccountDAO();
        Account acc = acd.FindAccountByUserName(username);
        int id = acc.getId();
        
        if(newpass1 != null && newpass2 != null && newpass1.equals(newpass2) && captcha.equals(captchagene)){
        acd.ForgotPassword(id, acd.toSHA1(newpass1));
        request.getRequestDispatcher("login.jsp").forward(request, response);
        check = true;
        request.setAttribute("check", check);
        }else{
            String err = "";
            if(!captcha.equals(captchagene)){
                err = "Invalid captcha";
                request.setAttribute("err",err);
                request.getRequestDispatcher("Newpassword.jsp").forward(request, response);
            }
            err = "Confirm password incorrect";
         request.setAttribute("err",err);
         request.getRequestDispatcher("Newpassword.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
