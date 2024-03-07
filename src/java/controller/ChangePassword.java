package controller;

import DAO.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Change password
        HttpSession ss = req.getSession();
        AccountDAO dao = new AccountDAO();
        String captchagene = (String) ss.getAttribute("captchaText");
        String captcha = req.getParameter("captchaInput");
        String curPass = req.getParameter("curPass");
        String newPass = req.getParameter("newPass");
        String retypePass = req.getParameter("retypePass");
        String err = "";
        int accountId = 0;
        if (req.getParameter("accountId") != null) {
            accountId = Integer.parseInt(req.getParameter("accountId"));
        }
        AccountDAO acc = new AccountDAO();
        Account accountItem = acc.GetAccountByID(accountId);
        req.setAttribute("accountItem", accountItem);

        if (captcha.equals(captchagene) && dao.toSHA1(curPass).equals(accountItem.getPassword()) && newPass.equals(retypePass) && !curPass.equals(newPass)) {
            acc.updateUserPassword(accountId, dao.toSHA1(newPass));
            err = "Change password successfully!";
            req.setAttribute("err", err);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } else {
            if (!captcha.equals(captchagene)) {
                err = "Invalid captcha";
                req.setAttribute("err", err);
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            } else if (!dao.toSHA1(curPass).equals(accountItem.getPassword())) {
                err = "Current password not valid";
                req.setAttribute("err", err);
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            } else if (!newPass.equals(retypePass)) {
                err = "Retype Password not valid";
                req.setAttribute("err", err);
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            } else if (curPass.equals(newPass)){
                err = "The new password must not be the same as the old password";
                req.setAttribute("err", err);
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            }
        }
    }

}
