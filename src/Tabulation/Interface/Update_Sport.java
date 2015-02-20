/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface Update_Sport {
    public void UpdateSport (int ID, int AID ,String percent, String SName,String status,String type,String Sched,String Date);
    public void del(int id,String  name);public void del1(int id,String  name);
    public void deactivate(int id);
}
