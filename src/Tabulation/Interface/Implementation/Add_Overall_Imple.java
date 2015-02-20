/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_OverallScore;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_Overall_Imple implements Add_OverallScore{
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;
    @Override
     public void Overall(double score, int aceid) {
        try{
            
            String sql = "INSERT into cultural_overall_score(COS_TotalScore,ACE_ID) Values(?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        ps.setDouble(1, score);
        ps.setInt(2, aceid);
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
}
