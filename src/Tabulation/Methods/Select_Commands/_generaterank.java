/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Additional_getters.Generated_ranks;
import Tabulation.getters_setters._rankgetset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class _generaterank {
    
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public _generaterank(){
        
    }
    public List<_rankgetset> Gen_rak(int id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("Select * from _sumresultsorting Where Cul_ID = '"+id+"' GROUP BY Team_Name");
        while(rs.next()){
            double cos_id = rs.getDouble("Score");
            String tn = rs.getString("Team_Name");
            int fn = rs.getInt("Cul_ID");
            
            _rankgetset EC = new _rankgetset(cos_id,tn,fn);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<_rankgetset> Gen_rak1()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("Select * from cultural_ranking_points ");
        while(rs.next()){
            double cos_id = rs.getDouble("RP_Point");
            int fn = rs.getInt("Cul_ID");
            
            _rankgetset EC = new _rankgetset(cos_id,fn);
            CollList.add(EC);
        }
        return CollList;
    }
}
