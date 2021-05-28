/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class Voucher {
    private String idVoucher;
    private String tenVoucher;
    private int phanTram;
    private int soLuong;
     private Date ngayBatDau;

    public String getIdVoucher() {
        return idVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public int getPhanTram() {
        return phanTram;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
     private Date ngayKetThuc;
  
}
