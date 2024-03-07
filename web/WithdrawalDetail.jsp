<%-- 
    Document   : WithdrawalRequest
    Created on : Feb 4, 2024, 1:18:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal Detail</title>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'In process'}">
            <form action="withdrawDetail" method="post">
                <h2>EDIT WITHDRAWAL REQUEST</h2>
                <h4 style="color:red">${sessionScope.errorWithdraw}</h4>
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đang chờ được xừ lý.</h4>    

                <label>Amount of withdraw money:</label>
                <input type="text" name="amount" oninput="formatCurrency(this)" value="${sessionScope.withdrawCurrent.getAmount()}" required>

                <label>Bank Name:</label>
                <input type="text" name="bankName" value="${sessionScope.withdrawCurrent.getBank_name()}"  required>

                <label>Bank User:</label>
                <input type="text" name="bankUser" value="${sessionScope.withdrawCurrent.getBank_user()}"  required>

                <label>Bank Number:</label>
                <input type="text" name="bankNumber" value="${sessionScope.withdrawCurrent.getBank_number()}"  required>
                
                <button type="submit">Save</button>
                <button type="button" onclick="back()">Cancel</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.withdrawCurrent.getStatus() != 'In process'}">
            <form action="withdrawDetail" method="post">
                <h2>EDIT WITHDRAWAL REQUEST</h2>
                
                <label>Amount of withdraw money:</label>
                <input type="text" name="amount" oninput="formatCurrency(this)" value="${sessionScope.withdrawCurrent.getAmount()}" readonly disabled>

                <label>Bank Name:</label>
                <input type="text" name="bankName" value="${sessionScope.withdrawCurrent.getBank_name()}"  readonly disabled>

                <label>Bank User:</label>
                <input type="text" name="bankUser" value="${sessionScope.withdrawCurrent.getBank_user()}"  readonly disabled>

                <label>Bank Number:</label>
                <input type="text" name="bankNumber" value="${sessionScope.withdrawCurrent.getBank_number()}" readonly disabled>

                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Finished'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đã được xác nhận, nếu chưa nhận được tiền vui lòng báo cáo <a href="#" style="font-weight: bold; color: blue;">tại đây</a>.</h4>    
                </c:if>
                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Denied'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đã bị từ chối, nếu có lỗi vui lòng báo cáo <a href="#" style="font-weight: bold; color: blue;">tại đây</a>.</h4>    
                </c:if>
                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Error'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đã bị lỗi, vui lòng kiểm tra lại thông tin rút tiền hoặc báo cáo <a href="#" style="font-weight: bold; color: blue;">tại đây</a>.</h4>    
                </c:if>
                
                <button type="button" onclick="back()" style="background-color: #3498db;">Done</button>
            </form>
        </c:if>


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
</html>
