/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class HospitalEnterprise extends Enterprise {
    
    public enum HospitalOrganizationType{
         Doctor("Doctor Organization"), Nurse("Nurse Organization"),Finance("Finance Organization");
        
        private String value;
        private HospitalOrganizationType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    
    public HospitalEnterprise(String name){
        super(name,EnterpriseType.Hospital);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
}
