/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 84327
 */
public class JDBCConnection {
    Connection con = null;
    public Connection getJDBCConnection(){
        try {
            String dbUrl = "jdbc:jtds:sqlserver://localhost/QLBanThucAn03";
            con = DriverManager.getConnection(dbUrl);
            System.out.println("thành công");
        } catch (SQLException e) {
            System.out.println("không kết nối được");
        }
        return con;
    }
}
