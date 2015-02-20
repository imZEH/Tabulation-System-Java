/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabulation.Methods.Select_Commands;

/**
 *
 * @author Neil
 */
public class getDate {
    
    public String  getmonth(String a){
           
           
        switch (a) {
            case "January":
                a = "01";
                break;
            case "February":
                a = "02";
                break;
            case "March":
                a = "03";
                break;
            case "April":
                a = "04";
                break;
            case "May":
                a = "05";
                break;
            case "June":
                a = "06";
                break;
            case "July":
                a = "07";
                break;
            case "August":
                a = "08";
                break;
            case "September":
                a = "09";
                break;
            case "October":
                a = "10";
                break;
            case "November":
                a = "11";
                break;
            case "December":
                a = "12";
                break;    
        }
        return a;
            
            }
    public String  getmonths(String a){
           
        switch (a) {
            case "01":
                a = "January";
                break;
            case "02":
                a = "February";
                break;
            case "03":
                a = "March";
                break;
            case "04":
                a = "April";
                break;
            case "05":
                a = "May";
                break;
            case "06":
                a = "June";
                break;
            case "07":
                a = "July";
                break;
            case "08":
                a = "August";
                break;
            case "09":
                a = "September";
                break;
            case "10":
                a = "October";
                break;
            case "11":
                a = "November";
                break;
            case "12":
                a = "December";
                break;    
        }
        return a;
            
            }
}
