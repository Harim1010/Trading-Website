/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Transaction;
import model.Wallet;
import model.Withdrawal;

/**
 *
 * @author Admin
 */
public class WalletDAO {

    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về

    public Wallet GetWalletByID(int id) {
        String sql = "SELECT * FROM dtb_demo.wallet where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wallet w = new Wallet();
                w.setWallet_id(rs.getInt(1));
                w.setUser_id(rs.getInt(2));
                w.setBalance(rs.getDouble(3));
                return w;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet setWalletByID2(int id, double amount) {
        String sql = "SELECT * FROM dtb_demo.wallet where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wallet w = new Wallet();
                w.setWallet_id(rs.getInt(1));
                w.setUser_id(rs.getInt(2));
                w.setBalance(amount);
                return w;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet setAmountWallet(Wallet wallet, double amount) {
        String sql = "update wallet set balance = balance + ? where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, wallet.getWallet_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet GetWalletByUserID(int id) {
        String sql = "SELECT * FROM dtb_demo.wallet where user_id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wallet w = new Wallet();
                w.setWallet_id(rs.getInt(1));
                w.setUser_id(rs.getInt(2));
                w.setBalance(rs.getDouble(3));
                return w;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Wallet GetWalletByWithdrawal(Withdrawal withdrawal) {
        String sql = "SELECT * FROM dtb_demo.wallet where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, withdrawal.getWallet_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wallet w = new Wallet();
                w.setWallet_id(rs.getInt(1));
                w.setUser_id(rs.getInt(2));
                w.setBalance(rs.getDouble(3));
                return w;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void UpdateWalletByID(Wallet w) {
        String sql = "update wallet\n"
                + "set balance = ? where id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setDouble(1, w.getBalance());
            ps.setInt(2, w.getWallet_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateTransaction(Queue<Transaction> queue, Wallet wallet, double amount, String payment_code) {
        DepositDAO dao = new DepositDAO();
        WalletDAO wadb = new WalletDAO();

        // Lấy ra giao dịch đầu tiên từ hàng đợi
        Transaction first = queue.peek(); // Thay đổi poll() thành peek() để không loại bỏ giao dịch từ hàng đợi

        // Kiểm tra xem giao dịch đầu tiên có tồn tại và có đúng điều kiện để cập nhật không
        if (first != null && wallet != null
                && first.getStatus().equals("Pending")
                && first.getWallet_id() == wallet.getWallet_id()
                && first.getPayment_Code().equals(payment_code)) {

            // Cập nhật số tiền chỉ khi không có phần tử nào khác trong hàng đợi cùng ID và cùng mã thanh toán
            if (queue.stream().filter(t -> t.getWallet_id() == wallet.getWallet_id() && t.getPayment_Code().equals(payment_code)).count() == 1) {
                wadb.setAmountWallet(wallet, amount);
                dao.updateStatus(wallet, first.getPayment_Code(), "Success");
            }
        }

        // Loại bỏ giao dịch đầu tiên từ hàng đợi sau khi xử lý
        queue.poll();

        // Gọi đệ quy để xử lý các giao dịch còn lại trong hàng đợi
        if (!queue.isEmpty()) {
            UpdateTransaction(queue, wallet, amount, payment_code);
        }
    }

    public void processQueue(Queue<Transaction> queue, Wallet wallet, double amount, String payment_code) {
        DepositDAO dao = new DepositDAO();
        WalletDAO wadb = new WalletDAO();

        double totalAmount = 0; // Khởi tạo biến tích lũy số tiền

        // Lặp qua từng phần tử trong hàng đợi
        while (!queue.isEmpty()) {
            // Lấy ra phần tử đầu tiên từ hàng đợi
            Transaction transaction = queue.poll();

            // Kiểm tra xem giao dịch và điều kiện có khớp với đối số truyền vào không
            if (transaction != null && wallet != null
                    && transaction.getStatus().equals("Pending")
                    && transaction.getWallet_id() == wallet.getWallet_id()
                    && transaction.getPayment_Code().equals(payment_code)) {
                dao.updateStatus(wallet, transaction.getPayment_Code(), "Success");
                if (amount > 0 || transaction.getStatus().equals("Success")) {
                    wadb.setAmountWallet(wallet, amount);
                }
            }
        }

        // Cập nhật số tiền trong tài khoản một lần duy nhất sau khi xử lý toàn bộ hàng đợi
    }

    public static void main(String[] args) {
        DepositDAO dao = new DepositDAO();
        WalletDAO wadb = new WalletDAO();

        // Lấy danh sách các giao dịch từ cơ sở dữ liệu
        List<Transaction> transaction = dao.getListTransaction(new Wallet(4));

        // Khởi tạo hàng đợi và thêm các giao dịch vào hàng đợi
        Queue<Transaction> queue = new LinkedList<>();
        for (Transaction item : transaction) {
            queue.add(item);
//        }
        }

        // Gọi phương thức UpdateTransaction() để xử lý hàng đợi
        //wadb.UpdateTransaction(queue, new Wallet(4), 1000, "17783652");
//            List<Transaction> tran2 = dao.loadTransaction(new Wallet(4), new Transaction(1, "Success", 4));
//            for (Transaction item1 : tran2) {
//                if (!queue.contains(item1)) {
//                    queue.add(item1);
//                }
//            }
//            System.out.println(queue);
//            wadb.processQueue(queue, new Wallet(4), 10000, "34119331");
    }

    //Iterator<Transaction> iterator = queue.iterator();
//        for (Transaction item : transaction) {
//            if (item.getStatus().contains("Pending")) {
//                queue.add(item);
//            }
//        }
////        System.out.println("before:");
////        System.out.println(queue);
//        Transaction first = queue.poll();
//        System.out.println(first);
//        if (first.getStatus().equals("Pending") && first.getPayment_Code().equals("92092400")) {
//            wadb.setAmountWallet(new Wallet(4), 10000);
//            dao.updateStatus(new Wallet(4), first.getPayment_Code(), "Success");
//        } else {
//            System.out.println("chua xu li den");
//        }
}

//        System.out.println("after");
//        System.out.println(queue);
//System.out.println(first);
//        if(first != null){
//            first.setStatus("Pending");
//        }
//        while (!queue.isEmpty()) {
//            //ham cong tien
//            Transaction element = queue.poll();
//            if (element != null) {
//                //wadb.setAmountWallet(wallet, amount);
//                element.setStatus("Success");
//            }
//        }
//        while (!queue.isEmpty()) {
//            Transaction peek = queue.poll(); // Lấy ra phần tử đầu tiên trong hàng đợi
//
//            if (peek.getStatus().equals("Pending")) {
//                // Nếu trạng thái của phần tử là "Pending", cập nhật nó thành "Success" và thực hiện các thao tác khác
//                wadb.setAmountWallet(wallet, amount);
//                dao.updateStatus(wallet, peek.getID(), "Success");
//            }
//
//            queue.remove(); // Loại bỏ phần tử hiện tại khỏi hàng đợi
//
//            System.out.println(peek.getStatus()); // In ra trạng thái của phần tử vừa xem
//        }
//        Iterator<Transaction> iterator = queue.iterator();
//        while (iterator.hasNext()) {
//            Transaction element = iterator.next();
//            if(element.getStatus().equals("Pending")){
//                //wadb.setAmountWallet(wallet, amount);
//                dao.updateStatus(wallet, element.getID(), "Success");
//            }
//            // Thực hiện xử lý cho phần tử này, ví dụ:  
//            System.out.println("Xử lý phần tử: " + element);
//        }
//        Transaction transaction1 = new Transaction("Pending", 1, "2", "1","des", "1");
//        boolean isInserted = false;
//        for (Transaction transaction2 : dao.getListTransaction(new Wallet(1))) {
//            if (transaction2 != null && transaction2.getTime() != null && transaction2.getPayment_Code() != null) {
//                if (transaction2.getWallet_id() == 1 && transaction2.getTime().equals("1") && (transaction2.getPayment_Code().equals("2")) && transaction2.getDescription().equals("des") && transaction2.getBankCode().equals("1") && transaction2.getStatus().equals("Pending")) {
//                    //System.out.println(transaction1.getTime());
//                    isInserted = true;
//                    //System.out.println("Reload lai a ku");
//                    //dao.setTransaction(a);
//                }
//                else {
//                    //System.out.println("chua co");
////                    dao.setTransaction(transaction1);
//                    System.out.println("chua co");
//                }
//                
//            }
//        }
////        System.out.println(isInserted);
////        
//        if (isInserted == true) {
//            System.out.println("reload lai a ku");
//            
//        } else  {
//            System.out.println("1 lan");
//            dao.setTransaction(transaction1);
//            
//            
//        }
//        for (Transaction transaction2 : dao.getListTransaction(new Wallet(1))) {
//                System.out.println(transaction2.toString());
//                if (transaction2 != null && transaction2.getTime() != null && transaction2.getPayment_Code() != null) {
//                if (transaction2.getWallet_id() == 1 && transaction2.getTime().equals("1") && (transaction2.getPayment_Code().equals("1")) && transaction2.getDescription().equals("1") && transaction2.getBankCode().equals("1") && transaction2.getStatus().equals("Pending")){
//                    System.out.println("co roi");
//                }
//                else {
//                    System.out.println("chua co");
//                }
//        }
//                else {
//                    System.out.println("null r");
//                }
//            if(transaction1.getTime().contains("!")){
//                dao.setTransaction(new Transaction("1", 1,"1111", "12212", "12312321", "1212312"));
//            }
//if (transaction1 != null && transaction1.getTime() != null) {
//    if (transaction1.getTime().equalsIgnoreCase("1")) {
//        // Đoạn mã xử lý khi thời gian của transaction1 là "1"
//        System.out.println(transaction1.getTime());
//    } else {
//        // Đoạn mã xử lý khi thời gian của transaction1 không phải là "1"
//    }
//} else {
//    // Đoạn mã xử lý khi transaction1 hoặc giá trị thời gian của nó là null
//}
//System.out.println(dao.getListTransaction(new Wallet(1)));
//    }
//    dao.getListTransaction(new Wallet(1));

