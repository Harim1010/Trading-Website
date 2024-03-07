/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import java.time.*;
import model.*;
import DAO.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Xuanphuc
 */
public class MySale extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ss = request.getSession();
        Account acc = (Account) ss.getAttribute("account");
        MySaleDao msd = new MySaleDao();
        List<Intermediary_order> listsalepro = msd.GetAllIntermediary_seller(acc.getId());
        if (acc != null) {
            request.setAttribute("listsalepro", listsalepro);
            request.getRequestDispatcher("MySale.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String Code = request.getParameter("code");
        String Title = request.getParameter("title");
        String Orderstatus = request.getParameter("orderstatus");
        String Productbuyer = request.getParameter("productbuyer");
        String Contact = request.getParameter("contact");
        String Publicstatus = request.getParameter("publicstatus");

        String Pricefrom = request.getParameter("pricefrom");
        String Priceto = request.getParameter("priceto");
        String Paytransaction = request.getParameter("paytransaction");


        String Createdfrom = request.getParameter("createdfrom");
        String CreatedTo = request.getParameter("createdto");
        String Updatedfrom = request.getParameter("updatedfrom");
        String Updatedto = request.getParameter("updatedto");
        HttpSession ss = request.getSession();
        Account acc =(Account) ss.getAttribute("account");
        MySaleDao msd = new MySaleDao();
        List<Intermediary_order> listsalepro =  msd.GetIoByFilter(Code, Orderstatus, Title, Contact, Publicstatus, Pricefrom, Priceto, Paytransaction, Createdfrom, CreatedTo, Updatedfrom, Updatedto,acc.getId());
        request.setAttribute("listsalepro", listsalepro);
        request.getRequestDispatcher("MySale.jsp").forward(request, response);
}
}