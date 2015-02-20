/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Graphical_User_Interface.Administrator_Module;


import Tabulation.Connections.EncryptAndDecrypt;
import Tabulation.Interface.Implementation.Update_Cultural_Event;
import Tabulation.Interface.Update_Cul_Event;
import Tabulation.Methods.Select_Commands.*;
import Tabulation.getters_setters.*;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Neil
 */
public class administrator extends javax.swing.JDialog implements ActionListener{
Timer clockTimer;
int row;

    public administrator() {
        super(null, java.awt.Dialog.ModalityType.MODELESS);
        initComponents();
       
        queryCultural_EventName();
        date.setText(getDate());
        clockTimer = new Timer(1000,this );
        clockTimer.start();
        
        this.setLocationRelativeTo(null);
        Report();
        addWindowListener(new administrator.LastProcess());
        //Parti_ID.setVisible(false);
    }
    public void getReportCRIandCAT(){
        DefaultTableModel model=(DefaultTableModel)jTable4.getModel();
        DefaultTableModel model1=(DefaultTableModel)jTable5.getModel();
        DefaultTableModel model2=(DefaultTableModel)jTable6.getModel();
        try{
        List<Cultural_Event> Slist= new ArrayList<>();
        List<Event_Category> sList = new ArrayList<Event_Category>();
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_Reports_Cultural QRP = new Query_Reports_Cultural();
       Slist = QRP.first((String)jComboBox1.getSelectedItem(), (String)jComboBox4.getSelectedItem(),(String)jComboBox5.getSelectedItem());
       sList = QRP.CategoryDetailsforsearch((String)jComboBox1.getSelectedItem());
       CList = QRP.CriteriaDetailsforsearch1((String)jComboBox1.getSelectedItem());
       model.setRowCount(0);
       model1.setRowCount(0);
       model2.setRowCount(0);
       jComboBox2.removeAllItems();jComboBox3.removeAllItems();
       for(int i = 0;i<Slist.size();i++){
            model.addRow(new Object[]{});
            jTable4.setValueAt(Slist.get(i).getCul_Percentage(), i,0);
            jTable4.setValueAt(Slist.get(i).getCul_Name(), i, 1);
       }
       for (int i=0;i<sList.size();i++){
              String cname = sList.get(i).getCategory_Name();
              jComboBox2.addItem(cname);
            }
            
            for(int j=0;j<CList.size();j++){
               String criname = CList.get(j).getCr_Description();
               jComboBox3.addItem(criname);
            }
       }catch(Exception e){
           System.out.print(e);
       }
    }
    public String getDate()
   {
      DateFormat dateFormat = new SimpleDateFormat( "MMMMMMMMM d, yyyy" );
      Calendar calendar = Calendar.getInstance();
      return dateFormat.format( calendar.getTime() );
   }
    private void Get_data()
{
    try{
    java.sql.Connection con;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    ResultSet rs1;ResultSet rs2;ResultSet rs3;
      con=(java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql1= "select * from rpt_overall_cultural";
        String sql2 = "SELECT * FROM rpt_overall_sports";
        String sql3 = "SELECT a.CODE,a.Team_Name,(a.TOTAL + b.TOTAL) Score "
                + " FROM rpt_overall_cultural a JOIN rpt_overall_sports b ON a.code = b.code group by a.code ASC";

          pst1=con.prepareStatement(sql1);
          pst2=con.prepareStatement(sql2);
          pst3=con.prepareStatement(sql3);
          rs1= pst1.executeQuery();
          rs2= pst2.executeQuery();
          rs3= pst3.executeQuery();
         jTable1.setModel(DbUtils.resultSetToTableModel(rs3));
         jTable2.setModel(DbUtils.resultSetToTableModel(rs1));
         jTable3.setModel(DbUtils.resultSetToTableModel(rs2));
         jTable3.setEditingRow(0);
         }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
         }
}
    
     public void Report(){
        try{
        List<Cultural_Event> sList1 = new ArrayList<>();
        List<Sport_Event> sList2 = new ArrayList<>();
        _Select_Cultural_Events qd = new _Select_Cultural_Events();
        sList1 = qd._report();
        sList2 = qd._report1();
        for (int i=0;i<sList1.size();i++){
             String Name = sList1.get(i).getCul_Name();
             jComboBox1.addItem(Name);
        }
        for (int i=0;i<sList2.size();i++){
             String Name = sList2.get(i).getSp_Name();
             jComboBox6.addItem(Name);
        }
        }catch(Exception e){
        
        }
    }
    
