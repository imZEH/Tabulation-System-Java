/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil
 */
public class query_ListOFEvent {
    
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public query_ListOFEvent(){
        
    }
    public List<Cultural_Event>wholeEvent(String ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Cultural_Event('"+ID+"','"+ID+"','"+ID+"')");
        
        while(rs.next()){
            int id = rs.getInt("Cul_ID");
            String EName = rs.getString("Cul_Name");
            String percent = rs.getString("Cul_Percentage");
            String stat =rs.getString("Cul_Status");
            String Date =   rs.getString("Cul_Date");
            String accname =  rs.getString("fullname");
            String Sched = rs.getString("Cul_Sched");
            Cultural_Event ac = new Cultural_Event(id,EName,percent,stat,Date,accname,Sched);
            CollList.add(ac);
        }
        
        return CollList;
    }
    
    public List<Cultural_Event>getCulturalforUpdate(String ID)throws Exception{
        List ColList = new ArrayList();
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Cul_ID,Cul_Name,Cul_Percentage,Cul_Status,Cul_Date,Cul_Type,"
                + "Cul_Sched,Cul_Type_Judging from Cultural_Events Where Cul_ID = '"+ID+"'");
    while(rs.next()){
            int id = rs.getInt("Cul_ID");
            String EName = rs.getString("Cul_Name");
            String percent = rs.getString("Cul_Percentage");
            String stat =rs.getString("Cul_Status");
            Date Date =   rs.getDate("Cul_Date");
            String Type = rs.getString("Cul_Type");
            String Sched = rs.getString("Cul_Sched");
            String Judge = rs.getString("Cul_Type_Judging");
            Cultural_Event ac = new Cultural_Event(id,EName,percent,stat,Date,Type,Sched,Judge);
            ColList.add(ac);
        }
        
        return ColList;
    
    }
}
