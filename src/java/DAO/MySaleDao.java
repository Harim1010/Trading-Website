/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import java.math.BigDecimal;
import model.*;
import model.Account;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Xuanphuc
 */
public class MySaleDao {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public List<Intermediary_order> GetAllIntermediary_seller(int id) {
        List<Intermediary_order> list = new ArrayList<>();
        String sql = "SELECT * FROM dtb_demo.intermediary_order where account_sold_id =? order by id desc";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intermediary_order io = new Intermediary_order();
                io.setId(rs.getInt(1));
                io.setDescription(rs.getString(2));
                io.setPrice(rs.getDouble(3));
                io.setFee_type(rs.getBoolean(4));
                io.setContact(rs.getString(5));
                io.setHide_description(rs.getString(6));
                io.setPublic_status(rs.getBoolean(7));
                io.setCreated_at(rs.getString(8));
                io.setUpdated_at(rs.getString(9));
                io.setAccount_sold_id(rs.getInt(10));
                io.setAccount_buy_id(rs.getInt(11));
                io.setProcessed(rs.getBoolean(12));
                io.setOrder_status(rs.getString(13));
                io.setIntermediary_fee(rs.getInt(14));
                io.setLink_to(rs.getString(15));
                io.setTitle(rs.getString(16));
                list.add(io);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Intermediary_order GetOrderByID(int id) {
        String sql = "SELECT * FROM dtb_demo.intermediary_order where id =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intermediary_order io = new Intermediary_order();
                io.setId(rs.getInt(1));
                io.setDescription(rs.getString(2));
                io.setPrice(rs.getDouble(3));
                io.setFee_type(rs.getBoolean(4));
                io.setContact(rs.getString(5));
                io.setHide_description(rs.getString(6));
                io.setPublic_status(rs.getBoolean(7));
                io.setCreated_at(rs.getString(8));
                io.setUpdated_at(rs.getString(9));
                io.setAccount_sold_id(rs.getInt(10));
                io.setAccount_buy_id(rs.getInt(11));
                io.setProcessed(rs.getBoolean(12));
                io.setOrder_status(rs.getString(13));
                io.setIntermediary_fee(rs.getInt(14));
                io.setLink_to(rs.getString(15));
                io.setTitle(rs.getString(16));
                return io;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void InsertProduct(Intermediary_order io) {
        String sql = "INSERT INTO intermediary_order ( description, price, fee_type, contact, hide_description, "
                + "public_status, created_at, updated_at, account_sold_id,account_buy_id, order_status, intermediary_fee, link_to, title) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, io.getDescription());
            ps.setDouble(2, io.getPrice());
            ps.setBoolean(3, io.isFee_type());
            ps.setString(4, io.getContact());
            ps.setString(5, io.getHide_description());
            ps.setBoolean(6, io.isPublic_status());
            ps.setString(7, io.getCreated_at());
            ps.setString(8, io.getUpdated_at());
            ps.setInt(9, io.getAccount_sold_id());
            ps.setInt(10, io.getAccount_buy_id());
            ps.setString(11, io.getOrder_status());
            ps.setDouble(12, io.getIntermediary_fee());
            ps.setString(13, io.getLink_to());
            ps.setString(14, io.getTitle());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("InsertProduct" + e.getMessage());
        }
    }

    public void DeleteProduct(int id) {
        String sql = "DELETE FROM `dtb_demo`.`intermediary_order` WHERE id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateProduct(Intermediary_order io) {
        String sql = "UPDATE intermediary_order SET description=?, price=?, fee_type=?, contact=?,"
                + " hide_description=?, public_status=?,created_at=?, updated_at=?, account_sold_id=?,"
                + "account_buy_id=?, order_status=?, intermediary_fee=?, link_to=?, title=? WHERE id=?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, io.getDescription());
            ps.setDouble(2, io.getPrice());
            ps.setBoolean(3, io.isFee_type());
            ps.setString(4, io.getContact());
            ps.setString(5, io.getHide_description());
            ps.setBoolean(6, io.isPublic_status());
            ps.setString(7, io.getCreated_at());
            ps.setString(8, io.getUpdated_at());
            ps.setInt(9, io.getAccount_sold_id());
            ps.setInt(10, io.getAccount_buy_id());
            ps.setString(11, io.getOrder_status());
            ps.setDouble(12, io.getIntermediary_fee());
            ps.setString(13, io.getLink_to());
            ps.setString(14, io.getTitle());
            ps.setInt(15, io.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateProduct: " + e.getMessage());
        }
    }

