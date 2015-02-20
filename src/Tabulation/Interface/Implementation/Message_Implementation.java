/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Message_interface;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Message_Implementation implements Message_interface{
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private Statement st;
   
   
    @Override
   public void add(String desc, String time, String acc, String culid, String catid, String criid){
       try{
       
            String sql = "INSERT INTO message(description,time,AccountID,CulID,CatID,CRID) "
                    + "   Values('"+desc+"',"+time+",'"+acc+"','"+culid+"','"+catid+"','"+criid+"')";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            ps= (CallableStatement) con.prepareCall(sql);
           
           
            
            rs = ps.executeQuery();
            String b =  new Duplicate().donescoring(rs);
            
           ps.executeUpdate();
           con.close();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
   }
}
