/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;


import Tabulation.Graphical_User_Interface.Administrator_Module.Users_Account_Window;
import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.getters_setters.Accounts;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Event_Category;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Neil
 */
public class Query_Account_ID {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_Account_ID(){
        
    }
   
   
  
    public List<Cultural_Event>queryAccount_ID()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events");
        
        while(rs.next()){
            String EventName = rs.getString("Cul_Name");
            Cultural_Event ac = new Cultural_Event(EventName);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Accounts> query_Whole_accountsdetails(String search)throws Exception{
        List CollList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select *,Acc_Gender,CONCAT(Acc_LName,', ',Acc_FName,' ',Acc_MName) fullname from Accounts where Acc_Status = 'Active' and Acc_ID LIKE '"+search+"%' OR Acc_FName LIKE '"+search+"%'"
                + " OR Acc_MName LIKE '"+search+"%' OR Acc_LName LIKE '"+search+"%' OR Acc_Status LIKE '"+search+"%'");
    
        while(rs.next()){
            int ID = rs.getInt("Acc_ID");
            String Fullname = rs.getString("fullname");
            String username = rs.getString("Acc_Username");
            String Gender = rs.getString("Acc_Gender");
            String Address = rs.getString("Acc_Address");
            String Contact = rs.getString("Acc_ContactNumber");
            String Status = rs.getString("Acc_Status");
            String Type = rs.getString("Acc_Type");
            Accounts ac = new Accounts(ID,Fullname,username,Gender,Address,Contact,Status,Type);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Accounts> query_Whole_accountsdetails_for_update(String search)throws Exception{
        List CollList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Accounts where Acc_ID =  '"+search+"'");
    
        while(rs.next()){
            int ID = rs.getInt("Acc_ID");
            String FName = rs.getString("Acc_FName");
            String MName = rs.getString("Acc_MName");
            String LName = rs.getString("Acc_LName");
            String Gender = rs.getString("Acc_Gender");
            String User = rs.getString("Acc_Username");
            String Pass = rs.getString("Acc_Password");
            String Address = rs.getString("Acc_Address");
            String Contact = rs.getString("Acc_ContactNumber");
            String Status = rs.getString("Acc_Status");
            String Type = rs.getString("Acc_Type");
            String path = rs.getString("Acc_Path");
            byte[] imagedata = rs.getBytes("Acc_Image");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            
            Accounts ac = new Accounts(ID,FName,MName,LName,Gender,Status,Type,User,Pass,Address,Contact,path,img);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Accounts> query_image(String id)throws Exception{
        List CollList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String qeury = "Select * from Accounts where Acc_ID = '"+id+"'";
        pst = (PreparedStatement) con.prepareStatement(qeury);
        rs = pst.executeQuery();
        if(rs.next()){
            
            byte[] imagedata = rs.getBytes("Acc_Image");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            
           // Accounts ac = new Accounts(image);
             Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
            administrator.TImage4.setIcon(ico);
            administrator.TImage4.setText("");
           // CollList.add(ac);
           }
            return CollList;
    }
}
