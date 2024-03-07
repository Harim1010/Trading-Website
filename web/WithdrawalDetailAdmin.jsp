<%-- 
    Document   : WithdrawalDetailAdmin
    Created on : Feb 6, 2024, 3:12:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal Detail</title>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'In process'}">
            <form action="withdrawHandle" method="post" class="button-container">
                <h2>WITHDRAWAL REQUEST</h2>

                <label>Amount of withdraw money:</label>
                <input type="text" name="amount" value="${sessionScope.withdrawCurrent.getAmount()}" readonly disabled>

                <label>Bank Name:</label>
                <input type="text" name="bankName" value="${sessionScope.withdrawCurrent.getBank_name()}" readonly disabled>

                <label>Bank User:</label>
                <input type="text" name="bankUser" value="${sessionScope.withdrawCurrent.getBank_user()}" readonly disabled>

                <label>Bank Number:</label>
                <input type="text" name="bankNumber" value="${sessionScope.withdrawCurrent.getBank_number()}" readonly disabled>

                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đang chờ được xừ lý.</h4>    
                
                <div class="button-row">
                    <button onclick="return confirmAccept()" type="submit" name="withdrawAction" value="accept">Accept</button>
                    <button onclick="return confirmDeny()" type="submit" name="withdrawAction" value="deny" style="background-color: #c0392b;">Deny</button>
                    <button onclick="return confirmError()" type="submit" name="withdrawAction" value="error" style="background-color: #3498db;">Error</button>
                    <button type="button" onclick="back()">Cancel</button>
                </div>
            </form>
        </c:if>
        <c:if test="${sessionScope.withdrawCurrent.getStatus() != 'In process'}">
            <form action="withdrawHandle" method="post" class="button-container">
                <h2>WITHDRAWAL REQUEST</h2>

                <label>Amount of withdraw money:</label>
                <input type="text" name="amount" value="${sessionScope.withdrawCurrent.getAmount()}" readonly disabled>

                <label>Bank Name:</label>
                <input type="text" name="bankName" value="${sessionScope.withdrawCurrent.getBank_name()}" readonly disabled>

                <label>Bank User:</label>
                <input type="text" name="bankUser" value="${sessionScope.withdrawCurrent.getBank_user()}" readonly disabled>

                <label>Bank Number:</label>
                <input type="text" name="bankNumber" value="${sessionScope.withdrawCurrent.getBank_number()}" readonly disabled>
                
                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Finished'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đã được hoàn thành.</h4>    
                </c:if>
                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Denied'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} đã bị từ chối.</h4>    
                </c:if>
                <c:if test="${sessionScope.withdrawCurrent.getStatus() eq 'Error'}">
                <h4 style="color:black">Đơn yêu cầu rút tiền có mã ${sessionScope.withdrawCurrent.getWithdrawal_id()} bị thông báo lỗi.</h4>    
                </c:if>
                
                <div class="button-row">
                    <button type="button" onclick="back()">Done</button>
                </div>
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

            label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
                color: #333; /* Set the label text color */
            }
            
            h4 {
                font-weight: bold;
                text-align: center;
                font-size: 15px; 
                font-family: Arial, sans-serif;
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

            .button-container {
                display: flex;
                flex-direction: column;
            }

            .button-row {
                display: flex;
                justify-content: space-between;
                margin-top: 10px; /* Để tạo khoảng cách giữa nút và các input */
            }

            button {
                background-color: #3498db;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                flex: 1;
                margin: 0 5px;
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
                background-color: #3498db;
            }

            button[type="button"]:hover {
                background-color: #007bb5;
            }

        </style>    
    </body>
    <script>
        function back() {
            window.location.href = "WithdrawalListAdmin.jsp";
        }
    </script>
    <script>
        function confirmAccept() {
            var confirmed = confirm("Admin xác nhận duyệt đơn rút tiền và đã chuyển tiền thành công cho khách hàng với mã yêu cầu ${sessionScope.withdrawCurrent.getWithdrawal_id()}");
            if (confirmed) {
                return true;
            } 
            return false;
        }

        function confirmDeny() {
            return confirm("Admin xác nhận từ chối đơn rút tiền có mã yêu cầu ${sessionScope.withdrawCurrent.getWithdrawal_id()}");
        }
        function confirmError() {
            return confirm("Admin xác nhận đơn yêu cầu rút tiền có mã yêu cầu ${sessionScope.withdrawCurrent.getWithdrawal_id()} bị lỗi");
        }
    </script>

</html>
