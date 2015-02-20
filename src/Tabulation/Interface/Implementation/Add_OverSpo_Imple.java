/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_OverAllSpo_Score;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_OverSpo_Imple implements Add_OverAllSpo_Score {
 private  java.sql.Connection con ;
 private  PreparedStatement ps;
 private ResultSet rs;
   
    @Override
    public void Overall_Sport(double Com_score,int Ap_id) {
        try{
            
            String sql = "INSERT into sports_overall_score(SOS_TotalScore,Ap_ID) Values(?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        
        ps.setDouble(1, Com_score);
        ps.setInt(2, Ap_id);
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
}
