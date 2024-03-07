<%-- 
    Document   : withdrawalList
    Created on : Jan 30, 2024, 9:28:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal Page</title>
        <script>
            function submitForm() {
                document.getElementById('withdrawForm').submit();
            }
        </script>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <div class="container-fluid" style="margin-top: 100px; margin-bottom: 110px;">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row" style="background-color: #3498db; padding-bottom: 13px; padding-top: 16px; align-items: center;" >
                        <h2 class="col-lg-9 text-lg-left" style="color: #ffffff; padding-left: 82px; font-weight: bold;">Withdrawal Request</h2> 
                        <form class="col-lg-3" action="withdrawSelect" id="withdrawForm">
                            <h3 style="font-weight: bold; color: white; font-size: 20px; display: inline-block; margin-right: 10px">Status:</h3>
                            <select type="submit" name="withdrawSelect" class="btn btn-primary" style="background-color: white; font-weight: bold; color: #3498db; border: none;" onchange="submitForm()">
                                <option ${sessionScope.withdrawSelect == null ? 'hidden' : ''}>${sessionScope.withdrawSelect}</option>
                                <option value="All" ${sessionScope.withdrawSelect == 'All' ? 'hidden' : ''}>All</option>
                                <option value="Error" ${sessionScope.withdrawSelect == 'Error' ? 'hidden' : ''}>Error</option>
                                <option value="Finished" ${sessionScope.withdrawSelect == 'Finished' ? 'hidden' : ''}>Finished</option>
                                <option value="In process" ${sessionScope.withdrawSelect == 'In process' ? 'hidden' : ''}>In process</option>
                                <option value="Denied" ${sessionScope.withdrawSelect == 'Denied' ? 'hidden' : ''}>Denied</option>
                            </select>
                        </form>
                    </div>
                </div>
                <table class="table table-striped table-hover" style="text-align: center;">
                    <thead>
                        <tr>
                            <th>Mã yêu cầu</th>
                            <th>Trạng thái</th>
                            <th>Số tiền rút</th>
                            <th>Ngân hàng</th>
                            <th>Số tài khoản</th>
                            <th>Chủ tài khoản</th>
                            <th>Thời gian tạo</th>
                            <th>Thời gian cập nhật</th>
                            <th>Thông tin</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listWithdraw}" var="p">
                            <tr>
                                <td>${p.withdrawal_id}</td>
                                <td>${p.status}</td>
                                <td><fmt:formatNumber type="currency" currencyCode="VND" value="${p.amount}" /></td>
                                <td>${p.bank_name}</td>
                                <td>${p.bank_number}</td>
                                <td>${p.bank_user}</td>
                                <td>${p.create_datetime}</td>
                                <td>${p.update_datetime}</td>
                                <td><a href="${pageContext.request.contextPath}/withdrawDetailAdmin?withdrawID=${p.withdrawal_id}">
                                        <button type="button" class="btn btn-primary" style="background-color: #3498db; font-weight: bold;">Detail</button>
                                    </a></td>
                                    <c:if test="${p.status eq 'In process'}">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/withdrawHandle?withdrawID=${p.withdrawal_id}&action=deny">
                                            <button onclick="return confirmDeny()" type="button" class="btn btn-primary" style="background-color: #dc3545; font-weight: bold;">Deny</button>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/withdrawHandle?withdrawID=${p.withdrawal_id}&action=accept">
                                            <button onclick="return confirmAccept()" type="button" class="btn btn-primary" style="background-color: #00cc00; font-weight: bold;">Accept</button>
                                        </a></td>   
                            <script>
                                function confirmAccept() {
                                    return confirm("Admin xác nhận duyệt đơn rút tiền và đã chuyển tiền thành công cho khách hàng");
                                }

                                function confirmDeny() {
                                    return confirm("Admin xác nhận từ chối đơn rút tiền");
                                }
                            </script> 
                        </c:if>
                        <c:if test="${p.status != 'In process'}">
                            <td>
                                <a href="#">
                                    <button onclick="reportDone()" type="button" class="btn btn-primary" style="background-color: #dc3545; font-weight: bold;">Deny</button>
                                </a>
                                <a href="#">
                                    <button onclick="reportDone()" type="button" class="btn btn-primary" style="background-color: #00cc00; font-weight: bold;">Accept</button>
                                </a></td>   
                            <script>

                                function reportDone() {
                                    alert("Đơn này đã được xử lý");
                                }
                            </script> 
                        </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </div>        
        <%@include file="Component/footer.jsp" %>

        <style>
            /* styles.css */
            .table {
                border-collapse: collapse;
                width: 100%;
            }

            .table th, .table td {
                border: 1px solid #ddd; /* Border giữa các ô */
                padding: 8px; /* Khoảng cách bên trong ô */
                text-align: center; /* Canh lề nội dung ô theo chiều ngang */
            }

            .table th {
                background-color: #f2f2f2; /* Màu nền cho phần tiêu đề cột */
            }

            .table th, .table td {
                border-radius: 8px; /* Bo tròn các góc ô */
            }

            .custom-checkbox {
                position: relative;
                padding-left: 30px;
                cursor: pointer;
                display: inline-block;
            }

            .custom-checkbox input {
                position: absolute;
                opacity: 0;
                cursor: pointer;
                height: 0;
                width: 0;
            }

            .custom-checkbox label {
                position: absolute;
                top: 0;
                left: 0;
                height: 15px;
                width: 15px;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 3px;
            }

            .custom-checkbox label:after {
                content: "";
                position: absolute;
                display: none;
            }

            .custom-checkbox input:checked + label:after {
                display: block;
            }

            .custom-checkbox label:after {
                left: 4px;
                top: 1px;
                width: 6px;
                height: 10px;
                border: solid #000;
                border-width: 0 2px 2px 0;
                transform: rotate(45deg);
            }

        </style>
    </body>
</html>
