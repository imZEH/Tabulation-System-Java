/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface Insert_Attend_Cul {
    public void insert_attendCultural(String Culname);
    public void insert_assignCultural(int id,String Culname);
    public void inser_attendSport(int SPID);
    public void insert_assignedSport(int Pid, String SpID);
    public void Update_AttendCultural(int PartID,int CulID);
    public void Update_AttendSport(int PartID,int CulID);
}
