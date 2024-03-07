<%-- 
    Document   : OPTvalue
    Created on : Jan 20, 2024, 3:45:53 PM
    Author     : Xuanphuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/OTP.css">
        <title>OTP Page</title>
    </head>

    <body>
        <div class="otp-wrap">
            <div class="header">OTP</div>
            <div class="otp-form">
                <form action="otp" method="post">
                    <div class="group">
                        <label for="user" class="label">Enter OTP</label>
                        <input id="user" type="text" name="otp" class="input" placeholder="Your OTP" inputmode="numeric">
                    </div>
                    <div class="group">
                        <label for="user" class="label" style="color: red">${requestScope.message}</label>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Verify OTP">
                    </div>
                    <div class="group">
                        <label for="user" class="resend" >resend OTP</label>
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="login.jsp">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var resendButton = document.querySelector('.resend');

                if (resendButton) {
                    resendButton.addEventListener('click', function () {
                        location.reload();
                    });
                }
            });
        </script>    
    </body>
</html>
