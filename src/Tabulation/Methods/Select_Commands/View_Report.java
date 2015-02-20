/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Connections.ResizeImage1;
import Tabulation.getters_setters.Participant;
import Tabulation.getters_setters.Report;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Neil
 */
public class View_Report {
     public View_Report(){
        
    }
   private java.sql.Connection con = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;
     public List<Report>View_Report(String cul)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("SELECT t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(cr.Cr_Computed) as total, co.COS_TotalScore from participants p "
                + "inner join teams t on p.Team_ID=t.Team_ID "
                + "inner join attended_culturalevents ac on p.Part_ID = ac.Part_ID "
                + "inner join criteria_score cr on ac.ACE_ID = cr.ACE_ID "
                + "inner join cultural_overall_score co on ac.ACE_ID = co.ACE_ID "
                + "inner join cultural_events ce on ac.Cul_ID = ce.Cul_ID where ce.Cul_Name = '"+cul+"' "
                + "group by fullname Order by Cos_TotalScore Desc ");
        while(rs.next()){
            
            
            String Team = rs.getString("Team_Name");
            String part = rs.getString("fullname");
            double tot = rs.getDouble("total");
            double Ave = rs.getDouble("COS_TotalScore");
            
            Report ac1 = new Report(Team,part,tot,Ave);
            
            CollList.add(ac1);
            
        }
        return CollList;
    }
     
     
     
     public List<Report>View_Report2(String cul)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
      
        rs = stmt.executeQuery("SELECT t.Team_Name, CONCAT(p.Part_FName,' ',p.Part_MName,' ',p.Part_LName)fullname, sum(cr.S_CompScore) as total, co.SOS_TotalScore from participants p "
                + "inner join teams t on p.Team_ID=t.Team_ID "
                + "inner join attended_sportsevents ac on p.Part_ID = ac.Part_ID "
                + "inner join sports_score cr on ac.Ap_ID = cr.Ap_ID "
                + "inner join sports_overall_score co on ac.Ap_ID = co.Ap_ID "
                + "inner join sports_events ce on ac.Sp_ID = ce.Sp_ID where ce.Sp_Name = '"+cul+"' "
                + "group by fullname Order by SOS_TotalScore Desc");
        while(rs.next()){
            
            
            String Team = rs.getString("Team_Name");
            String part = rs.getString("fullname");
            double tot = rs.getDouble("total");
            double Ave = rs.getDouble("SOS_TotalScore");
            
            Report ac1 = new Report(Team,part,tot,Ave);
            
            CollList.add(ac1);
            
        }
        return CollList;
    }
     
}
