/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Graphical_User_Interface.Administrator_Module;

import Tabulation.Interface.Implementation.Imp_PresentJudge;
import Tabulation.Interface.Present_Judge;
import Tabulation.Methods.Insert_Commands.getifgrouporindi;
import Tabulation.Methods.Select_Commands.*;
import Tabulation.getters_setters.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author Neil
 */
public class Controller extends javax.swing.JFrame implements ActionListener{

Timer clockTimer;
private boolean enable;
private boolean enabled;
private String universalID;
private String universalcriID;
public static DefaultTableModel model1;

    public Controller() {
        
        initComponents();
        _getData_Cultural();
        _getTeam();
        clockTimer = new Timer(3000,this );
        clockTimer.start();
        addWindowListener(new Controller.LastProcess());
        imp();
        this.setLocationRelativeTo(null);
    }
    
     

    public void _getData_Cultural(){
        
        DefaultTableModel model=(DefaultTableModel)cul_table.getModel();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._query_Culturalevents();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            cul_table.setValueAt(sList.get(i).getCul_ID(), i, 0);
            cul_table.setValueAt(sList.get(i).getCul_Name(), i, 1);
            cul_table.setValueAt(sList.get(i).getCul_Percentage(), i, 2);
            cul_table.setValueAt(sList.get(i).getCul_Status(), i, 3);
            }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
    
