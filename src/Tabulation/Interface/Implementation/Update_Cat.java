/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;

import Tabulation.Interface.Update_Event_Cat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class Update_Cat implements Update_Event_Cat{
    private java.sql.Connection con ;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public void Update_cat_evn(int catid, String catname, int culid, String  catper,String JudgeType){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String sql ="Update Event_Category set Category_ID = '"+catid+"',"
                    + "Category_Name = '"+catname+"',Cul_ID = '"+culid+"',Category_Per = '"+catper+"',"
                    + "Category_JudgeType = '"+JudgeType+"' where Category_ID = '"+catid+"'"; 
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
