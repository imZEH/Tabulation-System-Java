/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Neil
 */
public class Writing_On_My_CDRIVE {
    
    public int write_ON_C(int x)throws Exception{
        String fileName = "fiel.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(Integer.toString(x));
            

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
        
        return x;
    }
}
