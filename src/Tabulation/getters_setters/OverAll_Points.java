/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Neil
 */
public class OverAll_Points {
    public int OP_ID;
    private int OP_Percentage;
    private String OP_Status;
    private String OP_Type;

    public OverAll_Points(int id,int Percent,String Status,String Type){
        this.OP_ID = id;
        this.OP_Percentage = Percent;
        this.OP_Status = Status;
        this.OP_Type = Type;
    }
    
    
    
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
     * @return the OP_Percentage
     */
    public int getOP_Percentage() {
        return OP_Percentage;
    }

    /**
     * @param OP_Percentage the OP_Percentage to set
     */
    public void setOP_Percentage(int OP_Percentage) {
        this.OP_Percentage = OP_Percentage;
    }

    /**
     * @return the OP_Status
     */
    public String getOP_Status() {
        return OP_Status;
    }

    /**
     * @param OP_Status the OP_Status to set
     */
    public void setOP_Status(String OP_Status) {
        this.OP_Status = OP_Status;
    }

    /**
     * @return the OP_Type
     */
    public String getOP_Type() {
        return OP_Type;
    }

    /**
     * @param OP_Type the OP_Type to set
     */
    public void setOP_Type(String OP_Type) {
        this.OP_Type = OP_Type;
    }
}
