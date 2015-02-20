
package Tabulation.getters_setters;

import java.util.Date;


public class Cultural_Event {
    private int  Cul_ID;
    private int Acc_ID;
    private int OP_ID;
    private String Cul_Name;
    private String Cul_Percentage;
    private String Cul_Status;
    private String Cul_Date;
    private String Cul_Type;
    private String Cul_Sched;
    private String Fullname;
    private String type_judge;
    private String Cul_Type_Judging;
    private Date culdate;
    private String idreport;
    
    public Cultural_Event(){
        
    }
    public Cultural_Event(String point, String Teamname){
        this.Cul_Percentage = point;
        this.Cul_Name = Teamname;
    }
    
   
    
    public Cultural_Event(int _id, String _Cul_Name, String _Percent){
        this.Cul_ID = _id;
        this.Cul_Name = _Cul_Name;
        this.Cul_Percentage = _Percent;
    }
    public Cultural_Event(String _id, String _Cul_Name, String _Percent){
        this.idreport = _id;
        this.Cul_Name = _Cul_Name;
        this.Cul_Percentage = _Percent;
    }
    
    //insert transaction
    public Cultural_Event (String name, String percent, String status, String Date, String Type,String Sched,int ACCID,String typejudge){
       
        this.Cul_Name = name;
        this.Cul_Percentage = percent;
        this.Cul_Status = status;
        this.Cul_Date = Date;
        this.Cul_Sched = Sched;
        this.Acc_ID = ACCID;
        this.Cul_Type = Type;
        this.Cul_Type_Judging = typejudge;
        
    }
    
    public Cultural_Event(int CID,String cName, String cPercent,String cStatus, Date Date, String cTpe, String cSched,String cJudge){
        this.Cul_ID = CID;
        this.Cul_Name = cName;
        this.Cul_Percentage = cPercent;
        this.Cul_Status = cStatus;
        this.culdate = Date;
        this.Cul_Type = cTpe;
        this.Cul_Sched = cSched;
        this.Cul_Type_Judging = cJudge;
    }
    
    public Cultural_Event(int id, String Name, String Percent, String Stat){
        this.Cul_ID = id;
        this.Cul_Name = Name;
        this.Cul_Percentage = Percent;
        this.Cul_Status = Stat;
    }
    
    //query whole data
    public Cultural_Event(int id, String EName,String Perc, String Status,String Date, String Username, String Sched){
        this.Cul_ID = id;
        this.Cul_Name = EName;
        this.Cul_Percentage = Perc;
        this.Cul_Status = Status;
        this.Cul_Date = Date;
        this.Fullname = Username;
        this.Cul_Sched = Sched;
    }
    
    public Cultural_Event(int ID){
        this.Cul_ID = ID;
    }
    public Cultural_Event(String Event_Name){
        this.Cul_Name = Event_Name;
    }
    public Cultural_Event (int id, String Event_Name){
        this.Cul_ID = id;
        this.Cul_Name = Event_Name;
    }
    
    
   
    
   

    public int getCul_ID() {
        return Cul_ID;
    }

    
    
    
    public void setCul_ID(int Cul_ID) {
        this.Cul_ID = Cul_ID;
    }

    
    
    
    public String getCul_Name() {
        return Cul_Name;
    }

    
    
    public void setCul_Name(String Cul_Name) {
        this.Cul_Name = Cul_Name;
    }

    
    
    
    public String getCul_Percentage() {
        return Cul_Percentage;
    }

    
    
    
    public void setCul_Percentage(String Cul_Percentage) {
        this.Cul_Percentage = Cul_Percentage;
    }

    
    
    
    public String getCul_Status() {
        return Cul_Status;
    }

    
    
    
    public void setCul_Status(String Cul_Status) {
        this.Cul_Status = Cul_Status;
    }

    /**
     * @return the Cul_Date
     */
    public String getCul_Date() {
        return Cul_Date;
    }

    /**
     * @param Cul_Date the Cul_Date to set
     */
    public void setCul_Date(String Cul_Date) {
        this.Cul_Date = Cul_Date;
    }

    /**
     * @return the Acc_ID
     */
    public int getAcc_ID() {
        return Acc_ID;
    }

    /**
     * @param Acc_ID the Acc_ID to set
     */
    public void setAcc_ID(int Acc_ID) {
        this.Acc_ID = Acc_ID;
    }

    /**
     * @return the Fullname
     */
    public String getFullname() {
        return Fullname;
    }

    /**
     * @param Fullname the Fullname to set
     */
    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    /**
     * @return the Cul_Type
     */
    public String getCul_Type() {
        return Cul_Type;
    }

    /**
     * @param Cul_Type the Cul_Type to set
     */
    public void setCul_Type(String Cul_Type) {
        this.Cul_Type = Cul_Type;
    }

    /**
     * @return the Cul_Sched
     */
    public String getCul_Sched() {
        return Cul_Sched;
    }

    /**
     * @param Cul_Sched the Cul_Sched to set
     */
    public void setCul_Sched(String Cul_Sched) {
        this.Cul_Sched = Cul_Sched;
    }

    /**
     * @return the type_judge
     */
    public String getType_judge() {
        return type_judge;
    }

    /**
     * @param type_judge the type_judge to set
     */
    public void setType_judge(String type_judge) {
        this.type_judge = type_judge;
    }

    /**
     * @return the Cul_Type_Judging
     */
    public String getCul_Type_Judging() {
        return Cul_Type_Judging;
    }

    /**
     * @param Cul_Type_Judging the Cul_Type_Judging to set
     */
    public void setCul_Type_Judging(String Cul_Type_Judging) {
        this.Cul_Type_Judging = Cul_Type_Judging;
    }

    /**
     * @return the OP_ID
     */
    public int getOP_ID() {
        return OP_ID;
    }

    /**
     * @param OP_ID the OP_ID to set
     */
    public void setOP_ID(int OP_ID) {
        this.OP_ID = OP_ID;
    }

    /**
     * @return the culdate
     */
    public Date getCuldate() {
        return culdate;
    }

    /**
     * @param culdate the culdate to set
     */
    public void setCuldate(Date culdate) {
        this.culdate = culdate;
    }

    /**
     * @return the idreport
     */
    public String getIdreport() {
        return idreport;
    }

    /**
     * @param idreport the idreport to set
     */
    public void setIdreport(String idreport) {
        this.idreport = idreport;
    }

    /**
     * @return the hi
     */
    

}
