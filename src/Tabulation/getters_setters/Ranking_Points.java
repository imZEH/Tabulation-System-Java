/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class Ranking_Points {
    private int ID;
    private String Event_Name;
    private double Points;
    private String Rank;
    private String Event_Type;
    
    
    public Ranking_Points(int id,String name,String type){
        this.ID = id;
        this.Event_Name = name;
        this.Event_Type = type;
    }
    
    public Ranking_Points(double points,String rank){
        this.Points = points;
        this.Rank = rank;
    }
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Event_Name
     */
    public String getEvent_Name() {
        return Event_Name;
    }

    /**
     * @param Event_Name the Event_Name to set
     */
    public void setEvent_Name(String Event_Name) {
        this.Event_Name = Event_Name;
    }

    /**
     * @return the Points
     */
    public double getPoints() {
        return Points;
    }

    /**
     * @param Points the Points to set
     */
    public void setPoints(double Points) {
        this.Points = Points;
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
     * @return the Event_Type
     */
    public String getEvent_Type() {
        return Event_Type;
    }

    /**
     * @param Event_Type the Event_Type to set
     */
    public void setEvent_Type(String Event_Type) {
        this.Event_Type = Event_Type;
    }
}
