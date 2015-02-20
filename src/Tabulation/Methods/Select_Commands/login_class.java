/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Connections.EncryptAndDecrypt;
import Tabulation.getters_setters.Accounts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil
 */
public class login_class {
    public login_class(){
        
    }
private java.sql.Connection con = null;
private PreparedStatement pst = null;
private ResultSet rs = null;
  
    public List<Accounts>queryAccount(String username,String password)throws Exception{
        List CollList = new ArrayList();
        con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
        java.sql.Statement stmt = con.createStatement();
        EncryptAndDecrypt encrypter = new EncryptAndDecrypt("");
        String decrypted = encrypter.encrypt(password); 
        rs = stmt.executeQuery("CALL sqlPr_Select_Accounts_Login('"+username+"','"+decrypted+"')");
        
        while(rs.next()){
            int ID = rs.getInt("Acc_ID");
            String Full = rs.getString("fullname");
            String Type = rs.getString("Acc_Type");
            String status = rs.getString("Acc_Status");
            Accounts ac = new Accounts(ID,Full,Type,status);
            CollList.add(ac);
            
        }
        con.close();
        return CollList;
    }
}
