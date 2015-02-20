/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event1;
import Tabulation.getters_setters.Sport_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_Sports_Events {
     public View_Sports_Events() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Sport_Event>View_spo()throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Sp_ID, Sp_Name, Sp_Percentage, Sp_Status FROM sports_events" );
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String name = rs.getString("Sp_Name");
            String per = rs.getString("Sp_Percentage");
            
            String stat = rs.getString("Sp_Status");
            
            Sport_Event ac = new  Sport_Event(id, name,per,stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
      public List<Sport_Event>View_spo_ser(String ID, String Name, String Percen, String Ron, String Stat)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Sp_ID, Sp_Name, Sp_Percentage, Sp_Status FROM sports_events where Sp_ID  LIKE '%"+ID+"%' or Sp_Name LIKE '%"+Name+"%' or Sp_Percentage LIKE '%"+Percen+"%' or Sp_Rounds LIKE '%"+Ron+"%' or Sp_Status LIKE '%"+Stat+"%'");
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String name = rs.getString("Sp_Name");
            String per = rs.getString("Sp_Percentage");
            
            String stat = rs.getString("Sp_Status");
            
            Sport_Event ac = new  Sport_Event(id, name,per,stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
      
      public List<Sport_Event>_getsportevent()throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM sports_events Where Sp_Status = 'Active'");
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String name = rs.getString("Sp_Name");
            String per = rs.getString("Sp_Percentage");
            
            String stat = rs.getString("Sp_Type");
            
            Sport_Event ac = new  Sport_Event(id, name,per,stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
