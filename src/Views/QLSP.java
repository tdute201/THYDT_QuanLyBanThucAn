/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.*;
import Services.UserServices;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author thien
 */
public class QLSP extends javax.swing.JFrame {
    UserServices us;
    List<SanPham> listSP;
    List<DanhMuc> listDM;
    String ID = "";
    public QLSP() {
        us = new UserServices();
        
        initComponents();
        getAllDanhMuc();
        getAllDMForCombobox();
        getAllSP();
        
    }
    
    public JPanel NewLinePanel(int w, int h, int wgap, int hgap, String bColor) {
        JPanel lineJPanel = new JPanel();
        lineJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, wgap, hgap));
        lineJPanel.setBackground(Color.decode(bColor));
        lineJPanel.setPreferredSize( new Dimension(w, h));
        return lineJPanel;
    }
    
    public void getAllDMForCombobox() {
        danhMucSP.removeAllItems();
        listDM = us.getAllDM();
        for(DanhMuc d : listDM) 
            danhMucSP.addItem(d);
    }
    
    public void getAllDanhMuc() {
        danhMuc.removeAll();
        listDM = us.getAllDM();
        
        JPanel lineJPanel = NewLinePanel(134, 40, 0, 0, "#00CCCC");
        JLabel AllSP = ThongTin("Tất cả", 134, 40, 14, "#FFFFFF");
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
            lineJPanel = NewLinePanel(134, 40, 0, 0, "#00CCCC");
            JPanel panelDM = PanelDanhMuc(d);
            lineJPanel.add(panelDM);
            danhMuc.add(lineJPanel);
        }
        
        while(danhMuc.getComponentCount() < 12) {
            lineJPanel = NewLinePanel(134, 40, 0, 0, "#00CCCC");
            danhMuc.add(lineJPanel);
        }
        
        danhMuc.setVisible(true);
    }
    
    public JPanel PanelDanhMuc(DanhMuc d) {
        JPanel pDM = new JPanel();
        
        pDM.setPreferredSize(new Dimension(134, 40));
        pDM.setBackground(Color.decode("#00CCCC"));
        pDM.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField dm = DanhMuc(pDM,d);
        JButton btnXoa = XoaButton("X",pDM, dm, 40, 40);
        ActionXoaDM(btnXoa, d.getId());
        DMMoved_Exited(btnXoa, pDM, dm);
        
        pDM.add(btnXoa);
        pDM.add(dm);
        
        return pDM;
    }
    
    public JTextField DanhMuc(JPanel panel,DanhMuc d) {
        JTextField dm = new JTextField("   " + d.getTenDM());
        
        dm.setPreferredSize(new Dimension(94, 40));
        dm.setHorizontalAlignment(SwingConstants.LEADING);
        dm.setFont(new Font("Arial", Font.BOLD, 14));
        dm.setForeground(Color.white);
        dm.setBackground(Color.decode("#00CCCC"));
        dm.setBorder(null);
        
        DanhMucHover(panel,dm, d.getId());
        
        dm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
              if(!DMCheckBeforeUpdate(evt, dm)) 
                  return;
              
              if(us.UpdateDMByID(d.getId(), dm.getText().trim())) {
                  JOptionPane.showMessageDialog(rootPane , "Chỉnh sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                  getAllDanhMuc();
                  getAllDMForCombobox();
              }
              else
                JOptionPane.showMessageDialog(rootPane , "Chỉnh sửa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return dm;
    }
    
    public Boolean DMCheckBeforeUpdate(KeyEvent evt,JTextField c) {
        if(evt.getKeyCode() != KeyEvent.VK_ENTER) 
            return false;

        if(c.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void DanhMucHover(JPanel panel,Component dm, String idDM) {
        dm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DMExited(panel,dm);
            }
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getAllSPByDMNo(idDM);
            }
        });
        dm.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                DMMoved(panel,dm);
            }
        });
    }
    
    public void DMExited(JPanel panel, Component dm) {
        panel.setBackground(Color.decode("#00CCCC"));
        dm.setForeground(Color.white);
        dm.setBackground(Color.decode("#00CCCC"));
    }
    
    public void DMMoved(JPanel panel, Component dm) {
        dm.setForeground(Color.decode("#00CCCC"));
        dm.setBackground(Color.white);
        panel.setBackground(Color.white);
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
        
        if(menu.getComponentCount() == 0) {
            JLabel khongTimThay = ThongTin("Không tìm thấy sản phẩm nào", 650, 150, 16, "#333333");
            lineJPanel.add(khongTimThay);
            menu.add(lineJPanel);
        } 
        
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
            
            if(lineJPanel.getComponentCount() == 5)
                 lineJPanel = NewLinePanel(650, 150, 8, 8, "#FFFFFF");
            lineJPanel.add(banh);

           
            menu.add(lineJPanel);
        }
        menu.setVisible(true);
        
        if(menu.getComponentCount() == 0) {
            JLabel khongTimThay = ThongTin("Không tìm thấy sản phẩm nào", 650, 150, 16, "#333333");
            lineJPanel.add(khongTimThay);
            menu.add(lineJPanel);
        } 
        
        while(menu.getComponentCount() < 4) {
            lineJPanel = NewLinePanel(650,150, 8 , 8, "#FFFFFF");
            menu.add(lineJPanel);
        }
        
    }
    
    public JButton XoaButton(String txt,JPanel p, Component cp, int w, int h) {
        JButton btnXoa = new JButton(txt);
        
        btnXoa.setPreferredSize(new Dimension(w, h));
        btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXoa.setFont(new Font("Arial", Font.BOLD, 12));
        btnXoa.setForeground(Color.white);
        btnXoa.setBackground(Color.decode("#FF8383"));
        return btnXoa;
    }
    
    public void DMMoved_Exited(JButton btnXoa, JPanel p, Component cp) {
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DMExited(p, cp);
                btnXoa.setForeground(Color.white);
                btnXoa.setBackground(Color.decode("#FF8383"));
            }
        });
        btnXoa.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                DMMoved(p, cp);
                btnXoa.setForeground(Color.decode("#FF8383"));
                btnXoa.setBackground(Color.white);
            }
        });
    }
    
    public void SPMoved_Exited(JButton btnXoa, JPanel p, Component cp) {
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BanhExited(cp, p);
                btnXoa.setForeground(Color.white);
                btnXoa.setBackground(Color.decode("#FF8383"));
            }
        });
        btnXoa.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                BanhMoved(cp, p);
                btnXoa.setForeground(Color.decode("#FF8383"));
                btnXoa.setBackground(Color.white);
            }
        });
    }
    
    public void ActionXoaSP(JButton btnXoa, String id) {
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(rootPane , "Bạn có chắn chắn muốn xóa không!", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);

                if(confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                if(us.DeleteSP(id)) {
                    JOptionPane.showMessageDialog(rootPane , "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    tenUpdate.setText("");
                    dinhLuongUpdate.setText("");
                    giaBanUpdate.setText("");
                }
                else
                    JOptionPane.showMessageDialog(rootPane , "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                getAllSP();
            }
        });
    }
    
    public void ActionXoaDM(JButton btnXoa, String id) {
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(rootPane , "Bạn có chắn chắn muốn xóa không!", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);

                if(confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                if(us.DeleteDMById(id)) {
                    JOptionPane.showMessageDialog(rootPane , "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    tenUpdate.setText("");
                    dinhLuongUpdate.setText("");
                    giaBanUpdate.setText("");
                }
                else
                    JOptionPane.showMessageDialog(rootPane , "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                getAllSP();
                getAllDanhMuc();
                getAllDMForCombobox();
            }
        });
    }
    
    public JPanel TaoSP(SanPham b) {
        JPanel banh = new JPanel();
        
        banh.setPreferredSize(new Dimension(150, 150));
        banh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        banh.setBackground(Color.decode("#93d9ff"));
        banh.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        
        JLabel idbanh = ThongTin(b.getId(), 150, 14, 12, "#333333");
        JLabel tenBanh = ThongTin(b.getTen(), 150, 38, 20, "#ffffff");
        JLabel soLuong = ThongTin("Định lượng: " + b.getDinhLuong(), 150, 20, 14, "#333333");
        JLabel giaBan = ThongTin(us.toVND(b.getGiaBan()), 150, 20, 14, "#333333");
        JButton btnXoa = XoaButton("Xóa",banh,tenBanh, 150, 25);
        ActionXoaSP(btnXoa, b.getId());
        SPMoved_Exited(btnXoa, banh, tenBanh);
        
        banh.add(idbanh);
        banh.add(tenBanh);
        banh.add(giaBan);
        banh.add(soLuong);
        banh.add(btnXoa);
        
        banh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BanhExited(tenBanh, banh);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ID = b.getId();
                tenUpdate.setText(b.getTen());
                dinhLuongUpdate.setText(String.valueOf(b.getDinhLuong()));
                giaBanUpdate.setText(String.valueOf(b.getGiaBan()));
                
                String idDM;
                for(int i = 0; i < danhMucSP.getItemCount(); i++) {
                    idDM = danhMucSP.getItemAt(i).getId();
                    if(b.getDMNo().equals(idDM)) {
                        danhMucSP.setSelectedItem(danhMucSP.getItemAt(i));
                        break;
                    }
                }
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
        lable.setForeground(Color.decode("#ffffff"));
        panel.setBackground(Color.decode("#93d9ff"));
    }
    
    public void BanhMoved(Component lable, JPanel panel)  {
        lable.setForeground(Color.white);
        panel.setBackground(Color.decode("#3399FF"));
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

        jPanel1 = new javax.swing.JPanel();
        navBan = new javax.swing.JPanel();
        btnBan = new javax.swing.JLabel();
        navTrangChu = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tenUpdate = new javax.swing.JTextField();
        giaBanUpdate = new javax.swing.JTextField();
        dinhLuongUpdate = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        danhMucSP = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        danhMuc = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtDMUpdate = new javax.swing.JTextField();
        btnThemDanhMuc4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        navBan.setBackground(new java.awt.Color(102, 204, 255));
        navBan.setPreferredSize(new java.awt.Dimension(100, 40));
        navBan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                navBanMouseMoved(evt);
            }
        });
        navBan.setLayout(new java.awt.GridLayout(1, 0));

        btnBan.setBackground(new java.awt.Color(102, 204, 255));
        btnBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBan.setForeground(new java.awt.Color(255, 255, 255));
        btnBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBan.setText("Quản lí danh mục - sản phẩm");
        btnBan.setMinimumSize(new java.awt.Dimension(20, 40));
        btnBan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnBanMouseMoved(evt);
            }
        });
        btnBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanMouseExited(evt);
            }
        });
        navBan.add(btnBan);

        navTrangChu.setBackground(new java.awt.Color(102, 204, 255));
        navTrangChu.setPreferredSize(new java.awt.Dimension(100, 40));
        navTrangChu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                navTrangChuMouseMoved(evt);
            }
        });
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(navTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addGap(3, 3, 3)
                .addComponent(navBan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(663, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navBan, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 249, 243));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Tên sản phẩm");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Giá bán");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Định lượng");

        tenUpdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tenUpdate.setForeground(new java.awt.Color(153, 153, 153));

        giaBanUpdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        giaBanUpdate.setForeground(new java.awt.Color(153, 153, 153));

        dinhLuongUpdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dinhLuongUpdate.setForeground(new java.awt.Color(153, 153, 153));

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setBorder(null);
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuabtnSuaActionPerformed(evt);
            }
        });

        btnThemSP.setBackground(new java.awt.Color(0, 153, 153));
        btnThemSP.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSP.setText("Thêm");
        btnThemSP.setBorder(null);
        btnThemSP.setBorderPainted(false);
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPbtnThemSPActionPerformed(evt);
            }
        });

        danhMucSP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        danhMucSP.setForeground(new java.awt.Color(153, 153, 153));
        danhMucSP.setMaximumRowCount(100);
        danhMucSP.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Danh mục");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tenUpdate, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(giaBanUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10)
                                .addComponent(dinhLuongUpdate)
                                .addComponent(danhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(189, 189, 189))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(danhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giaBanUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dinhLuongUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(null);

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setMaximumSize(new java.awt.Dimension(650, 400));
        menu.setMinimumSize(new java.awt.Dimension(650, 400));
        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(menu);

        jScrollPane2.setBorder(null);

        danhMuc.setBackground(new java.awt.Color(0, 204, 204));
        danhMuc.setAutoscrolls(true);
        danhMuc.setLayout(new javax.swing.BoxLayout(danhMuc, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane2.setViewportView(danhMuc);

        jPanel9.setBackground(new java.awt.Color(0, 204, 204));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tên danh mục");

        txtDMUpdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDMUpdate.setForeground(new java.awt.Color(153, 153, 153));
        txtDMUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDMUpdateKeyPressed(evt);
            }
        });

        btnThemDanhMuc4.setBackground(new java.awt.Color(255, 255, 255));
        btnThemDanhMuc4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThemDanhMuc4.setForeground(new java.awt.Color(0, 204, 204));
        btnThemDanhMuc4.setText("Thêm");
        btnThemDanhMuc4.setBorder(null);
        btnThemDanhMuc4.setBorderPainted(false);
        btnThemDanhMuc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDanhMucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDMUpdate)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnThemDanhMuc4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDMUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemDanhMuc4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanMouseMoved
        btnBan.setForeground(Color.decode("#66CCFF"));
        navBan.setBackground(Color.white);
    }//GEN-LAST:event_btnBanMouseMoved

    private void btnBanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanMouseExited
        btnBan.setForeground(Color.white);
        navBan.setBackground(Color.decode("#66CCFF"));
    }//GEN-LAST:event_btnBanMouseExited

    private void navBanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navBanMouseMoved

    }//GEN-LAST:event_navBanMouseMoved

    private void btnTrangChuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseMoved
        btnTrangChu.setForeground(Color.decode("#66CCFF"));
        navTrangChu.setBackground(Color.white);
    }//GEN-LAST:event_btnTrangChuMouseMoved

    private void btnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseExited
        btnTrangChu.setForeground(Color.white);
        navTrangChu.setBackground(Color.decode("#66CCFF"));
    }//GEN-LAST:event_btnTrangChuMouseExited

    private void navTrangChuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_navTrangChuMouseMoved

    }//GEN-LAST:event_navTrangChuMouseMoved

    private void btnSuabtnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuabtnSuaActionPerformed
        if(tenUpdate.getText().trim().isEmpty() || dinhLuongUpdate.getText().isEmpty() || giaBanUpdate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(ID == "") {
            JOptionPane.showMessageDialog(this, "Hãy Chọn một sản phẩm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Double gia = Double.parseDouble(giaBanUpdate.getText());
            if(gia <= 0) {
                JOptionPane.showMessageDialog(this, "Hãy nhập giá bán > 0!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            DanhMuc dm = (DanhMuc) danhMucSP.getSelectedItem();
            if(us.UpdateSPByID(ID, tenUpdate.getText(), dinhLuongUpdate.getText(), gia, dm.getId()))
            JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            else
            JOptionPane.showMessageDialog(this, "Chỉnh sửa không thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            getAllSPByDMNo(dm.getId());
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số cho mục giá bán!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSuabtnSuaActionPerformed

    private void btnThemSPbtnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPbtnThemSPActionPerformed
        ID = "";
        
        if(tenUpdate.getText().trim().isEmpty() || dinhLuongUpdate.getText().isEmpty() || giaBanUpdate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Double gia = Double.parseDouble(giaBanUpdate.getText());
            
            if(gia <= 0) {
                JOptionPane.showMessageDialog(this, "Hãy nhập giá bán > 0!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            DanhMuc dm = (DanhMuc) danhMucSP.getSelectedItem();
            if(us.InsertSanPham(tenUpdate.getText(), dinhLuongUpdate.getText(), gia, dm.getId()))
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Thêm không thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            getAllSPByDMNo(dm.getId());
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số cho mục giá bán!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemSPbtnThemSPActionPerformed

    private void btnThemDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDanhMucActionPerformed
        String tenDM = txtDMUpdate.getText().trim();

        if(tenDM.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(us.InsertDanhMuc(tenDM)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            getAllDMForCombobox();
            getAllDanhMuc();
        }else
        JOptionPane.showMessageDialog(this, "Thêm không thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThemDanhMucActionPerformed

    private void txtDMUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDMUpdateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDMUpdateKeyPressed

    private void btnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseClicked
        TrangChu tc = new TrangChu();
        tc.setVisible(true);
        tc.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QLSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QLSP().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBan;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemDanhMuc4;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JPanel danhMuc;
    private javax.swing.JComboBox<DanhMuc> danhMucSP;
    private javax.swing.JTextField dinhLuongUpdate;
    private javax.swing.JTextField giaBanUpdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel navBan;
    private javax.swing.JPanel navTrangChu;
    private javax.swing.JTextField tenUpdate;
    private javax.swing.JTextField txtDMUpdate;
    // End of variables declaration//GEN-END:variables
}