    public List<Intermediary_order> GetIoByFilter(String Id, String OrderStatus, String Title, String Contact,
            String PublicStatus, String PriceFrom, String PriceTo, String FeeType, String Created_at1,
            String Created_at2, String Update_at1, String Update_at2, int account_sold_id) {
        List<Intermediary_order> list = new ArrayList<>();
        String sql = "SELECT * FROM dtb_demo.intermediary_order WHERE id LIKE ? AND order_status LIKE ? "
                + "AND title LIKE ? AND contact LIKE ? ";
        if (!PublicStatus.isEmpty()) {
            sql += "AND public_status = ?";
        }
        if (!FeeType.isEmpty()) {
            sql += "AND fee_type = ?";
        }
        if (!PriceFrom.isEmpty() && !PriceTo.isEmpty()) {
            sql += " AND price BETWEEN ? AND ?";
        }
        if (!Created_at1.isEmpty() && !Created_at2.isEmpty()) {
            sql += " AND created_at BETWEEN ? AND ?";
        }
        if (!Update_at1.isEmpty() && !Update_at2.isEmpty()) {
            sql += " AND updated_at BETWEEN ? AND ?";
        }
        sql += "and account_sold_id =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            int paramIndex = 1;
            ps.setString(paramIndex++, "%" + Id + "%");
            ps.setString(paramIndex++, "%" + OrderStatus + "%");
            ps.setString(paramIndex++, "%" + Title + "%");
            ps.setString(paramIndex++, "%" + Contact + "%");
            if (!PublicStatus.isEmpty()) {
                ps.setString(paramIndex++, PublicStatus);
            }
            if (!FeeType.isEmpty()) {
                ps.setString(paramIndex++, FeeType);
            }
            if (!PriceFrom.isEmpty() && !PriceTo.isEmpty()) {
                ps.setString(paramIndex++, PriceFrom);
                ps.setString(paramIndex++, PriceTo);
            }
            if (!Created_at1.isEmpty() && !Created_at2.isEmpty()) {
                ps.setString(paramIndex++, Created_at1);
                ps.setString(paramIndex++, Created_at2);
            }
            if (!Update_at1.isEmpty() && !Update_at2.isEmpty()) {
                ps.setString(paramIndex++, Update_at1);
                ps.setString(paramIndex++, Update_at2);
            }
            ps.setInt(paramIndex++, account_sold_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intermediary_order io = new Intermediary_order();
                io.setId(rs.getInt(1));
                io.setDescription(rs.getString(2));
                io.setPrice(rs.getDouble(3));
                io.setFee_type(rs.getBoolean(4));
                io.setContact(rs.getString(5));
                io.setHide_description(rs.getString(6));
                io.setPublic_status(rs.getBoolean(7));
                io.setCreated_at(rs.getString(8));
                io.setUpdated_at(rs.getString(9));
                io.setAccount_sold_id(rs.getInt(10));
                io.setAccount_buy_id(rs.getInt(11));
                io.setProcessed(rs.getBoolean(12));
                io.setOrder_status(rs.getString(13));
                io.setIntermediary_fee(rs.getInt(14));
                io.setLink_to(rs.getString(15));
                io.setTitle(rs.getString(16));
                list.add(io);
            }
            return list;
        } catch (Exception e) {
            System.out.println("GetIoByFilter: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int GetLastID() {
        String sql = "select id from intermediary_order order by id desc limit 1";
        int id = 0;
        try {
            con = new DBContext().connection;
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void UpDateLink(String link, int id) {
        String sql = "update intermediary_order set link_to = ? where id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareCall(sql);
            ps.setString(1, link);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetBuyerName(int id) {
        String username = "";
        String sql = "select account.username from intermediary_order join account "
                + "on intermediary_order.account_buy_id=account.id where intermediary_order.id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
    
        public String GetSellerName(int id) {
        String username = "";
        String sql = "select account.username from intermediary_order join account "
                + "on intermediary_order.account_sold_id=account.id where intermediary_order.id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public static void main(String[] args) {
        MySaleDao msd = new MySaleDao();
        System.out.println(msd.GetBuyerName(92));

    }
}
