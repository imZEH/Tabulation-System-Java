/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import Tabulation.Interface.Add_Voters_Password;
import Tabulation.Methods.Select_Commands.Duplicate;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Add_Voters_Password_Implementation implements Add_Voters_Password{
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
    @Override
   public void add(String pass,String DateCreated,String DateExpire,String btchname){
       try{
           
           String sql = "CALL sqlPr_Insert_Voters_Password(?,?,?,?)";
           con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
           callableStatement = (CallableStatement) con.prepareCall(sql);
           
           callableStatement.setString(1, pass);
           callableStatement.setString(2, DateCreated);
           callableStatement.setString(3, DateExpire);
           callableStatement.setString(4, btchname);
           
                
           callableStatement.executeUpdate();
           con.close();
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
   }
}
