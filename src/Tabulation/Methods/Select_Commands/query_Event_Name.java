/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Accounts;
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
public class query_Event_Name {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public query_Event_Name(){
        
    }
    
    public List<Cultural_Event>queryCultural_EventName()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events where Cul_Status = 'Active'");
        
        while(rs.next()){
           String EVName = rs.getString("Cul_Name");
           Cultural_Event CV = new Cultural_Event(EVName);
           CollList.add(CV);
            
        }
        return CollList;
    }
    
    public List<Event_Category>Event_Category_Name()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Event_Category");
        
        while(rs.next()){
            String Cat = rs.getString("Category_Name");
            Event_Category EC = new Event_Category(Cat);
            CollList.add(EC);
        }
        return CollList;
    }
}
