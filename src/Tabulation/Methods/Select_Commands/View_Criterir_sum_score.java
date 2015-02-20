/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Criteria_Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_Criterir_sum_score {
     public View_Criterir_sum_score() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Criteria_Score>queryView_Cr_sumScore(int ace_id)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT  sum(Cr_Computed) as 'sum' from Criteria_Score where ACE_ID = "+ace_id+"" );
        
        while(rs.next()){
            double sum = rs.getDouble("sum");
            
            Criteria_Score ac = new Criteria_Score(sum);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
     
    
}
