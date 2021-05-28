/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Services.QLVoucherrService;
import Models.Voucher;
import Services.kiemTraSo;
import java.awt.Color;
import javax.swing.JTextField;


/**
 *
 * @author acer
 */
public class QLVoucher extends javax.swing.JFrame {
QLVoucherrService QLservice ;
    Voucher VC;

    DefaultTableModel defaultTableModel;
    /**
     * Creates new form QLVoucher
     */
    public QLVoucher() {
             QLservice = new QLVoucherrService();
        initComponents();
        defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        tgbdVC.getDate();
        tgktVC.getDate();
       tblvc.setModel(defaultTableModel);
        defaultTableModel.addColumn("ID Voucher");
        defaultTableModel.addColumn("Tên Voucher");
        defaultTableModel.addColumn("Phần Trăm");
        defaultTableModel.addColumn("Số Lượng");
        defaultTableModel.addColumn("Ngày Bắt Đầu");
        defaultTableModel.addColumn("Ngày Kết Thúc");
        List<Voucher> VCC =QLservice.getALLVC();
        for (Voucher item : VCC){
            defaultTableModel.addRow(new Object[]{item.getIdVoucher(),item.getTenVoucher(),item.getPhanTram()+"%",
                item.getSoLuong(),item.getNgayBatDau(),item.getNgayKetThuc()});
        }
   
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tenVC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        soluongVC = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        phantramVC = new javax.swing.JTextField();
        tgbdVC = new com.toedter.calendar.JDateChooser();
        tgktVC = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        luu = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        them = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblvc = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        navTrangChu = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        btnBan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Tên VouCher");

        tenVC.setEditable(false);

        jLabel16.setText("Số Lượng");

        soluongVC.setEditable(false);

        jLabel17.setText("Thời Gian Kết Thúc");

        jLabel18.setText("Phần Trăm");

        jLabel20.setText("Thời Gian Bắt Đầu");

        phantramVC.setEditable(false);

        tgbdVC.setDateFormatString("d/MM/y");

        tgktVC.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phantramVC, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(soluongVC))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel17))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgktVC, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(tgbdVC, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(tenVC))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(soluongVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel20))
                    .addComponent(tgbdVC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgktVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(phantramVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        luu.setText("Lưu");
        luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuActionPerformed(evt);
            }
        });

        xoa.setText("Xóa");
        xoa.setEnabled(false);
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        sua.setText("Sửa");
        sua.setMaximumSize(new java.awt.Dimension(61, 23));
        sua.setMinimumSize(new java.awt.Dimension(61, 23));
        sua.setPreferredSize(new java.awt.Dimension(61, 23));
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(luu, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblvc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Voucher", "Số Lượng", "Phần Trăm", "Ngày Bắt Đầu", "Ngày Kết Thúc"
            }
        ));
        tblvc.setToolTipText("");
        tblvc.setName(""); // NOI18N
        tblvc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblvcMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblvc);
        tblvc.getAccessibleContext().setAccessibleName("");

        jLabel1.setText("Thông Tin Chi Tiết");

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
        btnBan.setText("Quản lí voucher");
        btnBan.setMinimumSize(new java.awt.Dimension(20, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(navTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addGap(5, 5, 5)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
        tenVC.setEditable(true);
        phantramVC.setEditable(true);
        soluongVC.setEditable(true);
        tenVC.setText("");
        phantramVC.setText("");
        soluongVC.setText("");
          
        sua.setEnabled(false);
   
    }//GEN-LAST:event_themActionPerformed

    private void tblvcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblvcMouseClicked
        // TODO add your handling code here:
          int row=tblvc.getSelectedRow();
        String idD =String.valueOf(tblvc.getValueAt(row,0));
        VC=QLservice.getVCbyID(idD);
        tenVC.setText(VC.getTenVoucher());
        phantramVC.setText(String.valueOf(VC.getPhanTram()));
        soluongVC.setText(String.valueOf(VC.getSoLuong()));
        tgbdVC.setDate(VC.getNgayBatDau());
        tgktVC.setDate(VC.getNgayKetThuc());

 
    }//GEN-LAST:event_tblvcMouseClicked

    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        // TODO add your handling code here:
               tenVC.setEditable(true);
        phantramVC.setEditable(true);
        soluongVC.setEditable(true);
        them.setEnabled(false);
      
    }//GEN-LAST:event_suaActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
           int row = tblvc.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane,"Vui Lòng Chọn Danh Mục Trước","Lỗi",JOptionPane.ERROR_MESSAGE);}
        else
        {
            int Confirm =JOptionPane.showConfirmDialog(rootPane,"Bạn Chắc Chắn Muốn Xóa Không");
            if(Confirm==JOptionPane.YES_OPTION)
            {

                String idVC =String.valueOf(tblvc.getValueAt(row,0));
                QLservice.deleteVC(idVC);
                defaultTableModel.setRowCount(0);
                List<Voucher> KHH =QLservice.getALLVC();
                for (Voucher item : KHH){
                    defaultTableModel.addRow(new Object[]{item.getIdVoucher(),item.getTenVoucher(),item.getPhanTram()+"%",
                item.getSoLuong(),item.getNgayBatDau(),item.getNgayKetThuc()});
            }
        }
        }
    }//GEN-LAST:event_xoaActionPerformed

    private void luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuActionPerformed
        // TODO add your handling code here:
        String nbd = ((JTextField)tgbdVC.getDateEditor().getUiComponent()).getText();
        String nkt = ((JTextField)tgktVC.getDateEditor().getUiComponent()).getText();
        if("".equals(tenVC.getText())||"".equals(phantramVC.getText())||"".equals(soluongVC.getText())||"".equals(nbd)||"".equals(nkt))
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập đủ thông tin!");
            return;
        }
        if(!kiemTraSo.isNumeric(phantramVC.getText())||!kiemTraSo.isNumeric(soluongVC.getText()))
        {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập số !");
            return;
        }
          java.util.Date tbd= tgbdVC.getDate();
      java.util.Date tkt =  tgktVC.getDate();
      java.sql.Date ttbd=new java.sql.Date (tbd.getTime());
      java.sql.Date ttkt=new java.sql.Date (tkt.getTime());
     
      if(ttbd.compareTo(ttkt)==1)
      {
            JOptionPane.showMessageDialog(rootPane,"Vui Lòng Chọn ngày bắt đầu nhỏ hơn ngày kết thúc","Lỗi",JOptionPane.ERROR_MESSAGE);
      }
        
      else
      {
         if(them.isEnabled()==true) 
            {  
              VC = new Voucher();
              VC.setIdVoucher(QLservice.NewIDVC());
              VC.setTenVoucher(tenVC.getText());
              VC.setPhanTram(Integer.parseInt(phantramVC.getText()));
              VC.setSoLuong(Integer.parseInt(soluongVC.getText()));
              VC.setNgayBatDau(ttbd);
              VC.setNgayKetThuc(ttkt);
              QLservice.insertVC(VC);
              tenVC.setText("");
              phantramVC.setText("");
              soluongVC.setText("");
             }
       if(sua.isEnabled()==true)
       {
         VC.setTenVoucher(tenVC.getText());
         VC.setPhanTram(Integer.valueOf(phantramVC.getText()));
         VC.setSoLuong(Integer.valueOf(soluongVC.getText()));
         VC.setNgayBatDau(ttbd);
         VC.setNgayKetThuc(ttkt);
        QLservice.updateVC(VC);
       }
        defaultTableModel.setRowCount(0);
            List<Voucher> KHH =QLservice.getALLVC();
                for (Voucher item : KHH){
                    defaultTableModel.addRow(new Object[]{item.getIdVoucher(),item.getTenVoucher(),item.getPhanTram()+"%",
                item.getSoLuong(),item.getNgayBatDau(),item.getNgayKetThuc()});
     
        tenVC.setEditable(false);
        phantramVC.setEditable(false);
        soluongVC.setEditable(false);
      
        sua.setEnabled(true);
        them.setEnabled(true);
                }
      }
    }//GEN-LAST:event_luuActionPerformed

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
//            java.util.logging.Logger.getLogger(QLVoucherrService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QLVoucherrService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QLVoucherrService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QLVoucherrService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new QLVoucher().setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBan;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton luu;
    private javax.swing.JPanel navTrangChu;
    private javax.swing.JTextField phantramVC;
    private javax.swing.JTextField soluongVC;
    private javax.swing.JButton sua;
    private javax.swing.JTable tblvc;
    private javax.swing.JTextField tenVC;
    private com.toedter.calendar.JDateChooser tgbdVC;
    private com.toedter.calendar.JDateChooser tgktVC;
    private javax.swing.JButton them;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
