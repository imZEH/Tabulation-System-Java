/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface._inserttemp;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class _inserttemp_implement implements _inserttemp{
    
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
    @Override
     public void add(String Team_ID,double score){
         try{
        
       
        String sql = "insert into temp(team_ID,totalscore) Values('"+Team_ID+"','"+score+"')";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(sql);
        
        callableStatement.executeUpdate();
       con.close();
         }catch(Exception e){}
     }
}
