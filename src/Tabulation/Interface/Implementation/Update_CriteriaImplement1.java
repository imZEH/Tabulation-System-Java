/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Update_Criteria;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Update_CriteriaImplement1 implements Update_Criteria{
    private java.sql.Connection con ;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public void UpdateCriteria_Cultural(int id, String des, double per, String CID, String CulID){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String query = "Update Criteria_Cultural set Cr_ID = '"+id+"',Cr_Description = '"+des+"',"
                    + "Cr_Percentage = '"+per+"',Category_ID = "+null+",Cul_ID = "+CulID+" Where Cr_ID = '"+id+"'";
            
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.executeUpdate();
            
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    @Override
    public void UpdateCriteria1_Category(int id, String des, double per, String CID, String CulID){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String query = "Update Criteria_Cultural set Cr_ID = '"+id+"',Cr_Description = '"+des+"',"
                    + "Cr_Percentage = '"+per+"',Category_ID = "+CID+",Cul_ID = "+null+" Where Cr_ID = '"+id+"'";
            
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.executeUpdate();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    
    @Override
    public void deleteCri(String name){
        try{
            String query = "Delete from criteria_cultural where Cr_Description = '"+name+"'";
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.executeUpdate();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
}
