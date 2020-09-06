/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Validations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author nupur
 */
public class ValidationResult {
    
    public boolean isValid;
    public int value;
    public float fvalue;

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    //--------to validate the Integer data entered 
        public static boolean isInt(JTextField f, String msg)
 {
          ValidationResult result = new ValidationResult();
          //boolean flag = true;
  try
  {
   result.value = Integer.parseInt(f.getText());
   return true;
  } 
  catch (NumberFormatException e)
  {
//   JOptionPane.showMessageDialog(f,
//    "Entry Error", msg,
//    JOptionPane.ERROR_MESSAGE);
   f.setBorder(new LineBorder(Color.red,1));
   f.setText("Enter only Integer Values");
   //f.requestFocus();
   return false;
  }
 }
        
        //--------------to validate the large integer data entered 
public static boolean isLong(JTextField f, String msg)
 {
          ValidationResult result = new ValidationResult();
  try
  {
   result.fvalue = Long.parseLong(f.getText());
   //result.isValid = true;
   return true;
  } 
  catch (NumberFormatException e)
  {
//   
   f.setBorder(new LineBorder(Color.red,1));
    f.setText("Only numbers are allowed");
   f.requestFocus();
   //result.isValid = false;
   result.value = 0;
   return false;
  }
 }
        
    
    //--------------to validate the Float data entered 
        public static boolean isFloat(JTextField f, String msg)
 {
          ValidationResult result = new ValidationResult();
  try
  {
   result.fvalue = Float.parseFloat(f.getText());
   //result.isValid = true;
   return true;
  } 
  catch (NumberFormatException e)
  {
//   
   f.setBorder(new LineBorder(Color.red,1));
    f.setText("Float values are allowed");
   f.requestFocus();
   //result.isValid = false;
   result.value = 0;
   return false;
  }
 }
        
 //------------to validate email address entered
        
   public static boolean isemail(JTextField f,String email) {
       
       //ValidationResult result = new ValidationResult();
       //int result;
       boolean b = true;
   String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    List<String> forbiddenDomains = Arrays.asList("domain1", "domain2");
    
    if ( email == null || email.trim().equals("")){
                f.setBorder(new LineBorder(Color.red,1)); 
                b=false;
                
        //new IllegalArgumentException("Email should not be empty!");
                                                   }
 if ( !pattern.matcher(email).matches()) {
        f.setBorder(new LineBorder(Color.red,1)); 
        b=false;
        
 }
 if ( forbiddenDomains.contains(email)){
    f.setBorder(new LineBorder(Color.red,1)); 
        b=false;
 }
    
    if(!b)
    f.setText("Enter the Correct Email  Format");
    return b; 
    }
   
   
   //--------- validate the column should not be empty and should contain only string value without any special characters
   public static boolean isnotnull(JTextField f,String value) {
    if ( value == null || value.trim().equals("")){
        f.setBorder(new LineBorder(Color.red,1));
        f.setText("Cannot be null");
        return false;
        //throw new IllegalArgumentException("Name should not be empty!");
    }
    else
        return true;
   }
    //--------only String Values Allowed
   public static boolean isAString(JTextField f,String value) {
    
    if ( !value.matches("[a-zA-Z]+")){
        f.setBorder(new LineBorder(Color.red,1));
        f.setText("Only Strings are allowed");
        return false;
    }
    else
        return true;
   }
   
//      public static boolean isSelected(JTextField f,String value) {
//    
//if ( !value.matches("[a-zA-Z]+")){
//    f.setBorder(new LineBorder(Color.red,1));
//    f.setText("Only Strings are allowed");
//    
// } 
//   return false;
//}
   //to validate password entered
   
   //public static void isPassword(JTextField f,String value) 
//   {
//       Scanner in = new Scanner(System.in);
//    System.out.print("Please enter a given  password : ");
//    String passwordhere = in.nextLine();
//    System.out.print("Please re-enter the password to confirm : ");
//    String confirmhere = in.nextLine();
//
//    List<String> errorList = new ArrayList<String>();
//
//    while (!isValid(passwordhere, confirmhere, errorList)) {
//        System.out.println("The password entered here  is invalid");
//        for (String error : errorList) {
//            System.out.println(error);
//        }
//
//        System.out.print("Please enter a given  password : ");
//        passwordhere = in.nextLine();
//        System.out.print("Please re-enter the password to confirm : ");
//        confirmhere = in.nextLine();
//    }
//    System.out.println("your password is: " + passwordhere);
//
//}

public static boolean passwordVarification(JTextField f, String passwordhere, String confirmhere, List<String> errorList) {

    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
    errorList.clear();

    boolean flag=true;

    if (!passwordhere.equals(confirmhere)) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("password and confirm password does not match");
        flag=false;
    }
    if (passwordhere.length() < 8) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("Password lenght must have alleast 8 character !!");
        flag=false;
    }
    if (!specailCharPatten.matcher(passwordhere).find()) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("Password must have atleast one specail character !!");
        flag=false;
    }
    if (!UpperCasePatten.matcher(passwordhere).find()) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("Password must have atleast one uppercase character !!");
        flag=false;
    }
    if (!lowerCasePatten.matcher(passwordhere).find()) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("Password must have atleast one lowercase character !!");
        flag=false;
    }
    if (!digitCasePatten.matcher(passwordhere).find()) {
        f.setBorder(new LineBorder(Color.red,1));
        //errorList.add("Password must have atleast one digit character !!");
        flag=false;
    }
    f.setText("password Mismach");
    return flag;
    

}
   
//TO check field should not be null

   

}


