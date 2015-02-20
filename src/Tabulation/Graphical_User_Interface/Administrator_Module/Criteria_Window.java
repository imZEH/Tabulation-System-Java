/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Graphical_User_Interface.Administrator_Module;

import Tabulation.Connections.Center_Frame;
import Tabulation.Interface.Implementation.Update_CriteriaImplement1;
import Tabulation.Interface.Update_Criteria;
import Tabulation.Methods.Insert_Commands.Add_Cultural_Event;
import Tabulation.Methods.Select_Commands.Duplicate;
import Tabulation.Methods.Select_Commands.Query_Criteria_Details;
import Tabulation.Methods.Select_Commands.View_Criter;
import Tabulation.Methods.Select_Commands.query_Date;
import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Cultural_Event;
import com.mysql.jdbc.CallableStatement;
import groovy.model.DefaultTableModel;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Criteria_Window extends javax.swing.JDialog {
    private CallableStatement callableStatement = null;
    private ResultSet rs = null;
    /**
     * Creates new form Criteria_Window
     */
    public Criteria_Window() {
        initComponents();
        getReference();
        ID.hide();
        ID1.hide();
        crid.hide();
        //new Center_Frame().Center(this);
        this.setLocationRelativeTo(null);
    }
    
    private void getReference() {
        try{
        List<Cultural_Event> sList= new ArrayList<>();
     //   javax.swing.table.DefaultTableModel model=(javax.swing.table.DefaultTableModel)Criteria_References.getModel();
        query_Date p =new query_Date();
        
        sList= p.query_reference();
        for (int i=0;i<sList.size();i++){
           String eventname =  sList.get(i).getCul_Name();
           event.addItem(eventname);
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void getdatae(){
        
        try{
        javax.swing.table.DefaultTableModel model=(javax.swing.table.DefaultTableModel)jTable1.getModel();
        if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        // int row = Criteria_References.getSelectedRow();
       // String TableClick = (Criteria_References.getModel().getValueAt(row, 0).toString());
       // String TableClick1 = (Criteria_References.getModel().getValueAt(row, 1).toString());
        List<Criteria_Cultural> WList1 = new ArrayList<>();
        List<Criteria_Cultural> WList2 = new ArrayList<>();
        List<Criteria_Cultural> CList = new ArrayList<>();
        List<Criteria_Cultural> CList2 = new ArrayList<>();
        
        Query_Criteria_Details QCD = new Query_Criteria_Details();
       String b = QCD.getEvent_Cultural((String)event.getSelectedItem());
       ID.setText(b);
       String c = QCD.getEvent_Category((String)event.getSelectedItem());
        WList1 = QCD.CriteriaDetails_For_Cultural_Event(b);
        WList2 = QCD.CriteriaDetails_For_Cultural_Event1(b);
        
        CList = QCD.CriteriaCategory(c);
        CList2 = QCD.CriteriaCategory1(c);
       
        
        
        char Char = b.charAt(0);
        String string = Character.toString(Char);
            switch (string) {
                case "4":
                    jLabel1.setVisible(true);
                    jLabel2.setVisible(false);
                    ID.setText(b);
                    ID1.setText("0");
                    break;
                case "1":
                    jLabel2.setVisible(true);
                    jLabel1.setVisible(false);
                    ID1.setText(c);
                    ID.setText("0");
                    break;
            }
        char Char1 = c.charAt(0);
        String string1 = Character.toString(Char1);
            switch (string1) {
                case "4":
                    jLabel1.setVisible(true);
                    jLabel2.setVisible(false);
                    ID.setText(b);
                    ID1.setText("0");
                    break;
                case "1":
                    jLabel2.setVisible(true);
                    jLabel1.setVisible(false);
                    ID1.setText(c);
                    ID.setText("0");
                    break;
            }
            
        for(int j=0;j<WList1.size();j++){
                model.addRow(new Object[]{});
                jTable1.setValueAt(WList1.get(j).getCategory_Name(), j, 0);
                jTable1.setValueAt(WList1.get(j).getCr_Description(), j, 1);
                jTable1.setValueAt(WList1.get(j).getCr_Percentage(), j, 2);
                
            }
        
        for(int j=0;j<CList.size();j++){
                model.addRow(new Object[]{});
                jTable1.setValueAt(CList.get(j).getCategory_Name(), j, 0);
                jTable1.setValueAt(CList.get(j).getCr_Description(), j, 1);
                jTable1.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
                
            }
        
        for(int j=0;j<WList2.size();j++){
                int a = WList2.get(j).getTotal();
                jLabel1.setText(Integer.toString(a));
            }
        for(int j=0;j<CList2.size();j++){
                double a = CList2.get(j).getValue();
                jLabel2.setText(Double.toString(a));
               
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
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        crName = new javax.swing.JTextField();
        crPercent = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        crsave = new javax.swing.JButton();
        crclear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        event = new javax.swing.JComboBox();
        ID = new javax.swing.JLabel();
        ID1 = new javax.swing.JLabel();
        crid = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criteria Window");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("tablogo.gif")));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel13.setBackground(new java.awt.Color(102, 102, 255));
        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Criteria");
        jLabel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel13.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Criteria");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setText("Percentage:");

        crName.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        crPercent.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        crPercent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100" }));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel14.setText("%");

        crsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/save.png"))); // NOI18N
        crsave.setText("Save");
        crsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crsaveActionPerformed(evt);
            }
        });

        crclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/clear.png"))); // NOI18N
        crclear.setText("Clear");
        crclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crclearActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/close.png"))); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Reference Name:");

        event.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        event.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--Event--:" }));
        event.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                eventItemStateChanged(evt);
            }
        });

        ID.setText("0");

        ID1.setText("0");

        crid.setText("jLabel3");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 161, Short.MAX_VALUE)
                        .addComponent(crsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crclear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(crPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ID1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(crid, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(crName)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(event, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(crName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(crPercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ID)
                        .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(crid)))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crsave)
                    .addComponent(crclear)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel12.setBackground(new java.awt.Color(102, 102, 255));
        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("History Criteria");
        jLabel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel12.setOpaque(true);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel15.setText("Total Percentage");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 4, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel16.setText("%");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 3, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel9.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 3, 12, 16));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 3, 35, 16));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 3, 35, 16));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Criteria", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFillsViewportHeight(true);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crsaveActionPerformed
        // TODO add your handling code here:
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        View_Criter VC = new View_Criter();
        try{
       Query_Criteria_Details QCD = new Query_Criteria_Details();
       String b = QCD.getEvent_Cultural((String)event.getSelectedItem());
       String c = QCD.getEvent_Category((String)event.getSelectedItem());
       Update_Criteria UC = new Update_CriteriaImplement1();
       
       
        char Char = ID.getText().charAt(0);
        String string = Character.toString(Char);
        Add_Cultural_Event ae = new Add_Cultural_Event();
        CList = VC.view(b);
        
        if(event.getSelectedItem().equals("--Select--Event--:") || crName.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Check all fields","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
       
            switch (crsave.getText()) {
                case "Save":
                    if(!ID.getText().equals("0")){
                            Criteria_Cultural CC = new Criteria_Cultural(b,null,crName.getText(),Double.parseDouble((String)crPercent.getSelectedItem()));
                            ae.AddCriteria_Cultural(CC);
                    }
                    else if(!ID1.getText().equals("0")){
                       
                            Criteria_Cultural C_C = new Criteria_Cultural(null,c,crName.getText(),Double.parseDouble((String)crPercent.getSelectedItem()));
                            ae.AddCriteria_Category(C_C);
                            
                    }
                    break;
                case "Update":
                    if(!ID.getText().equals("0")){
                        UC.UpdateCriteria_Cultural(Integer.parseInt(crid.getText()), crName.getText()
                                , Double.parseDouble((String)crPercent.getSelectedItem()), null, b);
                        JOptionPane.showMessageDialog(null,"Successfully Saved");
                        dispose();
                    }
                    else if(!ID1.getText().equals("0")){
                      UC.UpdateCriteria1_Category(Integer.parseInt(crid.getText()), crName.getText()
                              , Double.parseDouble((String)crPercent.getSelectedItem()), c, null);
                      JOptionPane.showMessageDialog(null,"Successfully Saved");
                      dispose();
                    }
                    break;
            }
        }
        
        getdatae();
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    }//GEN-LAST:event_crsaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void crclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crclearActionPerformed
        // TODO add your handling code here:
        
      //  Criteria_References.getSelectionModel().clearSelection();
        event.setSelectedItem("--Select--Event--:");
        crName.setText("");crPercent.setSelectedItem("5");
        
    }//GEN-LAST:event_crclearActionPerformed

    private void eventItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_eventItemStateChanged
        
            // TODO add your handling code here:
            getdatae(); 
      
   }//GEN-LAST:event_eventItemStateChanged

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
            java.util.logging.Logger.getLogger(Criteria_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Criteria_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Criteria_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Criteria_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                new Criteria_Window().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel ID;
    public static javax.swing.JLabel ID1;
    public static javax.swing.JTextField crName;
    public static javax.swing.JComboBox crPercent;
    private javax.swing.JButton crclear;
    public static javax.swing.JLabel crid;
    public static javax.swing.JButton crsave;
    public static javax.swing.JComboBox event;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
