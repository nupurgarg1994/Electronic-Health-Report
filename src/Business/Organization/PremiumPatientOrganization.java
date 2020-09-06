/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.AdminRole;
import Business.Role.PremiumPatientRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Aayush
 */
public class PremiumPatientOrganization extends Organization{
    public PremiumPatientOrganization() {
        super(OrganizationType.Premium.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new PremiumPatientRole());
        return roles;
    }
}
