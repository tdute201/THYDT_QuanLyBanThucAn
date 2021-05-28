/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.QLVoucherDAO;
import java.util.List;
import Models.Voucher;
import java.sql.Date;


/**
 *
 * @author acer
 */
public class QLVoucherrService {
    private final QLVoucherDAO SLCn;
    public QLVoucherrService(){
        SLCn = new QLVoucherDAO();
    }
    public List<Voucher> getALLVC(){
        return SLCn.getAllVC();//trả về dữ liệu
    }
    public void insertVC(Voucher dm){
        QLVoucherDAO.insertVC(dm);
    }
    public void deleteVC(String idVoucher){
        QLVoucherDAO.deleteVC(idVoucher);
    }
    public Voucher getVCbyID(String idDM)
    {
        return SLCn.getVCbyID(idDM);
    }
    public void updateVC(Voucher vc){
        QLVoucherDAO.updateVC(vc);
    }    
    public String NewIDVC()
    {
        return QLVoucherDAO.NewIDVC();
    }
    public List<Voucher> getVoucher(Date ngay){
        return SLCn.getVoucher(ngay);
    }
}
