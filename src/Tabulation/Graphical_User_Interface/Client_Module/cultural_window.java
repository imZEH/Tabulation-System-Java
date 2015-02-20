/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Graphical_User_Interface.Client_Module;

import Tabulation.Additional_Implements.Insert2_gen_ranks;
import Tabulation.Additional_getters.Generated_ranks;
import Tabulation.Additional_getters.View_Cul_Ranks;
import Tabulation.Additonal_Interface.add_generated_ranks;
import Tabulation.Additonal_methods.Generate_ranks;
import Tabulation.Additonal_methods.Select_Ranks;
import Tabulation.Graphical_User_Interface.Administrator_Module.login;
import Tabulation.Interface.Implementation._AddCriteriaScoreImplementation;
import Tabulation.Interface.Implementation._inserttemp_implement;
import Tabulation.Interface._AddCriteriaScore;
import Tabulation.Interface._inserttemp;
import Tabulation.Methods.Select_Commands.*;
import Tabulation.getters_setters.*;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neil
 */
public class cultural_window extends javax.swing.JFrame implements ActionListener{
Timer clockTimer;
    /**
     * Creates new form cultural_window
     */
    public cultural_window() {
        initComponents();
        date.setText(getDate());
            clockTimer = new Timer(1000,this );
        clockTimer.start();
        psink.hide();
        csink.hide();
        catsink.hide();
        this.setLocationRelativeTo(null);
    }
     public String getDate()
   {
      DateFormat dateFormat = new SimpleDateFormat( "MMMMMMMMM d, yyyy" );
      Calendar calendar = Calendar.getInstance();
      return dateFormat.format( calendar.getTime() );
   }
     
     public void _getData_Cultural(){
         CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card3");
        DefaultTableModel model=(DefaultTableModel)cultural.getModel();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._query_Culturalevents();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            cultural.setValueAt(sList.get(i).getCul_ID(), i, 0);
            cultural.setValueAt(sList.get(i).getCul_Name(), i, 1);
            cultural.setValueAt(sList.get(i).getCul_Percentage(), i, 2);
            }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
     public void _getAllData(){
         DefaultTableModel model=(DefaultTableModel)category.getModel();
        DefaultTableModel model2=(DefaultTableModel)criteria.getModel();
        DefaultTableModel model1=(DefaultTableModel)cultural.getModel();
        DefaultTableModel model3=(DefaultTableModel)_participant.getModel();
        List<Event_Category> sList = new ArrayList<Event_Category>(); 
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        List<Participant> XList = new ArrayList<Participant>();
        Query_EventDetails_forSearch QEDFS =new Query_EventDetails_forSearch();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        _Select_Cultural_Events _SCE = new _Select_Cultural_Events();
        int row = cultural.getSelectedRow();
        total.setText("0.00");
        String TableClick = (cultural.getModel().getValueAt(row, 0).toString());
        csink.setText(TableClick);
        catsink.setText("");
        try {
            sList = QEDFS.CategoryDetailsforsearch(Integer.parseInt(TableClick));
            //CList = QCD.CriteriaDetailsforsearch(Integer.parseInt(TableClick));
            CList = QCD.CriteriaDetailsforsearch1(Integer.parseInt(TableClick));
            XList = _SCE._query_participants(TableClick);
                model.setRowCount(0);
                model2.setRowCount(0);
                model3.setRowCount(0);
            for (int i=0;i<sList.size();i++){
                model.addRow(new Object[]{});
               category.setValueAt(sList.get(i).getCategory_ID(), i, 0);
               category.setValueAt(sList.get(i).getCategory_Name(), i, 1);
               category.setValueAt(sList.get(i).getCategory_Per(), i, 2);
               
            }
            
            for(int j=0;j<CList.size();j++){
                
                model2.addRow(new Object[]{});
                criteria.setValueAt(CList.get(j).getCr_ID(), j, 0);
                criteria.setValueAt(CList.get(j).getCr_Description(), j, 1);
                criteria.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
                criteria.setValueAt(CList.get(j).getTable(),j, 3);
            }
            for (int k=0;k<XList.size();k++){
                model3.addRow(new Object[]{});
               _participant.setValueAt(XList.get(k).getPartNum(), k, 0);
               _participant.setValueAt(XList.get(k).getFullname(), k, 1);
               _participant.setValueAt(XList.get(k).getTName(), k, 2);
               _participant.setValueAt(XList.get(k).getId(), k, 3);
            }
           
            v_Ima.setText("No Image Available");
            
        parnum.setText("");
        name.setText("");
        v_Ima.setIcon(null);
        team.setText("");
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
     
     public double data(double a){
         return 0;
     }
     
     
     public void _displayCriteria(){
         DefaultTableModel model=(DefaultTableModel)criteria.getModel();
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = category.getSelectedRow();
        String TableClick = (category.getModel().getValueAt(row, 0).toString());
        model.setRowCount(0);
        total.setText("0.00");
        catsink.setText(TableClick);
        try{
        CList = QCD.CriteriaDetailsforsearch2(Integer.parseInt(TableClick));
        //EventD.getSelectionModel().clearSelection();
        for(int j=0;j<CList.size();j++){
            double a = 0.00;
                model.addRow(new Object[]{});
                criteria.setValueAt(CList.get(j).getCr_ID(), j, 0);
                criteria.setValueAt(CList.get(j).getCr_Description(), j, 1);
                criteria.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
                //criteria.setValueAt(CList.get(j).getTable(),j, 3);
            }
        
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
     }
     
     public void _getPic(){
         try{
        
        int row = _participant.getSelectedRow();
        String TableClick = (_participant.getModel().getValueAt(row, 3).toString());
        String TableClick0 = (_participant.getModel().getValueAt(row, 0).toString());
        String TableClick1 = (_participant.getModel().getValueAt(row, 1).toString());
        String TableClick2= (_participant.getModel().getValueAt(row, 2).toString());
        query_participant_details q = new query_participant_details();
        psink.setText(TableClick);
        if(TableClick0.equals("N/A")){
            q._query_TeamImage(TableClick2);
            v_Ima.setText("");
        parnum.setText(TableClick0);
        name.setText(TableClick1);
        team.setText(TableClick2);
        }
        else{
        q._query_PartImage(TableClick);
        v_Ima.setText("");
        parnum.setText(TableClick0);
        name.setText(TableClick1);
        team.setText(TableClick2);
        }
        }catch(Exception e){}
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        culturalname = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        cuturalid = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scorewindow = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        v_Ima = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        parnum = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        _participant = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        team = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cultural = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        category = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        criteria = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        psink = new javax.swing.JLabel();
        csink = new javax.swing.JLabel();
        catsink = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Judges Window");
        setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("tablogo.gif")));
        setResizable(false);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 255, 0));
        jLabel1.setFont(new java.awt.Font("Cooper Black", 1, 60)); // NOI18N
        jLabel1.setForeground(java.awt.Color.green);
        jLabel1.setText("Tap for Scoring");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/tablogo1.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel38.setText("User Login:");

