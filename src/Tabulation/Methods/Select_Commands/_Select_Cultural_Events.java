/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Participant;
import Tabulation.getters_setters.Sport_Event;
import Tabulation.getters_setters.Team;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class _Select_Cultural_Events {
    
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public _Select_Cultural_Events(){
        
    }
    
    public List<Cultural_Event>_query_Culturalevents()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_Events Where Cul_Status = 'Active'");
        
        while(rs.next()){
            int ID = rs.getInt("Cul_ID");
            String EventName = rs.getString("Cul_Name");
            String Percnt = rs.getString("Cul_Percentage");
             String type = rs.getString("Cul_Type_Judging");
            Cultural_Event ac = new Cultural_Event(ID,EventName,Percnt,type);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Participant>_query_participants(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL _selectParticipant('"+ID+"')");
        
        while(rs.next()){
            String num = rs.getString("id");
            String EventName = rs.getString("fulname");
            String team = rs.getString("Team_Name");
            String  codeID = rs.getString("UID");
            Participant ac = new Participant(num,EventName,team,codeID);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
    public List<Team>_query_Teams(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select T.Team_ID,T.Team_Name,T.Team_Number"
                + " from Teams T CROSS JOIN Cultural_Events CE "
                + "Where CE.Cul_ID = '"+ID+"' and CE.Cul_Type_Judging = 'BY TEAM'");
        
        while(rs.next()){
            String tID = rs.getString("Team_ID");
            String EventName = rs.getString("Team_Name");
            String Num = rs.getString("Team_Number");
            Team ac = new Team(tID,EventName,Num);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Cultural_Event>_report1_cul(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL _cultural_report('"+ID+"')");
        
        while(rs.next()){
            String point = rs.getString("Score");
            String name = rs.getString("Team_Name");
            String rank = rs.getString("Rank");
            Cultural_Event ac = new Cultural_Event(point,name,rank);
            CollList.add(ac);
            
        }
        return CollList;
    }
    public List<Cultural_Event>_report1_spo(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL _sport_report('"+ID+"')");
        
        while(rs.next()){
            String point = rs.getString("Score");
            String name = rs.getString("Team_Name");
            String rank = rs.getString("Rank");
            Cultural_Event ac = new Cultural_Event(point,name,rank);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Cultural_Event>_report2_cul()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM _totalcultural_score t group by points DESC;");
        
        while(rs.next()){
            String point = rs.getString("points");
            String name = rs.getString("Team_Name");
            Cultural_Event ac = new Cultural_Event(point,name);
            CollList.add(ac);
            
        }
        return CollList;
    }
    public List<Cultural_Event>_report2_spo()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `_totalsport_score` group by Points desc");
        
        while(rs.next()){
            String point = rs.getString("points");
            String name = rs.getString("Team_Name");
            Cultural_Event ac = new Cultural_Event(point,name);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Cultural_Event>_report()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Cul_Name from Cultural_Events Where Cul_Status = 'Active'");
        
        while(rs.next()){
            String  point = rs.getString("Cul_Name");
            Cultural_Event ac = new Cultural_Event(point);
            CollList.add(ac);
            
        }
        return CollList;
    }
    public List<Sport_Event>_report1()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SP_Name from Sports_Events Where SP_Status = 'Active'");
        
        while(rs.next()){
            String  point = rs.getString("SP_Name");
            Sport_Event ac = new Sport_Event(point);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Cultural_Event>overall()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `sort` ORDER BY Points desc;");
        
        while(rs.next()){
            String  point = rs.getString("points");
            String team = rs.getString("Team_Name");
            Cultural_Event ac = new Cultural_Event(point,team);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
}
