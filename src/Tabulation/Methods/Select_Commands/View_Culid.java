/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.A_Category;
import Tabulation.getters_setters.Cul_id;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_Culid {
     public View_Culid() {
         
    }
      private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Cul_id>queryView_cul(int id)throws Exception{
        List CollList = new ArrayList();
        
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Cul_ID FROM event_category where Category_ID = "+id+"" );
        
        while(rs.next()){
            
            int cut_Id = rs.getInt("Cul_ID");
            
           
            Cul_id ac = new Cul_id(cut_Id);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