        culturalname.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        culturalname.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        culturalname.setText("jLabel39");

        time.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel40.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel40.setText("Date:");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel42.setText("Time:");

        date.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cuturalid.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cuturalid.setText("ID");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuturalid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(culturalname, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuturalid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(culturalname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(435, 435, 435))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(410, 410, 410))))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(home, "card2");

        scorewindow.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel87.setBackground(new java.awt.Color(102, 102, 255));
        jLabel87.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Participant Information");
        jLabel87.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel87.setOpaque(true);

        v_Ima.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        v_Ima.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        v_Ima.setText("No Image Available");
        v_Ima.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 255), 4, true));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setText("Number:");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        name.setEditable(false);
        name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        name.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setText("Contestant Name:");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        parnum.setEditable(false);
        parnum.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        parnum.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        _participant.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        _participant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Name", "Team", "Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        _participant.setShowHorizontalLines(false);
        _participant.setShowVerticalLines(false);
        _participant.getTableHeader().setReorderingAllowed(false);
        _participant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _participantMouseClicked(evt);
            }
        });
        _participant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                _participantKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(_participant);
        _participant.getColumnModel().getColumn(0).setResizable(false);
        _participant.getColumnModel().getColumn(1).setResizable(false);
        _participant.getColumnModel().getColumn(1).setPreferredWidth(200);
        _participant.getColumnModel().getColumn(2).setResizable(false);
        _participant.getColumnModel().getColumn(2).setPreferredWidth(150);
        _participant.getColumnModel().getColumn(3).setResizable(false);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel7.setText("Team:");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        team.setEditable(false);
        team.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        team.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(parnum, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(team, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(v_Ima, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(v_Ima, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parnum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(team, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel90.setBackground(new java.awt.Color(102, 102, 255));
        jLabel90.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Major/Minor Events");
        jLabel90.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel90.setOpaque(true);

        cultural.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cultural.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Event Name", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cultural.setShowHorizontalLines(false);
        cultural.setShowVerticalLines(false);
        cultural.getTableHeader().setReorderingAllowed(false);
        cultural.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                culturalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                culturalMouseEntered(evt);
            }
        });
        cultural.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                culturalKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(cultural);
        cultural.getColumnModel().getColumn(0).setResizable(false);
        cultural.getColumnModel().getColumn(1).setResizable(false);
        cultural.getColumnModel().getColumn(1).setPreferredWidth(300);
        cultural.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(102, 102, 255), null, null));

        jLabel91.setBackground(new java.awt.Color(102, 102, 255));
        jLabel91.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Category");
        jLabel91.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel91.setOpaque(true);

        category.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Category Name", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        category.setShowHorizontalLines(false);
        category.setShowVerticalLines(false);
        category.getTableHeader().setReorderingAllowed(false);
        category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryMouseClicked(evt);
            }
        });
        category.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoryKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(category);
        category.getColumnModel().getColumn(0).setResizable(false);
        category.getColumnModel().getColumn(1).setResizable(false);
        category.getColumnModel().getColumn(1).setPreferredWidth(300);
        category.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel89.setBackground(new java.awt.Color(102, 102, 255));
        jLabel89.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Score Sheet");
        jLabel89.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel89.setOpaque(true);

        criteria.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        criteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Criteria", "Percentage", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        criteria.setRowHeight(25);
        criteria.setShowHorizontalLines(false);
        criteria.setShowVerticalLines(false);
        criteria.getTableHeader().setReorderingAllowed(false);
        criteria.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                criteriaComponentRemoved(evt);
            }
        });
        criteria.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                criteriaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(criteria);
        criteria.getColumnModel().getColumn(0).setResizable(false);
        criteria.getColumnModel().getColumn(1).setResizable(false);
        criteria.getColumnModel().getColumn(1).setPreferredWidth(200);
        criteria.getColumnModel().getColumn(2).setResizable(false);
        criteria.getColumnModel().getColumn(3).setResizable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jLabel2.setText("Total Score Computed base on percentage:");

        total.setBackground(new java.awt.Color(0, 0, 0));
        total.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total.setText("0.00");
        total.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton1.setText("Submit");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(psink)
                        .addGap(25, 25, 25)
                        .addComponent(csink))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(catsink)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(psink)
                                .addComponent(csink))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(catsink)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout scorewindowLayout = new javax.swing.GroupLayout(scorewindow);
        scorewindow.setLayout(scorewindowLayout);
        scorewindowLayout.setHorizontalGroup(
            scorewindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scorewindowLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scorewindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        scorewindowLayout.setVerticalGroup(
            scorewindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scorewindowLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(scorewindow, "card3");

        jMenu1.setBackground(new java.awt.Color(0, 255, 0));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/action.png"))); // NOI18N
        jMenu1.setText("Action");
        jMenu1.setOpaque(true);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Home.png"))); // NOI18N
        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/system-log-out.png"))); // NOI18N
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card2");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        _getData_Cultural();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        _getData_Cultural();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        _getData_Cultural();
    }//GEN-LAST:event_homeMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int dialog =  JOptionPane.showConfirmDialog(null,"Do you want to close the system?","Prompt Window",JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
        dispose();
        new login().show();       
        }
       else if(dialog == JOptionPane.NO_OPTION) {}
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void criteriaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_criteriaAncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_criteriaAncestorAdded

    private void criteriaComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_criteriaComponentRemoved
        // TODO add your handling code here:
        
        int rw = criteria.getRowCount();
        double totalpoints= 0;
        double TableClick1 = 0;
        double TableClick2 =0;
        NumberFormat formatter = new DecimalFormat("#0.00");
        int row = criteria.getSelectedRow();
      
        Object TableClick = (criteria.getModel().getValueAt(row, 3));
       if(TableClick == null){
           criteria.getModel().setValueAt(0.00, row, 3);
       }
       else{
        for(int i = 0; i<rw ;i++){
           TableClick1 = ((Double) criteria.getModel().getValueAt(i, 2)).doubleValue();
           TableClick2 = ((Double) criteria.getModel().getValueAt(i, 3)).doubleValue();
           totalpoints += (TableClick1/100)*TableClick2;
       }
           total.setText(formatter.format(totalpoints));
           
       }
        
     
    }//GEN-LAST:event_criteriaComponentRemoved

    private void culturalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_culturalMouseClicked
        // TODO add your handling code here:
        _getAllData();
    }//GEN-LAST:event_culturalMouseClicked

    private void categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryMouseClicked
        // TODO add your handling code here:
        _displayCriteria();
    }//GEN-LAST:event_categoryMouseClicked

    private void culturalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_culturalKeyReleased
        // TODO add your handling code here:
        
        _getAllData();
    }//GEN-LAST:event_culturalKeyReleased

    private void categoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryKeyReleased
        // TODO add your handling code here:
        _displayCriteria();
    }//GEN-LAST:event_categoryKeyReleased

    private void _participantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__participantMouseClicked
        // TODO add your handling code here:
        _getPic();
    }//GEN-LAST:event__participantMouseClicked

    private void _participantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__participantKeyReleased
        // TODO add your handling code here:
        _getPic();
    }//GEN-LAST:event__participantKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        _AddCriteriaScore add = new _AddCriteriaScoreImplementation();
        int row = criteria.getRowCount();
        int rowCri = criteria.getSelectedRow();
        int rowPart = _participant.getSelectedRow();
        int rowCul = cultural.getSelectedRow();
        int rowCat = category.getSelectedRow();
        
        int cul;
        if(psink.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Select Participant","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(csink.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Select Event","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            
           if(catsink.getText().equals("")){
             for(int i = 0;i<row;i++){
            int part = ((Integer)_participant.getModel().getValueAt(rowPart, 3)).intValue();
            cul = ((Integer)cultural.getModel().getValueAt(rowCul, 0)).intValue();
            int cri = ((Integer)criteria.getModel().getValueAt(i, 0)).intValue();
            
            String percent = (criteria.getModel().getValueAt(i, 2)).toString();
            double score = ((Double)criteria.getModel().getValueAt(i, 3)).doubleValue();
           // add._addnocat(part, cul, cri, score, percent,cuturalid.getText());
          }
            JOptionPane.showMessageDialog(null,"Successfully  Saved");
           }
           else{
            for(int i = 0;i<row;i++){
            int part = ((Integer)_participant.getModel().getValueAt(rowPart, 3)).intValue();
            cul = ((Integer)cultural.getModel().getValueAt(rowCul, 0)).intValue();
            int cri = ((Integer)criteria.getModel().getValueAt(i, 0)).intValue();
            int cat = ((Integer)category.getModel().getValueAt(rowCat, 0)).intValue();
            String percent = (criteria.getModel().getValueAt(i, 2)).toString();
             double score = ((Double)criteria.getModel().getValueAt(i, 3)).doubleValue();
          //  add._add(part, cul, cri, score, percent,cuturalid.getText(),cat);
        }
            JOptionPane.showMessageDialog(null,"Successfully  Saved");
        }
        }
//        try{
//        List<_rankgetset> sList11 = new ArrayList<>();
//        List<_rankgetset> sList12= new ArrayList<>();
//        _generaterank p11 =new _generaterank();
//        _generaterank p12 =new _generaterank();
//        String x = null;
//        double y =0;
//        sList11= p11.Gen_rak(cul);
//             sList12= p12.Gen_rak1();
//        for (int i=0;i<sList11.size();i++){
//            x = sList11.get(i).getTeam_Name();
//            y = sList12.get(i).getPoints();
//           _inserttemp sp = new _inserttemp_implement();
//           sp.add(x, y);
//        }
//        }catch(Exception e){}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void culturalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_culturalMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_culturalMouseEntered

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
            java.util.logging.Logger.getLogger(cultural_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cultural_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cultural_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cultural_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new cultural_window().setVisible(true);
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        time.setText(String.format("%tI:%<tM:%<tS %<Tp", new Date()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable _participant;
    private javax.swing.JTable category;
    private javax.swing.JLabel catsink;
    private javax.swing.JTable criteria;
    private javax.swing.JLabel csink;
    private javax.swing.JTable cultural;
    public static javax.swing.JLabel culturalname;
    public static javax.swing.JLabel cuturalid;
    private javax.swing.JLabel date;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel main;
    private javax.swing.JTextField name;
    private javax.swing.JTextField parnum;
    private javax.swing.JLabel psink;
    private javax.swing.JPanel scorewindow;
    private javax.swing.JTextField team;
    private javax.swing.JLabel time;
    private javax.swing.JTextField total;
    public static javax.swing.JLabel v_Ima;
    // End of variables declaration//GEN-END:variables
}
