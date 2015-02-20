/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class Report {
    private String team;
    private String part;
    private double tot;
    private double ave;

    /**
     * @return the team
     */
    public Report(String tm, String pt, double total, double average){
        this.team = tm;
        this.part = pt;
        this.tot = total;
        this.ave = average;
    }
    
    
    public String getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return the part
     */
    public String getPart() {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart(String part) {
        this.part = part;
    }

    /**
     * @return the tot
     */
    public double getTot() {
        return tot;
    }

    /**
     * @param tot the tot to set
     */
    public void setTot(double tot) {
        this.tot = tot;
    }

    /**
     * @return the ave
     */
    public double getAve() {
        return ave;
    }

    /**
     * @param ave the ave to set
     */
    public void setAve(double ave) {
        this.ave = ave;
    }
    
    
}
