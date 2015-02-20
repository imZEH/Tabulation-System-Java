/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.*;
import Tabulation.Graphical_User_Interface.Client_Module.Scorer;
import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neil
 */
public class Duplicate {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private CallableStatement callableStatement = null;
    private ResultSet rs = null;
    public String  usernDup(ResultSet rs)throws Exception{
        String a = null;
        String b =null;
        if(rs.next()){
           a  = rs.getString(1);
            
        }
        if(a.equals("Username Already Exist")){
            JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,a);
            Users_Account_Window.aFName.setText("");
            Users_Account_Window.aMName.setText("");
            Users_Account_Window.aLName.setText("");
            Users_Account_Window.astatus.setSelectedItem("Active");
            Users_Account_Window.atype.setSelectedItem("--Select--Type--:");
            Users_Account_Window.auser.setText("");
            Users_Account_Window.apass.setText("");
            Users_Account_Window.aaddress.setText("");
            Users_Account_Window.anum.setText("");
            Users_Account_Window.UImage.setIcon(null);
            Users_Account_Window.UImage.setText("No Image Available");
            Users_Account_Window.aconpass.setText("");
            Users_Account_Window.userpromt.setText("");
            Users_Account_Window.passpromt.setText("");
            Users_Account_Window.confirmprompt.setText("");
        }
        return a;
        }
    
    public String dup_Overall(ResultSet rs) throws Exception{
        String a = null;
        String b =null;
        if(rs.next()){
           a  = rs.getString(1);
            
        }
        switch (a) {
            case "Data already exist otherwise Delete old data":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "The Overall Points will contain total of More than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                OverAll_Points_Window.evntType.setSelectedItem("--Select--Event--Type--:");
                OverAll_Points_Window.percentage.setSelectedItem("5");
                OverAll_Points_Window.status.setSelectedItem("Active");
                break;
        }
        
        return a;
    }
    
    public String dup_assignedCultural(ResultSet rs) throws Exception{
        String a = null;
        String b =null;
        if(rs.next()){
           a  = rs.getString(1);
            
        }
        if(a.equals("This Event already assigned on this participant")){
            JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
        }
        else{JOptionPane.showMessageDialog(null,a);
            
            }
        
        return a;
    }
    
    public String dup_Cultural(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
            a = rs.getString(1);
        }
        switch (a) {
            case "This Event will contain total of More than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Event Already Exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Cultural_Event_Window.EName.setText("");
                Cultural_Event_Window.EPercent.setSelectedItem("5");
                Cultural_Event_Window.majorevent.setSelected(true);
                Cultural_Event_Window.EActive.setSelected(true);
                Cultural_Event_Window.EIndiv.setSelected(true);
                Cultural_Event_Window.Hour.setText("00");
                Cultural_Event_Window.Min.setText("00");
                Cultural_Event_Window.AMPM.setSelectedItem("AM");
                break;
        }
        
        return a;
    }
    
    public String dup_SubEvent(ResultSet rs) throws Exception{
        String a = null;
        if(rs.next()){
            a = rs.getString(1);
        }
        switch (a) {
            case "Sub Event already exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
                case "ONLINE Event already exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "This Sub Event will contain total of more than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Cultural_Event_Window.event.setSelectedItem("--Select--Event--:");
                Cultural_Event_Window.subname.setText("");
                Cultural_Event_Window.subpercent.setSelectedItem("5");
                Cultural_Event_Window.judgeType.setSelectedItem("OFFLINE");
                
                                
                break;
        }
        return a;
    }
    
    public String dup_Criteria(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
            a = rs.getString(1);
        }
        switch (a) {
            case "Criteria already exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "This Criteria will contain more than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Criteria_Window.event.setSelectedItem("--Select--Event--:");
        Criteria_Window.crName.setText("");
        Criteria_Window.crPercent.setSelectedItem("5");
                 break;
        }
          return a;  
    }
    
    public String dup_Participant(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
            a = rs.getString(1);
        }
        switch (a) {
            case "Participant Already Exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Participants_Window.Pfname.setText("");
                Participants_Window.Pmname.setText("");
                Participants_Window.Plname.setText("");
                Participants_Window.Pmale.setSelected(true);
                Participants_Window.PStatus.setSelectedItem("Active");       
                Participants_Window.Paddress.setText("");        
                Participants_Window.Pcontact.setText("");
                Participants_Window.PTeam.setSelectedItem("--Select--Team--:");
                Participants_Window.Udirectory.setText("no-image.jpg");
                Participants_Window.PImage.setIcon(null);
                Participants_Window.PImage.setText("No Image Available");
                        break;
        }
        return a;
    }
    
    public String dup_AttendedCultural(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
        a = rs.getString(1);
    }
        switch (a) {
            case "This Event already assigned on this participant":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                break;
        }
        return a;
    }
    
    public String dup_Sports_Event(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
           a = rs.getString(1);         
          }
        switch(a){
            case "Event Already Exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "This Event will contain total of More than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Sports_Window.SPname.setText("");
                Sports_Window.SPpercent.setSelectedItem("5");
                Sports_Window.SPstatus.setSelectedItem("Active");
                Sports_Window.SPtype.setSelectedItem("--Select--Type--:");
                Sports_Window.Hour.setText("00");
                Sports_Window.Min.setText("00");
                Sports_Window.AMPM.setSelectedItem("AM");
                break;
        }
        return a;
    }
    
    
    public String dup_Ranking_Points(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
           a = rs.getString(1);         
          }
        switch(a){
            case "This Rank already Exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Ranking_Points_Window.RPrank.setSelectedItem("-Select--Rank--:");
                Ranking_Points_Window.RPpercent.setSelectedItem("5");
                Ranking_Points_Window.Parti_ID.setText("");
                Ranking_Points_Window.RPname.setSelectedItem("--Choose--:");
                break;
            case "This Event will contain more than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return a;
    }
    
    public String dup_Team(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
           a = rs.getString(1);         
          }
        switch(a){
            case "Team Name Already Exist":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                Team_Window.Udirectory.setText("no-image.jpg");
                Team_Window.Tname.setText("");
                Team_Window.Tnumber.setText("");
                Team_Window.Tstatus.setSelectedItem("Active");
                Team_Window.TImage.setIcon(null);
                Team_Window.TImage.setText("No Image Available");
                Team_Window.color.setText("#6a00d7");
                break;
            
        }
        return a;
    }
    
    public String dup_Overall_Points(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
           a = rs.getString(1);         
          }
        switch(a){
            case "The Overall Points will contain total of More than 100% this is invalid":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "Successfully Updated":
                JOptionPane.showMessageDialog(null,a);
                break;
            
        }
        return a;
    }
    
    
    public String donescoring(ResultSet rs)throws Exception{
        String a = null;
        if(rs.next()){
           a = rs.getString(1);         
          }
        switch(a){
            case "You had already score this event":
                JOptionPane.showMessageDialog(null,a,"Error",JOptionPane.ERROR_MESSAGE);
                
                break;
            case "Successfully Saved":
                JOptionPane.showMessageDialog(null,a);
                
                break;
            
        }
        return a;
    }
    
    
    
    public String getdatausername(String data)throws Exception{
       
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Duplicate_Username('"+data+"')");
        String data1 = null;
        while(rs.next()){
        data1 = rs.getString(1);
        }
        con.close();
        return data1;
    } 
    
    
   
}
