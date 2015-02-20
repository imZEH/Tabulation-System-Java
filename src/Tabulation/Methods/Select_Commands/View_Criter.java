/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Cultural_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Comlab1
 */
public class View_Criter {
    public View_Criter() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Criteria_Cultural>queryView_Cr(String id, String id2)throws Exception{
        List CollList = new ArrayList();
        
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT  Cr_ID,Cr_Description from criteria_cultural where Cul_ID = '"+id+"' or Category_ID = '"+id2+"'" );
        
        while(rs.next()){
            int crid = rs.getInt("Cr_ID");
            String Name = rs.getString("Cr_Description");
            
            Criteria_Cultural ac = new Criteria_Cultural(crid,Name);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
     
     
     public List<Criteria_Cultural>queryView_Cr2(String cr)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Cr_Percentage FROM criteria_cultural where Cr_ID = '"+cr+"' " );
        
        while(rs.next()){
            
            Integer Name = rs.getInt("Cr_Percentage");
           
            Criteria_Cultural ac = new Criteria_Cultural(Name);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
     public List<Criteria_Cultural>view(String id)throws Exception{
         List CollList = new ArrayList();
         con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Criteria_from_Events('"+id+"')" );
        while(rs.next()){
            
            String Name = rs.getString(1);
            String crit = rs.getString(2);
            double percent = rs.getDouble(3);
           
            Criteria_Cultural ac = new Criteria_Cultural(Name,crit,percent);
            
            CollList.add(ac);
            
        }
        return CollList;
     }
     
    }
    

