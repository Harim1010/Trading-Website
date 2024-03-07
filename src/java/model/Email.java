package model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class Email {

    static final String from = "hoandv2003002@gmail.com";
    static final String password = "ensa hzcj ybrv oxen";

    public boolean sendEmail(String to, String tieude, String noidung) {

        // email: hoandv2003002@gmail.com
        // pass: ensa hzcj ybrv oxen
        //final String from = "hoandv2003002@gmail.com";
        //final String password = "ensa hzcj ybrv oxen";
        // Properties: khai bao cac thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
        props.put("mail.smtp.port", "587"); //TLS 587 (google) SSL 465
        props.put("mail.smtp.auth", "true"); //su dung cai host smtp co phai dang nhap hay k -> khai bao true
        props.put("mail.smtp.starttls.enable", "true");

        //create Authenticator
        // la 1 cai pass co san de bo vao du an khi lay ra phien lam viec de gui mail
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // tao ra 1 phien lam viec
        // session cua email
        Session session = Session.getInstance(props, auth);

        // Gui email
        //final String to = "hoan1102003@gmail.com";
        //Message de gui mail
        MimeMessage mess = new MimeMessage(session);
        try {
            // kieu noi dung
            mess.addHeader("Content-type", "text/HTML; charset = UTF-8");
            // nguoi gui
            mess.setFrom(from);
            // nguoi nhan
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // tieu de email
            mess.setSubject(tieude,"UTF-8");
            // quy dinh ngay gui di
            mess.setSentDate(new Date());
            mess.setContent(noidung, "text/html;charset = UTF-8");
            Transport.send(mess);
            System.out.println("Gui thanh cong");
            return true;
        } catch (Exception ex) {
            System.out.println("Loi trong qua trinh gui email");
            ex.printStackTrace();
            return false;
        }
    }
    

    public static void main(String[] args) {
        // co the dung vong for de gui email
        //Email e = new Email();
        //e.sendEmail("hoan1102003@gmail.com","Tiêu đề ", "Hoang gaygally khoc !");
    }

}
