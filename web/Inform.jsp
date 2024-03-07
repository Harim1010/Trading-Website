<%-- 
    Document   : Inform
    Created on : Feb 17, 2024, 2:35:23 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Web Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #948e8e;
        }

        .inform {
            width: 850px;
            height: 780px;
            background-color: rgb(252, 252, 255);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: rgb(11, 11, 11);
            overflow: visible;
            margin-top: 20px;
            margin-bottom: 20px;
            position: relative;
            /* Thêm thuộc tính position: relative; */
        }

        .footer {
            margin-top: auto;
            width: 100%;
            text-align: center;
        }

        .header {
            background-color: #97b6f3;
            display: flex;
            justify-content: space-between;
            width: 100%;
            padding: 0;
            position: absolute;
            top: 0;
            right: 0;
            padding-right: 0px;
        }

        #qr img {
            height: 400px;
            width: 400px;
        }

        .close-btn {
            background: none;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        .content {
            text-align: left;
        }

        .content li {
            text-align: left;
        }

        .attention{
            padding-left: 60px;
            margin-top: 4px;
            text-align: left;
        }

        .close-btn {
            color: black;
            border: 0.5px solid rgb(15, 14, 14);
            padding: 10px;
            background-color: red;
        }

        h3 {
            padding-left: 20px;
            padding-top: 10px;
            padding-bottom: 0;
        }

        .content {
            display: inline-block;
        }

        h2 {
            margin-top: 20px;
        }

        h3 {
            color: red;
            font-size: 25px;
        }

        h4 {
            padding-top: 0;
            font-size: 16px;
        }

        .footer {
            text-align: left;
            font-size: 16px;
            padding-left: 2px;
            margin-top: 2px;
            font-weight: bold;
        }

        #contact {
            padding-left: 20px;
        }

        #please {
            color: rgb(11, 163, 239);
            font-weight: bold;
        }

        span {
            color: rgb(229, 47, 47);
            font-weight: bold;
        }

        b{
            font-weight: 400px;
            font-size: 20px;
        }
        
    </style>
</head>

<body>
    <div class="inform">
        <div class="noidung">
            <div class="header">
                <h3>Inform</h3>
                <button class="close-btn" onclick="goBack()">X</button>
            </div>
            <h2>Vui lòng chuyển khoản đúng theo nội dung dưới đây</h2>
            <div id="qr"><img
                    src="https://img.vietqr.io/image/BIDV-3980881611-compact.png?amount=10000&addInfo=D${sessionScope.qr}V11003H&accountName=DUONG%20VAN%20HOAN">
            </div>
            <div class="content">
                <ul>
                    <li>Số tiền: <span>${requestScope.amount}<u>đ</u></span></li>
                    <li>Nội dung chuyển khoản: <span>D${sessionScope.qr}V11003H</span></li>
                    <li>Tên chủ tài khoản: <span>DUONG VAN HOAN</span></li>
                    <li>Số tài khoản: <span>3980881611</span></li>
                    <li><span>Ngân hàng TMCP Đầu tư và Phát triển Việt Nam</span></li>
                </ul>
            </div>
            <h3>Chú ý: Mỗi mã QRCode chỉ chuyển 1 lần duy nhất</h3>
            <h4>Nếu chuyển thủ công điền sai thông tin chuyển khoản hoặc chuyển nhiều lần cùng 1 mã giao dịch, hệ thống
                sẽ:</h4>
            <div class="attention">
                <ul>
                    <li><b>không</b> cộng tiền vào tài khoản của quý khách</li>
                    <li><b>không</b> hoàn tiền lại cho quý khách</li>
                    <li><b>không</b> chịu trách nhiệm bồi thường về khoản tiền chuyển nhầm hoặc thừa</li>
                </ul>
            </div>
            <div class="footer">
                <div id="please">Vui lòng chờ đợi 1 vài phút để hệ thống cập nhật số dư sau khi đã chuyển khoản.</div>
                <div>Nếu đợi 20 phút sau khi chuyển khoản không thấy cập nhật thông tin giao dịch, vui lòng liên hệ QTV:
                </div>
                <div id="contact">&#x2022; Zalo/Phone <span>(Message Only)</span>: 0868.970.828</div>
            </div>
        </div>
    </div>
    <script>
        function goBack() {
            window.history.go(-1); 
        }
    </script>
</body>

</html>
