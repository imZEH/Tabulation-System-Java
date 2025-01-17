/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Graphical_User_Interface.Administrator_Module;

import Tabulation.Connections.Center_Frame;
import Tabulation.Interface.Implementation.Update_Cat;
import Tabulation.Interface.Update_Event_Cat;
import Tabulation.Methods.Insert_Commands.Add_Cultural_Event;
import Tabulation.Methods.Select_Commands.Query_Cultural_ID;
import Tabulation.Methods.Select_Commands.query_Date;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Event_Category;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class sub_category extends javax.swing.JDialog {

    /**
     * Creates new form sub_category
     */
    public sub_category(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ID1.hide();
        ID2.hide();
        this.setLocationRelativeTo(null);
        getReference2();
    }

    
    private void getReference2(){
        try{
        List<Cultural_Event> sList= new ArrayList<>();
        //DefaultTableModel model2=(DefaultTableModel)sub_reference.getModel();
        
        query_Date p =new query_Date();
     //   model2.setRowCount(0);
        sList= p.query_reference2();
        for (int i=0;i<sList.size();i++){
            
      //      model2.addRow(new Object[]{});
            
            String name = sList.get(i).getCul_Name();
            wew.addItem(name);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
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
        jPanel12 = new javax.swing.JPanel();
        subpercent = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        subname = new javax.swing.JTextField();
        save1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        clear1 = new javax.swing.JButton();
        close1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        wew = new javax.swing.JComboBox();
        ID1 = new javax.swing.JLabel();
        ID2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        judgeType = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        subpercent.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        subpercent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100" }));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel17.setText("Percentage:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setText("Sub Event Name:");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setText("%");

        subname.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/save.png"))); // NOI18N
        save1.setText("Save");
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(102, 102, 255));
        jLabel25.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Subl Event Info");
        jLabel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel25.setOpaque(true);

        clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/clear.png"))); // NOI18N
        clear1.setText("Clear");
        clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear1ActionPerformed(evt);
            }
        });

        close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/close.png"))); // NOI18N
        close1.setText("Close");
        close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jButton2.setText("Add Criteria");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel20.setText("Reference Name:");

        wew.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        wew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--Event--:" }));
        wew.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wewItemStateChanged(evt);
            }
        });

        ID1.setText("0");

        ID2.setText("0");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel21.setText("Judge Type:");

        judgeType.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        judgeType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OFFLINE", "ONLINE" }));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(subpercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(subname)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wew, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(38, 38, 38)
                                .addComponent(judgeType, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(save1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clear1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(close1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(wew)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(subname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(subpercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(judgeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save1)
                    .addComponent(clear1)
                    .addComponent(close1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        // TODO add your handling code here:
        try {
            Query_Cultural_ID QCI = new Query_Cultural_ID();
            String a = QCI.getIDfor_SubEvent((String) wew.getSelectedItem());
            //  DefaultTableModel model=(DefaultTableModel)sub_reference.getModel();
            //  int row = sub_reference.getSelectedRow();
            //  String TableClick = (sub_reference.getModel().getValueAt(row, 0).toString());

            if (wew.getSelectedItem().equals("--Select--Event--:") || subname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Check all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (save1.getText()) {
                    case "Save":
                        Add_Cultural_Event ae = new Add_Cultural_Event();
                        Event_Category EC = new Event_Category(Integer.parseInt(a), subname.getText(), (String) subpercent.getSelectedItem(), (String) judgeType.getSelectedItem());
                        ae.AddCategory(EC);
                        break;
                    case "Update":
                        Update_Event_Cat UEC = new Update_Cat();
                        UEC.Update_cat_evn(Integer.parseInt(ID1.getText()), subname.getText(), Integer.parseInt(ID2.getText()), (String) subpercent.getSelectedItem(), (String) judgeType.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Successfully Updated");
                        dispose();
                        break;
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_save1ActionPerformed

    private void clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear1ActionPerformed
        // TODO add your handling code here:
        subname.setText("");
        subpercent.setSelectedItem("5");
        //    sub_reference.getSelectionModel().clearSelection();
    }//GEN-LAST:event_clear1ActionPerformed

    private void close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close1ActionPerformed
        dispose();
    }//GEN-LAST:event_close1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        new Criteria_Window().show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void wewItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wewItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_wewItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sub_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sub_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sub_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sub_category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                sub_category dialog = new sub_category(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel ID1;
    public static javax.swing.JLabel ID2;
    private javax.swing.JButton clear1;
    private javax.swing.JButton close1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    public static javax.swing.JComboBox judgeType;
    public static javax.swing.JButton save1;
    public static javax.swing.JTextField subname;
    public static javax.swing.JComboBox subpercent;
    public static javax.swing.JComboBox wew;
    // End of variables declaration//GEN-END:variables
}
