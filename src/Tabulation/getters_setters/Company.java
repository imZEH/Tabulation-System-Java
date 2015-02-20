/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.getters_setters;

/**
 *
 * @author user
 */
public class Company {
    private String compname;
    private String org;
    private String add;
    private String title;
    private int id;
    
    public Company(int id,String name, String orga, String add, String title){
        this.compname = name;
        this.org = orga;
        this.add = add;
        this.title = title;
    }

    /**
     * @return the compname
     */
    public String getCompname() {
        return compname;
    }

    /**
     * @param compname the compname to set
     */
    public void setCompname(String compname) {
        this.compname = compname;
    }

    /**
     * @return the org
     */
    public String getOrg() {
        return org;
    }

    /**
     * @param org the org to set
     */
    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * @return the add
     */
    public String getAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(String add) {
        this.add = add;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
}
