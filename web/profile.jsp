<%-- 
    Document   : profile
    Created on : Jan 18, 2024, 1:38:44 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.*" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/profile.css"/>
        <link rel="stylesheet" href="./assets/css/style.css"/>
        <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css"/>
        <title>Profile</title>
    </head>
    <body>
        <!-- ======= Header ======= -->
        <%@include file="Component/header.jsp" %>
        
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <!--            <nav class="nav nav-borders">
                            <a class="nav-link active ms-0" href="https://www.bootdey.com/snippets/view/bs5-edit-profile-account-details" target="__blank">Profile</a>
                            <a class="nav-link" href="https://www.bootdey.com/snippets/view/bs5-profile-billing-page" target="__blank">Billing</a>
                            <a class="nav-link" href="https://www.bootdey.com/snippets/view/bs5-profile-security-page" target="__blank">Security</a>
                            <a class="nav-link" href="https://www.bootdey.com/snippets/view/bs5-edit-notifications-page"  target="__blank">Notifications</a>
                        </nav>
                        <hr class="mt-0 mb-4">-->
            
            <div class="row" style="margin-top: 100px">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                            <!-- Profile picture help block-->
                            <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                            <!-- Profile picture upload button-->
                            <button class="btn btn-primary" type="button">Upload new image</button>
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Information</div>
                        <div class="card-body">
                            <form action="profile" method="POST">
                                <!-- Form Group (username)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Username</label>
                                    <input class="form-control" id="inputUsername" name="username" type="text" placeholder="Enter your username" value="${requestScope.accountItem.getUsername()}" readonly disabled>
                                    <input class="form-control" name="accountId" type="hidden" placeholder="Enter your username" value="${requestScope.accountItem.getId()}">

                                </div>
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputFullname">Fullname</label>
                                    <input class="form-control" id="inputFullname" name="fullname" type="text" placeholder="Enter your fullname" value="${requestScope.accountItem.getName()}">
                                </div>
                                <!-- Form Row-->
                                <!--                                <div class="row gx-3 mb-3">
                                                                     Form Group (first name)
                                                                    <div class="col-md-6">
                                                                        <label class="small mb-1" for="inputFirstName">First name</label>
                                                                        <input class="form-control" id="inputFirstName" type="text" placeholder="Enter your first name" value="Valerie">
                                                                    </div>
                                                                     Form Group (last name)
                                                                    <div class="col-md-6">
                                                                        <label class="small mb-1" for="inputLastName">Last name</label>
                                                                        <input class="form-control" id="inputLastName" type="text" placeholder="Enter your last name" value="Luna">
                                                                    </div>
                                                                </div>-->
                                <!-- Form Row        -->
<!--                                <div class="row gx-3 mb-3">
                                     Form Group (organization name)
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputOrgName">Organization name</label>
                                        <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Start Bootstrap">
                                    </div>
                                     Form Group (location)
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputLocation">Location</label>
                                        <input class="form-control" id="inputLocation" type="text" placeholder="Enter your location" value="San Francisco, CA">
                                    </div>
                                </div>-->
                                <!-- Form Group (email address)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                    <input class="form-control" id="inputEmailAddress" name="email" type="email" placeholder="Enter your email address" value="${requestScope.accountItem.getEmail()}" readonly disabled>
                                </div>
                                <!-- Form Row-->
                                <div class="row gx-3 mb-3">
                                    <!-- Form Group (phone number)-->
                                    <div class="col-md-12">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" id="inputPhone" type="tel" placeholder="Enter your phone number" value="" name="phone">
                                    </div>
                                    <!-- Form Group (birthday)-->
<!--                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputBirthday">Birthday</label>
                                        <input class="form-control" id="inputBirthday" type="text" name="birthday" placeholder="Enter your birthday" value="06/10/1988">
                                    </div>-->
                                </div>
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputDesAddress">Description</label>
                                    <input class="form-control" id="inputDesAddress" name="description" type="textarea" placeholder="Enter your description" value="" name="description">
                                </div>
                                <!-- Save changes button-->
                                <input class="btn btn-primary" type="submit" value="Save changes" onclick="confirmChanges()">
                            </form>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">Change Password</div>
                            <div class="card-body">
                                <form action="changePassword" method="POST">
                                    <!-- Form Group (username)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputCurrentPass">Current Password</label>
                                        <input class="form-control" id="inputCurrentPass" name="curPass" type="password" placeholder="Enter your current password" value="">
                                        <input class="form-control" name="accountId" type="hidden" placeholder="Enter your username" value="${accountItem.getId()}">

                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputNewPass">New Password</label>
                                        <input class="form-control" id="inputNewPass" name="newPass" type="password" placeholder="Enter your new password" value="">
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputRetypePass">Re-type Password</label>
                                        <input class="form-control" id="inputRetypePass" name="retypePass" type="password" placeholder="Re-type new password" value="">
                                    </div>
                                    <div class="group group-captcha mb-3">
                                        <img src="login" alt="CAPTCHA">
                                        <input class="input form-control" type="text" name="captchaInput" placeholder="Nhập CAPTCHA" required>
                                    </div>
                                    <div class="group">
                                        <h3 style="color: red; font:600 16px/18px 'Open Sans',sans-serif;"> ${requestScope.err}</h3>
                                    </div>
                                    <input class="btn btn-primary" type="submit" value="Change password" onclick="confirmChanges()">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        function confirmChanges() {
            return confirm("Are you sure to change information?");
        }
    </script>
</html>
