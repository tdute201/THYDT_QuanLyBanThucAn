package XuatFile;
import Models.CTHD;
import Models.ChiTietDonHang;
import Models.SanPham;
import Services.UserServices;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
/**
 *
 * @author giasutinhoc.vn
 */
public class Xuat {
 public void xuatFile (Date ngay, List<ChiTietDonHang> list, int tTien, double tGiam, double tongTien) {
     UserServices serviceSP = new UserServices();
   try {
     //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
     File f = new File("d:/file/mydata.txt");
     FileWriter fw = new FileWriter(f);
     //Bước 2: Ghi dữ liệu
     fw.write("\n\nNgày: "+ngay);
     fw.write("\n\n----------------------------------------------------------------------");
     
       for (ChiTietDonHang cthd : list) {
            String id = cthd.getIdSP();
            String tenSP="";
            for (SanPham sp : serviceSP.getAllSP()) {
                if(id == null ? sp.getId()== null : id.equals(sp.getId()))
                {
                    tenSP = sp.getTen();
                    
                }
            }
            fw.write("\n"+tenSP+" | "  + cthd.getSLMua()+" | "+cthd.getGiaBan());
       }
       fw.write("\n\n----------------------------------------------------------------------");
       fw.write("\n\nThành tiền: " + tTien);
       fw.write("\n\nTiền giảm: " + tGiam);
       fw.write("\n\nTổng tiền: " + tongTien);
     //Bước 3: Đóng luồng
     fw.close();
   } catch (IOException ex) {
     System.out.println("Loi ghi file: " + ex);
 }
 }
}