package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.IntermediaryOrder;

public class IntermediaryOrderDAO {

    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về

    public List<IntermediaryOrder> getAllIntermediaryOrder() {
        List<IntermediaryOrder> listOrder = new ArrayList<>();
        String query = "SELECT intermediary_order.id, description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, order_status, intermediary_fee, link_to, title, account.username FROM dtb_demo.intermediary_order INNER JOIN dtb_demo.account on intermediary_order.account_sold_id = account.id order by intermediary_order.id desc;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrder.add(new IntermediaryOrder(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getBoolean(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDouble(12), rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrder;
    }

    public IntermediaryOrder getIntermediaryOrderById(int oId) {
        IntermediaryOrder interOrder = new IntermediaryOrder();
        String query = "SELECT intermediary_order.id, description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, order_status, intermediary_fee, link_to, title, account.username FROM dtb_demo.intermediary_order INNER JOIN dtb_demo.account on intermediary_order.account_sold_id = account.id where intermediary_order.id = " + oId + ";";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                interOrder = new IntermediaryOrder(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getBoolean(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDouble(12), rs.getString(13), rs.getString(14), rs.getString(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return interOrder;
    }

    public List<IntermediaryOrder> getAllMyIntermediaryOrder(int uId) {
        List<IntermediaryOrder> listOrder = new ArrayList<>();
        String query = "SELECT intermediary_order.id, description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, order_status, intermediary_fee, link_to, title, account.username FROM dtb_demo.intermediary_order INNER JOIN dtb_demo.account on intermediary_order.account_sold_id = account.id and intermediary_order.account_buy_id = ? order by intermediary_order.id desc;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrder.add(new IntermediaryOrder(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5),
                        rs.getString(6), rs.getBoolean(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDouble(12), rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrder;
    }

    public boolean buyIntermediaryOrder(int oId, int uId, double price) {
        try {
            con = new DBContext().connection;
            String strUpdate = "Update `dtb_demo`.`intermediary_order` "
                    + "SET `account_buy_id`= ?, "
                    + "`order_status`= 'checking', `updated_at` = now() "
                    + "where `id`= ?;";
            ps = con.prepareStatement(strUpdate);
            ps.setInt(1, uId);
            ps.setInt(2, oId);
            ps.execute();

            String strUpdate2 = "Update `dtb_demo`.`wallet` "
                    + "SET `balance`= `balance` - ? "
                    + "where `user_id`= ?;";
            ps = con.prepareStatement(strUpdate2);
            ps.setDouble(1, price);
            ps.setInt(2, uId);
            ps.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        }
        return false;
    }

    public void confirmBuyIntermediaryOrder(int oId, int uBuyId, int uSellId, double price) {
        try {
            con = new DBContext().connection;
            String strUpdate = "Update `dtb_demo`.`intermediary_order` "
                    + "SET `account_buy_id`= ?, "
                    + "`order_status`= 'transacted', `updated_at` = now() "
                    + "where `id`= ?;";
            ps = con.prepareStatement(strUpdate);
            ps.setInt(1, uBuyId);
            ps.setInt(2, oId);
            ps.execute();

            String strUpdate2 = "Update `dtb_demo`.`wallet` "
                    + "SET `balance`= `balance` + ? "
                    + "where `user_id`= ?;";
            ps = con.prepareStatement(strUpdate2);
            ps.setDouble(1, price);
            ps.setInt(2, uSellId);
            ps.execute();
            con.close();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.toSHA1("1"));
    }
}
