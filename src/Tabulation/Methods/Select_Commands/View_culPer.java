/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.A_Category;
import Tabulation.getters_setters.Cultural_Event1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_culPer {
    public View_culPer() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Cultural_Event1>queryView_perCul(int cul_id)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Cul_Percentage from Cultural_Events where Cul_ID ="+cul_id+"" );
        
        while(rs.next()){
            String perct = rs.getString("Cul_Percentage");
            
            
            Cultural_Event1 ac = new  Cultural_Event1(perct);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
     
     
     public List<Cultural_Event1>queryView_perSpo(String cul_id)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Sp_Percentage FROM sports_events where Sp_ID ='"+cul_id+"'" );
        
        while(rs.next()){
            String perct = rs.getString("Sp_Percentage");
            
            
            Cultural_Event1 ac = new  Cultural_Event1(perct);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
