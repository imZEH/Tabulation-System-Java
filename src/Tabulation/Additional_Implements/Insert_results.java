/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additional_Implements;

import Tabulation.Additonal_Interface.Add_results;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author AL AMRY
 */
public class Insert_results implements Add_results{
    
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;
 
 @Override
  public void results(String crp_id, String srp_id){
       try{
            
        String sql = "Insert into results (Crp_id,Sgrp_id) VALUES (?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1,crp_id);
        ps.setString(2,srp_id);
        
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
  }
}
