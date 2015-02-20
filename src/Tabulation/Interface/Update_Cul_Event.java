/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface Update_Cul_Event {
    public void update_Cultural(int Cid, int AID,String Cul_Name, String Cul_Percentage,String stat, String Date,String Type ,String Sched,String judgetype);
    public void Delete_Cultural(int id);
    public void Delete_Category(int id);
    public void Delete_Criteria(int id);
    public void Delete_User(int id);
    public void Delete_Participant(int id);
    public void Delete_Team(int id);
    public void Delete_Sports(int id);
    public void Delete_Assigned_Cultural(int id,int ID1);
    public void Delete_Assigned_Sports(int ID,int ID1);
    public void Delete_Overall(int id);
}
