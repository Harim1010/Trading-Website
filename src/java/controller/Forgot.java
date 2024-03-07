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
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;

/**
 *
 * @author Xuanphuc
 */
public class Forgot extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        String username = request.getParameter("username");
        int otpvalue = 0;
        AccountDAO acd = new AccountDAO();
        Account acc = acd.FindAccountByUserName(username);
        
        if(acc != null){
                Random rand = new Random();
                otpvalue = rand.nextInt(1255650);
                String to = acc.getEmail();
                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true");
                Session sessions = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("hoandv2003002@gmail.com", "ensa hzcj ybrv oxen");
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(sessions);
                    message.setFrom(new InternetAddress(acc.getEmail()));// change accordingly
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("[Thông báo]- Lấy lại mật khẩu qua email!", "UTF-8");
                    message.setText("Bạn hoặc ai đó đã yêu cầu lấy lại mật khẩu cho tài khoản "+acc.getName()
                            +"\nMã otp để đổi mật khẩu của bạn:\n" + otpvalue
                            + "\nVui lòng không để lộ mã OTP để tránh mất tài khoản!", "UTF-8");
                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("message", "OTP has been sent to your Email address");
                mySession.setAttribute("otp", otpvalue);
//                mysession.setMaxInactiveInterval(300);
                mySession.setAttribute("username", username);
                request.getRequestDispatcher("EnterOTP.jsp").forward(request, response);
            }else{
               request.setAttribute("err", "Can't find username");
               request.getRequestDispatcher("forgot.jsp").forward(request, response);
        }
    }
}
