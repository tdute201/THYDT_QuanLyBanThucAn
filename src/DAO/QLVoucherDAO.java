/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Voucher;

/**
 *
 * @author acer
 */
public class QLVoucherDAO {
    static JDBCConnection con= new JDBCConnection();
    public List<Voucher> getAllVC(){
        List<Voucher> dmes = new ArrayList<>();//tao list moi
        Connection cn =  con.getJDBCConnection();
        String sql ="select * from tblVoucher";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Voucher dm = new Voucher();//tao doi tuong moi cua class user
                dm.setIdVoucher(rs.getString("idVoucher"));
                dm.setTenVoucher(rs.getString("tenVoucher"));
                dm.setPhanTram(rs.getInt("phanTram"));
               dm.setSoLuong(rs.getInt("soLuong"));
                dm.setNgayBatDau(rs.getDate("ngayBatDau"));
                dm.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                dmes.add(dm);//thêm us vào list users
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dmes;//tra ve list
    }
     public Voucher getVCbyID(String id){
        Connection cn =  con.getJDBCConnection();
        String sql ="select * from tblVoucher WHERE idVoucher = ? ";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, id);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Voucher dm = new Voucher();//tao doi tuong moi cua class user
                 dm.setIdVoucher(rs.getString("idVoucher"));
                dm.setTenVoucher(rs.getString("tenVoucher"));
                dm.setPhanTram(rs.getInt("phanTram"));
               dm.setSoLuong(rs.getInt("soLuong"));
                dm.setNgayBatDau(rs.getDate("ngayBatDau"));
                dm.setNgayKetThuc(rs.getDate("ngayKetThuc"));
        
               return dm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
      
    
    
    public static void insertVC(Voucher vc){
        Connection cn = con.getJDBCConnection();
        String sql="insert into tblVoucher values(?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, vc.getIdVoucher());
            pstm.setString(2, vc.getTenVoucher());
            pstm.setInt(3, vc.getPhanTram());
            pstm.setInt(4, vc.getSoLuong());
            pstm.setDate(5,  vc.getNgayBatDau());
            pstm.setDate(6, vc.getNgayKetThuc());

            int rs=pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateVC(Voucher vc){
        Connection cn = con.getJDBCConnection();
        String sql="update tblVoucher set  tenVoucher = ?, phanTram=?, soLuong=? , ngayBatDau=?,ngayKetThuc=?"
                + " where idVoucher = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
                   
            pstm.setString(1, vc.getTenVoucher());
            pstm.setInt(2, vc.getPhanTram());
            pstm.setInt(3, vc.getSoLuong());
            pstm.setDate(4,  vc.getNgayBatDau());
            pstm.setDate(5, vc.getNgayKetThuc());
            pstm.setString(6, vc.getIdVoucher());
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void deleteVC(String idVoucher){
        Connection cn = con.getJDBCConnection();
        String sql = "delete from tblVoucher where idVoucher = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idVoucher);
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String NewIDVC() {
        Connection cn = con.getJDBCConnection();
        String sql = "select max(right(idVoucher,5)) from tblVoucher";
         String newID="";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
               newID = rs.getString(1);
                newID = "00000" + (Integer.parseInt(newID) + 1);
                newID = "VC" + newID.substring(newID.length()-5);
           
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newID;
    }
    
    public List<Voucher> getVoucher(Date ngay){ //lay nhung voucher con hsd
        Connection cn = con.getJDBCConnection();
        List<Voucher> list = new ArrayList<>();
        String sql = "select *\n" +
                    "from tblVoucher\n" +
                    "where GETDATE() between ngayBatDau and ngayketThuc and soLuong > 0";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Voucher vc = new Voucher();
                vc.setIdVoucher(rs.getString("idVoucher"));
                vc.setTenVoucher(rs.getString("tenVoucher"));
                vc.setPhanTram(rs.getInt("phanTram"));
               vc.setSoLuong(rs.getInt("soLuong"));
                vc.setNgayBatDau(rs.getDate("ngayBatDau"));
                vc.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                list.add(vc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLVoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
