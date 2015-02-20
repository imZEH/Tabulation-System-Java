/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Event_Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_Cultural_ID {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_Cultural_ID(){
        
    }
    
    public List<Cultural_Event> Cultural_ID(String Cultural_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events where Cul_Name = '"+Cultural_Name+"'");
        while(rs.next()){
            int ID = rs.getInt("Cul_ID");
            Cultural_Event CE = new Cultural_Event(ID);
            CollList.add(CE);
        }
        
        return CollList;
    }
    
    public List<Event_Category> Event_Category_ID(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Event_Category where Category_Name = '"+Event_Name+"'");
        while(rs.next()){
            int ID = rs.getInt("Category_ID");
            Event_Category EC = new Event_Category(ID);
            CollList.add(EC);
        }
        return CollList;
    }
    public List<Cultural_Event> Event_Cultural_ID(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events where Cul_Name = '"+Event_Name+"'");
        while(rs.next()){
            int ID = rs.getInt("Cul_ID");
            Cultural_Event EC = new Cultural_Event(ID);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public String getIDfor_SubEvent(String Name)throws Exception{
        int ID = 0;
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events where Cul_Name = '"+Name+"'");
        while(rs.next()){
          ID = rs.getInt("Cul_ID");
         
         }
        return Integer.toString(ID);
    }
}
