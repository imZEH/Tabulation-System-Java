/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additional_Implements;

import Tabulation.Additonal_Interface.add_generated_ranks;
import Tabulation.Additonal_Interface.delete_gen_ranks;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author AL AMRY
 */
public class Insert_gen_ranks implements delete_gen_ranks  {
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;

    @Override
    public void Update(int id){
        try{
            
            String sql = "Update cultural_generated_ranking_points set RP_ID ="+id+"";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        
        
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
    }

