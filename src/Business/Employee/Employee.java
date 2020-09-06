/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.UserAccount.UserAccount;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author raunak
 */
public class Employee {
    
    
    private String name;
    //private String fname;
    //private String lname;
    private int id;
    private static int count = 1;
    private Address address;
    private Date dob;
    private String salutation;
    private String gender;
    private String emailId;
    private String ssn;
    private String phoneNo;
    private ImageIcon imageIcon;
   
    public enum EmployeeType{

        Sysadmin("Employee"), 
        Enterpriseadmin("Enterprise Admin"), Finance("Finance Employee"),
        Doctor("Doctor"), Nurse("Nurse"), 
        Standard ("Standard Patient"), Premium ("Premium Patient"), Guardian ("Guardian"),
        Store("Pharmacy Store Employee"),
        Lab("Lab Assistant");

        private String value;
        private EmployeeType(String value) {
            this.value = value;
        }

        public String getValue() {

            return value;

        }

    }
    

    public Employee() {
        id = count;
        count++;
        address = new Address();
        dob=new Date();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Employee.count = count;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    
    
    @Override
    public String toString() {
        return name;
    }
    
    
}
