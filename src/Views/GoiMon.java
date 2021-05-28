/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.*;
import Services.QLVoucherrService;
import Services.ServiceHoaDo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Services.UserServices;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thien
 */
public class GoiMon extends javax.swing.JFrame {
    UserServices us;
    List<SanPham> listSP;
    List<DanhMuc> listDM;
    List<ChiTietDonHang> listCTDH;
    
    String idBanAn = "";
    
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    public GoiMon(String idBan) {
        idBanAn = idBan;
        us = new UserServices();
        
        initComponents();
        getAllDanhMuc();
        getAllSP();
        
        tbDonHang.setModel(dtm);
        dtm.addColumn("IDSP");
        dtm.addColumn("TÊN SẢN PHẨM");
        dtm.addColumn("SỐ LƯỢNG");
        dtm.addColumn("GIÁ BÁN");
        dtm.addColumn("THÀNH TIỀN");
        
        LoadDH();
        donHang.setText("Đơn hàng: " + us.getIDDHbyIDBan(idBan));
    }
    
    public void LoadDH() {
        if(!us.DaCoDHChuaThanhToan(idBanAn))
            us.NewDH(idBanAn);
        getCTDHbyIDBan();
    }
    
    public void NotEnableSL() {
        btnTang.setEnabled(false);
        btnGiam.setEnabled(false);
        txtsoLuong.setEnabled(false);
    }
    
    public void EnableSL() {
        btnTang.setEnabled(true);
        btnGiam.setEnabled(true);
        txtsoLuong.setEnabled(true);
    }
    
