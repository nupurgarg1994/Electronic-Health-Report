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
 * @author Aayush
 */
public class PharmacyEnterprise extends Enterprise{
    
    public enum PharmacyOrganizationType{
         Store("Store Organization"),Finance("Finance Organization");
        
        private String value;
        private PharmacyOrganizationType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    
    public PharmacyEnterprise(String name){
        super(name,Enterprise.EnterpriseType.Pharmacy);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
}
