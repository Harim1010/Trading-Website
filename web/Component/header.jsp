<%-- 
    Document   : header
    Created on : Jan 30, 2024, 10:16:16 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.*" %>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Trading Market</title>

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <script>
        function confirmLogout() {
            return confirm("Are you sure you want to log out?");
        }
    </script>
</head>
<body>
    <header id="header" class="fixed-top d-flex align-items-center">
        <div class="container d-flex">
            <div class="row d-flex align-items-center">
                <div class="logo col-lg-3 text-lg-left text-center">
                    <h1><a href="home.jsp">Sulk Market</a></h1>
                </div>
                <c:if test="${sessionScope.account == null}">
                    <div class="col-lg-6"></div>
                    <nav id="navbar" class="navbar col-lg-3 text-lg-right text-center">
                        <ul>
                            <li><a class="nav-link" href="home.jsp">Trang chủ</a></li>
                            <li class="dropdown"><a class="nav-link" href="login.jsp"><span>Thanh toán</span></a>
                                <ul>
                                    <li><a href="login.jsp">Nạp tiền</a></li>
                                    <li><a href="login.jsp">Lịch sử giao dịch</a></li>
                                    <li><a href="login.jsp">Yêu cầu rút tiền</a></li>     
                                </ul>       
                            </li>       

                            <li class="dropdown"><a class="nav-link" href="#"><span>Mua hàng</span></a>
                                <ul>
                                    <li><a href="#">Sản phẩm</a></li>
                                    <li><a href="login.jsp">Đơn hàng</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="nav-link" href="#"><span>Trung gian</span></a>
                                <ul>
                                    <li><a href="#">Chợ công khai</a></li>
                                    <li><a href="login.jsp">Đơn bán của tôi</a></li>
                                    <li><a href="login.jsp">Đơn mua của tôi</a></li>
                                </ul>
                            </li>
                            <li><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp?id=0">Đăng kí</a></li>
                            <li><a class="getstarted" href="login.jsp">Đăng nhập</a></li>
                        </ul>
                    </nav>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <div class="col-lg-3"></div>
                    <nav id="navbar" class="navbar col-lg-6 text-lg-right">
                        <ul>
                            <li><a class="nav-link" href="home.jsp">Trang chủ</a></li>
                            <li class="dropdown"><a class="nav-link" href="#"><span>Thanh toán</span></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/Deposit1.jsp">Nạp tiền</a></li>
                                    <li><a href="transhis">Lịch sử giao dịch</a></li>
                                    <li><a href="${pageContext.request.contextPath}/withdrawList">Yêu cầu rút tiền</a></li>     
                                </ul>       
                            </li>       

                            <li class="dropdown"><a class="nav-link" href="#"><span>Mua hàng</span> </a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/publicMarket">Sản phẩm</a></li>
                                    <li><a href="${pageContext.request.contextPath}/purchaseOrder?accountId=${sessionScope.account.getId()}">Đơn hàng</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="nav-link" href="#"><span>Trung gian</span></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/intermediaryPublicMarket?accountId=${sessionScope.account.getId()}">Chợ công khai</a></li>
                                    <li><a href="mysale">Đơn bán của tôi</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myIntermediaryOrder?accountId=${sessionScope.account.getId()}">Đơn mua của tôi</a></li>
                                </ul>
                            </li>
                            <li><a class="nav-link" href="#">Thông báo</a></li>
                                <c:if test="${sessionScope.account.getRole_id() == 1}">
                                <li class="dropdown"><a class="nav-link" href="#"><span>Quản lí</span></a>
                                    <ul>
                                        <li><a href="managerAc">Danh sách người dùng</a></li>
                                        <li><a href="#">Đơn khiếu nại</a></li>
                                        <li><a href="${pageContext.request.contextPath}/withdrawListAdmin">Giao dịch khách hàng</a></li>
                                        <li><a href="#">Kiểm tra đơn hàng</a></li>
                                    </ul>
                                </li>
                            </c:if>
                            <li><a class="nav-link" href="#">Số dư: ${sessionScope.walletCurrent.getBalance()}</a></li>    
                            <li class="dropdown avatar"><a class="nav-link avatar"><img style="width: 30px; height: 30px" src="assets/img/3541871.png"></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/profile?accountId=${sessionScope.account.getId()}">Thông tin của tôi</a></li>
                                    <li><a href="${pageContext.request.contextPath}/logout" onclick="return confirmLogout()">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </header>

</body>
