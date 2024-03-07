package DAO;

import model.trans_history;
import DBContext.DBContext;
import model.Account;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Xuanphuc
 */
public class AccountDAO {

    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về

    public int UserAcountExist(String username, String pass) {
        int id = 0;
        String sql = "SELECT * FROM dtb_demo.account where username =? and password=?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Account GetAccountByID(int id) {
        String sql = "SELECT * FROM dtb_demo.account where id = ?";
        try {

            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt(1));
                acc.setName(rs.getString(2));
                acc.setAvatar(rs.getString(3));
                acc.setUsername(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPassword(rs.getString(6));
                acc.setPhonenumber(rs.getString(7));
                acc.setStory(rs.getString(8));
                acc.setRole_id(rs.getInt(9));
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account FindAccountByUserEmail(String email) {
        String sql = "SELECT * FROM dtb_demo.account where email = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt(1));
                acc.setName(rs.getString(2));
                acc.setAvatar(rs.getString(3));
                acc.setUsername(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPassword(rs.getString(6));
                acc.setPhonenumber(rs.getString(7));
                acc.setStory(rs.getString(8));
                acc.setRole_id(rs.getInt(9));
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account FindAccountByUserName(String username) {
        String sql = "SELECT * FROM dtb_demo.account where username = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt(1));
                acc.setName(rs.getString(2));
                acc.setAvatar(rs.getString(3));
                acc.setUsername(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPassword(rs.getString(6));
                acc.setPhonenumber(rs.getString(7));
                acc.setStory(rs.getString(8));
                acc.setRole_id(rs.getInt(9));
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getallacc() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from dtb_demo.account";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void ForgotPassword(int id, String password) {
        String sql = "UPDATE dtb_demo.`account` SET password = ? WHERE id = ?;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ForgotPassword: " + e.getMessage());
        }
    }

    public boolean kiemTraTenDangNhap(String username) {
        boolean ketQua = false;
        String sql = "SELECT * FROM account WHERE username=?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                ketQua = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    public void insert(Account t) {
        int ketQua = 0;
        String sql = "insert into account(name,username,email,password,role_id)\n" + "values(?,?,?,?,2)";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getName());
            ps.setString(2, t.getUsername());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPassword());
            ketQua = ps.executeUpdate();
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insert_wallet(int user_id){
        String sql = "insert into wallet(user_id) values (?)";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toSHA1(String str) {
        String salt = "huiashohawvhjb@#!AAljcuasiuw";
        String result = null;
        str = str + salt;
        try {
            byte[] dataByte = str.getBytes("UTF-8");
            MessageDigest mess = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(mess.digest(dataByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNoiDung(Account acc, String otp) {
        String noiDung = "<p>Sulk Market xin chào bạn <strong>" + acc.getName() + "</strong></p>\n"
                + "<p>Vui lòng xác thực tài khoản của bạn bằng cách nhập mã: <strong>" + otp + ".</strong></p>\n"
                + "<p>Xin vui lòng không chia sẻ mã này cho người khác.</p>\n"
                + "<p>​Đây là email trả lời tự động, vui lòng không phản hồi email này.</p>\n"
                + "<p>Trân trọng cảm ơn</p>";
        return noiDung;
    }

    public void deleteAccount(String id) {
        String query = "DELETE FROM Account\n"
                + "WHERE id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        }
    }

    public void updateAccountById(Account x) {
        String query = "update Account\n"
                + "set name = ?, username = ?, password = ?, role_id = ? where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, x.getName());
            ps.setString(2, x.getUsername());
            ps.setString(3, x.getPassword());
            ps.setInt(4, x.getRole_id());
            ps.setInt(5, x.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public int UserAcountExist1(String username) {
        int id = 0;
        String sql = "SELECT * FROM dtb_demo.account where username =? and password=?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Account checkAccountExist(String user) {
        String query = "select * from Account\n"
                + "where [user] = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            }
        } catch (Exception e) {

        }

        return null;
    }

    public void updateUserPassword(int accountId, String newPass) {
        try {
            String strUpdate = "Update account "
                    + "set password=? "
                    + "where id=?";
            con = new DBContext().connection;
            ps = con.prepareStatement(strUpdate);
            ps.setString(1, newPass);
            ps.setInt(2, accountId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        }
    }

    public void updateUserInformation(int accountId, String fullname) {
        try {
            String strUpdate = "Update account "
                    + "set name=? "
                    + "where id=?";
            con = new DBContext().connection;
            ps = con.prepareStatement(strUpdate);
            ps.setString(1, fullname);
            ps.setInt(2, accountId);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Update:" + e.getMessage());
        }
    }

    public List<trans_history> getTrans() {
        List<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public int getAccount_ID(String username){
        String sql = "select * from account where username = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<trans_history> getTransById(int id) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "where io.id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByMoney(int money) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "where io.intermediary_fee = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, money);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByType(boolean type) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "where io.fee_type = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByWork(boolean work) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "where io.processed = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, work);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<trans_history> getTransByNote(String note) {
        ArrayList<trans_history> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "WHERE\n"
                + "    io.order_status LIKE ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + note + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new trans_history(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public trans_history getDetailsTransById(int id) {
        String sql = "SELECT\n"
                + "    io.id AS intermediary_order_id,\n"
                + "    io.intermediary_fee,\n"
                + "    io.fee_type,\n"
                + "    io.processed,\n"
                + "    io.order_status,\n"
                + "    acc.name AS account_sold_name,\n"
                + "    io.created_at,\n"
                + "    io.updated_at\n"
                + "FROM\n"
                + "    intermediary_order io\n"
                + "INNER JOIN\n"
                + "    account acc ON io.account_sold_id = acc.id\n"
                + "where io.id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new trans_history(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getBoolean(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getString(6), // Sử dụng account_sold_id
                        rs.getTimestamp(7),
                        rs.getTimestamp(8)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        //System.out.println(dao.toSHA1("1"));
        System.out.println(dao.getTransByNote("t"));
    }
}
