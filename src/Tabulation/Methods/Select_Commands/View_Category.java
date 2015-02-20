/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.A_Category;
import Tabulation.getters_setters.Criteria_Cultural;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Comlab1
 */
public class View_Category {
     public View_Category() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<A_Category>queryView_Category(int id)throws Exception{
        List CollList = new ArrayList();
        
       con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT event_category.Category_ID, event_category.Category_Name FROM event_category  inner join Cultural_Events on event_category.Cul_ID = Cultural_Events.Cul_ID where event_category.Cul_ID = "+id+"" );
        
        while(rs.next()){
            
            int cat_Id = rs.getInt("Category_ID");
            String name = rs.getString("Category_Name");
           
            A_Category ac = new A_Category(cat_Id, name);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
