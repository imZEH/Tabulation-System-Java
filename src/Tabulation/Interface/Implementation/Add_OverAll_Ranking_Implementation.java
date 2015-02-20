/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_OverAll_Ranking;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_OverAll_Ranking_Implementation  implements Add_OverAll_Ranking {
    private  java.sql.Connection con ;
    private  PreparedStatement ps;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    @Override
    public void add(double percentage,String Status,String Type){
        try{
        String sql = "CALL sqlPr_Insert_OverAllPoints(?,?,?)";
        
        con = Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement = (CallableStatement) con.prepareCall(sql);
        
        callableStatement.setDouble(1, percentage);
        callableStatement.setString(2, Status);
        callableStatement.setString(3, Type);
        
        rs = callableStatement.executeQuery();
       String b =  new Duplicate().dup_Overall(rs);
        callableStatement.executeUpdate();
        
        con.close();
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void update(int id,double percentage,String Status,String type){
        try{
            String sql = "CALL sqlPr_Update_Overall_Points(?,?,?,?)";
             con = Tabulation.Connections.Conn.getMySqlConnection();
             callableStatement = (CallableStatement) con.prepareCall(sql);
             
             callableStatement.setInt(1, id);
             callableStatement.setDouble(2, percentage);
             callableStatement.setString(3, Status);
             callableStatement.setString(4, type);
             
            rs = callableStatement.executeQuery();
            String b =  new Duplicate().dup_Overall_Points(rs);
             
             callableStatement.executeUpdate();
        
             con.close();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
