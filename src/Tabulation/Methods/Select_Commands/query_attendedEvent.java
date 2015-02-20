/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Attended_CulturalEvents;
import Tabulation.getters_setters.Attended_SportsEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class query_attendedEvent {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public query_attendedEvent(){
        
    }
    
    public List<Attended_CulturalEvents> queryattendCulturalEvent(String ID)throws Exception{
        List ColList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery( "Select ATT.Cul_ID,CE.Cul_Name from Attended_CulturalEvents ATT "
                + " INNER JOIN Cultural_Events CE ON CE.Cul_ID = ATT.Cul_ID Where ATT.Part_ID = '"+ID+"'");
        
        while(rs.next()){
            int id = rs.getInt("Cul_ID");
            String name = rs.getString("Cul_Name");
            Attended_CulturalEvents ACE = new  Attended_CulturalEvents(id,name);
            ColList.add(ACE);
        }
        return ColList;
    }
    
    public List<Attended_SportsEvent> queryattendSportEvent(String ID)throws Exception{
        List ColList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery( "Select ATT.Sp_ID,SE.Sp_Name from Attended_SportsEvents ATT "
                + " INNER JOIN Sports_Events SE ON SE.Sp_ID = ATT.Sp_ID Where ATT.Part_ID = '"+ID+"'");
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String name = rs.getString("Sp_Name");
          //  Attended_SportsEvent ACE = new  Attended_SportsEvent(id,name);
           // ColList.add(ACE);
        }
        return ColList;
    }
    
    public List<Attended_SportsEvent> query_Assigned(String ID)throws Exception{
        List ColList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery( "CALL sqlPr_Select_Assigned_Event('"+ID+"')");
        
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String type = rs.getString(3);
            Attended_SportsEvent ACE = new  Attended_SportsEvent(id,name,type);
            ColList.add(ACE);
        }
        return ColList;
    }
}
