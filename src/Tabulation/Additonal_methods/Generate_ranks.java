/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additonal_methods;


import Tabulation.Additional_getters.Generated_ranks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AL AMRY
 */
public class Generate_ranks {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Generate_ranks(){
        
    }
    public List<Generated_ranks> Gen_rak(int id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("CALL Module_Judge_generate_rank("+id+")");
        while(rs.next()){
            int cos_id = rs.getInt("COS_ID");
            String tn = rs.getString("Team_Name");
            String fn = rs.getString("fullname");
            double tot = rs.getDouble("total");
            double ovrtot = rs.getDouble("COS_TotalScore");
            
            Generated_ranks EC = new Generated_ranks(cos_id,tn,fn,tot,ovrtot);
            CollList.add(EC);
        }
        return CollList;
    }
    
     public List<Generated_ranks> Gen_rak_sport(int id)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("CALL Module_Sports("+id+")");
        while(rs.next()){
            int sos_id = rs.getInt("SOS_ID");
            String tn = rs.getString("Team_Name");
            String fn = rs.getString("fullname");
            double tot = rs.getDouble("total");
            double ovrtot = rs.getDouble("SOS_TotalScore");
            
            Generated_ranks EC = new Generated_ranks(sos_id,tn,fn,tot,ovrtot);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
}
