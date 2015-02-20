/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Insert_Commands;

import Tabulation.getters_setters.Cultural_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class getifgrouporindi {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public String get(String TID,String CID) throws Exception{
        String EventName = null;
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select ACE.Part_ID from attended_culturalevents ACE join cultural_events CE ON CE.Cul_ID = ACE.Cul_ID "
                + "JOIN Participants P ON P.Part_ID = ACE.Part_ID"
                + " JOIN Teams T ON T.Team_ID = P.Team_ID Where T.Team_ID = '"+TID+"' and CE.Cul_ID = '"+CID+"' LIMIT 1");
        while(rs.next()){
            EventName = rs.getString("Part_ID");
            
        }
        
        return EventName;
    }
}
