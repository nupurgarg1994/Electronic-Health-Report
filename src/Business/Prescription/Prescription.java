/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Prescription;

import java.util.ArrayList;

/**
 *
 * @author Mahesh Kandukuri
 */
public class Prescription {

    private int prescriptionNum;
    private String Date;
    private ArrayList<String> medicineName;
    private ArrayList<Integer> NumOfTimesPerDay;
    private String prescribedBy;

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }


    public Prescription() {
    }

    public int getPrescriptionNum() {
        return prescriptionNum;
    }

    public ArrayList<String> getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(ArrayList<String> medicineName) {
        this.medicineName = medicineName;
    }

    public ArrayList<Integer> getNumOfTimesPerDay() {
        return NumOfTimesPerDay;
    }

    public void setNumOfTimesPerDay(ArrayList<Integer> NumOfTimesPerDay) {
        this.NumOfTimesPerDay = NumOfTimesPerDay;
    }

    public void setPrescriptionNum(int prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

//    private 
//    private UserAccount 
}
