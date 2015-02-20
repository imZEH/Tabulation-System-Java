/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Add_Team;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */

public class AddTeam_Implement implements Add_Team {
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
   
    
    @Override
   public void Team(String TName, String TNumber, String TeamPic,String path,String Status,String Color){
       try{
        
       
        String sql = "CALL sqlPr_Insert_Teams(?,?,?,?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        String picture = TeamPic;
        File f=new File(picture);
        FileInputStream fis;
        fis = new FileInputStream(f);
       callableStatement.setString(1, TName);
       callableStatement.setString(2, TNumber);
       callableStatement.setBinaryStream(3, (InputStream)fis, (int)(f.length()));
       callableStatement.setString(4,Status);
       callableStatement.setString(5, path);
       callableStatement.setString(6, Color);
       
       rs = callableStatement.executeQuery();
        String b = new Duplicate().dup_Team(rs);
       
       callableStatement.executeUpdate();
       con.close();
       
       }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   
       
    }
   
    @Override
   public void Participant(int TID, String FName, String MName, String LName, String Add,String Gender,String Contact, String Status, String Pic,String path){
       try{
        String sql = "CALL sqlPr_Insert_Participant(?,?,?,?,?,?,?,?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        String picture = Pic;
        File f=new File(picture);
        FileInputStream fis;
        fis = new FileInputStream(f);
        
       callableStatement.setInt(1, TID);
       callableStatement.setString(2, FName);
       callableStatement.setString(3, MName);
       callableStatement.setString(4, LName);
       callableStatement.setString(5, Add);
       callableStatement.setString(6, Gender);
       callableStatement.setString(7, Contact);
       callableStatement.setString(8, Status);
       callableStatement.setBinaryStream(9, (InputStream)fis, (int)(f.length()));
       callableStatement.setString(10, path);
       
       rs = callableStatement.executeQuery();
       String b =  new Duplicate().dup_Participant(rs);
       
       callableStatement.executeUpdate();
       con.close();
       }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   }
    
    @Override
    public void updateparticipant(int PID,int TID, String FName, String MName, String LName, String Add,String Gender,String Contact, String Status, String Pic,String path){
        try{
            
             File f=new File(Pic);
            FileInputStream fis ;
            fis = new FileInputStream(f);
            
            String sql = "CALL sqlPr_Update_Participant(?,?,?,?,?,?,?,?,?,?,?)";
            
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement = (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, PID);
            callableStatement.setInt(2, TID);
            callableStatement.setString(3, FName);
            callableStatement.setString(4, MName);
            callableStatement.setString(5, LName);
            callableStatement.setString(6, Add);
            callableStatement.setString(7, Gender);
            callableStatement.setString(8, Contact);
            callableStatement.setString(9, Status);
            callableStatement.setBinaryStream(10, (InputStream)fis, (int)(f.length()));
            callableStatement.setString(11, path);
            
            callableStatement.executeUpdate();
       con.close();
            
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    
    @Override
    public void updateTeam(int TID, String TName, String TNumber, String TeamPic,String path,String Status,String Color){
            try{
            File f=new File(TeamPic);
            FileInputStream fis ;
            fis = new FileInputStream(f);
            String sql = "CALL sqlPr_Update_Team(?,?,?,?,?,?,?)";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement= (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, TID);
            callableStatement.setString(2, TName);
            callableStatement.setString(3, TNumber);
            callableStatement.setBinaryStream(4, (InputStream)fis, (int)(f.length()));
            callableStatement.setString(5, Status);
            callableStatement.setString(6, path);
            callableStatement.setString(7, Color);
            
            callableStatement.executeUpdate();
            
            con.close();
            }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    
}
