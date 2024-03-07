<%-- 
    Document   : forgot
    Created on : Jan 16, 2024, 10:30:50 AM
    Author     : Xuanphuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/forgot.css">
        <title>Forgot Password</title>
    </head>
   
</head>
    <body>
        <div class="login-wrap">
            <div class="header">Forgot Password</div>
            <div class="login-form">
                <form action="forgot" method="post">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" type="text" name="username" class="input" placeholder="Your username">
                    </div>
                     <div class="group">
                         <h3 style="color: red">${requestScope.err}</h3>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Send me OTP">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="login.jsp">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
