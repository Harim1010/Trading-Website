<%-- 
    Document   : resetpassword
    Created on : Jan 15, 2024, 2:57:16 PM
    Author     : Xuanphuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/newpassword.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>New Password</title>

    </head>

    <body>
        <div class="newpass-wrap">
            <div class="header">New password</div>
            <div class="newpass-form">
                <form action="reset" method="post">
                    <div class="group">
                        <label for="user" class="label">Enter new password</label>
                        <input id="user" type="password" name="newpassword1" class="input" placeholder="new password">
                    </div>

                    <div class="group">
                        <label for="user" class="label">Confirm new password</label>
                        <input id="user" type="password" name="newpassword2" class="input" placeholder=" confirm new password">
                    </div>

                    <div class="group group-captcha">
                        <img src="reset" alt="CAPTCHA">
                        <div class="captcha-refresh" onclick="refresh()">
                            <i class="fa fa-refresh fa-spin"></i>
                        </div>
                        <input class="input" type="text" name="captchaInput" placeholder="Nhập CAPTCHA" required>
                    </div>

                    <div class="group">
                        <label for="user" class="label" style="color: red" >${requestScope.err}</label>
                    </div>  

                    <div class="group">
                        <input type="submit" class="button" value="Reset password" >
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="login.jsp">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="captcha.js"></script>
        <script>
                            function refresh() {
                                $.ajax({
                                    type: "GET",
                                    url: "/DemoSWP391/login",
                                    success: function () {
                                        console.log("Captcha refreshed successfully");
                                        var captchaImage = document.querySelector('.group-captcha img');

                                        var timestamp = new Date().getTime();

                                        captchaImage.src = '/DemoSWP391/login?' + timestamp;
                                    },
                                    error: function (xhr, status, error) {
                                        console.error("Error refreshing captcha:", error);
                                    }
                                });
                            }
        </script>

    </body>
</html>
