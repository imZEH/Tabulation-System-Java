/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.A_Category;
import Tabulation.getters_setters.Criteria_Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_subPer {
    public View_subPer() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<A_Category>queryView_perSub(int sub_id)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT Category_Per from Event_Category where Category_ID ="+sub_id+"" );
        
        while(rs.next()){
            int perct = rs.getInt("Category_Per");
            
            
            A_Category ac = new  A_Category(perct);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}

