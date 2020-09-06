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
public class UserEnterprise extends Enterprise{
    
         public enum UserOrganizationType{
         Premium("Premium Users Organization"), Standard ("Standard Users Organization"), Guardian("Guardian Organization");
        
        private String value;
        private UserOrganizationType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    
    public UserEnterprise(String name){
        super(name,EnterpriseType.User);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
}
