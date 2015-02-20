/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Connections;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Neil
 */
public class Center_Frame {
    
    public void Center(JFrame frame){
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
      int       nX  = (int) (scr.getWidth()  - frame.getWidth()  ) / 2;
      int       nY  = (int) (scr.getHeight() - frame.getHeight() ) / 2;

      frame.setLocation( nX, nY );
    }
}