    public void _getTeam(){
        try{
        DefaultTableModel model=(DefaultTableModel)team_table.getModel();
      
       if (model.getRowCount() != 0){
            model.setRowCount(0);
         }
        List<Team> sList = new ArrayList<>();
        query_Team_Name QTN =new query_Team_Name();
        sList = QTN.team_controller();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            team_table.setValueAt(sList.get(i).getTeam_ID(), i, 0);
            team_table.setValueAt(sList.get(i).getTeam_Name(), i, 1);
            team_table.setValueAt(sList.get(i).getTeam_Number(), i, 2);
            }
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void _getAllData(){
        DefaultTableModel model=(DefaultTableModel)cat_table.getModel();
        DefaultTableModel model2=(DefaultTableModel)criteria_table.getModel();
        DefaultTableModel model1=(DefaultTableModel)cul_table.getModel();
        DefaultTableModel model3=(DefaultTableModel)part_table.getModel();
        DefaultTableModel model4=(DefaultTableModel)team_table.getModel();
        List<Event_Category> sList = new ArrayList<Event_Category>(); 
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        List<Participant> XList = new ArrayList<Participant>();
        List<Team> TList = new ArrayList<Team>();
        Query_EventDetails_forSearch QEDFS =new Query_EventDetails_forSearch();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        _Select_Cultural_Events _SCE = new _Select_Cultural_Events();
        int row = cul_table.getSelectedRow();
        
        String TableClick = (cul_table.getModel().getValueAt(row, 0).toString());
        
        
        try {
            sList = QEDFS.CategoryDetailsforsearch(Integer.parseInt(TableClick));
            CList = QCD.CriteriaDetailsforsearch(Integer.parseInt(TableClick));
            CList = QCD.CriteriaDetailsforsearch1(Integer.parseInt(TableClick));
            XList = _SCE._query_participants(TableClick);
            TList = _SCE._query_Teams(TableClick);
                model.setRowCount(0);
                model2.setRowCount(0);
                model3.setRowCount(0);
                model4.setRowCount(0);
                v_Ima.setIcon(null);
                v_Ima.setText("No Image Available");
                TImage8.setIcon(null);
                TImage8.setText("No Image Available");
            for (int i=0;i<sList.size();i++){
                model.addRow(new Object[]{});
               cat_table.setValueAt(sList.get(i).getCategory_ID(), i, 0);
               cat_table.setValueAt(sList.get(i).getCategory_Name(), i, 1);
               cat_table.setValueAt(sList.get(i).getCategory_Per(), i, 2);
               
            }
            
            for(int j=0;j<CList.size();j++){
                
                model2.addRow(new Object[]{});
                criteria_table.setValueAt(CList.get(j).getCr_ID(), j, 0);
                criteria_table.setValueAt(CList.get(j).getCr_Description(), j, 1);
                criteria_table.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
            for (int k=0;k<XList.size();k++){
                model3.addRow(new Object[]{});
              
               part_table.setValueAt(XList.get(k).getFullname(), k, 0);
               part_table.setValueAt(XList.get(k).getTName(), k, 1);
               part_table.setValueAt(XList.get(k).getIDID(), k, 2);
            }
            
            for(int j=0; j<TList.size(); j++){
                model4.addRow(new Object[]{});
                
                team_table.setValueAt(TList.get(j).gettID(), j, 0);
                team_table.setValueAt(TList.get(j).getTeam_Name(), j, 1);
                 team_table.setValueAt(TList.get(j).getTeam_Number(), j, 2);
                
            }
          
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
    
    public void imp(){
        J1Pic.setVisible(false);J2Pic.setVisible(false);J3Pic.setVisible(false);J4Pic.setVisible(false);
        J5Pic.setVisible(false);J6Pic.setVisible(false);J7Pic.setVisible(false);J8Pic.setVisible(false);
        J9Pic.setVisible(false);J10Pic.setVisible(false);J11Pic.setVisible(false);J12Pic.setVisible(false);
        J13Pic.setVisible(false);J14Pic.setVisible(false);J15Pic.setVisible(false);J16Pic.setVisible(false);
        J17Pic.setVisible(false);J18Pic.setVisible(false);J19Pic.setVisible(false);J20Pic.setVisible(false);
        J21Pic.setVisible(false);J22Pic.setVisible(false);J23Pic.setVisible(false);J24Pic.setVisible(false);
        J25Pic.setVisible(false);J26Pic.setVisible(false);J27Pic.setVisible(false);J28Pic.setVisible(false);
        J29Pic.setVisible(false);J30Pic.setVisible(false);J31Pic.setVisible(false);J32Pic.setVisible(false);
        J33Pic.setVisible(false);J34Pic.setVisible(false);J35Pic.setVisible(false);J36Pic.setVisible(false);
        J37Pic.setVisible(false);J38Pic.setVisible(false);J39Pic.setVisible(false);J40Pic.setVisible(false);
        J1lbl.setVisible(false);J2lbl.setVisible(false);J3lbl.setVisible(false);J4lbl.setVisible(false);
        J5lbl.setVisible(false);J6lbl.setVisible(false);J7lbl.setVisible(false);J8lbl.setVisible(false);
        J9lbl.setVisible(false);J10lbl.setVisible(false);J11lbl.setVisible(false);J12lbl.setVisible(false);
        J13lbl.setVisible(false);J14lbl.setVisible(false);J15lbl.setVisible(false);J16lbl.setVisible(false);
        J17lbl.setVisible(false);J18lbl.setVisible(false);J19lbl.setVisible(false);J20lbl.setVisible(false);
        J21lbl.setVisible(false);J22lbl.setVisible(false);J23lbl.setVisible(false);J24lbl.setVisible(false);
        J25lbl.setVisible(false);J26lbl.setVisible(false);J27lbl.setVisible(false);J28lbl.setVisible(false);
        J29lbl.setVisible(false);J30lbl.setVisible(false);J31lbl.setVisible(false);J32lbl.setVisible(false);
        J33lbl.setVisible(false);J34lbl.setVisible(false);J35lbl.setVisible(false);J36lbl.setVisible(false);
        J37lbl.setVisible(false);J38lbl.setVisible(false);J39lbl.setVisible(false);J40lbl.setVisible(false);
        
    }
    public void _displayCriteria(){
         DefaultTableModel model=(DefaultTableModel)criteria_table.getModel();
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = cat_table.getSelectedRow();
        String TableClick = (cat_table.getModel().getValueAt(row, 0).toString());
        model.setRowCount(0);
        
        try{
        CList = QCD.CriteriaDetailsforsearch2(Integer.parseInt(TableClick));
        //EventD.getSelectionModel().clearSelection();
        for(int j=0;j<CList.size();j++){
            double a = 0.00;
                model.addRow(new Object[]{});
                criteria_table.setValueAt(CList.get(j).getCr_ID(), j, 0);
                criteria_table.setValueAt(CList.get(j).getCr_Description(), j, 1);
                criteria_table.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
        
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
     }
    public void _getTeamPic(){
         try{
        
        int row = team_table.getSelectedRow();
        String TableClick = (team_table.getModel().getValueAt(row, 0).toString());
        query_participant_details q = new query_participant_details();
        v_Ima.setText("");
        q._query_TeamImage(TableClick);
        
     
        }catch(Exception e){
        System.out.print(e);}
    }
     public void _getPartPic(){
         try{
        
        int row = part_table.getSelectedRow();
        String TableClick = (part_table.getModel().getValueAt(row, 2).toString());
        query_participant_details q = new query_participant_details();
        TImage8.setText("");
        q._query_PartImage(TableClick);
        
        
        }catch(Exception e){System.out.print(e);}
    }
     
     public void  setPart(){
      _getPartPic();
        Properties prop = new Properties();
        int partrow = part_table.getSelectedRow();
        int crirow = criteria_table.getSelectedRow();
        Present_Judge PJ = new Imp_PresentJudge();
        int culrow = cul_table.getSelectedRow();
        try {
       
            
        if(universalID.equals("1")){
            int catrow = cat_table.getSelectedRow();
            prop.setProperty("Participant_No",part_table.getValueAt(partrow, 2).toString());
            prop.setProperty("Participant_Name", part_table.getValueAt(partrow, 0).toString());
    	    prop.setProperty("Cultural_ID",cul_table.getValueAt(culrow, 0).toString());
            prop.setProperty("Cultural_Name",cul_table.getValueAt(culrow, 1).toString());
            prop.setProperty("Category_ID",cat_table.getValueAt(catrow, 0).toString());
            prop.setProperty("Category_Name",cat_table.getValueAt(catrow, 1).toString());
            prop.setProperty("Criteria_ID",criteria_table.getValueAt(crirow, 0).toString());
            prop.setProperty("Criteria",criteria_table.getValueAt(crirow, 1).toString());
            prop.setProperty("Criteria_Percent",criteria_table.getValueAt(crirow, 2).toString());
            prop.setProperty("Team_No","Team is not be setted for scoring");
            prop.setProperty("Team_Name","Team is not be setted for scoring");
            prop.setProperty("DerivedID","No Data");
            prop.store(new FileOutputStream("temp.properties"), null);
            PJ.add(1,"");
        }
        else{
            prop.setProperty("Participant_No",part_table.getValueAt(partrow, 2).toString());
            prop.setProperty("Participant_Name", part_table.getValueAt(partrow, 0).toString());
    	    prop.setProperty("Cultural_ID",cul_table.getValueAt(culrow, 0).toString());
            prop.setProperty("Cultural_Name",cul_table.getValueAt(culrow, 1).toString());
            prop.setProperty("Category_ID","This event has no category");
            prop.setProperty("Category_Name","This event has no category");
            prop.setProperty("Criteria_ID",criteria_table.getValueAt(crirow, 0).toString());
            prop.setProperty("Criteria",criteria_table.getValueAt(crirow, 1).toString());
            prop.setProperty("Criteria_Percent",criteria_table.getValueAt(crirow, 2).toString());
            prop.setProperty("Team_No","Team is not be setted for scoring");
            prop.setProperty("Team_Name","Team is not be setted for scoring");
            prop.setProperty("DerivedID","No Data");
            prop.store(new FileOutputStream("temp.properties"), null);
             PJ.add(1,"");
        
            }
    	} catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,"Please set first the Category or Criteria","Error",JOptionPane.ERROR_MESSAGE);
        }
     }
     
     public void setTeam(){
         _getTeamPic();
         Present_Judge PJ = new Imp_PresentJudge();
        Properties prop = new Properties();
        int teamrow = team_table.getSelectedRow();
        int crirow = criteria_table.getSelectedRow();
        
        int culrow = cul_table.getSelectedRow();
        try {
        
        
        if(universalID.equals("1")){
            int catrow = cat_table.getSelectedRow();
            prop.setProperty("Team_No",team_table.getValueAt(teamrow, 0).toString());
            prop.setProperty("Team_Name", team_table.getValueAt(teamrow, 1).toString());
    	    prop.setProperty("Cultural_ID",cul_table.getValueAt(culrow, 0).toString());
            prop.setProperty("Cultural_Name",cul_table.getValueAt(culrow, 1).toString());
            prop.setProperty("Category_ID",cat_table.getValueAt(catrow, 0).toString());
            prop.setProperty("Category_Name",cat_table.getValueAt(catrow, 1).toString());
            prop.setProperty("Criteria_ID",criteria_table.getValueAt(crirow, 0).toString());
           prop.setProperty("Criteria",criteria_table.getValueAt(crirow, 1).toString());
           prop.setProperty("Criteria_Percent",criteria_table.getValueAt(crirow, 2).toString());
            prop.setProperty("Participant_No","Participant is not be setted for scoring");
            prop.setProperty("Participant_Name","Participant is not be setted for scoring");
            prop.setProperty("DerivedID",new getifgrouporindi().get(team_table.getValueAt(teamrow, 0).toString(), cul_table.getValueAt(culrow, 0).toString()));
            prop.store(new FileOutputStream("temp.properties"), null);
             PJ.add(1,"");
        }
        else{
            prop.setProperty("Team_No",team_table.getValueAt(teamrow, 0).toString());
            prop.setProperty("Team_Name", team_table.getValueAt(teamrow, 1).toString());
    	    prop.setProperty("Cultural_ID",cul_table.getValueAt(culrow, 0).toString());
            prop.setProperty("Cultural_Name",cul_table.getValueAt(culrow, 1).toString());
            prop.setProperty("Category_ID","This event has no category");
            prop.setProperty("Category_Name","This event has no category");
            prop.setProperty("Criteria_ID",criteria_table.getValueAt(crirow, 0).toString());
            prop.setProperty("Criteria",criteria_table.getValueAt(crirow, 1).toString());
            prop.setProperty("Criteria_Percent",criteria_table.getValueAt(crirow, 2).toString());
            prop.setProperty("Participant_No","Participant is not be setted for scoring");
            prop.setProperty("Participant_Name",team_table.getValueAt(teamrow, 1).toString());
            prop.setProperty("DerivedID",new getifgrouporindi().get(team_table.getValueAt(teamrow, 0).toString(), cul_table.getValueAt(culrow, 0).toString()));
            prop.store(new FileOutputStream("temp.properties"), null);
             PJ.add(1,"");
            
        }     
    	} catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,"Please set first the Category or Criteria","Error",JOptionPane.ERROR_MESSAGE);
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

        textarePOP = new javax.swing.JPopupMenu();
        Clear = new javax.swing.JMenuItem();
        culPOP = new javax.swing.JPopupMenu();
        culSet = new javax.swing.JMenuItem();
        culDone = new javax.swing.JMenuItem();
        culRollback = new javax.swing.JMenuItem();
        catPOP = new javax.swing.JPopupMenu();
        catSet = new javax.swing.JMenuItem();
        catDone = new javax.swing.JMenuItem();
        catRollback = new javax.swing.JMenuItem();
        partPOP = new javax.swing.JPopupMenu();
        partSet = new javax.swing.JMenuItem();
        partDone = new javax.swing.JMenuItem();
        partRollback = new javax.swing.JMenuItem();
        teamPOP = new javax.swing.JPopupMenu();
        teamSet = new javax.swing.JMenuItem();
        teamDone = new javax.swing.JMenuItem();
        teamRollback = new javax.swing.JMenuItem();
        criPOP = new javax.swing.JPopupMenu();
        criSet = new javax.swing.JMenuItem();
        criDone = new javax.swing.JMenuItem();
        criRollback = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cul_table = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer,
                int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                List<Integer> selectedRows = (List<Integer>) getClientProperty("highlightRows");
                c.setBackground(selectedRows.contains(row) ? new Color(255,102,102) : getBackground());
                if(isRowSelected(row)){
                    c.setBackground(new Color(102,102,255));
                }
                return c;
            }

        };
        jPanel6 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cat_table = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer,
                int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                List<Integer> selectedRows = (List<Integer>) getClientProperty("highlightRows");
                c.setBackground(selectedRows.contains(row) ? new Color(255,102,102) : getBackground());
                if(isRowSelected(row)){
                    c.setBackground(new Color(102,102,255));
                }
                return c;
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        v_Ima = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        team_table = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer,
                int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                List<Integer> selectedRows = (List<Integer>) getClientProperty("highlightRows");
                c.setBackground(selectedRows.contains(row) ? new Color(255,102,102) : getBackground());
                if(isRowSelected(row)){
                    c.setBackground(new Color(102,102,255));
                }
                return c;
            }
        };
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        part_table = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer,
                int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                List<Integer> selectedRows = (List<Integer>) getClientProperty("highlightRows");
                c.setBackground(selectedRows.contains(row) ? new Color(255,102,102) : getBackground());
                if(isRowSelected(row)){
                    c.setBackground(new Color(102,102,255));
                }
                return c;
            }
        };
        jLabel91 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        TImage8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        J1Pic = new javax.swing.JLabel();
        J1lbl = new javax.swing.JLabel();
        J2lbl = new javax.swing.JLabel();
        J2Pic = new javax.swing.JLabel();
        J3lbl = new javax.swing.JLabel();
        J3Pic = new javax.swing.JLabel();
        J4lbl = new javax.swing.JLabel();
        J4Pic = new javax.swing.JLabel();
        J5lbl = new javax.swing.JLabel();
        J5Pic = new javax.swing.JLabel();
        J6lbl = new javax.swing.JLabel();
        J6Pic = new javax.swing.JLabel();
        J7lbl = new javax.swing.JLabel();
        J7Pic = new javax.swing.JLabel();
        J8lbl = new javax.swing.JLabel();
        J8Pic = new javax.swing.JLabel();
        J9lbl = new javax.swing.JLabel();
        J9Pic = new javax.swing.JLabel();
        J10lbl = new javax.swing.JLabel();
        J10Pic = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        J11lbl = new javax.swing.JLabel();
        J11Pic = new javax.swing.JLabel();
        J12Pic = new javax.swing.JLabel();
        J12lbl = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        J13lbl = new javax.swing.JLabel();
        J13Pic = new javax.swing.JLabel();
        J14Pic = new javax.swing.JLabel();
        J14lbl = new javax.swing.JLabel();
        J15lbl = new javax.swing.JLabel();
        J15Pic = new javax.swing.JLabel();
        J16Pic = new javax.swing.JLabel();
        J16lbl = new javax.swing.JLabel();
        J17lbl = new javax.swing.JLabel();
        J17Pic = new javax.swing.JLabel();
        J18Pic = new javax.swing.JLabel();
        J18lbl = new javax.swing.JLabel();
        J19lbl = new javax.swing.JLabel();
        J19Pic = new javax.swing.JLabel();
        J20Pic = new javax.swing.JLabel();
        J20lbl = new javax.swing.JLabel();
        J21lbl = new javax.swing.JLabel();
        J21Pic = new javax.swing.JLabel();
        J22Pic = new javax.swing.JLabel();
        J22lbl = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        J23lbl = new javax.swing.JLabel();
        J23Pic = new javax.swing.JLabel();
        J24Pic = new javax.swing.JLabel();
        J24lbl = new javax.swing.JLabel();
        J25lbl = new javax.swing.JLabel();
        J25Pic = new javax.swing.JLabel();
        J26Pic = new javax.swing.JLabel();
        J26lbl = new javax.swing.JLabel();
        J27lbl = new javax.swing.JLabel();
        J27Pic = new javax.swing.JLabel();
        J28Pic = new javax.swing.JLabel();
        J28lbl = new javax.swing.JLabel();
        J29lbl = new javax.swing.JLabel();
        J29Pic = new javax.swing.JLabel();
        J30Pic = new javax.swing.JLabel();
        J30lbl = new javax.swing.JLabel();
        J31lbl = new javax.swing.JLabel();
        J31Pic = new javax.swing.JLabel();
        J32Pic = new javax.swing.JLabel();
        J32lbl = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        J33lbl = new javax.swing.JLabel();
        J33Pic = new javax.swing.JLabel();
        J34Pic = new javax.swing.JLabel();
        J34lbl = new javax.swing.JLabel();
        J35lbl = new javax.swing.JLabel();
        J35Pic = new javax.swing.JLabel();
        J36Pic = new javax.swing.JLabel();
        J36lbl = new javax.swing.JLabel();
        J37lbl = new javax.swing.JLabel();
        J37Pic = new javax.swing.JLabel();
        J38Pic = new javax.swing.JLabel();
        J38lbl = new javax.swing.JLabel();
        J39lbl = new javax.swing.JLabel();
        J39Pic = new javax.swing.JLabel();
        J40Pic = new javax.swing.JLabel();
        J40lbl = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        criteria_table = new javax.swing.JTable(){

            public Component prepareRenderer(TableCellRenderer renderer,
                int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                List<Integer> selectedRows = (List<Integer>) getClientProperty("highlightRows");
                c.setBackground(selectedRows.contains(row) ? new Color(255,102,102) : getBackground());
                if(isRowSelected(row)){
                    c.setBackground(new Color(102,102,255));
                }

                return c;
            }

        };
        jLabel93 = new javax.swing.JLabel();

        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/clear.png"))); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        textarePOP.add(Clear);

        culSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/settings.png"))); // NOI18N
        culSet.setText("Set");
        culSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                culSetActionPerformed(evt);
            }
        });
        culPOP.add(culSet);

        culDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Marker.png"))); // NOI18N
        culDone.setText("Mark as Done Scoring");
        culDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                culDoneActionPerformed(evt);
            }
        });
        culPOP.add(culDone);

        culRollback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/refresh.png"))); // NOI18N
        culRollback.setText("Rollback Marker");
        culRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                culRollbackActionPerformed(evt);
            }
        });
        culPOP.add(culRollback);

        catSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/settings.png"))); // NOI18N
        catSet.setText("Set");
        catSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catSetActionPerformed(evt);
            }
        });
        catPOP.add(catSet);

        catDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Marker.png"))); // NOI18N
        catDone.setText("Mark as Done Scoring");
        catDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catDoneActionPerformed(evt);
            }
        });
        catPOP.add(catDone);

        catRollback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/refresh.png"))); // NOI18N
        catRollback.setText("Rollback Marker");
        catRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catRollbackActionPerformed(evt);
            }
        });
        catPOP.add(catRollback);

        partSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/settings.png"))); // NOI18N
        partSet.setText("Set");
        partSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partSetActionPerformed(evt);
            }
        });
        partPOP.add(partSet);

        partDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Marker.png"))); // NOI18N
        partDone.setText("Mark as Done Scoring");
        partDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partDoneActionPerformed(evt);
            }
        });
        partPOP.add(partDone);

        partRollback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/refresh.png"))); // NOI18N
        partRollback.setText("Rollback Marker");
        partRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partRollbackActionPerformed(evt);
            }
        });
        partPOP.add(partRollback);

        teamSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/settings.png"))); // NOI18N
        teamSet.setText("Set");
        teamSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamSetActionPerformed(evt);
            }
        });
        teamPOP.add(teamSet);

        teamDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Marker.png"))); // NOI18N
        teamDone.setText("Mark as Done Scoring");
        teamDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamDoneActionPerformed(evt);
            }
        });
        teamPOP.add(teamDone);

        teamRollback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/refresh.png"))); // NOI18N
        teamRollback.setText("Rollback Marker");
        teamRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamRollbackActionPerformed(evt);
            }
        });
        teamPOP.add(teamRollback);

        criSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/settings.png"))); // NOI18N
        criSet.setText("Set");
        criSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criSetActionPerformed(evt);
            }
        });
        criPOP.add(criSet);

        criDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Marker.png"))); // NOI18N
        criDone.setText("Mark as Done Scoring");
        criDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criDoneActionPerformed(evt);
            }
        });
        criPOP.add(criDone);

        criRollback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/refresh.png"))); // NOI18N
        criRollback.setText("Rollback Marker");
        criRollback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criRollbackActionPerformed(evt);
            }
        });
        criPOP.add(criRollback);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Controller");
        setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("tablogo.gif")));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel87.setBackground(new java.awt.Color(102, 102, 255));
        jLabel87.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Event");
        jLabel87.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel87.setOpaque(true);
        jLabel87.setPreferredSize(new java.awt.Dimension(12, 28));

        cul_table.putClientProperty("highlightRows", new ArrayList<Integer>());
        cul_table.setSelectionBackground(new Color(102,102,255));
        cul_table.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cul_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODE", "Event Name", "Percentage", "Type of Scoring"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cul_table.setComponentPopupMenu(culPOP);
        cul_table.setFillsViewportHeight(true);
        cul_table.setOpaque(true);
        cul_table.setSelectionBackground(new Color(102,102,255));
        cul_table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        cul_table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cul_table.setShowHorizontalLines(false);
        cul_table.setShowVerticalLines(false);
        cul_table.getTableHeader().setReorderingAllowed(false);
        cul_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cul_tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cul_tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(cul_table);
        cul_table.getColumnModel().getColumn(0).setResizable(false);
        cul_table.getColumnModel().getColumn(0).setPreferredWidth(100);
        cul_table.getColumnModel().getColumn(1).setResizable(false);
        cul_table.getColumnModel().getColumn(1).setPreferredWidth(170);
        cul_table.getColumnModel().getColumn(2).setResizable(false);
        cul_table.getColumnModel().getColumn(2).setPreferredWidth(90);
        cul_table.getColumnModel().getColumn(3).setResizable(false);
        cul_table.getColumnModel().getColumn(3).setPreferredWidth(130);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel88.setBackground(new java.awt.Color(102, 102, 255));
        jLabel88.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Category");
        jLabel88.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel88.setOpaque(true);

        cat_table.putClientProperty("highlightRows", new ArrayList<Integer>());
        cat_table.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cat_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CODE", "Category Name", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cat_table.setComponentPopupMenu(catPOP);
        cat_table.setFillsViewportHeight(true);
        cat_table.setShowHorizontalLines(false);
        cat_table.setShowVerticalLines(false);
        cat_table.getTableHeader().setReorderingAllowed(false);
        cat_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cat_tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cat_tableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(cat_table);
        cat_table.getColumnModel().getColumn(0).setResizable(false);
        cat_table.getColumnModel().getColumn(1).setResizable(false);
        cat_table.getColumnModel().getColumn(1).setPreferredWidth(190);
        cat_table.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel90.setBackground(new java.awt.Color(102, 102, 255));
        jLabel90.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Team");
        jLabel90.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel90.setOpaque(true);

        jPanel52.setBackground(new java.awt.Color(51, 51, 51));
        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel52.setOpaque(false);

        v_Ima.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        v_Ima.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        v_Ima.setText("No Image Available");
        v_Ima.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(v_Ima, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(v_Ima, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        team_table.putClientProperty("highlightRows", new ArrayList<Integer>());
        team_table.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        team_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CODE", "Team", "Team Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        team_table.setComponentPopupMenu(teamPOP);
        team_table.setFillsViewportHeight(true);
        team_table.setShowHorizontalLines(false);
        team_table.setShowVerticalLines(false);
        team_table.getTableHeader().setReorderingAllowed(false);
        team_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                team_tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                team_tableMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(team_table);
        team_table.getColumnModel().getColumn(0).setResizable(false);
        team_table.getColumnModel().getColumn(1).setResizable(false);
        team_table.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));
        jPanel4.setPreferredSize(new java.awt.Dimension(329, 215));

        part_table.putClientProperty("highlightRows", new ArrayList<Integer>());
        part_table.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        part_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Team", "CODE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        part_table.setComponentPopupMenu(partPOP);
        part_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        part_table.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        part_table.setFillsViewportHeight(true);
        part_table.setShowHorizontalLines(false);
        part_table.setShowVerticalLines(false);
        part_table.getTableHeader().setReorderingAllowed(false);
        part_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                part_tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                part_tableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(part_table);
        part_table.getColumnModel().getColumn(0).setResizable(false);
        part_table.getColumnModel().getColumn(0).setPreferredWidth(150);
        part_table.getColumnModel().getColumn(1).setResizable(false);
        part_table.getColumnModel().getColumn(2).setResizable(false);

        jLabel91.setBackground(new java.awt.Color(102, 102, 255));
        jLabel91.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Participant");
        jLabel91.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel91.setOpaque(true);

        jPanel53.setBackground(new java.awt.Color(51, 51, 51));
        jPanel53.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel53.setOpaque(false);

        TImage8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TImage8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TImage8.setText("No Image Available");
        TImage8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage8, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage8, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel11.setBackground(new java.awt.Color(102, 102, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, null));
        jLabel11.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel12.setText("Set data");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel10.setText("Done Scoring");

        jLabel1.setBackground(new java.awt.Color(255, 102, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, null));
        jLabel1.setOpaque(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        J1Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J1Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J1lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J1lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J1lbl.setText("Judge 1");

        J2lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J2lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J2lbl.setText("Judge 1");

        J2Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J2Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J3lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J3lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J3lbl.setText("Judge 1");

        J3Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J3Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J4lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J4lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J4lbl.setText("Judge 1");

        J4Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J4Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J5lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J5lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J5lbl.setText("Judge 1");

        J5Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J5Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J6lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J6lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J6lbl.setText("Judge 1");

        J6Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J6Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J7lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J7lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J7lbl.setText("Judge 1");

        J7Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J7Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J8lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J8lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J8lbl.setText("Judge 1");

        J8Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J8Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J9lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J9lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J9lbl.setText("Judge 1");

        J9Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J9Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J10lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J10lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J10lbl.setText("Judge 1");

        J10Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J10Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(240, 240, 240));

        J11lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J11lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J11lbl.setText("Judge 1");

        J11Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J11Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J12Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J12Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J12lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J12lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J12lbl.setText("Judge 1");

        jSeparator2.setBackground(new java.awt.Color(240, 240, 240));

        J13lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J13lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J13lbl.setText("Judge 1");

        J13Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J13Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J14Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J14Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J14lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J14lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J14lbl.setText("Judge 1");

        J15lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J15lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J15lbl.setText("Judge 1");

        J15Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J15Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J16Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J16Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J16lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J16lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J16lbl.setText("Judge 1");

        J17lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J17lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J17lbl.setText("Judge 1");

        J17Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J17Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J18Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J18Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J18lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J18lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J18lbl.setText("Judge 1");

        J19lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J19lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J19lbl.setText("Judge 1");

        J19Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J19Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J20Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J20Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J20lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J20lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J20lbl.setText("Judge 1");

        J21lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J21lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J21lbl.setText("Judge 1");

        J21Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J21Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J22Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J22Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J22lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J22lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J22lbl.setText("Judge 1");

        jSeparator3.setBackground(new java.awt.Color(240, 240, 240));

        J23lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J23lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J23lbl.setText("Judge 1");

        J23Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J23Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J24Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J24Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J24lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J24lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J24lbl.setText("Judge 1");

        J25lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J25lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J25lbl.setText("Judge 1");

        J25Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J25Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J26Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J26Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J26lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J26lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J26lbl.setText("Judge 1");

        J27lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J27lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J27lbl.setText("Judge 1");

        J27Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J27Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J28Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J28Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J28lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J28lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J28lbl.setText("Judge 1");

        J29lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J29lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J29lbl.setText("Judge 1");

        J29Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J29Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J30Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J30Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J30lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J30lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J30lbl.setText("Judge 1");

        J31lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J31lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J31lbl.setText("Judge 1");

        J31Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J31Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J32Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J32Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J32lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J32lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J32lbl.setText("Judge 1");

        jSeparator4.setBackground(new java.awt.Color(240, 240, 240));

        J33lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J33lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J33lbl.setText("Judge 1");

        J33Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J33Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J34Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J34Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J34lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J34lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J34lbl.setText("Judge 1");

        J35lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J35lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J35lbl.setText("Judge 1");

        J35Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J35Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J36Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J36Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J36lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J36lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J36lbl.setText("Judge 1");

        J37lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J37lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J37lbl.setText("Judge 1");

        J37Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J37Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J38Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J38Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J38lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J38lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J38lbl.setText("Judge 1");

        J39lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J39lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J39lbl.setText("Judge 1");

        J39Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J39Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J40Pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J40Pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/computer-green.png"))); // NOI18N

        J40lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        J40lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J40lbl.setText("Judge 1");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J31lbl)
                            .addComponent(J31Pic))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J32lbl)
                            .addComponent(J32Pic))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J33lbl)
                            .addComponent(J33Pic))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J34lbl)
                            .addComponent(J34Pic))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J35lbl)
                            .addComponent(J35Pic))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J36lbl)
                            .addComponent(J36Pic))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J37lbl)
                            .addComponent(J37Pic))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J38lbl)
                            .addComponent(J38Pic))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J39Pic)
                            .addComponent(J39lbl))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J40lbl)
                            .addComponent(J40Pic)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J21lbl)
                            .addComponent(J21Pic))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J22lbl)
                            .addComponent(J22Pic))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J23lbl)
                            .addComponent(J23Pic))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J24lbl)
                            .addComponent(J24Pic))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J25lbl)
                            .addComponent(J25Pic))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J26lbl)
                            .addComponent(J26Pic))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J27lbl)
                            .addComponent(J27Pic))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J28lbl)
                            .addComponent(J28Pic))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J29Pic)
                            .addComponent(J29lbl))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J30lbl)
                            .addComponent(J30Pic)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J11lbl)
                                .addComponent(J11Pic))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J12lbl)
                                .addComponent(J12Pic))
                            .addGap(52, 52, 52)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J13lbl)
                                .addComponent(J13Pic))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J14lbl)
                                .addComponent(J14Pic))
                            .addGap(55, 55, 55)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J15lbl)
                                .addComponent(J15Pic))
                            .addGap(54, 54, 54)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J16lbl)
                                .addComponent(J16Pic))
                            .addGap(49, 49, 49)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J17lbl)
                                .addComponent(J17Pic))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J18lbl)
                                .addComponent(J18Pic))
                            .addGap(52, 52, 52)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J19Pic)
                                .addComponent(J19lbl))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J20lbl)
                                .addComponent(J20Pic)))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J1lbl)
                                .addComponent(J1Pic))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J2lbl)
                                .addComponent(J2Pic))
                            .addGap(52, 52, 52)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J3lbl)
                                .addComponent(J3Pic))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J4lbl)
                                .addComponent(J4Pic))
                            .addGap(55, 55, 55)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J5lbl)
                                .addComponent(J5Pic))
                            .addGap(54, 54, 54)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J6lbl)
                                .addComponent(J6Pic))
                            .addGap(49, 49, 49)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J7lbl)
                                .addComponent(J7Pic))
                            .addGap(50, 50, 50)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J8lbl)
                                .addComponent(J8Pic))
                            .addGap(52, 52, 52)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J9Pic)
                                .addComponent(J9lbl))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(J10lbl)
                                .addComponent(J10Pic)))))
                .addContainerGap(160, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J1Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J1lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J2Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J2lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J4Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J4lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J5Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J5lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J6Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J6lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J7Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J7lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J8Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J8lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J9Pic)
                            .addComponent(J10Pic))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J10lbl)
                            .addComponent(J9lbl)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J3Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J3lbl)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J11Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J11lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J12Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J12lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J14Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J14lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J15Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J15lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J16Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J16lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J17Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J17lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J18Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J18lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J19Pic)
                            .addComponent(J20Pic))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J20lbl)
                            .addComponent(J19lbl)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J13Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J13lbl)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J21Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J21lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J22Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J22lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J24Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J24lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J25Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J25lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J26Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J26lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J27Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J27lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J28Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J28lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J29Pic)
                            .addComponent(J30Pic))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J30lbl)
                            .addComponent(J29lbl)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J23Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J23lbl)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J31Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J31lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J32Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J32lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J34Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J34lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J35Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J35lbl))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J36Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J36lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J37Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J37lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(J38Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J38lbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J39Pic)
                            .addComponent(J40Pic))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J40lbl)
                            .addComponent(J39lbl)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(J33Pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(J33lbl)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel11);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        criteria_table.putClientProperty("highlightRows", new ArrayList<Integer>());
        criteria_table.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        criteria_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODE", "Criteria", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        criteria_table.setCellSelectionEnabled(false);
        criteria_table.setComponentPopupMenu(criPOP);
        criteria_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        criteria_table.setFillsViewportHeight(true);
        criteria_table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        criteria_table.setShowHorizontalLines(false);
        criteria_table.setShowVerticalLines(false);
        criteria_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(criteria_table);
        criteria_table.getColumnModel().getColumn(0).setResizable(false);
        criteria_table.getColumnModel().getColumn(1).setResizable(false);
        criteria_table.getColumnModel().getColumn(2).setResizable(false);

        jLabel93.setBackground(new java.awt.Color(102, 102, 255));
        jLabel93.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Criteria");
        jLabel93.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel93.setOpaque(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cul_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cul_tableMouseClicked
        // TODO add your handling code here:
        _getAllData();
        universalID = "";
    }//GEN-LAST:event_cul_tableMouseClicked

    private void cat_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cat_tableMouseClicked
        // TODO add your handling code here:
        _displayCriteria();
        universalID = "1";
    }//GEN-LAST:event_cat_tableMouseClicked

    private void part_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_part_tableMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_part_tableMouseClicked

    private void team_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_team_tableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_team_tableMouseClicked

    private void cul_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cul_tableMousePressed
   
