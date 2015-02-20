/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Event_Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_EventDetails_forSearch {
     private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_EventDetails_forSearch(){
        
    }
    public List<Event_Category> CategoryDetailsforsearch(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select EC.Category_ID,EC.Category_Name,EC.Category_Per,EC.Category_JudgeType from Event_Category EC "
                + " INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where EC.Cul_ID = '"+ID+"' and EC.Category_JudgeType = 'OFFLINE'");
        while(rs.next()){
            int id = rs.getInt("Category_ID");
            String Des = rs.getString("Category_Name");
            String per = rs.getString("Category_Per");
            String JudgeType = rs.getString("Category_JudgeType");
            Event_Category EC = new Event_Category(id,Des,per,JudgeType);
            CollList.add(EC);
        }
        return CollList;
    }
    
    public List<Event_Category> UpdateCategoryDetailsforsearch(int ID)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select EC.Category_ID,EC.Category_Name,EC.Category_Per,CE.Cul_Name,CE.Cul_Type from Event_Category EC "
                + " INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where EC.Category_ID = '"+ID+"'");
        while(rs.next()){
            int id = rs.getInt("Category_ID");
            String Des = rs.getString("Category_Name");
            String per = rs.getString("Category_Per");
            String name = rs.getString("Cul_Name");
            String type = rs.getString("Cul_Type");
            Event_Category EC = new Event_Category(id,Des,per,name,type);
            CollList.add(EC);
        }
        return CollList;
    }
}
