/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Views;

import Models.*;
import Services.UserServices;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author thien
 */
public class BanAn_MangVe extends javax.swing.JFrame {
    List<Ban> listBan;
    UserServices us;
    int mv = 1;
    int tb = 1;
    
    public BanAn_MangVe() {
        initComponents();
        us = new UserServices();
        getAllBan();
        getAllMangVe();
    }
    
    public JPanel NewLinePanel(int w, int h, int wgap, int hgap, String bColor) {
        JPanel lineJPanel = new JPanel();
        lineJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, wgap, hgap));
        lineJPanel.setBackground(Color.decode(bColor));
        lineJPanel.setPreferredSize( new Dimension(w, h));
        return lineJPanel;
    }
    
    public void getAllMangVe() {
        int count = 0;
        int spt = 0;
        
        pMangVe.removeAll();
        listBan = us.getAllBan();
        JPanel lineJPanel = NewLinePanel(812, 150, 8, 8, "#FFFFFF");
        pMangVe.setVisible(false);
        for(Ban b : listBan) {
            String loai = b.getIdBan().substring(0, 1); 
            if(loai.equals("M")) {
                spt++;
                JPanel ban = TaoBan(b, "Mang về");
                if(lineJPanel.getComponentCount() == 5)
                     lineJPanel = NewLinePanel(812, 150, 8, 8, "#FFFFFF");
                lineJPanel.add(ban);
                pMangVe.add(lineJPanel);
                
                if(!b.getIsOrdered())
                count++;
            }
        }
        pMangVe.setVisible(true);
        trongMV.setText("Trống: " + count + "/" + spt);
    }
    
    public void getAllBan() {
        int count = 0;
        int spt = 0;
        
        banAn.removeAll();
        listBan = us.getAllBan();
        JPanel lineJPanel = NewLinePanel(812, 150, 8, 8, "#FFFFFF");
        banAn.setVisible(false);
        for(Ban b : listBan) {
            String loai = b.getIdBan().substring(0, 1);
            
            if(loai.equals("B")) {
                spt++;
                JPanel ban = TaoBan(b, "Bàn");

                if(lineJPanel.getComponentCount() == 5)
                     lineJPanel = NewLinePanel(812, 150, 8, 8, "#FFFFFF");
                lineJPanel.add(ban);
                banAn.add(lineJPanel);
                
                if(!b.getIsOrdered())
                    count++;
            }
            
            
        }
        banAn.setVisible(true);
        
        trongBA.setText("Trống: " + count + "/" + spt);
        
        while(banAn.getComponentCount() < 3) {
            lineJPanel = NewLinePanel(812,150, 8 , 8, "#FFFFFF");
            banAn.add(lineJPanel);
        }
    }
    
    public JPanel TaoBan(Ban b, String loai) {
        JPanel ban = new JPanel();
        String bColor = "#93d9ff";
        if(b.getIsOrdered())
            bColor = "#FF9933";
        
        final String c = bColor;
        ban.setPreferredSize(new Dimension(150, 150));
        ban.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ban.setBackground(Color.decode(bColor));
        ban.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        
        String idBan = b.getIdBan();
        JLabel tenBan = ThongTin(loai + " " + (loai.contains("Bàn") ? tb++ : mv++), 150, 50, 20, "#FFFFFF");
        JLabel isOrder = ThongTin((b.getIsOrdered() == false ? "Trống" : "Có khách"), 150, 20, 14, "#333333");
        
        
        ban.add(tenBan);
        ban.add(isOrder);
        
        ban.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BanExited(ban, c);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Clicked(b.getIdBan());
            }
        });
        ban.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) { 
                BanMoved(ban);
            }
        });
        return ban;
    }
    
    public void Clicked(String idBan) {
        new GoiMon(idBan).setVisible(true);
        this.dispose();
    }
    
    public void BanExited(JPanel panel, String bColor)  {
        panel.setBackground(Color.decode(bColor));
    }
    
    public void BanMoved( JPanel panel)  {
        panel.setBackground(Color.decode("#85b3ef"));
    }
    
    public JLabel ThongTin(String s, int width, int height, int fontSize, String fColor) {
        JLabel thongTin = new JLabel(s,SwingConstants.CENTER);
        thongTin.setPreferredSize(new Dimension(width, height));
        thongTin.setFont(new Font("Arial", Font.BOLD, fontSize));
        thongTin.setForeground(Color.decode(fColor));
        return thongTin;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
        jLabel2 = new javax.swing.JLabel();
        trongMV = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        trongBA = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        banAn = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pMangVe = new javax.swing.JPanel();

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
        btnBan.setText("Bàn");
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
                .addComponent(navBan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(659, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navBan, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(navTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mang về");

        trongMV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        trongMV.setForeground(new java.awt.Color(255, 255, 255));
        trongMV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trongMV.setText("Trống: 5/5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trongMV)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addComponent(trongMV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bàn ăn");

        trongBA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        trongBA.setForeground(new java.awt.Color(255, 255, 255));
        trongBA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trongBA.setText("Trống: 10/10");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(trongBA)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addComponent(trongBA, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        banAn.setBackground(new java.awt.Color(255, 255, 255));
        banAn.setLayout(new javax.swing.BoxLayout(banAn, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(banAn);

        jScrollPane2.setBorder(null);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pMangVe.setBackground(new java.awt.Color(255, 255, 255));
        pMangVe.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 8));
        jScrollPane2.setViewportView(pMangVe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(830, 714));
        setLocationRelativeTo(null);
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
//            java.util.logging.Logger.getLogger(BanAn_MangVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BanAn_MangVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BanAn_MangVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BanAn_MangVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BanAn_MangVe().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banAn;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBan;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel navBan;
    private javax.swing.JPanel navTrangChu;
    private javax.swing.JPanel pMangVe;
    private javax.swing.JLabel trongBA;
    private javax.swing.JLabel trongMV;
    // End of variables declaration//GEN-END:variables

}
