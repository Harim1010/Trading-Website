package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.PurchaseOrder;

public class PurchaseOrderDAO {
    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về
    
    public List<PurchaseOrder> getAllPurchaseOrder() {
        List<PurchaseOrder> listOrder = new ArrayList<>();
        String query = "SELECT purchase_order.id, status, product_id, product.name, hide_information, purchase_order.quantity, purchase_order.price, total_price, create_date, update_date, user_id from dtb_demo.purchase_order INNER JOIN dtb_demo.product ON dtb_demo.purchase_order.product_id = dtb_demo.product.id;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrder.add(new PurchaseOrder(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10), rs.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listOrder;
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.toSHA1("1"));
    }
}
