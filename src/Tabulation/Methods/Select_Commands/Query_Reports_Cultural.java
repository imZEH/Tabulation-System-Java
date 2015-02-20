/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Event_Category;
import Tabulation.getters_setters.Sport_Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_Reports_Cultural {
    private java.sql.Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public Query_Reports_Cultural(){
        
    }
    
    public List<Cultural_Event>first(String text,String type,String category)throws Exception{
         List CollList = new ArrayList();
         String b = null;
        switch (type) {
            case "INDIVIDUAL":
               if(category.equals("With Category")){
                   b =  "SELECT Contestant as name,SUM(total_cultural) as total,Team_Name FROM rpt_cultural_with_category Where JType= '"+type+"' and Cul_Name = '"+text+"' group by contestant ORDER BY Cul_Name,total DESC";
               }
               else{
                   b= "SELECT Contestant as name,SUM(total_cultural) as total,Team_Name FROM rpt_cultural_withno_category Where JType= '"+type+"' and Cul_Name = '"+text+"' group by contestant ORDER BY Cul_Name,total DESC";
               }
                 break;
            case "BY TEAM":
                if(category.equals("With No Category")){
                   b= "SELECT Team_Name as name,SUM(total_cultural) as total,Team_Name FROM rpt_cultural_with_category Where JType= '"+type+"' and Cul_Name = '"+text+"' group by Team_ID ORDER BY Cul_Name,total DESC"; 
                }
                else{
                   b= "SELECT Team_Name as name,SUM(total_cultural) as total,Team_Name FROM rpt_cultural_withno_category Where JType= '"+type+"' and Cul_Name = '"+text+"' group by Team_ID ORDER BY Cul_Name,total DESC";
                }
                
                break;
        }
         DecimalFormat df2 = new DecimalFormat( "#.##" );
       
         
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery(b);
        
        while(rs.next()){
            String name = rs.getString("name");
            double score = rs.getDouble("total");
            String a = df2.format(score);
            Cultural_Event ac = new Cultural_Event(name,a);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    public List<Event_Category> second(String name,String type)throws Exception{
        List CollList = new ArrayList();
        String b = null;
        switch (type) {
            case "INDIVIDUAL":
                b =  "SELECT Contestant as name,SUM(total_Category) as total,Team_Name "
                        + "FROM rpt_category Where JType= '"+type+"' and Category_Name = '"+name+"' group by contestant ORDER BY Cul_Name,total DESC";
                break;
            case "BY TEAM":
                b= "SELECT Team_Name as name,SUM(total_Category) as total,Team_Name "
                        + "FROM rpt_category Where JType= '"+type+"' and Category_Name = '"+name+"' group by Team_Name ORDER BY Cul_Name,total DESC";
                break;
        }
         DecimalFormat df2 = new DecimalFormat( "#.##" );
       
         
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery(b);
        
        while(rs.next()){
            String cname = rs.getString("name");
            double score = rs.getDouble("total");
            String a = df2.format(score);
            Event_Category ac = new Event_Category(cname,a);
            CollList.add(ac);
            
        }
      
        
        return CollList;
    }
    
    public List<Criteria_Cultural> third(String name,String type,String category)throws Exception{
       List CollList = new ArrayList();
         String b = null;
        switch (type) {
            case "INDIVIDUAL":
               if(category.equals("With Category")){
                   b =  "SELECT Contestant as name,SUM(Cr_Computed) as total"
                           + " FROM rpt_criteria_withcategory Where JType= '"+type+"' and Cr_Description = '"+name+"' group by contestant ORDER BY Cul_Name,total DESC";
               }
               else{
                   b =  "SELECT Contestant as name,SUM(Cr_Computed) as total"
                           + " FROM rpt_criteria_withno_category Where JType= '"+type+"' and Cr_Description = '"+name+"' group by contestant ORDER BY Cul_Name,total DESC";
               }
                 break;
            case "BY TEAM":
                if(category.equals("With No Category")){
                   b= "SELECT Team_Name as name,SUM(Cr_Computed) as total"
                           + " FROM rpt_criteria_withno_category Where JType= '"+type+"' and Cr_Description = '"+name+"' group by Team_ID ORDER BY Cul_Name,total DESC"; 
                }else{
                    b =  "SELECT Team_Name as name,SUM(Cr_Computed) as total"
                           + " FROM rpt_criteria_withcategory Where JType= '"+type+"' and Cr_Description = '"+name+"' group by contestant ORDER BY Cul_Name,total DESC";
                }
                
                break;
        }
         DecimalFormat df2 = new DecimalFormat( "#.##" );
       
         
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery(b);
        
        while(rs.next()){
            String criname = rs.getString("name");
            double score = rs.getDouble("total");
            String a = df2.format(score);
            Criteria_Cultural ac = new Criteria_Cultural(criname,a);
            CollList.add(ac);
            
        }
        return CollList;
    }
    
    
    public List<Event_Category> CategoryDetailsforsearch(String name)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select EC.Category_ID,EC.Category_Name,EC.Category_Per,EC.Category_JudgeType from Event_Category EC "
                + " INNER JOIN Cultural_Events CE ON CE.Cul_ID=EC.Cul_ID where CE.Cul_Name = '"+name+"' and EC.Category_JudgeType = 'OFFLINE'");
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
    
    public List<Criteria_Cultural> CriteriaDetailsforsearch1(String text)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Cultural_Events CE ON CE.Cul_ID=CC.Cul_ID where CE.Cul_Name = '"+text+"'");
        while(rs.next()){
            int id = rs.getInt("Cr_ID");
            String Des = rs.getString("Cr_Description");
            double per = rs.getDouble("Cr_Percentage");
            Criteria_Cultural EC = new Criteria_Cultural(id,Des,per);
            CollList.add(EC);
        }
        return CollList;
    }
    
     public List<Criteria_Cultural> CriteriaDetailsforsearch2(String text)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("Select CC.Cr_ID,CC.Cr_Description,CC.Cr_Percentage from Criteria_Cultural CC "
                 + "INNER JOIN Event_Category EC ON EC.Category_ID=CC.Category_ID where EC.Category_Name = '"+text+"'");
        while(rs.next()){
            String Des = rs.getString("Cr_Description");
            Criteria_Cultural EC = new Criteria_Cultural(Des);
            CollList.add(EC);
        }
        return CollList;
}
     public List<Sport_Event>spreport(String search)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM rpt_sportsevent Where Sp_name = '"+search+"' ORDER BY total_sports DESC");
        DecimalFormat df2 = new DecimalFormat( "#.##" );
        while(rs.next()){
           
            String SName = rs.getString("Team_Name");
            double score = rs.getDouble("total_Sports");
            String total = df2.format(score);
            Sport_Event ac = new Sport_Event(SName,total);
            CollList.add(ac);
            
        }
        return CollList;
    }
}
