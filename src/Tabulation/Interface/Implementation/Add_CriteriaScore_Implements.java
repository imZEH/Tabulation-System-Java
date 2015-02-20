/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_CriteriaScore;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_CriteriaScore_Implements implements Add_CriteriaScore{
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;
    @Override
    public void Criteria(double GivenScore, int Cr_id, double Com_score, int Ace_id) {
        try{
            
            String sql = "INSERT into criteria_score(CrScore,Cr_ID,Cr_Computed,ACE_ID) Values(?,?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        ps.setDouble(1, GivenScore);
        ps.setInt(2, Cr_id);
        ps.setDouble(3, Com_score);
        ps.setInt(4, Ace_id);
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
        
    }
    

