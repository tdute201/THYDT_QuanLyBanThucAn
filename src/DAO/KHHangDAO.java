/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.KHHang;
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
 * @author 84327
 */
public class KHHangDAO {
    JDBCConnection con = new JDBCConnection();
    public List<KHHang> getKH(){
        Connection cn = con.getJDBCConnection();
        List<KHHang> list = new ArrayList<>();
        String sql = "select * from tblKhachHang";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                KHHang khHang = new KHHang();
                khHang.setIdKH(rs.getString("maKH"));
                khHang.setTenKH(rs.getString("tenKH"));
                khHang.setSdt(rs.getString("SDT"));
                khHang.setDiaChi(rs.getString("diachiKH"));
                khHang.setLoaiKH(rs.getString("loaiKH"));
                list.add(khHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KHHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String NewIDKH() {
        Connection cn = con.getJDBCConnection();
        String sql = "select max(right(maKH,5)) from tblKhachHang";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String newID = rs.getString(1);
                newID = "00000" + (Integer.parseInt(newID) + 1);
                newID = "KH" + newID.substring(newID.length()-5);
                return newID;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "KH00001";
    }
    public void Insert (KHHang khang){
        Connection cn = con.getJDBCConnection();
        String sql= "insert into tblKhachHang values (?,?,?,?,?)";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, khang.getIdKH());
            pstm.setString(2, khang.getTenKH());
            pstm.setString(3, khang.getDiaChi());
            pstm.setString(4, khang.getSdt());
            pstm.setString(5, khang.getLoaiKH());
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(KHHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Update (KHHang khang){
        Connection cn = con.getJDBCConnection();
        String sql= "update tblKhachHang set tenKH = ?, SDT = ?, diachiKH = ? , loaiKH = ? where maKH = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            
            pstm.setString(1, khang.getTenKH());
            pstm.setString(2, khang.getSdt());
            pstm.setString(3, khang.getDiaChi());
            pstm.setString(4, khang.getLoaiKH());
            pstm.setString(5, khang.getIdKH());
            
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(KHHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
