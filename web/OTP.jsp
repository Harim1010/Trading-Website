<%-- 
    Document   : OTP
    Created on : Jan 22, 2024, 9:16:35 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/register.css">
        <script src="./assets/js/script.js" defer></script>
    </head>
    <body>
        <div class="otpbox login-form">
            <div class="wrap" id="myForm">
                <header>
                    <i class=""></i>
                </header>
                <h4>Enter OTP Code</h4>
                <form class="otpform" action="otp1" method="post">
                    <div class="input-field">
                        <input type="text" name="otp1">
                        <input type="text" name="otp2" disabled>
                        <input type="text" name="otp3" disabled>
                        <input type="text" name="otp4" disabled>
                        <input type="text" name="otp5" disabled>
                        <input type="text" name="otp6" disabled>
                    </div>
                    <div onclick="verifyForm()"><a href="otp"><button class="active" type="submit">Verify OTP</button></a></div>
                    <div>${requestScope.error}</div>
                </form>
            </div>
        </div>  
    </body>
    <script>
        function verifyForm(){
            return confirm("Register successfully!");
        }
    </script>
</html> 