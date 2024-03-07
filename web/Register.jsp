<%-- 
    Document   : register
    Created on : Jan 19, 2024, 3:03:48 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/login.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Login Form</title>
    </head>
    <body>
        <div class="login-wrap" style="height: 700px">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in"><label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up" checked><label for="tab-2" class="tab">Sign Up</label>
                <div class="login-form">
                    <form action="register" method="post">
                        <div class="sign-up-htm">
                            <div class="group">
                                <label for="user" class="label">Name</label>
                                <input id="user" type="text" class="input" name="name" required="">
                            </div>
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name="username" required="">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name="password" required="">
                            </div>
                            <div class="group">
                                <label for="pass" class="label" >Repeat Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name="repeatpassword" required="">
                            </div>
                            <div class="group">
                                <label for="pass" class="label" >Email Address</label>
                                <input id="pass" type="text" class="input" name="email" required="">
                            </div>
                            <div class="group" style="text-align: center">
                                <label style="color: red">${requestScope.notice}</label>
                            </div>
                            <div class="group group-captcha">
                                <img src="login" alt="CAPTCHA">
                                <div class="captcha-refresh" onclick="refresh2()">
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                                <input class="input" type="text" name="captchaInput1" placeholder="Nhập CAPTCHA" required>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign Up" style="cursor: pointer" onclick="openForm()">
                            </div>
                            <div class="foot-lnk">
                                <a href="login.jsp">Already Member?</a> 
                            </div>
                        </div>
                    </form>
                    <form action="login" method="get">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" data-type="password">
                            </div>
                            <div class="group">
                                <input id="check" type="checkbox" class="check">
                                <label for="check"><span class="icon"></span> Keep me Signed in</label>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <div class="group">
                                <h3 style="color: red; font:600 16px/18px 'Open Sans',sans-serif;"></h3>
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="forgot.jsp">Forgot Password?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        function openForm() {
            return confirm("Vui lòng truy cập vào email để kích hoạt tài khoản ");
        }
        function submitForm() {
            window.location.href = "${pageContext.request.contextPath}/OTP.jsp";
            return false;
        }
        function refresh2() {
            $.ajax({
                type: "GET",
                url: "/DemoSWP391/register", // 
                success: function () {
                    console.log("Captcha refreshed successfully");
                    var captchaImage = document.querySelector('.group-captcha img');
                    var timestamp = new Date().getTime();
                    captchaImage.src = '/DemoSWP391/register?' + timestamp;
                },
                error: function (xhr, status, error) {
                    console.error("Error refreshing captcha:", error);
                }
            });
        }
    </script>
</html>