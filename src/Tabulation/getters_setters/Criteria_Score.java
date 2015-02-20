
package Tabulation.getters_setters;


public class Criteria_Score {
    private int CrScore_ID;
    private double CrScore;
    private int Cr_ID;
    private double Cr_sum;

   public Criteria_Score(double sum){
       this.Cr_sum = sum;
   }
    public int getCrScore_ID() {
        return CrScore_ID;
    }

   
    public void setCrScore_ID(int CrScore_ID) {
        this.CrScore_ID = CrScore_ID;
    }

    
    public double getCrScore() {
        return CrScore;
    }

    
    public void setCrScore(double CrScore) {
        this.CrScore = CrScore;
    }

    
    public int getCr_ID() {
        return Cr_ID;
    }

    
    public void setCr_ID(int Cr_ID) {
        this.Cr_ID = Cr_ID;
    }

    /**
     * @return the Cr_sum
     */
    public double getCr_sum() {
        return Cr_sum;
    }

    /**
     * @param Cr_sum the Cr_sum to set
     */
    public void setCr_sum(double Cr_sum) {
        this.Cr_sum = Cr_sum;
    }
}
