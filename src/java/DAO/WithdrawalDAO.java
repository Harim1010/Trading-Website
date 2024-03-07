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
import java.util.List;
import model.Account;
import model.Wallet;
import model.Withdrawal;

/**
 *
 * @author Admin
 */
public class WithdrawalDAO {

    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về

    public Withdrawal GetWithdrawalByID(int id) {
        String sql = "SELECT * FROM dtb_demo.withdrawal where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Withdrawal w = new Withdrawal();
                w.setWithdrawal_id(rs.getInt(1));
                w.setWallet_id(rs.getInt(2));
                w.setAmount(rs.getDouble(3));
                w.setCreate_datetime(rs.getTimestamp(4));
                w.setStatus(rs.getString(5));
                w.setBank_user(rs.getString(6));
                w.setBank_number(rs.getString(7));
                w.setBank_name(rs.getString(8));
                w.setUpdate_datetime(rs.getTimestamp(9));
                return w;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void UpdateWithdrawalByID(Withdrawal w) {
        String sql = "update withdrawal\n"
                + "set amount = ?, bank_user = ?, bank_number = ?, bank_name = ?, update_datetime = ? where id = ?";
        try {
            con = new DBContext().connection; // Kết nối CSDL SQL
            ps = con.prepareStatement(sql); // Khởi tạo PreparedStatement
            ps.setDouble(1, w.getAmount());
            ps.setString(2, w.getBank_user());
            ps.setString(3, w.getBank_number());
            ps.setString(4, w.getBank_name());
            ps.setTimestamp(5, w.getUpdate_datetime());
            ps.setInt(6, w.getWithdrawal_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public void UpdateWithdrawalByID2(Withdrawal w) {
        String sql = "update withdrawal\n"
                + "set status = ?, update_datetime = ? where id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql); 
            ps.setString(1, w.getStatus());
            ps.setTimestamp(2, w.getUpdate_datetime());
            ps.setInt(3, w.getWithdrawal_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public List<Withdrawal> GetAllWithdrawal() {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "select * from dtb_demo.withdrawal order by id desc";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Withdrawal(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Withdrawal> GetAllWithdrawalByWalletID(int id) {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "select * from dtb_demo.withdrawal where wallet_id = ? order by id DESC";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Withdrawal(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void InsertWithdrawal(Withdrawal w) {
        int ketQua = 0;
        String sql = "INSERT INTO `dtb_demo`.`withdrawal` (`wallet_id`, `amount`, `create_datetime`, `status`, `bank_user`, `bank_number`, `bank_name`, `update_datetime`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, w.getWallet_id());
            ps.setDouble(2, w.getAmount());
            ps.setTimestamp(3, w.getCreate_datetime());
            ps.setString(4, w.getStatus());
            ps.setString(5, w.getBank_user());
            ps.setString(6, w.getBank_number());
            ps.setString(7, w.getBank_name());
            ps.setTimestamp(8, w.getUpdate_datetime());
            ketQua = ps.executeUpdate();
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Withdrawal> GetAllWithdrawalByStatus(String status) {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "select * from dtb_demo.withdrawal where status = ? order by id desc";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Withdrawal(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Withdrawal> GetAllWithdrawalByStatusAndWalletID(int wallet_id, String status) {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "select * from dtb_demo.withdrawal where wallet_id = ? and status = ? order by id desc";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, wallet_id);
            ps.setString(2, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Withdrawal(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        
    }

}
