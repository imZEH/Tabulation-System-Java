/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class reset_rank {
    public reset_rank(){
        
    }
     private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
   
   public void reset_Cultural_Rank(String data)throws Exception{
       String sql = "Delete from Cultural_Ranking_Points Where Cul_ID = '"+data+"'";
       con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
       pst = (PreparedStatement) con.prepareCall(sql);
       pst.executeUpdate();
       con.close();
   }
   
   public void reset_Sport_Rank(String data)throws Exception{
       String sql = "Delete From Sports_Ranking_Points Where Sp_ID = '"+data+"'";
       con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
       pst = (PreparedStatement) con.prepareCall(sql);
       pst.executeUpdate();
       con.close();
   }
   
   public void _runsort()throws Exception{
       String sql = "CALL _sorting();";
       con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
       pst = (PreparedStatement) con.prepareCall(sql);
       pst.executeUpdate();
       con.close();
   }
}
