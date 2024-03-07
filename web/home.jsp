<%-- 
    Document   : homePage
    Created on : Jan 6, 2024, 1:45:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.*" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Sulk Market</title>

        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">

        <script>
            function confirmLogout() {
                return confirm("Are you sure you want to log out?");
            }
        </script>
    </head>

    <body>
        <%@include file="Component/header.jsp" %>
        <!-- ======= Section ======= -->
        <section id="hero" class="d-flex align-items-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6  d-flex flex-column justify-content-center">
                        <h1 data-aos="fade-up">Manage your transaction easier and safer with us</h1>
                        <h2 data-aos="fade-up" data-aos-delay="400">We are team of intermediate trader, our reputation is your satisfaction.</h2>
                        <div data-aos="fade-up" data-aos-delay="800">
                            <c:if test="${sessionScope.account == null}">
                                <a href="login.jsp" class="btn-get-started">Đăng nhập</a> 
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                                <a href="#" class="btn-get-started">Tạo một giao dịch</a> 
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-6 hero-img">
                        <img src="assets/img/hero-img.png">
                    </div>
                </div>
            </div>
        </section>
        <!-- End Section -->
        <!-- ======= Footer ======= -->
        <footer id="footer">
            <div class="container" >
                <div class="row d-flex align-items-center">
                    <div class="col-lg-6 text-lg-left text-center">
                        <div class="copyright">
                            &copy; Copyright <strong>Sulk Market</strong>. All Rights Reserved
                        </div>
                        <div class="credits">
                            Designed by <a href="https://www.facebook.com/tylerdurden1409">Thầy Quang Vĩ Đại</a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <nav class="footer-links text-lg-right text-center pt-2 pt-lg-0">
                            <a href="home.jsp">Home</a>
                            <a href="#">About</a>
                            <a href="#">Privacy Policy</a>
                            <a href="#">Terms of Use</a>
                        </nav>
                    </div>
                </div>
            </div>
        </footer><!-- End Footer -->
    </body>
</html>
