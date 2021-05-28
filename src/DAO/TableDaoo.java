/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vando
 */
public class TableDaoo {
    static JDBCConnection connection=new JDBCConnection();
    public List<Table> getAllTable()
    {
        List<Table> table=new ArrayList<>();
        Connection con=connection.getJDBCConnection();
        String sql="Select * from tblBan where trangThai = 'c'";
        try {
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while (rs.next())
            {
                Table tab=new Table();
                tab.setIdTable(rs.getString("IdBan"));
                tab.setTinhTrang(rs.getString("trangThai"));
                tab.setIsOrdered(rs.getString("isOrdered"));
                table.add(tab);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDaoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }
    public Table getTableById(String id)
    {
        //List<Table> table=new ArrayList<>();
        Connection con=connection.getJDBCConnection();
        String sql="Select * from tblBan where IdBan=?";
        try {
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            while (rs.next())
            {
                Table tab=new Table();
                tab.setIdTable(rs.getString("IdBan"));
                tab.setTinhTrang(rs.getString("trangThai"));
                tab.setIsOrdered(rs.getString("isOrdered"));
                return tab;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDaoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void addTable(Table table)
    {
        Connection con=connection.getJDBCConnection();
        String sql="Insert into tblBan(IdBan) values(?)";
        try {
            PreparedStatement pst=con.prepareStatement(sql); 
            pst.setString(1, table.getIdBan());
            int rs=pst.executeUpdate();
//            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TableDaoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteTable(String id)
    {
        Connection con=connection.getJDBCConnection();
        String sql="Update tblBan set trangThai='x' where IdBan=?";
        try {
            PreparedStatement pst=con.prepareStatement(sql); 
            pst.setString(1, id);
            int rs=pst.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TableDaoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