//         if (evt.getClickCount() ==2){
//             
//                  
//                int row = cul_table.rowAtPoint(evt.getPoint());
//                if (row == -1)
//                    return;
//                List<Integer> selectedRows = (List<Integer>) cul_table
//                        .getClientProperty("highlightRows");
//                int index = selectedRows.indexOf(row);
//                if (index != -1)
//                    selectedRows.remove(index);
//                else
//                    selectedRows.add(row);
//                cul_table.repaint();
//                //cul_table.setForeground(Color.black);
//}
//         else{
//             
//             
//         }
////         else {cul_table.repaint();
////             
////             cul_table.setForeground(Color.black);
////             cul_table.setSelectionBackground(Color.black);
////         }
    }//GEN-LAST:event_cul_tableMousePressed

    private void cat_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cat_tableMousePressed
        // TODO add your handling code here:
//         if (evt.getClickCount() ==2){
//             
//                  
//                int row = cat_table.rowAtPoint(evt.getPoint());
//                if (row == -1)
//                    return;
//                List<Integer> selectedRows = (List<Integer>) cat_table
//                        .getClientProperty("highlightRows");
//                int index = selectedRows.indexOf(row);
//                if (index != -1)
//                    selectedRows.remove(index);
//                else
//                    selectedRows.add(row);
//                cat_table.repaint();
//                //cul_table.setForeground(Color.black);
//}
    }//GEN-LAST:event_cat_tableMousePressed

    private void team_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_team_tableMousePressed
