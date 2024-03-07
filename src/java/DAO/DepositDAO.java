/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Transaction;
import model.Wallet;

/**
 *
 * @author User
 */
public class DepositDAO {
    
    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về
    //    public int insertStatus(Wallet wallet,int amount, String status) {
    //        String sql = "insert into dtb_demo.vnpay_transaction(status,Wallet_id) values (?,?)";
    //        try {
    //            con = new DBContext().connection; //connect sql
    //            ps = con.prepareStatement(sql);
    //            ps.setString(1, status);
    //            ps.setInt(2, wallet_id);
    //            ps.executeUpdate();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    return amount;
    //}

    public void changeAmount(String balance, Wallet wallet) {
        String sql = "update wallet set balance = ? where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, balance);
            ps.setInt(2, wallet.getWallet_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setStatus(String status, Wallet wallet) {
        String sql = "insert into dtb_demo.vnpay_transaction(status,Wallet_id) values (?,?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, wallet.getWallet_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setTransaction(Transaction transaction){
        String sql = "insert into vnpay_transaction(status,Wallet_id,Payment_code,Time,Description,BankCode) values (?,?,?,?,?,?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, transaction.getStatus());
            ps.setInt(2, transaction.getWallet_id());
            ps.setString(3, transaction.getPayment_Code());
            ps.setString(4, transaction.getTime());
            ps.setString(5, transaction.getDescription());
            ps.setString(6, transaction.getBankCode());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Transaction> getListTransaction(Wallet wallet) {
        String sql = "select * from vnpay_transaction where Wallet_id = ?";
        List<Transaction> a = new ArrayList<>();
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, wallet.getWallet_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a.add(new Transaction(rs.getString("Status"), rs.getInt("Wallet_id"), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    
    
    public void updateStatus(Wallet wallet,String payment_code, String status) {
        String sql = "update dtb_demo.vnpay_transaction set status = ? where Wallet_id = ? and Payment_code = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, wallet.getWallet_id());
            ps.setString(3, payment_code);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setAmount(Wallet wallet, double amount) {
        String sql = "update wallet set balance = ? where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, wallet.getWallet_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double GetWalletByDeposit(int id) {
        String sql = "SELECT * FROM dtb_demo.wallet where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wallet wid = new Wallet();
                wid.setWallet_id(rs.getInt(1));
                wid.setUser_id(rs.getInt(2));
                wid.setBalance(rs.getDouble(3));
                return wid.getBalance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Wallet GetWalletbyID(Wallet wallet) {
        String sql = "SELECT * FROM dtb_demo.wallet where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, wallet.getUser_id());
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
    public List<Transaction> loadTransaction(Wallet wallet, Transaction a){
        List<Transaction> list = new ArrayList<>();
        String sql = "select * from vnpay_transaction where wallet_id = ? and status = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, wallet.getWallet_id());
            ps.setString(2, a.getStatus());
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transaction(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        DepositDAO dao = new DepositDAO();
        //System.out.println(dao.getListTransaction(new Wallet(1)));
        dao.setTransaction(new Transaction("pending", 1, "1", "!", "1", "!"));
        
    }
}
