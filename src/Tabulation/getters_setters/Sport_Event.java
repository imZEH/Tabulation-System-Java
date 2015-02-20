


package Tabulation.getters_setters;



public class Sport_Event {
    private int Sp_ID;
    private String Sp_Percentage;
    private String Sp_Name;
    private String Sp_Status;
    private int Acc_ID;
    private String Sp_Type;
    private String Sp_Rounds;
    private String Sp_Sched;
    private String Sp_Date;
    private String fullname;
    
    public Sport_Event(String spname){
        this.Sp_Name = spname;
    }
    
    public Sport_Event(String tname, String score){
        this.fullname = tname;
        this.Sp_Percentage  =score;
    }

    //insert data into sports table
    public Sport_Event(String per, String name, String stat, String type,String Sched,String Date,int AID){
        this.Acc_ID = AID;
        this.Sp_Percentage = per;
        this.Sp_Name = name;
        this.Sp_Status = stat;
         this.Sp_Type = type;
        this.Sp_Sched = Sched;
        this.Sp_Date = Date;
    }
    public Sport_Event(int id,String percent, String  SName,String status,String type,String Sched){
        this.Sp_ID = id;
        this.Sp_Name = SName;
        this.Sp_Percentage = percent;
        this.Sp_Status = status;
        this.Sp_Type = type;
        this.Sp_Sched = Sched;
    }
    public Sport_Event(int id,String  SName,String percent, String status,String type,String Sched,String username){
        this.Sp_ID = id;
        this.Sp_Name = SName;
        this.Sp_Percentage = percent;
        this.Sp_Status = status;
        this.Sp_Type = type;
        this.Sp_Sched = Sched;
        this.fullname = username;
    }
    
    //search in sport parameter
    public Sport_Event(int ID, String SName, String per, String Status, String type,String Sched,String date,String username){
        this.Sp_ID = ID;
        this.Sp_Name = SName;
        this.Sp_Percentage = per;
        this.Sp_Status = Status;
        this.Sp_Type = type;
        this.fullname = username;
        this.Sp_Sched = Sched;
        this.Sp_Date = date;
    }
    
    public Sport_Event(int id, String name, String per, String type){
        this.Sp_ID = id;
        this.Sp_Name = name;
        this.Sp_Percentage = per;
        this.Sp_Type = type;
    }
   
    //query ID
    public Sport_Event(int ID){
        this.Sp_ID = ID;
    }
    
    
    public int getSp_ID() {
        return Sp_ID;
    }

    
    
    public void setSp_ID(int Sp_ID) {
        this.Sp_ID = Sp_ID;
    }

    
    
    public String getSp_Percentage() {
        return Sp_Percentage;
    }

    
    
    public void setSp_Percentage(String Sp_Percentage) {
        this.Sp_Percentage = Sp_Percentage;
    }

    
    
    public String getSp_Name() {
        return Sp_Name;
    }

    
    
    public void setSp_Name(String Sp_Name) {
        this.Sp_Name = Sp_Name;
    }

    
    
    public String getSp_Status() {
        return Sp_Status;
    }

    
    
    public void setSp_Status(String Sp_Status) {
        this.Sp_Status = Sp_Status;
    }

    
    
    public int getAcc_ID() {
        return Acc_ID;
    }

    
    
    public void setAc_ID(int Acc_ID) {
        this.Acc_ID = Acc_ID;
    }

    
    public String getSp_Type() {
        return Sp_Type;
    }

    
    public void setSp_Type(String Sp_Type) {
        this.Sp_Type = Sp_Type;
    }

    
    public String getSp_Rounds() {
        return Sp_Rounds;
    }

    
    public void setSp_Rounds(String Sp_Rounds) {
        this.Sp_Rounds = Sp_Rounds;
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
     * @return the Sp_Sched
     */
    public String getSp_Sched() {
        return Sp_Sched;
    }

    /**
     * @param Sp_Sched the Sp_Sched to set
     */
    public void setSp_Sched(String Sp_Sched) {
        this.Sp_Sched = Sp_Sched;
    }

    /**
     * @return the Sp_Date
     */
    public String getSp_Date() {
        return Sp_Date;
    }

    /**
     * @param Sp_Date the Sp_Date to set
     */
    public void setSp_Date(String Sp_Date) {
        this.Sp_Date = Sp_Date;
    }

    
    
}
