/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.*;
/**
 *
 * @author thien
 */
public class UserDao {
    JDBCConnection con = new JDBCConnection();
    public List<Ban> getAllBan() {
       List<Ban> listBan = new ArrayList();
        Connection cn = con.getJDBCConnection();
        String sql = "select * from tblBan where trangThai = 'c'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Ban b = new Ban();
                b.setIdBan(resultSet.getString("idBan"));
                b.setTrangThai(resultSet.getString("trangThai"));
                b.setIsOrdered(resultSet.getString("isOrdered"));
                listBan.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBan; 
    }
    
    public List<DanhMuc> getAllDM() {
        List<DanhMuc> listDM= new ArrayList();
        Connection cn = con.getJDBCConnection();
        String sql = "select * from tblDanhMuc where trangThai = 'c'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setId(resultSet.getString("idDM"));
                dm.setTenDM(resultSet.getString("tenDM"));
                dm.setTrangThai(resultSet.getString("trangThai"));
                listDM.add(dm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listDM;
    } 
            
    public List<SanPham> getAllSP() {
        List<SanPham> listSP = new ArrayList();
        Connection cn = con.getJDBCConnection();
        String sql = "select * from tblSanPham where trangThai = 'c'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                SanPham sp = new SanPham();
                sp.setId(resultSet.getString("id"));
                sp.setTen(resultSet.getString("tenSP"));
                sp.setDinhLuong(resultSet.getString("dinhLuong"));
                sp.setGiaBan(resultSet.getDouble("giaBan"));
                sp.setDMNo(resultSet.getString("DMNo"));
                sp.setTrangThai(resultSet.getString("trangThai"));
                listSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listSP;
    }
    //Select by
        public List<ChiTietDonHang> getCTDHbyIDBan(String idBan) {
        List<ChiTietDonHang> listCTDH = new ArrayList();
        Connection cn = con.getJDBCConnection();
        String sql = "select * from tblChiTietDonHang ct join tblDonHang d on ct.idDH = d.maDH where d.maBanNo = ? and d.thanhToan = 'k'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idBan);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ChiTietDonHang ct = new ChiTietDonHang();
                ct.setIdDH(resultSet.getString("idDH"));
                ct.setIdSP(resultSet.getString("idSP"));
                ct.setSLMua(resultSet.getInt("SLMua"));
                ct.setGiaBan(resultSet.getDouble("giaBan"));
                listCTDH.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listCTDH;
    }
    //NewID
    private String NewIDDM() {
        Connection cn = con.getJDBCConnection();
        String sql = "select max(right(idDM,5)) from tblDanhMuc";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String newID = rs.getString(1);
                newID = "00000" + (Integer.parseInt(newID) + 1);
                newID = "DM" + newID.substring(newID.length()-5);
                return newID;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "DM00001";
    }
    
    private String NewIDDH() {
        Connection cn = con.getJDBCConnection();
        String sql = "select max(right(maDH,5)) from tblDonHang";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String newID = rs.getString(1);
                newID = "00000" + (Integer.parseInt(newID) + 1);
                newID = "DH" + newID.substring(newID.length()-5);
                return newID;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "DH00001";
    }
    
    private String NewIDSP() {
        Connection cn = con.getJDBCConnection();
        String sql = "select max(right(id,5)) from tblSanPham";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String newID = rs.getString(1);
                newID = "00000" + (Integer.parseInt(newID) + 1);
                newID = "SP" + newID.substring(newID.length()-5);
                return newID;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "SP00001";
    }
    
    //Insert
    public Boolean InsertDM(String ten) {
        Connection cn = con.getJDBCConnection();
        String sql = "insert into tblDanhMuc(idDM, tenDM) values (?,?)";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, NewIDDM());
            preparedStatement.setString(2, ten);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Boolean InsertSP(String ten, String dinhLuong, Double giaBan, String danhMucNo) {
        Connection cn = con.getJDBCConnection();
        String sql = "insert into tblSanPham values (?,?,?,?,default,?)";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            
            preparedStatement.setString(1, NewIDSP());
            preparedStatement.setString(2, ten);
            preparedStatement.setString(3, dinhLuong);
            preparedStatement.setDouble(4, giaBan);
            preparedStatement.setString(5, danhMucNo);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Update
    public Boolean UpdateDanhMucByID(String id, String ten) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblDanhMuc set tenDM = ? where idDM = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, ten);
            preparedStatement.setString(2, id);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Boolean UpdateSanPhamByID(String id, String ten, String dinhLuong, Double giaBan, String danhMucNo) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblSanPham set tenSP = ?, dinhLuong = ?, giaBan = ?, DMNo = ? where id = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, ten);
            preparedStatement.setString(2, dinhLuong);
            preparedStatement.setDouble(3, giaBan);
            preparedStatement.setString(4, danhMucNo);
            preparedStatement.setString(5, id);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //ChitetDonHang - Goi mon
    public boolean daCoDH(String idBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "select maDH from tblDonHang where maBanNo = ? and thanhToan = 'k'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idBan);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean IsOrdered(String idBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblBan set isOrdered = 'r' where idBan = ?";
        System.out.println(idBan);
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idBan);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean IsOrdered_k(String idBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblBan set isOrdered = 'k' where idBan = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idBan);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean newDH(String idBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "insert into tblDonHang(maDH, maBanNo) values(?,?)";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, NewIDDH());
            preparedStatement.setString(2, idBan);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1) {
                IsOrdered(idBan);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void ResetCTDHByID(String idDH) {
        Connection cn = con.getJDBCConnection();
        String sql = "delete from tblChiTietDonHang where idDH = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idDH);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CapNhatDSMonByID(String idDH, String idSP, int sl, double giaBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "insert into tblChiTietDonHang values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idDH);
            preparedStatement.setString(2, idSP);
            preparedStatement.setInt(3, sl);
            preparedStatement.setDouble(4, giaBan);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String idDHbyIDBan(String idBan) {
        Connection cn = con.getJDBCConnection();
        String sql = "select maDH from tblDonHang where maBanNo = ? and thanhToan = 'k'";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, idBan);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getString("maDH");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    //Delete
    public Boolean DeleleSanPhamByID(String id) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblSanPham set trangThai = 'x' where id = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Boolean DeleleDanhMucByID(String id) {
        Connection cn = con.getJDBCConnection();
        String sql = "update tblDanhMuc set trangThai = 'x' where idDM = ?";
        try {
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int rs = preparedStatement.executeUpdate();
            if(rs != -1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
