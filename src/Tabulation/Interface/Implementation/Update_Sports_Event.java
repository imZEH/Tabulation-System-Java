/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Update_Sport;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Update_Sports_Event implements Update_Sport{
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement;
   
    @Override
   public void UpdateSport(int ID,int AID , String percent, String SName,String status,String type,String Sched,String Date){
       try{
        
        String sql = "CALL sqlPr_Update_Sport_Event(?,?,?,?,?,?,?,?)" ;
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement = (CallableStatement) con.prepareCall(sql);
        
        callableStatement.setInt(1, ID);
        callableStatement.setInt(2, AID);
        callableStatement.setString(3, SName);
        callableStatement.setString(4, percent);
        callableStatement.setString(5, status);
        callableStatement.setString(6,type);
        callableStatement.setString(7,Sched);
        callableStatement.setString(8,Date);
        callableStatement.executeUpdate();
        con.close();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        }
   }
    
    @Override
    public void del(int id,String  name){
        try{
            con = (java.sql.Connection)Tabulation.Connections.Conn.getMySqlConnection();
            String sql = "Delete ATT from Attended_SportsEvents ATT INNER JOIN "
                    + "Sports_Events SE ON SE.Sp_ID = ATT.Sp_ID where SE.Sp_Name = '"+name+"' and ATT.Part_ID = '"+id+"'";
            
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void del1(int id,String  name){
        try{
            con = (java.sql.Connection)Tabulation.Connections.Conn.getMySqlConnection();
            String sql = "Delete ATT from Attended_CulturalEvents ATT INNER JOIN "
                    + "Cultural_Events CE ON CE.Cul_ID = ATT.Cul_ID where CE.Cul_Name = '"+name+"' and ATT.Part_ID = '"+id+"'";
            
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void deactivate(int id){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String sql = "Update Sports_Events set Sp_Status= 'InActive' where Sp_ID = '"+id+"'";
            
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
}

