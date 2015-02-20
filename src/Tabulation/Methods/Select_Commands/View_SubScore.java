/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Sport_Event;
import Tabulation.getters_setters.Sub_Res;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_SubScore {
     public View_SubScore() {
    }
    
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Sub_Res>View_SubRes(String cul, String ful)throws Exception{
        List CollList = new ArrayList();
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("select cc.Cr_Description, cs.Cr_Computed from criteria_score cs "
                + "inner join criteria_cultural cc on cs.Cr_ID = cc.Cr_ID "
                + "inner join attended_culturalevents ac on cs.ACE_ID = ac.ACE_ID "
                + "inner join participants p on ac.Part_ID = p.Part_ID "
                + "inner join cultural_events ce on ce.Cul_ID = ac.Cul_ID "
                + "where ce.Cul_Name = '"+cul+"' and CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName) = '"+ful+"' order by Cr_Description" );
        
        while(rs.next()){
            
            String name = rs.getString("Cr_Description");
            double score = rs.getDouble("Cr_Computed");
            
            Sub_Res ac = new  Sub_Res(name,score);
            
            CollList.add(ac);
            
        }
        return CollList;
    }
    
}
