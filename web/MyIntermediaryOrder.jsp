<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đơn trung gian đã mua</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css"/>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="assets/css/IntermediaryPublicMarket.css"/>
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
            function confirmBuying(oId, uId)
            {
                var c = confirm("Xác nhận thông tin chính xác");
                if (c)
                {
                    window.location.href = "confirmBuyIntermediaryOrder?oId=" + oId + "&uId=" + uId;
                }
            }
        </script>

    <body>
        <!-- ======= Header ======= -->
        <%@include file="Component/header.jsp" %>
        <div class="container-xl content">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row" style="background-color:#566787 " >
                        <h2 style="color: #ffffff"><b style="color: #ffffff; margin-left: 10px">Đơn mua của tôi</b></h2>   
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Mã trung gian</th>
                            <th>Trạng thái</th>
                            <th>Người bán</th>
                            <th>Chủ đề trung gian</th>
                            <th>Phương thức liên lạc</th>
                            <th>Công khai / Riêng tư</th>
                            <th>Giá tiền</th>
                            <th>Bên chịu phí trung gian</th>
                            <th>Phí trung gian</th>
                            <th>Tổng phí đã thanh toán</th>
                            <th>Thời gian tạo</th>
                            <th>Cập nhật cuối</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOrder}" var="p">
                            <tr>
                                <td>${p.getId()}</td>
                                <td>
                                    <c:if test="${p.getOrder_status() == 'checking'}">Người mua đang kiểm tra thông tin</c:if>
                                    <c:if test="${p.getOrder_status() == 'transacted'}">Hoàn thành</c:if>
                                </td>
                                <td>${p.getAccount_sold_name()}</td>
                                <td>${p.getTitle()}</td>
                                <td>${p.getContact()}</td>
                                <td>
                                    <c:if test="${p.getPublic_status()}">Công khai ai cũng có thể nhìn thấy</c:if>
                                    <c:if test="${!p.getPublic_status()}">Chỉ người có link chia sẻ mới có thể truy cập</c:if>
                                </td>
                                <td>${p.getPrice()}</td>
                                <td>
                                    <c:if test="${p.getFee_type()}">Người mua</c:if>
                                    <c:if test="${!p.getFee_type()}">Người bán</c:if>
                                </td>
                                <td>${p.getIntermediary_fee()}</td>
                                <td>${p.getIntermediary_fee() + p.getPrice()}</td>
                                <td>${p.getCreated_at()}</td>
                                <td>${p.getUpdated_at()}</td>
                                <td>
                                    <a href="#" onclick="openModal(event, ${p.getId()})" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">info</i>Chi tiết</a>
                                </td>
<!--                                <td><a href="deleteAc?pid=${p.id}"  class="edit" data-toggle="modal"  onclick="doDelete(${p.id})"><i class="material-icons" data-toggle="tooltip" title="Delete" >add_shopping_cart</i>Mua</a></td>-->
                            </tr>
                            <!--<a href="#">-->
                            <!-- The Modal -->
                        <div id="myModal-${p.getId()}" class="modal">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close" onclick="closeModal(${p.getId()})">&times;</span>
                                    <h2>Chi tiết sản phẩm</h2>
                                </div>
                                <div class="modal-body">
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Mã trung gian</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getId()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Trạng thái đơn hàng</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="<c:if test="${p.getOrder_status() == 'checking'}">Người mua đang kiểm tra thông tin</c:if><c:if test="${p.getOrder_status() == 'transacted'}">Hoàn thành</c:if>" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Người bán</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getAccount_sold_name()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Chủ đề trung gian</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getTitle()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Giá tiền</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getPrice()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Bên chịu phí trung gian</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="<c:if test="${p.getFee_type()}">Người mua</c:if><c:if test="${!p.getFee_type()}">Người bán</c:if>" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Phí trung gian</label>
                                            <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getIntermediary_fee()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Tổng tiền bên mua cần thanh toán</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="<c:if test="${p.getFee_type()}">${p.getIntermediary_fee() + p.getPrice()}</c:if><c:if test="${!p.getFee_type()}">${p.getPrice()}</c:if>" readonly disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Mô tả</label>
                                            <div class="col-sm-10">
                                                    <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getDescription()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Phương thức liên hệ</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getContact()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Nội dung ẩn</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getHide_description()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Thời gian tạo</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getCreated_at()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Cập nhật cuối</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getUpdated_at()}" readonly disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="colFormLabel" class="col-sm-2 col-form-label fw-bolder">Link chia sẻ</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="colFormLabel" placeholder="col-form-label" value="${p.getLink_to()}" readonly disabled>
                                        </div>
                                    </div>
                                    <c:if test="${p.getOrder_status() == 'checking'}">
                                        <div class="row mb-3">
                                            <a href="#"  class="buying" data-toggle="modal"  onclick="confirmBuying(${p.id}, ${uId})"><i class="material-icons" data-toggle="tooltip" title="Delete" >add_shopping_cart</i>Xác nhận thông tin chính xác</a>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <h4 style="color:red">${mess}</h4>
                    </tbody>
                </table>

            </div>
            <button type="button" class="btn btn-primary" onclick="back()">Trang chủ</button>
            <script>
                // Get the button that opens the modal
//                var btn = document.getElementById("myBtn");

                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[0];

                // When the user clicks on the button, open the modal
//                btn.onclick = function () {
//                    modal.style.display = "block";
//                };

                var modal;

                function openModal(event, id) {
                    event.preventDefault();
                    // Get the modal
                    modal = document.getElementById("myModal-" + id);
                    modal.style.display = "block";
                }
                ;

                // When the user clicks on <span> (x), close the modal
                function closeModal(id) {
                    // Get the modal
                    modal = document.getElementById("myModal-" + id);
                    modal.style.display = "none";
                }
                ;

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                    }
                };

            </script>
    </body>
</html>