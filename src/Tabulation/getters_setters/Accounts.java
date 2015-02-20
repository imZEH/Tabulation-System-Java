


package Tabulation.getters_setters;

import java.awt.Image;
import javax.swing.ImageIcon;




public class Accounts{
    private int Acc_ID;
    private String Acc_FName;
    private String Acc_MName;
    private String Acc_LName;
    private String Acc_Username;
    private String Acc_Password;
    private String Acc_Address;
    private String Acc_ContactNumber;
    private String Status;
    private String Type;
    private String FullName;
    private ImageIcon image;
    private String path;
    private String Acc_Gender;
    private Image Picture;
    private String msgname;

   
    public Accounts(){
        
    }
    
    public Accounts(int ID,String Fullname,String Type,String status){
        this.Acc_ID = ID;
        this.FullName = Fullname;
        this.Type = Type;
        this.Status = status;
        
    }
    
    //query details for update
    public Accounts(int ID, String FName,String MName,String LName, String Gender,String Status,String Type,String user,String password,String Address, String Contact,  String path,Image Pic){
        this.Acc_ID = ID;
        this.Acc_FName = FName;
        this.Acc_MName = MName;
        this.Acc_LName = LName;
        this.Acc_Gender = Gender;
        this.Status = Status;
        this.Type = Type;
        this.Acc_Username = user;
        this.Acc_Password = password;
        this.Acc_Address = Address;
        this.Acc_ContactNumber = Contact;
        this.path = path;
        this.Picture = Pic;
    }
    
    public Accounts(int ID, String Fullname,String username,String Gender ,String Address, String Contact, String Status, String Type){
        this.Acc_ID = ID;
        this.FullName = Fullname;
        this.Acc_Username = username;
        this.Acc_Gender = Gender;
        this.Acc_Address = Address;
        this.Acc_ContactNumber = Contact;
        this.Status = Status;
        this.Type = Type;
    }
    
    public Accounts(int Acc_ID){
        this.Acc_ID= Acc_ID;
    }
    
    //query image
    public Accounts(ImageIcon image){
        this.image = image;
    }
    
    public int getAcc_ID() {
        return Acc_ID;
    }

    
    
    
    public void setAcc_ID(int Acc_ID) {
        this.Acc_ID = Acc_ID;
    }

    
    
    
    public String getAcc_FName() {
        return Acc_FName;
    }

    
    
    
    public void setAcc_FName(String Acc_FName) {
        this.Acc_FName = Acc_FName;
    }

    
    
    
    public String getAcc_MName() {
        return Acc_MName;
    }

    
    
    
    public void setAcc_MName(String Acc_MName) {
        this.Acc_MName = Acc_MName;
    }

    
    
    
    public String getAcc_LName() {
        return Acc_LName;
    }

    
    
    
    public void setAcc_LName(String Acc_LName) {
        this.Acc_LName = Acc_LName;
    }

    
    
    
    public String getAcc_Username() {
        return Acc_Username;
    }

    
    
    
    public void setAcc_Username(String Acc_Username) {
        this.Acc_Username = Acc_Username;
    }

    
    
    
    public String getAcc_Password() {
        return Acc_Password;
    }

    
    
    
    public void setAcc_Password(String Acc_Password) {
        this.Acc_Password = Acc_Password;
    }

    
    
    
    public String getStatus() {
        return Status;
    }

    
    
    
    public void setStatus(String Status) {
        this.Status = Status;
    }

   
    
    public String getType() {
        return Type;
    }

   
    
    public void setType(String Type) {
        this.Type = Type;
    }

    
    
    public String getFullName() {
        return FullName;
    }

    
    
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     * @return the Acc_Address
     */
    public String getAcc_Address() {
        return Acc_Address;
    }

    /**
     * @param Acc_Address the Acc_Address to set
     */
    public void setAcc_Address(String Acc_Address) {
        this.Acc_Address = Acc_Address;
    }

    /**
     * @return the Acc_ContactNumber
     */
    public String getAcc_ContactNumber() {
        return Acc_ContactNumber;
    }

    /**
     * @param Acc_ContactNumber the Acc_ContactNumber to set
     */
    public void setAcc_ContactNumber(String Acc_ContactNumber) {
        this.Acc_ContactNumber = Acc_ContactNumber;
    }

    /**
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageIcon image) {
        this.image = image;
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
     * @return the Acc_Gender
     */
    public String getAcc_Gender() {
        return Acc_Gender;
    }

    /**
     * @param Acc_Gender the Acc_Gender to set
     */
    public void setAcc_Gender(String Acc_Gender) {
        this.Acc_Gender = Acc_Gender;
    }

    /**
     * @return the Picture
     */
    public Image getPicture() {
        return Picture;
    }

    /**
     * @param Picture the Picture to set
     */
    public void setPicture(Image Picture) {
        this.Picture = Picture;
    }

    /**
     * @return the msgname
     */
    public String getMsgname() {
        return msgname;
    }

    /**
     * @param msgname the msgname to set
     */
    public void setMsgname(String msgname) {
        this.msgname = msgname;
    }
}
