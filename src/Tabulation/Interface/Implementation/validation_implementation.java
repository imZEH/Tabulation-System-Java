/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.validation;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class validation_implementation  implements validation{
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
    
    
    @Override
     public void update(String expiration,String bactname){
        try{
            
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String sql ="Update voters_password set Vp_Expired = '"+expiration+"' Where Vp_batchname  = '"+bactname+"'"; 
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
            
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    
    }
}