//        // TODO add your handling code here:
//        if (evt.getClickCount() ==2){
//             
//                  
//                int row = team_table.rowAtPoint(evt.getPoint());
//                if (row == -1)
//                    return;
//                List<Integer> selectedRows = (List<Integer>) team_table
//                        .getClientProperty("highlightRows");
//                int index = selectedRows.indexOf(row);
//                if (index != -1)
//                    selectedRows.remove(index);
//                else
//                    selectedRows.add(row);
//                team_table.repaint();
//                //cul_table.setForeground(Color.black);
//}
    }//GEN-LAST:event_team_tableMousePressed

    private void part_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_part_tableMousePressed
        // TODO add your handling code here:
//        if (evt.getClickCount() ==2){
//             
//                  
//                int row = part_table.rowAtPoint(evt.getPoint());
//                if (row == -1)
//                    return;
//                List<Integer> selectedRows = (List<Integer>) part_table
//                        .getClientProperty("highlightRows");
//                int index = selectedRows.indexOf(row);
//                if (index != -1)
//                    selectedRows.remove(index);
//                else
//                    selectedRows.add(row);
//                part_table.repaint();
//                //cul_table.setForeground(Color.black);
//}
    }//GEN-LAST:event_part_tableMousePressed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
      //  Controller_Mess.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void culDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_culDoneActionPerformed
        // TODO add your handling code here:
        int row = cul_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) cul_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1){
                }   //selectedRows.remove(index);
                else
                    selectedRows.add(row);
                cul_table.repaint();
    }//GEN-LAST:event_culDoneActionPerformed

    private void culRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_culRollbackActionPerformed
        // TODO add your handling code here:
        int row = cul_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) cul_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1)
                 selectedRows.remove(index);
               
                cul_table.repaint();
    }//GEN-LAST:event_culRollbackActionPerformed

    private void catDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catDoneActionPerformed
        // TODO add your handling code here:
         int row = cat_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) cat_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1){
                }   //selectedRows.remove(index);
                else
                    selectedRows.add(row);
                cat_table.repaint();
    }//GEN-LAST:event_catDoneActionPerformed

    private void catRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catRollbackActionPerformed
        // TODO add your handling code here:
        int row = cat_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) cat_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1)
                 selectedRows.remove(index);
               
                cat_table.repaint();
    }//GEN-LAST:event_catRollbackActionPerformed

    private void partDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partDoneActionPerformed
        // TODO add your handling code here:
         int row = part_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) part_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1){
                }   //selectedRows.remove(index);
                else
                    selectedRows.add(row);
                part_table.repaint();
    }//GEN-LAST:event_partDoneActionPerformed

    private void partRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partRollbackActionPerformed
        // TODO add your handling code here:
        int row = part_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) part_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1)
                 selectedRows.remove(index);
               
                part_table.repaint();
    }//GEN-LAST:event_partRollbackActionPerformed

    private void teamDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamDoneActionPerformed
        // TODO add your handling code here:
        int row = team_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) team_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1){
                }   //selectedRows.remove(index);
                else
                    selectedRows.add(row);
                team_table.repaint();
    }//GEN-LAST:event_teamDoneActionPerformed

    private void teamRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamRollbackActionPerformed
        // TODO add your handling code here:
        int row = team_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) team_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1)
                 selectedRows.remove(index);
               
                team_table.repaint();
    }//GEN-LAST:event_teamRollbackActionPerformed

    private void criDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criDoneActionPerformed
        // TODO add your handling code here:
        int row = criteria_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) criteria_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1){
                }   //selectedRows.remove(index);
                else
                    selectedRows.add(row);
                criteria_table.repaint();
    }//GEN-LAST:event_criDoneActionPerformed

    private void criRollbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criRollbackActionPerformed
        // TODO add your handling code here:
        int row = criteria_table.getSelectedRow();
                if (row == -1)
                    return;
                List<Integer> selectedRows = (List<Integer>) criteria_table
                        .getClientProperty("highlightRows");
                int index = selectedRows.indexOf(row);
                if (index != -1)
                 selectedRows.remove(index);
               
                criteria_table.repaint();
    }//GEN-LAST:event_criRollbackActionPerformed

    private void criSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criSetActionPerformed
        // TODO add your handling code here:
       universalcriID = "1";
    }//GEN-LAST:event_criSetActionPerformed

    private void culSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_culSetActionPerformed
        // TODO add your handling code here:
        _getAllData();
        universalID = "";
    }//GEN-LAST:event_culSetActionPerformed

    private void catSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catSetActionPerformed
        // TODO add your handling code here:
        _displayCriteria();
        universalID = "1";
    }//GEN-LAST:event_catSetActionPerformed

    private void partSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partSetActionPerformed
        // TODO add your handling code here:
        setPart();
    }//GEN-LAST:event_partSetActionPerformed

    private void teamSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamSetActionPerformed
        // TODO add your handling code here:
        setTeam();
    }//GEN-LAST:event_teamSetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
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
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                new Controller().setVisible(true);
            }
        });
                    
    }
    
    private class LastProcess extends WindowAdapter {
        @Override
   public void windowClosing(WindowEvent e){
       int dialog =  JOptionPane.showConfirmDialog(null,"Are your sure to close this window?","Message",JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
        dispose();
               
        }
       else if(dialog == JOptionPane.NO_OPTION) {}
 }
    }
    @Override
     public void actionPerformed(ActionEvent e) {
        try{
                  
                   List<message_getSet> sList1 = new ArrayList<>();
                   message s = new message();
                   sList1 = s.message_relay();
                   imp();
                   for(int i = 0 ;i<sList1.size();i++){
                       String acc = sList1.get(i).getAccid();
                       String bol = sList1.get(i).getBol();
                       
                       ImageIcon Red = new ImageIcon("computer-red.png");
                       ImageIcon Green = new ImageIcon("computer-green.png");
     if(i == 0){
        if(bol.equals("1")){J1Pic.setVisible(true);J1lbl.setVisible(true);
                            J1Pic.setIcon(Red);J1lbl.setText("Judge "+acc);}
                      else {J1Pic.setVisible(true);J1lbl.setVisible(true);
                            J1Pic.setIcon(Green);J1lbl.setText("Judge "+acc);}
                         
           }else if(i == 1){
        if(bol.equals("1")){J2Pic.setVisible(true);J2lbl.setVisible(true);
                            J2Pic.setIcon(Red);J2lbl.setText("Judge "+acc);}
                      else {J2Pic.setVisible(true);J2lbl.setVisible(true);
                            J2Pic.setIcon(Green);J2lbl.setText("Judge "+acc);}
           }else if(i == 2){
        if(bol.equals("1")){J3Pic.setVisible(true);J3lbl.setVisible(true);
                            J3Pic.setIcon(Red);J3lbl.setText("Judge "+acc);}
                      else {J3Pic.setVisible(true);J3lbl.setVisible(true);
                            J3Pic.setIcon(Green);J3lbl.setText("Judge "+acc);}
                         
           }else if(i == 3){
        if(bol.equals("1")){J4Pic.setVisible(true);J4lbl.setVisible(true);
                            J4Pic.setIcon(Red);J4lbl.setText("Judge "+acc);}
                      else {J4Pic.setVisible(true);J4lbl.setVisible(true);
                            J4Pic.setIcon(Green);J4lbl.setText("Judge "+acc);}
                         
           }else if(i == 4){
        if(bol.equals("1")){J5Pic.setVisible(true);J5lbl.setVisible(true);
                            J5Pic.setIcon(Red);J5lbl.setText("Judge "+acc);}
                      else {J5Pic.setVisible(true);J5lbl.setVisible(true);
                            J5Pic.setIcon(Green);J5lbl.setText("Judge "+acc);}
           }else if(i == 5){
        if(bol.equals("1")){J6Pic.setVisible(true);J6lbl.setVisible(true);
                            J6Pic.setIcon(Red);J6lbl.setText("Judge "+acc);}
                      else {J6Pic.setVisible(true);J6lbl.setVisible(true);
                            J6Pic.setIcon(Green);J6lbl.setText("Judge "+acc);}
           }else if(i == 6){
        if(bol.equals("1")){J7Pic.setVisible(true);J7lbl.setVisible(true);
                           J7Pic.setIcon(Red);J7lbl.setText("Judge "+acc);}
                      else {J7Pic.setVisible(true);J7lbl.setVisible(true);
                            J7Pic.setIcon(Green);J7lbl.setText("Judge "+acc);}
           }else if(i == 7){
        if(bol.equals("1")){J8Pic.setVisible(true);J8lbl.setVisible(true);
                            J8Pic.setIcon(Red);J8lbl.setText("Judge "+acc);}
                      else {J8Pic.setVisible(true);J8lbl.setVisible(true);
                            J8Pic.setIcon(Green);J8lbl.setText("Judge "+acc);}
           }else if(i == 8){
        if(bol.equals("1")){J9Pic.setVisible(true);J9lbl.setVisible(true);
                            J9Pic.setIcon(Red);J9lbl.setText("Judge "+acc);}
                      else {J9Pic.setVisible(true);J9lbl.setVisible(true);
                            J9Pic.setIcon(Green);J9lbl.setText("Judge "+acc);}
           }else if(i == 9){
        if(bol.equals("1")){J10Pic.setVisible(true);J10lbl.setVisible(true);
                            J10Pic.setIcon(Red);J10lbl.setText("Judge "+acc);}
                      else {J10Pic.setVisible(true);J10lbl.setVisible(true);
                            J10Pic.setIcon(Green);J10lbl.setText("Judge "+acc);}
          }else if(i == 10){
        if(bol.equals("1")){J11Pic.setVisible(true);J11lbl.setVisible(true);
                            J11Pic.setIcon(Red);J11lbl.setText("Judge "+acc);}
                      else {J11Pic.setVisible(true);J11lbl.setVisible(true);
                            J11Pic.setIcon(Green);J11lbl.setText("Judge "+acc);}
          }else if(i == 11){
        if(bol.equals("1")){J12Pic.setVisible(true);J12lbl.setVisible(true);
                            J12Pic.setIcon(Red);J12lbl.setText("Judge "+acc);}
                      else {J12Pic.setVisible(true);J12lbl.setVisible(true);
                            J12Pic.setIcon(Green);J12lbl.setText("Judge "+acc);}
         }else if(i == 12){
        if(bol.equals("1")){J13Pic.setVisible(true);J13lbl.setVisible(true);
                            J13Pic.setIcon(Red);J13lbl.setText("Judge "+acc);}
                      else {J13Pic.setVisible(true);J13lbl.setVisible(true);
                            J13Pic.setIcon(Green);J13lbl.setText("Judge "+acc);}
         }else if(i == 13){
        if(bol.equals("1")){J14Pic.setVisible(true);J14lbl.setVisible(true);
                            J14Pic.setIcon(Red);J14lbl.setText("Judge "+acc);}
                      else {J14Pic.setVisible(true);J14lbl.setVisible(true);
                            J14Pic.setIcon(Green);J14lbl.setText("Judge "+acc);}
         }else if(i == 14){
        if(bol.equals("1")){J15Pic.setVisible(true);J15lbl.setVisible(true);
                            J15Pic.setIcon(Red);J15lbl.setText("Judge "+acc);}
                      else {J15Pic.setVisible(true);J15lbl.setVisible(true);
                            J15Pic.setIcon(Green);J15lbl.setText("Judge "+acc);}
         }else if(i == 15){
        if(bol.equals("1")){J16Pic.setVisible(true);J16lbl.setVisible(true);
                            J16Pic.setIcon(Red);J16lbl.setText("Judge "+acc);}
                      else {J16Pic.setVisible(true);J16lbl.setVisible(true);
                            J16Pic.setIcon(Green);J16lbl.setText("Judge "+acc);}
         }else if(i == 16){
        if(bol.equals("1")){J17Pic.setVisible(true);J17lbl.setVisible(true);
                            J17Pic.setIcon(Red);J17lbl.setText("Judge "+acc);}
                      else {J17Pic.setVisible(true);J17lbl.setVisible(true);
                            J17Pic.setIcon(Green);J17lbl.setText("Judge "+acc);}
         }else if(i == 17){
        if(bol.equals("1")){J18Pic.setVisible(true);J18lbl.setVisible(true);
                            J18Pic.setIcon(Red);J18lbl.setText("Judge "+acc);}
                      else {J18Pic.setVisible(true);J18lbl.setVisible(true);
                            J18Pic.setIcon(Green);J18lbl.setText("Judge "+acc);}
         }else if(i == 18){
        if(bol.equals("1")){J19Pic.setVisible(true);J19lbl.setVisible(true);
                            J19Pic.setIcon(Red);J19lbl.setText("Judge "+acc);}
                      else {J19Pic.setVisible(true);J19lbl.setVisible(true);
                            J19Pic.setIcon(Green);J19lbl.setText("Judge "+acc);}
         }else if(i == 19){
        if(bol.equals("1")){J20Pic.setVisible(true);J20lbl.setVisible(true);
                            J20Pic.setIcon(Red);J20lbl.setText("Judge "+acc);}
                      else {J20Pic.setVisible(true);J20lbl.setVisible(true);
                            J20Pic.setIcon(Green);J20lbl.setText("Judge "+acc);}
         }else if(i == 20){
        if(bol.equals("1")){J21Pic.setVisible(true);J21lbl.setVisible(true);
                            J21Pic.setIcon(Red);J21lbl.setText("Judge "+acc);}
                      else {J21Pic.setVisible(true);J21lbl.setVisible(true);
                            J21Pic.setIcon(Green);J21lbl.setText("Judge "+acc);}
         }else if(i == 21){
        if(bol.equals("1")){J22Pic.setVisible(true);J22lbl.setVisible(true);
                            J22Pic.setIcon(Red);J22lbl.setText("Judge "+acc);}
                      else {J22Pic.setVisible(true);J22lbl.setVisible(true);
                            J22Pic.setIcon(Green);J22lbl.setText("Judge "+acc);}
         }else if(i == 22){
        if(bol.equals("1")){J23Pic.setVisible(true);J23lbl.setVisible(true);
                            J23Pic.setIcon(Red);J23lbl.setText("Judge "+acc);}
                      else {J23Pic.setVisible(true);J23lbl.setVisible(true);
                            J23Pic.setIcon(Green);J23lbl.setText("Judge "+acc);}
         }else if(i == 23){
        if(bol.equals("1")){J24Pic.setVisible(true);J24lbl.setVisible(true);
                            J24Pic.setIcon(Red);J24lbl.setText("Judge "+acc);}
                      else {J24Pic.setVisible(true);J24lbl.setVisible(true);
                            J24Pic.setIcon(Green);J24lbl.setText("Judge "+acc);}
         }else if(i == 24){
        if(bol.equals("1")){J25Pic.setVisible(true);J25lbl.setVisible(true);
                            J25Pic.setIcon(Red);J25lbl.setText("Judge "+acc);}
                      else {J25Pic.setVisible(true);J25lbl.setVisible(true);
                            J25Pic.setIcon(Green);J25lbl.setText("Judge "+acc);}
         }else if(i == 25){
        if(bol.equals("1")){J26Pic.setVisible(true);J26lbl.setVisible(true);
                            J26Pic.setIcon(Red);J26lbl.setText("Judge "+acc);}
                      else {J26Pic.setVisible(true);J26lbl.setVisible(true);
                            J26Pic.setIcon(Green);J26lbl.setText("Judge "+acc);}
         }else if(i == 26){
        if(bol.equals("1")){J27Pic.setVisible(true);J27lbl.setVisible(true);
                            J27Pic.setIcon(Red);J27lbl.setText("Judge "+acc);}
                      else {J27Pic.setVisible(true);J27lbl.setVisible(true);
                            J27Pic.setIcon(Green);J27lbl.setText("Judge "+acc);}
         }else if(i == 27){
        if(bol.equals("1")){J28Pic.setVisible(true);J28lbl.setVisible(true);
                            J28Pic.setIcon(Red);J28lbl.setText("Judge "+acc);}
                      else {J28Pic.setVisible(true);J28lbl.setVisible(true);
                            J28Pic.setIcon(Green);J28lbl.setText("Judge "+acc);}
         }else if(i == 28){
        if(bol.equals("1")){J29Pic.setVisible(true);J29lbl.setVisible(true);
                            J29Pic.setIcon(Red);J29lbl.setText("Judge "+acc);}
                      else {J29Pic.setVisible(true);J29lbl.setVisible(true);
                            J29Pic.setIcon(Green);J29lbl.setText("Judge "+acc);}
         }else if(i == 29){
        if(bol.equals("1")){J30Pic.setVisible(true);J30lbl.setVisible(true);
                            J30Pic.setIcon(Red);J30lbl.setText("Judge "+acc);}
                      else {J30Pic.setVisible(true);J30lbl.setVisible(true);
                            J30Pic.setIcon(Green);J30lbl.setText("Judge "+acc);}
         }else if(i == 30){
        if(bol.equals("1")){J31Pic.setVisible(true);J31lbl.setVisible(true);
                            J31Pic.setIcon(Red);J31lbl.setText("Judge "+acc);}
                      else {J31Pic.setVisible(true);J31lbl.setVisible(true);
                            J31Pic.setIcon(Green);J31lbl.setText("Judge "+acc);}
         }else if(i == 31){
        if(bol.equals("1")){J32Pic.setVisible(true);J32lbl.setVisible(true);
                            J32Pic.setIcon(Red);J32lbl.setText("Judge "+acc);}
                      else {J32Pic.setVisible(true);J32lbl.setVisible(true);
                            J32Pic.setIcon(Green);J32lbl.setText("Judge "+acc);}
         }else if(i == 32){
        if(bol.equals("1")){J33Pic.setVisible(true);J33lbl.setVisible(true);
                            J33Pic.setIcon(Red);J33lbl.setText("Judge "+acc);}
                      else {J33Pic.setVisible(true);J33lbl.setVisible(true);
                            J33Pic.setIcon(Green);J33lbl.setText("Judge "+acc);}
         }else if(i == 33){
        if(bol.equals("1")){J34Pic.setVisible(true);J34lbl.setVisible(true);
                            J34Pic.setIcon(Red);J34lbl.setText("Judge "+acc);}
                      else {J34Pic.setVisible(true);J34lbl.setVisible(true);
                            J34Pic.setIcon(Green);J34lbl.setText("Judge "+acc);}
         }else if(i == 34){
        if(bol.equals("1")){J35Pic.setVisible(true);J35lbl.setVisible(true);
                            J35Pic.setIcon(Red);J35lbl.setText("Judge "+acc);}
                      else {J35Pic.setVisible(true);J35lbl.setVisible(true);
                            J35Pic.setIcon(Green);J35lbl.setText("Judge "+acc);}
         }else if(i == 35){
        if(bol.equals("1")){J36Pic.setVisible(true);J36lbl.setVisible(true);
                            J36Pic.setIcon(Red);J36lbl.setText("Judge "+acc);}
                      else {J36Pic.setVisible(true);J36lbl.setVisible(true);
                            J36Pic.setIcon(Green);J36lbl.setText("Judge "+acc);}
        }else if(i == 36){
        if(bol.equals("1")){J37Pic.setVisible(true);J37lbl.setVisible(true);
                            J37Pic.setIcon(Red);J37lbl.setText("Judge "+acc);}
                      else {J37Pic.setVisible(true);J37lbl.setVisible(true);
                            J37Pic.setIcon(Green);J37lbl.setText("Judge "+acc);}
         }else if(i == 37){
        if(bol.equals("1")){J38Pic.setVisible(true);J38lbl.setVisible(true);
                            J38Pic.setIcon(Red);J38lbl.setText("Judge "+acc);}
                      else {J38Pic.setVisible(true);J38lbl.setVisible(true);
                            J38Pic.setIcon(Green);J38lbl.setText("Judge "+acc);}
         }else if(i == 38){
        if(bol.equals("1")){J39Pic.setVisible(true);J39lbl.setVisible(true);
                            J39Pic.setIcon(Red);J39lbl.setText("Judge "+acc);}
                      else {J39Pic.setVisible(true);J39lbl.setVisible(true);
                            J39Pic.setIcon(Green);J39lbl.setText("Judge "+acc);}
         }else if(i == 39){
        if(bol.equals("1")){J40Pic.setVisible(true);J40lbl.setVisible(true);
                            J40Pic.setIcon(Red);J40lbl.setText("Judge "+acc);}
                      else {J39Pic.setVisible(true);J40lbl.setVisible(true);
                            J39Pic.setIcon(Green);J40lbl.setText("Judge "+acc);}
                          }
                       
                   }
                   
                    }catch(Exception ex){
                        System.out.print(ex);
                    }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Clear;
    private javax.swing.JLabel J10Pic;
    private javax.swing.JLabel J10lbl;
    private javax.swing.JLabel J11Pic;
    private javax.swing.JLabel J11lbl;
    private javax.swing.JLabel J12Pic;
    private javax.swing.JLabel J12lbl;
    private javax.swing.JLabel J13Pic;
    private javax.swing.JLabel J13lbl;
    private javax.swing.JLabel J14Pic;
    private javax.swing.JLabel J14lbl;
    private javax.swing.JLabel J15Pic;
    private javax.swing.JLabel J15lbl;
    private javax.swing.JLabel J16Pic;
    private javax.swing.JLabel J16lbl;
    private javax.swing.JLabel J17Pic;
    private javax.swing.JLabel J17lbl;
    private javax.swing.JLabel J18Pic;
    private javax.swing.JLabel J18lbl;
    private javax.swing.JLabel J19Pic;
    private javax.swing.JLabel J19lbl;
    private javax.swing.JLabel J1Pic;
    private javax.swing.JLabel J1lbl;
    private javax.swing.JLabel J20Pic;
    private javax.swing.JLabel J20lbl;
    private javax.swing.JLabel J21Pic;
    private javax.swing.JLabel J21lbl;
    private javax.swing.JLabel J22Pic;
    private javax.swing.JLabel J22lbl;
    private javax.swing.JLabel J23Pic;
    private javax.swing.JLabel J23lbl;
    private javax.swing.JLabel J24Pic;
    private javax.swing.JLabel J24lbl;
    private javax.swing.JLabel J25Pic;
    private javax.swing.JLabel J25lbl;
    private javax.swing.JLabel J26Pic;
    private javax.swing.JLabel J26lbl;
    private javax.swing.JLabel J27Pic;
    private javax.swing.JLabel J27lbl;
    private javax.swing.JLabel J28Pic;
    private javax.swing.JLabel J28lbl;
    private javax.swing.JLabel J29Pic;
    private javax.swing.JLabel J29lbl;
    private javax.swing.JLabel J2Pic;
    private javax.swing.JLabel J2lbl;
    private javax.swing.JLabel J30Pic;
    private javax.swing.JLabel J30lbl;
    private javax.swing.JLabel J31Pic;
    private javax.swing.JLabel J31lbl;
    private javax.swing.JLabel J32Pic;
    private javax.swing.JLabel J32lbl;
    private javax.swing.JLabel J33Pic;
    private javax.swing.JLabel J33lbl;
    private javax.swing.JLabel J34Pic;
    private javax.swing.JLabel J34lbl;
    private javax.swing.JLabel J35Pic;
    private javax.swing.JLabel J35lbl;
    private javax.swing.JLabel J36Pic;
    private javax.swing.JLabel J36lbl;
    private javax.swing.JLabel J37Pic;
    private javax.swing.JLabel J37lbl;
    private javax.swing.JLabel J38Pic;
    private javax.swing.JLabel J38lbl;
    private javax.swing.JLabel J39Pic;
    private javax.swing.JLabel J39lbl;
    private javax.swing.JLabel J3Pic;
    private javax.swing.JLabel J3lbl;
    private javax.swing.JLabel J40Pic;
    private javax.swing.JLabel J40lbl;
    private javax.swing.JLabel J4Pic;
    private javax.swing.JLabel J4lbl;
    private javax.swing.JLabel J5Pic;
    private javax.swing.JLabel J5lbl;
    private javax.swing.JLabel J6Pic;
    private javax.swing.JLabel J6lbl;
    private javax.swing.JLabel J7Pic;
    private javax.swing.JLabel J7lbl;
    private javax.swing.JLabel J8Pic;
    private javax.swing.JLabel J8lbl;
    private javax.swing.JLabel J9Pic;
    private javax.swing.JLabel J9lbl;
    public static javax.swing.JLabel TImage8;
    private javax.swing.JMenuItem catDone;
    private javax.swing.JPopupMenu catPOP;
    private javax.swing.JMenuItem catRollback;
    private javax.swing.JMenuItem catSet;
    private javax.swing.JTable cat_table;
    private javax.swing.JMenuItem criDone;
    private javax.swing.JPopupMenu criPOP;
    private javax.swing.JMenuItem criRollback;
    private javax.swing.JMenuItem criSet;
    private javax.swing.JTable criteria_table;
    private javax.swing.JMenuItem culDone;
    private javax.swing.JPopupMenu culPOP;
    private javax.swing.JMenuItem culRollback;
    private javax.swing.JMenuItem culSet;
    public javax.swing.JTable cul_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JMenuItem partDone;
    private javax.swing.JPopupMenu partPOP;
    private javax.swing.JMenuItem partRollback;
    private javax.swing.JMenuItem partSet;
    private javax.swing.JTable part_table;
    private javax.swing.JMenuItem teamDone;
    private javax.swing.JPopupMenu teamPOP;
    private javax.swing.JMenuItem teamRollback;
    private javax.swing.JMenuItem teamSet;
    private javax.swing.JTable team_table;
    private javax.swing.JPopupMenu textarePOP;
    public static javax.swing.JLabel v_Ima;
    // End of variables declaration//GEN-END:variables
}
