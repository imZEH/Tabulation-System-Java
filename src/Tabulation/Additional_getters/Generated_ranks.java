/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additional_getters;

/**
 *
 * @author AL AMRY
 */
public class Generated_ranks {
    private int cos_id;
    private String teamname;
    private String fullname;
    private double total;
    private double overalltot;
public Generated_ranks(int cid,String tn, String fn, double tot, double os){
    this.cos_id = cid;
    this.teamname = tn;
    this.fullname = fn;
    this.total =tot;
    this.overalltot = os;
}
    /**
     * @return the teamname
     */
    public String getTeamname() {
        return teamname;
    }

    /**
     * @param teamname the teamname to set
     */
    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the overalltot
     */
    public double getOveralltot() {
        return overalltot;
    }

    /**
     * @param overalltot the overalltot to set
     */
    public void setOveralltot(double overalltot) {
        this.overalltot = overalltot;
    }

    /**
     * @return the cos_id
     */
    public int getCos_id() {
        return cos_id;
    }

    /**
     * @param cos_id the cos_id to set
     */
    public void setCos_id(int cos_id) {
        this.cos_id = cos_id;
    }
    
}
