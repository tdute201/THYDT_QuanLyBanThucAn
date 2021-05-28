/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Models.*;
import DAO.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author thien
 */
public class UserServices {
    UserDao userDao;
    public UserServices() {
        userDao = new UserDao();
    }
    
    public List<Ban> getAllBan() {
        return userDao.getAllBan();
    }
    
    public List<DanhMuc> getAllDM() {
        return userDao.getAllDM();
    }
    
    public List<SanPham> getAllSP() {
        return userDao.getAllSP();
    }
    
    //ChiTietDonHang - DOnHang - Goi mon
    public String getIDDHbyIDBan(String idBan) {
        return userDao.idDHbyIDBan(idBan);
    }
    public List<ChiTietDonHang> getCTDHbyIDBan(String idBan) {
        return userDao.getCTDHbyIDBan(idBan);
    }
    
    public boolean DaCoDHChuaThanhToan(String idBan) {
        return userDao.daCoDH(idBan);
    }
    
    public boolean NewDH(String idban) {
        return userDao.newDH(idban);
    }
    
    public void ResetCTDHByID(String idDH) {
        userDao.ResetCTDHByID(idDH);
    }
    
    public void CapNhatDSMonByID(String idDH, String idSP, int sl, double giaBan) {
        userDao.CapNhatDSMonByID(idDH, idSP, sl, giaBan);
    }
    
    //insert
    public boolean InsertDanhMuc(String tenDM) {
        return userDao.InsertDM(tenDM);
    }
    
    public boolean InsertSanPham(String ten, String dinhLuong, Double giaBan, String danhMucNo) {
        return userDao.InsertSP(ten, dinhLuong, giaBan, danhMucNo);
    }
    
    //Update
    public boolean UpdateDMByID(String id, String name) {
        return userDao.UpdateDanhMucByID(id, name);
    }
    
    public boolean UpdateSPByID(String id, String ten, String dinhLuong, Double giaBan, String danhMucNo)   {
        return userDao.UpdateSanPhamByID(id, ten, dinhLuong, giaBan, danhMucNo);
    }
    
    //Delete
    public boolean DeleteSP(String id) {
        return userDao.DeleleSanPhamByID(id);
    }
    
    public boolean DeleteDMById(String id) {
        return userDao.DeleleDanhMucByID(id);
    }
    
    public String toVND(double gia) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(gia);
    }
    
    public boolean IsOrdered_k(String id){
        return userDao.IsOrdered_k(id);
    }
}
