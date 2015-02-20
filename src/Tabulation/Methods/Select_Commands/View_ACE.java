/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Attended_CulturalEvents;
import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Get_Ace;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class View_ACE {
     private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public View_ACE(){
        
    }
    public List<Get_Ace> v_ace(int part, int cul)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT ACE_ID  FROM attended_culturalevents where Part_ID = "+part+" and Cul_ID = "+cul+"");
        while(rs.next()){
            int id = rs.getInt("ACE_ID");
            
            Get_Ace EC = new Get_Ace(id);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
    
    public List<Get_Ace> v_ace2(int cul, int ac)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT ACE_ID  FROM attended_culturalevents where Cul_ID = "+cul+" and ACE_Secnum = "+ac+"");
        while(rs.next()){
            int id = rs.getInt("ACE_ID");
            
            Get_Ace EC = new Get_Ace(id);
            CollList.add(EC);
        }
        return CollList;
    }
}
