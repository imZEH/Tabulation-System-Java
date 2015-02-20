/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Insert_Attend_Cul;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class inser_Attend_Cul_Implement implements Insert_Attend_Cul{
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
    @Override
   public void insert_attendCultural(String Culname){
       try{
           String qeury = "CALL slq_Pr_Insert_Attended_CulturalEvent(?)";
           con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           callableStatement= (CallableStatement) con.prepareCall(qeury);
           
           callableStatement.setString(1, Culname);
           
           rs = callableStatement.executeQuery();
           String b =  new Duplicate().dup_Overall(rs);
           callableStatement.executeUpdate();
           con.close();
        
        
       }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   }
    
    @Override
    public void insert_assignCultural(int id,String Culname){
       try{
           String qeury = "CALL sqlPr_Assigned_Event_for_Cultural(?,?)";
           con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           callableStatement= (CallableStatement) con.prepareCall(qeury);
           
           callableStatement.setInt(1, id);
           callableStatement.setString(2, Culname);
           
           rs = callableStatement.executeQuery();
           String b =  new Duplicate().dup_assignedCultural(rs);
           callableStatement.executeUpdate();
           con.close();
        
        
       }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   }
    
    @Override
    public void inser_attendSport(int SPID){
       try{
           String qeury = "CALL sqlPr_Insert_Attended_SportsEvent(?)";
           con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           callableStatement= (CallableStatement) con.prepareCall(qeury);
           
           
           callableStatement.setInt(1, SPID);
           rs = callableStatement.executeQuery();
           String b =  new Duplicate().dup_Overall(rs);
           
           callableStatement.executeUpdate();
           con.close();
        
       }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
   }
    
    @Override
    public void insert_assignedSport(int Pid, String SpID){
        try{
            String sql = "CALL sqlPr_Assigned_Event_for_Sports(?,?)";
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement = (CallableStatement) con.prepareCall(sql);
            
            callableStatement.setInt(1, Pid);
            callableStatement.setString(2, SpID);
            
            rs = callableStatement.executeQuery();
            String b = new Duplicate().dup_assignedCultural(rs);
           callableStatement.executeUpdate();
           con.close();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @Override
    public void Update_AttendCultural(int PartID,int CulID){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            
            String query = "insert into Attended_CulturalEvents(Part_ID,Cul_ID) Values(?,?)";
            
            
            ps = (PreparedStatement) con.prepareStatement(query);
            
            ps.setInt(1, PartID);
            ps.setInt(2, CulID);
            ps.executeUpdate();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    @Override
    public void Update_AttendSport(int PartID,int CulID){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            
            String query = "insert into Attended_SportsEvents(Sp_ID,Part_ID) Values(?,?)";
             
            
            ps = (PreparedStatement) con.prepareStatement(query);
            
            ps.setInt(1, CulID);
            ps.setInt(2, PartID);
            ps.executeUpdate();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
}
