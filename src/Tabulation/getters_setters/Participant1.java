/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class Participant1 {
    private String Fname;
    private String Mname;
    private String Lname;
    private String team;
    private int teamnum;
    private String sec_num;
    private String fullname;

     public Participant1 (String id,String FN,String Team){
        this.sec_num = id;
        this.fullname = FN;
        this.team = Team;
        
    }
    
    public String getFname() {
        return Fname;
    }

    /**
     * @param Fname the Fname to set
     */
    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    /**
     * @return the Mname
     */
    public String getMname() {
        return Mname;
    }

    /**
     * @param Mname the Mname to set
     */
    public void setMname(String Mname) {
        this.Mname = Mname;
    }

    /**
     * @return the Lname
     */
    public String getLname() {
        return Lname;
    }

    /**
     * @param Lname the Lname to set
     */
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    /**
     * @return the team
     */
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
     * @return the teamnum
     */
    public int getTeamnum() {
        return teamnum;
    }

    /**
     * @param teamnum the teamnum to set
     */
    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
    }

    /**
     * @return the sec_num
     */
    public String getSec_num() {
        return sec_num;
    }

    /**
     * @param sec_num the sec_num to set
     */
    public void setSec_num(String sec_num) {
        this.sec_num = sec_num;
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
    
 
}

