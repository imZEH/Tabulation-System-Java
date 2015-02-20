


package Tabulation.getters_setters;




public class Criteria_Cultural {
    private int Cr_ID;
    private String Cr_Description;
    private double Cr_Percentage;
    private String Category_ID;
    private String Category_Name;
    private int total;
    private String Cul_ID;
    private String Cul_Name;
    private Integer Cr_per;
    private double value;
    private double table;
    private String IDID;
    private String PERPER;
    
    public Criteria_Cultural(String criname, String score){
        this.Cr_Description = criname;
        this.Category_ID = score;
    }
   
    public Criteria_Cultural(int cid , String culid, String catid,String cname , double per){
        this.Cr_ID = cid;
        this.Cul_ID = culid;
        this.Category_ID = catid;
        this.Category_Name = cname;
        this.Cr_Percentage = per;
    }
    
    public Criteria_Cultural(String CulID,String CaID,String Des, double Per ){
        this.Category_ID = CaID;
        this.Cul_ID = CulID;
        this.Cr_Description = Des;
        this.Cr_Percentage = Per;
        
    }
    public Criteria_Cultural(double Value){
        this.value = Value;
    }
    
    //query criteria in add event module
    public Criteria_Cultural(String Name,String Des, double Per){
        this.Category_Name = Name;
        this.Cr_Description = Des;
        this.Cr_Percentage = Per;
    }
    public Criteria_Cultural(String Name,String Des, double Per,int total){
        this.Category_Name = Name;
        this.Cr_Description = Des;
        this.Cr_Percentage = Per;
        this.total = total;
        
    }
    //query for searching
     public Criteria_Cultural(String ID, String Des, String Per){
        this.IDID = ID;
        this.Cr_Description = Des;
        this.PERPER = Per;
    }
    public Criteria_Cultural(int ID, String Des, double Per){
        this.Cr_ID = ID;
        this.Cr_Description = Des;
        this.Cr_Percentage = Per;
    }
    
    
    public  Criteria_Cultural (String Name){
        
        this.Cr_Description = Name;
       
    }
    
    
    public  Criteria_Cultural (int id,String Name){
        this.Cr_ID = id;
        this.Cr_Description = Name;
       
    }
    
   //query total percentage
    public Criteria_Cultural(int total){
        this.total = total;
    }
    
    //query for update
    public Criteria_Cultural(int id,String des, double per, String name,String EID){
        this.Cr_ID = id;
        this.Cr_Description = des;
        this.Cr_Percentage = per;
        this.Category_Name = name;
        this.Cul_ID = EID;
    }
    
    
    public Criteria_Cultural(Integer Per){
        
        this.Cr_per = Per;
    }
    
    
     
    
    
    
    
    public int getCr_ID() {
        return Cr_ID;
    }

    
    
    
    public void setCr_ID(int Cr_ID) {
        this.Cr_ID = Cr_ID;
    }

    
    
    
    public String getCr_Description() {
        return Cr_Description;
    }

    
    
    
    public void setCr_Description(String Cr_Description) {
        this.Cr_Description = Cr_Description;
    }

    
    
    
    public double getCr_Percentage() {
        return Cr_Percentage;
    }

    
    
    
    public void setCr_Percentage(double Cr_Percentage) {
        this.Cr_Percentage = Cr_Percentage;
    }

    
    
    
    public String getCategory_ID() {
        return Category_ID;
    }

    
    
    
    public void setCategory_ID(String Category_ID) {
        this.Category_ID = Category_ID;
    }

   
    public String getCategory_Name() {
        return Category_Name;
    }

    
    public void setCategory_Name(String Category_Name) {
        this.Category_Name = Category_Name;
    }

    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the Cul_ID
     */
    public String getCul_ID() {
        return Cul_ID;
    }

    /**
     * @param Cul_ID the Cul_ID to set
     */
    public void setCul_ID(String Cul_ID) {
        this.Cul_ID = Cul_ID;
    }

    /**
     * @return the Cul_Name
     */
    public String getCul_Name() {
        return Cul_Name;
    }

    /**
     * @param Cul_Name the Cul_Name to set
     */
    public void setCul_Name(String Cul_Name) {
        this.Cul_Name = Cul_Name;
    }

    /**
     * @return the Cr_per
     */
    public int getCr_per() {
        return Cr_per;
    }

    /**
     * @param Cr_per the Cr_per to set
     */
    public void setCr_per(int Cr_per) {
        this.setCr_per((Integer) Cr_per);
    }

    /**
     * @param Cr_per the Cr_per to set
     */
    public void setCr_per(Integer Cr_per) {
        this.Cr_per = Cr_per;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the table
     */
    public double getTable() {
        return 0.00;
    }

    /**
     * @param table the table to set
     */
    public void setTable(double table) {
        this.table = table;
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

    /**
     * @return the PERPER
     */
    public String getPERPER() {
        return PERPER;
    }

    /**
     * @param PERPER the PERPER to set
     */
    public void setPERPER(String PERPER) {
        this.PERPER = PERPER;
    }

   
}
