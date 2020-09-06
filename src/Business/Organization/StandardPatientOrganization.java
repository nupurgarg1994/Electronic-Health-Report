/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.PremiumPatientRole;
import Business.Role.Role;
import Business.Role.StandardPatientRole;
import java.util.ArrayList;

/**
 *
 * @author Aayush
 */
public class StandardPatientOrganization extends Organization{
    public StandardPatientOrganization() {
        super(OrganizationType.Standard.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new StandardPatientRole());
        return roles;
    }
    
}
