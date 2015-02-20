/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Connections;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * @author Neil
 */
public class Copy_Files {
    public static void copyFile(File sourceFile, File destFile)
            throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            // previous code: destination.transferFrom(source, 0, source.size());
            // to avoid infinite loops, should be:
            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size
                    - count)) < size)
                ;
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public void PATH1(String Path) {
       try {
        File sourceFile = new File(Path);
        File destFile = new File("CompanyLOGO.png");
        copyFile(sourceFile,destFile);
        } catch (IOException ex) {
           ex.printStackTrace();
         }
    }
    public void PATH2(String Path) {
       try {
        File sourceFile = new File(Path);
        File destFile = new File("OragnizationLOGO.png");
        copyFile(sourceFile,destFile);
        } catch (IOException ex) {
           ex.printStackTrace();
         }
    }
}

