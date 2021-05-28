/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.HoaDonDAO;
import Models.HDonn;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author NHUY
 */
public class ServiceHoaDo {
    
    HoaDonDAO hoaDonConnect = new HoaDonDAO();
    public List<HDonn> getAllHoaDon(){
        return hoaDonConnect.getAllHoaDon();
    }
    public HDonn getHoaDon(String id){
        return hoaDonConnect.getHoaDon(id);
    }
    public void Update(String id){
        hoaDonConnect.Update(id);
    }
    public void Update_HD_VC (String idDH, String idVC){
        hoaDonConnect.Update_HD_VC(idDH, idVC);
    }
    public void Update_sl_VC (String idVC, int sl){
        hoaDonConnect.Update_sl_VC(idVC, sl);
    }
    public List<HDonn> timHD_idHD(String idHD){
        return hoaDonConnect.timHD_idHD(idHD);
    }
    
    public List<HDonn> timHD_idKH(String idKH){
        return hoaDonConnect.timHD_idKH(idKH);
    }
    
    public List<HDonn> timHD_idKH_idKD(String idKH, String idHD){
        return hoaDonConnect.timHD_idKH_idHD(idKH, idHD);
    }
    
    public List<HDonn> timHD_ngay(Date ngay){
        return hoaDonConnect.timHD_ngay(ngay);
    }
    
    public List<HDonn> timHD_idKH_ngay(String idKH, Date ngay){
        return hoaDonConnect.timHD_idKH_ngay(idKH, ngay);
    }
    
    public List<HDonn> timHD(String idKH, Date ngay, String idHD){
        return hoaDonConnect.timHD(idKH, ngay, idHD);
    }
    
    public int tongTien(String idHD){
        return hoaDonConnect.tongTien(idHD);
    }
//    public int DoanhThu(String nam, String thang, String ngay){
//        return hoaDonConnect.DoanhThu(nam, thang, ngay);
//    }
//    
//    public int TongDoanhThu(){
//        return hoaDonConnect.TongDoanhThu();
//    }
//    public List<HDonn> hoaDon_Ngay(String ngay, String thang, String nam){
//        return hoaDonConnect.hoaDon_Ngay(ngay, thang, nam);
//    }
}
