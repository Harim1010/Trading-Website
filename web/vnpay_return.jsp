<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vnpay.common.Config"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="DAO.DepositDAO" %>
<%@page import="DAO.WalletDAO" %>
<%@page import="model.Wallet" %>
<%@page import="java.util.LinkedList" %>
<%@page import="model.Transaction" %>
<%@page import="java.util.Queue" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="/vnpay_jsp/assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="assets/css/TransactionDetails.css"/>
        <<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap-grid.css"/>
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }
            //trc khi chuyen sang cho vnpay thi status mac dinh la pending, neu successed thi doi status sang thanh cong va soat lai 1 lan nua thi dau den cuoi xem co giao dich nao con pending thi xu li not (trong bang vnpay_transaction, nghia la cu thuc hien giao dich la luu vao db la pending r neu ma xu li xong thi cap nhat xem successed hay failed neu failed thi cap nhat vao trong bang wallet la account bang bao nhieu do ben vnpay tra ve status thi cap nhat status trong bang vnpay_transaction sau do neu thanh cong thi lai bat dau do lai xem co cai nao van con pending thi xu li not (neu van chua xu li dc truong hop 2 accout cung an thanh toan 1 luc thi phai dung queue(FIFO) de luu giao dich de xu li tung cai 1) neu thanh cong thi luu vao 1 cai queue r thuc hien tung cai 1 tranh reload lai trang thi lien tuc cong tien
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);

        %>
        <!--Begin display -->
        <%@include file="Component/header.jsp" %>
        <div id="transaction_form" class="container" style="margin-top: 100px" >
            <div class="header clearfix">
                <h3 class="text-muted" style="color: white">KẾT QUẢ THANH TOÁN</h3>
            </div>
            <table class="table-responsive">
                <tr class="form-group">
                    <td>1. Mã giao dịch thanh toán:</td>
                    <td style="padding-left: 300px"><%=request.getParameter("vnp_TxnRef")%></td>
                </tr>    
                <tr class="form-group">
                    <td>2. Số tiền:</td>
                    <td style="padding-left: 443px"><%= Double.parseDouble(request.getParameter("vnp_Amount")) / 100 %></td>
                </tr>  
                <tr class="form-group">
                    <td >3. Mô tả giao dịch:</td>
                    <td style="padding-left: 280px"><%=request.getParameter("vnp_OrderInfo")%></td>
                </tr> 
                <tr class="form-group">
                    <td >4. Mã ngân hàng thanh toán:</td>
                    <td style="padding-left: 300px"><%=request.getParameter("vnp_BankCode")%></td>
                </tr> 
                <tr class="form-group">
                    <td>5. Thời gian thanh toán:</td>
                    <td style="padding-left: 300px"><%=request.getParameter("vnp_PayDate")%></td>
                </tr> 
                <tr class="form-group">
                    <td style="padding-left: 50px">6. Tình trạng giao dịch:</td>
                    <td style="padding-left: 200px" id="status" style="text-align: center">
                        <%
                            DepositDAO dao = new DepositDAO();
                            WalletDAO wadb = new WalletDAO();
                            HttpSession ss = request.getSession();
                            Wallet wallet = (Wallet)ss.getAttribute("walletCurrent"); 
                            double amount = Double.parseDouble(request.getParameter("vnp_Amount"))/100;
                            boolean isInserted = false;
                            Transaction a = new Transaction("Pending",wallet.getWallet_id(),request.getParameter("vnp_TxnRef"),request.getParameter("vnp_PayDate"),request.getParameter("vnp_OrderInfo"),request.getParameter("vnp_BankCode"));
                            String notice = "";
                            if (signValue.equals(vnp_SecureHash)) {    
                                List<Transaction> transaction = dao.getListTransaction(wallet);
                                Queue<Transaction> queue = new LinkedList<>();
                                //vnp_TxnRef
                                
                                //dao.setStatus("Pending",wallet);
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    
                                    
//                                    for (Transaction item : transaction) {
//                                        queue.add(item);
//                                    }
                                
                                for (Transaction transaction1 : transaction) {
                                if (transaction1 != null && transaction1.getTime() != null && transaction1.getPayment_Code()!=null) {
                                if (transaction1.getWallet_id() == wallet.getWallet_id() && transaction1.getTime().equals(request.getParameter("vnp_PayDate")) && (transaction1.getPayment_Code().equals(request.getParameter("vnp_TxnRef"))) && transaction1.getDescription().equals(request.getParameter("vnp_OrderInfo")) && transaction1.getBankCode().equals(request.getParameter("vnp_BankCode")) && transaction1.getStatus().equals("Success")) {
                                //System.out.println(transaction1.getTime());
                                isInserted = true;
                                //System.out.println("Reload lai a ku");
                                //dao.setTransaction(a);
                                }
                                
                                }
                                }
                                if (isInserted == true) {
                                out.print("This transaction has already been done!");
                                } else  {
                                out.print("Done! Please return home to check ");
                                //out.print("1 lan");
                                //out.print(isInserted);
                                dao.setTransaction(a);
//                                List<Transaction> tran2 = dao.loadTransaction(wallet,a);
//                                for (Transaction item1 : tran2) {
//                                    queue.add(item1);
//                                }
                                //wadb.setAmountWallet(wallet, amount);
                                queue.add(a);
                                //out.print("before"+ queue);
                                }
                                wadb.processQueue(queue, wallet, amount, request.getParameter("vnp_TxnRef"));
                                
                                //out.print("after"+queue);
                                //wadb.processQueue(queue, wallet, amount, request.getParameter("vnp_TxnRef"));
                                //wadb.UpdateTransaction(queue, wallet, amount,request.getParameter("vnp_TxnRef"));
                                
                                //if(queue != null){
                                //wadb.UpdateTransaction(queue,wallet,amount);}
                                //out.print(queue);
                                //out.print(a);
                                   
                                    //while (!queue.isEmpty()) {
                                    //Transaction peek = queue.peek(); 

                                    //if (peek.getStatus().equals("Pending")) {
                                    
                                    //dao.updateStatus(wallet, peek.getID(), "Success");
                                    //ham cong tien
                                    //wadb.setAmountWallet(wallet, amount);
                                    //}

                                    //queue.remove(); 

                                    //}

                                    //out.print(queue.peek().getStatus());
                                    //ham lay wallet day ra day len session
                                    Wallet wa2 = wadb.GetWalletByID(wallet.getWallet_id());
                                    //ham update status
                                    
                                    //out.print("Success");
                                    //dao.updateStatus(wallet,"Success");
                                    //double amount2 = dao.GetWalletByDeposit(wallet.getWallet_id());
                                    
                                    //double total = amount2+amount;
                                    //dao.setAmount(wallet,total);
                                    //Wallet wa2 = wadb.setWalletByID2(wallet.getWallet_id(),(total));
                                    ss.setAttribute("walletCurrent",wa2);
                                } else {
                                    out.print("Failed! Số tiền bạn nạp phải lớn hơn 10,000VNĐ");
                                    //dao.updateStatus(wallet,"Failed");
                                }
                         
                            } else {
                                    out.print("Invalid signature");
                            }
                        %></td>
                </tr> 
                <%
                String transactionStatus = request.getParameter("vnp_TransactionStatus");
                boolean isSuccess = "00".equals(transactionStatus);
                %>
                <tr>
                    <td style="display: block; margin: 0 auto; padding-top: 10px; padding-bottom: 10px">
                        <% if (isSuccess) { %>
                        <input type="submit" value="Return home" onclick="redirectToHome()">
                        <input type="submit" value="Make a new request" onclick="createNewTransaction()">
                        <% } else { %>
                        <input type="submit" value="Make a new request" onclick="createNewTransaction()">
                        <% } %>
                    </td>
                </tr>


            </table>
        </div>
    </body>
    <%@include file="Component/footer.jsp" %>
    <script>
        function redirectToHome() {
            window.location.href = "/DemoSWP391/home.jsp";
        }

        
        function createNewTransaction() {
            window.location.href = "/DemoSWP391/Deposit1.jsp";
        }
    </script>

</html>
