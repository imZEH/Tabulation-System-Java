package Other_Functions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class server_is_writing {
    
    
    
    public void server()throws Exception {

        // The name of the file to open.
        String fileName = "\\\\"+new GetServer_IP_Address().hello()+"\\Users\\Public\\temp.properties";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("5000");

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