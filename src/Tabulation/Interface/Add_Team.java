/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface Add_Team {
    public void Team(String TName, String TNumber, String TeamPic, String path,String Status,String Color);
    public void Participant(int TID, String FName, String MName, String LName, String Add,String Gender,String Contact, String Status, String Pic,String path);
    public void updateparticipant(int PDI,int TID, String FName, String MName, String LName, String Add,String Gender,String Contact, String Status, String Pic,String path);
    public void updateTeam(int TID, String TName, String TNumber, String TeamPic,String path,String Status,String Color);

}
