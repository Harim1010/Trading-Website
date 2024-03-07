<%-- 
    Document   : WithdrawalRequest
    Created on : Feb 4, 2024, 1:18:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal Request</title>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <form action="withdraw" method="post">
            <h2>MAKE A NEW WITHDRAWAL REQUEST</h2>
            <h4 style="color:red">${sessionScope.errorWithdraw}</h4>
            <label>Amount of withdraw money:</label>
            <input type="text" name="amount" placeholder="100.000" oninput="formatCurrency(this)" required>

            <label>Bank Name:</label>
            <input type="text" name="bankName" placeholder="VD: MB Bank (MBB), Vietcombank (VCB),..." required>

            <label>Bank User:</label>
            <input type="text" name="bankUser" required>

            <label>Bank Number:</label>
            <input type="text" name="bankNumber" required>

            <button type="submit" onclick="return confirmWithdrawRequest()">Submit</button>
            <button type="button" onclick="back()">Cancel</button>
        </form>

        <style>
            body {
                background-color: #3498db; /* Set the background color of the body to sea blue */
                margin: 0; /* Remove default body margin */
                font-family: Arial, sans-serif; /* Set a font-family for better readability */
            }

            form {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff; /* Set the background color of the form to white */
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow for depth */
                margin-top: 120px;
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

            button {
                background-color: #3498db;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
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

        </style>    
    </body>
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
            window.location.href = "withdrawalList.jsp";
        }
    </script>
    <script>
        function confirmWithdrawRequest() {
            return confirm("Xác nhận đúng thông tin TK Ngân hàng? Nếu thông tin bị sai, admin hoàn toàn không chịu trách nhiệm");
        }
    </script>
</html>
