/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;

/**
 *
 * @author Neil
 */
public class Reading_On_C {
    
    public void Reading()throws Exception{
        String fileName = "C:\\Users\\Public\\Documents\\Temp.properties";
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        String IDADD = ip.getHostAddress();
        // This will reference one line at a time
        
        Properties info = new Properties();
        info.load(new FileInputStream("config.properties"));
        
        String address = info.getProperty("address");
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
             line = bufferedReader.readLine();
             int   ans = Integer.parseInt(line);
          //     new ClientInitiator().initialize(ans,address);

            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}
