/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additonal_methods;

import Tabulation.Additional_getters.Count_ranks_dta;
import Tabulation.Additional_getters.View_Cul_Ranks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AL AMRY
 */
public class Select_count {
     private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Select_count(){
        
    }
    public int  View_rks()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("SELECT count(Crp_Id) as 'conter' FROM cultural_generated_ranking_points");
       int cnter = 0;
        while(rs.next()){
            cnter= rs.getInt("conter");
            
          //  Count_ranks_dta EC = new Count_ranks_dta(cnter);
           // CollList.add(EC);
        }
        return cnter;
    }
    
    
}
