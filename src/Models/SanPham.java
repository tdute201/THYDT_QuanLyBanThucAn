/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author thien
 */
public class SanPham {
    private String id;
    private String ten;
    private String dinhLuong;
    private double giaBan;
    private boolean trangThai;
    private String DMNo;

    public SanPham() {
    }

    public SanPham(String id, String ten, String dinhLuong, double giaBan, boolean trangThai, String DMNo) {
        this.id = id;
        this.ten = ten;
        this.dinhLuong = dinhLuong;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.DMNo = DMNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getDMNo() {
        return DMNo;
    }

    public void setDMNo(String DMNo) {
        this.DMNo = DMNo;
    }

    public String getDinhLuong() {
        return dinhLuong;
    }

    public void setDinhLuong(String dinhLuong) {
        this.dinhLuong = dinhLuong;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai.equals("c");
    }

    
}
