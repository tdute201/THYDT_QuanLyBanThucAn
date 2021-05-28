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
public class ChiTietDonHang {
    private String idDH;
    private String idSP;
    private int SLMua;
    private double giaBan;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(String idDH, String idSP, int SLMua, double giaBan) {
        this.idDH = idDH;
        this.idSP = idSP;
        this.SLMua = SLMua;
        this.giaBan = giaBan;
    }

    public String getIdDH() {
        return idDH;
    }

    public void setIdDH(String idDH) {
        this.idDH = idDH;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public int getSLMua() {
        return SLMua;
    }

    public void setSLMua(int SLMua) {
        this.SLMua = SLMua;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    
    
}
