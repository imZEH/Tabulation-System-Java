


package Tabulation.getters_setters;

import java.awt.Image;
import java.sql.Blob;




public class Participant {
    private int id;
    private String Fname;
    private String Mname;
    private String Lname;
    private String Address;
    private String ContactNum;
    private Image picture;
    private String team;
    private String Fullname;
    private int teamnum;
    private String path;
    private String TName;
    private String sec_num;
    private String Gender;
    private String Status;
    private String PartNum;
   private String IDID;

    /**
     * @return the Fname
     */
   public Participant(String _Num, String _fulname, String _Team, String _ID){
        this.PartNum = _Num;
        this.Fullname = _fulname;
        this.TName = _Team;
        this.IDID = _ID;
    }
    
    public Participant(String _Num, String _fulname, String _Team, int _ID){
        this.PartNum = _Num;
        this.Fullname = _fulname;
        this.TName = _Team;
        this.id = _ID;
    }
    
    public Participant (String FName, String MName,String LName,String Team,int Num){
        this.Fname = FName;
        this.Mname = MName;
        this.Lname = LName;
        this.team = Team;
        this.teamnum = Num;
    }
    
    //query whole participant details
    
    public Participant(int id, String fullname, String gender, String Contact,String address,String status,String team){
        this.id = id;
        this.Fullname = fullname;
        this.Gender = gender;
        this.ContactNum = Contact;
        this.Address = address;
        this.Status = status;
        this.TName = team;
        
    }
    
    //query for update
    public Participant(int id,String FName, String MName,String LName,String address,String gender, String Contact ,String status,String TName,String path,Image img){
        this.id = id;
        this.Fname = FName;
        this.Mname = MName;
        this.Lname = LName;
        this.Address = address;
        this.ContactNum = Contact;
        this.TName = TName;
        this.path = path;
        this.Gender = gender;
        this.Status = status;
        this.picture = img;
    }
    
     public Participant (int id,String FN,String Team){
        this.Fullname = FN;
        this.id = id;
        this.Gender = Team;
        
    }
     
     public Participant(int id, String name, String Gender, String Team){
             this.Fullname = name;
             this.id = id;
            this.Gender = Gender;
             this.TName = Team;
     }
     
     public Participant (int id,String Team){
        
        this.id = id;
        this.team = Team;
        
    }
    
       public Participant (String FN,String Team,int Num){
        this.Fullname = FN;
        
        this.team = Team;
        this.teamnum = Num;
    }
     public Participant (String FName, String MName,String LName){
        this.Fname = FName;
        this.Mname = MName;
        this.Lname = LName;
       
    }
      public Participant (String secnum){
       this.sec_num = secnum;
    }
      
      
    public String getFname() {
        return Fname;
    }

    
    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    
    public String getMname() {
        return Mname;
    }

    
    public void setMname(String Mname) {
        this.Mname = Mname;
    }

    
    public String getLname() {
        return Lname;
    }

    
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    
    public String getTeam() {
        return team;
    }

    
    public void setTeam(String team) {
        this.team = team;
    }

    
    public int getTeamnum() {
        return teamnum;
    }

   
    public void setTeamnum(int teamnum) {
        this.teamnum = teamnum;
    }

    
    public String getFullname() {
        return Fullname;
    }

    
    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    
    public String getAddress() {
        return Address;
    }

    
    public void setAddress(String Address) {
        this.Address = Address;
    }

    
    public String getContactNum() {
        return ContactNum;
    }

    
    public void setContactNum(String ContactNum) {
        this.ContactNum = ContactNum;
    }

   
    public Image getPicture() {
        return picture;
    }

    
    public void setPicture(Image picture) {
        this.picture = picture;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the TName
     */
    public String getTName() {
        return TName;
    }

    /**
     * @param TName the TName to set
     */
    public void setTName(String TName) {
        this.TName = TName;
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
     * @return the Gender
     */
    public String getGender() {
        return Gender;
    }

    /**
     * @param Gender the Gender to set
     */
    public void setGender(String Gender) {
        this.Gender = Gender;
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
     * @return the PartNum
     */
    public String getPartNum() {
        return PartNum;
    }

    /**
     * @param PartNum the PartNum to set
     */
    public void setPartNum(String PartNum) {
        this.PartNum = PartNum;
    }

    /**
     * @return the IDID
     */
    public String getIDID() {
        return IDID;
    }

    /**
     * @param IDID the IDID to set
     */
    public void setIDID(String IDID) {
        this.IDID = IDID;
    }
    
 
}
