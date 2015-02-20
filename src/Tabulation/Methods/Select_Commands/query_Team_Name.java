/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.getters_setters.Team;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Neil
 */
public class query_Team_Name {
    private java.sql.Connection con = null;
    private java.sql.PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public query_Team_Name(){}
    
    public void team_id(Team team)throws Exception{
        String name =  team.getTeam_Name();
        
        int id =0;
        String sql = "Select * from Teams where Team_Name = '"+name+"'";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        pst = (PreparedStatement) con.prepareStatement(sql);
        rs = pst.executeQuery();
        while(rs.next()){ 
            id = rs.getInt(1);
        }
        team.setTeam_ID(id);
       
    
    }
    public List<Team>qeury_team_name()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams");
        while(rs.next()){
            int id = rs.getInt("Team_ID");
            String name = rs.getString("Team_Name");
            String number = rs.getString("Team_Number");
            Team EC = new Team(id,name,number);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Team>query_whole_team_details(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Teams('"+search+"','"+search+"');");
        while(rs.next()){
            int id = rs.getInt(1);
            String TName = rs.getString(2);
            String TNumber = rs.getString(3);
            String Status = rs.getString(4);
            Team EC = new Team(id,TName,TNumber,Status);
            CollList.add(EC);
        }
        return CollList;
    }
    public List<Team>team_controller()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from teams Where Team_Status = 'Active'");
        while(rs.next()){
            int id = rs.getInt(1);
            String TName = rs.getString(2);
            String TNumber = rs.getString(3);
            String Status = rs.getString(4);
            Team EC = new Team(id,TName,TNumber,Status);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Team>query_whole_team_details_ForUpdate(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams where Team_ID ='"+search+"'");
        while(rs.next()){
            int id = rs.getInt("Team_ID");
            String TName = rs.getString("Team_Name");
            String TNumber = rs.getString("Team_Number");
            String path = rs.getString("Team_Path");
            String Status = rs.getString("Team_Status");
             byte [] imagedata = rs.getBytes("Team_logo");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Team EC = new Team(id,TName,TNumber,Status,path,img);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Team>getimage_forteam(String search)throws Exception{
        BufferedImage imag = null;
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams where Team_ID ='"+search+"'");
        while(rs.next()){
             byte [] imagedata = rs.getBytes("Team_logo");
            Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
            Image dimg = img.getScaledInstance(144, 144,Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(dimg);
          administrator.TImage7.setIcon(ico);
        }
        return CollList;
    }
     public List<Team>query_wholeJOINING_team(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams T "
                + "INNER JOIN Participants P ON T.Team_ID = P.Team_ID where P.Part_ID ='"+search+"'");
        while(rs.next()){
            int id = rs.getInt("Team_ID");
            String TName = rs.getString("Team_Name");
            String TNumber = rs.getString("Team_Number");
            Team EC = new Team(id,TName,TNumber);
            CollList.add(EC);
        }
        return CollList;
    }
}
