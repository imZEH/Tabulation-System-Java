/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_Ranking_Points;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_Ranking_Points_Implementations implements Add_Ranking_Points{
    private  java.sql.Connection con ;
    private  PreparedStatement ps;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    @Override
    public void add_CulturalRank(String Rank,String Percent,String ID){
        try{
            String sql ="CALL sqlPr_Insert_Cultural_Ranking_Points(?,?,?)";
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement = (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setString(1, ID);
            callableStatement.setString(2, Percent);
            callableStatement.setString(3, Rank);
            rs = callableStatement.executeQuery();
            String b =  new Duplicate().dup_Ranking_Points(rs);
            
            callableStatement.executeUpdate();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void add_SportsRank(String Rank,String Percent,String ID){
        try{
            String sql ="CALL sqlPr_Insert_Sports_Ranking_Points(?,?,?)";
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement = (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setString(1, ID);
            callableStatement.setString(2, Percent);
            callableStatement.setString(3, Rank);
            rs = callableStatement.executeQuery();
            String b =  new Duplicate().dup_Ranking_Points(rs);
            
            callableStatement.executeUpdate();
            con.close();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
