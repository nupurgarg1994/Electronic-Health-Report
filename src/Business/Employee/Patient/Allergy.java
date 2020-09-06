/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee.Patient;

import java.util.Date;

/**
 *
 * @author Aayush
 */
public class Allergy {

    private String allergyType;
    private String allergicTo;
    private String observedBy;
    private Date firstObserved;
    private String desc;

    public Allergy() {
        
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    public String getAllergicTo() {
        return allergicTo;
    }

    public void setAllergicTo(String allergicTo) {
        this.allergicTo = allergicTo;
    }

    public String getObservedBy() {
        return observedBy;
    }

    public void setObservedBy(String observedBy) {
        this.observedBy = observedBy;
    }

    public Date getFirstObserved() {
        return firstObserved;
    }

    public void setFirstObserved(Date firstObserved) {
        this.firstObserved = firstObserved;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    

    
}
