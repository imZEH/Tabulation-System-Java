/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Neil
 */
public class Client_Writing_In_Desktop {
    
    
    public void desktop(String hi) throws Exception{
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        String IP = ip.getHostAddress();
        String fileName = "file.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(hi);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
