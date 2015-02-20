package Other_Functions;

import java.io.*;

public class client_is_reading {
    
    public static void main(String [] args) throws Exception{
        new client_is_reading().client();
    }
    public void client()throws Exception {
        // The name of the file to open.
        String fileName = "file.txt";
String s1;
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                s1 = bufferedReader.readLine();
                if(s1.length() != 0){
                int ans = Integer.parseInt(line)+1;
                new client_is_writing().getID(ans);
                new Client_Writing_In_Desktop().desktop(Integer.toString(ans));
                new Writing_On_My_CDRIVE().write_ON_C(ans);
                
                
				System.out.println(ans);
			}
              
                
                
            }	

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