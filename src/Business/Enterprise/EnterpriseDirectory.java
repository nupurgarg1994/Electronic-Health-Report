/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.OrganizationDirectory;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseList;
   

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }
    
    public EnterpriseDirectory(){
        enterpriseList=new ArrayList<Enterprise>();
    }
    
    //Create enterprise
    public Enterprise createAndAddEnterprise(String name,Enterprise.EnterpriseType type){
        Enterprise enterprise=null;
        if(type==Enterprise.EnterpriseType.Hospital){
            enterprise=new HospitalEnterprise(name);
            enterpriseList.add(enterprise);
        }
         if(type==Enterprise.EnterpriseType.Diagnostic){
            enterprise=new DiagnosticEnterprise(name);
            enterpriseList.add(enterprise);
        }
         if(type==Enterprise.EnterpriseType.Pharmacy){
            enterprise=new PharmacyEnterprise(name);
            enterpriseList.add(enterprise);
        }
         if(type==Enterprise.EnterpriseType.User){
            enterprise=new UserEnterprise(name);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
    
    public void deleteEnterprise(Enterprise e)
    {
        enterpriseList.remove(e);
    }
    
    public ArrayList<Enterprise> getSpecificEnterpriseList(Enterprise.EnterpriseType type) {
        ArrayList<Enterprise> enterprise = new ArrayList<Enterprise>();
        if (this.getEnterpriseList() != null) {
            for (Enterprise e : this.getEnterpriseList()) {
                if (e.getEnterpriseType().getValue().equals(type.getValue())) {
                    enterprise.add(e);
                }
            }
        }
        return enterprise;

    }
}
