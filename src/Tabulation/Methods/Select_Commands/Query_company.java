/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Company;
import Tabulation.getters_setters.Cultural_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Query_company {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public List<Company>Query_company()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select * from company");
        
        while(rs.next()){
            int id = rs.getInt("Comp_ID");
            String Name = rs.getString("Comp_Name");
            String Org = rs.getString("Comp_Org");
            String add = rs.getString("Comp_Add");
            String title = rs.getString("Comp_Title");
            Company ac = new Company(id,Name,Org,add,title);
            CollList.add(ac);
            
        }
        return CollList;
    }
}
