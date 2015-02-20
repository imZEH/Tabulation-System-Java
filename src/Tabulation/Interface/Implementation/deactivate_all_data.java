/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class deactivate_all_data {
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
   public void del()throws Exception{
       String sql = "CALL sqlPr_Update_all_Data();";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        callableStatement.executeUpdate();
       con.close();
   }
}
