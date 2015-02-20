/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Connections.ResizeImage1;
import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.Graphical_User_Interface.Client_Module.A_FinalForm;
import Tabulation.Graphical_User_Interface.Client_Module.A_FinalForm_Sport;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Participant;
import Tabulation.getters_setters.Participant1;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Comlab1
 */
public class View_Parti {
    public View_Parti(){
        
    }
     private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Participant>queryViewParti(String cul,String ac)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("select t.Team_ID,t.Team_Name,t.Team_logo,t.Team_Path from attended_culturalevents ac "
                + "inner join participants p on ac.Part_ID = p.Part_ID "
                + "inner join teams t on p.Team_ID = t.Team_ID where ac.Cul_ID = '"+cul+"' and ac.ACE_Secnum = '"+ac+"'");
        while(rs.next()){
            int num = rs.getInt("Team_ID");
            
            String TName = rs.getString("Team_Name");
            String path = rs.getString("Team_Path");
            new ResizeImage1().BufferedImage(path);
            
            Image image = new ImageIcon(path).getImage();
            ImageIcon image1 =new ImageIcon(image);
            A_FinalForm.v_Ima.setIcon(image1);
            Participant ac1 = new Participant(num,TName);
            
            CollList.add(ac1);
            
        }
        return CollList;
    }
     
     
      public List<Participant>queryViewParti2(String tnum, String cul)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("Select participants.Part_ID,CONCAT(participants.Part_FName,' ',participants.Part_MName,' ',participants.Part_LName)fullname,participants.Part_Pic,participants.Part_Path,teams.Team_Name from participants "
                + "inner join teams on participants.Team_ID = teams.Team_ID "
                + "inner join attended_culturalevents ac  on participants.Part_ID = ac.Part_ID where ac.ACE_Secnum  = '"+tnum+"' and ac.Cul_ID= '"+cul+"' ");
     
        while(rs.next()){
            int p_id = rs.getInt("Part_ID");
            String Fn = rs.getString("fullname");
            String TName = rs.getString("Team_Name");
           
            String path = rs.getString("Part_Path");
            new ResizeImage1().BufferedImage(path);
            
            Image image = new ImageIcon(path).getImage();
            ImageIcon image1 =new ImageIcon(image);
            A_FinalForm.v_Ima.setIcon(image1);
            Participant ac = new Participant(p_id,Fn, TName);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
      
       public List<Participant>queryViewParti3(String tnum)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("SELECT ACE_Secnum FROM attended_culturalevents where Cul_ID =  '"+tnum+"' ");
     
        while(rs.next()){
            String dd = rs.getString("ACE_Secnum");
            Participant ac = new Participant(dd);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
       
       
        public List<Participant>queryViewParti_Sports(String tnum)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("select T.Team_Name,P.Part_ID, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, p.Part_Gender from Attended_SportsEvents asp "
                + "inner join Participants p on asp.Part_ID = p.Part_ID "
                + "inner join Teams t on p.Team_ID = t.Team_ID where asp.Sp_ID ='"+tnum+"' ");
     
        while(rs.next()){
            int tnum2 = rs.getInt("Part_ID");
            String fulname = rs.getString("fullname");
            String Gender = rs.getString("Part_Gender");
            String Team = rs.getString("Team_Name");
            Participant ac = new Participant(tnum2,fulname,Gender,Team);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
        
        
        public List<Participant1>queryViewParti_Sports2(String tnum)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("select t.Team_Number, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, t.Team_Name from Attended_SportsEvents asp "
                + "inner join Participants p on asp.Part_ID = p.Part_ID "
                + "inner join Teams t on p.Team_ID = t.Team_ID where asp.Sp_ID ='"+tnum+"' ");
     
        while(rs.next()){
            String tnum2 = rs.getString("Team_Number");
            String Fname = rs.getString("fullname");
            String tname = rs.getString("Team_Name");
            Participant1 ac = new Participant1(tnum2,Fname,tname);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
       
     public List<Participant>ViewParti_sports(String cul,String ac)throws Exception{
        List CollList = new ArrayList();
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("select t.Team_ID,t.Team_Name,t.Team_logo,t.Team_Path from attended_sportsevents asp "
                + "inner join participants p on asp.Part_ID = p.Part_ID "
                + "inner join teams t on p.Team_ID = t.Team_ID where asp.Sp_ID = '"+cul+"' and t.Team_Number = '"+ac+"'");
        while(rs.next()){
            int num = rs.getInt("Team_ID");
            
            String TName = rs.getString("Team_Name");
            String path = rs.getString("Team_Path");
            new ResizeImage1().BufferedImage(path);
            
            Image image = new ImageIcon(path).getImage();
            ImageIcon image1 =new ImageIcon(image);
            A_FinalForm_Sport.v_Ima2.setIcon(image1);
            Participant ac1 = new Participant(num,TName);
            
            CollList.add(ac1);
            
        }
        return CollList;
    }
     
   
}


