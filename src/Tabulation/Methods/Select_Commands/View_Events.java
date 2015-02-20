/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;
import Tabulation.getters_setters.A_Category;
import Tabulation.getters_setters.Cultural_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Comlab1
 */
public class View_Events {
    public View_Events() {
        
    
}
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Cultural_Event>queryEvents(String id, String name, String percent, String status)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_events where Cul_ID  LIKE '%"+id+"%' or Cul_Name LIKE '%"+name+"%' or Cul_Percentage LIKE '%"+percent+"%'or Cul_Status LIKE '%"+status+"%'");
        
        while(rs.next()){
            int Id = rs.getInt("Cul_Id");
            String Name = rs.getString("Cul_Name");
            String Per = rs.getString("Cul_Percentage");
            String Stat = rs.getString("Cul_Status");
            Cultural_Event ac = new Cultural_Event(Id, Name, Per, Stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
      public List<Cultural_Event>queryViewEvents()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_events");
        
        while(rs.next()){
            int Id = rs.getInt("Cul_Id");
            String Name = rs.getString("Cul_Name");
            String Per = rs.getString("Cul_Percentage");
            String Stat = rs.getString("Cul_Status");
            Cultural_Event ac = new Cultural_Event(Id, Name, Per, Stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
      
       public List<Cultural_Event>queryView_Events(String id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Cultural_events where Cul_ID = '"+id+"'");
        
        while(rs.next()){
            int Id = rs.getInt("Cul_Id");
            String Name = rs.getString("Cul_Name");
            String Per = rs.getString("Cul_Percentage");
            String Stat = rs.getString("Cul_Status");
            Cultural_Event ac = new Cultural_Event(Id, Name, Per, Stat);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
       
       
        public List<A_Category>queryView_Events1(String id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Event_Category where Category_ID = '"+id+"'");
        
        while(rs.next()){
            int Id = rs.getInt(1);
            String Name = rs.getString(3);
            A_Category ac = new A_Category(Id,Name);
            
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
