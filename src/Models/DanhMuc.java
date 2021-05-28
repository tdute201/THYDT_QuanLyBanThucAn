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
public class DanhMuc {
    private String id;
    private boolean trangThai;
    private String tenDM;

    public DanhMuc() {
    }

    public DanhMuc(String id, boolean trangThai, String tenDM) {
        this.id = id;
        this.trangThai = trangThai;
        this.tenDM = tenDM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai.equals("c");
    }

    @Override
    public String toString() {
        return tenDM;
    }
    
    
    
}
