/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Connections;

/**
 *
 * @author Neil
 */
public class Export {
    public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
 
        String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
    
    public static void  main (String [] args){
        String db = "auto";
        String username = "root";
        String pass = "1234";
        String dir = "backup.sql";
        new Export().backupDB(db, username, pass, dir);
    }
}
