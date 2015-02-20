/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Insert_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.Criteria_Window;
import Tabulation.Methods.Select_Commands.Duplicate;
import Tabulation.getters_setters.Criteria_Cultural;
import Tabulation.getters_setters.Cultural_Event;
import Tabulation.getters_setters.Event_Category;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Neil
 */
public class Add_Cultural_Event {
    private java.sql.Connection con ;
    private PreparedStatement ps ;
    private ResultSet rs;
    private CallableStatement callableStatement = null;
    
    public void EventAdd(Cultural_Event CV)throws Exception{
        
        String query = "CALL slqPr_Insert_Cultural_Event(?,?,?,?,?,?,?,?)";
        
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(query);
        
        
       
        callableStatement.setInt(1, CV.getAcc_ID());
        callableStatement.setString(2, CV.getCul_Name());
        callableStatement.setString(3, CV.getCul_Percentage());
        callableStatement.setString(4, CV.getCul_Status());
        callableStatement.setString(5, CV.getCul_Date());
        callableStatement.setString(6, CV.getCul_Type());
        callableStatement.setString(7, CV.getCul_Sched());
        callableStatement.setString(8, CV.getCul_Type_Judging());
        
        rs = callableStatement.executeQuery();
        String b =  new Duplicate().dup_Cultural(rs)
                ;
        callableStatement.executeUpdate();
        con.close();
    }
    
    public void AddCategory(Event_Category EC)throws Exception{
        String query = "CALL sqlPr_Insert_Event_Category(?,?,?,?)";
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(query);
        
        
        callableStatement.setInt(1, EC.getCategory_ID());
        callableStatement.setString(2, EC.getCategory_Name());
        callableStatement.setString(3, EC.getCategory_Per());
        callableStatement.setString(4, EC.getJudgeType());
        
        rs = callableStatement.executeQuery();
        String b =  new Duplicate().dup_SubEvent(rs);
        
        callableStatement.executeUpdate();
        con.close();
    }
    
    public void AddCriteria_Cultural(Criteria_Cultural CC)throws Exception{
        String query = "CALL sqlPr_Insert_Criteria_Cultural(?,?,?,?);";
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(query);
        
        callableStatement.setString(1, CC.getCul_ID());
        callableStatement.setString(2, CC.getCategory_ID());
        callableStatement.setString(3, CC.getCr_Description());
        callableStatement.setDouble(4, CC.getCr_Percentage());
        
        rs = callableStatement.executeQuery();
        String b =  new Duplicate().dup_Criteria(rs);
        
        callableStatement.executeUpdate();
        
        con.close();
    }
     public void AddCriteria_Category(Criteria_Cultural CC)throws Exception{
        String query = "CALL sqlPr_Insert_Criteria_Category(?,?,?,?);";
        con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement= (CallableStatement) con.prepareCall(query);
        
        callableStatement.setString(1, CC.getCul_ID());
        callableStatement.setString(2, CC.getCategory_ID());
        callableStatement.setString(3, CC.getCr_Description());
        callableStatement.setDouble(4, CC.getCr_Percentage());
        
        rs = callableStatement.executeQuery();
        String b =  new Duplicate().dup_Criteria(rs);
        
        callableStatement.executeUpdate();
        con.close();
    }
}
