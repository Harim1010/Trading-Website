<%-- 
    Document   : Deposit1
    Created on : Feb 28, 2024, 10:11:16 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <div style="background-color: #3498db;padding-top: 20px;padding-bottom: 30px">
        <form action="vnpayajax" method="post" id="deposit_request">
            <h2 style="color: white">MAKE A NEW DEPOSIT REQUEST</h2>
            <c:if test="${sessionScope.errorDeposit != null}">
            <h4 style="color:red">Số tiền không được nhỏ hơn 10,000 VNĐ</h4>
            </c:if>       
            <label>Amount (VND):</label>
            <input id="amount" type="text" name="amount" placeholder="Amount to deposit (Minimum 10,000 VND)" oninput="formatCurrency(this)" required>
            <textarea id = "number_text" type="text" autocomplete="off" contenteditable="true" readonly style="line-height: 0.5;border: none;resize: none"></textarea>
            <label>Transaction method:</label>
            <input type="radio" id="money1" value="" name="deposit_method" required checked>
            <label id="lb1">PAYMENT GATE (ADDITIONAL SERVICE FEE IF ANY)</label>
            <label id="des">Description:</label>     
            <textarea type="text" id="description" name="description" class="description" placeholder="Description for this transaction" style="overflow-y: auto;font-family: sans-serif"></textarea>
            <button type="submit">Submit</button>
            <button type="button" onclick="back()">Cancel</button>
        </form>
        </div>
        <style>
            body {
                background-color: #3498db; /* Set the background color of the body to sea blue */
                margin: 0; /* Remove default body margin */
                font-family: Arial, sans-serif; /* Set a font-family for better readability */
            }

            #number_text:focus {
                outline:none;
            }

            #money1 {
                display: none;
            }


            form {
                max-width: 800px;
                margin: 0 auto;
                padding: 50px;
                background-color: #fff; /* Set the background color of the form to white */
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow for depth */
                margin-top: 90px;
                margin-bottom: 90px;
            }

            h2 {
                text-align: center;
                font-size: 1.5em;
                font-weight: bold;
                color: #ffffff; /* Màu chữ trắng, có thể điều chỉnh */
                margin-bottom: 20px; /* Khoảng cách dưới h1 */
                border: 2px solid #3498db; /* Border bo quanh h1 với màu xanh, có thể điều chỉnh */
                padding: 10px; /* Khoảng cách giữa border và chữ */
                background-color: #3498db;
                border-radius: 8px;
            }

            h4 {
                font-weight: bold;
                text-align: center;
                font-size: 15px;
                font-family: Arial, sans-serif;
            }

            label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
                color: #333; /* Set the label text color */
            }

            input {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 10px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            #lb1 {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 10px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-align: center;
                cursor: pointer;
            }

            button {
                background-color: #3498db;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="radio"]:checked + label {
                background-color: #35bced;
                color: aliceblue;
                border-color: rgb(60, 138, 190);
            }

            button:hover {
                background-color: #007bb5;
            }

            button[type="submit"] {
                background-color: #00cc00;
            }

            button[type="submit"]:hover {
                background-color: #008c00;
            }

            button[type="button"] {
                background-color: #e74c3c;
            }

            button[type="button"]:hover {
                background-color: #c0392b;
            }
            label {
                width: 100%;
            }
            textarea{
                width: 100%;
            }
            #description {
                height: 100px;
            }


        </style>    
    </body>
    <%@include file="Component/footer.jsp" %>
    <script>
        function formatCurrency(input) {
            var value = input.value;
            value = value.replace(/[^0-9]/g, '');
            value = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value);
            input.value = value;
        }
    </script>
    <script>
        function back() {
            window.location.href = "home.jsp";
        }
        
        document.getElementById("deposit_request").addEventListener("submit", function (event) {
            var priceInput = document.getElementById("amount").value;
                priceInput = priceInput.substring(0,priceInput.length() - 2);
                priceInput = priceInput.replace(".", "");
            if (priceInput < 10000) {
               event.preventDefault();
               showToast("Amount cannot be less than 10,000!");
            }

        });

        function showToast(message) {
            Toastify({
                text: message,
                duration: 3000,
                close: true,
                gravity: "top",
                position: 'right',
                backgroundColor: "linear-gradient(to right, #ff416c, #ff4b2b)",
                stopOnFocus: true,
            }
            ).showToast();
        }
    </script>
</html>

