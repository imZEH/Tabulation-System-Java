
package Tabulation.getters_setters;


public class Event_Category {
    private int Category_ID;
    private String Category_Name;
    private int Cul_ID;
    private String Category_Per;
    private String Cul_Type;
    private String Cul_Name;
    private String JudgeType;
    private String IDID;
    
    public Event_Category(String cname,String score){
       this.Category_Name = cname;
       this.Category_Per = score;
    }
  
    //Insert transaction
    public Event_Category(int Category_ID, String Category_Name, int Cul_ID, String Category_Per){
        this.Category_ID = Category_ID;
        this.Category_Name = Category_Name;
        this.Cul_ID = Cul_ID;
        this.Category_Per = Category_Per;
    }
    //query for category in searching
   
    public Event_Category(int ID, String Name, String per,String JudgeType){
        this.Category_ID= ID;
        this.Category_Name = Name;
        this.Category_Per = per;
        this.JudgeType = JudgeType;
    }
    
   //query category name
    public Event_Category(String Name){
        this.Category_Name = Name;
    }
    //query category ID
    public Event_Category(int ID){
        this.Category_ID = ID;
    }
    
    //update category
    public Event_Category(int ID, String Name, String per,String name,String type){
        this.Category_ID = ID;
        this.Category_Name = Name;
        this.Category_Per = per;
        this.Cul_Name = name;
        this.Cul_Type = type;
    }
   
    
    public int getCategory_ID() {
        return Category_ID;
    }

   
    
    public void setCategory_ID(int Category_ID) {
        this.Category_ID = Category_ID;
    }

    
    
    public String getCategory_Name() {
        return Category_Name;
    }

    
    
    public void setCategory_Name(String Category_Name) {
        this.Category_Name = Category_Name;
    }

    
    
    public int getCul_ID() {
        return Cul_ID;
    }

    
    
    public void setCul_ID(int Cul_ID) {
        this.Cul_ID = Cul_ID;
    }

    
    public String getCategory_Per() {
        return Category_Per;
    }

    
    public void setCategory_Per(String Category_Per) {
        this.Category_Per = Category_Per;
    }

    /*
     * @return the Cul_Name
     */
    public String getCul_Name() {
        return Cul_Name;
    }

    /*
     * @param Cul_Name the Cul_Name to set
     */
    public void setCul_Name(String Cul_Name) {
        this.Cul_Name = Cul_Name;
    }

    /*
     * @return the Cul_Type
     */
    public String getCul_Type() {
        return Cul_Type;
    }

    /*
     * @param Cul_Type the Cul_Type to set
     */
    public void setCul_Type(String Cul_Type) {
        this.Cul_Type = Cul_Type;
    }

    /*
     * @return the cat_per
     */

    /*
     * @return the cat_per
  */

    /**
     * @return the JudgeType
     */
    public String getJudgeType() {
        return JudgeType;
    }

    /**
     * @param JudgeType the JudgeType to set
     */
    public void setJudgeType(String JudgeType) {
        this.JudgeType = JudgeType;
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
