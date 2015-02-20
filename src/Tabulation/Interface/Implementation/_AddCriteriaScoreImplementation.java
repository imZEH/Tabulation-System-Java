/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface._AddCriteriaScore;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class _AddCriteriaScoreImplementation implements _AddCriteriaScore{
    private  java.sql.Connection con ;
    private  PreparedStatement ps;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    @Override
    public void _add(int pID, int cID, int crID , double score,String percent ,String accid, int category, String time){
        try{
        
            String sql = "CALL _criteriascore(?,?,?,?,?,?,?,?)";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement= (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, pID);
            callableStatement.setInt(2, cID);
            callableStatement.setInt(3, crID);
            callableStatement.setDouble(4, score);
            callableStatement.setString(5, percent);
            callableStatement.setString(6, accid);
            callableStatement.setInt(7, category);
            callableStatement.setString(8, time);
            
            rs = callableStatement.executeQuery();
           String b =  new Duplicate().donescoring(rs);
            
            callableStatement.executeUpdate();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void _addnocat(int pID, int cID, int crID , double score,String percent ,String accid,String time){
        try{
        
            String sql = "CALL _criteriasocre_no_category(?,?,?,?,?,?,?)";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement= (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, pID);
            callableStatement.setInt(2, cID);
            callableStatement.setInt(3, crID);
            callableStatement.setDouble(4, score);
            callableStatement.setString(5, percent);
            callableStatement.setString(6, accid);
            callableStatement.setString(7, time);
            
            rs = callableStatement.executeQuery();
            String b =  new Duplicate().donescoring(rs);
            
            
            callableStatement.executeUpdate();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void _addsport_score(int SPID, int TID, double Score,String acc){
        try{
        
            String sql = "CALL _sportScore(?,?,?,?)";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement= (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, SPID);
            callableStatement.setInt(2, TID);
            callableStatement.setDouble(3, Score);
            callableStatement.setString(4, acc);
            
            callableStatement.executeUpdate();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
