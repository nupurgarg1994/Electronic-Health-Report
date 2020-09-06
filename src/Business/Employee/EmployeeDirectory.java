/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Business.Employee.Employee.EmployeeType;
import Business.Organization.Organization;
import static Business.Organization.Organization.OrganizationType.Premium;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class EmployeeDirectory {
    
    private ArrayList<Employee> employeeList;

    public EmployeeDirectory() {
        employeeList = new ArrayList();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
    
//   public Employee createEmployee(String name, Organization org){
//       Employee emp = null;
//       if(org.getName().equals(Organization.OrganizationType.Standard.getValue()))
//       { 
//        emp = new StandardPatient();
//        emp.setName(name);
//        }
//       
//        if(org.getName().equals(Organization.OrganizationType.Premium.getValue()))
//       { 
//        emp = new PremiumPatient();
//        emp.setName(name);
//        }
//        
//       employeeList.add(emp);
//       return emp;
//   }

    public Employee createEmployee(String name, EmployeeType type) {

        if (type.getValue().equals(EmployeeType.Sysadmin.getValue())){

            Employee employee = new Employee();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Enterpriseadmin.getValue())){

            Employee employee = new Employee();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Finance.getValue())){

            Employee employee = new FinanceEmployee();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Doctor.getValue())){

            Employee employee = new Doctor();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Nurse.getValue())){

            Employee employee = new Nurse();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Standard.getValue())){

            Employee employee = new StandardPatient();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Premium.getValue())){

             Employee employee = new PremiumPatient();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Guardian.getValue())){

            Employee employee = new Guardian();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Store.getValue())){

            Employee employee = new StoreEmployee();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        else if (type.getValue().equals(EmployeeType.Lab.getValue())){

            Employee employee = new LabAssistant();

            employee.setName(name);

            employeeList.add(employee);
            return employee;

        }

        return null;

    }
   
//       public Employee createEmployee(String name) {
//        Employee employee = new Employee();
//        employee.setName(name);
//        employeeList.add(employee);
//        return employee;}
       
}