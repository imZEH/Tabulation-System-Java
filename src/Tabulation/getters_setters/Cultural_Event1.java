/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class Cultural_Event1 {
    private int  Cul_ID;
    private String Cul_Name;
    private String Cul_Percentage;
    private String Cul_Status;
    private String Cul_Date;
    private int Acc_ID;
    private String type_judge;
    
    /**
     * @return the Cul_ID
     */
    
    
   public Cultural_Event1 (String per){
    this.Cul_Percentage = per;
    
}
    public int getCul_ID() {
        return Cul_ID;
    }

    /**
     * @param Cul_ID the Cul_ID to set
     */
    public void setCul_ID(int Cul_ID) {
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
     * @return the Cul_Percentage
     */
    public String getCul_Percentage() {
        return Cul_Percentage;
    }

    /**
     * @param Cul_Percentage the Cul_Percentage to set
     */
    public void setCul_Percentage(String Cul_Percentage) {
        this.Cul_Percentage = Cul_Percentage;
    }

    /**
     * @return the Cul_Status
     */
    public String getCul_Status() {
        return Cul_Status;
    }

    /**
     * @param Cul_Status the Cul_Status to set
     */
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
}
