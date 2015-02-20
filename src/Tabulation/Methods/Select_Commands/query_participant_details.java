/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.Controller;
import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.Graphical_User_Interface.Client_Module.Scorer;
import Tabulation.Graphical_User_Interface.Client_Module.Sport_window;
import Tabulation.getters_setters.Participant;
import Tabulation.getters_setters.Team;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Neil
 */
public class query_participant_details {
    private java.sql.Connection con = null;
    private java.sql.PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public query_participant_details(){}
    
    public List<Participant>query_participant(String name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Participants('"+name+"','"+name+"')");
        while(rs.next()){
            int ID = rs.getInt(1);
            String fulname = rs.getString(2);
            String gender = rs.getString(3);
            String contact = rs.getString(4);
            String address = rs.getString(5);
            String status = rs.getString(6);
            String team = rs.getString(7);
            Participant EC = new Participant(ID,fulname,gender,contact,address,status,team);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Participant>participant_for_assigning()throws Exception{
        
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Participant_Name_ID()");
        while(rs.next()){
            int ID = rs.getInt(1);
            String fulname = rs.getString(2);
            Participant EC = new Participant(ID,fulname);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Participant>qeury_Part(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select *,CONCAT(P.Part_LName,', ',P.Part_FName,' ',P.Part_MName) fullname from Participants P"
                + " INNER JOIN Teams T ON T.Team_ID = P.Team_ID where "
                + "T.Team_ID ='"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Part_ID");
            String fulname = rs.getString("fullname");
            String add = rs.getString("Part_Address");
            String contact = rs.getString("Part_ContactNumber");
            //Participant EC = new Participant(id,fulname,add,contact);
        //    CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Participant>qeury_PartUpdate(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select P.Part_ID,P.Part_FName,P.Part_MName,P.Part_LName,P.Part_Gender,"
                + "P.Part_Address,P.Part_ContactNumber,P.Part_Path,P.Part_Pic,P.Part_Status, T.Team_Name from Participants P INNER JOIN Teams T ON "
                + "P.Team_ID = T.Team_ID where P.Part_ID = '"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Part_ID");
            String FName = rs.getString("Part_FName");
            String MName = rs.getString("Part_MName");
            String LName = rs.getString("Part_LName");
            String add = rs.getString("Part_Address");
            String gender = rs.getString("Part_Gender");
            String contact = rs.getString("Part_ContactNumber");
            String stat = rs.getString("Part_Status");
            String path = rs.getString("Part_Path");
            String Tname = rs.getString("Team_Name");
            byte [] imagedata = rs.getBytes("Part_Pic");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Participant EC = new Participant(id,FName,MName,LName,add,gender,contact,stat,Tname,path,img);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Participant>query_Image(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Participants where Part_ID = '"+ID+"'");
        while(rs.next()){
            byte [] imagedata = rs.getBytes("Part_Pic");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
            
            administrator.TImage6.setIcon(ico);
        }
        return CollList;
    }
    public List<Participant>_query_PartImage(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Participants where Part_ID = '"+ID+"'");
        while(rs.next()){
            byte [] imagedata = rs.getBytes("Part_Pic");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
            Controller.TImage8.setIcon(ico);
        }
        return CollList;
    }
    
    public List<Participant>_queryScorer_PartImage(String TID,String PID)throws Exception{
        String a = null ;
        String b = null;
        if(TID.equals("Team is not be setted for scoring")){
            a = "Select * from Participants where Part_ID = '"+PID+"'";
            b = "Part_Pic";
        }
        else if(PID.equals("Participant is not be setted for scoring")){
            a = "Select * from teams where Team_ID = '"+TID+"'";
            b = "Team_logo";
        }
        
        
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery(a);
        while(rs.next()){
            byte [] imagedata = rs.getBytes(b);
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
            Scorer.v_Ima.setText("");
            Scorer.v_Ima.setIcon(ico);
        }
        return CollList;
    }
    
    public List<Team>_query_TeamImage(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams where Team_ID = '"+ID+"'");
        while(rs.next()){
            byte [] imagedata = rs.getBytes("Team_Logo");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
            Controller.v_Ima.setIcon(ico);
        }
        return CollList;
    }
     public List<Participant>_query_PartImageSport(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Participants where Part_ID = '"+ID+"'");
        while(rs.next()){
            byte [] imagedata = rs.getBytes("Part_Pic");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            ImageIcon image =new ImageIcon(img);
            Sport_window.pimage.setIcon(image);
        }
        return CollList;
    }
     public List<Team>_query_TeamImageSport(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams where Team_ID = '"+ID+"'");
        while(rs.next()){
            byte [] imagedata = rs.getBytes("Team_Logo");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            ImageIcon image =new ImageIcon(img);
            Sport_window.timage.setIcon(image);
        }
        return CollList;
    }
}
