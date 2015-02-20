/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Connections;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Neil
 */
public class Conn {
    public static java.sql.Connection getMySqlConnection() throws Exception {
EncryptAndDecrypt encrypter = new EncryptAndDecrypt("");
String driver = "com.mysql.jdbc.Driver";
Properties info = new Properties();
info.load(new FileInputStream("config.properties"));
String address = info.getProperty("address");
String user = info.getProperty("username");
String pass = info.getProperty("password");
String url ="jdbc:mysql://"+address+":3306/automated";

Class.forName(driver); 
java.sql.Connection conn = DriverManager.getConnection(url, user,pass);
return conn;
}
}
