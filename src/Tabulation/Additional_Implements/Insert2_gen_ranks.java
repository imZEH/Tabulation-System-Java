/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additional_Implements;

import Tabulation.Additonal_Interface.add_generated_ranks;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author AL AMRY
 */
public class Insert2_gen_ranks implements add_generated_ranks {
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;
 
 
    @Override
 public void gen_ranks(int Cos_id, int id){
        try{
            
        String sql = "Insert into cultural_generated_ranking_points (COS_ID,RP_ID) VALUES (?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1,Cos_id);
        ps.setInt(2,id);
        
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
    
    
    
    @Override
 public void gen_ranks_sport(int Cos_id, int id){
        try{
            
        String sql = "Insert into sport_generated_rankingpoints (SOS_ID,SRP_ID) VALUES (?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1,Cos_id);
        ps.setInt(2,id);
        
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
    
   
}
