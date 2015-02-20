/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_SportScore;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_Spo_Score_Imple implements Add_SportScore{
 private  java.sql.Connection con ;
 private  PreparedStatement ps;

    @Override
     public void Sports_Score(double GivenScore, int Ap_id,double Com_score) {
        try{
            
             String sql = "INSERT into sports_score(S_Given_Score,Ap_ID,S_CompScore) Values(?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        ps.setDouble(1, GivenScore);
        ps.setInt(2,Ap_id);
        ps.setDouble(3, Com_score);
        
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
    
}
