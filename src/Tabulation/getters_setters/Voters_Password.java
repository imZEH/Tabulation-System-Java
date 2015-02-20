/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class Voters_Password {
    private int VP_ID;
    private String VP_Pass;
    private String Status;
    private String Date;
    private String printed;
    private String expire;
    private String batcname;

    public Voters_Password(String Date){
        this.Date = Date;
    }
    
    
    public Voters_Password(String pass,String DateCreate,String DateExpire,String Status,String PrintedStat){
        this.VP_Pass = pass;
        this.Date = DateCreate;
        this.expire = DateExpire;
        this.Status = Status;
        this.printed = PrintedStat;
    }
    
    
    public Voters_Password(String pass,String DateCreate,String DateExpire,String Status,String PrintedStat,String bname){
        this.VP_Pass = pass;
        this.Date = DateCreate;
        this.expire = DateExpire;
        this.Status = Status;
        this.printed = PrintedStat;
        this.batcname = bname;
    }
    /**
     * @return the VP_ID
     */
    public int getVP_ID() {
        return VP_ID;
    }

    /**
     * @param VP_ID the VP_ID to set
     */
    public void setVP_ID(int VP_ID) {
        this.VP_ID = VP_ID;
    }

    /**
     * @return the VP_Pass
     */
    public String getVP_Pass() {
        return VP_Pass;
    }

    /**
     * @param VP_Pass the VP_Pass to set
     */
    public void setVP_Pass(String VP_Pass) {
        this.VP_Pass = VP_Pass;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return the printed
     */
    public String getPrinted() {
        return printed;
    }

    /**
     * @param printed the printed to set
     */
    public void setPrinted(String printed) {
        this.printed = printed;
    }

    /**
     * @return the expire
     */
    public String getExpire() {
        return expire;
    }

    /**
     * @param expire the expire to set
     */
    public void setExpire(String expire) {
        this.expire = expire;
    }

    /**
     * @return the batcname
     */
    public String getBatcname() {
        return batcname;
    }

    /**
     * @param batcname the batcname to set
     */
    public void setBatcname(String batcname) {
        this.batcname = batcname;
    }
}
