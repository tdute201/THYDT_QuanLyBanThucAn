/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.UserDao;
import Models.DanhMuc;
import Models.SanPham;
import Models.thongKeVoucher;
import Services.ServiceCTHDd;
import Services.UserServices;
import java.awt.Color;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class thongKe extends javax.swing.JFrame {
    UserDao userDao;
    ServiceCTHDd serviceCTHD;
    DefaultTableModel defaultTableModel, tableModel, dftb, df;
    UserServices userServices;
    public void setTabelSP(List<SanPham> list){
        int i=1;
        tongTien = 0;
        for (SanPham sPham : list) {
            int sl = serviceCTHD.soLuongSP(sPham.getId());
            tongTien+=(sPham.getGiaBan())*sl;
            //userServices.toVND(tongTien);
            defaultTableModel.addRow(new Object[]{i,sPham.getTen(),userServices.toVND(sPham.getGiaBan()),sl,userServices.toVND((sPham.getGiaBan())*sl)});
            i++;
        }
    }
    public void setTabelSP_BanChay(List<SanPham> list){
        int i=1;
        for (SanPham sPham : list) {
            int sl = serviceCTHD.soLuongSP(sPham.getId());
            dftb.addRow(new Object[]{i,sPham.getTen(),userServices.toVND(sPham.getGiaBan()),sl,userServices.toVND((sPham.getGiaBan())*sl)});
            i++;
        }
    }
    public void setTabelSP_BanChay_Ngay(List<SanPham> list, Date bd, Date kt){ //sản phẩm bán chạy theo loại trong khoảng thời gian
        int i=1;
        for (SanPham sPham : list) {
            int sl = serviceCTHD.soLuongSP_Ngay(sPham.getId(),bd,kt);
            dftb.addRow(new Object[]{i,sPham.getTen(),userServices.toVND(sPham.getGiaBan()),sl,userServices.toVND((sPham.getGiaBan())*sl)});
            i++;
        }
    }
    public void setTabelSP(List<SanPham> list, Date bd, Date kt){ //trong khoảng thời gian
        int i=1;
        tongTien = 0;
        for (SanPham sPham : list) {
            int sl = serviceCTHD.soLuongSP_Ngay(sPham.getId(),bd,kt);
            tongTien+=(sPham.getGiaBan())*sl;
            defaultTableModel.addRow(new Object[]{i,sPham.getTen(),userServices.toVND(sPham.getGiaBan()),sl,userServices.toVND((sPham.getGiaBan())*sl)});
            i++;
        }
    }
    public void setTableVc(List<thongKeVoucher> list){
        double tong;
        tongVc=0;
        for (thongKeVoucher keVoucher : list) {
            tong = serviceCTHD.tongTien_HD_VC(keVoucher.getIdVC())*keVoucher.getPhanTram()/100;
            tongVc+=tong;
            tableModel.addRow(new Object[]{keVoucher.getTenVC(),keVoucher.getPhanTram(),keVoucher.getSl(),userServices.toVND(tong)});
        }
    }
    public void setTableVc(List<thongKeVoucher> list, Date bd, Date kt){
        double tong;
        tongVc=0;
        for (thongKeVoucher keVoucher : list) {
            tong = serviceCTHD.tongTien_HD_VC_Ngay(keVoucher.getIdVC(),bd,kt)*keVoucher.getPhanTram()/100;
            tongVc+=tong;
            tableModel.addRow(new Object[]{keVoucher.getTenVC(),keVoucher.getPhanTram(),keVoucher.getSl(),userServices.toVND(tong)});
        }
    }
    public String idLoai(String tenLoai){
        String idLoai="";
        for (DanhMuc object : userDao.getAllDM()) {
                    if(tenLoai == null ? object.getTenDM()== null : tenLoai.equals(object.getTenDM()))
                        idLoai = object.getId();
                }
        return idLoai;
    }
    public void setTabelSP_KhongBanDuoc(List<SanPham> list){
        int i=1;
        for (SanPham sPham : list) {
            df.addRow(new Object[]{i,sPham.getTen(),userServices.toVND(sPham.getGiaBan())});
            i++;
        }
    }
    public thongKe() {
        initComponents();
        userDao = new UserDao();
        serviceCTHD = new ServiceCTHDd();
        userServices = new UserServices();
        List<DanhMuc> listDM = userDao.getAllDM();
        cbDanhMuc.removeAllItems();
        cbDanhMuc.addItem("---Chọn danh mục---");
        for (DanhMuc danhMuc : listDM) {
            cbDanhMuc.addItem(danhMuc.getTenDM());
        }
        lbDM.setVisible(false);
        cbDanhMuc.setVisible(false);
        
        defaultTableModel = new DefaultTableModel(){
            @Override //để người dùng không thể thay đổi dữ liêu thêm vào isCellEdittable
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column); 
                return false;
            }
        };
        tbDoanhThu.setModel(defaultTableModel);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("TÊN SẢN PHẨM");
        defaultTableModel.addColumn("ĐƠN GIÁ");
        defaultTableModel.addColumn("SỐ LƯỢNG ĐÃ BÁN");
        defaultTableModel.addColumn("THÀNH TIỀN");
        setTabelSP(userDao.getAllSP());
        
        lbTongTien.setText(""+userServices.toVND(tongTien));
        
        tableModel = new DefaultTableModel(){
            @Override //để người dùng không thể thay đổi dữ liêu thêm vào isCellEdittable
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column); 
                return false;
            }
        };
        tableModel.addColumn("TÊN VOUCHER");
        tableModel.addColumn("PHẦN TRĂM");
        tableModel.addColumn("SỐ LƯỢNG DÙNG");
        tableModel.addColumn("THÀNH TIỀN");
        tbVC.setModel(tableModel);
        setTableVc(serviceCTHD.listVoucher());
        lbTongVC.setText(""+userServices.toVND(tongVc));
        lbTongDoanhThu.setText(""+userServices.toVND(tongTien-tongVc));
        
        dftb = new DefaultTableModel(){
            @Override //để người dùng không thể thay đổi dữ liêu thêm vào isCellEdittable
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column); 
                return false;
            }
        };
       
        dftb.addColumn("STT");
        dftb.addColumn("TÊN SẢN PHẨM");
        dftb.addColumn("ĐƠN GIÁ");
        dftb.addColumn("SỐ LƯỢNG ĐÃ BÁN");
        dftb.addColumn("THÀNH TIỀN");
        tbBanChay.setModel(dftb);
        setTabelSP_BanChay(serviceCTHD.banChay());
        
        df = new DefaultTableModel(){
            @Override //để người dùng không thể thay đổi dữ liêu thêm vào isCellEdittable
            public boolean isCellEditable(int row, int column) {
                //return super.isCellEditable(row, column); 
                return false;
            }
        };
       
        df.addColumn("STT");
        df.addColumn("TÊN SẢN PHẨM");
        df.addColumn("ĐƠN GIÁ");
        tblKhongBanDuoc.setModel(df);
        setTabelSP_KhongBanDuoc(serviceCTHD.khongBanDuoc());
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneltable = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbVC = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbTongVC = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBanChay = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhongBanDuoc = new javax.swing.JTable();
        lbDThu = new javax.swing.JButton();
        lbTongDoanhThu = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        navTrangChu = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        btnBan = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        ngayKT = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ngayBD = new com.toedter.calendar.JDateChooser();
        lbDM = new javax.swing.JButton();
        cbDanhMuc = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        paneltable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        paneltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneltableMouseClicked(evt);
            }
        });

        tbDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản shẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbDoanhThu);
        if (tbDoanhThu.getColumnModel().getColumnCount() > 0) {
            tbDoanhThu.getColumnModel().getColumn(0).setResizable(false);
        }

        tbVC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbVC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "id voucher", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(tbVC);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        lbTongTien.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Tổng voucher:");

        lbTongVC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTongVC, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lbTongVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        paneltable.addTab("Doanh thu", jPanel1);

        tbBanChay.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tbBanChay);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        paneltable.addTab("Những sản phẩm bán chạy nhất", jPanel2);

        tblKhongBanDuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng sản phẩm", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblKhongBanDuoc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        paneltable.addTab("Những sản phẩm không được ưa chuộng", jPanel3);

        lbDThu.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lbDThu.setText("Tổng doanh thu");

        lbTongDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        navTrangChu.setBackground(new java.awt.Color(102, 204, 255));
        navTrangChu.setPreferredSize(new java.awt.Dimension(100, 40));
        navTrangChu.setLayout(new java.awt.GridLayout(1, 0));

        btnTrangChu.setBackground(new java.awt.Color(102, 204, 255));
        btnTrangChu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseMoved(evt);
            }
        });
        btnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseExited(evt);
            }
        });
        navTrangChu.add(btnTrangChu);

        btnBack.setBackground(new java.awt.Color(102, 204, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setText("/");
        btnBack.setOpaque(true);

        btnBan.setBackground(new java.awt.Color(102, 204, 255));
        btnBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBan.setForeground(new java.awt.Color(255, 255, 255));
        btnBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBan.setText("Thống kê");
        btnBan.setMinimumSize(new java.awt.Dimension(20, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(navTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(774, 774, 774))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("THỐNG KÊ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ngayKT.setDateFormatString("d/M/y");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Ngày kết thúc:");
        jLabel3.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Ngày bắt đầu:");
        jLabel2.setToolTipText("");

        ngayBD.setDateFormatString("d/M/y");

        lbDM.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbDM.setText("Danh mục sản phẩm");
        lbDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbDMActionPerformed(evt);
            }
        });

        cbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbDM, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(ngayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneltable, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbDThu)
                        .addGap(47, 47, 47)
                        .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneltable, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDThu)
                    .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = paneltable.getSelectedIndex();
        String nbd = ((JTextField)ngayBD.getDateEditor().getUiComponent()).getText();
        String nkt = ((JTextField)ngayKT.getDateEditor().getUiComponent()).getText();
        if(i==0)
        {
            
            if("".equals(nbd)&&"".equals(nkt))
            {
                defaultTableModel.setRowCount(0);
                setTabelSP(userDao.getAllSP());
                lbTongTien.setText(""+userServices.toVND(tongTien));
                tableModel.setRowCount(0);
                setTableVc(serviceCTHD.listVoucher());
                lbTongVC.setText(""+userServices.toVND(tongVc));
                lbTongDoanhThu.setText(""+userServices.toVND(tongTien-tongVc));
            }

            if("".equals(nbd)&& !"".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu không được để trống");

            if(!"".equals(nbd)&& "".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được để trống");
            if(!"".equals(nbd)&& !"".equals(nkt))        
            {
                java.util.Date b =  ngayBD.getDate();
                java.sql.Date bd=new java.sql.Date (b.getTime());
                java.util.Date k =  ngayKT.getDate();
                java.sql.Date kt=new java.sql.Date (k.getTime());
                if(k.before(b))
                    JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                else
                {
                    defaultTableModel.setRowCount(0);
                    setTabelSP(serviceCTHD.getAllSP_DaBan_theoNgay(bd, kt),bd,kt);
                    lbTongTien.setText(""+userServices.toVND(tongTien));
                    tableModel.setRowCount(0);
                    setTableVc(serviceCTHD.listVoucher_Ngay(bd, kt), bd, kt);
                    lbTongVC.setText(""+userServices.toVND(tongVc));
                    lbTongDoanhThu.setText(""+userServices.toVND(tongTien-tongVc));
                }
            }

        }
        if(i==1)
        {
            if("".equals(nbd)&&"".equals(nkt))
            {
                if(cbDanhMuc.getSelectedIndex()<=0)
                {
                    dftb.setRowCount(0);
                    setTabelSP_BanChay(serviceCTHD.banChay());
                }
                else
                {
                    String tenLoai = (String) cbDanhMuc.getSelectedItem();
                    String idL = idLoai(tenLoai);
                    System.out.println(idL);
                    dftb.setRowCount(0);
                    setTabelSP_BanChay(serviceCTHD.banChay_IdLoai(idL));
                }
            }

            if("".equals(nbd)&& !"".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu không được để trống");

            if(!"".equals(nbd)&& "".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được để trống");
            if(!"".equals(nbd)&& !"".equals(nkt))        
            {
                java.util.Date b =  ngayBD.getDate();
                java.sql.Date bd=new java.sql.Date (b.getTime());
                java.util.Date k =  ngayKT.getDate();
                java.sql.Date kt=new java.sql.Date (k.getTime());
                if(k.before(b))
                    JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                else
                {
                    if(cbDanhMuc.getSelectedIndex()<=0)
                    {
                        dftb.setRowCount(0);
                        setTabelSP_BanChay(serviceCTHD.banChay_Ngay(bd,kt));
                    }
                    else
                    {
                        String tenLoai = (String) cbDanhMuc.getSelectedItem();
                        String idL = idLoai(tenLoai);
                        dftb.setRowCount(0);
                        setTabelSP_BanChay_Ngay(serviceCTHD.banChay_IdLoai_Ngay(idL,bd,kt),bd,kt);
                    }
                }
            }
        }
        if(i==2)
        {
            if("".equals(nbd)&&"".equals(nkt))
            {
                if(cbDanhMuc.getSelectedIndex()<=0)
                {
                    df.setRowCount(0);
                    setTabelSP_KhongBanDuoc(serviceCTHD.khongBanDuoc());
                }
                else
                {
                    String tenLoai = (String) cbDanhMuc.getSelectedItem();
                    String idL = idLoai(tenLoai);
                    df.setRowCount(0);
                    setTabelSP_KhongBanDuoc(serviceCTHD.khongBanDuoc_idLoai(idL));
                }
            }
            if("".equals(nbd)&& !"".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu không được để trống");

            if(!"".equals(nbd)&& "".equals(nkt))
                JOptionPane.showMessageDialog(rootPane, "Ngày kết thúc không được để trống");
            if(!"".equals(nbd)&& !"".equals(nkt))        
            {
                java.util.Date b =  ngayBD.getDate();
                java.sql.Date bd=new java.sql.Date (b.getTime());
                java.util.Date k =  ngayKT.getDate();
                java.sql.Date kt=new java.sql.Date (k.getTime());
                if(k.before(b))
                    JOptionPane.showMessageDialog(rootPane, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                else
                {
                    if(cbDanhMuc.getSelectedIndex()<=0)
                    {
                        df.setRowCount(0);
                        setTabelSP_KhongBanDuoc(serviceCTHD.khongBanDuoc_Ngay(bd,kt));
                    }
                    else
                    {
                        String tenLoai = (String) cbDanhMuc.getSelectedItem();
                        String idL = idLoai(tenLoai);
                        df.setRowCount(0);
                        setTabelSP_KhongBanDuoc(serviceCTHD.khongBanDuoc_idLoai_Ngay(idL, bd, kt));
                    }
                }
            }
        }
        
      

    }//GEN-LAST:event_jButton1ActionPerformed

    private void paneltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneltableMouseClicked
        int i = paneltable.getSelectedIndex();
        if(i==0)
        {
            lbDM.setVisible(false);
            cbDanhMuc.setVisible(false);
            lbDThu.setVisible(true);
            lbTongDoanhThu.setVisible(true);
        }
        if(i==1)
        {
            lbDM.setVisible(true);
            cbDanhMuc.setVisible(true);
            lbDThu.setVisible(false);
            lbTongDoanhThu.setVisible(false);
        }
        if(i==2)
        {
            lbDM.setVisible(true);
            cbDanhMuc.setVisible(true);
            lbDThu.setVisible(false);
            lbTongDoanhThu.setVisible(false);
        }
    }//GEN-LAST:event_paneltableMouseClicked

    private void btnTrangChuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseMoved
        btnTrangChu.setForeground(Color.decode("#66CCFF"));
        navTrangChu.setBackground(Color.white);
    }//GEN-LAST:event_btnTrangChuMouseMoved

    private void btnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseClicked
        TrangChu tc = new TrangChu();
        tc.setVisible(true);
        tc.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuMouseClicked

    private void btnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseExited
        btnTrangChu.setForeground(Color.white);
        navTrangChu.setBackground(Color.decode("#66CCFF"));
    }//GEN-LAST:event_btnTrangChuMouseExited

    private void lbDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbDMActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//      
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                thongKe tk = new thongKe();
//                tk.setVisible(true);
//                tk.setLocationRelativeTo(null);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBan;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JComboBox<String> cbDanhMuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton lbDM;
    private javax.swing.JButton lbDThu;
    private javax.swing.JTextField lbTongDoanhThu;
    private javax.swing.JTextField lbTongTien;
    private javax.swing.JTextField lbTongVC;
    private javax.swing.JPanel navTrangChu;
    private com.toedter.calendar.JDateChooser ngayBD;
    private com.toedter.calendar.JDateChooser ngayKT;
    private javax.swing.JTabbedPane paneltable;
    private javax.swing.JTable tbBanChay;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbVC;
    private javax.swing.JTable tblKhongBanDuoc;
    // End of variables declaration//GEN-END:variables
    private double tongTien, tongVc=0;
    
}
