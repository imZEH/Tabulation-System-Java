/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author Comlab1
 */
public class A_Category {
    private int id;
    private String name;
    private int cu_id;
    private int per;
    private double res_sub;
public A_Category(int id, String name){
    this.id = id;
    this.name = name;
    
    
}

public A_Category (int cat_per){
    this.per = cat_per;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cu_id
     */
    public int getCu_id() {
        return cu_id;
    }

    /**
     * @param cu_id the cu_id to set
     */
    public void setCu_id(int cu_id) {
        this.cu_id = cu_id;
    }

    /**
     * @return the per
     */
    public int getPer() {
        return per;
    }

    /**
     * @param per the per to set
     */
    public void setPer(int per) {
        this.per = per;
    }

    /**
     * @return the res_sub
     */
    public double getRes_sub() {
        return res_sub;
    }

    /**
     * @param res_sub the res_sub to set
     */
    public void setRes_sub(double res_sub) {
        this.res_sub = res_sub;
    }
    
    
}
