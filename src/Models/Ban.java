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
public class Ban {
    private String idBan;
    private boolean isOrdered;
    private boolean trangThai;

    public Ban() {
    }
    
    public Ban(String idBan, boolean isOrdered, boolean trangThai) {
        this.idBan = idBan;
        this.isOrdered = isOrdered;
        this.trangThai = trangThai;
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public boolean getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(String isOrdered) {
        this.isOrdered = isOrdered.equals("r");
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.isOrdered = trangThai.equals("c");
    }
    
}
