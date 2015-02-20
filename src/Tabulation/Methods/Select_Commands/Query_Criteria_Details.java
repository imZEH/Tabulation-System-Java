/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Criteria_Cultural;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_Criteria_Details {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_Criteria_Details(){
        
    }
    public List<Criteria_Cultural> CriteriaDetailsforsearch(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Event_Category EC ON CC.Category_ID = EC.Category_ID "
                + " INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where EC.Cul_ID = '"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Cr_ID");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(id,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Criteria_Cultural> CriteriaDetailsforsearch1(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID where CE.Cul_ID = '"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Cr_ID");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(id,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    public List<Criteria_Cultural> CriteriaDetailsforsearch3(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID where CE.Cul_ID = '"+ID+"'");
        while(rs.next()){
            String id = rs.getString("Cr_ID");
            String Des = rs.getString("Cr_Description");
            String per = rs.getString("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(id,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Criteria_Cultural> CriteriaDetailsforsearch2(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Event_Category EC ON EC.Category_ID=CC.Category_ID where EC.Category_ID = '"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Cr_ID");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(id,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
    
    public List<Criteria_Cultural> CriteriaCategory(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select EC.Category_Name,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                + "INNER JOIN Event_Category EC ON CC.Category_ID = EC.Category_ID  where EC.Category_ID = '"+Event_Name+"'");
        while(rs.next()){
            String name = rs.getString("Category_Name");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(name,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Criteria_Cultural> CriteriaCategory1(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SUM(CC.Cr_Percentage) perent from Criteria_Cultural CC "
                + "INNER JOIN Event_Category EC ON CC.Category_ID = EC.Category_ID  where EC.Category_ID = '"+Event_Name+"'");
        while(rs.next()){
            double name = rs.getDouble("perent");
            Criteria_Cultural EC = new Criteria_Cultural(name);
            CollList.add(EC);
        }
        return CollList;
    }
    public List<Criteria_Cultural> Criteriapercentage_for_Cultural(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SUM(CC.Cr_Percentage) total from Criteria_Cultural CC "
                + "INNER JOIN Cultural_Events CE ON CC.Cul_ID = CE.Cul_ID where CE.Cul_Name = '"+Event_Name+"'");
        while(rs.next()){
            int total = rs.getInt("total");
            Criteria_Cultural EC = new Criteria_Cultural(total);
            CollList.add(EC);
        }
        return CollList;
    }
    public List<Criteria_Cultural> Criteriapercentage(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SUM(CC.Cr_Percentage) total from Criteria_Cultural CC INNER JOIN Event_Category EC ON CC.Category_ID = EC.Category_ID"
    +" where EC.Category_Name = '"+Event_Name+"'");
        while(rs.next()){
            int total = rs.getInt("total");
            Criteria_Cultural EC = new Criteria_Cultural(total);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
     
     public List<Criteria_Cultural>queryView_Cr(int id, int id2)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT  criteria_cultural.Cr_Description from criteria_cultural inner join event_category on criteria_cultural.Category_ID = event_category.Category_ID where event_category.Cul_ID = "+id+" and event_category.Category_ID = "+id2+"" );
        
        while(rs.next()){
            
            String Name = rs.getString("Cr_Description");
           
            Criteria_Cultural ac = new Criteria_Cultural(Name);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     public List<Criteria_Cultural>queryView_Cr_Des(String Des)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM criteria_cultural  where criteria_cultural.Cr_Description = '"+Des+"'" );
        
        while(rs.next()){
            
            String Name = rs.getString("Cr_Description");
            int id = rs.getInt("Cr_ID");
            double perc = rs.getDouble("Cr_Percentage");
            
           
            Criteria_Cultural ac = new Criteria_Cultural(id,Name,perc);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
     public List<Criteria_Cultural> CriteriaDetails_For_Cultural_Event(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CE.Cul_Name,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                + "INNER JOIN Cultural_Events CE ON CC.Cul_ID = CE.Cul_ID  where CE.Cul_ID = '"+Event_Name+"'");
        while(rs.next()){
            String name = rs.getString("Cul_Name");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(name,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
     
     public List<Criteria_Cultural> CriteriaDetails_For_Cultural_Event1(String Event_Name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select SUM(CC.Cr_Percentage) percent from Criteria_Cultural CC "
                + "INNER JOIN Cultural_Events CE ON CC.Cul_ID = CE.Cul_ID  where CE.Cul_ID = '"+Event_Name+"'");
        while(rs.next()){
            int a = rs.getInt("percent");
            Criteria_Cultural EC = new Criteria_Cultural(a);
            CollList.add(EC);
        }
        return CollList;
    }
     
     public List<Criteria_Cultural> For_Update1(String id)throws Exception{
         List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage,EC.Category_Name,EC.Category_ID from Criteria_Cultural CC "
                 + "INNER JOIN Event_Category EC ON EC.Category_ID=CC.Category_ID "
                + " where CC.Cr_ID = '"+id+"'");
        
        while(rs.next()){
            int ID = rs.getInt("Cr_ID");
            String des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            String name = rs.getString("Category_Name");
            String EID = rs.getString("Category_ID");
            Criteria_Cultural EC = new Criteria_Cultural(ID,des,per,name,EID);
            CollList.add(EC);
        }
        
        return CollList;
     }
     public List<Criteria_Cultural> For_Update2(String id)throws Exception{
         List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage,CE.Cul_Name,CC.Cul_ID from Criteria_Cultural CC "
                 + "INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID "
                + " where CC.Cr_ID = '"+id+"'");
        
        while(rs.next()){
            int ID = rs.getInt("Cr_ID");
            String des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            String name = rs.getString("Cul_Name");
             String EID = rs.getString("Cul_ID");
            Criteria_Cultural EC = new Criteria_Cultural(ID,des,per,name,EID);
            CollList.add(EC);
        }
        
        return CollList;
     }
     
     
     public String getEvent_Cultural(String name)throws Exception{
         int a = 0;
         con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Cul_ID from Cultural_Events where Cul_Name = '"+name+"'");
        while(rs.next()){
            a = rs.getInt("Cul_ID");
        }
         
         return Integer.toString(a);
     }
     
      public String getEvent_Category(String name)throws Exception{
         int a = 0;
         con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Category_ID from Event_Category where Category_Name = '"+name+"'");
        while(rs.next()){
            a = rs.getInt("Category_ID");
        }
         
         return Integer.toString(a);
     }
}
