<%-- 
    Document   : login.jsp
    Created on : Jan 13, 2024, 11:55:39 AM
    Author     : Admin
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
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                <div class="login-form">
                    <c:set var="cookie" value="${pageContext.request.cookies}"/>
                    <form action="login" method="post">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" name="username" class="input" value="${cookie.cu.value}">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" name="password" class="input" data-type="password"  value="${cookie.cp.value}">
                            </div>

                            <div class="group group-captcha">
                                <img src="login" alt="CAPTCHA">
                                <div class="captcha-refresh" onclick="refresh1()">
                                    <i class="fa fa-refresh fa-spin"></i>
                                </div>
                                <input class="input" type="text" name="captchaInput" placeholder="Nhập CAPTCHA" required>
                            </div>

                            <div class="group">
                                <input id="check" type="checkbox" class="check" name="remember" ${(cookie.cr != null?'checked':'')}>
                                <label for="check"><span class="icon"></span> Remember me</label>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <div class="group">
                                <h3 style="color: red; font:600 16px/18px 'Open Sans',sans-serif;"> ${requestScope.err}</h3>
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="forgot.jsp">Forgot Password?</a>
                            </div>
                        </div>
                    </form>
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
                            <div class="group">
                                <label style="color: red;text-align: center">${requestScope.notice}</label>
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
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="captcha.js"></script>
        <script>
                                    function refresh1() {
                                        $.ajax({
                                            type: "GET",
                                            url: "/DemoSWP391/login",
                                            success: function () {
                                                console.log("Captcha refreshed successfully");
                                                var captchaImage = document.querySelector('.group-captcha img');
                                                var captchaInput = document.querySelector('.group-captcha input[name="captchaInput"]');
                                                var timestamp = new Date().getTime();

                                                captchaImage.src = '/DemoSWP391/login?' + timestamp;
                                                captchaInput.value = "";
                                            },
                                            error: function (xhr, status, error) {
                                                console.error("Error refreshing captcha:", error);
                                            }
                                        });
                                    }
                                    const urlParams = new URLSearchParams(window.location.search);
                                    const uid = urlParams.get('id');
                                    const id = parseInt(uid);
                                    if (id === 0) {
                                        var myCheckbox = document.getElementById('tab-2');
                                        var myCheckbox2 = document.getElementById('tab-1');
                                        // Thêm thuộc tính checked
                                        myCheckbox.checked = true;
                                        myCheckbox2.checked = false;
                                    }
                                    function openForm() {
                                        return confirm("Vui lòng truy cập vào email để kích hoạt tài khoản ")
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

        <!-- JS CHO NÚT REFRESH -->
        <!--        <script>
                    function refresh2() {
                        $.ajax({
                            type: "GET",
                            url: "/DemoSWP391/register", 
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
                </script>-->
    </body>
</html>
