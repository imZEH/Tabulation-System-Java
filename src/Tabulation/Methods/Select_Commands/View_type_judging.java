/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event1;
import Tabulation.getters_setters.Cultural_Event2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_type_judging {
    public View_type_judging() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Cultural_Event2>View_type(int cul_id)throws Exception{
        List CollList = new ArrayList();
        
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Cul_Type_Judging from Cultural_Events where Cul_ID ="+cul_id+"" );
        
        while(rs.next()){
            String perct = rs.getString("Cul_Type_Judging");
            
            
            Cultural_Event2 ac = new  Cultural_Event2(perct);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
    
}
