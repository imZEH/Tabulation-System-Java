/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Insert_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Sport_Event;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import Tabulation.Methods.Select_Commands.Duplicate;

/**
 *
 * @author Neil
 */
public class Add_Sports_Event {
    private java.sql.Connection con ;
    private PreparedStatement ps ;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    public void EventAdd(Sport_Event SE)throws Exception{
        
        String query = "CALL sqlPr_Insert_Sports_Event(?,?,?,?,?,?,?);";
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(query);
        
        callableStatement.setInt(1, SE.getAcc_ID());
        callableStatement.setString(2, SE.getSp_Percentage());
        callableStatement.setString(3, SE.getSp_Name());
        callableStatement.setString(4, SE.getSp_Status());
        callableStatement.setString(5, SE.getSp_Type());
        callableStatement.setString(6, SE.getSp_Sched());
        callableStatement.setString(7, SE.getSp_Date());
        
        rs = callableStatement.executeQuery();
        String b = new Duplicate().dup_Sports_Event(rs);
        
        callableStatement.executeUpdate();
        con.close();
    }
}
