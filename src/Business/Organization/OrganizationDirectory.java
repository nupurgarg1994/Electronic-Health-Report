/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.OrganizationType;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class OrganizationDirectory {

    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public Organization createOrganization(OrganizationType type) {

        Organization organization = null;

        if (type.getValue().equals(OrganizationType.Doctor.getValue())) {

            organization = new DoctorOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Lab.getValue())) {

            organization = new LabOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Finance.getValue())) {

            organization = new FinanceOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Nurse.getValue())) {

            organization = new NurseOrganization();

            organizationList.add(organization);

        } //        else if (type.getValue().equals(OrganizationType.Orders.getValue())){
        //            organization = new OrdersOrganization();
        //            organizationList.add(organization);
        //        }
        else if (type.getValue().equals(OrganizationType.Standard.getValue())) {

            organization = new StandardPatientOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Store.getValue())) {

            organization = new StoreOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Premium.getValue())) {

            organization = new PremiumPatientOrganization();

            organizationList.add(organization);

        } else if (type.getValue().equals(OrganizationType.Guardian.getValue())) {

            organization = new GuardianOrganization();

            organizationList.add(organization);

        }

        return organization;

    }

}
