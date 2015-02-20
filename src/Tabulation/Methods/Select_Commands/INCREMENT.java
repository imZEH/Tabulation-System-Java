/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class INCREMENT {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public INCREMENT(){
        
    }
    
    public int increment_Cultural()throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events");
        
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Cul_ID");
            x = ID+1;
            
           }
        return x;
        
    }
    
    public int increment_Criteria()throws Exception{
        con =(java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Criteria_Cultural");
        
        int x = 0;
        if(rs.last()){
           int ID = rs.getInt("Cr_ID");
            x = ID+1;
            
         }
        return x;
        
    }
    
    public int increment_Category()throws Exception{
        con =(java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Event_Category");
        
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Category_ID");
            x = ID+1;
            
        }
        return x;
    }
    
    public int increment_Sports()throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Sports_Events");
        
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Sp_ID");
            x = ID+1;
            
           }
        return x;
        
    }
    public int increment_Team()throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Teams");
        
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Team_ID");
            x = ID+1;
            
           }
        return x;
    }
    public int increment_Participant()throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Participants");
        
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Part_ID");
            x = ID+1;
            
    }
        return x;
}
    public int increment_Accounts()throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Accounts");
        int x = 0;
        if(rs.last()){
            int ID = rs.getInt("Acc_ID");
            x = ID+1;
           
    }
        return x;
}
}
