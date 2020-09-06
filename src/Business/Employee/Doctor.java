/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;

/**
 *
 * @author Aayush
 */
public class Doctor extends Employee{
    private String fname;
    private String lname;
    private String languagesSpoken;
    private Date dateOFBirth;
//    private int streetNumber;
//    private String city;
//    private int zipCode;
//    private int houseNo;
//    private String country;
    
    

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLanguagesSpoken() {
        return languagesSpoken;
    }

    public void setLanguagesSpoken(String languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    public Date getDateOFBirth() {
        return dateOFBirth;
    }

    public void setDateOFBirth(Date dateOFBirth) {
        this.dateOFBirth = dateOFBirth;
    }

    
    
    
    
}
