/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.OverAll_Points;
import Tabulation.getters_setters.Ranking_Points;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_Overall_Points {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public View_Overall_Points(){
        
    }
    
    public List<OverAll_Points>queryoverall_points( String data)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Overll_Points('"+data+"','"+data+"','"+data+"');");
        
        while(rs.next()){
            int id = rs.getInt(1);
            int percent = rs.getInt(2);
            String status = rs.getString(3);
            String type = rs.getString(4);
            OverAll_Points ac = new OverAll_Points(id,percent,status,type);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Ranking_Points>queryCulturalSports_Rank( String data)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Rank_for_SportsandCultural('"+data+"','"+data+"')");
        
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String type = rs.getString(3);
            Ranking_Points ac = new Ranking_Points(id,name,type);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Ranking_Points>query_Rank( String data)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_Rank('"+data+"')");
        
        while(rs.next()){
            double points = rs.getInt(1);
            String rank = rs.getString(2);
            Ranking_Points ac = new Ranking_Points(points,rank);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
}
