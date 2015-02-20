/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Connections;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class Message extends Thread{
   
    String msg = "";
    DataInputStream dis = null;
    JTextArea txt = null;
    String NAME = "";
    
    public Message(DataInputStream d,JTextArea a){
        this.dis = d;
        this.txt = a;
    }
   
    
    
    public void run(){
        while(true){
            try {
                Calendar date=Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                msg = dis.readUTF();
                txt.append("\n"+this.getName()+":"+msg+"\t"+sdf.format(date.getTime()));
            } catch (IOException ex) {
                Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
