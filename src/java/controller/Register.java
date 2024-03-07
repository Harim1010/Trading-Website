/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.Captcha;
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
import model.Email;
import model.SoNgauNhien;

/**
 *
 * @author Xuanphuc
 */
public class Register extends HttpServlet {

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
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatpassword = request.getParameter("repeatpassword");
        String email = request.getParameter("email");
        String captcha = request.getParameter("captchaInput1");
        AccountDAO acc = new AccountDAO();
        Account newacc = new Account(email, username, password, name, 2);
        HttpSession session = request.getSession();
        String captchagene = (String) session.getAttribute("captchaText");
        String baoLoi = "";
        try {
            if (acc.kiemTraTenDangNhap(username)) {
                baoLoi = "The username is exist! Please choose another name.\n";
            }
            if (!password.equals(repeatpassword)) {
                baoLoi = "Confirm password incorrect!\n";
            }
            if(!captcha.equals(captchagene)){
                baoLoi += "Wrong captcha\n";
            }
            if(acc.isValidEmailAddress(email) == false){
                baoLoi = "Invalid email (abc@example.com)\n";
            }
            if (baoLoi.length() > 0) {
                request.setAttribute("notice", baoLoi);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                session.setAttribute("acc", newacc);
                SoNgauNhien random = new SoNgauNhien();
                String otp = random.maOTP();
                Email e = new Email();
                e.sendEmail(email, "Xác thực tài khoản tại Sulk Market", acc.getNoiDung(newacc, otp));
                session.setAttribute("hoan", otp);
                //session.setMaxInactiveInterval(300);
                request.getRequestDispatcher("OTP.jsp").forward(request, response);
            }
            //RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            //rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
    //CODE CAPTCHA
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
//
//    private String generateCaptchaText() {
//        int length = 6;
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        Random random = new Random();
//        StringBuilder captchaText = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            captchaText.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return captchaText.toString();
//    }
//
//    private BufferedImage generateCaptchaImage(String text) {
//    int width = 200;
//    int height = 50;
//    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//    Graphics2D g = (Graphics2D) image.getGraphics();
//
//    g.setColor(Color.white);
//    g.fillRect(0, 0, width, height);
//
//    for (int i = 0; i < 10; i++) {
//        g.setColor(getRandomColor());
//        int x1 = (int) (Math.random() * width);
//        int y1 = (int) (Math.random() * height);
//        int x2 = (int) (Math.random() * width);
//        int y2 = (int) (Math.random() * height);
//        g.drawLine(x1, y1, x2, y2);
//    }
//
//    g.setFont(new Font("Arial", Font.BOLD, 30));
//    for (int i = 0; i < text.length(); i++) {
//        g.setColor(getRandomColor());
//        int x = 20 + i * 30;
//        int y = 40 - (int) (Math.random() * 10);
//        g.drawString(String.valueOf(text.charAt(i)), x, y);
//    }
//
//    return image;
//}
//
//    private Color getRandomColor() {
//        Random random = new Random();
//        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
//    }

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
