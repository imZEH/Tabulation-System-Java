

package Tabulation.getters_setters;



public class Attended_CulturalEvents {
    private int Ace_ID;
    private int Cul_ID;
    private int Team_ID;
    private String EventName;

    public Attended_CulturalEvents (int Num){
        this.Cul_ID = Num;
       
    }
    
   
    //query attended for participant attended event
    
    public Attended_CulturalEvents(int ATTID, String name){
        this.Cul_ID = ATTID;
        this.EventName = name;
    }
    
    
    public int getAce_ID() {
        return Ace_ID;
    }

    
    
    
    public void setAce_ID(int Ace_ID) {
        this.Ace_ID = Ace_ID;
    }

    
    
    
    public int getCul_ID() {
        return Cul_ID;
    }

    
    
    
    public void setCul_ID(int Cul_ID) {
        this.Cul_ID = Cul_ID;
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
    
}
