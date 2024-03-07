package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Product;

public class ProductDAO {
    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về
    
    public List<Product> getAllProduct() {
        List<Product> listProduct = new ArrayList<>();
        String query = "SELECT product.id, product.name, quantity, category_id, code, price, hide_information, description, link_share, image, categories.name FROM dtb_demo.product INNER JOIN dtb_demo.categories ON dtb_demo.product.category_id = dtb_demo.categories.id";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProduct.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listProduct;
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.toSHA1("1"));
    }
}
