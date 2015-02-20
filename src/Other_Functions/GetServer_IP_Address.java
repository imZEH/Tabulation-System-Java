/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Neil
 */
public class GetServer_IP_Address {
    
    
    
    public String hello ()throws Exception{
        Properties info = new Properties();
        info.load(new FileInputStream("config.properties"));
        
        String address = info.getProperty("address");
        
        
        return address;
    }
}
