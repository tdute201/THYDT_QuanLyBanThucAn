/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.KHHangDAO;
import Models.KHHang;
import java.util.List;

/**
 *
 * @author 84327
 */
public class ServiceKH {
    KHHangDAO khHangConnect = new KHHangDAO();
    public List<KHHang> getKh(){
        return khHangConnect.getKH();
    }
    public String NewIDKH() {
        return khHangConnect.NewIDKH();
    }
    public void Insert (KHHang khang){
        khHangConnect.Insert(khang);
    }
    public void Update (KHHang khang){
        khHangConnect.Update(khang);
    }
//    public int idKH_Max (String loaiKH){
//        return khHangConnect.idKH_Max(loaiKH);
//    }
}
