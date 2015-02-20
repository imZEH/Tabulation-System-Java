/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface.Implementation;
import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.Interface.Add_User;
import Tabulation.Methods.Select_Commands.Duplicate;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */

public class Add_UserImplement implements Add_User{
   private  java.sql.Connection con ;
   private  PreparedStatement ps;
   private Statement cs;
   private ResultSet rs;
   private CallableStatement callableStatement = null;
    
    @Override
   public void AddUser(String FName, String MName, String LName, String Gender, String Status, String Type,String Username, String Password,String Image,String Address,String Number,String Path){
      
        try{
           
      String sql = "CALL sqlPr_Insert_Accounts(?,?,?,?,?,?,?,?,?,?,?,?)";
       /* String sql = "CALL sqlPr_Insert_Accounts('"+FName+"','"+MName+"','"+LName+"','"+Gender+"',"
                + "'"+Status+"','"+Type+"','"+Username+"','"+Password+"','"+Image+"','"+Address+"',"
                + "'"+Number+"','"+Path+"')";*/
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        callableStatement = (CallableStatement) con.prepareCall(sql);
        
      
        String picture = Image;
        File f=new File(picture);
        FileInputStream fis;
        fis = new FileInputStream(f);
       callableStatement.setString(1, FName);
       callableStatement.setString(2, MName);
       callableStatement.setString(3, LName);
       callableStatement.setString(4, Gender);
       callableStatement.setString(5, Status);
       callableStatement.setString(6, Type);
       callableStatement.setString(7, Username);
       callableStatement.setString(8, Password);
       callableStatement.setBinaryStream(9, (InputStream)fis, (int)(f.length()));
       callableStatement.setString(10, Address);
       callableStatement.setString(11, Number);
       callableStatement.setString(12, Path);
       
       rs = callableStatement.executeQuery();
       String b =  new Duplicate().usernDup(rs);
       
       callableStatement.executeUpdate();
       con.close();
    
      }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        
       }
 }
    
    @Override
    public void updateuser(int ID, String FName, String MName, String LName, String Gender, String Status, String Type,String Username, String Password,String Image,String Address,String Number,String Path){
        try{
            
            File f=new File(Image);
            FileInputStream fis ;
            fis = new FileInputStream(f);
            
            String sql = "CALL sqlPr_Update_Accounts(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            callableStatement = (CallableStatement) con.prepareCall(sql);
            callableStatement.setInt(1, ID);
            callableStatement.setString(2, FName);
            callableStatement.setString(3, MName);
            callableStatement.setString(4, LName);
            callableStatement.setString(5, Gender);
            callableStatement.setString(6, Status);
            callableStatement.setString(7, Type);
            callableStatement.setString(8, Username);
            callableStatement.setString(9, Password);
            callableStatement.setBinaryStream(10, (InputStream)fis, (int)(f.length()));
            callableStatement.setString(11, Address);
            callableStatement.setString(12, Number);
            callableStatement.setString(13, Path);
            callableStatement.executeUpdate();
            
            con.close();
            
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    
    @Override
    public void deactivate(int id){
        try{
            con = (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
            String sql = "Update Accounts set Acc_Status = 'InActive' where Acc_ID = '"+id+"'";
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
}
