/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.message_getSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class message {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public message(){
        
    }
    
    public List<message_getSet>message_relay()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from tempDb");
        
        while(rs.next()){
            String Accout = rs.getString("AccountID");
            String bol = rs.getString("bol");
            message_getSet ac = new message_getSet(bol,Accout);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public boolean doneScoring(String acc, String culid, String catid, String criid)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from message Where AccountID = '"+acc+"' and CulID = '"+culid+"' and "
                + "CatID = '"+catid+"' and CRID = '"+criid+"'");
        boolean cond = false;
        if(rs.next()){
            cond = true;
            
        }
        return cond;
    }
}
