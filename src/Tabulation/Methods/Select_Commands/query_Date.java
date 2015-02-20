/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Attended_CulturalEvents;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Team;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class query_Date {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public  String query_Date() throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("select CURDATE() as date");
        String date = null;
        while(rs.next()){
            date = rs.getString("date");
        }
        return date;
    }
    public String query_interval(String a)throws Exception{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        String date1 = new query_Date().query_Date();
        rs = stmt.executeQuery("SELECT DATE_ADD('"+date1+"', INTERVAL '"+a+"' DAY) date");
        String date = null;
        while(rs.next()){
            date = rs.getString("date");
        }
        return date;
    }
   
    public List<Cultural_Event> query_reference()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPR_Select_Event_References()");
        int id ;
        String data = null;
        while(rs.next()){
            id = rs.getInt(1);
            data = rs.getString(2);
            Cultural_Event CV = new Cultural_Event(id,data);
            CollList.add(CV);
        }
        
        return CollList;
    }
    
    public List<Cultural_Event> query_reference2()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_MajorMinor_Event()");
        int id ;
        String data = null;
        while(rs.next()){
            id = rs.getInt(1);
            data = rs.getString(2);
            Cultural_Event CV = new Cultural_Event(id,data);
            CollList.add(CV);
        }
        
        return CollList;
    }
    
    public List<Team> query_Team_for_Participant()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Team_for_Participant()");
        
        String data = null;
        while(rs.next()){
           
            data = rs.getString(1);
            Team T = new Team(data);
            CollList.add(T);
        }
        
        return CollList;
    }
    
    public List<Team> query_Event_to_Assign()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Sports_and_Cultural()");
        
        String data = null;
        while(rs.next()){
           
            data = rs.getString(1);
            int id = rs.getInt(2);
            String type = rs.getString(3);
            Team T = new Team(data,id,type);
            CollList.add(T);
        }
        
        return CollList;
    }
    
    public List<Team> query_all_Event()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_all_Event()");
        
        String data = null;
        while(rs.next()){
           
            data = rs.getString(1);
            Team T = new Team(data);
            CollList.add(T);
        }
        
        return CollList;
    }
   
}
