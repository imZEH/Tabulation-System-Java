


package Tabulation.getters_setters;




public class Attended_SportsEvent {
    private int Ap_ID;
    private int Sp_ID;
    private int Team_ID;
    private String EventName;
    private String EventType;
    //query attended for participant attended event
    
    public Attended_SportsEvent(int ATTID, String name,String type){
        this.Sp_ID= ATTID;
        this.EventName = name;
        this.EventType = type;
    }
    
    
    public int getAp_ID() {
        return Ap_ID;
    }

    
    
    
    public void setAp_ID(int Ap_ID) {
        this.Ap_ID = Ap_ID;
    }

    
    
    
    public int getSp_ID() {
        return Sp_ID;
    }

    
    
    
    public void setSp_ID(int Sp_ID) {
        this.Sp_ID = Sp_ID;
    }

    
    
    
    public int getTeam_ID() {
        return Team_ID;
    }

    
    
    
    public void setTeam_ID(int Team_ID) {
        this.Team_ID = Team_ID;
    }

    /**
     * @return the EventName
     */
    public String getEventName() {
        return EventName;
    }

    /**
     * @param EventName the EventName to set
     */
    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    /**
     * @return the EventType
     */
    public String getEventType() {
        return EventType;
    }

    /**
     * @param EventType the EventType to set
     */
    public void setEventType(String EventType) {
        this.EventType = EventType;
    }
}
