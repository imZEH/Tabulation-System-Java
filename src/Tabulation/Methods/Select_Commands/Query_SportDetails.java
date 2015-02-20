/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Sport_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_SportDetails {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_SportDetails(){
        
    }
   
  
    public List<Sport_Event>queryAccount_ID(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Sports_Events('"+search+"','"+search+"')");
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String SName = rs.getString("SP_Name");
            String percent = rs.getString("Sp_Percentage");
            String status = rs.getString("Sp_Status");
            String type = rs.getString("Sp_Type");
            String username = rs.getString("fullname");
            String Sched = rs.getString("Sp_Sched");
            String date = rs.getString("Sp_Date");
            Sport_Event ac = new Sport_Event(id,SName,percent,status,type,Sched,date,username);
            CollList.add(ac);
            
        }
        return CollList;
    }
    public List<Sport_Event>querytoupdate(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Sports_For_Update('"+search+"')");
        
        while(rs.next()){
            int id = rs.getInt(1);
            String percent = rs.getString(2);
            String SName = rs.getString(3);
            String status = rs.getString(4);
            String type = rs.getString(5);
            String Sched = rs.getString(6);
            Sport_Event ac = new Sport_Event(id,percent,SName,status,type,Sched);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
    public List<Sport_Event>queryAccount_ID1()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SE.Sp_ID,SE.Sp_Percentage,SE.Sp_Name,SE.Sp_Status,SE.Sp_Type,"
                + " CONCAT(A.Acc_LName,', ',A.Acc_FName,' ',A.Acc_MName) fullname,SE.Sp_Sched from Sports_Events SE"
                + " INNER JOIN Accounts A ON A.Acc_ID = SE.ACC_ID Where SE.Sp_Status = 'Active'");
        
        while(rs.next()){
            int id = rs.getInt("Sp_ID");
            String SName = rs.getString("SP_Name");
            String percent = rs.getString("Sp_Percentage");
            String status = rs.getString("Sp_Status");
            String type = rs.getString("Sp_Type");
            String username = rs.getString("fullname");
            String Sched = rs.getString("Sp_Sched");
            Sport_Event ac = new Sport_Event(id,SName,percent,status,type,Sched,username);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Sport_Event> Sports_ID(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Sports_Events where Sp_Name = '"+Event_Name+"'");
        while(rs.next()){
            int ID = rs.getInt("Sp_ID");
            Sport_Event EC = new Sport_Event(ID);
            CollList.add(EC);
        }
        return CollList;
    }
}
