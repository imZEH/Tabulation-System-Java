/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.add_company;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class add_company_implement implements add_company{
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
    @Override
    public void add(int id,String name, String orga, String add, String title){
        try{
        
        String sql = "CALL sqlPr_Configuration(?,?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        callableStatement.setString(1, name);
        callableStatement.setString(2, orga);
        callableStatement.setString(3, add);
        callableStatement.setString(4, title);
        
        callableStatement.executeUpdate();
        con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
