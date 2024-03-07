<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đơn mua của tôi</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css"/>
        <link href="assets/css/ListPurchaseOrder.css" rel="stylesheet"/>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
        <script>
            function back() {
                window.location.href = "home.jsp";
            }
            function doDelete(id)
            {
                var c = confirm("Are you sure?");
                if (c)
                {
                    window.location.href = "deleteAc?pid=" + id;
                }
            }
        </script>

    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row" style="background-color:#566787 " >
                        <h2 style="color: #ffffff">Đơn mua<b style="color: #ffffff; margin-left: 10px">Của Tôi</b></h2>   
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Trạng thái đơn hàng</th>
                            <th>Sản phẩm đặt mua</th>
                            <th>Đơn giá (VNĐ)</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Thời gian tạo đơn hàng</th>
                            <th>Thời gian xử lý đơn hàng</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listPurchaseOrder}" var="p">
                            <c:if test="${ accountId == p.getUserId() }">
                                <tr>
                                    <td>${p.getId()}</td>
                                    <td>${p.getStatus()}</td>
                                    <td>${p.getProductName()}</td>
                                    <td>${p.getPrice()}</td>
                                    <td>${p.getQuantity()}</td>
                                    <td>${p.getTotalPrice()}</td>
                                    <td>${p.getCreateDate()}</td>
                                    <td>${p.getCreateDate()}</td>
                                    <td>
                                        <a href="#" onclick="openModal(event, ${p.getId()})" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">info</i>Chi tiết</a>
                                    </td>
                                </tr>
                            <div id="myModal-${p.getId()}" class="modal">
                                <!-- Modal content -->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <span class="close" onclick="closeModal(${p.getId()})">&times;</span>
                                        <h2>Chi tiết đơn hàng</h2>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Mã đơn hàng</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getId()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Sản phẩm đặt mua</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getProductName()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Trạng thái đơn hàng</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getStatus()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Đơn giá</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getPrice()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Số lượng</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getQuantity()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Thành tiền</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getTotalPrice()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Kết quả đơn hàng</label>
                                            <div class="col-sm-10">
                                                <text>${p.getHide_info()}</text>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Thời gian hoàn tất xử lý đơn hàng</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getCreateDate()}" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <a href="deleteAc?pid=${p.id}"  class="buying" data-toggle="modal"  onclick="doDelete(${p.id})"><i class="material-icons" data-toggle="tooltip" title="Delete" >add_shopping_cart</i>Mua</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <h4 style="color:red">${mess}</h4>
                    </tbody>
                </table>

            </div>
            <button type="button" class="btn btn-primary" onclick="back()">Trang chủ</button>
            <script>
                // Get the button that opens the modal
                var btn = document.getElementById("myBtn");

                var modal;

                function openModal(event, id) {
                    event.preventDefault();
                    // Get the modal
                    modal = document.getElementById("myModal-" + id);
                    modal.style.display = "block";
                };

                // When the user clicks on <span> (x), close the modal
                function closeModal(id) {
                    // Get the modal
                    modal = document.getElementById("myModal-" + id);
                    modal.style.display = "none";
                };

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };

            </script>
    </body>
</html>