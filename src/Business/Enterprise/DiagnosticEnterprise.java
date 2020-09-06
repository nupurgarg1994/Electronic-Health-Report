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



public class DiagnosticEnterprise extends Enterprise{
    
        public enum DiagnosticOrganizationType{
         Lab("Lab Organization"),Finance("Finance Organization");
        
        private String value;
        private DiagnosticOrganizationType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    
    public DiagnosticEnterprise(String name){
        super(name,Enterprise.EnterpriseType.Diagnostic);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
}
