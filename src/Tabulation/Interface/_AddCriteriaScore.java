/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Interface;

/**
 *
 * @author Neil
 */
public interface _AddCriteriaScore {
    public void _add(int pID, int cID, int crID , double score,String percent,String accid, int category, String time);
     public void _addnocat(int pID, int cID, int crID , double score,String percent,String accid, String time);
     public void _addsport_score(int SPID, int TID, double Score,String accid);
}
