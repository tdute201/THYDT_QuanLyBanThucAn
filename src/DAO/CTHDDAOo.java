/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.CTHD;
import Models.SanPham;
import Models.thongKeVoucher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHUY
 */
public class CTHDDAOo {
    JDBCConnection con = new JDBCConnection();
    public List<CTHD> getCTHD(String idHD){
        Connection cn = con.getJDBCConnection();
        List<CTHD> list = new ArrayList<>();
        String sql = "select * from tblChiTietDonHang where idDH = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1,idHD);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                CTHD cthd = new CTHD();
                cthd.setIdHD(rs.getString("idDH"));
                cthd.setIdSP(rs.getString("idSP"));
                cthd.setSoLuong(rs.getString("SLMua"));
                cthd.setDonGia(rs.getString("giaBan"));
                list.add(cthd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public double tienHD(String idHD){
        List<CTHD> list = new ArrayList<>();
        list = getCTHD(idHD);
        double tongTien = 0;
        for (CTHD cthd : list) {
            tongTien +=Double.parseDouble(cthd.getSoLuong()) * Double.parseDouble(cthd.getDonGia());
        }
        return tongTien;
    }
    
    
   
    public int soLuongSP(String idSP){//số lượng đã bán của tất cả sản phẩm
        Connection cn = con.getJDBCConnection();
        int sl =0;
        String sql = "select sum(SLMua) soLuong \n" +
                    "from tblChiTietDonHang as c, tblDonHang as d\n" +
                    "where c.idDH = d.maDH and idSP = ? and d.thanhToan = 'r'\n" +
                    " group by idSP ";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idSP);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                sl = (rs.getInt("soLuong"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
    public int soLuongSP_Ngay(String idSP, Date bd, Date kt){//số lượng đã bán của tất cả sản phẩm trong khoảng thời gian
        Connection cn = con.getJDBCConnection();
        int sl =0;
        String sql = "select sum(SLMua) soLuong \n" +
                    "from tblChiTietDonHang as c , tblDonHang as d\n" +
                    "where c.idDH=d.maDH and idSP = ? and d.ngayTaoDH between convert(date,?,103) \n" +
                    "and convert(date,?,103) and d.thanhToan ='r' \n" +
                    "group by idSP ";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idSP);
            pstm.setDate(2, bd);
            pstm.setDate(3, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                sl = (rs.getInt("soLuong"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
    public List<thongKeVoucher> listVoucher(){ //các voucher đã sử dụng
        Connection cn = con.getJDBCConnection();
        List<thongKeVoucher> list= new ArrayList<>();
        String sql = "select v.tenVoucher as ten ,v.phanTram as pt,COUNT(maVCNo) as sl, v.idVoucher as id \n" +
                    "from tblDonHang as d, tblVoucher as v\n" +
                    "where d.maVCNo = v.idVoucher and d.thanhToan = 'r'\n" +
                    "group by d.maVCNo, v.tenVoucher,v.phanTram, v.idVoucher";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                thongKeVoucher tk = new thongKeVoucher();
                tk.setTenVC(rs.getString("ten"));
                tk.setSl(rs.getInt("sl"));
                tk.setPhanTram(rs.getInt("pt"));
                tk.setIdVC(rs.getString("id"));
                list.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<thongKeVoucher> listVoucher_Ngay(Date bd, Date kt){//các voucher được sử dụng trong khoảng thời gian
        Connection cn = con.getJDBCConnection();
        List<thongKeVoucher> list= new ArrayList<>();
        String sql = "select v.tenVoucher as ten ,v.phanTram as pt,COUNT(maVCNo) as sl, v.idVoucher as id \n" +
                    "from tblDonHang as d, tblVoucher as v\n" +
                    "where d.maVCNo = v.idVoucher and d.ngayTaoDH between convert(date,?,103) \n" +
                    "and convert(date,?,103) and d.thanhToan ='r' \n" +
                    "group by d.maVCNo, v.tenVoucher,v.phanTram, v.idVoucher";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                thongKeVoucher tk = new thongKeVoucher();
                tk.setTenVC(rs.getString("ten"));
                tk.setSl(rs.getInt("sl"));
                tk.setPhanTram(rs.getInt("pt"));
                tk.setIdVC(rs.getString("id"));
                list.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public double tongTien_HD_VC(String idVC){ //tong tien cac hoa don co maVC = idVC
        Connection cn = con.getJDBCConnection();
        double tong = 0;
        String sql = "select sum(phu.thanhTien) as tt  \n" +
                    "from (\n" +
                    "		select sum(SLMua*giaBan) as thanhTien , maVCNo \n" +
                    "		from tblChiTietDonHang as c, tblDonHang as d\n" +
                    "		where c.idDH=d.maDH and maVCNo = ? and d.thanhToan = 'r'\n" +
                    "		group by idDH, maVCNo ) as phu\n" +
                    "group by phu.maVCNo";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idVC);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tong = rs.getDouble("tt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tong;
    }
    public double tongTien_HD_VC_Ngay(String idVC, Date bd, Date kt){ //tong tien cac hoa don co maVC = idVC trong khoảng thời gian
        Connection cn = con.getJDBCConnection();
        double tong = 0;
        String sql = "select sum(phu.thanhTien) as tt  --tổng tiền của các hóa đơn có dùng voucher\n" +
                    "from (\n" +
                    "		select sum(SLMua*giaBan) as thanhTien , maVCNo --tien mỗi hóa đơn\n" +
                    "		from tblChiTietDonHang as c, tblDonHang as d\n" +
                    "		where c.idDH=d.maDH and maVCNo = ? and d.thanhToan = 'r' and d.ngayTaoDH between convert(date,?,103) \n" +
                    "and convert(date,?,103)\n" +
                    "		group by idDH, maVCNo ) as phu\n" +
                    "group by phu.maVCNo";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idVC);
            pstm.setDate(2, bd);
            pstm.setDate(3, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                tong = rs.getDouble("tt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tong;
    }
    public List<SanPham> getAllSP_DaBan_theoNgay(Date bd, Date kt){ //list SP đã bán trong khoảng thời gian
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select * \n" +
                    "from tblSanPham as s\n" +
                    "where s.id in (\n" +
                    "	select idSP\n" +
                    "	from tblChiTietDonHang as c , tblDonHang as d\n" +
                    "	where c.idDH= d.maDH and d.ngayTaoDH between convert(date,?,103) and convert(date,?,103) and d.thanhToan ='r' )";
        try {
            PreparedStatement pstm= cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<SanPham> banChay (){
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select * \n" +
                    "from tblSanPham\n" +
                    "where id in(\n" +
                    "select idSP\n" +
                    "from tblChiTietDonHang as c , tblDonHang as d\n" +
                    "where c.idDH = d.maDH and d.thanhToan = 'r'\n" +
                    "group by idSP\n" +
                    " having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  \n" +
                    "			 from tblChiTietDonHang \n" +
                    "			 group by idSP \n" +
                    "			 order by soLuong desc))";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<SanPham> banChay_Ngay (Date bd, Date kt){ //những sản phẩm bán chạy trong khoảng thời gian
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select *\n" +
                    "from tblSanPham \n" +
                    "where id in (\n" +
                    "select idSP \n" +
                    "from tblChiTietDonHang as c, tblDonHang as h\n" +
                    "where c.idDH=h.maDH \n" +
                    "group by idSP \n" +
                    "having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  from tblChiTietDonHang as c, tblDonHang as h\n" +
                    "where c.idDH=h.maDH and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,?,103) and CONVERT(date,?,103)\n" +
                    " group by idSP order by soLuong desc )\n" +
                    " )";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<SanPham> banChay_IdLoai (String idLoai){
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select *\n" +
                    "from tblSanPham  \n" +
                    "where id in ( \n" +
                    "select idSP\n" +
                    "from tblChiTietDonHang as c, tblDonHang as d \n" +
                    "where c.idDH = d.maDH and d.thanhToan = 'r'  \n" +
                    "group by idSP  \n" +
                    "having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  from tblChiTietDonHang as c, tblDanhMuc as d, tblSanPham as s , tblDonHang as do \n" +
                    "where c.idSP = s.id and s.DMNo = d.idDM and d.idDM = ? and c.idDH = do.maDH and do.thanhToan = 'r'  \n" +
                    "group by idSP order by soLuong desc) \n" +
                    ")";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idLoai);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<SanPham> banChay_IdLoai_Ngay (String idLoai, Date bd, Date kt){
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select *\n" +
                    "from tblSanPham \n" +
                    "where id in (\n" +
                    "select idSP \n" +
                    "from tblChiTietDonHang as c, tblDonHang as h\n" +
                    "where c.idDH=h.maDH  and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,?,103) and CONVERT(date,?,103)\n" +
                    "group by idSP \n" +
                    "having sum(SLMua) = ( select top 1 sum(SLMua) soLuong  from tblChiTietDonHang as c, tblDonHang as h, tblDanhMuc as d, tblSanPham as s\n" +
                    "where c.idDH=h.maDH and c.idSP = s.id and s.DMNo = d.idDM and d.idDM = ? and \n" +
                    "h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,?,103) and CONVERT(date,?,103)\n" +
                    " group by idSP order by soLuong desc )\n" +
                    " )";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            pstm.setString(3, idLoai);
            pstm.setDate(4, bd);
            pstm.setDate(5, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<SanPham> khongBanDuoc(){
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select * \n" +
                    " from tblSanPham \n" +
                    " where id NOT IN(\n" +
                    " select idSP \n" +
                    " from tblChiTietDonHang as c, tblDonHang as h\n" +
                    " where c.idDH=h.maDH and h.thanhToan = 'r'\n" +
                    " ) ";
        PreparedStatement pstm;
        try {
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<SanPham> khongBanDuoc_Ngay(Date bd, Date kt){
        Connection cn = con.getJDBCConnection();
        List<SanPham> list = new ArrayList<>();
        String sql = "select * \n" +
                    " from tblSanPham \n" +
                    " where id NOT IN(\n" +
                    " select idSP \n" +
                    " from tblChiTietDonHang as c, tblDonHang as h \n" +
                    " where c.idDH=h.maDH and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,?,103) and CONVERT(date,?,103)\n" +
                    " ) ";
        PreparedStatement pstm;
        try {
            pstm = cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<SanPham> khongBanDuoc_idLoai(String idLoai){
        Connection cn = con.getJDBCConnection();
        List<SanPham> listSP = new ArrayList<>();
        String sql = "select * \n" +
                    " from tblSanPham \n" +
                    " where id NOT IN(\n" +
                    " select idSP \n" +
                    " from tblChiTietDonHang as c, tblDonHang as h\n" +
                    " where c.idDH=h.maDH and h.thanhToan = 'r'\n" +
                    " ) and DMNo = ? ";
        PreparedStatement pstm;
        try {
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, idLoai);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
               SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                listSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSP;
    }
     public List<SanPham> khongBanDuoc_idLoai_Ngay(String idLoai,Date bd, Date kt){
        Connection cn = con.getJDBCConnection();
        List<SanPham> listSP = new ArrayList<>();
        String sql = "select * \n" +
            " from tblSanPham \n" +
            " where id NOT IN(\n" +
            " select idSP \n" +
            " from tblChiTietDonHang as c, tblDonHang as h \n" +
            " where c.idDH=h.maDH and h.thanhToan = 'r' and convert(date,ngayTaoDH,103) between convert(date,?,103) and CONVERT(date,?,103)\n" +
            " ) and DMNo = ?";
                    
        PreparedStatement pstm;
        try {
            pstm = cn.prepareStatement(sql);
            pstm.setDate(1, bd);
            pstm.setDate(2, kt);
            pstm.setString(3, idLoai);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getString("id"));
                sp.setTen(rs.getString("tenSP"));
                sp.setDinhLuong(rs.getString("dinhLuong"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setDMNo(rs.getString("DMNo"));
                sp.setTrangThai(rs.getString("trangThai"));
                listSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTHDDAOo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSP;
    }
}
