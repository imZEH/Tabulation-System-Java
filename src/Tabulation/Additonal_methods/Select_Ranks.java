/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additonal_methods;

import Tabulation.Additional_getters.Generated_ranks;
import Tabulation.Additional_getters.View_Cul_Ranks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AL AMRY
 */
public class Select_Ranks {
     private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Select_Ranks(){
        
    }
    public List<View_Cul_Ranks>View_rks()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("CALL select_cul_ranks()");
        while(rs.next()){
            int id = rs.getInt("RP_ID");
            int c_id = rs.getInt("Cul_ID");
            int point = rs.getInt("RP_Point");
            String rks = rs.getString("RP_Rank");
            
            View_Cul_Ranks EC = new View_Cul_Ranks(id,c_id,point,rks);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
    public List<View_Cul_Ranks>View_rks_sport()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("CALL select_sports_ranks()");
        while(rs.next()){
            int id = rs.getInt("SRP_ID");
            int c_id = rs.getInt("Sp_ID");
            int point = rs.getInt("SRP_Points");
            String rks = rs.getString("SRP_Rank");
            
            View_Cul_Ranks EC = new View_Cul_Ranks(id,c_id,point,rks);
            CollList.add(EC);
        }
        return CollList;
    }
}
