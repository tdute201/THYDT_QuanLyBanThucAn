/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.CTHDDAOo;
import Models.CTHD;
import Models.SanPham;
import Models.thongKeVoucher;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author NHUY
 */
public class ServiceCTHDd {
    CTHDDAOo cTHDConnect= new CTHDDAOo();
    public List<CTHD> getCTHD(String idHD){
        return cTHDConnect.getCTHD(idHD);
    }
    
    public double tienHD(String idHD){
        return cTHDConnect.tienHD(idHD);
    }
    
    public int soLuongSP(String idSP){ //số lượng đã bán của tất cả sản phẩm
        return cTHDConnect.soLuongSP(idSP);
    }
    
    public int soLuongSP_Ngay(String idSP, Date bd, Date kt){ //số lượng đã bán của tất cả sản phẩm trong khoảng thời gian
        return cTHDConnect.soLuongSP_Ngay(idSP, bd, kt);
    }
    
    public List<thongKeVoucher> listVoucher(){
        return cTHDConnect.listVoucher();
    }
    public List<thongKeVoucher> listVoucher_Ngay(Date bd, Date kt){//các voucher được sử dụng trong khoảng thời gian
        return cTHDConnect.listVoucher_Ngay(bd, kt);
    }
    public double tongTien_HD_VC(String idVC){
        return cTHDConnect.tongTien_HD_VC(idVC);
    }
    public double tongTien_HD_VC_Ngay(String idVC, Date bd, Date kt){ //tong tien cac hoa don co maVC = idVC trong khoảng thời gian
        return cTHDConnect.tongTien_HD_VC_Ngay(idVC, bd, kt);
    }
    public List<SanPham> getAllSP_DaBan_theoNgay(Date bd, Date kt){
        return cTHDConnect.getAllSP_DaBan_theoNgay(bd, kt);
    }
    public List<SanPham> banChay (){
        return cTHDConnect.banChay();
    }
    public List<SanPham> banChay_Ngay (Date bd, Date kt){
        return cTHDConnect.banChay_Ngay(bd, kt);
    }
    
    public List<SanPham> banChay_IdLoai (String idLoai){
        return cTHDConnect.banChay_IdLoai(idLoai);
    }
    public List<SanPham> banChay_IdLoai_Ngay (String idLoai,Date bd, Date kt){
        return cTHDConnect.banChay_IdLoai_Ngay(idLoai,bd,kt);
    }
    
    public List<SanPham> khongBanDuoc(){
        return cTHDConnect.khongBanDuoc();
    }
    public List<SanPham> khongBanDuoc_idLoai(String idLoai){
        return cTHDConnect.khongBanDuoc_idLoai(idLoai);
    }
    public List<SanPham> khongBanDuoc_Ngay(Date bd, Date kt){
        return cTHDConnect.khongBanDuoc_Ngay(bd, kt);
    }
    public List<SanPham> khongBanDuoc_idLoai_Ngay(String idLoai,Date bd, Date kt){
        return cTHDConnect.khongBanDuoc_idLoai_Ngay(idLoai, bd, kt);
    }
}
