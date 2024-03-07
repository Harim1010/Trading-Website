/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DBContext.DBContext;

/**
 *
 * @author Xuanphuc
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class MyDAO extends DBContext {

    public Connection con = null; // có kiểu dữ liệu Connection, lưu trữ kết nối đến csdl

    public PreparedStatement ps = null; // dùng để thực hiện các truy vấn sql được chuẩn bị trước

    public ResultSet rs = null; // dùng để lưu kết quả truy vấn từ csdl

    public String xSql = null; // dùng để lưu trữ các câu truy vấn sql

    public MyDAO() {
        con = connection;
    }

    // kết nối đến csdl sẽ được đóng để giải phóng tài nguyên và ngăn chặn rò rỉ tài nguyên
    @Override
    public void finalize() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}

