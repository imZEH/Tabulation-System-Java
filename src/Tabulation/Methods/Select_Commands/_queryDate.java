/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Voters_Password;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class _queryDate {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public _queryDate(){
        
    }
    
    public List<Voters_Password>_date()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT VP_DateCreated FROM `voters_password` GROUP by VP_DateCreated");
        
        while(rs.next()){
            String date = rs.getString("VP_DateCreated");
            Voters_Password ac = new Voters_Password(date);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Voters_Password>_pass(String date)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM `voters_password` Where VP_DateCreated = '"+date+"' and VP_Printed = 'NP'");
        
        while(rs.next()){
            String pass = rs.getString("VP_Pass");
            String DateCreate = rs.getString("VP_DateCreated");
            String DateExpire = rs.getString("VP_Expired");
            String Status = rs.getString("VP_Status");
            String PrintedStat = rs.getString("Vp_Printed");
            Voters_Password ac = new Voters_Password(pass,DateCreate,DateExpire,Status,PrintedStat);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public void reset_Sport_Rank(String batchname)throws Exception{
       String sql = "Update voters_password set VP_Printed = 'P' Where Vp_batchname = '"+batchname+"' and VP_Printed = 'NP'";
       con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
       pst = (PreparedStatement) con.prepareCall(sql);
       pst.executeUpdate();
       con.close();
   }
}
