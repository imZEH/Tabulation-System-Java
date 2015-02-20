/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Update_Cul_Event;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Update_Cultural_Event implements Update_Cul_Event{
    private java.sql.Connection con ;
    private PreparedStatement ps;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    @Override
    public void update_Cultural(int Cid, int AID,String Cul_Name, String Cul_Percentage,String stat, String Date,String Type ,String Sched,String judgetype){
        try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "CALL sqlPr_Update_Cutural_Event(?,?,?,?,?,?,?,?,?)" ;
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        callableStatement.setInt(1, Cid);
        callableStatement.setInt(2, AID);
        callableStatement.setString(3, Cul_Name);
        callableStatement.setString(4, Cul_Percentage);
        callableStatement.setString(5, stat);
        callableStatement.setString(6, Date);
        callableStatement.setString(7, Type);
        callableStatement.setString(8, Sched);
        callableStatement.setString(9, judgetype);
        
        callableStatement.executeUpdate();
        con.close();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        }
    }
    
    @Override
    public void Delete_Cultural(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Cultural_Events set Cul_Status = 'MARK AS DELETION' Where Cul_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    
    @Override
    public void Delete_Category(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Delete from Event_Category where Category_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null,"Transaction Granted");
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,"Can't Delete this Sub Event","Error",JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    @Override
    public void Delete_Criteria(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Delete from Criteria_Cultural where Cr_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    
    @Override
    public void Delete_User(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Accounts set Acc_Status = 'MARK AS DELETION' where Acc_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    
    @Override
    public void Delete_Participant(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Participants set Part_Status = 'MARK AS DELETION' where Part_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    @Override
     public void Delete_Team(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Teams set Team_Status = 'MARK AS DELETION' where Team_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    @Override
     public void Delete_Sports(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Sports_Events set Sp_Status = 'MARK AS DELETION' where Sp_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    @Override
     public void Delete_Assigned_Cultural(int ID,int ID1){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Delete from Attended_CulturalEvents Where Cul_ID = '"+ID+"' and Part_ID = '"+ID1+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    @Override
     public void Delete_Assigned_Sports(int ID,int ID1){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Delete from Attended_SportsEvents where Sp_ID = '"+ID+"' and Part_ID = '"+ID1+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
    @Override
     public void Delete_Overall(int ID){
       try{
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        String sql = "Update Overall_Points  set OP_Status = 'MARK AS DELETION' where OP_ID = '"+ID+"'";
        ps = (PreparedStatement) con.prepareStatement(sql);
        ps.executeUpdate();
        
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,c);
        } 
    }
}
