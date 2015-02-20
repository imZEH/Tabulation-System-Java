/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Present_Judge;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class Imp_PresentJudge implements Present_Judge{
     private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
    
    @Override
    public void add(int id,String Text){
        
        try{
        String sql = "call sqlPr_Insert_TempDB('"+id+"','"+Text+"')";
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            ps= (CallableStatement) con.prepareCall(sql);
           
            
           ps.executeUpdate();
           con.close();
           
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}
