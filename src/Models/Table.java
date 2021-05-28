/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author vando
 */
public class Table {
    private String IdBan;
    private Boolean TinhTrang;
    private Boolean isOrdered;

    public String getIdBan() {
        return IdBan;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public Boolean getIsOrdered() {
        return isOrdered;
    }


    public void setIdTable(String IdBan) {
        this.IdBan = IdBan;
    }

    public void setTinhTrang(String TinhTrang) {
        if (TinhTrang.equals("c"))
            this.TinhTrang=true;
        else
            this.TinhTrang=false;
    }

    public void setIsOrdered(String isOrdered) {
        if (isOrdered.equals("c"))
            this.isOrdered=true;
        else
            this.isOrdered=false;
    }

    
}
