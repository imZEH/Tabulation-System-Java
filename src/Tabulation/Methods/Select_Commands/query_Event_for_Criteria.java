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
 * @author Neil
 */
public class query_Event_for_Criteria {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    public query_Event_for_Criteria(){
    
    }
     public List<Criteria_Cultural>query_Criteria(String id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from Criteria_Cultural Where Cr_ID = '"+id+"'");
        
        while(rs.next()){
            int crid = rs.getInt(1);
            String culid = rs.getString(2);
            String catid = rs.getString(3);
            String des = rs.getString(4);
            double per = rs.getDouble(5);
            Criteria_Cultural ac = new Criteria_Cultural(crid,culid,catid,des,per);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
     
     public String getdata_cutural(String id)throws Exception{
         con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Cul_Name from Cultural_Events Where Cul_ID = '"+id+"'");
        String name = null;
        while(rs.next()){
             name= rs.getString("Cul_Name");
        }
        
        return name ;
     }
     public String getdata_category(String id)throws Exception{
         con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select Category_Name from Event_Category Where Category_ID = '"+id+"'");
        String name = null;
        while(rs.next()){
             name= rs.getString("Category_Name");
        }
        
        return name ;
     }
}
