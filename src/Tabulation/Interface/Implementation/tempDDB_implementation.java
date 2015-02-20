/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.tempDB;
import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class tempDDB_implementation implements tempDB{
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
    
    @Override
    public void add(String bol, String acc){
        try{
            
            String qeury = "CALL _tempDB(?,?)";
            
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement= (CallableStatement) con.prepareCall(qeury);
           
            callableStatement.setString(1, bol);
            callableStatement.setString(2, acc);
            
            callableStatement.executeUpdate();
            con.close();
            
        }catch(Exception e){
            System.out.print(e);
        }
    }
}
