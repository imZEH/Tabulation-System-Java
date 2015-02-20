/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.add_companyinfo;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class add_compinfo_implements implements add_companyinfo{
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   
    @Override
    public void add(String comp,String org, String add, String title){
            try{
            
            String sql = "insert into company values(?,?,?,?)";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        ps = (PreparedStatement) con.prepareStatement(sql);
        
        ps.setString(1, comp);
        ps.setString(2, org);
        ps.setString(3, add);
        ps.setString(4, title);
        ps.executeUpdate();
            
            
             }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
}
