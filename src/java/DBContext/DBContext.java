package DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/dtb_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "1102003hoan";

    // Phương thức để lấy connection
    public DBContext() {
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối đến cơ sở dữ liệu và trả về connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    // Phương thức để đóng connection
    public static void main(String[] args) {
        // Sử dụng connection
        DBContext a = new DBContext();
        if (a.connection != null) {
            System.out.println("Kết nối thành công!");
            // Thực hiện các thao tác với connection ở đây
            // ...

            // Sau khi hoàn thành, đóng connection
        }
    }

}
