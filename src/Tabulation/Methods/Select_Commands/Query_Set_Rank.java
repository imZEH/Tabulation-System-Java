/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.getters_setters.Cultural_Event;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class Query_Set_Rank {
    private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
   
   public static String ordinal(int i) {
    String[] sufixes = new String[] {"","st", "nd", "rd", "th", "th", "th", "th", "th", "th","th","th" };
    switch (i % 100) {
    case 11:
    case 12:
    case 13:
    case 10:
    case 20:
    case 30:
    case 40:
    case 50:
    case 60:
    case 70:
    case 80:
    case 90:
    case 100:
        return i + "th";
    default:
        return i + sufixes[i % 10];

    }
    
    
}
   
  
   
   public List<Cultural_Event>queryCultural_EventName()throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        rs = stmt.executeQuery("CALL sqlPr_Select_CountParticipantforRank()");
        int a = 0;
        int EVName =0;
        while(rs.next()){
           EVName = rs.getInt(1);
          }
        for(int i = EVName;i>0;i--){
              a = i;
              String ZEH = ordinal(a);
              Cultural_Event CV = new Cultural_Event(ZEH);
              
              CollList.add(CV);
           }
           
            return CollList;
        }
   
  
}
