/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee.Patient;

/**
 *
 * @author Aayush
 */
public class Goals {
    private String healthMeasure;
    private String myResult;
    private String target;
    private String suggestedBy;
    private String Comments;

    public Goals(String healthMeasure, String myResult, String target, String suggestedBy, String Comments) {
        this.healthMeasure = healthMeasure;
        this.myResult = myResult;
        this.target = target;
        this.suggestedBy = suggestedBy;
        this.Comments = Comments;
    }
    
    
}
