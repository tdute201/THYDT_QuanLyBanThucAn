/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.HDonn;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHUY
 */
public class HoaDonDAO {
    JDBCConnection con = new JDBCConnection();
    public List<HDonn> getAllHoaDon(){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang "; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public HDonn getHoaDon(String id){
        Connection cn = con.getJDBCConnection();
        HDonn hDon = new HDonn();
        String sql = "select * from tblDonHang where maDH = ? "; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hDon;
    }
    

    public void Update (String idDH){
        Connection cn = con.getJDBCConnection();
        String sql = "Update tblDonHang set thanhToan = 'r' where maDH = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idDH);
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Update_HD_VC (String idDH, String idVC){
        Connection cn = con.getJDBCConnection();
        String sql = "Update tblDonHang set maVCNo = ? where maDH = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idVC);
            pstm.setString(2, idDH);
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Update_sl_VC (String idVC, int sl){
        Connection cn = con.getJDBCConnection();
        int soluong = sl-1;
        String sql = "Update tblVoucher set soLuong = ? where maVCNo = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, soluong);
            pstm.setString(2, idVC);
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<HDonn> timHD_idHD(String idHD){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where maDH = ? "; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idHD);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HDonn> timHD_idKH(String idKH){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where maKHNo = ? "; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idKH);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HDonn> timHD_idKH_idHD(String idKH, String idHD){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where maKHNo = ? and maDH = ?"; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idKH);
            pstm.setString(2, idHD);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HDonn> timHD_ngay(Date ngay){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where ngayTaoDH = ? "; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setDate(1, ngay);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HDonn> timHD_idKH_ngay(String idKH, Date ngay){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where maKHNo = ? and ngayTaoDH = ?"; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idKH);
            pstm.setDate(2, ngay);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<HDonn> timHD(String idKH, Date ngay, String idHD){
        Connection cn = con.getJDBCConnection();
        List<HDonn> list = new ArrayList<>();
        String sql = "select * from tblDonHang where maKHNo = ? and ngayTaoDH = ? and maDH = ?"; 
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idKH);
            pstm.setDate(2, ngay);
            pstm.setString(3, idHD);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("maDH"));
                hDon.setNgayTao(rs.getDate("ngayTaoDH"));
                hDon.setIdKH(rs.getString("maKHNo"));
                hDon.setIdBan(rs.getString("maBanNo"));
                hDon.setIdVC(rs.getString("maVCNo"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int tongTien(String idHD){
        Connection cn = con.getJDBCConnection();
        int tongTien =0;
        String sql = "select sum((SLMua)*giaBan) as tongtien\n" +
                    "    from tblChiTietDonHang\n" +
                    "    where idDH = ?\n" +
                    "    group by idDH";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idHD);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tongTien = (rs.getInt("tongtien"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tongTien;
    }
    
//    public int DoanhThu(String nam, String thang, String ngay){
//        Connection cn = con.getJDBCConnection();
//        int tongTien =0;
//        if(ngay=="")
//        {
//            if(thang !="")
//                {
//                    String sql = "select sum(CONVERT(int,soLuong)*CONVERT(int,donGiaBan)) as tongtien\n" +
//                             "from tblDonHang as h, ChitietHD as c\n" +
//                             "where h.idHD = c.idHD and year(CONVERT(date,ngayTao,103))= ? and month(CONVERT(date,ngayTao,103))= ?";
//                    try {
//                        PreparedStatement pstm = cn.prepareStatement(sql);
//                        pstm.setString(1, nam);
//                        pstm.setString(2, thang);
//                        ResultSet rs = pstm.executeQuery();
//                        while(rs.next()){
//                            tongTien = Integer.parseInt(rs.getString("tongtien"));
//                        }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            else
//                {
//                    String sql = "select sum(CONVERT(int,soLuong)*CONVERT(int,donGiaBan)) as tongtien\n" +
//                             "from tblDonHang as h, ChitietHD as c\n" +
//                             "where h.idHD = c.idHD and year(CONVERT(date,ngayTao,103))= ?";
//                    try {
//                        PreparedStatement pstm = cn.prepareStatement(sql);
//                        pstm.setString(1, nam);
//                        ResultSet rs = pstm.executeQuery();
//                        while(rs.next()){
//                            tongTien = Integer.parseInt(rs.getString("tongtien"));
//                        }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//        }
//        else
//        {
//            String sql = "select sum(CONVERT(int,soLuong)*CONVERT(int,donGiaBan)) as tongtien\n" +
//                             "from tblDonHang as h, ChitietHD as c\n" +
//                             "where h.idHD = c.idHD and year(CONVERT(date,ngayTao,103))= ? and month(CONVERT(date,ngayTao,103))= ? and day(CONVERT(date,ngayTao,103))= ? ";
//            try {
//                    PreparedStatement pstm = cn.prepareStatement(sql);
//                    pstm.setString(1, nam);
//                    pstm.setString(2, thang);
//                    pstm.setString(3, ngay);
//                    ResultSet rs = pstm.executeQuery();
//                    while(rs.next()){
//                        tongTien = Integer.parseInt(rs.getString("tongtien"));
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }
//        return tongTien;
//    }
    
//    public int TongDoanhThu(){
//        Connection cn = con.getJDBCConnection();
//        int tt=0;
//        String sql ="select sum(CONVERT(int,soLuong)*CONVERT(int,donGiaBan)) as tongtien\n" +
//                    "from tblDonHang as h, ChitietHD as c\n" +
//                    "where h.idHD = c.idHD";
//        try {
//            PreparedStatement pstm = cn.prepareStatement(sql);
//            ResultSet rs = pstm.executeQuery();
//            while(rs.next()){
//                tt = Integer.parseInt(rs.getString("tongtien"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return tt;
//    }
    
    /*public List<HDonn> hoaDon_Ngay(String ngay, String thang, String nam){
        Connection cn = con.getConnecDB();
        List<HDonn> list = new ArrayList<>();
        String sql ="select *\n" +
                    "from tblDonHang \n" +
                    "where year(CONVERT(date,ngayTao,103))= ? and month(CONVERT(date,ngayTao,103))= ? and day(CONVERT(date,ngayTao,103))= ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, nam);
            pstm.setString(2, thang);
            pstm.setString(3, ngay);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                HDonn hDon = new HDonn();
                hDon.setIdHD(rs.getString("idHD"));
                hDon.setNgayTao(rs.getString("ngayTao"));
                hDon.setThoiGian(rs.getString("thoiGian"));
                hDon.setIdKH(rs.getString("idKH"));
                list.add(hDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblDonHangConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
    
    
}
