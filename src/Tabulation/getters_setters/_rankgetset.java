/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class _rankgetset {
    private String Team_Name;
    private double Score;
    private  String Rank;
    private double points;
    private int cul_id;

    /**
     * @return the cul_id
     */
    
    
    public  _rankgetset(double scre, String teaname,int id ){
        this.Score = scre;
        this.Team_Name = teaname;
        this.cul_id = id;
    }
     public  _rankgetset(double scre, int id ){
        this.points = scre;
        this.cul_id = id;
    }
    public int getCul_id() {
        return cul_id;
    }

    /**
     * @param cul_id the cul_id to set
     */
    public void setCul_id(int cul_id) {
        this.cul_id = cul_id;
    }

    /**
     * @return the Team_Name
     */
    
    
    public String getTeam_Name() {
        return Team_Name;
    }

    /**
     * @param Team_Name the Team_Name to set
     */
    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
    }

    /**
     * @return the Score
     */
    public double getScore() {
        return Score;
    }

    /**
     * @param Score the Score to set
     */
    public void setScore(double Score) {
        this.Score = Score;
    }

    /**
     * @return the Rank
     */
    public String getRank() {
        return Rank;
    }

    /**
     * @param Rank the Rank to set
     */
    public void setRank(String Rank) {
        this.Rank = Rank;
    }

    /**
     * @return the points
     */
    public double getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(double points) {
        this.points = points;
    }
}
