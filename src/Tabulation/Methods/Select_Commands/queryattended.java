/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Attended_CulturalEvents;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Comlab1
 */
public class queryattended {
   public queryattended(){
        
    }
     private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
   
   
     public List<Attended_CulturalEvents>queryattend()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        
        rs = stmt.executeQuery("Select attended_culturalevents.ACE_ID from attended_culturalevents inner join cultural_events on attended_culturalevents.Cul_ID = cultural_events.Cul_ID");
        
        while(rs.next()){
            
           
            int num = rs.getInt(1);
             Attended_CulturalEvents ac = new  Attended_CulturalEvents(num);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
     
      public List<Attended_CulturalEvents>queryattend_Sport(String spid, String tenum)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        
        rs = stmt.executeQuery("SELECT a.Ap_ID FROM attended_sportsevents a "
                + "inner join participants p on a.Part_ID = p.Part_ID "
                + "inner join teams t on p.Team_ID = t.Team_ID "
                + "where a.Sp_ID = '"+spid+"' and t.Team_Number = '"+tenum+"'");
        
        while(rs.next()){
            
           
            int num = rs.getInt("Ap_ID");
             Attended_CulturalEvents ac = new  Attended_CulturalEvents(num);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
