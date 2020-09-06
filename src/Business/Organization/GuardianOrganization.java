/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.GuardianRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Aayush
 */
public class GuardianOrganization extends Organization{
    public GuardianOrganization() {
        super(OrganizationType.Guardian.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new GuardianRole());
        return roles;
    }
}
