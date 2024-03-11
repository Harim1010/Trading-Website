/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Wallet;
import model.Withdrawal;
import model.trans_history;
/**
 *
 * @author caoth
 */
public class TransactionHistoryDAO {
    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về
    
    public void InsertIntoTransactionHistory(int user_id,double money, boolean type, String note, String create_by, Timestamp create_at, Timestamp update_at) {
        String sql = "INSERT INTO dtb_demo.`transaction_history` ( user_id,money,type,note,create_by,create_at,update_at)"
                + " VALUES (?,?, ?, ?, ?,?,?);";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql); 
            ps.setInt(1, user_id);
            ps.setDouble(2,money);
            ps.setBoolean(3, type);
            ps.setString(4, note);
            ps.setString(5, create_by);
            ps.setTimestamp(6, create_at);
            ps.setTimestamp(7, update_at);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public List<trans_history> getTrans( int user_id) {
        List<trans_history> list = new ArrayList<>();
        String sql = "select * from transaction_history where user_id = ? order by id desc";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<trans_history> getTransById(int user_id, int id) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "select * from transaction_history where user_id = ? and id = ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByMoney( int user_id,double money) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "select * from transaction_history where user_id = ? and money = ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setDouble(2, money);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByBoolean(int user_id, boolean input, String statement) {
        ArrayList<trans_history> list = new ArrayList<>();
            String sql = "select * from transaction_history where user_id = ? and " + statement + " = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setBoolean(2, input);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByNote(int user_id,String note) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "select * from transaction_history where user_id = ? and note LIKE ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, "%" + note + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public trans_history getDetailsTransById(int user_id, int id) {
        String sql = "select * from transaction_history where user_id = ? and id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new trans_history(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7), // Sử dụng account_sold_id
                        rs.getTimestamp(8),
                        rs.getTimestamp(9)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        TransactionHistoryDAO th = new TransactionHistoryDAO();
        System.out.println(th.getTransByBoolean(1, true, "type").toString());
    }
}
