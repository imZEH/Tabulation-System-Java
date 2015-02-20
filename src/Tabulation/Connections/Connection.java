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
 * @author acer
 */
public class Connection {
    
   public static java.sql.Connection getMySqlConnection() throws Exception {
String driver = "com.mysql.jdbc.Driver";
String user = "root";
String pass = "1234";
String url ="jdbc:mysql://localhost:3306/auto";

Class.forName(driver); 
java.sql.Connection conn = DriverManager.getConnection(url, user,pass);
return conn;
}

    
}

