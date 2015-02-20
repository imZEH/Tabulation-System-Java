/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

import Tabulation.Graphical_User_Interface.Administrator_Module.administrator;
import Tabulation.Graphical_User_Interface.Administrator_Module.login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Neil
 */
public class SplashDemo {
public static SplashScreen loadingScreen;
public static Double loadingTextArea;
public static Double loadingProgressArea;
public static Graphics2D loadingGraphics;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic herelo
        loadingMethod();
        mainMethod();
        
        if(loadingScreen != null){
            loadingScreen.close();
        }
    }
    
    public static void loadingMethod(){
        loadingScreen = SplashScreen.getSplashScreen();
        if(loadingScreen != null){
            Dimension dim = loadingScreen.getSize();
            int ht = dim.height;
            int wd = dim.width;
            
            loadingTextArea = new Rectangle2D.Double(15 , ht*0.7, wd*0.4,30);
            loadingProgressArea = new Rectangle2D.Double(15,ht *0.85, wd *0.4,25);
            
            loadingGraphics = loadingScreen.createGraphics();
            
        }
    
    }
    public static void loadingText(String string){
        if(loadingScreen!=null){
            loadingGraphics.setPaint(Color.CYAN);
            loadingGraphics.fill(loadingTextArea);
            loadingGraphics.setPaint(Color.black);
            loadingGraphics.drawString(string, (int) loadingTextArea.getX()+10, (int) loadingTextArea.getY()+20);
            
            loadingScreen.update();
            
        }
    }
    
    public static void loadingProgress(int prog){
        
        if(loadingScreen != null){
            loadingGraphics.setPaint(Color.gray);
            loadingGraphics.fill(loadingProgressArea);
            
            loadingGraphics.setPaint(Color.BLACK);
            loadingGraphics.draw(loadingProgressArea);
            
            int x = (int)loadingProgressArea.getMinX();
            int y = (int) loadingProgressArea.getMinY();
            
            int width = (int) loadingProgressArea.getWidth();
            int height = (int) loadingProgressArea.getHeight();
            
            int donePorg = prog * width/ 100;
            
            loadingGraphics.setPaint(Color.GREEN);
            loadingGraphics.fillRect(x, y, donePorg, height);
            
            loadingScreen.update();
        }
    }
    
    public static void mainMethod() throws IOException{
        for(int i  = 1; i <=10 ; i++){
            
            
            try{
                 try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                URL url  = new URL("");
                
                BufferedReader in =  new BufferedReader(new InputStreamReader(url.openStream()));
                
                String str;
                while((str = in.readLine()) != null){
                    System.out.println(str);
                }
                in.close();
            }catch(MalformedURLException e){
                
            }catch(IOException e){}
        
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new login().setVisible(true);
            }
        });
        
    }
}
