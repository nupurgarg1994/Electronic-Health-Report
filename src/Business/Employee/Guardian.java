/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.Employee.Patient.Allergy;
import Business.Employee.Patient.EmergencyContact;
import Business.Employee.Patient.FamilyHistory;
import Business.Employee.Patient.Goals;
import Business.Employee.Patient.Immunizations;
import Business.Employee.Patient.ImplantedDevices;
import Business.Employee.Patient.MedicalHistory;
import Business.Employee.Patient.Medications;
import Business.Employee.Patient.Surgeries;
import Business.Employee.Patient.VitalSigns;
import java.util.ArrayList;

/**
 *
 * @author Aayush
 */
public class Guardian extends Employee{
    private String fname;
    private String lname;
    private String relationship;
    private String middleName;
    private String mmid;
    private float height;
    private float weight;
    private String bloodGroup;
    private float bmi;
    private String smoking;
    private String drinking;
    private String motherName;
    private String fatherName;
    private String race;
    private String insuranceNo;
    private String insuranceApplicable;
    private String languagesSpoken;
    private String occupation;
    
    private ArrayList<Allergy> allergy;
    private ArrayList<Documents> documents;
    private ArrayList<EmergencyContact> emergencyContact;
    private ArrayList<FamilyHistory> familyHistory;
    private ArrayList<Goals> goals;
    private ArrayList<Immunizations> immunizations;
    private ArrayList<ImplantedDevices> implantedDevices;
    private ArrayList<MedicalHistory> medicalHistory;
    private ArrayList<Medications> medications;
    private ArrayList<Surgeries> surgeries;
    private ArrayList<VitalSigns> vitalSigns;

    public Guardian() {
        height = (float) 0.0;
        weight = (float) 0.0;
        bmi = (float) 0.0;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

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

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getDrinking() {
        return drinking;
    }

    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }

    public String getMmid() {
        return mmid;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getInsuranceApplicable() {
        return insuranceApplicable;
    }

    public void setInsuranceApplicable(String insuranceApplicable) {
        this.insuranceApplicable = insuranceApplicable;
    }

    public String getLanguagesSpoken() {
        return languagesSpoken;
    }

    public void setLanguagesSpoken(String languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
