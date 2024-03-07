
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản ký tài khoản</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="./assets/css/info.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link href="css/ManagerProduct.css" rel="stylesheet" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="./assets/css/style.css" rel="stylesheet" />
        <style>
            img {
                width: 200px;
                height: 120px;
            }
        </style>

    </head>

    <body>
        <%@include file="Component/header.jsp" %>
        <style>
            .table-wrapper {
                border: 1px solid #ccc;
                /* Thêm viền màu xám cho toàn bộ bảng */
                border-radius: 5px;
                /* Bo tròn các góc */
                overflow: auto;
                /* Kích hoạt thanh cuộn ngang nếu bảng quá rộng */
            }
        </style>
        <div class="container-fluid" style="margin-top: 115px;">
            <div class="table-wrapper" >
                <div class="table-title">
                    <div class="row" style="background-color:#3498db; padding-bottom: 15px; padding-top: 18px;">
                        <h2 style="color: #ffffff; font-size: 250%;">Transaction History</h2>
                    </div>
                </div>

                <style>
                    .table {
                        table-layout: fixed;
                    }

                    .table th {
                        border: 1px solid #ccc;
                        /* Hiển thị viền cho các cột */
                        white-space: nowrap;
                        /* Ngăn chặn nội dung tự động xuống dòng mới */
                        overflow: hidden;
                        /* Ẩn nội dung bị tràn ra ngoài phạm vi cột */
                        text-overflow: ellipsis;
                        /* Hiển thị dấu elipsis (...) khi nội dung bị tràn ra ngoài */
                        cursor: grab;
                        /* Biến con trỏ thành biểu tượng kéo thả */
                    }

                    .table th::after {
                        content: '';
                        /* Chắc chắn rằng pseudo-element sẽ có nội dung */
                        position: absolute;
                        /* Vị trí tuyệt đối đối với th */
                        top: 0;
                        /* Đặt vị trí phía trên */
                        right: 0;
                        /* Đặt vị trí phía bên phải */
                        bottom: 0;
                        /* Đặt vị trí phía dưới */
                        width: 20px;
                        /* Độ rộng của phần mờ */
                        background: linear-gradient(to right, rgba(255, 255, 255, 0), rgba(255, 255, 255, 1));
                        /* Tạo hiệu ứng phần mờ */
                        pointer-events: none;
                        /* Đảm bảo sự kiện không ảnh hưởng đến phần mờ */
                    }

                    .table td.expandable::after {
                        content: '\2194';
                        /* Một biểu tượng kéo ngang */
                        position: absolute;
                        /* Vị trí tuyệt đối đối với td */
                        top: 50%;
                        /* Đặt vị trí phía trên */
                        right: 4px;
                        /* Đặt vị trí phía bên phải */
                        transform: translateY(-50%);
                        /* Canh chỉnh để căn giữa theo chiều dọc */
                        cursor: ew-resize;
                        /* Đổi con trỏ thành biểu tượng kéo ngang */
                        pointer-events: auto;
                        /* Đảm bảo sự kiện ảnh hưởng đến biểu tượng kéo */
                        color: #3498db;
                        /* Màu của biểu tượng */
                    }

                    .table td {
                        white-space: nowrap;
                        /* Ngăn chặn nội dung tự động xuống dòng mới */
                        overflow: hidden;
                        /* Ẩn nội dung bị tràn ra ngoài phạm vi cột */
                        text-overflow: ellipsis;
                        /* Hiển thị dấu elipsis (...) khi nội dung bị tràn ra ngoài */
                    }

                    .table th:hover {
                        cursor: pointer;
                        /* Biến con trỏ thành biểu tượng kéo thả khi di chuột qua cột */
                    }

                    .table-wrapper {
                        border: 1px solid #ccc;
                        /* Thêm viền màu xám cho toàn bộ bảng */
                        border-radius: 5px;
                        /* Bo tròn các góc */
                        overflow: auto;
                        /* Kích hoạt thanh cuộn ngang nếu bảng quá rộng */
                    }

                    .table th,
                    .table td {
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                    }

                    .table td {
                        border: 1px solid #ccc;
                        /* Hiển thị viền cho các cột */
                    }
                </style>


                <table class="table table-striped table-hover">
                    <thead>
                        <tr style="font-size: 100%;">
                            <th  style="width: 5%;">
                                <div style="text-align: center;">  Trading code </div>
                                <br>
                                <input id="filterMaGiaoDich" style="width: 67%;" type="text" placeholder="Search..." value="${giaodich}">
                                <button onclick="search()"><i class="fa fa-search"></i></button>
                            </th>
                            <th style="width: 5%;">
                                <div style="text-align: center;"> Money </div>
                                <br>
                                <input id="soTien" style="width: 67%;" type="text" placeholder="Search..." value="${money}">

                                <button onclick="search1()"><i class="fa fa-search"></i></button>
                            </th>
                            <th style="width: 5%;">
                                <div style="text-align: center;">Transaction type</div>
                                <br>
                                <select id="type" style="width: 67%;height: 44px;">
                                    <option>Option</option>
                                    <option value="-">Negative</option>
                                    <option value="+">Positive</option>
                                </select>

                                <button onclick="search2()"><i class="fa fa-search"></i></button>
                            </th>
                            <th style="width: 4%;">
                                <div style="text-align: center;">Processed</div>
                                <br>
                                <select id="work" style="width: 67%; height: 44px;" >
                                    <option>Option</option>
                                    <option value="processed">Processed</option>
                                    <option value="unprocessed">Unprocessed</option>
                                </select>

                                <button onclick="search3()"><i class="fa fa-search"></i></button>
                            </th>
                            <th style="width: 5%;">
                                <div style="text-align: center;">Note</div>
                                <br>
                                <input id="note" style="width: 67%;" type="text" placeholder="Search..." value="${note}">
                                <button onclick="search4()"><i class="fa fa-search"></i></button>
                            </th>
                            <th style="width: 4.5%;">
                                <div style="margin-bottom: 65px;text-align: center;" >Transaction creator</div>
                            </th>
                            <th style="width: 5%;">
                                <div style="margin-bottom: 35px;text-align: center;"> Creation time</div>
                                <br>
                            </th>
                            <th style="width: 5%;">
                                <div style="text-align: center;margin-bottom: 35px">Last updated</div>
                                <br>

                            </th>
                            <th style="width: 3%;">
                                <!-- Add a button for clearing filters -->
                                <button style="background-color: #bb2d3b" id="clear" onclick="clearFilters()">Unfilter</button>
                                <div style="height:26px"></div>
                                <!-- Không cần filter cho cột này -->
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${listA}" var="p">
                            <tr>
                                <td style="text-align: center;">${p.id}</td>
                                <td style="text-align: center;">${p.money}</td>
                                <td style="text-align: center;">${p.type == false ? '-' : '+'}</td>
                                <td style="text-align: center;">${p.work == false ? 'Unprocessed' : 'Processed'}</td>
                                <td>${p.note}</td>
                                <td>${p.create_by}</td>
                                <td>${p.create_at}</td>
                                <td>${p.update_at}</td>
                                <td>
                                    <button style="background-color: #4dbd74" class="details" data-id="${p.id}" data-toggle="modal">
                                        Details
                                    </button>
                                </td>

                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
                <script>
                    function clearFilters() {
                        document.getElementById("filterMaGiaoDich").value = "";
                        document.getElementById("soTien").value = "";
                        document.getElementById("type").selectedIndex = 0;
                        document.getElementById("work").selectedIndex = 0;
                        // Clear other filter inputs if needed

                        // Lấy các tham số lọc từ các ô filter khác nếu cần
                        var newUrl = "";

                        // Tạo URL mới dựa trên các tham số lọc
                        newUrl = "transhis ";

                        // Thực hiện tải lại trang với URL mới
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    function search() {
                        var id = document.getElementById("filterMaGiaoDich").value;
                        // Lấy các tham số lọc từ các ô filter khác nếu cần
                        var newUrl = "";

                        // Tạo URL mới dựa trên các tham số lọc
                        newUrl = "filter?filterMaGiaoDich=" + id;

                        // Thực hiện tải lại trang với URL mới
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    function search1() {
                        var soTien = document.getElementById("soTien").value;
                        // Lấy các tham số lọc từ các ô filter khác nếu cần
                        var newUrl = "";

                        // Tạo URL mới dựa trên các tham số lọc
                        newUrl = "filterMoney?soTien=" + soTien;

                        // Thực hiện tải lại trang với URL mới
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    function search2() {
                        var type = document.getElementById("type").value;
                        // Lấy các tham số lọc từ các ô filter khác nếu cần
                        var newUrl = "";

                        // Tạo URL mới dựa trên các tham số lọc
                        newUrl = "filterType?type=" + type;

                        // Thực hiện tải lại trang với URL mới
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    function search3() {
                        var work = document.getElementById("work").value;
                        // Lấy các tham số lọc từ các ô filter khác nếu cần
                        var newUrl = "";

                        // Tạo URL mới dựa trên các tham số lọc
                        newUrl = "filterWork?work=" + work;

                        // Thực hiện tải lại trang với URL mới
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    function search4() {
                        var note = document.getElementById("note").value;
                        var newUrl = "filterNote?note=" + note;
                        window.location.href = newUrl;
                    }
                </script>
                <script>
                    // Hàm xử lý việc lọc và kích hoạt tải dữ liệu
                    function filterTransactions() {
                        var input, filter, table, tr, td, i;
                        input = document.getElementById("transactionFilterInput");
                        filter = input.value.toUpperCase();
                        table = document.getElementById("transactionTable");
                        tr = table.getElementsByTagName("tr");

                        // Duyệt qua tất cả các hàng của bảng và ẩn những hàng không khớp với bộ lọc
                        for (i = 0; i < tr.length; i++) {
                            td = tr[i].getElementsByTagName("td")[0]; // Chỉ mục cột cho ID giao dịch
                            if (td) {
                                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                                    tr[i].style.display = "";
                                } else {
                                    tr[i].style.display = "none";
                                }
                            }
                        }
                    }
                </script>

                <!-- Cấu trúc bảng của bạn với dữ liệu động -->
                <table>
                    <thead>
                        <!-- Nội dung tiêu đề -->
                    </thead>
                    <tbody id="transactionTable">
                        <!-- Các hàng dữ liệu động -->
                        <!-- Các phần tử <tr>...</tr> sẽ được chèn vào đây động -->
                    </tbody>
                </table>
                <div id="myModal" class="modal">
                    <div class="modal-content" style="width: 80%;margin-left: 12%;padding: 0">
                        <span class="close" onclick="closeModal()">&times;</span>
                        <div class="modal_body" style="display: block">
                            <div class="deposit__header" >
                                <h2>Transaction History</h2>
                            </div>
                            <div class="content">
                                <table class="inner" style="border: 1px solid black;width: 800px;height: 300px">
                                    <thead>
                                        <tr style="border: 1px solid black">

                                    </thead>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <style>
                    /* Hiển thị modal */
                    .modal {
                        display: none;
                        position: fixed;
                        z-index: 1;
                        left: 0;
                        top: 0;
                        width: 80%;
                        height: 80%;
                        overflow: auto;
                        background-color: rgba(0, 0, 0, 0.4);
                        /* Làm mờ nền */
                    }

                    /* Nội dung modal */
                    .modal-content {
                        background-color: #fefefe;
                        font-size: 140%;
                        margin-top: 90px;
                        padding: 20px;
                        border: 1px solid #888;
                        width: 66.67%;
                        /* 2/3 của màn hình */
                        height: 66.67%;
                        /* 2/3 của màn hình */
                        position: relative;
                        /* Để căn giữa theo chiều ngang và dọc */
                    }

                    .modal_body {
                        position: absolute;
                        /* Cần thiết để căn giữa theo chiều ngang và chiều dọc */
                        top: 50%;
                        /* Căn nội dung lên trên 50% của modal */
                        left: 50%;
                        /* Căn nội dung sang trái 50% của modal */
                        transform: translate(-50%, -50%);
                        /* Dịch chuyển nội dung đi lề trái 50% và lề trên 50% */
                    }

                    /* Đóng modal */
                    .close {
                        color: #aaa;
                        float: right;
                        font-size: 28px;
                        font-weight: bold;
                    }

                    .close:hover,
                    .close:focus {
                        color: black;
                        text-decoration: none;
                        cursor: pointer;
                    }
                </style>

                <script>
                    $(document).ready(function () {
                        $(".details").click(function () {
                            document.getElementById('myModal').style.display = 'block';
                            var formData = {
                                id: $(this).data("id")
                            };
                            $.ajax({
                                type: 'POST',
                                url: "detailsTransHis",
                                data: formData,
                                success: function (response) {
                                    $(".inner").html(response);
                                },
                                error: function () {
                                    // Xử lý lỗi nếu có
                                    alert("Đã xảy ra lỗi khi tải trang");
                                }
                            });
                        });
                    });

                    function closeModal() {
                        document.getElementById('myModal').style.display = 'none';

                    }
                </script>
            </div>
            <a href="home.jsp">
                <button type="button" class="btn btn-primary" onclick="back()"
                        style="background-color: #3498db;font-size: 125%;">Trang chủ</button>
                <script src="js/ManagerProduct.js" type="text/javascript"></script>
            </a>
        </div>
        <%@include file="Component/footer.jsp" %>
    </body>
</html>

