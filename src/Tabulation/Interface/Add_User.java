/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface Add_User {
    public void AddUser(String FName, String MName, String LName, String Gender, String Status, String Type,String Username, String Password,String Image,String Address,String Number,String Path);
    public void updateuser(int ID, String FName, String MName, String LName, String Gender,String Username, String Password,String Status, String Type, String Address, String Contactr, String Pic, String path);
    public void deactivate(int id);
}
