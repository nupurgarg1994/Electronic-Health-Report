/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee.Patient;

import Business.Employee.Address;

/**
 *
 * @author Aayush
 */
 public class EmergencyContact {
   private  String name;
   private  String relationship;
   private  String phone;
   private  String emailId;
   private  Address address;

    public EmergencyContact(String name, String relationship, String phone, String emailId, Address address) {
        this.name = name;
        this.relationship = relationship;
        this.phone = phone;
        this.emailId = emailId;
        this.address = address;
    }
    
   
    
}
