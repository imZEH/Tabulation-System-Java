/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additonal_methods;

import Tabulation.Additional_getters.View_Cul_Ranks;
import Tabulation.Additional_getters.View_gen_id;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AL AMRY
 */
public class Select_gen_id {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Select_gen_id (){
    }
    public List<View_gen_id>View_gen()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("Select Crp_id from cultural_generated_ranking_points");
        while(rs.next()){
            String id = rs.getString("Crp_id");
            
            
            View_gen_id EC = new View_gen_id(id);
            CollList.add(EC);
        }
        return CollList;
    }
    
    
    
    
     public List<View_gen_id>View_gen_sport()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement(); 
        rs = stmt.executeQuery("Select Sgrp_Id from sport_generated_rankingpoints");
        while(rs.next()){
            String id = rs.getString("Sgrp_id");
            
            
            View_gen_id EC = new View_gen_id(id);
            CollList.add(EC);
        }
        return CollList;
    }
    }

