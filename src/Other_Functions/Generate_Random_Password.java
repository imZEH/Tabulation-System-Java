/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.util.Random;

/**
 *
 * @author Neil
 */
public class Generate_Random_Password {
   
    public String generate(){
    char[] chars = "AaBbCcDdEeFfGgHhIJjKkLMmNnPpQqRrSsTtUuVvWwXxYyZz123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
                }
    String output = sb.toString();
    return output;
    }
}

