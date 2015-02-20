

package Tabulation.getters_setters;

import java.awt.Image;
import java.sql.Blob;



public class Team {
    private int Team_ID;
    private String Team_Name;
    private String Team_Number;
    private Image Team_Pic;
    private String Team_Path;
    private String Team_Status;
    private String Type;
    private String tID;

    public Team(){};
    
    
    public Team(String ID, String Name, String num){
        this.tID = ID;
        this.Team_Name = Name;
        this.Team_Number = num;
    }
    
    
    //qeury team name
    public Team(String Team_Name,int id,String type){
        this.Team_Name = Team_Name;
        this.Team_ID = id;
        this.Type = type;
        
    }
    
    public Team(String Team_Name){
        this.Team_Name = Team_Name;
    }
    
    //query whole team details
    public Team(int id, String Teamname, String tnumber){
        this.Team_ID = id;
        this.Team_Name = Teamname;
        this.Team_Number = tnumber;
    }
    
    
    public Team(int id, String Teamname, String tnumber,String status){
        this.Team_ID = id;
        this.Team_Name = Teamname;
        this.Team_Number = tnumber;
        this.Team_Status = status;
    }
    
    //query for update
    public Team(int id, String Teamname, String tnumber,String status,String path,Image pic){
        this.Team_ID = id;
        this.Team_Name = Teamname;
        this.Team_Number = tnumber;
        this.Team_Status = status;
        this.Team_Path = path;
        this.Team_Pic = pic;
    }
    
    public int getTeam_ID() {
        return Team_ID;
    }

    
    
    public void setTeam_ID(int Team_ID) {
        this.Team_ID = Team_ID;
    }

    
    
    public String getTeam_Name() {
        return Team_Name;
    }

    
    
    public void setTeam_Name(String Team_Name) {
        this.Team_Name = Team_Name;
    }

    
    
    public String getTeam_Number() {
        return Team_Number;
    }

    
    
    public void setTeam_Number(String Team_Number) {
        this.Team_Number = Team_Number;
    }

    
    public Image getTeam_Pic() {
        return Team_Pic;
    }

    
    public void setTeam_Pic(Image Team_Pic) {
        this.Team_Pic = Team_Pic;
    }

    /**
     * @return the Team_Path
     */
    public String getTeam_Path() {
        return Team_Path;
    }

    /**
     * @param Team_Path the Team_Path to set
     */
    public void setTeam_Path(String Team_Path) {
        this.Team_Path = Team_Path;
    }

    /**
     * @return the Team_Status
     */
    public String getTeam_Status() {
        return Team_Status;
    }

    /**
     * @param Team_Status the Team_Status to set
     */
    public void setTeam_Status(String Team_Status) {
        this.Team_Status = Team_Status;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the tID
     */
    public String gettID() {
        return tID;
    }

    /**
     * @param tID the tID to set
     */
    public void settID(String tID) {
        this.tID = tID;
    }
    
}
