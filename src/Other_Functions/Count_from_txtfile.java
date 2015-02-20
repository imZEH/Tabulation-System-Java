/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Other_Functions;

import java.util.Scanner;

/**
 *
 * @author Neil
 */
public class Count_from_txtfile {


    public static void main(String[] args)
    {
        Scanner read = new Scanner(System.in);
 
        String myString = new String();
 
        System.out.print("Please enter any string: ");
        myString = read.nextLine();
        int line = 0, let = 0, size = 0;
 
        System.out.println("\nThe words are:");
 
        for(int i=0;i < myString.length(); i++)
        {
            char letter = myString.charAt(i);
 
            boolean ch = Character.isWhitespace(letter);
 
            if(ch || myString.length()-1==i)
            {
                System.out.print(letter);
                System.out.println();
                let++;
                line++;
 
            }
            else
            {
                System.out.print(letter);
            }
        }
        System.out.println("\nLines: " + line);
        System.out.println("How many words: " + let);
    }
}