    public void INCREMENT()throws Exception{
       new INCREMENT().increment_Cultural();
       new INCREMENT().increment_Criteria();
        }
    public void queryCultural_EventName(){
        try{
        List<Cultural_Event> sList1 = new ArrayList<>();
        List<Event_Category> sList2 = new ArrayList<>();
        List<Sport_Event> sList = new ArrayList<>();   
        Query_SportDetails QSD =new Query_SportDetails();
        query_Event_Name p =new query_Event_Name();
        
        sList1= p.queryCultural_EventName();
        sList2 = p.Event_Category_Name();
        for (int i=0;i<sList1.size();i++){
           String Name = sList1.get(i).getCul_Name();
          
           //cb_culeve.addItem(Name);
        }
        for (int i=0;i<sList1.size();i++){
           String Name = sList1.get(i).getCul_Name();
           //List_MajorEvent.addItem(Name);
           
        }
        for(int i=0;i<sList2.size();i++){
            String Name = sList2.get(i).getCategory_Name();
          
        }
            sList = QSD.queryAccount_ID1();
            for (int i=0;i<sList.size();i++){
            String sports = sList.get(i).getSp_Name();
            
         //   cb_spo.addItem(sports);
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    public void queryTeam_Name()throws Exception{
        List<Team> sList1 = new ArrayList<>();
        query_Team_Name p =new query_Team_Name();
        
        sList1= p.qeury_team_name();
        for (int i=0;i<sList1.size();i++){
           String TName = sList1.get(i).getTeam_Name();
         
        }
    }
    
   
    
    public void Event(){
        try {
            CardLayout cl = (CardLayout) main.getLayout();
            cl.show(main,"card7");  
            INCREMENT();
            
            search_Event.setText("Enter Event ID OR Event Name");
            DefaultTableModel model1=(DefaultTableModel)EventD.getModel();
            DefaultTableModel model2=(DefaultTableModel)critable.getModel();
            DefaultTableModel model3=(DefaultTableModel)ECategory.getModel();
            model1.setRowCount(0);
            model2.setRowCount(0);
            model3.setRowCount(0);
            
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void cultural_update(){
        Cultural_Event_Window as = new Cultural_Event_Window();
        DefaultTableModel model=(DefaultTableModel)EventD.getModel();
        int row1 = EventD.getSelectedRow();
       String TableClick = (EventD.getModel().getValueAt(row1, 0).toString());
       
        List<Cultural_Event> sList = new ArrayList<>();   
        query_ListOFEvent QLP =new query_ListOFEvent();
        int id = 0;
        String cname = null,cpercent = null,cstat = null,ctype = null,csched = null,cjudge = null;
        Date cdate = null;
        try {
            sList = QLP.getCulturalforUpdate(TableClick);
            for (int i=0;i<sList.size();i++){
            id = sList.get(i).getCul_ID();
            cname = (sList.get(i).getCul_Name());
            cpercent = (sList.get(i).getCul_Percentage());
            cstat = (sList.get(i).getCul_Status());
            cdate = (sList.get(i).getCuldate());
            ctype = (sList.get(i).getCul_Type());
            csched = (sList.get(i).getCul_Sched());
            cjudge = (sList.get(i).getCul_Type_Judging());
            
            }
        String Date = csched.toString();
        String a[] = Date.split(" ");    
        String t = a[2];
        String[] t1 = t.split(":");
        String p1 = t1[0];
        String p2 = t1[1];
        String p3 = a[3];
         
        
        
        
        as.ID.setText(Integer.toString(id));
        as.EName.setText(cname);
        as.EPercent.setSelectedItem(cpercent);
        as.date.setDate(cdate);
        as.Hour.setText(p1);
        as.Min.setText(p2);
        as.AMPM.setSelectedItem(p3);
        as.save.setText("Update");
        if(ctype.equals("Major Event")){
            as.majorevent.setSelected(true);
        }else{as.minorevent.setSelected(true);
        }
        if(cstat.equals("Active")){
            as.EActive.setSelected(true);
        }else{as.EInActive.setSelected(true);}
        if(cjudge.equals("INDIVIDUAL")){
            as.EIndiv.setSelected(true);
        }else{as.EByte.setSelected(true);}
        CardLayout cl = (CardLayout) as.main.getLayout();
        cl.show(as.main,"card3");
        as.show();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void delete_Cultural(){
        int row1 = EventD.getSelectedRow();
       String TableClick = (EventD.getModel().getValueAt(row1, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Cultural(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)EventD.getModel();
       model.setRowCount(0);
       search_Event.setText("Enter Event ID OR Event Name");
    }
    
    public void update_SPort(){
        Sports_Window SW =  new Sports_Window();
        DefaultTableModel model=(DefaultTableModel)Tsport1.getModel();
         int row1 = Tsport1.getSelectedRow();
       String TableClick = (Tsport1.getModel().getValueAt(row1, 0).toString());
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Sport_Event> sList = new ArrayList<>();   
        Query_SportDetails QSD =new Query_SportDetails();
        int id = 0;
        String name = null,per = null,stat = null,type = null,Rounds = null,Sched = null;
        Date date = null;
        try {
            sList = QSD.querytoupdate(TableClick);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            id = sList.get(i).getSp_ID();
            name = sList.get(i).getSp_Name();
            per = sList.get(i).getSp_Percentage();
            stat = sList.get(i).getSp_Status();
            type = sList.get(i).getSp_Type();
            Sched = sList.get(i).getSp_Sched();
            }
            
            String Date = Sched.toString();
            String a[] = Date.split(" ");
            String ZEH = ""+a[0]+" "+a[1];
            String t = a[2];
            String[] t1 = t.split(":");
            String p1 = t1[0];
            String p2 = t1[1];
            String p3 = a[3];
            Date date1 = new SimpleDateFormat("MMMMMMM d,yyyy").parse(ZEH);
            
            SW.spID.setText(Integer.toString(id));
            SW.SPname.setText(name);
            SW.SPpercent.setSelectedItem(per);
            SW.SPstatus.setSelectedItem(stat);
            SW.SPtype.setSelectedItem(type);
            SW.date.setDate(date1);
            SW.Hour.setText(p1);
            SW.Min.setText(p2);
            SW.AMPM.setSelectedItem(p3);
            SW.jButton3.setText("Update");
            
            SW.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
   
    public void delete_Sport(){
        int row1 = Tsport1.getSelectedRow();
       String TableClick = (Tsport1.getModel().getValueAt(row1, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Sports(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)Tsport1.getModel();
       model.setRowCount(0);
       searcSP1.setText("Type ID OR Sports Name");
    }
    
    public void reset_rank(){
          try{
        
        DefaultTableModel model2=(DefaultTableModel)rank1.getModel();
        int row2 = rank1.getSelectedRow();
        String TableClick1 = (rank1.getModel().getValueAt(row2, 0).toString());
       
       char Char = TableClick1.charAt(0);
        String string = Character.toString(Char);
        switch (string) {
            case "4":            
                new reset_rank().reset_Cultural_Rank(TableClick1);
                JOptionPane.showMessageDialog(null,"Successfully Saved");
        break;
            case "5":
                new reset_rank().reset_Sport_Rank(TableClick1);
                JOptionPane.showMessageDialog(null,"Successfully Saved");
                  break;
        }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,e);
}
    }
    
    public void update_user(){
        Users_Account_Window useraccounts = new Users_Account_Window();
        DefaultTableModel model=(DefaultTableModel)UserTable1.getModel();
       int row1 = UserTable1.getSelectedRow();
       String TableClick = (UserTable1.getModel().getValueAt(row1, 0).toString());
       if (model.getRowCount() != 0){
            model.setRowCount(0);
            TImage4.setIcon(null);
            TImage4.setText("No Image Available");
             SUser1.setText("Type User ID Or User Name");
        }
      
        List<Accounts> sList = new ArrayList<>();   
        Query_Account_ID QLP =new Query_Account_ID();
        
        try {
            BufferedImage img = null;
            
            sList = QLP.query_Whole_accountsdetails_for_update(TableClick);
            int ID = 0;
            Image pic = null;
            String FName = null,MName = null,LName = null,Gender = null,Add = null,Contact = null,Stats = null,Type = null,user = null,pass = null,path = null;
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
             ID = sList.get(i).getAcc_ID();
            FName = sList.get(i).getAcc_FName();
            MName = sList.get(i).getAcc_MName();
            LName = sList.get(i).getAcc_LName();
            Gender = sList.get(i).getAcc_Gender();
            Add = sList.get(i).getAcc_Address();
            Contact = sList.get(i).getAcc_ContactNumber();
            Stats = sList.get(i).getStatus();
            Type =sList.get(i).getType();
            user = sList.get(i).getAcc_Username();
            pass = sList.get(i).getAcc_Password();
            path = sList.get(i).getPath();
            pic = sList.get(i).getPicture();
            }
            EncryptAndDecrypt encrypter = new EncryptAndDecrypt("");
             String encrypted = encrypter.decrypt(pass);
            
            
            Image dimg = pic.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon image =new ImageIcon(dimg);
            
            useraccounts.ID.setText(Integer.toString(ID));
            useraccounts.aFName.setText(FName);
            useraccounts.aMName.setText(MName);
            useraccounts.aLName.setText(LName);
            useraccounts.aaddress.setText(Add);
            useraccounts.anum.setText(Contact);        
            useraccounts.astatus.setSelectedItem(Stats);        
            useraccounts.atype.setSelectedItem(Type);        
            useraccounts.auser.setText(user);
            useraccounts.apass.setText(encrypted);
            useraccounts.Udirectory.setText(path);
            useraccounts.UImage.setText("");
            useraccounts.UImage.setIcon(image);
            useraccounts.jButton3.setText("Update");
            if(Gender.equals("Male")){
                useraccounts.amale.setSelected(true);
            }else {useraccounts.afemale.setSelected(true);}
            useraccounts.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void delete_user(){
        int row = UserTable1.getSelectedRow();
       String TableClick = (UserTable1.getModel().getValueAt(row, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_User(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)UserTable1.getModel();
       model.setRowCount(0);
       SUser1.setText("Type User ID Or User Name");
    }
    
    public void update_Team(){
        Team_Window TW = new Team_Window();
         DefaultTableModel model=(DefaultTableModel)teamtable1.getModel();
        int row1 = teamtable1.getSelectedRow();
       String TableClick = (teamtable1.getModel().getValueAt(row1, 0).toString());
       
       if (model.getRowCount() != 0){
            model.setRowCount(0);
            TImage7.setIcon(null);
            TImage7.setText("No Image Available");
            teamsearch1.setText("Team ID OR Team Name");
        }
        List<Team> sList = new ArrayList<>();
        query_Team_Name QTN =new query_Team_Name();
        query_participant_details QPD = new query_participant_details();
        
        int ID = 0;
        String Tname = null,Tnumber = null,logo,path = null,Status = null;
        Image image = null;
        
        try {
            sList = QTN.query_whole_team_details_ForUpdate(TableClick);
            for (int i=0;i<sList.size();i++){
            
            ID = sList.get(i).getTeam_ID();
            Tname = sList.get(i).getTeam_Name();
            Tnumber = sList.get(i).getTeam_Number();
            path = sList.get(i).getTeam_Path();
            image = sList.get(i).getTeam_Pic();
            Status = sList.get(i).getTeam_Status();
            }
            
            Image dimg = image.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(dimg);
            
            TW.hideid.setText(Integer.toString(ID));
            TW.Tname.setText(Tname);
            TW.Tnumber.setText(Tnumber);
            TW.Tstatus.setSelectedItem(Status);
            TW.TImage.setText("");
            TW.TImage.setIcon(img);
            TW.Udirectory.setText(path);
            TW.jButton2.setText("Update");
            
            TW.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void delete_Team(){
        int row1 = teamtable1.getSelectedRow();
       String TableClick = (teamtable1.getModel().getValueAt(row1, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Team(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)teamtable1.getModel();
       model.setRowCount(0);
       teamsearch1.setText("Team ID OR Team Name");
    }
    
    public void update_Participant(){
        Participants_Window Partiwindow = new Participants_Window();
        DefaultTableModel model=(DefaultTableModel)parttable.getModel();
        DefaultTableModel model1=(DefaultTableModel)assigned.getModel();
       int row1 = parttable.getSelectedRow();
       String TableClick = (parttable.getModel().getValueAt(row1, 0).toString());
       
        
       if (model.getRowCount() != 0 && model1.getRowCount() != 0){
            model.setRowCount(0);
            model1.setRowCount(0);
            TImage6.setIcon(null);
            TImage6.setText("No Image Available");
            partsearch.setText("Participant ID OR Participant Name");
        }
        List<Participant> PList = new ArrayList<>(); 
        query_participant_details QPD = new query_participant_details();
        int ID = 0;
        String FName = null,MName = null,LName = null,Gender = null,Add = null,Contact = null,Status = null,path = null,Team = null;
        Image imag = null;
        try {
            PList = QPD.qeury_PartUpdate(TableClick);
            for (int i=0;i<PList.size();i++){
            
           ID =  PList.get(i).getId();
           FName =  PList.get(i).getFname();
           MName =  PList.get(i).getMname();
           LName =  PList.get(i).getLname();
           Gender = PList.get(i).getGender();
           Add =  PList.get(i).getAddress();
           Contact =  PList.get(i).getContactNum();
           Status = PList.get(i).getStatus();
           Team = PList.get(i).getTName();
           path =  PList.get(i).getPath();
           imag = PList.get(i).getPicture();
            }
            
            Image dimg = imag.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
           
            
            Partiwindow.Parti_ID.setText(Integer.toString(ID));
            Partiwindow.Pfname.setText(FName);
            Partiwindow.Pmname.setText(MName);
            Partiwindow.Plname.setText(LName);
            Partiwindow.PStatus.setSelectedItem(Status);
            Partiwindow.Paddress.setText(Add);
            Partiwindow.Pcontact.setText(Contact);
            Partiwindow.PTeam.setSelectedItem(Team);
            Partiwindow.Udirectory.setText(path);
            Partiwindow.PImage.setText("");
            Partiwindow.PImage.setIcon(ico);
            Partiwindow.jButton4.setText("Update");
            if(Gender.equals("Male")){
                Partiwindow.Pmale.setSelected(true);
            }else{Partiwindow.Pfemale.setSelected(true);}
            
            Partiwindow.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void delete_Participant(){
        int row1 = parttable.getSelectedRow();
       String TableClick = (parttable.getModel().getValueAt(row1, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Participant(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)parttable.getModel();
       model.setRowCount(0);
       partsearch.setText("Participant ID OR Participant Name");
    }
    
    public void delete_Overall(){
         int row1 = overall.getSelectedRow();
       String TableClick = (overall.getModel().getValueAt(row1, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Overall(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)overall.getModel();
       model.setRowCount(0);
       search_Event1.setText("Enter  ID OR Event Type");
    }
    public void update_Overall(){
        OverAll_Points_Window OPW = new OverAll_Points_Window();
         DefaultTableModel model=(DefaultTableModel)overall.getModel();
        int row1 = overall.getSelectedRow();
        String TableClick0 = (overall.getModel().getValueAt(row1, 0).toString());
        String TableClick1 = (overall.getModel().getValueAt(row1, 1).toString());
        String TableClick2 = (overall.getModel().getValueAt(row1, 2).toString());
        String TableClick3 = (overall.getModel().getValueAt(row1, 3).toString());
        
        OPW.evntType.setSelectedItem(TableClick1);
        OPW.percentage.setSelectedItem(TableClick2);
        OPW.status.setSelectedItem(TableClick3);
        OPW.jButton3.setText("Update");
        OPW.id.setText(TableClick0);
        
        OPW.show();
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cultural_Events = new javax.swing.JPopupMenu();
        Add_Cultural_Event = new javax.swing.JMenuItem();
        add_subEvent = new javax.swing.JMenuItem();
        addcrit1 = new javax.swing.JMenuItem();
        Update_Cultural_Event = new javax.swing.JMenuItem();
        delete_cul = new javax.swing.JMenuItem();
        Category_Event = new javax.swing.JPopupMenu();
        addcrit3 = new javax.swing.JMenuItem();
        Add_Event_Category = new javax.swing.JMenuItem();
        Update_Event_Category = new javax.swing.JMenuItem();
        delete_categ = new javax.swing.JMenuItem();
        Event_Criteria = new javax.swing.JPopupMenu();
        Add_Event_Criteria = new javax.swing.JMenuItem();
        Update_Event_Criteria = new javax.swing.JMenuItem();
        delete_crit = new javax.swing.JMenuItem();
        Users = new javax.swing.JPopupMenu();
        add_user = new javax.swing.JMenuItem();
        Update_User = new javax.swing.JMenuItem();
        dele_user = new javax.swing.JMenuItem();
        Participant_popup = new javax.swing.JPopupMenu();
        add_Part = new javax.swing.JMenuItem();
        Update_Participant = new javax.swing.JMenuItem();
        assign_event = new javax.swing.JMenuItem();
        dele_Part = new javax.swing.JMenuItem();
        Team = new javax.swing.JPopupMenu();
        add_team = new javax.swing.JMenuItem();
        Update_Team = new javax.swing.JMenuItem();
        del_team = new javax.swing.JMenuItem();
        Sport_Event = new javax.swing.JPopupMenu();
        add_Sport = new javax.swing.JMenuItem();
        Update_Sport = new javax.swing.JMenuItem();
        dele_Sport = new javax.swing.JMenuItem();
        attended_Event = new javax.swing.JPopupMenu();
        Delete = new javax.swing.JMenuItem();
        OverAll_Points = new javax.swing.JPopupMenu();
        add_Points = new javax.swing.JMenuItem();
        update_points = new javax.swing.JMenuItem();
        delete_points = new javax.swing.JMenuItem();
        rank_Event = new javax.swing.JPopupMenu();
        add_rank = new javax.swing.JMenuItem();
        rank = new javax.swing.JPopupMenu();
        reset = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        overallPrint = new javax.swing.JPopupMenu();
        OV = new javax.swing.JMenuItem();
        detais = new javax.swing.JMenuItem();
        culutralPrint = new javax.swing.JPopupMenu();
        CP = new javax.swing.JMenuItem();
        detais1 = new javax.swing.JMenuItem();
        sportPrint = new javax.swing.JPopupMenu();
        SP = new javax.swing.JMenuItem();
        detais2 = new javax.swing.JMenuItem();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        main = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        admin = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        event = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        search_Event = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        critable = new javax.swing.JTable();
        jLabel89 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        EventD = new javax.swing.JTable();
        jLabel87 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        ECategory = new javax.swing.JTable();
        jLabel88 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        reports = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        report1event = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        report1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        report2cul = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        sportscombo = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        sporttable = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        sportotal = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        allscore = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        User_Window = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        UserTable1 = new javax.swing.JTable();
        jLabel91 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        TImage4 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        SUser1 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        Sport_Window = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        searcSP1 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        Tsport1 = new javax.swing.JTable();
        jLabel93 = new javax.swing.JLabel();
        Participant_Window = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        assigned = new javax.swing.JTable();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        parttable = new javax.swing.JTable();
        jLabel98 = new javax.swing.JLabel();
        partsearch = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        TImage6 = new javax.swing.JLabel();
        Overall_Points_Window = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        overall = new javax.swing.JTable();
        jLabel108 = new javax.swing.JLabel();
        search_Event1 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        Rank_Window = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        rank1 = new javax.swing.JTable();
        jLabel112 = new javax.swing.JLabel();
        search_Event3 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        ranktable = new javax.swing.JTable();
        team_window = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        teamtable1 = new javax.swing.JTable();
        jLabel110 = new javax.swing.JLabel();
        teamsearch1 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        TImage7 = new javax.swing.JLabel();
        report = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel94 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        detailed = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel102 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel103 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();

        Cultural_Events.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        Add_Cultural_Event.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        Add_Cultural_Event.setText("Add Cultural Event");
        Add_Cultural_Event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Cultural_EventActionPerformed(evt);
            }
        });
        Cultural_Events.add(Add_Cultural_Event);

        add_subEvent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_subEvent.setText("Add Event Category");
        add_subEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_subEventActionPerformed(evt);
            }
        });
        Cultural_Events.add(add_subEvent);

        addcrit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        addcrit1.setText("Add Criteria");
        addcrit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcrit1ActionPerformed(evt);
            }
        });
        Cultural_Events.add(addcrit1);

        Update_Cultural_Event.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Cultural_Event.setText("Update Cultural Event");
        Update_Cultural_Event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_Cultural_EventActionPerformed(evt);
            }
        });
        Cultural_Events.add(Update_Cultural_Event);

        delete_cul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        delete_cul.setText("Delete");
        delete_cul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_culActionPerformed(evt);
            }
        });
        Cultural_Events.add(delete_cul);

        Category_Event.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        addcrit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        addcrit3.setText("Add Criteria");
        addcrit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcrit3ActionPerformed(evt);
            }
        });
        Category_Event.add(addcrit3);

        Add_Event_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        Add_Event_Category.setText("Add Sub Event");
        Add_Event_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Event_CategoryActionPerformed(evt);
            }
        });
        Category_Event.add(Add_Event_Category);

        Update_Event_Category.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Event_Category.setText("Update Sub Event");
        Update_Event_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_Event_CategoryActionPerformed(evt);
            }
        });
        Category_Event.add(Update_Event_Category);

        delete_categ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        delete_categ.setText("Delete");
        delete_categ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_categActionPerformed(evt);
            }
        });
        Category_Event.add(delete_categ);

        Event_Criteria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        Add_Event_Criteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        Add_Event_Criteria.setText("Add Criteria");
        Add_Event_Criteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Event_CriteriaActionPerformed(evt);
            }
        });
        Event_Criteria.add(Add_Event_Criteria);

        Update_Event_Criteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Event_Criteria.setText("Update Criteria");
        Update_Event_Criteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_Event_CriteriaActionPerformed(evt);
            }
        });
        Event_Criteria.add(Update_Event_Criteria);

        delete_crit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        delete_crit.setText("Delete Criteria");
        delete_crit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_critActionPerformed(evt);
            }
        });
        Event_Criteria.add(delete_crit);

        Users.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Users.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        add_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_user.setText("Add User");
        add_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_userActionPerformed(evt);
            }
        });
        Users.add(add_user);

        Update_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_User.setText("Update User");
        Update_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_UserActionPerformed(evt);
            }
        });
        Users.add(Update_User);

        dele_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        dele_user.setText("Delete");
        dele_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dele_userActionPerformed(evt);
            }
        });
        Users.add(dele_user);

        Participant_popup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        add_Part.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_Part.setText("Add Participant");
        add_Part.setToolTipText("");
        add_Part.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_PartActionPerformed(evt);
            }
        });
        Participant_popup.add(add_Part);

        Update_Participant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Participant.setText("Update Participant");
        Update_Participant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_ParticipantActionPerformed(evt);
            }
        });
        Participant_popup.add(Update_Participant);

        assign_event.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/up_arrow.png"))); // NOI18N
        assign_event.setText("Assign Event for Selected Participant");
        assign_event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assign_eventActionPerformed(evt);
            }
        });
        Participant_popup.add(assign_event);

        dele_Part.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        dele_Part.setText("Delete");
        dele_Part.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dele_PartActionPerformed(evt);
            }
        });
        Participant_popup.add(dele_Part);

        Team.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        add_team.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_team.setText("Add Team");
        add_team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_teamActionPerformed(evt);
            }
        });
        Team.add(add_team);

        Update_Team.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Team.setText("Update Team");
        Update_Team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_TeamActionPerformed(evt);
            }
        });
        Team.add(Update_Team);

        del_team.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        del_team.setText("Delete");
        del_team.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_teamActionPerformed(evt);
            }
        });
        Team.add(del_team);

        Sport_Event.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        add_Sport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_Sport.setText("Add Sport Event");
        add_Sport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_SportActionPerformed(evt);
            }
        });
        Sport_Event.add(add_Sport);

        Update_Sport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        Update_Sport.setText("Update Sport Event");
        Update_Sport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_SportActionPerformed(evt);
            }
        });
        Sport_Event.add(Update_Sport);

        dele_Sport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        dele_Sport.setText("Delete");
        dele_Sport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dele_SportActionPerformed(evt);
            }
        });
        Sport_Event.add(dele_Sport);

        attended_Event.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 10))); // NOI18N

        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        Delete.setText("Delete Assigned Event");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        attended_Event.add(Delete);

        add_Points.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_Points.setText("Add Overall Points");
        add_Points.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_PointsActionPerformed(evt);
            }
        });
        OverAll_Points.add(add_Points);

        update_points.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        update_points.setText("Update Overall Points");
        update_points.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_pointsActionPerformed(evt);
            }
        });
        OverAll_Points.add(update_points);

        delete_points.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        delete_points.setText("Delete Overall Points");
        delete_points.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_pointsActionPerformed(evt);
            }
        });
        OverAll_Points.add(delete_points);

        add_rank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        add_rank.setText("Add Rank Points");
        add_rank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_rankActionPerformed(evt);
            }
        });
        rank_Event.add(add_rank);

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/configure_shortcuts.png"))); // NOI18N
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        rank.add(reset);

        OV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        OV.setText("Print");
        OV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OVActionPerformed(evt);
            }
        });
        overallPrint.add(OV);

        detais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/SEO-icon.png"))); // NOI18N
        detais.setText("View Detailed Information");
        detais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detaisActionPerformed(evt);
            }
        });
        overallPrint.add(detais);

        CP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        CP.setText("Print");
        CP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPActionPerformed(evt);
            }
        });
        culutralPrint.add(CP);

        detais1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/SEO-icon.png"))); // NOI18N
        detais1.setText("View Detailed Information");
        detais1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detais1ActionPerformed(evt);
            }
        });
        culutralPrint.add(detais1);

        SP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        SP.setText("Print");
        SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPActionPerformed(evt);
            }
        });
        sportPrint.add(SP);

        detais2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/SEO-icon.png"))); // NOI18N
        detais2.setText("View Detailed Information");
        detais2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detais2ActionPerformed(evt);
            }
        });
        sportPrint.add(detais2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Administrator Window");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tablogo.gif")));
        setName("Administrator_Window"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        main.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/tablogo1.png"))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel38.setText("User Login:");

        admin.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        admin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        admin.setText("jLabel39");

        time.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel40.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel40.setText("Date:");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel42.setText("Time:");

        date.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        userID.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        userID.setText("ID");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 737, Short.MAX_VALUE)
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
                    .addComponent(userID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(jLabel9)
                .addContainerGap(428, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(home, "card2");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(1440, 650));

        search_Event.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        search_Event.setText("Enter Event ID OR Event Name");
        search_Event.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_EventMouseClicked(evt);
            }
        });
        search_Event.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_EventKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Search:");

        jButton2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton2.setText("Clear Field");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        critable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        critable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Description", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        critable.setComponentPopupMenu(Event_Criteria);
        critable.setFillsViewportHeight(true);
        critable.setShowHorizontalLines(false);
        critable.setShowVerticalLines(false);
        critable.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(critable);
        critable.getColumnModel().getColumn(0).setResizable(false);
        critable.getColumnModel().getColumn(1).setResizable(false);
        critable.getColumnModel().getColumn(2).setResizable(false);

        jLabel89.setBackground(new java.awt.Color(102, 102, 255));
        jLabel89.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Criteria");
        jLabel89.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel89.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        EventD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        EventD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event ID", "Event Name", "Percentage", "Date Created", "Status", "Event Schedule", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EventD.setComponentPopupMenu(Cultural_Events);
        EventD.setFillsViewportHeight(true);
        EventD.setShowHorizontalLines(false);
        EventD.setShowVerticalLines(false);
        EventD.getTableHeader().setReorderingAllowed(false);
        EventD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EventDMouseClicked(evt);
            }
        });
        EventD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EventDKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(EventD);
        EventD.getColumnModel().getColumn(0).setResizable(false);
        EventD.getColumnModel().getColumn(1).setResizable(false);
        EventD.getColumnModel().getColumn(1).setPreferredWidth(150);
        EventD.getColumnModel().getColumn(2).setResizable(false);
        EventD.getColumnModel().getColumn(2).setPreferredWidth(90);
        EventD.getColumnModel().getColumn(3).setResizable(false);
        EventD.getColumnModel().getColumn(3).setPreferredWidth(140);
        EventD.getColumnModel().getColumn(4).setResizable(false);
        EventD.getColumnModel().getColumn(4).setPreferredWidth(90);
        EventD.getColumnModel().getColumn(5).setResizable(false);
        EventD.getColumnModel().getColumn(5).setPreferredWidth(200);
        EventD.getColumnModel().getColumn(6).setResizable(false);
        EventD.getColumnModel().getColumn(6).setPreferredWidth(150);

        jLabel87.setBackground(new java.awt.Color(102, 102, 255));
        jLabel87.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Cultural Event");
        jLabel87.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel87.setOpaque(true);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        ECategory.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ECategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Caetgory Name", "Percentage", "Type of judging"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ECategory.setComponentPopupMenu(Category_Event);
        ECategory.setFillsViewportHeight(true);
        ECategory.setShowHorizontalLines(false);
        ECategory.setShowVerticalLines(false);
        ECategory.getTableHeader().setReorderingAllowed(false);
        ECategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ECategoryMouseClicked(evt);
            }
        });
        ECategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ECategoryKeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(ECategory);
        ECategory.getColumnModel().getColumn(0).setResizable(false);
        ECategory.getColumnModel().getColumn(0).setPreferredWidth(0);
        ECategory.getColumnModel().getColumn(1).setResizable(false);
        ECategory.getColumnModel().getColumn(2).setResizable(false);
        ECategory.getColumnModel().getColumn(3).setResizable(false);

        jLabel88.setBackground(new java.awt.Color(102, 102, 255));
        jLabel88.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Sub Event");
        jLabel88.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel88.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
        );

        jButton6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton6.setText("Print all Active Events");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addComponent(search_Event, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search_Event)
                            .addComponent(jButton2)
                            .addComponent(jButton6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout eventLayout = new javax.swing.GroupLayout(event);
        event.setLayout(eventLayout);
        eventLayout.setHorizontalGroup(
            eventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        eventLayout.setVerticalGroup(
            eventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        main.add(event, "card3");

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Search :");

        report1event.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        report1event.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select-Event-:" }));
        report1event.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                report1eventItemStateChanged(evt);
            }
        });

        report1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        report1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "Score", "Rank"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        report1.setShowHorizontalLines(false);
        report1.setShowVerticalLines(false);
        report1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(report1);
        report1.getColumnModel().getColumn(0).setResizable(false);
        report1.getColumnModel().getColumn(1).setResizable(false);
        report1.getColumnModel().getColumn(2).setResizable(false);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton10.setText("Print");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report1event, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(report1event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Report 1", jPanel10);

        jButton4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        report2cul.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        report2cul.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        report2cul.setShowHorizontalLines(false);
        report2cul.setShowVerticalLines(false);
        report2cul.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(report2cul);
        report2cul.getColumnModel().getColumn(0).setResizable(false);
        report2cul.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Report 2", jPanel11);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Cultural Event", jPanel4);

        jTabbedPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("Search :");

        sportscombo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        sportscombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select-Event-:" }));
        sportscombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sportscomboItemStateChanged(evt);
            }
        });

        sporttable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        sporttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "Score", "Rank"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sporttable.setShowHorizontalLines(false);
        sporttable.setShowVerticalLines(false);
        sporttable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(sporttable);
        sporttable.getColumnModel().getColumn(0).setResizable(false);
        sporttable.getColumnModel().getColumn(1).setResizable(false);
        sporttable.getColumnModel().getColumn(2).setResizable(false);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton9.setText("Print");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sportscombo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap())
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sportscombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Report 1", jPanel12);

        jButton7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton7.setText("Print");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        sportotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sportotal.setShowHorizontalLines(false);
        sportotal.setShowVerticalLines(false);
        sportotal.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(sportotal);
        sportotal.getColumnModel().getColumn(0).setResizable(false);
        sportotal.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Report 2", jPanel13);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("Sport Event", jPanel5);

        allscore.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        allscore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team", "OverAll Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allscore.setShowHorizontalLines(false);
        allscore.setShowVerticalLines(false);
        allscore.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(allscore);
        allscore.getColumnModel().getColumn(0).setResizable(false);
        allscore.getColumnModel().getColumn(1).setResizable(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton8.setText("Print");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1351, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("OverAll Result", jPanel6);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout reportsLayout = new javax.swing.GroupLayout(reports);
        reports.setLayout(reportsLayout);
        reportsLayout.setHorizontalGroup(
            reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        reportsLayout.setVerticalGroup(
            reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(reports, "card4");

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        UserTable1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        UserTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Full Name", "Username", "Gender", "Address", "Contact Number", "Status", "User Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UserTable1.setComponentPopupMenu(Users);
        UserTable1.setFillsViewportHeight(true);
        UserTable1.setShowHorizontalLines(false);
        UserTable1.setShowVerticalLines(false);
        UserTable1.getTableHeader().setReorderingAllowed(false);
        UserTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserTable1MouseClicked(evt);
            }
        });
        UserTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserTable1KeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(UserTable1);
        UserTable1.getColumnModel().getColumn(0).setResizable(false);
        UserTable1.getColumnModel().getColumn(1).setResizable(false);
        UserTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        UserTable1.getColumnModel().getColumn(2).setResizable(false);
        UserTable1.getColumnModel().getColumn(3).setResizable(false);
        UserTable1.getColumnModel().getColumn(4).setResizable(false);
        UserTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
        UserTable1.getColumnModel().getColumn(5).setResizable(false);
        UserTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        UserTable1.getColumnModel().getColumn(6).setResizable(false);
        UserTable1.getColumnModel().getColumn(7).setResizable(false);
        UserTable1.getColumnModel().getColumn(7).setPreferredWidth(100);

        jLabel91.setBackground(new java.awt.Color(102, 102, 255));
        jLabel91.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Users Information");
        jLabel91.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel91.setOpaque(true);

        jPanel33.setBackground(new java.awt.Color(51, 51, 51));
        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel33.setOpaque(false);

        TImage4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TImage4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TImage4.setText("No Image Available");
        TImage4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel90.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel90.setText("Search:");

        SUser1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        SUser1.setText("Type User ID Or User Name");
        SUser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SUser1MouseClicked(evt);
            }
        });
        SUser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SUser1KeyReleased(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton23.setText("Clear Field");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1332, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel90)
                        .addComponent(jButton23)
                        .addComponent(SUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout User_WindowLayout = new javax.swing.GroupLayout(User_Window);
        User_Window.setLayout(User_WindowLayout);
        User_WindowLayout.setHorizontalGroup(
            User_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        User_WindowLayout.setVerticalGroup(
            User_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(User_Window, "card5");

        Sport_Window.setBackground(new java.awt.Color(255, 255, 255));
        Sport_Window.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        searcSP1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        searcSP1.setText("Type ID OR Sports Name");
        searcSP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searcSP1MouseClicked(evt);
            }
        });
        searcSP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searcSP1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searcSP1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searcSP1KeyTyped(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton24.setText("Clear Field");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton11.setText("Print all Active Events");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel92.setText("Search:");

        Tsport1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Tsport1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sport Name", "Percentage", "Status", "Type", "Event Schedule", "Date Created", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tsport1.setComponentPopupMenu(Sport_Event);
        Tsport1.setFillsViewportHeight(true);
        Tsport1.setShowHorizontalLines(false);
        Tsport1.setShowVerticalLines(false);
        Tsport1.getTableHeader().setReorderingAllowed(false);
        jScrollPane17.setViewportView(Tsport1);
        Tsport1.getColumnModel().getColumn(0).setResizable(false);
        Tsport1.getColumnModel().getColumn(1).setResizable(false);
        Tsport1.getColumnModel().getColumn(1).setPreferredWidth(200);
        Tsport1.getColumnModel().getColumn(2).setResizable(false);
        Tsport1.getColumnModel().getColumn(2).setPreferredWidth(100);
        Tsport1.getColumnModel().getColumn(3).setResizable(false);
        Tsport1.getColumnModel().getColumn(4).setResizable(false);
        Tsport1.getColumnModel().getColumn(5).setResizable(false);
        Tsport1.getColumnModel().getColumn(5).setPreferredWidth(200);
        Tsport1.getColumnModel().getColumn(6).setResizable(false);
        Tsport1.getColumnModel().getColumn(7).setResizable(false);
        Tsport1.getColumnModel().getColumn(7).setPreferredWidth(200);

        jLabel93.setBackground(new java.awt.Color(102, 102, 255));
        jLabel93.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Sports Information");
        jLabel93.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel93.setOpaque(true);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1332, Short.MAX_VALUE)
            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searcSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searcSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92)
                    .addComponent(jButton24)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Sport_WindowLayout = new javax.swing.GroupLayout(Sport_Window);
        Sport_Window.setLayout(Sport_WindowLayout);
        Sport_WindowLayout.setHorizontalGroup(
            Sport_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sport_WindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Sport_WindowLayout.setVerticalGroup(
            Sport_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Sport_WindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main.add(Sport_Window, "card6");

        Participant_Window.setBackground(new java.awt.Color(255, 255, 255));
        Participant_Window.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel96.setBackground(new java.awt.Color(102, 102, 255));
        jLabel96.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Assigned Event for Participant");
        jLabel96.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel96.setOpaque(true);

        assigned.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        assigned.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event ID", "Event Name", "Event Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        assigned.setComponentPopupMenu(attended_Event);
        assigned.setFillsViewportHeight(true);
        assigned.setShowHorizontalLines(false);
        assigned.setShowVerticalLines(false);
        assigned.getTableHeader().setReorderingAllowed(false);
        jScrollPane18.setViewportView(assigned);
        assigned.getColumnModel().getColumn(0).setResizable(false);
        assigned.getColumnModel().getColumn(1).setResizable(false);
        assigned.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
        );

        jLabel95.setBackground(new java.awt.Color(102, 102, 255));
        jLabel95.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("Particpant Information");
        jLabel95.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel95.setOpaque(true);

        parttable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        parttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Contact Number", "Address", "Status", "Team"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        parttable.setComponentPopupMenu(Participant_popup);
        parttable.setFillsViewportHeight(true);
        parttable.setShowHorizontalLines(false);
        parttable.setShowVerticalLines(false);
        parttable.getTableHeader().setReorderingAllowed(false);
        parttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parttableMouseClicked(evt);
            }
        });
        parttable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                parttableKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(parttable);
        parttable.getColumnModel().getColumn(0).setResizable(false);
        parttable.getColumnModel().getColumn(0).setPreferredWidth(50);
        parttable.getColumnModel().getColumn(1).setResizable(false);
        parttable.getColumnModel().getColumn(1).setPreferredWidth(200);
        parttable.getColumnModel().getColumn(2).setResizable(false);
        parttable.getColumnModel().getColumn(3).setResizable(false);
        parttable.getColumnModel().getColumn(3).setPreferredWidth(100);
        parttable.getColumnModel().getColumn(4).setResizable(false);
        parttable.getColumnModel().getColumn(4).setPreferredWidth(200);
        parttable.getColumnModel().getColumn(5).setResizable(false);
        parttable.getColumnModel().getColumn(6).setResizable(false);

        jLabel98.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel98.setText("Search:");

        partsearch.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        partsearch.setText("Participant ID OR Participant Name");
        partsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                partsearchMouseClicked(evt);
            }
        });
        partsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                partsearchKeyReleased(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton26.setText("Clear Field");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jPanel50.setBackground(new java.awt.Color(51, 51, 51));
        jPanel50.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel50.setOpaque(false);

        TImage6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TImage6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TImage6.setText("No Image Available");
        TImage6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage6, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage6, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane8)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(partsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 768, Short.MAX_VALUE)
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(partsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton26)
                            .addComponent(jLabel98))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );

        javax.swing.GroupLayout Participant_WindowLayout = new javax.swing.GroupLayout(Participant_Window);
        Participant_Window.setLayout(Participant_WindowLayout);
        Participant_WindowLayout.setHorizontalGroup(
            Participant_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Participant_WindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Participant_WindowLayout.setVerticalGroup(
            Participant_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Participant_WindowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        main.add(Participant_Window, "card7");

        Overall_Points_Window.setBackground(new java.awt.Color(255, 255, 255));

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel107.setBackground(new java.awt.Color(102, 102, 255));
        jLabel107.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("OverAll Points Information");
        jLabel107.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel107.setOpaque(true);

        overall.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        overall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Event Type", "Points", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        overall.setComponentPopupMenu(OverAll_Points);
        overall.setFillsViewportHeight(true);
        overall.setShowHorizontalLines(false);
        overall.setShowVerticalLines(false);
        jScrollPane24.setViewportView(overall);
        overall.getColumnModel().getColumn(0).setResizable(false);
        overall.getColumnModel().getColumn(1).setResizable(false);
        overall.getColumnModel().getColumn(2).setResizable(false);
        overall.getColumnModel().getColumn(3).setResizable(false);

        jLabel108.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel108.setText("Search:");

        search_Event1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        search_Event1.setText("Enter  ID OR Event Type");
        search_Event1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_Event1MouseClicked(evt);
            }
        });
        search_Event1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_Event1KeyReleased(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton27.setText("Clear Field");
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_Event1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27)
                .addContainerGap())
            .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 1336, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(search_Event1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Overall_Points_WindowLayout = new javax.swing.GroupLayout(Overall_Points_Window);
        Overall_Points_Window.setLayout(Overall_Points_WindowLayout);
        Overall_Points_WindowLayout.setHorizontalGroup(
            Overall_Points_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Overall_Points_WindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Overall_Points_WindowLayout.setVerticalGroup(
            Overall_Points_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Overall_Points_WindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main.add(Overall_Points_Window, "card8");

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel111.setBackground(new java.awt.Color(102, 102, 255));
        jLabel111.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Ranking Events Information");
        jLabel111.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel111.setOpaque(true);

        rank1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rank1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Event Name", "Event Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rank1.setComponentPopupMenu(rank_Event);
        rank1.setFillsViewportHeight(true);
        rank1.setShowHorizontalLines(false);
        rank1.setShowVerticalLines(false);
        rank1.getTableHeader().setReorderingAllowed(false);
        rank1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rank1MouseClicked(evt);
            }
        });
        rank1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rank1KeyReleased(evt);
            }
        });
        jScrollPane26.setViewportView(rank1);
        rank1.getColumnModel().getColumn(0).setResizable(false);
        rank1.getColumnModel().getColumn(1).setResizable(false);
        rank1.getColumnModel().getColumn(1).setPreferredWidth(200);
        rank1.getColumnModel().getColumn(2).setResizable(false);

        jLabel112.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel112.setText("Search:");

        search_Event3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        search_Event3.setText("Enter  ID OR Event Name OR Rank");
        search_Event3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_Event3MouseClicked(evt);
            }
        });
        search_Event3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_Event3ActionPerformed(evt);
            }
        });
        search_Event3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_Event3KeyReleased(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton29.setText("Clear Field");
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        ranktable.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ranktable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ranktable.setComponentPopupMenu(rank);
        ranktable.setFillsViewportHeight(true);
        ranktable.setShowHorizontalLines(false);
        ranktable.setShowVerticalLines(false);
        ranktable.getTableHeader().setReorderingAllowed(false);
        jScrollPane25.setViewportView(ranktable);
        ranktable.getColumnModel().getColumn(0).setResizable(false);
        ranktable.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel112)
                .addGap(8, 8, 8)
                .addComponent(search_Event3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addContainerGap())
            .addComponent(jScrollPane25)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search_Event3)
                        .addComponent(jButton29))
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout Rank_WindowLayout = new javax.swing.GroupLayout(Rank_Window);
        Rank_Window.setLayout(Rank_WindowLayout);
        Rank_WindowLayout.setHorizontalGroup(
            Rank_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Rank_WindowLayout.setVerticalGroup(
            Rank_WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(Rank_Window, "card9");

        team_window.setBackground(new java.awt.Color(255, 255, 255));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jLabel109.setBackground(new java.awt.Color(102, 102, 255));
        jLabel109.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("Team Information");
        jLabel109.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel109.setOpaque(true);

        teamtable1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        teamtable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Team ID", "Team Name", "Team Number", "Team Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teamtable1.setComponentPopupMenu(Team);
        teamtable1.setFillsViewportHeight(true);
        teamtable1.setShowHorizontalLines(false);
        teamtable1.setShowVerticalLines(false);
        teamtable1.getTableHeader().setReorderingAllowed(false);
        teamtable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teamtable1MouseClicked(evt);
            }
        });
        teamtable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamtable1KeyReleased(evt);
            }
        });
        jScrollPane27.setViewportView(teamtable1);
        teamtable1.getColumnModel().getColumn(0).setResizable(false);
        teamtable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        teamtable1.getColumnModel().getColumn(1).setResizable(false);
        teamtable1.getColumnModel().getColumn(2).setResizable(false);
        teamtable1.getColumnModel().getColumn(3).setResizable(false);

        jLabel110.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel110.setText("Search:");

        teamsearch1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        teamsearch1.setText("Team ID OR Team Name");
        teamsearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teamsearch1MouseClicked(evt);
            }
        });
        teamsearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teamsearch1KeyReleased(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton28.setText("Clear Field");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jPanel52.setBackground(new java.awt.Color(51, 51, 51));
        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel52.setOpaque(false);

        TImage7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TImage7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TImage7.setText("No Image Available");
        TImage7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage7, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TImage7, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane27)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teamsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 830, Short.MAX_VALUE)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(teamsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout team_windowLayout = new javax.swing.GroupLayout(team_window);
        team_window.setLayout(team_windowLayout);
        team_windowLayout.setHorizontalGroup(
            team_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team_windowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        team_windowLayout.setVerticalGroup(
            team_windowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team_windowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        main.add(team_window, "card10");

        report.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(overallPrint);
        jTable1.setFillsViewportHeight(true);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setComponentPopupMenu(culutralPrint);
        jTable2.setFillsViewportHeight(true);
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(jTable2);

        jTable3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setComponentPopupMenu(sportPrint);
        jTable3.setFillsViewportHeight(true);
        jTable3.setShowHorizontalLines(false);
        jTable3.setShowVerticalLines(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(jTable3);

        jLabel94.setBackground(new java.awt.Color(102, 102, 255));
        jLabel94.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Overall Result");
        jLabel94.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel94.setOpaque(true);

        jLabel97.setBackground(new java.awt.Color(102, 102, 255));
        jLabel97.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Cultural Event Results");
        jLabel97.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel97.setOpaque(true);

        jLabel100.setBackground(new java.awt.Color(102, 102, 255));
        jLabel100.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Sports Results");
        jLabel100.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel100.setOpaque(true);

        javax.swing.GroupLayout reportLayout = new javax.swing.GroupLayout(report);
        report.setLayout(reportLayout);
        reportLayout.setHorizontalGroup(
            reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
            .addComponent(jScrollPane13)
            .addComponent(jScrollPane11)
        );
        reportLayout.setVerticalGroup(
            reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportLayout.createSequentialGroup()
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(report, "card11");

        detailed.setBackground(new java.awt.Color(255, 255, 255));
        detailed.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255)));

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTabbedPane5.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel99.setBackground(new java.awt.Color(102, 102, 255));
        jLabel99.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Main Event");
        jLabel99.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel99.setOpaque(true);

        jTable4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setFillsViewportHeight(true);
        jTable4.setShowHorizontalLines(false);
        jTable4.setShowVerticalLines(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(jTable4);
        jTable4.getColumnModel().getColumn(0).setResizable(false);
        jTable4.getColumnModel().getColumn(1).setResizable(false);

        jLabel101.setBackground(new java.awt.Color(102, 102, 255));
        jLabel101.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Category");
        jLabel101.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel101.setOpaque(true);

        jTable5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setFillsViewportHeight(true);
        jTable5.setShowHorizontalLines(false);
        jTable5.setShowVerticalLines(false);
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(jTable5);
        jTable5.getColumnModel().getColumn(0).setResizable(false);
        jTable5.getColumnModel().getColumn(1).setResizable(false);

        jTable6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setFillsViewportHeight(true);
        jTable6.setShowHorizontalLines(false);
        jTable6.setShowVerticalLines(false);
        jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(jTable6);
        jTable6.getColumnModel().getColumn(0).setResizable(false);
        jTable6.getColumnModel().getColumn(1).setResizable(false);

        jLabel102.setBackground(new java.awt.Color(102, 102, 255));
        jLabel102.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Criteria");
        jLabel102.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel102.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Search Main Event :");

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-:" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Search Category :");

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Search Criteria :");

        jComboBox4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-Type-of-Judging:", "INDIVIDUAL", "BY TEAM" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jComboBox5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-Event-Type-:", "With Category", "With No Category" }));
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane14)
            .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(42, 42, 42)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 696, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jTabbedPane5.addTab("Cultural Events", jPanel3);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jTable7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setFillsViewportHeight(true);
        jTable7.setShowHorizontalLines(false);
        jTable7.setShowVerticalLines(false);
        jTable7.getTableHeader().setReorderingAllowed(false);
        jScrollPane19.setViewportView(jTable7);
        jTable7.getColumnModel().getColumn(0).setResizable(false);
        jTable7.getColumnModel().getColumn(1).setResizable(false);

        jLabel103.setBackground(new java.awt.Color(102, 102, 255));
        jLabel103.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Main Event");
        jLabel103.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(102, 102, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jLabel103.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("Search by:");

        jComboBox6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-Sports Event:" }));
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Print.png"))); // NOI18N
        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
            .addComponent(jScrollPane19)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabbedPane5.addTab("Sports Events", jPanel15);

        javax.swing.GroupLayout detailedLayout = new javax.swing.GroupLayout(detailed);
        detailed.setLayout(detailedLayout);
        detailedLayout.setHorizontalGroup(
            detailedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        detailedLayout.setVerticalGroup(
            detailedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        main.add(detailed, "card12");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/File.png"))); // NOI18N
        jMenu3.setText("File     |");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/report-share.png"))); // NOI18N
        jMenuItem9.setText("Reports");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/dialog-password.png"))); // NOI18N
        jMenuItem11.setText("Generate Password");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/configure_shortcuts.png"))); // NOI18N
        jMenuItem8.setText("Configuration");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/ControlPanel.png"))); // NOI18N
        jMenuItem4.setText("Controller");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/about.png"))); // NOI18N
        jMenuItem10.setText("About");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/question-balloon.png"))); // NOI18N
        jMenuItem13.setText("Help");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/system-log-out.png"))); // NOI18N
        jMenuItem6.setText("Logout");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar2.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/event.png"))); // NOI18N
        jMenu4.setText("Event     |");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/points.png"))); // NOI18N
        jMenu1.setText("OverAll Points");

        jMenuItem34.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem34.setText("View");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem34);

        jMenuItem35.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem35.setText("Add");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem35);

        jMenuItem36.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem36.setText("Update");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem36);

        jMenuItem38.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem38.setText("Delete");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem38);

        jMenu4.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/cultural.png"))); // NOI18N
        jMenu2.setText("Cultural Event");

        jMenuItem44.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Add.png"))); // NOI18N
        jMenuItem44.setText("Add Criteria");
        jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem44ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem44);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Add.png"))); // NOI18N
        jMenuItem12.setText("Add Sub Event");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem1.setText("View");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem2.setText("Add");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem31.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem31.setText("Update");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem31);

        jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem30.setText("Delete");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem30);

        jMenu4.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/sports.png"))); // NOI18N
        jMenu5.setText("Sports Event");

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem23.setText("View");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem23);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem3.setText("Add");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem24.setText("Update");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenuItem26.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem26.setText("Delete");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem26);

        jMenu4.add(jMenu5);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Rank-History.png"))); // NOI18N
        jMenu9.setText("Ranking Points");

        jMenuItem39.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem39.setText("View");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem39);

        jMenuItem40.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem40.setText("Add");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem40);

        jMenuItem42.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem42.setText("Reset");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem42);

        jMenu4.add(jMenu9);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/User.png"))); // NOI18N
        jMenu6.setText("User");

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem21.setText("View");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem7.setText("Add");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem22.setText("Update");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem22);

        jMenuItem27.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem27.setText("Delete");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem27);

        jMenu4.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/team.png"))); // NOI18N
        jMenu7.setText("Team");

        jMenuItem28.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem28.setText("View");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem28);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem14.setText("Add");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem18.setText("Update");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem18);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem20.setText("Delete");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem20);

        jMenu4.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/participation.png"))); // NOI18N
        jMenu8.setText("Participant");

        jMenuItem29.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/view.png"))); // NOI18N
        jMenuItem29.setText("View");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem29);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/add (1).png"))); // NOI18N
        jMenuItem5.setText("Add");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/update.png"))); // NOI18N
        jMenuItem15.setText("Update");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tabulation/Image/Icon/Delete (1).png"))); // NOI18N
        jMenuItem17.setText("Delete");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);

        jMenu4.add(jMenu8);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu4MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
       
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu3MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        //CardLayout cl = (CardLayout) main.getLayout();
       // cl.show(main,"card2");
    }//GEN-LAST:event_jMenu3MouseClicked

    private void Add_Cultural_EventActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Add_Cultural_EventActionPerformed
        
        new Cultural_Event_Window().show();
        CardLayout cl = (CardLayout) Cultural_Event_Window.main.getLayout();
        cl.show(Cultural_Event_Window.main,"card3");
    }//GEN-LAST:event_Add_Cultural_EventActionPerformed

    private void jMenuItem1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card3");
        search_Event.setText("Enter Event ID OR Event Name");
        DefaultTableModel model1=(DefaultTableModel)EventD.getModel();
        DefaultTableModel model2=(DefaultTableModel)critable.getModel();
        DefaultTableModel model3=(DefaultTableModel)ECategory.getModel();
        model1.setRowCount(0);
        model2.setRowCount(0);
        model3.setRowCount(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       
        new Cultural_Event_Window().show();
       CardLayout cl = (CardLayout) Cultural_Event_Window.main.getLayout();
        cl.show(Cultural_Event_Window.main,"card3");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void search_EventKeyReleased(KeyEvent evt) {//GEN-FIRST:event_search_EventKeyReleased
        DefaultTableModel model=(DefaultTableModel)EventD.getModel();
       String empty = search_Event.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        query_ListOFEvent QLP =new query_ListOFEvent();
        
        try {
            sList = QLP.wholeEvent(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            EventD.setValueAt(sList.get(i).getCul_ID(), i, 0);
            EventD.setValueAt(sList.get(i).getCul_Name(), i, 1);
            EventD.setValueAt(sList.get(i).getCul_Percentage(), i, 2);
            EventD.setValueAt(sList.get(i).getCul_Date(), i, 3);
            EventD.setValueAt(sList.get(i).getCul_Status(), i, 4);
            EventD.setValueAt(sList.get(i).getCul_Sched(), i, 5);
            EventD.setValueAt(sList.get(i).getFullname(), i, 6);
            }
            if(empty.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_EventKeyReleased

    private void search_EventMouseClicked(MouseEvent evt) {//GEN-FIRST:event_search_EventMouseClicked
        search_Event.setText("");
    }//GEN-LAST:event_search_EventMouseClicked

    private void EventDMouseClicked(MouseEvent evt) {//GEN-FIRST:event_EventDMouseClicked
        DefaultTableModel model=(DefaultTableModel)ECategory.getModel();
        DefaultTableModel model2=(DefaultTableModel)critable.getModel();
        DefaultTableModel model1=(DefaultTableModel)EventD.getModel();
        List<Event_Category> sList = new ArrayList<Event_Category>(); 
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_EventDetails_forSearch QEDFS =new Query_EventDetails_forSearch();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = EventD.getSelectedRow();
        String TableClick = (EventD.getModel().getValueAt(row, 0).toString());
        try {
            sList = QEDFS.CategoryDetailsforsearch(Integer.parseInt(TableClick));
            //CList = QCD.CriteriaDetailsforsearch(Integer.parseInt(TableClick));
            CList = QCD.CriteriaDetailsforsearch1(Integer.parseInt(TableClick));
                model.setRowCount(0);
                model2.setRowCount(0);
            for (int i=0;i<sList.size();i++){
                model.addRow(new Object[]{});
               ECategory.setValueAt(sList.get(i).getCategory_ID(), i, 0);
               ECategory.setValueAt(sList.get(i).getCategory_Name(), i, 1);
               ECategory.setValueAt(sList.get(i).getCategory_Per(), i, 2);
               ECategory.setValueAt(sList.get(i).getJudgeType(), i, 3);
               
            }
            
            for(int j=0;j<CList.size();j++){
                model2.addRow(new Object[]{});
                critable.setValueAt(CList.get(j).getCr_ID(), j, 0);
                critable.setValueAt(CList.get(j).getCr_Description(), j, 1);
                critable.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
           
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_EventDMouseClicked

    private void jMenuItem3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
/*        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card8");
        try {
            new INCREMENT().increment_Sports();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }*/
       
        new Sports_Window().show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
/*        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card9");
        try{
       // queryCultural_EventName();
        new INCREMENT().increment_Team();
        new INCREMENT().increment_Participant();
        queryTeam_Name();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}*/
        
        new Participants_Window().show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        int dialog =  JOptionPane.showConfirmDialog(null,"Do you want to close the system?","Message",JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
        dispose();
        new login().show();       
        }
       else if(dialog == JOptionPane.NO_OPTION) {}
       
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
      /*  CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card4");
        try {
            new INCREMENT().increment_Accounts();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
        }*/
        
       new Users_Account_Window().show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void Add_Event_CategoryActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Add_Event_CategoryActionPerformed
        new Cultural_Event_Window().show();
        CardLayout cl = (CardLayout) Cultural_Event_Window.main.getLayout();
        cl.show(Cultural_Event_Window.main,"card4");
    }//GEN-LAST:event_Add_Event_CategoryActionPerformed

    private void Add_Event_CriteriaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Add_Event_CriteriaActionPerformed
        // TODO add your handling code here:
        
        new Criteria_Window().show();
        
    }//GEN-LAST:event_Add_Event_CriteriaActionPerformed

    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model1=(DefaultTableModel)EventD.getModel();
        DefaultTableModel model2=(DefaultTableModel)critable.getModel();
        DefaultTableModel model3=(DefaultTableModel)ECategory.getModel();
        model1.setRowCount(0);
        model2.setRowCount(0);
        model3.setRowCount(0);
        search_Event.setText("Enter Event ID OR Event Name");
//        Color bgColor  = JColorChooser.showDialog(this, "Choose Background Color", getBackground());
//        if(bgColor!=null){
//            event.setBackground(bgColor);
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Update_Cultural_EventActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_Cultural_EventActionPerformed
        // TODO add your handling code here:
         cultural_update();
        
    }//GEN-LAST:event_Update_Cultural_EventActionPerformed

    private void delete_culActionPerformed(ActionEvent evt) {//GEN-FIRST:event_delete_culActionPerformed
        delete_Cultural();
    }//GEN-LAST:event_delete_culActionPerformed

    private void Update_Event_CategoryActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_Event_CategoryActionPerformed
        Cultural_Event_Window as = new Cultural_Event_Window();
        DefaultTableModel model1=(DefaultTableModel)ECategory.getModel();
        DefaultTableModel model2=(DefaultTableModel)EventD.getModel();
        int row1 = ECategory.getSelectedRow();
         int row2 = EventD.getSelectedRow();
        String TableClick1 = (ECategory.getModel().getValueAt(row1, 0).toString());
        String TableClick2 = (ECategory.getModel().getValueAt(row1, 1).toString());
        String TableClick3= (ECategory.getModel().getValueAt(row1, 2).toString());
        String TableClick6= (ECategory.getModel().getValueAt(row1, 3).toString());
        String TableClick4 = (EventD.getModel().getValueAt(row2, 1).toString());
        String TableClick5 = (EventD.getModel().getValueAt(row2, 0).toString());
        
       
        
        
       
        as.event.setSelectedItem(TableClick4);
        as.subname.setText(TableClick2);
        as.subpercent.setSelectedItem(TableClick3);
        as.ID1.setText(TableClick1);
        as.ID2.setText(TableClick5);
        as.judgeType.setSelectedItem(TableClick6);
        as.save1.setText("Update");
        as.event.setEnabled(false);
        
        CardLayout cl = (CardLayout) as.main.getLayout();
        cl.show(as.main,"card4");
        as.show();
        
        
    }//GEN-LAST:event_Update_Event_CategoryActionPerformed

    private void Update_ParticipantActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_ParticipantActionPerformed
        // TODO add your handling code here:
        update_Participant();
    }//GEN-LAST:event_Update_ParticipantActionPerformed

    private void Update_SportActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_SportActionPerformed
        // TODO add your handling code here:
         update_SPort();
    }//GEN-LAST:event_Update_SportActionPerformed

    private void Update_UserActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_UserActionPerformed
        // TODO add your handling code here:
        update_user();
    }//GEN-LAST:event_Update_UserActionPerformed

    private void Update_TeamActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_TeamActionPerformed
        // TODO add your handling code here:
       update_Team();
    }//GEN-LAST:event_Update_TeamActionPerformed

    private void ECategoryMouseClicked(MouseEvent evt) {//GEN-FIRST:event_ECategoryMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)critable.getModel();
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = ECategory.getSelectedRow();
        String TableClick = (ECategory.getModel().getValueAt(row, 0).toString());
        model.setRowCount(0);
        try{
        CList = QCD.CriteriaDetailsforsearch2(Integer.parseInt(TableClick));
        //EventD.getSelectionModel().clearSelection();
        for(int j=0;j<CList.size();j++){
                model.addRow(new Object[]{});
                critable.setValueAt(CList.get(j).getCr_ID(), j, 0);
                critable.setValueAt(CList.get(j).getCr_Description(), j, 1);
                critable.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
        
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }//GEN-LAST:event_ECategoryMouseClicked

    private void Update_Event_CriteriaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Update_Event_CriteriaActionPerformed
        // TODO add your handling code here:
        Criteria_Window cri = new Criteria_Window();
        DefaultTableModel mode1l=(DefaultTableModel)critable.getModel();;
        int row1 = critable.getSelectedRow();
        String TableClick0 = (critable.getModel().getValueAt(row1, 0).toString());
        
        
        List<Criteria_Cultural> CList = new ArrayList<>();
        List<Criteria_Cultural> SList = new ArrayList<>();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        try{
        CList = QCD.For_Update1(TableClick0);
        SList = QCD.For_Update2(TableClick0);
        String culname = null,catname = null,cname = null,eid = null;
        int cid = 0;
        double per = 0;
       
        for(int i = 0;i<CList.size();i++){
            cid = CList.get(i).getCr_ID();
            cname = CList.get(i).getCr_Description();
            per = CList.get(i).getCr_Percentage();
            eid = CList.get(i).getCul_ID();
            catname = CList.get(i).getCategory_Name();
            
            cri.event.setSelectedItem(catname);
            cri.ID1.setText(eid);
        }
        for(int i = 0;i<SList.size();i++){
            cid = SList.get(i).getCr_ID();
            cname = SList.get(i).getCr_Description();
            per = SList.get(i).getCr_Percentage();
            culname = SList.get(i).getCategory_Name();
            eid = SList.get(i).getCul_ID();
            cri.event.setSelectedItem(culname);
            cri.ID.setText(eid);
        }
        cri.crName.setText(cname);
        cri.crPercent.setSelectedItem(per);
        cri.crsave.setText("Update");
        cri.crid.setText(TableClick0);
         
        cri.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_Update_Event_CriteriaActionPerformed

    private void DeleteActionPerformed(ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        int row1 = assigned.getSelectedRow();
        int row2 = parttable.getSelectedRow();
       String TableClick1 = (assigned.getModel().getValueAt(row1, 0).toString());
       
       String TableClick2 = (parttable.getModel().getValueAt(row2, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
        char Char = TableClick1.charAt(0);
        String string = Character.toString(Char);
        switch (string) {
            case "4":
                {
                    UE.Delete_Assigned_Cultural(Integer.parseInt(TableClick1),Integer.parseInt(TableClick2));
                    JOptionPane.showMessageDialog(null,"Transaction Granted");
                    DefaultTableModel model=(DefaultTableModel)assigned.getModel();
                    model.setRowCount(0);
                    break;
                }
            case "5":
                {
                    UE.Delete_Assigned_Sports(Integer.parseInt(TableClick1),Integer.parseInt(TableClick2));
                    JOptionPane.showMessageDialog(null,"Transaction Granted");
                    DefaultTableModel model=(DefaultTableModel)assigned.getModel();
                    model.setRowCount(0);
                    break;
                }
        }
           
    }//GEN-LAST:event_DeleteActionPerformed

    private void assign_eventActionPerformed(ActionEvent evt) {//GEN-FIRST:event_assign_eventActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model1=(DefaultTableModel)parttable.getModel();
        DefaultTableModel model2=(DefaultTableModel)assigned.getModel();
        
       int row = parttable.getSelectedRow();
       String TableClick = (parttable.getModel().getValueAt(row, 0).toString());
       
       if (model1.getRowCount() != 0 || model2.getRowCount() != 0){
           // model1.setRowCount(0);
          //  model2.setRowCount(0);
        }
       
       Assigned_Window a  = new Assigned_Window();
       
       a.jLabel1.setText(TableClick);
       a.show(); 
        
    }//GEN-LAST:event_assign_eventActionPerformed

    private void jMenuItem9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
      
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card11");
        Get_data();
//         List<Cultural_Event> sList = new ArrayList<Cultural_Event>();
//        View_Events p = new View_Events();
//        try {
//            sList = p.queryView_Events(null);
//          
//            for (int i=0;i<sList.size();i++){
//            
//            String culName = sList.get(i).getCul_Name();
//            //cb_culeve.addItem(culName);
//            
//        }
//       
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,ex);
//        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        new HelpFrame(new javax.swing.JFrame(), true).show();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void formWindowOpened(WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      /*  try {
            // TODO add your handling code here:
            new server_is_writing().server();
        } catch (Exception ex) {
            Logger.getLogger(administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem11ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        new Password_Generator().show();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void formWindowClosed(WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem14ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        
        new Team_Window().show();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem20ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        delete_Team();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem31ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        cultural_update();
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem23ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card6");
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        update_SPort();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem21ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card5");
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        update_user();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem18ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        update_Team();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem15ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        update_Participant();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem28ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card10");
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card7");
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void EventDKeyReleased(KeyEvent evt) {//GEN-FIRST:event_EventDKeyReleased
        // TODO add your handling code here:
       DefaultTableModel model=(DefaultTableModel)ECategory.getModel();
        DefaultTableModel model2=(DefaultTableModel)critable.getModel();
        DefaultTableModel model1=(DefaultTableModel)EventD.getModel();
        List<Event_Category> sList = new ArrayList<Event_Category>(); 
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_EventDetails_forSearch QEDFS =new Query_EventDetails_forSearch();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = EventD.getSelectedRow();
        String TableClick = (EventD.getModel().getValueAt(row, 0).toString());
        try {
            sList = QEDFS.CategoryDetailsforsearch(Integer.parseInt(TableClick));
            //CList = QCD.CriteriaDetailsforsearch(Integer.parseInt(TableClick));
            CList = QCD.CriteriaDetailsforsearch1(Integer.parseInt(TableClick));
                model.setRowCount(0);
                model2.setRowCount(0);
            for (int i=0;i<sList.size();i++){
                model.addRow(new Object[]{});
               ECategory.setValueAt(sList.get(i).getCategory_ID(), i, 0);
               ECategory.setValueAt(sList.get(i).getCategory_Name(), i, 1);
               ECategory.setValueAt(sList.get(i).getCategory_Per(), i, 2);
               ECategory.setValueAt(sList.get(i).getJudgeType(), i, 3);
               
            }
            
            for(int j=0;j<CList.size();j++){
                model2.addRow(new Object[]{});
                critable.setValueAt(CList.get(j).getCr_ID(), j, 0);
                critable.setValueAt(CList.get(j).getCr_Description(), j, 1);
                critable.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
           
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_EventDKeyReleased

    private void ECategoryKeyReleased(KeyEvent evt) {//GEN-FIRST:event_ECategoryKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)critable.getModel();
        List<Criteria_Cultural> CList = new ArrayList<Criteria_Cultural>();
        Query_Criteria_Details QCD = new Query_Criteria_Details();
        int row = ECategory.getSelectedRow();
        String TableClick = (ECategory.getModel().getValueAt(row, 0).toString());
        model.setRowCount(0);
        try{
        CList = QCD.CriteriaDetailsforsearch2(Integer.parseInt(TableClick));
        
        for(int j=0;j<CList.size();j++){
                model.addRow(new Object[]{});
                critable.setValueAt(CList.get(j).getCr_ID(), j, 0);
                critable.setValueAt(CList.get(j).getCr_Description(), j, 1);
                critable.setValueAt(CList.get(j).getCr_Percentage(), j, 2);
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    
    }//GEN-LAST:event_ECategoryKeyReleased

    private void UserTable1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_UserTable1MouseClicked
        // TODO add your handling code here:
        int row = UserTable1.getSelectedRow();
        String TableClick = (UserTable1.getModel().getValueAt(row, 0).toString());
        
        List<Accounts> sList = new ArrayList<>();
        Query_Account_ID QAI = new Query_Account_ID();
        try{
            QAI.query_image(TableClick);
            ImageIcon icon;
            for(int i = 0;i<sList.size();i++){
               // icon = sList.get(i).getImage();
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_UserTable1MouseClicked

    private void SUser1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_SUser1MouseClicked
        // TODO add your handling code here:
         SUser1.setText("");
    }//GEN-LAST:event_SUser1MouseClicked

    private void SUser1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_SUser1KeyReleased
        // TODO add your handling code here:
         DefaultTableModel model=(DefaultTableModel)UserTable1.getModel();
       String empty = SUser1.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Accounts> sList = new ArrayList<>();   
        Query_Account_ID QLP =new Query_Account_ID();
        
        try {
            sList = QLP.query_Whole_accountsdetails(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            UserTable1.setValueAt(sList.get(i).getAcc_ID(), i, 0);
            UserTable1.setValueAt(sList.get(i).getFullName(), i, 1);
            UserTable1.setValueAt(sList.get(i).getAcc_Username(), i, 2);
            UserTable1.setValueAt(sList.get(i).getAcc_Gender(), i,3);
            UserTable1.setValueAt(sList.get(i).getAcc_Address(), i, 4);
            UserTable1.setValueAt(sList.get(i).getAcc_ContactNumber(), i, 5);
            UserTable1.setValueAt(sList.get(i).getStatus(), i,6);
            UserTable1.setValueAt(sList.get(i).getType(), i, 7);
            }
            if(empty.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_SUser1KeyReleased

    private void jButton23ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)UserTable1.getModel();
        SUser1.setText("Type User ID Or User Name");
        model.setRowCount(0);
        TImage4.setIcon(null);
        TImage4.setText("No Image Available");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void UserTable1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_UserTable1KeyReleased
        // TODO add your handling code here:
        int row = UserTable1.getSelectedRow();
        String TableClick = (UserTable1.getModel().getValueAt(row, 0).toString());
        
        List<Accounts> sList = new ArrayList<>();
        Query_Account_ID QAI = new Query_Account_ID();
        try{
            QAI.query_image(TableClick);
            ImageIcon icon;
            for(int i = 0;i<sList.size();i++){
               // icon = sList.get(i).getImage();
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_UserTable1KeyReleased

    private void searcSP1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_searcSP1MouseClicked
        // TODO add your handling code here:
        searcSP1.setText("");
    }//GEN-LAST:event_searcSP1MouseClicked

    private void searcSP1KeyPressed(KeyEvent evt) {//GEN-FIRST:event_searcSP1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_searcSP1KeyPressed

    private void searcSP1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_searcSP1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)Tsport1.getModel();
       String empty = searcSP1.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Sport_Event> sList = new ArrayList<>();   
        Query_SportDetails QSD =new Query_SportDetails();
        
        try {
            sList = QSD.queryAccount_ID(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            Tsport1.setValueAt(sList.get(i).getSp_ID(), i, 0);
            Tsport1.setValueAt(sList.get(i).getSp_Name(), i, 1);
            Tsport1.setValueAt(sList.get(i).getSp_Percentage(), i, 2);
            Tsport1.setValueAt(sList.get(i).getSp_Status(), i, 3);
            Tsport1.setValueAt(sList.get(i).getSp_Type(), i, 4);
            Tsport1.setValueAt(sList.get(i).getSp_Sched(), i, 5);
            Tsport1.setValueAt(sList.get(i).getSp_Date(), i, 6);
            Tsport1.setValueAt(sList.get(i).getFullname(), i, 7);
            }
            if(empty.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_searcSP1KeyReleased

    private void searcSP1KeyTyped(KeyEvent evt) {//GEN-FIRST:event_searcSP1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searcSP1KeyTyped

    private void jButton24ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)Tsport1.getModel();
        model.setRowCount(0);
        searcSP1.setText("Type ID OR Sports Name");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jMenuItem36ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        // TODO add your handling code here:
        update_Overall();
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem38ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        // TODO add your handling code here:
        delete_Overall();
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem35ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:
        
        new OverAll_Points_Window().show();
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem40ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        // TODO add your handling code here:
       
        new Ranking_Points_Window().show();
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void search_Event1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_search_Event1MouseClicked
        // TODO add your handling code here:
        search_Event1.setText("");
    }//GEN-LAST:event_search_Event1MouseClicked

    private void search_Event1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_search_Event1KeyReleased
        // TODO add your handling code here:
       DefaultTableModel model=(DefaultTableModel)overall.getModel();
       String empty = search_Event1.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<OverAll_Points> sList = new ArrayList<>();   
        View_Overall_Points QSD =new View_Overall_Points();
        
        try {
            sList = QSD.queryoverall_points(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            overall.setValueAt(sList.get(i).getOP_ID(), i, 0);
            overall.setValueAt(sList.get(i).getOP_Type(), i, 1);
            overall.setValueAt(sList.get(i).getOP_Percentage(), i, 2);
            overall.setValueAt(sList.get(i).getOP_Status(), i, 3);
            }
            if(empty.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_Event1KeyReleased

    private void jButton27ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)overall.getModel();
        model.setRowCount(0);
        search_Event1.setText("Enter  ID OR Event Type");
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jMenuItem34ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card8");
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem39ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        // TODO add your handling code here:
       
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card9");
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void partsearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_partsearchKeyReleased
        // TODO add your handling code here:
         try{
        DefaultTableModel model=(DefaultTableModel)parttable.getModel();
       String seacrh = partsearch.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
         }
        List<Participant> sList = new ArrayList<>();
        query_participant_details QTN =new query_participant_details();
        sList = QTN.query_participant(seacrh);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            parttable.setValueAt(sList.get(i).getId(), i, 0);
            parttable.setValueAt(sList.get(i).getFullname(), i, 1);
            parttable.setValueAt(sList.get(i).getGender(), i, 2);
            parttable.setValueAt(sList.get(i).getContactNum(), i, 3);
            parttable.setValueAt(sList.get(i).getAddress(), i, 4);
            parttable.setValueAt(sList.get(i).getStatus(), i, 5);
            parttable.setValueAt(sList.get(i).getTName(), i, 6);
            
            }
            if(seacrh.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_partsearchKeyReleased

    private void jButton26ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model1=(DefaultTableModel)parttable.getModel();
        DefaultTableModel model2=(DefaultTableModel)assigned.getModel();
        model2.setRowCount(0);
        model1.setRowCount(0);
        TImage6.setIcon(null);
        TImage6.setText("No Image Available");
        partsearch.setText("Participant ID OR Participant Name");
    }//GEN-LAST:event_jButton26ActionPerformed

    private void partsearchMouseClicked(MouseEvent evt) {//GEN-FIRST:event_partsearchMouseClicked
        // TODO add your handling code here:
        partsearch.setText("");
    }//GEN-LAST:event_partsearchMouseClicked

    private void parttableMouseClicked(MouseEvent evt) {//GEN-FIRST:event_parttableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model3=(DefaultTableModel)assigned.getModel();
        model3.setRowCount(0);
        try{
        List<Attended_SportsEvent> SList = new ArrayList<>();
        int row = parttable.getSelectedRow();
        String TableClick = (parttable.getModel().getValueAt(row, 0).toString());
        query_participant_details q = new query_participant_details();
        query_attendedEvent qaE = new query_attendedEvent();
        q.query_Image(TableClick);
        TImage6.setText("");
        SList = qaE.query_Assigned(TableClick);
        for (int i=0;i<SList.size();i++){
            model3.addRow(new Object[]{});
            assigned.setValueAt(SList.get(i).getSp_ID(), i, 0);
            assigned.setValueAt(SList.get(i).getEventName(), i, 1);
            assigned.setValueAt(SList.get(i).getEventType(), i, 2);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_parttableMouseClicked

    private void search_Event3MouseClicked(MouseEvent evt) {//GEN-FIRST:event_search_Event3MouseClicked
        // TODO add your handling code here:
        search_Event3.setText("");
    }//GEN-LAST:event_search_Event3MouseClicked

    private void search_Event3KeyReleased(KeyEvent evt) {//GEN-FIRST:event_search_Event3KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)rank1.getModel();
       String empty = search_Event3.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
        }
        List<Ranking_Points> sList = new ArrayList<>();   
        View_Overall_Points QSD =new View_Overall_Points();
        
        try {
            sList = QSD.queryCulturalSports_Rank(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            rank1.setValueAt(sList.get(i).getID(), i, 0);
            rank1.setValueAt(sList.get(i).getEvent_Name(), i, 1);
            rank1.setValueAt(sList.get(i).getEvent_Type(), i,2);
            }
            if(empty.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_search_Event3KeyReleased

    private void jButton29ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void rank1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_rank1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)ranktable.getModel();
       int row = rank1.getSelectedRow();
        String TableClick = (rank1.getModel().getValueAt(row, 0).toString());
       
        List<Ranking_Points> sList = new ArrayList<>();   
        View_Overall_Points QSD =new View_Overall_Points();
        
        try {
            sList = QSD.query_Rank(TableClick);
            model.setRowCount(0);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            ranktable.setValueAt(sList.get(i).getRank(), i, 0);
            ranktable.setValueAt(sList.get(i).getPoints(), i, 1);
            }
         
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_rank1MouseClicked

    private void search_Event3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_search_Event3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_Event3ActionPerformed

    private void teamtable1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_teamtable1MouseClicked
        // TODO add your handling code here:
         
         int row = teamtable1.getSelectedRow();
        String TableClick = (teamtable1.getModel().getValueAt(row, 0).toString());
        query_Team_Name QTN = new query_Team_Name();
        try {
            
            TImage7.setText("");
            QTN.getimage_forteam(TableClick);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_teamtable1MouseClicked

    private void teamsearch1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_teamsearch1MouseClicked
        // TODO add your handling code here:
        teamsearch1.setText("");
    }//GEN-LAST:event_teamsearch1MouseClicked

    private void teamsearch1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_teamsearch1KeyReleased
        // TODO add your handling code here:
        try{
        DefaultTableModel model=(DefaultTableModel)teamtable1.getModel();
       String seacrh = teamsearch1.getText();
       if (model.getRowCount() != 0){
            model.setRowCount(0);
         }
        List<Team> sList = new ArrayList<>();
        query_Team_Name QTN =new query_Team_Name();
        sList = QTN.query_whole_team_details(seacrh);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            teamtable1.setValueAt(sList.get(i).getTeam_ID(), i, 0);
            teamtable1.setValueAt(sList.get(i).getTeam_Name(), i, 1);
            teamtable1.setValueAt(sList.get(i).getTeam_Number(), i, 2);
            teamtable1.setValueAt(sList.get(i).getTeam_Status(), i, 3);
            }
            if(seacrh.isEmpty()){
        model.setRowCount(0);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_teamsearch1KeyReleased

    private void jButton28ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)teamtable1.getModel();
        model.setRowCount(0);
        teamsearch1.setText("Team ID OR Team Name");
        TImage7.setIcon(null);
        TImage7.setText("No Image Available");
    }//GEN-LAST:event_jButton28ActionPerformed

    private void addcrit1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addcrit1ActionPerformed
        // TODO add your handling code here:
       
        new Criteria_Window().show();
    }//GEN-LAST:event_addcrit1ActionPerformed

    private void add_userActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_userActionPerformed
        // TODO add your handling code here:
       
        new Users_Account_Window().show();
    }//GEN-LAST:event_add_userActionPerformed

    private void add_SportActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_SportActionPerformed
        // TODO add your handling code here:
        
        new Sports_Window().show();
    }//GEN-LAST:event_add_SportActionPerformed

    private void add_PartActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_PartActionPerformed
        // TODO add your handling code here:
       
        new Participants_Window().show();
    }//GEN-LAST:event_add_PartActionPerformed

    private void add_teamActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_teamActionPerformed
        // TODO add your handling code here:
       
        new Team_Window().show();
    }//GEN-LAST:event_add_teamActionPerformed

    private void add_PointsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_PointsActionPerformed
        // TODO add your handling code here:
       
        new OverAll_Points_Window().show();
    }//GEN-LAST:event_add_PointsActionPerformed

    private void add_rankActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_rankActionPerformed
        // TODO add your handling code here:
        
        new Ranking_Points_Window().show();
    }//GEN-LAST:event_add_rankActionPerformed

    private void update_pointsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_update_pointsActionPerformed
        // TODO add your handling code here:
       update_Overall();
    }//GEN-LAST:event_update_pointsActionPerformed

    private void resetActionPerformed(ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
      reset_rank();
       
        
    }//GEN-LAST:event_resetActionPerformed

    private void delete_categActionPerformed(ActionEvent evt) {//GEN-FIRST:event_delete_categActionPerformed
        // TODO add your handling code here:
        int row = ECategory.getSelectedRow();
       String TableClick = (ECategory.getModel().getValueAt(row, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Category(Integer.parseInt(TableClick));
       
       DefaultTableModel model=(DefaultTableModel)ECategory.getModel();
       model.setRowCount(0);
       search_Event.setText("Enter Event ID OR Event Name");
    }//GEN-LAST:event_delete_categActionPerformed

    private void delete_critActionPerformed(ActionEvent evt) {//GEN-FIRST:event_delete_critActionPerformed
        // TODO add your handling code here:
        int row = critable.getSelectedRow();
       String TableClick = (critable.getModel().getValueAt(row, 0).toString());
       Update_Cul_Event UE = new Update_Cultural_Event();
       UE.Delete_Criteria(Integer.parseInt(TableClick));
       JOptionPane.showMessageDialog(null,"Transaction Granted");
       DefaultTableModel model=(DefaultTableModel)critable.getModel();
       model.setRowCount(0);
       search_Event.setText("Enter Event ID OR Event Name");
    }//GEN-LAST:event_delete_critActionPerformed

    private void dele_userActionPerformed(ActionEvent evt) {//GEN-FIRST:event_dele_userActionPerformed
        // TODO add your handling code here:
        delete_user(); 
    }//GEN-LAST:event_dele_userActionPerformed

    private void dele_PartActionPerformed(ActionEvent evt) {//GEN-FIRST:event_dele_PartActionPerformed
        // TODO add your handling code here:
        delete_Participant();
    }//GEN-LAST:event_dele_PartActionPerformed

    private void del_teamActionPerformed(ActionEvent evt) {//GEN-FIRST:event_del_teamActionPerformed
        // TODO add your handling code here:
         delete_Team();
    }//GEN-LAST:event_del_teamActionPerformed

    private void dele_SportActionPerformed(ActionEvent evt) {//GEN-FIRST:event_dele_SportActionPerformed
        // TODO add your handling code here:
          delete_Sport();
    }//GEN-LAST:event_dele_SportActionPerformed

    private void delete_pointsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_delete_pointsActionPerformed
        // TODO add your handling code here:
       delete_Overall();
    }//GEN-LAST:event_delete_pointsActionPerformed

    private void jMenuItem30ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        delete_Cultural();
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem26ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        delete_Sport();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem42ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        // TODO add your handling code here:
        reset_rank();
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem27ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        delete_user();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem17ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        delete_Participant();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem44ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem44ActionPerformed
        // TODO add your handling code here:
        new Criteria_Window().show();
    }//GEN-LAST:event_jMenuItem44ActionPerformed

    private void addcrit3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addcrit3ActionPerformed
        // TODO add your handling code here:
        new Criteria_Window().show();
    }//GEN-LAST:event_addcrit3ActionPerformed

    private void jMenuItem8ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        new Config_Window().show();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        new Cultural_Event_Window().show();
        CardLayout cl = (CardLayout) Cultural_Event_Window.main.getLayout();
        cl.show(Cultural_Event_Window.main,"card4");
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void teamtable1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_teamtable1KeyReleased
        // TODO add your handling code here:
        int row = teamtable1.getSelectedRow();
        String TableClick = (teamtable1.getModel().getValueAt(row, 0).toString());
        query_Team_Name QTN = new query_Team_Name();
        try {
            
            TImage7.setText("");
            QTN.getimage_forteam(TableClick);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_teamtable1KeyReleased

    private void rank1KeyReleased(KeyEvent evt) {//GEN-FIRST:event_rank1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)ranktable.getModel();
       int row = rank1.getSelectedRow();
        String TableClick = (rank1.getModel().getValueAt(row, 0).toString());
       
        List<Ranking_Points> sList = new ArrayList<>();   
        View_Overall_Points QSD =new View_Overall_Points();
        
        try {
            sList = QSD.query_Rank(TableClick);
            model.setRowCount(0);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            ranktable.setValueAt(sList.get(i).getRank(), i, 0);
            ranktable.setValueAt(sList.get(i).getPoints(), i, 1);
            }
         
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_rank1KeyReleased

    private void parttableKeyReleased(KeyEvent evt) {//GEN-FIRST:event_parttableKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model3=(DefaultTableModel)assigned.getModel();
        model3.setRowCount(0);
        try{
        List<Attended_SportsEvent> SList = new ArrayList<>();
        int row = parttable.getSelectedRow();
        String TableClick = (parttable.getModel().getValueAt(row, 0).toString());
        query_participant_details q = new query_participant_details();
        query_attendedEvent qaE = new query_attendedEvent();
        q.query_Image(TableClick);
        TImage6.setText("");
        SList = qaE.query_Assigned(TableClick);
        for (int i=0;i<SList.size();i++){
            model3.addRow(new Object[]{});
            assigned.setValueAt(SList.get(i).getSp_ID(), i, 0);
            assigned.setValueAt(SList.get(i).getEventName(), i, 1);
            assigned.setValueAt(SList.get(i).getEventType(), i, 2);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_parttableKeyReleased

    private void add_subEventActionPerformed(ActionEvent evt) {//GEN-FIRST:event_add_subEventActionPerformed
        // TODO add your handling code here:
        new Cultural_Event_Window().show();
        CardLayout cl = (CardLayout) Cultural_Event_Window.main.getLayout();
        cl.show(Cultural_Event_Window.main,"card4");
    }//GEN-LAST:event_add_subEventActionPerformed

    private void report1eventItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_report1eventItemStateChanged
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)report1.getModel();
       String empty = (String) report1event.getSelectedItem();
       
            model.setRowCount(0);
        
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._report1_cul(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            report1.setValueAt(sList.get(i).getCul_Name(), i, 0);
            report1.setValueAt(sList.get(i).getIdreport(), i, 1);
            report1.setValueAt(sList.get(i).getCul_Percentage(), i, 2);
            
            }
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_report1eventItemStateChanged

    private void jTabbedPane2MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)report2cul.getModel();
         model.setRowCount(0);
        
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._report2_cul();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            report2cul.setValueAt(sList.get(i).getCul_Name(), i, 0);
            report2cul.setValueAt(sList.get(i).getCul_Percentage(), i, 1);
            
            }
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void jTabbedPane3MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jTabbedPane3MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)sportotal.getModel();
         model.setRowCount(0);
        
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._report2_spo();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            sportotal.setValueAt(sList.get(i).getCul_Name(), i, 0);
            sportotal.setValueAt(sList.get(i).getCul_Percentage(), i, 1);
            
            }
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_jTabbedPane3MouseClicked

    private void jTabbedPane1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        try{
        new reset_rank()._runsort();
        
        DefaultTableModel model=(DefaultTableModel)allscore.getModel();
         model.setRowCount(0);
        
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        
            sList = QLP.overall();
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            allscore.setValueAt(sList.get(i).getCul_Name(), i, 0);
            allscore.setValueAt(sList.get(i).getCul_Percentage(), i, 1);
            
            }
       
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void sportscomboItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_sportscomboItemStateChanged
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)sporttable.getModel();
       String empty = (String) sportscombo.getSelectedItem();
       
            model.setRowCount(0);
        
        List<Cultural_Event> sList = new ArrayList<Cultural_Event>();   
        _Select_Cultural_Events QLP =new _Select_Cultural_Events();
        
        try {
            sList = QLP._report1_spo(empty);
            for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            sporttable.setValueAt(sList.get(i).getCul_Name(), i, 0);
            sporttable.setValueAt(sList.get(i).getIdreport(), i, 1);
            sporttable.setValueAt(sList.get(i).getCul_Percentage(), i, 2);
            
            }
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_sportscomboItemStateChanged

    private void jButton10ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
                if(report1event.getSelectedItem().equals("Select-Event-:")){
                        JOptionPane.showMessageDialog(null,"Please Select Event","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                JasperDesign jasperDesign = JRXmlLoader.load("cultural_by_eventReport.jrxml");
                String sql = "CALL _cultural_report('"+report1event.getSelectedItem()+"');";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                }
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
                
                JasperDesign jasperDesign = JRXmlLoader.load("cultural_total.jrxml");
                String sql = "SELECT *,points,team_name,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname FROM _totalcultural_score,Accounts t CROSS JOIN Company Where Acc_TYpe = 'Cultural Judges' group by points DESC";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
               
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
                if(sportscombo.getSelectedItem().equals("Select-Event-:")){
                        JOptionPane.showMessageDialog(null,"Please Select Event","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                JasperDesign jasperDesign = JRXmlLoader.load("sport_by_eventReport.jrxml");
                String sql = "CALL _sport_report('"+sportscombo.getSelectedItem()+"')";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                }
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("sport_total.jrxml");
                String sql = "SELECT *,points,team_name,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname FROM _totalsport_score,Accounts t CROSS JOIN Company Where Acc_TYpe = 'Sports Judges' group by points DESC;";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("overall_result.jrxml");
                String sql = "SELECT *,Points,Team_Name,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname FROM `sort`,Accounts a CROSS JOIN Company Group by Points DESC;";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new Controller().show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void detaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detaisActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card12");
    }//GEN-LAST:event_detaisActionPerformed

    private void detais1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detais1ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card12");
    }//GEN-LAST:event_detais1ActionPerformed

    private void detais2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detais2ActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main,"card12");
    }//GEN-LAST:event_detais2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        getReportCRIandCAT();
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        getReportCRIandCAT();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
        getReportCRIandCAT();
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        try{
        DefaultTableModel model1=(DefaultTableModel)jTable5.getModel();
        List<Event_Category> sList = new ArrayList<>();
         List<Criteria_Cultural> cList = new ArrayList<>();
        Query_Reports_Cultural QRP = new Query_Reports_Cultural();
        sList = QRP.second((String)jComboBox2.getSelectedItem(), (String)jComboBox4.getSelectedItem());
        cList = QRP.CriteriaDetailsforsearch2((String)jComboBox2.getSelectedItem());
        model1.setRowCount(0);
        jComboBox3.removeAllItems();
        for(int i = 0;i<sList.size();i++){
            model1.addRow(new Object[]{});
            jTable5.setValueAt(sList.get(i).getCategory_Name(), i, 0);
            jTable5.setValueAt(sList.get(i).getCategory_Per(), i, 1);
        }
        for(int i = 0;i<cList.size();i++){
            String ccriname = cList.get(i).getCr_Description();
            jComboBox3.addItem(ccriname);
        }
        }catch(Exception e){
            System.out.print(e);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        try{
            DefaultTableModel model1=(DefaultTableModel)jTable6.getModel();
            List<Criteria_Cultural> sList = new ArrayList<>();
            Query_Reports_Cultural QRP = new Query_Reports_Cultural();
            sList = QRP.third((String)jComboBox3.getSelectedItem(), (String)jComboBox4.getSelectedItem(), (String) jComboBox5.getSelectedItem());
            model1.setRowCount(0);
        for(int i = 0;i<sList.size();i++){
            model1.addRow(new Object[]{});
            jTable6.setValueAt(sList.get(i).getCr_Description(), i, 0);
            jTable6.setValueAt(sList.get(i).getCategory_ID(), i, 1);
        }
        }catch(Exception e){
            System.out.print(e);
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
        try{
        DefaultTableModel model=(DefaultTableModel)jTable7.getModel();
        List<Sport_Event> sList = new ArrayList<>();   
        Query_Reports_Cultural QSD =new Query_Reports_Cultural();
        sList = QSD.spreport((String)jComboBox6.getSelectedItem());
        model.setRowCount(0);
        for (int i=0;i<sList.size();i++){
            model.addRow(new Object[]{});
            jTable7.setValueAt(sList.get(i).getFullname(), i, 0);
            jTable7.setValueAt(sList.get(i).getSp_Percentage(), i, 1);
        }
        
        }catch(Exception e){
            System.out.print(e);
        }
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void OVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OVActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_OverAllResults.jrxml");
                String sql = "SELECT Comp_Name,Comp_Org,Comp_add,Comp_title,a.CODE,a.Team_Name,(a.TOTAL + b.TOTAL) Score FROM rpt_overall_cultural a JOIN rpt_overall_sports b ON a.code = b.code CROSS JOIN Company C CROSS JOIN Accounts acc group by a.code ASC";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_OVActionPerformed

    private void CPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_Overall_Cultural_Results.jrxml");
                String sql = "select * from rpt_overall_cultural CROSS JOIN Company ORDER BY TOTAL Desc";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_CPActionPerformed

    private void SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPActionPerformed
        // TODO add your handling code here:
         try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_overAll_sports_result.jrxml");
                String sql = "Select * from rpt_overall_sports cross join company c ORDER BY TOTAL DESC";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_SPActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        try{
        Runtime.getRuntime().exec("hh.exe help.chm");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem().equals("Choose-:")&&jComboBox4.getSelectedItem().equals("Choose-Type-of-Judging:")&&jComboBox5.getSelectedItem().equals("Choose-Event-Type-:")){
            JOptionPane.showMessageDialog(null,"Please Check the fields before printing","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(jComboBox4.getSelectedItem().equals("INDIVIDUAL")&&jComboBox5.getSelectedItem().equals("With Category")){
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_cultural_detailed_reports.jrxml");
                String sql = "SELECT *,Contestant as name,SUM(total_cultural) as total FROM rpt_cultural_with_category CROSS JOIN Company c Where Cul_Name = $P{culname} group by contestant ORDER BY Cul_Name,total DESC";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }   
       }else if(jComboBox4.getSelectedItem().equals("BY TEAM")&&jComboBox5.getSelectedItem().equals("With Category")){
           try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_cultural_detailed_reports_byteam.jrxml");
                String sql = "SELECT *,Team_Name as name,SUM(total_cultural) as total FROM rpt_cultural_with_category CROSS JOIN Company c Where Cul_Name = $P{culname}  group by Team_ID ORDER BY Cul_Name,total DESC;";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
       }
       else if(jComboBox4.getSelectedItem().equals("INDIVIDUAL")&&jComboBox5.getSelectedItem().equals("With No Category")){
          try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_cultural_detailed_report_no_category.jrxml");
                String sql = "SELECT *,Contestant as name,SUM(total_cultural) as total FROM rpt_cultural_withno_category CROSS JOIN Company c where cul_name = $P{culname}  group by contestant ORDER BY Cul_Name,total DESC;";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        } 
       } else if(jComboBox4.getSelectedItem().equals("BY TEAM")&&jComboBox5.getSelectedItem().equals("With No Category")){
          try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_cultural_detailedreport_nocategory_byteam.jrxml");
                String sql = "SELECT *,Team_Name as name,SUM(total_cultural) as total FROM rpt_cultural_withno_category CROSS JOIN Company c   group by Team_Name ORDER BY Cul_Name,total DESC;";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        } 
       }
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(jComboBox2.getSelectedItem().equals("")&&jComboBox4.getSelectedItem().equals("Choose-Type-of-Judging:")){
            JOptionPane.showMessageDialog(null,"Please Check the fields before printing","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(jComboBox4.getSelectedItem().equals("INDIVIDUAL")){
            try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
           parameters.put("category", (String) jComboBox2.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_Category.jrxml");
                String sql = "SELECT * FROM rpt_category CROSS JOIN Company c where category_Name = $P{category} and Cul_Name = $P{culname}";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        }else if(jComboBox4.getSelectedItem().equals("BY TEAM")){
             try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("culname",(String)jComboBox1.getSelectedItem());
           parameters.put("category", (String) jComboBox2.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_category_by_team.jrxml");
                String sql = "SELECT * FROM rpt_category CROSS JOIN Company c where category_Name = $P{category} and Cul_Name = $P{culname} group by team_ID";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        if(jComboBox6.getSelectedItem().equals("Choose-Sports Event:")){
            JOptionPane.showMessageDialog(null,"Please Check the fields before printing","Error",JOptionPane.ERROR_MESSAGE); 
        }
        else{
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           Map parameters = new HashMap();
           parameters.put("spname",(String)jComboBox6.getSelectedItem());
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_sports_detailed_report.jrxml");
                String sql = "SELECT * FROM rpt_sportsevent CROSS JOIN Company c where SP_Name = $P{spname} ORDER BY total_Sports DESC";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_list_of_culutral_events.jrxml");
                String sql = "select *,cul_type,cul_name,rp_rank,rp_point from cultural_events ce   join cultural_ranking_points  crp on ce.cul_id = crp.cul_id CROSS JOIN COMPANY c Where ce.cul_status = 'Active'";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
           Connection con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           
            try {
                
                // TODO add your handling code here:
               
                JasperDesign jasperDesign = JRXmlLoader.load("rpt_list_of_sports_events.jrxml");
                String sql = "select * from sports_events sp join sports_ranking_points srp on srp.sp_id = sp.sp_id cross join company c where sp.sp_status = 'Active'";
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText(sql);
                            jasperDesign.setQuery(newQuery);
                            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign );
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                            JasperViewer.viewReport(jasperPrint,false);
                
                
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new administrator().setVisible(true);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        time.setText(String.format("%tI:%<tM:%<tS %<Tp", new Date()));
        
    }
    private class LastProcess extends WindowAdapter {
        @Override
   public void windowClosing(WindowEvent e){
       int dialog =  JOptionPane.showConfirmDialog(null,"Do you want to close the system?","Message",JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
        dispose();
        new login().show();       
        }
       else if(dialog == JOptionPane.NO_OPTION) {}
 }
    }
    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JMenuItem Add_Cultural_Event;
    javax.swing.JMenuItem Add_Event_Category;
    javax.swing.JMenuItem Add_Event_Criteria;
    javax.swing.JMenuItem CP;
    javax.swing.JPopupMenu Category_Event;
    javax.swing.JPopupMenu Cultural_Events;
    javax.swing.JMenuItem Delete;
    javax.swing.JTable ECategory;
    javax.swing.JTable EventD;
    javax.swing.JPopupMenu Event_Criteria;
    javax.swing.JMenuItem OV;
    javax.swing.JPopupMenu OverAll_Points;
    javax.swing.JPanel Overall_Points_Window;
    javax.swing.JPanel Participant_Window;
    javax.swing.JPopupMenu Participant_popup;
    javax.swing.JPanel Rank_Window;
    javax.swing.JMenuItem SP;
    javax.swing.JTextField SUser1;
    javax.swing.JPopupMenu Sport_Event;
    javax.swing.JPanel Sport_Window;
    public static javax.swing.JLabel TImage4;
    public static javax.swing.JLabel TImage6;
    public static javax.swing.JLabel TImage7;
    javax.swing.JPopupMenu Team;
    javax.swing.JTable Tsport1;
    javax.swing.JMenuItem Update_Cultural_Event;
    javax.swing.JMenuItem Update_Event_Category;
    javax.swing.JMenuItem Update_Event_Criteria;
    javax.swing.JMenuItem Update_Participant;
    javax.swing.JMenuItem Update_Sport;
    javax.swing.JMenuItem Update_Team;
    javax.swing.JMenuItem Update_User;
    javax.swing.JTable UserTable1;
    javax.swing.JPanel User_Window;
    javax.swing.JPopupMenu Users;
    javax.swing.JMenuItem add_Part;
    javax.swing.JMenuItem add_Points;
    javax.swing.JMenuItem add_Sport;
    javax.swing.JMenuItem add_rank;
    javax.swing.JMenuItem add_subEvent;
    javax.swing.JMenuItem add_team;
    javax.swing.JMenuItem add_user;
    javax.swing.JMenuItem addcrit1;
    javax.swing.JMenuItem addcrit3;
    public static javax.swing.JLabel admin;
    javax.swing.JTable allscore;
    javax.swing.JMenuItem assign_event;
    javax.swing.JTable assigned;
    javax.swing.JPopupMenu attended_Event;
    javax.swing.ButtonGroup buttonGroup1;
    javax.swing.ButtonGroup buttonGroup2;
    javax.swing.ButtonGroup buttonGroup3;
    javax.swing.ButtonGroup buttonGroup4;
    javax.swing.JTable critable;
    javax.swing.JPopupMenu culutralPrint;
    javax.swing.JLabel date;
    javax.swing.JMenuItem del_team;
    javax.swing.JMenuItem dele_Part;
    javax.swing.JMenuItem dele_Sport;
    javax.swing.JMenuItem dele_user;
    javax.swing.JMenuItem delete_categ;
    javax.swing.JMenuItem delete_crit;
    javax.swing.JMenuItem delete_cul;
    javax.swing.JMenuItem delete_points;
    javax.swing.JPanel detailed;
    javax.swing.JMenuItem detais;
    javax.swing.JMenuItem detais1;
    javax.swing.JMenuItem detais2;
    javax.swing.JPanel event;
    javax.swing.JPanel home;
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton10;
    javax.swing.JButton jButton11;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton23;
    javax.swing.JButton jButton24;
    javax.swing.JButton jButton26;
    javax.swing.JButton jButton27;
    javax.swing.JButton jButton28;
    javax.swing.JButton jButton29;
    javax.swing.JButton jButton3;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton6;
    javax.swing.JButton jButton7;
    javax.swing.JButton jButton8;
    javax.swing.JButton jButton9;
    javax.swing.JComboBox jComboBox1;
    javax.swing.JComboBox jComboBox2;
    javax.swing.JComboBox jComboBox3;
    javax.swing.JComboBox jComboBox4;
    javax.swing.JComboBox jComboBox5;
    javax.swing.JComboBox jComboBox6;
    javax.swing.JLabel jLabel100;
    javax.swing.JLabel jLabel101;
    javax.swing.JLabel jLabel102;
    javax.swing.JLabel jLabel103;
    javax.swing.JLabel jLabel107;
    javax.swing.JLabel jLabel108;
    javax.swing.JLabel jLabel109;
    javax.swing.JLabel jLabel110;
    javax.swing.JLabel jLabel111;
    javax.swing.JLabel jLabel112;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel38;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel40;
    javax.swing.JLabel jLabel42;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel87;
    javax.swing.JLabel jLabel88;
    javax.swing.JLabel jLabel89;
    javax.swing.JLabel jLabel9;
    javax.swing.JLabel jLabel90;
    javax.swing.JLabel jLabel91;
    javax.swing.JLabel jLabel92;
    javax.swing.JLabel jLabel93;
    javax.swing.JLabel jLabel94;
    javax.swing.JLabel jLabel95;
    javax.swing.JLabel jLabel96;
    javax.swing.JLabel jLabel97;
    javax.swing.JLabel jLabel98;
    javax.swing.JLabel jLabel99;
    javax.swing.JMenu jMenu1;
    javax.swing.JMenu jMenu2;
    javax.swing.JMenu jMenu3;
    javax.swing.JMenu jMenu4;
    javax.swing.JMenu jMenu5;
    javax.swing.JMenu jMenu6;
    javax.swing.JMenu jMenu7;
    javax.swing.JMenu jMenu8;
    javax.swing.JMenu jMenu9;
    javax.swing.JMenuBar jMenuBar2;
    javax.swing.JMenuItem jMenuItem1;
    javax.swing.JMenuItem jMenuItem10;
    javax.swing.JMenuItem jMenuItem11;
    javax.swing.JMenuItem jMenuItem12;
    javax.swing.JMenuItem jMenuItem13;
    javax.swing.JMenuItem jMenuItem14;
    javax.swing.JMenuItem jMenuItem15;
    javax.swing.JMenuItem jMenuItem17;
    javax.swing.JMenuItem jMenuItem18;
    javax.swing.JMenuItem jMenuItem2;
    javax.swing.JMenuItem jMenuItem20;
    javax.swing.JMenuItem jMenuItem21;
    javax.swing.JMenuItem jMenuItem22;
    javax.swing.JMenuItem jMenuItem23;
    javax.swing.JMenuItem jMenuItem24;
    javax.swing.JMenuItem jMenuItem26;
    javax.swing.JMenuItem jMenuItem27;
    javax.swing.JMenuItem jMenuItem28;
    javax.swing.JMenuItem jMenuItem29;
    javax.swing.JMenuItem jMenuItem3;
    javax.swing.JMenuItem jMenuItem30;
    javax.swing.JMenuItem jMenuItem31;
    javax.swing.JMenuItem jMenuItem34;
    javax.swing.JMenuItem jMenuItem35;
    javax.swing.JMenuItem jMenuItem36;
    javax.swing.JMenuItem jMenuItem38;
    javax.swing.JMenuItem jMenuItem39;
    javax.swing.JMenuItem jMenuItem4;
    javax.swing.JMenuItem jMenuItem40;
    javax.swing.JMenuItem jMenuItem42;
    javax.swing.JMenuItem jMenuItem44;
    javax.swing.JMenuItem jMenuItem5;
    javax.swing.JMenuItem jMenuItem6;
    javax.swing.JMenuItem jMenuItem7;
    javax.swing.JMenuItem jMenuItem8;
    javax.swing.JMenuItem jMenuItem9;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel10;
    javax.swing.JPanel jPanel11;
    javax.swing.JPanel jPanel12;
    javax.swing.JPanel jPanel13;
    javax.swing.JPanel jPanel14;
    javax.swing.JPanel jPanel15;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel32;
    javax.swing.JPanel jPanel33;
    javax.swing.JPanel jPanel34;
    javax.swing.JPanel jPanel35;
    javax.swing.JPanel jPanel37;
    javax.swing.JPanel jPanel38;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel47;
    javax.swing.JPanel jPanel48;
    javax.swing.JPanel jPanel5;
    javax.swing.JPanel jPanel50;
    javax.swing.JPanel jPanel51;
    javax.swing.JPanel jPanel52;
    javax.swing.JPanel jPanel6;
    javax.swing.JPanel jPanel7;
    javax.swing.JPanel jPanel8;
    javax.swing.JPanel jPanel9;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane10;
    javax.swing.JScrollPane jScrollPane11;
    javax.swing.JScrollPane jScrollPane12;
    javax.swing.JScrollPane jScrollPane13;
    javax.swing.JScrollPane jScrollPane14;
    javax.swing.JScrollPane jScrollPane15;
    javax.swing.JScrollPane jScrollPane16;
    javax.swing.JScrollPane jScrollPane17;
    javax.swing.JScrollPane jScrollPane18;
    javax.swing.JScrollPane jScrollPane19;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane24;
    javax.swing.JScrollPane jScrollPane25;
    javax.swing.JScrollPane jScrollPane26;
    javax.swing.JScrollPane jScrollPane27;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JScrollPane jScrollPane5;
    javax.swing.JScrollPane jScrollPane6;
    javax.swing.JScrollPane jScrollPane7;
    javax.swing.JScrollPane jScrollPane8;
    javax.swing.JScrollPane jScrollPane9;
    javax.swing.JSeparator jSeparator1;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JTabbedPane jTabbedPane2;
    javax.swing.JTabbedPane jTabbedPane3;
    javax.swing.JTabbedPane jTabbedPane5;
    javax.swing.JTable jTable1;
    javax.swing.JTable jTable2;
    javax.swing.JTable jTable3;
    javax.swing.JTable jTable4;
    javax.swing.JTable jTable5;
    javax.swing.JTable jTable6;
    javax.swing.JTable jTable7;
    javax.swing.JPanel main;
    javax.swing.JTable overall;
    javax.swing.JPopupMenu overallPrint;
    javax.swing.JTextField partsearch;
    javax.swing.JTable parttable;
    javax.swing.JPopupMenu rank;
    javax.swing.JTable rank1;
    javax.swing.JPopupMenu rank_Event;
    javax.swing.JTable ranktable;
    javax.swing.JPanel report;
    javax.swing.JTable report1;
    javax.swing.JComboBox report1event;
    javax.swing.JTable report2cul;
    javax.swing.JPanel reports;
    javax.swing.JMenuItem reset;
    javax.swing.JTextField searcSP1;
    javax.swing.JTextField search_Event;
    javax.swing.JTextField search_Event1;
    javax.swing.JTextField search_Event3;
    javax.swing.JPopupMenu sportPrint;
    javax.swing.JTable sportotal;
    javax.swing.JComboBox sportscombo;
    javax.swing.JTable sporttable;
    javax.swing.JPanel team_window;
    javax.swing.JTextField teamsearch1;
    javax.swing.JTable teamtable1;
    javax.swing.JLabel time;
    javax.swing.JMenuItem update_points;
    public static javax.swing.JLabel userID;
    // End of variables declaration//GEN-END:variables


}
