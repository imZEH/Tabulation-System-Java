/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Additional_getters;

/**
 *
 * @author AL AMRY
 */
public class View_Cul_Ranks {
    private int id;
    private int cul_id;
    private int point;
    private String ranks;

    public View_Cul_Ranks(int e_id, int e_cul_id,int pts, String rks ){
        this.id = e_id;
        this.cul_id = e_cul_id;
        this.point = pts;
        this.ranks = rks;
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
     * @return the cul_id
     */
    public int getCul_id() {
        return cul_id;
    }

    /**
     * @param cul_id the cul_id to set
     */
    public void setCul_id(int cul_id) {
        this.cul_id = cul_id;
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * @return the ranks
     */
    public String getRanks() {
        return ranks;
    }

    /**
     * @param ranks the ranks to set
     */
    public void setRanks(String ranks) {
        this.ranks = ranks;
    }
    
    
}
