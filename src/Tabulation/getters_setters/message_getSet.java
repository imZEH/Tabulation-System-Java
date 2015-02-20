/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class message_getSet {
    private String ID;
    private String Des;
    private String time;
    private String Accid;
    private String culid;
    private String catid;
    private String crid;
    private String bol;
    
    
    public message_getSet(String bol,String acc){
        this.Accid = acc;
        this.bol = bol;
        
    }
    
    public message_getSet(String acc, String culid, String catid, String crid){
        this.Accid = acc;
        this.culid = culid;
        this.catid = catid;
        this.crid = crid;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the Des
     */
    public String getDes() {
        return Des;
    }

    /**
     * @param Des the Des to set
     */
    public void setDes(String Des) {
        this.Des = Des;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the Accid
     */
    public String getAccid() {
        return Accid;
    }

    /**
     * @param Accid the Accid to set
     */
    public void setAccid(String Accid) {
        this.Accid = Accid;
    }

    /**
     * @return the culid
     */
    public String getCulid() {
        return culid;
    }

    /**
     * @param culid the culid to set
     */
    public void setCulid(String culid) {
        this.culid = culid;
    }

    /**
     * @return the catid
     */
    public String getCatid() {
        return catid;
    }

    /**
     * @param catid the catid to set
     */
    public void setCatid(String catid) {
        this.catid = catid;
    }

    /**
     * @return the crid
     */
    public String getCrid() {
        return crid;
    }

    /**
     * @param crid the crid to set
     */
    public void setCrid(String crid) {
        this.crid = crid;
    }

    /**
     * @return the bol
     */
    public String getBol() {
        return bol;
    }

    /**
     * @param bol the bol to set
     */
    public void setBol(String bol) {
        this.bol = bol;
    }
}