    public JPanel NewLinePanel(int w, int h, int wgap, int hgap, String bColor) {
        JPanel lineJPanel = new JPanel();
        lineJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, wgap, hgap));
        lineJPanel.setBackground(Color.decode(bColor));
        lineJPanel.setPreferredSize( new Dimension(w, h));
        return lineJPanel;
    }
    
    public String getTenSPbyID(String id) {
        for(SanPham sp : listSP)
            if(sp.getId().equals(id))
                return sp.getTen();
        return "";
    }
    
    public void getCTDHbyIDBan() {
        listCTDH = us.getCTDHbyIDBan(idBanAn);
        dtm.setRowCount(0);
        
        for(ChiTietDonHang ct : listCTDH) {
            dtm.addRow(new Object[] {ct.getIdSP(), getTenSPbyID(ct.getIdSP()), ct.getSLMua(), us.toVND(ct.getGiaBan()), us.toVND(ct.getSLMua() * ct.getGiaBan())});
        }
        SetTongTien();
    }
    
    public void getAllDanhMuc() {
        danhMuc.removeAll();
        listDM = us.getAllDM();
        
        JPanel lineJPanel = NewLinePanel(156, 40, 0, 0, "#00CCCC");
        JLabel AllSP = ThongTin("  Tất cả", 156, 40, 14, "#FFFFFF");
        AllSP.setHorizontalAlignment(SwingConstants.LEFT);
        AllSP.setOpaque(true);
        AllSP.setBackground(Color.decode("#00CCCC"));
        AllSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AllSP.setForeground(Color.white);
                AllSP.setBackground(Color.decode("#00CCCC"));
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getAllSP();
            }
        });
        AllSP.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                AllSP.setForeground(Color.decode("#00CCCC"));
                AllSP.setBackground(Color.white);
            }
        });
        
        danhMuc.setVisible(false);
        lineJPanel.add(AllSP);
        danhMuc.add(lineJPanel);
        
        for(DanhMuc d : listDM) {
            lineJPanel = NewLinePanel(156, 40, 0, 0, "#00CCCC");
            JLabel dm = DanhMuc(d);
            lineJPanel.add(dm);
            danhMuc.add(lineJPanel);
        }
        
        while(danhMuc.getComponentCount() < 17) {
            lineJPanel = NewLinePanel(156, 40, 0, 0, "#00CCCC");
            danhMuc.add(lineJPanel);
        }
        
        danhMuc.setVisible(true);
    }
    
    public JLabel DanhMuc(DanhMuc d) {
        JLabel dm = new JLabel("  " + d.getTenDM());
        
        dm.setPreferredSize(new Dimension(156, 40));
        dm.setFont(new Font("Arial", Font.BOLD, 14));
        dm.setForeground(Color.white);
        dm.setBackground(Color.decode("#00CCCC"));
        dm.setOpaque(true);
        
        DanhMucHover(dm, d.getId());
        return dm;
    }
    
    public void DanhMucHover(Component dm, String idDM) {
        dm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DMExited(dm);
            }
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getAllSPByDMNo(idDM);
            }
        });
        dm.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                DMMoved(dm);
            }
        });
    }
    
    public void DMExited(Component dm) {
        dm.setForeground(Color.white);
        dm.setBackground(Color.decode("#00CCCC"));
    }
    
    public void DMMoved(Component dm) {
        dm.setForeground(Color.decode("#00CCCC"));
        dm.setBackground(Color.white);
    }
    
    public void getAllSpByName(String name) {
        menu.removeAll();
        listSP = us.getAllSP();
        JPanel lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFF9F3");
        menu.setVisible(false);
        for(SanPham b : listSP) {
            if(b.getTen().toLowerCase().contains(name)) {
                JPanel banh = TaoSP(b);
            
                if(lineJPanel.getComponentCount() == 4)
                     lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFF9F3");
                
                lineJPanel.add(banh);
                menu.add(lineJPanel);
            }
        }
        if(menu.getComponentCount() == 0) {
            JLabel khongTimThay = ThongTin("Không tìm thấy sản phẩm nào", 650, 150, 16, "#333333");
            lineJPanel.add(khongTimThay);
            menu.add(lineJPanel);
        } 
        
        menu.setVisible(true);
        
        while(menu.getComponentCount() < 4  && menu.getComponentCount() != 0) {
            lineJPanel = NewLinePanel(650,150, 8 , 8, "#FFF9F3");
            menu.add(lineJPanel);
        }
    }
    
    public void getAllSPByDMNo(String idDM) {
        menu.removeAll();
        listSP = us.getAllSP();
        JPanel lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFF9F3");
        menu.setVisible(false);
        for(SanPham b : listSP) {
            if(b.getDMNo().equals(idDM)) {
                JPanel banh = TaoSP(b);
            
                if(lineJPanel.getComponentCount() == 4)
                     lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFF9F3");
                lineJPanel.add(banh);

                
                menu.add(lineJPanel);
            }
        }
        menu.setVisible(true);
        
        while(menu.getComponentCount() < 4  && menu.getComponentCount() != 0) {
            lineJPanel = NewLinePanel(650,150, 8 , 8, "#FFF9F3");
            menu.add(lineJPanel);
        }
    }
    
    public void getAllSP() {
        menu.removeAll();
        listSP = us.getAllSP();
        JPanel lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFFFFF");
        menu.setVisible(false);
        for(SanPham b : listSP) {
            JPanel banh = TaoSP(b);
            
            if(lineJPanel.getComponentCount() == 4)
                 lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFFFFF");
            lineJPanel.add(banh);

           
            menu.add(lineJPanel);
        }
        menu.setVisible(true);
        
        
        while(menu.getComponentCount() < 5) {
            lineJPanel = NewLinePanel(650,150, 8 , 8, "#FFFFFF");
            menu.add(lineJPanel);
        }
    }
    
    public JPanel TaoSP(SanPham b) {
        JPanel banh = new JPanel();
        
        banh.setPreferredSize(new Dimension(150, 150));
        banh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        banh.setBackground(Color.decode("#93d9ff"));
        banh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        
        JLabel tenBanh = ThongTin(b.getTen(), 150, 50, 20, "#FFFFFF");
        JLabel soLuong = ThongTin("Định lượng: " + b.getDinhLuong(), 150, 20, 14, "#333333");
        JLabel giaBan = ThongTin(us.toVND(b.getGiaBan()), 150, 20, 14, "#333333");
        
        
        banh.add(tenBanh);
        banh.add(giaBan);
        banh.add(soLuong);
        
        banh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BanhExited(tenBanh, banh);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                for(int i = 0; i < dtm.getRowCount(); i++)
                    if(b.getId().equals(dtm.getValueAt(i, 0))) {
                        int sl = (int) dtm.getValueAt(i, 2) + 1;
                        
                        dtm.setValueAt(sl, i, 2);
                        dtm.setValueAt(us.toVND(sl * b.getGiaBan()), i, 4);
                        SetTongTien();
                        return;
                    }
                dtm.addRow(new Object[] { b.getId(), b.getTen(), 1, us.toVND(b.getGiaBan()), us.toVND(b.getGiaBan())});
                SetTongTien();
            }
        });
        banh.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                BanhMoved(tenBanh, banh);
            }
        });
        return banh;
    }
    
    public void BanhExited(Component lable, JPanel panel)  {
        panel.setBackground(Color.decode("#93d9ff"));
    }
    
    public void BanhMoved(Component lable, JPanel panel)  {
        panel.setBackground(Color.decode("#85b3ef"));
    }
    
    public JLabel ThongTin(String s, int width, int height, int fontSize, String fColor) {
        JLabel thongTin = new JLabel(s,SwingConstants.CENTER);
        thongTin.setPreferredSize(new Dimension(width, height));
        thongTin.setFont(new Font("Arial", Font.BOLD, fontSize));
        thongTin.setForeground(Color.decode(fColor));
        return thongTin;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBack3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnGoiMon = new javax.swing.JLabel();
        navBan = new javax.swing.JPanel();
        btnBan = new javax.swing.JLabel();
        navTrangChu = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        tFTimKiem = new javax.swing.JTextField();
        timKiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        btnGiam = new javax.swing.JButton();
        txtsoLuong = new javax.swing.JTextField();
        btnTang = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDonHang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        danhMuc = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tongTien = new javax.swing.JLabel();
        donHang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 249, 243));

        jScrollPane1.setBackground(new java.awt.Color(255, 249, 243));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jScrollPane1.setFocusCycleRoot(true);
        jScrollPane1.setOpaque(false);

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setMinimumSize(new java.awt.Dimension(650, 530));
        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(menu);
        menu.getAccessibleContext().setAccessibleDescription("");

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        btnBack3.setBackground(new java.awt.Color(102, 204, 255));
        btnBack3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack3.setForeground(new java.awt.Color(255, 255, 255));
        btnBack3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack3.setText("/");
        btnBack3.setOpaque(true);

        jPanel6.setBackground(new java.awt.Color(102, 204, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        btnGoiMon.setBackground(new java.awt.Color(102, 204, 255));
        btnGoiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGoiMon.setForeground(new java.awt.Color(255, 255, 255));
        btnGoiMon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGoiMon.setText("Gọi món");
        btnGoiMon.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnGoiMonMouseMoved(evt);
            }
        });
        btnGoiMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGoiMonMouseExited(evt);
            }
        });
        jPanel6.add(btnGoiMon);

        navBan.setBackground(new java.awt.Color(102, 204, 255));
        navBan.setPreferredSize(new java.awt.Dimension(100, 40));
        navBan.setLayout(new java.awt.GridLayout(1, 0));

        btnBan.setBackground(new java.awt.Color(102, 204, 255));
        btnBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBan.setForeground(new java.awt.Color(255, 255, 255));
        btnBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBan.setText("Bàn");
        btnBan.setMinimumSize(new java.awt.Dimension(20, 40));
        btnBan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBanMouseMoved(evt);
            }
        });
        btnBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBanMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanMouseExited(evt);
            }
        });
        navBan.add(btnBan);

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

        tFTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        timKiem.setBackground(new java.awt.Color(255, 255, 255));
        timKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timKiem.setText("Tìm kiếm");
        timKiem.setBorderPainted(false);
        timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(navTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addGap(3, 3, 3)
                .addComponent(navBan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(tFTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(timKiem)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tFTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBack3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 249, 240));

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("XÓA");
        btnXoa.setBorder(null);
        btnXoa.setBorderPainted(false);
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnGiam.setBackground(new java.awt.Color(255, 255, 255));
        btnGiam.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGiam.setForeground(new java.awt.Color(255, 131, 131));
        btnGiam.setText("-");
        btnGiam.setBorderPainted(false);
        btnGiam.setEnabled(false);
        btnGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangActionPerformed(evt);
            }
        });

        txtsoLuong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtsoLuong.setForeground(new java.awt.Color(153, 153, 153));
        txtsoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsoLuong.setBorder(null);
        txtsoLuong.setEnabled(false);
        txtsoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsoLuongKeyPressed(evt);
            }
        });

        btnTang.setBackground(new java.awt.Color(255, 255, 255));
        btnTang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTang.setForeground(new java.awt.Color(51, 153, 0));
        btnTang.setText("+");
        btnTang.setBorderPainted(false);
        btnTang.setEnabled(false);
        btnTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Số lượng:");

        btnThanhToan.setBackground(new java.awt.Color(255, 153, 51));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorderPainted(false);
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtsoLuong)
            .addComponent(btnTang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbDonHang.setBackground(new java.awt.Color(255, 249, 240));
        tbDonHang.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tbDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDonHang.setGridColor(new java.awt.Color(0, 102, 102));
        tbDonHang.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tbDonHang.setShowHorizontalLines(false);
        tbDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDonHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDonHang);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        danhMuc.setBackground(new java.awt.Color(0, 204, 204));
        danhMuc.setLayout(new javax.swing.BoxLayout(danhMuc, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(danhMuc);

        jPanel3.setBackground(new java.awt.Color(255, 249, 240));

        tongTien.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tongTien.setForeground(new java.awt.Color(51, 51, 51));
        tongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongTien.setText("Tổng tiền: 0đ");

        donHang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        donHang.setForeground(new java.awt.Color(51, 51, 51));
        donHang.setText("Đơn hàng: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(donHang, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(donHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void XoaRowHoaDon() {
        int confirm = JOptionPane.showConfirmDialog(rootPane , "Bạn có chắn chắn muốn xóa không!", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);

        if(confirm != JOptionPane.YES_OPTION) {
            return;
        }
        dtm.removeRow(tbDonHang.getSelectedRow());
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.gray);
        txtsoLuong.setText("");
        NotEnableSL();
        SetTongTien();
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        XoaRowHoaDon();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDonHangMouseClicked
        int selected = tbDonHang.getSelectedRow();
        txtsoLuong.setText(String.valueOf(tbDonHang.getValueAt(selected, 2)));
        btnXoa.setEnabled(true);
        btnXoa.setBackground(Color.decode("#FF8383"));
        EnableSL();
    }//GEN-LAST:event_tbDonHangMouseClicked

    private void btnTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTangActionPerformed
        int vt = tbDonHang.getSelectedRow();
        int sl = (int) dtm.getValueAt(vt, 2) ;
        
        if("+".equals(evt.getActionCommand())) {
            dtm.setValueAt(++sl, vt, 2);
        } else if(sl == 1) {
            btnXoaActionPerformed(evt);
            SetTongTien();
            return;
        } else {
            dtm.setValueAt(--sl, vt, 2);
        }
        
        txtsoLuong.setText( String.valueOf(tbDonHang.getValueAt(vt, 2)));
        SetThanhTien(sl);
        SetTongTien();
    }//GEN-LAST:event_btnTangActionPerformed
    
    public void SetTongTien() {
        String thanhTien;
        double tTien = 0;
        for(int i = 0; i < tbDonHang.getRowCount(); i++) {
            thanhTien = String.valueOf(tbDonHang.getValueAt(i, 4));
            thanhTien = thanhTien.substring(0, thanhTien.length()-2).replace(".", "");
            tTien += Double.parseDouble(thanhTien);
        }
        
        tongTien.setText("Tổng tiền: " + us.toVND(tTien));
    }
    
    public void SetThanhTien(int sl) {
        int vt =  tbDonHang.getSelectedRow();
        String gia = String.valueOf(tbDonHang.getValueAt(vt, 3));
        gia = gia.substring(0, gia.length()-2).replace(".", "");
        double thanhTien = sl * Double.parseDouble(gia);
        dtm.setValueAt(us.toVND(thanhTien), vt, 4);
    }
    
    private void txtsoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsoLuongKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int sl = Integer.parseInt(txtsoLuong.getText());
                int vt =  tbDonHang.getSelectedRow();
                
                if(sl == 0) {
                    XoaRowHoaDon();
                    return;
                }
                if(sl < 0) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập số >= 0!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                dtm.setValueAt(sl,vt, 2);
                txtsoLuong.setText( String.valueOf(tbDonHang.getValueAt(vt, 2)));
                SetThanhTien(sl);
                SetTongTien();
            } catch(HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Hãy nhập số nguyên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtsoLuongKeyPressed

    private void btnTrangChuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseMoved
        btnTrangChu.setForeground(Color.decode("#66CCFF"));
        navTrangChu.setBackground(Color.white);
    }//GEN-LAST:event_btnTrangChuMouseMoved

    private void btnBanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanMouseMoved
        btnBan.setForeground(Color.decode("#66CCFF"));
        navBan.setBackground(Color.white);
    }//GEN-LAST:event_btnBanMouseMoved

    private void btnGoiMonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoiMonMouseMoved
        btnGoiMon.setForeground(Color.decode("#66CCFF"));
        jPanel6.setBackground(Color.white);
    }//GEN-LAST:event_btnGoiMonMouseMoved

    private void btnGoiMonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoiMonMouseExited
        btnGoiMon.setForeground(Color.white);
        jPanel6.setBackground(Color.decode("#66CCFF"));
    }//GEN-LAST:event_btnGoiMonMouseExited

    private void btnBanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanMouseExited
        btnBan.setForeground(Color.white);
        navBan.setBackground(Color.decode("#66CCFF"));
    }//GEN-LAST:event_btnBanMouseExited

    private void btnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseExited
        btnTrangChu.setForeground(Color.white);
        navTrangChu.setBackground(Color.decode("#66CCFF"));
        
        
    }//GEN-LAST:event_btnTrangChuMouseExited

    private void btnBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanMouseClicked
        updateDSMonByID();
        if(dtm.getRowCount()==0)
            us.IsOrdered_k(idBanAn);
        new BanAn_MangVe().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBanMouseClicked

    private void timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemActionPerformed
        String tenSP = tFTimKiem.getText().trim().toLowerCase();
        if(tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập tên sản phẩm cần tìm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        getAllSpByName(tenSP);
    }//GEN-LAST:event_timKiemActionPerformed

    private void btnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseClicked
       updateDSMonByID();
       if(dtm.getRowCount()==0)
            us.IsOrdered_k(idBanAn);
       TrangChu tc = new TrangChu();
        tc.setVisible(true);
        tc.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        updateDSMonByID();
        String idDH = us.getIDDHbyIDBan(idBanAn);
        ServiceHoaDo hd = new ServiceHoaDo();
        QLVoucherrService vc = new QLVoucherrService();
        HDonn hoadon = new HDonn();
        hoadon = hd.getHoaDon(idDH);
        List<Voucher> list = vc.getVoucher((Date) hoadon.getNgayTao());
        List<ChiTietDonHang> ctdh = us.getCTDHbyIDBan(idBanAn);
        ThanhToan tt = new ThanhToan(this,idBanAn,list,ctdh, idDH);
        tt.setVisible(true);
        tt.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_btnThanhToanActionPerformed
    
    public void updateDSMonByID() {
        String idDH, idSP, gia;
        int sl;
        
        idDH = us.getIDDHbyIDBan(idBanAn);
        us.ResetCTDHByID(idDH);
        for(int i = 0; i < tbDonHang.getRowCount(); i++) {
            idSP = (String) tbDonHang.getValueAt(i, 0);
            
            sl = Integer.parseInt(String.valueOf(tbDonHang.getValueAt(i, 2)));
            gia = String.valueOf(tbDonHang.getValueAt(i, 3));
            gia = gia.substring(0, gia.length()-2).replace(".", "");
            
            us.CapNhatDSMonByID(idDH, idSP, sl, Double.parseDouble(gia));
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBack3;
    private javax.swing.JLabel btnBan;
    private javax.swing.JButton btnGiam;
    private javax.swing.JLabel btnGoiMon;
    private javax.swing.JButton btnTang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel danhMuc;
    private javax.swing.JLabel donHang;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel navBan;
    private javax.swing.JPanel navTrangChu;
    private javax.swing.JTextField tFTimKiem;
    private javax.swing.JTable tbDonHang;
    private javax.swing.JButton timKiem;
    private javax.swing.JLabel tongTien;
    private javax.swing.JTextField txtsoLuong;
    // End of variables declaration//GEN-END:variables
}
