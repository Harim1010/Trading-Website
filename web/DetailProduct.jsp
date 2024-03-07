<%-- 
    Document   : AddProduct
    Created on : Jan 29, 2024, 10:50:48 PM
    Author     : Xuanphuc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>JSP Page</title>
        <!-- Include Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tiny.cloud/1/tvyzan2m5d7swqx0o070hgujuzbcvpkmqalb4rrhampk4hgr/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
        <link rel="stylesheet" href="./assets/css/DetailProduct.css">
        <style>
            * {
                box-sizing: border-box;
                padding: 0;
                margin: 0; /* Remove default margin */
            }
            body {
                background-color: rgba(0, 0, 0, 0.7);
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh; /* 100% viewport height */

            }

            .custom-container {
                max-width: 800px;
                background-color: #fff;
                margin: 100px 0px;
            }

            .intermediary-order {
                font-size: 20px;
                font-weight: bold;
                color: black;
                padding: 10px;
                margin-bottom: 20px;
                border-bottom: 2px solid gray;
            }

            .form-row {
                margin-bottom: 10px;
            }

            label {
                font-size: 14px;
            }

            input[type="text"] {
                font-size: 14px;
            }

            .btn {
                font-size: 14px;
            }
            .fee-type-container {
                display: flex;
                background-color: #fff;
                gap : 10px;
            }

            .fee-type-option {
                flex: 1; /* Make each option flexible */
            }

            .fee-type-option input {
                display: none;
            }

            .fee-type-option label {
                background-color: #ddd;
                padding: 10px;
                cursor: pointer;
                width: 100%;
                text-align: center;
                border-radius: 8px;
            }

            .fee-type-option input:checked + label {
                background-color: #0a58ca;
                color: #fff;
            }



            .public-status-container {
                display: flex;
                background-color: #fff;
                gap : 10px;
            }

            .public-status-option {
                flex: 1; /* Make each option flexible */
            }

            .public-status-option input {
                display: none;
            }

            .public-status-option label {
                background-color: #ddd;
                padding: 10px;
                cursor: pointer;
                width: 100%;
                text-align: center;
                border-radius: 8px;
            }

            .public-status-option input:checked + label {
                background-color: #0a58ca;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <%@include file="Component/header.jsp" %>
        <div class="container custom-container">
            <div class="intermediary-order">
                Detail of Product
            </div>

            <form id="contact" action="detailproduct" method="post">
                <!-- code -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="title">Code</label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="code" name="code" required value="${io.getId()}" autofocus readonly=""/>
                    </div>
                </div>

                <!-- nguoi ban -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="title">Seller</label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="seller" name="seller" value="${account.getName()}" required autofocus readonly=""/>
                    </div>
                </div>

                <!-- nguoi mua -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="title">Buyer</label>
                    </div>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="buyer" name="buyer"  required autofocus readonly=""/>
                    </div>
                </div>

                <!-- trang thai giao dich -->
                <div class="form-row">
                    <div class="col-md-2">
                        <label for="title">Status product</label>
                    </div>
                    <c:choose>
                        <c:when test="${io.getOrder_status().equals('ready to trade')}">
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="ttgd" name="ttgd" required autofocus value="${io.getOrder_status() ? 'Đã giao dịch' : 'Chưa giao dịch'}" readonly="" />
                            </div>
                        </div> 

                        <!-- Title -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="title">Title</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="title" name="title" required autofocus >${io.getTitle()}</textarea>
                            </div>
                        </div>                

                        <!-- gia ca -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Price</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="price" name="price" value="${io.getPrice()}" required>
                            </div>
                        </div>

                        <!-- Fee Type -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label>Fee Type</label>
                            </div>
                            <div class="col-md-10">
                                <div class="fee-type-container">
                                    <div class="fee-type-option">
                                        <input type="radio" id="buy" name="feetype" value="1" ${io.isFee_type() == true ? 'checked' : ''} required >
                                        <label for="buy">Buyer</label>
                                    </div>
                                    <div class="fee-type-option">
                                        <input type="radio" id="sale" name="feetype" value="0" ${io.isFee_type() == false ? 'checked' : ''} required >
                                        <label for="sale">Seller</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- phi trung gian -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Transaction fees</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.getPrice() * 0.1}" required readonly="">
                            </div>
                        </div>

                        <!-- tong phi can tra -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Total fee</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.isFee_type() ? io.getPrice() + io.getPrice() * 0.1 : io.getPrice()}" readonly="" required>
                            </div>
                        </div>

                        <!-- tien ben ban nhan -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Seller receive</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.isFee_type() ? io.getPrice(): io.getPrice()-io.getPrice() * 0.1}" readonly="" required>
                            </div>
                        </div>

                        <!-- Description -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="description">Description(The more detailed the product, the better because this will
                                    be the legal basis for resolving complaints if any arise later)</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control" id="description" name="description" rows="8" required>${io.getDescription()}</textarea>
                            </div>
                        </div>

                        <!-- Contact -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="contact">Contact</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="contact" name="contact" rows="2" required> ${io.getContact()}</textarea>
                            </div>
                        </div>

                        <!-- Hide Description -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Hide Description</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control" id="hide" name="hide" rows="5" required>${io.getHide_description()}</textarea>
                            </div>
                        </div>

                        <!-- Public Status -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label>Public Status</label>
                            </div>
                            <div class="col-md-10 ">
                                <div class="public-status-container">
                                    <div class="public-status-option">
                                        <input type="radio" id="public" name="publicstatus" value="1" ${io.isPublic_status()== true ?'checked' : ''}  required>
                                        <label for="public">Public for everyone</label>
                                    </div>
                                    <div class="public-status-option">
                                        <input type="radio" id="unpublic" name="publicstatus" value="0" ${io.isPublic_status()== false ?'checked' : ''}  required>
                                        <label for="unpublic">Hide for those with the link</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- thoi gian tao -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Created time</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="createdtime" name="createdtime" value="${io.getCreated_at()} " readonly="" required/>
                            </div>
                        </div>

                        <!-- thoi gian cuoi -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Updated time</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="updatedtime" name="updatedtime" value="${io.getUpdated_at()}" readonly="" required/>
                            </div>
                        </div>

                        <input type="hidden" value="${io.getId()}" name="id">

                        <div class="form-group text-center">
                            <input type="submit" class="btn btn-primary mx-auto" value="update">
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="ttgd" name="ttgd" required autofocus value="${io.getOrder_status()}" readonly="" />
                            </div>
                        </div> 

                        <!-- Title -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="title">Title</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="title" name="title" required autofocus readonly="" >${io.getTitle()}</textarea>
                            </div>
                        </div>                

                        <!-- gia ca -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Price</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="price" name="price" value="${io.getPrice()}" required readonly="">
                            </div>
                        </div>

                        <!-- Fee Type -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label>Fee Type</label>
                            </div>
                            <div class="col-md-10">
                                <div class="fee-type-container">
                                    <div class="fee-type-option">
                                        <input type="radio" id="buy" name="feetype" value="1" ${io.isFee_type() == true ? 'checked' : ''} required disabled="">
                                        <label for="buy">Buyer</label>
                                    </div>
                                    <div class="fee-type-option">
                                        <input type="radio" id="sale" name="feetype" value="0" ${io.isFee_type() == false ? 'checked' : ''} required disabled="">
                                        <label for="sale">Seller</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- phi trung gian -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Transaction fees</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.getPrice() * 0.1}" required readonly="">
                            </div>
                        </div>

                        <!-- tong phi can tra -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Total fee</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.isFee_type() ? io.getPrice() + io.getPrice() * 0.1 : io.getPrice()}" readonly="" required>
                            </div>
                        </div>

                        <!-- tien ben ban nhan -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="price">Seller receive</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="iofee" name="iofee" value="${io.isFee_type() ? io.getPrice(): io.getPrice()-io.getPrice() * 0.1}" readonly="" required>
                            </div>
                        </div>

                        <!-- Description -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="description">Description</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="description" name="description" rows="8" required readonly="">${io.getDescription()}</textarea>
                            </div>
                        </div>

                        <!-- Contact -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="contact">Contact</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="contact" name="contact" rows="2" required readonly=""> ${io.getContact()}</textarea>
                            </div>
                        </div>

                        <!-- Hide Description -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Hide Description</label>
                            </div>
                            <div class="col-md-10">
                                <textarea type="text" class="form-control keep-plain-textarea" id="hide" name="hide" rows="5" required readonly="">${io.getHide_description()}</textarea>
                            </div>
                        </div>

                        <!-- Public Status -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label>Public Status</label>
                            </div>
                            <div class="col-md-10 ">
                                <div class="public-status-container">
                                    <div class="public-status-option">
                                        <input type="radio" id="public" name="publicstatus" value="1" ${io.isPublic_status()== true ?'checked' : ''}  required disabled="">
                                        <label for="public">Public for everyone</label>
                                    </div>
                                    <div class="public-status-option">
                                        <input type="radio" id="unpublic" name="publicstatus" value="0" ${io.isPublic_status()== false ?'checked' : ''}  required disabled="">
                                        <label for="unpublic">Hide for those with the link</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- thoi gian tao -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Created time</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="createdtime" name="createdtime" value="${io.getCreated_at()} " readonly="" required/>
                            </div>
                        </div>

                        <!-- thoi gian cuoi -->
                        <div class="form-row">
                            <div class="col-md-2">
                                <label for="hide">Updated time</label>
                            </div>
                            <div class="col-md-10">
                                <input type="text" class="form-control" id="updatedtime" name="updatedtime" value="${io.getUpdated_at()}" readonly="" required/>
                            </div>
                        </div>

                        <input type="hidden" value="${io.getId()}" name="id">

                        <div class="form-group text-center">
                        </c:otherwise>
                    </c:choose>
                    <a href="mysale">   <button type="button" class="btn btn-secondary close-button">Close</button></a>
                </div>

            </form>
        </div>
        <script>
            tinymce.init({
                selector: 'textarea:not(.keep-plain-textarea)',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage advtemplate ai mentions tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                tinycomments_mode: 'embedded',
                tinycomments_author: 'Author name',
                mergetags_list: [
                    {value: 'First.Name', title: 'First Name'},
                    {value: 'Email', title: 'Email'},
                ],
                ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
            });
        </script>

        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
