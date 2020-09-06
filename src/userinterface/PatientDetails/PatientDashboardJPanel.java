/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.PatientDetails;

import Business.EcoSystem;
import Business.Employee.Address;
import Business.Employee.Doctor;
import Business.Employee.Employee;
import Business.Employee.Nurse;
import Business.Employee.PremiumPatient;
import Business.Employee.StandardPatient;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Prescription.Prescription;
import Business.UserAccount.UserAccount;
import Business.Validations.ValidationResult;
import Business.WorkQueue.WorkRequest;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nupur
 */
public class PatientDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form PatientJPanel
     */
    private StandardPatient standardPatient;
    private PremiumPatient premiumPatient;
    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;
    private EcoSystem business;
    private ImageIcon ii;

    public PatientDashboardJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;
        this.business = business;
        groupButtonSelection();

        if (account.getEmployee() instanceof StandardPatient) {
            this.standardPatient = (StandardPatient) account.getEmployee();
        } else if (account.getEmployee() instanceof PremiumPatient) {
            this.premiumPatient = (PremiumPatient) account.getEmployee();
        }

        this.ii = account.getEmployee().getImageIcon();
        displayCompletePatient();
        editbutton.setEnabled(true);
        savebutton.setEnabled(false);
        populateCountrycombobox();
        populateAppointmentCombobox();
        displayPrescriptions();
        setButtons();
    }

    
     public void setButtons()
    {
        jButton17.setEnabled(false);
        jButton2.setEnabled(false);
        jButton23.setEnabled(false);
        jButton8.setEnabled(false);
        enametxtfeild.setEnabled(false);
        phonetxtfeild.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEditable(false);
        enameTextField2.setEnabled(false);
        phoneTextField.setEnabled(false);
        jTextField2.setEnabled(false);
        addressjTextField.setEnabled(false);
    }
    
    public void displayPrescriptions()
    {
    if (standardPatient != null && account.getEmployee() instanceof StandardPatient) {
            DefaultTableModel model = (DefaultTableModel) prescriptionJTable.getModel();
            model.setRowCount(0);
            if (standardPatient.getPrescriptions() != null) {
                for (Prescription p : standardPatient.getPrescriptions()) {
                    Object row[] = new Object[7];
                    row[0] = p.getDate();
                    row[1] = p.getPrescriptionNum();
                    row[2] = p.getPrescribedBy();
                    if (p.getMedicineName() != null) {
                        String medName = "";
                        for (String s : p.getMedicineName()) {
                            medName += s + ",";
                        }
                        row[3] = medName;
                    }

                    ((DefaultTableModel) prescriptionJTable.getModel()).addRow(row);
                }
            }

        } else if (premiumPatient != null && account.getEmployee() instanceof PremiumPatient) {
            DefaultTableModel model = (DefaultTableModel) prescriptionJTable.getModel();
            model.setRowCount(0);
            if (premiumPatient.getPrescriptions() != null) {
                for (Prescription p : premiumPatient.getPrescriptions()) {
                    Object row[] = new Object[7];
                    row[0] = p.getDate();
                    row[1] = p.getPrescriptionNum();
                    row[2] = p.getPrescribedBy();
                    if (p.getMedicineName() != null) {
                          String medName = "";
                          for (String s : p.getMedicineName()) {
                              medName += s + ",";
                          }
                          row[3] = medName;
                      }
                    ((DefaultTableModel) prescriptionJTable.getModel()).addRow(row);
                }
            }
        }
    }
    
    
    public void displayCompletePatient() {
        if (account.getEmployee() instanceof StandardPatient) {
            mmidTextField.setText(this.standardPatient.getMmid());
            MMIDTXTFEILD.setText(this.standardPatient.getMmid());
            displayDashBoard();
            displayProfile();
            adjustTabPane();
        } else if (account.getEmployee() instanceof PremiumPatient) {
            mmidTextField.setText(this.premiumPatient.getMmid());
            MMIDTXTFEILD.setText(this.premiumPatient.getMmid());
            displayDashBoard();
            displayProfile();
        }
    }

    public void populateCountrycombobox() {
        countrycombobox.addItem("Select");
        countrycombobox.addItem("India");
        countrycombobox.addItem("United States of America");
        countrycombobox.setSelectedItem("Select");
    }

    public void adjustTabPane() {
        for(int i = 0; i <emergencyContactTabbedPane.getComponents().length; i++) {
            if(emergencyContactTabbedPane.getTitleAt(i).equalsIgnoreCase("APPOINTMENT")) {
                System.out.println(emergencyContactTabbedPane.getTitleAt(i));
                emergencyContactTabbedPane.setEnabledAt(i,false);
            }
        }
        
    }

    public void populateAppointmentCombobox() {
        
//        ArrayList<Doctor> doctorList = new ArrayList();
//        ArrayList<Nurse> nurseList = new ArrayList();
        for (Network network : business.getNetworkList()) {
                ArrayList<Enterprise> user = network.getEnterpriseDirectory().getSpecificEnterpriseList(Enterprise.EnterpriseType.Hospital);
                for (Enterprise u : user) {
                    ArrayList<Organization> org = (u.getOrganizationDirectory().getOrganizationList());
                    for (Organization o : org) {
                        for (UserAccount ua: o.getUserAccountDirectory().getUserAccountList()) {
                            
                            if(ua.getEmployee() instanceof Doctor){
                                jComboBox1.addItem(ua.getEmployee());
                                
//                                 System.out.println("Doctor"+ua.getEmployee().getName());
//                                 doctorList.add((Doctor) ua.getEmployee());
                            }
                            else if(ua.getEmployee() instanceof Nurse){
                                jComboBox6.addItem(ua.getEmployee());
//                                System.out.println("Nurse"+ua.getEmployee().getName());
//                                nurseList.add((Nurse) ua.getEmployee());
                            }
                            
                            //jComboBox1.setModel(new DefaultComboBoxModel(doctorList.toArray()));
                            //jComboBox6.setModel(new DefaultComboBoxModel(nurseList.toArray()));
                        }
                    }
                }

            }
    }
    


    public void displayDashBoard() {
        if (standardPatient != null && account.getEmployee() instanceof StandardPatient) {
            if (standardPatient.getFname() != null && standardPatient.getMiddleName() != null && standardPatient.getLname() != null) {
                namejTextField.setText(standardPatient.getFname() + " " + standardPatient.getMiddleName() + " " + standardPatient.getLname());
            }
            phonenumbertxtfeild.setText(standardPatient.getPhoneNo());
            insuranceDTxtField.setText(standardPatient.getInsuranceNo());
            //if u have any method
//            ageTextField.setText(standardPatient.getDob())
            genderDTxtField.setText(standardPatient.getGender());
        } else if (premiumPatient != null && account.getEmployee() instanceof PremiumPatient) {
            if (premiumPatient.getFname() != null && premiumPatient.getMiddleName() != null && premiumPatient.getLname() != null) {
                namejTextField.setText(premiumPatient.getFname() + " " + premiumPatient.getMiddleName() + " " + premiumPatient.getLname());
            }
//            namejTextField.setText(premiumPatient.getFname() + " " + premiumPatient.getMiddleName() + " " + premiumPatient.getLname());
            phonenumbertxtfeild.setText(premiumPatient.getPhoneNo());
            insuranceDTxtField.setText(premiumPatient.getInsuranceNo());
            genderDTxtField.setText(premiumPatient.getGender());
        }

    }

    public void displayProfile() {
        if (standardPatient != null) {
            FirstNamejTextField.setText(standardPatient.getFname() != null ? standardPatient.getFname() : "");
            Middlenametxtfeild.setText(standardPatient.getMiddleName() != null ? standardPatient.getMiddleName() : "");
            Lastnametxtfeild.setText(standardPatient.getLname() != null ? standardPatient.getLname() : "");

            if (standardPatient.getSalutation() != null) {
                for (int i = 0; i < SALUTAIONCOMBOBOX.getModel().getSize(); i++) {
                    if (standardPatient.getSalutation().equals(SALUTAIONCOMBOBOX.getItemAt(i).toString())) {
                        SALUTAIONCOMBOBOX.setSelectedIndex(i);
                        break;
                    }
                }
            }

            // dobDateChooser.setDate(standardPatient.getDob());
            heighttxtfeild.setText(String.valueOf(standardPatient.getHeight()));
            weighttxtfeild.setText(String.valueOf(standardPatient.getWeight()));

            if (standardPatient.getBloodGroup() != null) {
                for (int i = 0; i < bloodgroupcombox.getModel().getSize(); i++) {
                    if (standardPatient.getBloodGroup().equals(bloodgroupcombox.getItemAt(i).toString())) {
                        bloodgroupcombox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            BMItxtfeild.setText(String.valueOf(standardPatient.getBmi()));

            if (standardPatient.getSmoking() != null) {
                for (int i = 0; i < smokingcombobox.getModel().getSize(); i++) {
                    if (standardPatient.getSmoking().equals(smokingcombobox.getItemAt(i).toString())) {
                        smokingcombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            if (standardPatient.getDrinking() != null) {
                for (int i = 0; i < drinkingcombobox1.getModel().getSize(); i++) {
                    if (standardPatient.getDrinking().equals(drinkingcombobox1.getItemAt(i).toString())) {
                        drinkingcombobox1.setSelectedIndex(i);
                        break;
                    }
                }
            }

            Address address = standardPatient.getAddress();
            apartmentNoTextField.setText(address.getApartmentNo());
            streetnotxtfeild.setText(address.getStreetNo());
            streetNameTextField.setText(address.getStreetName());
            cityjTextField.setText(address.getCity());

            if (address.getState() != null) {
                for (int i = 0; i < statecombobox.getModel().getSize(); i++) {
                    if (address.getState().equals(statecombobox.getItemAt(i).toString())) {
                        statecombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            zipcodetxtfeild.setText(address.getZipCode());
            if (address.getCountry() != null) {
                for (int i = 0; i < countrycombobox.getModel().getSize(); i++) {
                    if (address.getCountry().equals(countrycombobox.getItemAt(i).toString())) {
                        countrycombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            MOTHERNAMETXTFEILD.setText(standardPatient.getMotherName());
            FATHERNAMEjTextField1.setText(standardPatient.getFatherName());

            if (standardPatient.getRace() != null) {
                for (int i = 0; i < racecombobox.getModel().getSize(); i++) {
                    if (standardPatient.getRace().equals(racecombobox.getItemAt(i).toString())) {
                        racecombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            MMIDTXTFEILD.setText(standardPatient.getMmid());
            insurancenumebr.setText(standardPatient.getInsuranceNo());
            Emailidtxtfeild.setText(standardPatient.getEmailId());
            SSNTXTFEILD.setText(standardPatient.getSsn());
            Phonetxtfeild.setText(standardPatient.getPhoneNo());

            if (standardPatient.getLanguagesSpoken() != null) {
                for (int i = 0; i < languagespokencombobox.getModel().getSize(); i++) {
                    if (standardPatient.getLanguagesSpoken().equals(languagespokencombobox.getItemAt(i).toString())) {
                        languagespokencombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            if (standardPatient.getOccupation() != null) {
                for (int i = 0; i < occupationcombobox.getModel().getSize(); i++) {
                    if (standardPatient.getOccupation().equals(occupationcombobox.getItemAt(i).toString())) {
                        occupationcombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }
            standardPatient.setImageIcon(ii);
        } else if (premiumPatient != null) {
            FirstNamejTextField.setText(premiumPatient.getFname() != null ? premiumPatient.getFname() : "");
            Middlenametxtfeild.setText(premiumPatient.getMiddleName() != null ? premiumPatient.getMiddleName() : "");
            Lastnametxtfeild.setText(premiumPatient.getLname() != null ? premiumPatient.getLname() : "");

            if (premiumPatient.getSalutation() != null) {
                for (int i = 0; i < SALUTAIONCOMBOBOX.getModel().getSize(); i++) {
                    if (premiumPatient.getSalutation().equals(SALUTAIONCOMBOBOX.getItemAt(i).toString())) {
                        SALUTAIONCOMBOBOX.setSelectedIndex(i);
                        break;
                    }
                }
            }

            //dobDateChooser.setDate(premiumPatient.getDob());
            heighttxtfeild.setText(String.valueOf(premiumPatient.getHeight()));
            weighttxtfeild.setText(String.valueOf(premiumPatient.getWeight()));

            if (premiumPatient.getBloodGroup() != null) {
                for (int i = 0; i < bloodgroupcombox.getModel().getSize(); i++) {
                    if (premiumPatient.getBloodGroup().equals(bloodgroupcombox.getItemAt(i).toString())) {
                        bloodgroupcombox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            BMItxtfeild.setText(String.valueOf(premiumPatient.getBmi()));

            if (premiumPatient.getSmoking() != null) {
                for (int i = 0; i < smokingcombobox.getModel().getSize(); i++) {
                    if (premiumPatient.getSmoking().equals(smokingcombobox.getItemAt(i).toString())) {
                        smokingcombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            if (premiumPatient.getDrinking() != null) {
                for (int i = 0; i < drinkingcombobox1.getModel().getSize(); i++) {
                    if (premiumPatient.getDrinking().equals(drinkingcombobox1.getItemAt(i).toString())) {
                        drinkingcombobox1.setSelectedIndex(i);
                        break;
                    }
                }
            }

            Address address = premiumPatient.getAddress();
            apartmentNoTextField.setText(address.getApartmentNo());
            streetnotxtfeild.setText(address.getStreetNo());
            streetNameTextField.setText(address.getStreetName());
            cityjTextField.setText(address.getCity());

            if (address.getState() != null) {
                for (int i = 0; i < statecombobox.getModel().getSize(); i++) {
                    if (address.getState().equals(statecombobox.getItemAt(i).toString())) {
                        statecombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            zipcodetxtfeild.setText(address.getZipCode());
            if (address.getCountry() != null) {
                for (int i = 0; i < countrycombobox.getModel().getSize(); i++) {
                    if (address.getCountry().equals(countrycombobox.getItemAt(i).toString())) {
                        countrycombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            MOTHERNAMETXTFEILD.setText(premiumPatient.getMotherName());
            FATHERNAMEjTextField1.setText(premiumPatient.getFatherName());

            if (premiumPatient.getRace() != null) {
                for (int i = 0; i < racecombobox.getModel().getSize(); i++) {
                    if (premiumPatient.getRace().equals(racecombobox.getItemAt(i).toString())) {
                        racecombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            MMIDTXTFEILD.setText(premiumPatient.getMmid());
            insurancenumebr.setText(premiumPatient.getInsuranceNo());
            Emailidtxtfeild.setText(premiumPatient.getEmailId());
            SSNTXTFEILD.setText(premiumPatient.getSsn());
            Phonetxtfeild.setText(premiumPatient.getPhoneNo());

            if (premiumPatient.getLanguagesSpoken() != null) {
                for (int i = 0; i < languagespokencombobox.getModel().getSize(); i++) {
                    if (premiumPatient.getLanguagesSpoken().equals(languagespokencombobox.getItemAt(i).toString())) {
                        languagespokencombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            if (premiumPatient.getOccupation() != null) {
                for (int i = 0; i < occupationcombobox.getModel().getSize(); i++) {
                    if (premiumPatient.getOccupation().equals(occupationcombobox.getItemAt(i).toString())) {
                        occupationcombobox.setSelectedIndex(i);
                        break;
                    }
                }
            }
            premiumPatient.setImageIcon(ii);
        }

    }

    public boolean validateData() //        isStringValidatios()
    {

        boolean b1 = ValidationResult.isnotnull(FirstNamejTextField, FirstNamejTextField.getText());
        boolean b2 = ValidationResult.isnotnull(Lastnametxtfeild, Lastnametxtfeild.getText());
        boolean b3 = ValidationResult.isemail(Emailidtxtfeild, Emailidtxtfeild.getText());
        boolean b4 = ValidationResult.isnotnull(FATHERNAMEjTextField1, FATHERNAMEjTextField1.getText());
        //boolean b5 = ValidationResult.isnotnull(enametxtfeild,enametxtfeild.getText());
//        boolean b6 = ValidationResult.isnotnull(enametxtfeild,enametxtfeild.getText());
//        boolean b7 = ValidationResult.isnotnull(enameTextField2,enameTextField2.getText());
        boolean b8 = ValidationResult.isnotnull(citytxtfeild, citytxtfeild.getText());
        boolean b9 = ValidationResult.isFloat(heighttxtfeild, heighttxtfeild.getText());
        boolean b10 = ValidationResult.isFloat(weighttxtfeild, weighttxtfeild.getText());
        boolean b11 = ValidationResult.isLong(SSNTXTFEILD, SSNTXTFEILD.getText());
        boolean b12 = ValidationResult.isLong(Phonetxtfeild, Phonetxtfeild.getText());
        boolean b13 = ValidationResult.isLong(zipcodetxtfeild, zipcodetxtfeild.getText());

        if (b1 && b2 && b3 && b4 && b8 && b9 && b10 && b11 && b12 && b13) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        emergencyContactTabbedPane = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        genderDTxtField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        insuranceDTxtField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mmidTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ageTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        duesTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        phonejTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        namejTextField = new javax.swing.JTextField();
        phonenumbertxtfeild = new javax.swing.JTextField();
        patientBackjButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        documentTypeComboBox2 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        AllergyTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        documentTypeComboBox = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        documentJtable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        SUCIDE = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        SALUTAIONCOMBOBOX3 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX4 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX5 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX6 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX7 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX8 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX9 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX10 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX12 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX13 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX14 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX15 = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel62 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        comapnyNamejTextField = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        PhoneTextField = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        streetjTextField = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        cityjTextField = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        statejTextField = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        countryjTextField = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        faxtextField = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        zipjTextField = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        contactPersonjTextField = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        cellTextField = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        emailjTextField = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        MEDICALHSTRYTABLE = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        medicationJTable = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        prescriptionJTable = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        surgeryJTable = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        VitalSignsTable = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        SALUTAIONCOMBOBOX = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        racecombobox = new javax.swing.JComboBox();
        Lastnametxtfeild = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Middlenametxtfeild = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        MOTHERNAMETXTFEILD = new javax.swing.JTextField();
        malejRadioButton1 = new javax.swing.JRadioButton();
        femalejRadioButton = new javax.swing.JRadioButton();
        othersjRadioButton3 = new javax.swing.JRadioButton();
        MMIDTXTFEILD = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        SSNTXTFEILD = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        yesjRadioButton4 = new javax.swing.JRadioButton();
        nojRadioButton5 = new javax.swing.JRadioButton();
        othersjRadioButton6 = new javax.swing.JRadioButton();
        insurancenumebr = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        weighttxtfeild = new javax.swing.JTextField();
        heighttxtfeild = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        bloodgroupcombox = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        Emailidtxtfeild = new javax.swing.JTextField();
        Phonetxtfeild = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        BMItxtfeild = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        smokingcombobox = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        languagespokencombobox = new javax.swing.JComboBox();
        occupationcombobox = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        statecombobox = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        streetNameTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        apartmentNoTextField = new javax.swing.JTextField();
        countrycombobox = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        streetnotxtfeild = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        citytxtfeild = new javax.swing.JTextField();
        savebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        zipcodetxtfeild = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        FirstNamejTextField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        FATHERNAMEjTextField1 = new javax.swing.JTextField();
        drinkingcombobox1 = new javax.swing.JComboBox();
        jButton24 = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        imagePathJLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        enametxtfeild = new javax.swing.JTextField();
        SALUTAIONCOMBOBOX1 = new javax.swing.JComboBox();
        SALUTAIONCOMBOBOX2 = new javax.swing.JComboBox();
        phonetxtfeild = new javax.swing.JTextField();
        addressjTextField = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        enameTextField2 = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton25 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton27 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jComboBox6 = new javax.swing.JComboBox();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton33 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton34 = new javax.swing.JButton();

        jPanel14.setBackground(new java.awt.Color(99, 136, 140));

        jLabel6.setText("Gender :");

        jLabel8.setText("Phone :");

        jLabel10.setText("Insurance ID:");

        insuranceDTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insuranceDTxtFieldActionPerformed(evt);
            }
        });

        jLabel9.setText("MMID :");

        jLabel7.setText("Age :");

        duesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Medicines", "Lab Tests", "Virtual Signs"
            }
        ));
        jScrollPane4.setViewportView(duesTable);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Appointments", "Tests", "Vital Signs"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Visited By", "Last Visited"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        jButton4.setText("Health Goals");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Update Virtual Signs");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("NAME:");

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel2.setText("DASH BOARD");

        phonejTextArea.setColumns(20);
        phonejTextArea.setRows(5);
        jScrollPane1.setViewportView(phonejTextArea);

        jLabel3.setText("TIP OF THE DAY");

        jLabel4.setText("UPCOMING APPOINTMENTS");

        jLabel5.setText("DUES");

        jLabel11.setText("PROFILE VISITS");

        jLabel12.setText("IMAGE");

        patientBackjButton.setText(">>Back");
        patientBackjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientBackjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel9))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mmidTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(ageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(namejTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 428, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(insuranceDTxtField)
                                    .addComponent(genderDTxtField)
                                    .addComponent(phonenumbertxtfeild, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jButton4)
                                .addGap(77, 77, 77)
                                .addComponent(jButton5))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 561, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(patientBackjButton)
                .addGap(112, 112, 112))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(namejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phonenumbertxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mmidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(insuranceDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(genderDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(2, 2, 2)
                .addComponent(patientBackjButton)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Dashboard", jPanel14);

        jPanel2.setBackground(new java.awt.Color(99, 136, 140));

        jLabel46.setText("ALLERGY TYPE");

        documentTypeComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "**SELECT**", "FOOD ALLERGY", "DRUG ALLERGY", "CONTACT DERMATITIES", "LATEX ALLERGY", "ANIMAL ALLERGY", "OTHER", " ", " ", " ", " ", " " }));
        documentTypeComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentTypeComboBox2ActionPerformed(evt);
            }
        });

        AllergyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ALLERGIC TO", "OBSERVED BY", "FIRST OBSERVED", "DESCRIPTION"
            }
        ));
        jScrollPane2.setViewportView(AllergyTable);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel46)
                        .addGap(79, 79, 79)
                        .addComponent(documentTypeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jButton1)
                        .addGap(54, 54, 54)
                        .addComponent(jButton2)))
                .addGap(0, 587, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(documentTypeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(545, 545, 545))
        );

        emergencyContactTabbedPane.addTab("Allergies", jPanel2);

        jPanel3.setBackground(new java.awt.Color(99, 136, 140));

        jLabel47.setText("Document Type ");

        documentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "**SELECT**", "Government ID", "Prescription", "Lap Report" }));
        documentTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentTypeComboBoxActionPerformed(evt);
            }
        });

        documentJtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Document Type", "Dr. Name", "Uploaded By", "Uploaded On", "Verified By", "Comments", "View"
            }
        ));
        jScrollPane3.setViewportView(documentJtable);

        jButton3.setText("Add");

        jButton6.setText("Save");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(483, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(64, 64, 64)
                .addComponent(documentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(jButton3)
                .addGap(50, 50, 50)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(documentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton6)))
                .addContainerGap(607, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Documents", jPanel3);

        jPanel5.setBackground(new java.awt.Color(99, 136, 140));

        jLabel61.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel61.setText("FAMILY HISTORY ");

        jCheckBox1.setText("DIABETICS");

        jCheckBox2.setText("HIGH BLOOD PRESSURE");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("CHOLESTROL");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("STROKE");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("PSYCHIARTIC ILLNESS");

        SUCIDE.setText("SUCIDE");

        jCheckBox7.setText("ALCOHAL / DRUG PROBLEMS");

        jCheckBox8.setText("HEART ATTACK BEFORE 55");

        jCheckBox9.setText("EPILEPSY ");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jCheckBox10.setText("BLOOD CLOT DISORDER");
        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });

        jCheckBox11.setText("GLAUCOMA");
        jCheckBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox11ActionPerformed(evt);
            }
        });

        jCheckBox12.setText("CANCER");

        SALUTAIONCOMBOBOX3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX3ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX4ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX5ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX6ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX7ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX8ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX9ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX10ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX12ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX13ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX14ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RELATIONSHIP", "MOTHER", "FATHER", "OTHER", " " }));
        SALUTAIONCOMBOBOX15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX15ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane7.setViewportView(jTextArea2);

        jLabel62.setText("DESCRIPTION :");

        jButton9.setText("EDIT");

        jButton10.setText("SAVE");

        jLabel63.setText("OTHER OBSERVATIONS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel62)
                                        .addGap(50, 50, 50))
                                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox4)
                                            .addComponent(jCheckBox8)
                                            .addComponent(jCheckBox10)
                                            .addComponent(jCheckBox2))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SALUTAIONCOMBOBOX3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox1)
                                            .addComponent(jCheckBox3)
                                            .addComponent(jCheckBox11)
                                            .addComponent(jCheckBox9))
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SALUTAIONCOMBOBOX8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SALUTAIONCOMBOBOX10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jLabel61)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SALUTAIONCOMBOBOX13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jCheckBox12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SALUTAIONCOMBOBOX12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SALUTAIONCOMBOBOX14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(SUCIDE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SALUTAIONCOMBOBOX15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)
                        .addGap(53, 53, 53)
                        .addComponent(jButton10)
                        .addGap(79, 79, 79)))
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(SALUTAIONCOMBOBOX7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(SALUTAIONCOMBOBOX8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(SALUTAIONCOMBOBOX3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4)
                            .addComponent(SALUTAIONCOMBOBOX5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SALUTAIONCOMBOBOX12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox7)
                            .addComponent(SALUTAIONCOMBOBOX13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox8)
                                .addComponent(SALUTAIONCOMBOBOX4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox11))
                            .addComponent(SALUTAIONCOMBOBOX9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox9)
                            .addComponent(SALUTAIONCOMBOBOX6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SALUTAIONCOMBOBOX10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox5)
                            .addComponent(SALUTAIONCOMBOBOX14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SUCIDE)
                                    .addComponent(jCheckBox10)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(SALUTAIONCOMBOBOX15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton10)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap(436, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Family History", jPanel5);

        jPanel6.setBackground(new java.awt.Color(99, 136, 140));

        jLabel64.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel64.setText("Health Goals");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Health Measure", "My Result", "My Health Aciement goals ", "Suggested By", "Comments"
            }
        ));
        jScrollPane8.setViewportView(jTable1);

        jButton11.setText("Add");

        jButton12.setText("Save");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(54, 54, 54)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(474, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel64)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap(564, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Goals", jPanel6);

        jPanel7.setBackground(new java.awt.Color(99, 136, 140));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Vaccine", "Vacination Date", "Dr. Name", "Dr. Designation", "Manufacturer of Vaccinel", "Bach No. of Vacine", "Vacination valid periodl"
            }
        ));
        jScrollPane9.setViewportView(jTable2);

        jButton13.setText("Add");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(617, 617, 617)
                        .addComponent(jButton13)
                        .addGap(296, 296, 296)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton13)
                .addGap(599, 599, 599))
        );

        emergencyContactTabbedPane.addTab("Immunisation", jPanel7);

        jPanel8.setBackground(new java.awt.Color(99, 136, 140));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MFG", "Product", "Model No.", "Serial No.", "Implant Date", "Dr. Name", "Dr. Designation", "Lab Report", "Designation"
            }
        ));
        jScrollPane10.setViewportView(jTable3);

        jButton14.setText("Add");

        jButton15.setText("Edit");

        jLabel65.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel65.setText("Implanted Devices");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel65)
                .addContainerGap(772, Short.MAX_VALUE))
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(58, 58, 58)
                .addComponent(jButton15)
                .addGap(238, 238, 238))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15))
                .addContainerGap(589, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Implanted Devices", jPanel8);

        jPanel9.setBackground(new java.awt.Color(99, 136, 140));

        jLabel73.setText("Company Name :");

        comapnyNamejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comapnyNamejTextFieldActionPerformed(evt);
            }
        });

        jLabel74.setText("Phone No: ");

        jLabel75.setText("Street :");

        jLabel76.setText("Phone 2 :");

        jLabel77.setText("City :");

        jLabel78.setText("State :");

        jLabel79.setText("Country :");

        jLabel80.setText("Fax :");

        jLabel81.setText("Zip :");

        jLabel82.setText("Email :");

        jLabel83.setText("Contact Person :");

        jLabel84.setText("Cell :");

        jLabel85.setText("Email :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel74)
                                .addComponent(jLabel75))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addGap(73, 73, 73)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(statejTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(PhoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(streetjTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(162, 162, 162)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77)
                                    .addComponent(jLabel79))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(cityjTextField)
                                    .addComponent(countryjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel81)
                                    .addComponent(jLabel82))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(faxtextField)
                                    .addComponent(zipjTextField)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                            .addComponent(comapnyNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel83)
                        .addGap(29, 29, 29)
                        .addComponent(contactPersonjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(cellTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel85)
                        .addGap(28, 28, 28)
                        .addComponent(emailjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(comapnyNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(PhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80)
                    .addComponent(faxtextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(streetjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77)
                        .addComponent(cityjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81)
                        .addComponent(zipjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addComponent(statejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countryjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(contactPersonjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84)
                    .addComponent(cellTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85)
                    .addComponent(emailjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(630, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Insurance", jPanel9);

        jPanel10.setBackground(new java.awt.Color(99, 136, 140));

        jLabel66.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel66.setText("MEDICAL HISTORY :");

        MEDICALHSTRYTABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CONDITION", "STATUS", "PRESCIRPTION ID", "OBSERVED DATE ", "RESOLVED DATE ", "OBSERVED BY"
            }
        ));
        jScrollPane11.setViewportView(MEDICALHSTRYTABLE);

        jButton16.setText("Add");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Save");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel66))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jButton16)
                            .addGap(43, 43, 43)
                            .addComponent(jButton17))
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel66)
                .addGap(76, 76, 76)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton17))
                .addGap(648, 648, 648))
        );

        emergencyContactTabbedPane.addTab("Medical History", jPanel10);

        jPanel11.setBackground(new java.awt.Color(99, 136, 140));

        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel67.setText("MEDICATIONS");

        medicationJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Dosage", "WHAT for", "Frequency", "Remaining"
            }
        ));
        jScrollPane12.setViewportView(medicationJTable);

        jButton19.setText("Add");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addGap(0, 489, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jButton19)
                .addGap(31, 31, 31)
                .addComponent(jButton18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel67)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18)
                            .addComponent(jButton19))))
                .addContainerGap(638, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Medications", jPanel11);

        jPanel12.setBackground(new java.awt.Color(99, 136, 140));

        jLabel68.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel68.setText("REPORTS");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Prescription Number", "Test Name", "Suggested By", "Issued Date", "Description", "VIew"
            }
        ));
        jScrollPane13.setViewportView(jTable7);

        jButton20.setText("Add");

        jButton21.setText("Save");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addContainerGap(936, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton20)
                .addGap(49, 49, 49)
                .addComponent(jButton21)
                .addGap(145, 145, 145))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel68)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addContainerGap(536, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Lab Reports", jPanel12);

        jPanel15.setBackground(new java.awt.Color(99, 136, 140));

        jLabel70.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel70.setText("PRESCRIPTIONS");

        prescriptionJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Issue Date", "Prescription No.", "Diagnosed By", "Medical Condition", "Report By ", "Order Status", "View"
            }
        ));
        jScrollPane15.setViewportView(prescriptionJTable);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel70)
                .addContainerGap(801, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(849, 849, 849))
        );

        emergencyContactTabbedPane.addTab("Prescriptions", jPanel15);

        jPanel16.setBackground(new java.awt.Color(99, 136, 140));

        jLabel71.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel71.setText("Surgeries Done");

        surgeryJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "PRE-operative Diagnosis", "POST-Operative Diagnosis", "Procedure", "Surgeon", "Assistant", "Description"
            }
        ));
        jScrollPane16.setViewportView(surgeryJTable);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane16))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel71)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(653, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Surgeries", jPanel16);

        jPanel17.setBackground(new java.awt.Color(99, 136, 140));

        jLabel72.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel72.setText("VITAL SIGNS:");

        VitalSignsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Weight", "Body Temperature", "Blood Pressure", "Pulse", "Initials", "Last Updated", "Updated by "
            }
        ));
        jScrollPane17.setViewportView(VitalSignsTable);

        jButton22.setText("Add");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Save");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel72))
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jButton22)
                        .addGap(68, 68, 68)
                        .addComponent(jButton23)))
                .addContainerGap(480, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel72)
                .addGap(74, 74, 74)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton23))
                .addContainerGap(560, Short.MAX_VALUE))
        );

        emergencyContactTabbedPane.addTab("Vital Signs", jPanel17);

        jPanel1.setBackground(new java.awt.Color(99, 136, 140));

        jLabel13.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 24)); // NOI18N
        jLabel13.setText("PROFILE INFORMATION:");

        jLabel14.setText("SALUTATION :");

        SALUTAIONCOMBOBOX.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "Mr.", "Ms.", "Mrs.", "Dr.", "Sr.", "Jr.", "None" }));
        SALUTAIONCOMBOBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOXActionPerformed(evt);
            }
        });

        jLabel15.setText("RACE");

        racecombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "American", "Asian", "Black or African American", "Native Hawaiian ", "White", "Hispanic", "Lation", "Other", " " }));

        jLabel16.setText("LAST NAME *");

        jLabel17.setText("GENDER *");

        jLabel18.setText("MOTHER NAME *");

        jLabel19.setText("MIDDLE NAME *");

        malejRadioButton1.setText("MALE");
        malejRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                malejRadioButton1ActionPerformed(evt);
            }
        });

        femalejRadioButton.setText("FEMALE");

        othersjRadioButton3.setText("OTHERS");

        jLabel20.setText("MMID*");

        jLabel21.setText("D.O.B *");

        jLabel23.setText("SSN *");

        jLabel24.setText("INSURANCE APPLICABLE *");

        yesjRadioButton4.setText("yes");

        nojRadioButton5.setText("No");

        othersjRadioButton6.setText("OTHERS");

        insurancenumebr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insurancenumebrActionPerformed(evt);
            }
        });

        jLabel25.setText("INSURANCE NUMBER ");

        jLabel26.setText("HEIGHT *");

        jLabel27.setText("WEIGHT *");

        jLabel28.setText("BLOOD GROUP *");

        bloodgroupcombox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O-positive", "O-negative ", "A-positive", "A-negative", "B-positive", "B-negative", "AB-positive", "AB-negative " }));
        bloodgroupcombox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodgroupcomboxActionPerformed(evt);
            }
        });

        jLabel29.setText("EMAIL ID *");

        Phonetxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhonetxtfeildActionPerformed(evt);
            }
        });

        jLabel30.setText("PHONE NUMBER *");

        jLabel31.setText("BMI");

        jLabel32.setText("SMOKING");

        smokingcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "Occasionally", "Regular Smoker ", "HEavy Smoker ", " " }));

        jLabel33.setText("LANGUAGES SPOKEN *");

        languagespokencombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "Afrikaans", "Albanian", "Amharic", "Arabic (Egyptian Spoken)", "Aramaic", "Armenian", "Assamese", "Aymara", "Azerbaijani", "Balochi", "Bamanankan", "Bashkort (Bashkir)", "Basque", "Belarusan", "Bengali", "Bhojpuri", "Bislama", "Bosnian", "Brahui", "Bulgarian", "Burmese", "Cantonese", "Catalan", "Cebuano", "Chechen", "Cherokee", "Croatian", "Czech", "Dakota", "Danish", "Dari", "Dholuo", "DutchEnglish", "Esperanto", "Estonian", "Éwé", "Finnish", "French", "Georgian", "German", "Gikuyu", "Greek", "Guarani", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hawaiian Creole", "Hebrew", "Hiligaynon", "Hindi", "Hungarian", "Icelandic", "Igbo", "Ilocano", "Indonesian (Bahasa Indonesia)", "Inuit/Inupiaq", "Irish Gaelic", "Italian", "Japanese", "Jarai", "Javanese", "K’iche’", "Kabyle", "Kannada", "Kashmiri", "Kazakh", "Khmer", "KhoekhoeKorean", "Kurdish", "Kyrgyz", "Lao", "Latin", "Latvian", "Lingala", "Lithuanian", "Macedonian", "Maithili", "Malagasy", "Malay (Bahasa Melayu)", "Malayalam", "Mandarin (Chinese)", "Marathi", "Mende", "Mongolian", "Nahuatl", "Navajo", "Nepali", "Norwegian", "Ojibwa", "Oriya", "Oromo", "Pashto", "Persian", "Polish", "Portuguese", "Punjabi", "Quechua", "Romani", "Romanian", "Russian", "Rwanda", "Samoan", "Sanskrit", "SerbianShona", "Sindhi", "Sinhala", "Slovak", "Slovene", "Somali", "Spanish", "Swahili", "Swedish", "Tachelhit", "Tagalog", "Tajiki", "Tamil", "Tatar", "Telugu", "Thai", "Tibetic languages", "Tigrigna", "Tok Pisin", "Turkish", "Turkmen", "Ukrainian", "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Warlpiri", "Welsh", "Wolof", "Xhosa", "Yakut", "Yiddish", "Yoruba", "Yucatec", "Zapotec", "Zulu" }));

        occupationcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "STUDENT", "SELF EMPLOYED", "BUSINESS", "DOCTOR", "CHARTED ACCOUNTANT", "OTHER" }));

        jLabel34.setText("OCCUPATION");

        statecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statecomboboxActionPerformed(evt);
            }
        });

        jLabel35.setText("DRINKING");

        jLabel36.setText("Street Name:  *");

        jLabel37.setText("Apartment  No.:");

        apartmentNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apartmentNoTextFieldActionPerformed(evt);
            }
        });

        countrycombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        countrycombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countrycomboboxActionPerformed(evt);
            }
        });
        countrycombobox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                countrycomboboxPropertyChange(evt);
            }
        });

        jLabel38.setText("COUNTRY *");

        jLabel39.setText("STREET NO *");

        jLabel40.setText("STATE *");

        jLabel41.setText("CITY *");

        savebutton.setText("save");
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });

        editbutton.setText("Edit");
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });

        zipcodetxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipcodetxtfeildActionPerformed(evt);
            }
        });

        jLabel42.setText("ZIPCODE *");

        jLabel43.setText("FIRST NAME *");

        jLabel44.setText("FATHER NAME *");

        drinkingcombobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "Ocasinally ", "Heavy Drinker", "Casual Drinker" }));

        jButton24.setText("Upload");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        imageLabel.setText("The image will display here");
        imageLabel.setPreferredSize(new java.awt.Dimension(190, 120));

        imagePathJLabel.setText("Image Path");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel14)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(streetnotxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BMItxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(smokingcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(FirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(111, 111, 111)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel44))
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(MOTHERNAMETXTFEILD, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(FATHERNAMEjTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(racecombobox, javax.swing.GroupLayout.Alignment.LEADING, 0, 185, Short.MAX_VALUE)
                                                .addComponent(MMIDTXTFEILD, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(insurancenumebr, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(malejRadioButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(femalejRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(othersjRadioButton3))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(yesjRadioButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nojRadioButton5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(othersjRadioButton6))))
                                    .addComponent(bloodgroupcombox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(Lastnametxtfeild)
                                                .addComponent(SALUTAIONCOMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Middlenametxtfeild)
                                                .addComponent(zipcodetxtfeild)
                                                .addComponent(drinkingcombobox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(statecombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(streetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(106, 106, 106)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel29)
                                                            .addComponent(jLabel30)
                                                            .addComponent(jLabel33)
                                                            .addComponent(jLabel37)
                                                            .addComponent(jLabel38)
                                                            .addComponent(jLabel41)
                                                            .addComponent(jLabel34))
                                                        .addGap(39, 39, 39))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(editbutton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(savebutton))
                                                            .addComponent(jLabel24))
                                                        .addGap(18, 18, 18)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(Emailidtxtfeild, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(SSNTXTFEILD, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(languagespokencombobox, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                                        .addComponent(Phonetxtfeild, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(apartmentNoTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(countrycombobox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(occupationcombobox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(citytxtfeild, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(weighttxtfeild, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                        .addComponent(heighttxtfeild)))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imagePathJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton24, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(jLabel27)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel18)
                            .addComponent(MOTHERNAMETXTFEILD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Middlenametxtfeild, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(FATHERNAMEjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(Lastnametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(racecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(MMIDTXTFEILD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(SALUTAIONCOMBOBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(insurancenumebr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(femalejRadioButton)
                                        .addComponent(othersjRadioButton3))
                                    .addComponent(jLabel17)
                                    .addComponent(malejRadioButton1))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(heighttxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagePathJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(yesjRadioButton4)
                            .addComponent(nojRadioButton5)
                            .addComponent(othersjRadioButton6))))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(weighttxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(Emailidtxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(bloodgroupcombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SSNTXTFEILD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BMItxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)
                        .addComponent(Phonetxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(smokingcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languagespokencombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel34)
                                    .addComponent(drinkingcombobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)
                            .addComponent(streetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apartmentNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(occupationcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countrycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(streetnotxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(citytxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(zipcodetxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editbutton)
                    .addComponent(savebutton))
                .addGap(75, 75, 75))
        );

        emergencyContactTabbedPane.addTab("Profile", jPanel1);

        jPanel4.setBackground(new java.awt.Color(99, 136, 140));

        jLabel48.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel48.setText("EMERGENCY CONTACT :");

        jLabel49.setText("EMERGENCY CONTACT 1");

        jLabel50.setText("EMERGENCY CONTACT 2");

        jLabel51.setText("NAME :");

        jLabel52.setText("RELATIONSHIP :");

        jLabel53.setText("PHONE :");

        jLabel54.setText("ADDRESS :");

        jLabel55.setText("EMAIL ID :");

        jLabel56.setText("EMAIL ID :");

        jLabel57.setText("PHONE :");

        jLabel58.setText("ADDRESS :");

        jLabel59.setText("NAME :");

        jLabel60.setText("RELATIONSHIP :");

        jButton7.setText("EDIT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("SAVE");

        enametxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enametxtfeildActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-SELECT-", "WIFE", "HUSBAND", "MOTHER", "FATHER", "SIBLING", "OTHER", " " }));
        SALUTAIONCOMBOBOX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX1ActionPerformed(evt);
            }
        });

        SALUTAIONCOMBOBOX2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-SELECT-", "WIFE", "HUSBAND", "FATHER", "MOTHER", "SIBLING", "OTHER" }));
        SALUTAIONCOMBOBOX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALUTAIONCOMBOBOX2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel53)
                            .addComponent(jLabel55)
                            .addComponent(jLabel52)
                            .addComponent(jLabel54))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phonetxtfeild)
                            .addComponent(enametxtfeild)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(SALUTAIONCOMBOBOX1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(enameTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 8, Short.MAX_VALUE))
                                    .addComponent(SALUTAIONCOMBOBOX2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addressjTextField))))
                        .addGap(608, 608, 608))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jButton7)
                .addGap(85, 85, 85)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48)
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(enametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59)
                    .addComponent(enameTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel60)
                    .addComponent(SALUTAIONCOMBOBOX1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SALUTAIONCOMBOBOX2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel57)
                    .addComponent(phonetxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel55))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58)
                        .addComponent(addressjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(441, 441, 441))
        );

        emergencyContactTabbedPane.addTab("EmergenctContact", jPanel4);

        jPanel13.setBackground(new java.awt.Color(99, 136, 140));

        jLabel69.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel69.setText("APPOINTMENTS");

        jLabel22.setText("DOCTOR");

        jButton25.setText("BOOK");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel45.setText("HEALTH ISSUES");

        jLabel86.setText("NURSE");

        jButton26.setText("BOOK");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel87.setText("HEALTH SERVICE REQUIRED");

        jLabel88.setText("PHARMACY");

        jButton27.setText("BOOK");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel89.setText("MEDICINES REQUIRED");

        jLabel90.setText("DIAGNOSTIC");

        jButton28.setText("BOOK");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel91.setText("TESTS REQUIERED");

        jButton29.setText("BOOK");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("BOOK");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "APPOINTMENT PERSON", "APPOINTMENTID", "DATE"
            }
        ));
        jScrollPane14.setViewportView(jTable4);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane18.setViewportView(jTextArea1);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane19.setViewportView(jTextArea3);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane20.setViewportView(jTextArea4);

        jButton33.setText("BOOK");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane21.setViewportView(jTextArea5);

        jButton34.setText("BOOK");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(744, 744, 744)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton26)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton28)
                        .addGap(117, 117, 117))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel69))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel22)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton25)
                            .addComponent(jButton29)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane19)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                        .addGap(180, 180, 180)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87)
                            .addComponent(jLabel90))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton34)
                            .addComponent(jButton33)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane21)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(1300, 1300, 1300))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel69)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(263, 263, 263)
                                        .addComponent(jLabel91))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addComponent(jButton33)
                                        .addGap(61, 61, 61)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jButton34))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel86)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel87)
                                        .addGap(136, 136, 136)
                                        .addComponent(jLabel90))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22))
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel45)
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(jButton27))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jButton29)
                                                .addGap(61, 61, 61)
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel88))))
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(jButton30))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(jLabel89)))
                                                .addGap(15, 15, 15)
                                                .addComponent(jButton25))))))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jButton26)
                        .addGap(240, 240, 240)
                        .addComponent(jButton28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        emergencyContactTabbedPane.addTab("APPOINTMENT", jPanel13);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(emergencyContactTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(emergencyContactTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SALUTAIONCOMBOBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOXActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_SALUTAIONCOMBOBOXActionPerformed

    private void insurancenumebrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insurancenumebrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insurancenumebrActionPerformed

    private void PhonetxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhonetxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhonetxtfeildActionPerformed

    private void statecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statecomboboxActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_statecomboboxActionPerformed

    public void groupButtonSelection() {
        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(malejRadioButton1);
        groupGender.add(femalejRadioButton);
        groupGender.add(othersjRadioButton3);

        ButtonGroup groupInsuranceApplicable = new ButtonGroup();
        groupInsuranceApplicable.add(yesjRadioButton4);
        groupInsuranceApplicable.add(nojRadioButton5);
        groupInsuranceApplicable.add(othersjRadioButton6);

    }

    private void apartmentNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apartmentNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apartmentNoTextFieldActionPerformed

    private void countrycomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countrycomboboxActionPerformed
        statecombobox.removeAllItems();
        if (countrycombobox.getSelectedItem().toString().equalsIgnoreCase("India")) {
            statecombobox.removeAllItems();
            statecombobox.addItem("Andhrapradesh");
            statecombobox.addItem("Andaman & NIcobar Island");
            statecombobox.addItem("Arunachal Pradesh");
            statecombobox.addItem("Assam");
            statecombobox.addItem("Bihar");
            statecombobox.addItem("Chhatisgarh");
            statecombobox.addItem("Chandigarh");
            statecombobox.addItem("Delhi");
            statecombobox.addItem("Daman And Diu");
            statecombobox.addItem("Goa");
            statecombobox.addItem("Gujrat");
            statecombobox.addItem("Haryana");
            statecombobox.addItem("Himachal Pradesh");
            statecombobox.addItem("jammu and kashmir");
            statecombobox.addItem("Jharkhand");
            statecombobox.addItem("Karnataka");
            statecombobox.addItem("Kerala");
            statecombobox.addItem("Lakshadeep");
            statecombobox.addItem("Madhya Pradesh");
            statecombobox.addItem("Maharashtra");
            statecombobox.addItem("Manipur");
            statecombobox.addItem("Meghalaya");
            statecombobox.addItem("Mizoram");
            statecombobox.addItem("Nagaland");
            statecombobox.addItem("Orissa");
            statecombobox.addItem("Punjab");
            statecombobox.addItem("Pondicherry");
            statecombobox.addItem("Rajasthan");
            statecombobox.addItem("Sikkim");
            statecombobox.addItem("Tamil Nadu");
            statecombobox.addItem("Telangana");
            statecombobox.addItem("Tripura");
            statecombobox.addItem("Uttarpradesh");
            statecombobox.addItem("Uttaranchal");
            statecombobox.addItem("West Bengal");

        } else if (countrycombobox.getSelectedItem().toString().equalsIgnoreCase("United states of america")) {
            statecombobox.removeAllItems();

            statecombobox.addItem("Alabama");
            statecombobox.addItem("Alaska");
            statecombobox.addItem("Arunachal Pradesh");
            statecombobox.addItem("Arizona");
            statecombobox.addItem("Arkansas");
            statecombobox.addItem("California");
            statecombobox.addItem("Colorado");
            statecombobox.addItem("Connecticut");
            statecombobox.addItem("Delaware");
            statecombobox.addItem("Florida");
            statecombobox.addItem("Georgia");
            statecombobox.addItem("Hawaii");
            statecombobox.addItem("Idaho");
            statecombobox.addItem("Illinois");
            statecombobox.addItem("Indiana");
            statecombobox.addItem("Iowa");
            statecombobox.addItem("Kansas");
            statecombobox.addItem("Kentuckhy");
            statecombobox.addItem("Lousiana");
            statecombobox.addItem("Maine");
            statecombobox.addItem("Maryland");
            statecombobox.addItem("Massachusetts");
            statecombobox.addItem("Michigan");
            statecombobox.addItem("Minnesota");
            statecombobox.addItem("Mississippi");
            statecombobox.addItem("Missourie");
            statecombobox.addItem("Montana");
            statecombobox.addItem("Neberaska");
            statecombobox.addItem("Nevada");
            statecombobox.addItem("New Hamsphire");
            statecombobox.addItem("New Jersey");
            statecombobox.addItem("NEw York");
            statecombobox.addItem("New Mexico");
            statecombobox.addItem("North Carolina ");
            statecombobox.addItem("North Dakota");
            statecombobox.addItem("Ohio");
            statecombobox.addItem("Okhlama");
            statecombobox.addItem("Oregon");
            statecombobox.addItem("Pennsylvania");
            statecombobox.addItem("Rhode Island");
            statecombobox.addItem("SOuth Caarolina");
            statecombobox.addItem("South Dakota");
            statecombobox.addItem("Tennessess");
            statecombobox.addItem("Texas");
            statecombobox.addItem("Utha");
            statecombobox.addItem("Vermont");
            statecombobox.addItem("Virginia");
            statecombobox.addItem("WAshington");
            statecombobox.addItem("West Virginia");
            statecombobox.addItem("Wisconin");
            statecombobox.addItem(" Wyomin");
        }
    }//GEN-LAST:event_countrycomboboxActionPerformed

    private void countrycomboboxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_countrycomboboxPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_countrycomboboxPropertyChange

    private void zipcodetxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zipcodetxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zipcodetxtfeildActionPerformed

    private void documentTypeComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentTypeComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documentTypeComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void documentTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentTypeComboBoxActionPerformed
        // TODO add your handling code here:

        String type = (String) documentTypeComboBox.getSelectedItem();
        documentJtable.getModel().setValueAt(type, 0, 0);
    }//GEN-LAST:event_documentTypeComboBoxActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    private void SALUTAIONCOMBOBOX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_SALUTAIONCOMBOBOX3ActionPerformed

    private void SALUTAIONCOMBOBOX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX4ActionPerformed

    private void SALUTAIONCOMBOBOX5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX5ActionPerformed

    private void SALUTAIONCOMBOBOX6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX6ActionPerformed

    private void SALUTAIONCOMBOBOX7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX7ActionPerformed

    private void SALUTAIONCOMBOBOX8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX8ActionPerformed

    private void SALUTAIONCOMBOBOX9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX9ActionPerformed

    private void SALUTAIONCOMBOBOX10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX10ActionPerformed

    private void SALUTAIONCOMBOBOX12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX12ActionPerformed

    private void SALUTAIONCOMBOBOX13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX13ActionPerformed

    private void SALUTAIONCOMBOBOX14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX14ActionPerformed

    private void SALUTAIONCOMBOBOX15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        jButton16.setEnabled(false);
        jButton17.setEnabled(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void insuranceDTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insuranceDTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insuranceDTxtFieldActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void comapnyNamejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comapnyNamejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comapnyNamejTextFieldActionPerformed

    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttonActionPerformed
        // TODO add your handling code here:
        if (validateData()) {
            
            if(account.getEmployee() instanceof StandardPatient){
            standardPatient.setFname(FirstNamejTextField.getText());
            standardPatient.setMiddleName(Middlenametxtfeild.getText());
            standardPatient.setLname(Lastnametxtfeild.getText());
            standardPatient.setSalutation((String) SALUTAIONCOMBOBOX.getSelectedItem());
            // standardPatient.setDob(dobDateChooser.getDate());
            standardPatient.setHeight(Float.parseFloat(heighttxtfeild.getText()));
            standardPatient.setWeight(Float.parseFloat(weighttxtfeild.getText()));
            standardPatient.setBloodGroup((String) bloodgroupcombox.getSelectedItem());
            standardPatient.setBmi(Float.parseFloat(BMItxtfeild.getText()));
            standardPatient.setSmoking((String) smokingcombobox.getSelectedItem());
            standardPatient.setDrinking((String) drinkingcombobox1.getSelectedItem());
            

            Address address = new Address();
            address.setApartmentNo(apartmentNoTextField.getText());
            address.setStreetNo(streetnotxtfeild.getText());
            address.setStreetName(streetNameTextField.getText());
            address.setCity(cityjTextField.getText());
            address.setState((String) statecombobox.getSelectedItem());
            address.setZipCode(zipcodetxtfeild.getText());
            address.setCountry((String) countrycombobox.getSelectedItem());
            standardPatient.setAddress(address);
            standardPatient.setGender(malejRadioButton1.getText());

            standardPatient.setMotherName(MOTHERNAMETXTFEILD.getText());
            standardPatient.setFatherName(FATHERNAMEjTextField1.getText());
            standardPatient.setRace((String) racecombobox.getSelectedItem());
            standardPatient.setMmid(MMIDTXTFEILD.getText());
            standardPatient.setInsuranceNo(insurancenumebr.getText());
            standardPatient.setEmailId(Emailidtxtfeild.getText());
            standardPatient.setSsn(SSNTXTFEILD.getText());
            standardPatient.setPhoneNo(Phonetxtfeild.getText());
            standardPatient.setLanguagesSpoken((String) languagespokencombobox.getSelectedItem());
            standardPatient.setOccupation((String) occupationcombobox.getSelectedItem());
            standardPatient.setImageIcon(ii);

            JOptionPane.showMessageDialog(null, "Profile Saved successfully!");
            displayDashBoard();
            editbutton.setEnabled(true);
            savebutton.setEnabled(false);}
            else
                if(account.getEmployee() instanceof PremiumPatient)
                {
                    
            premiumPatient.setFname(FirstNamejTextField.getText());
            premiumPatient.setMiddleName(Middlenametxtfeild.getText());
            premiumPatient.setLname(Lastnametxtfeild.getText());
            premiumPatient.setSalutation((String) SALUTAIONCOMBOBOX.getSelectedItem());
            // premiumPatient.setDob(dobDateChooser.getDate());
            premiumPatient.setHeight(Float.parseFloat(heighttxtfeild.getText()));
            premiumPatient.setWeight(Float.parseFloat(weighttxtfeild.getText()));
            premiumPatient.setBloodGroup((String) bloodgroupcombox.getSelectedItem());
            premiumPatient.setBmi(Float.parseFloat(BMItxtfeild.getText()));
            premiumPatient.setSmoking((String) smokingcombobox.getSelectedItem());
            premiumPatient.setDrinking((String) drinkingcombobox1.getSelectedItem());
            

            Address address = new Address();
            address.setApartmentNo(apartmentNoTextField.getText());
            address.setStreetNo(streetnotxtfeild.getText());
            address.setStreetName(streetNameTextField.getText());
            address.setCity(cityjTextField.getText());
            address.setState((String) statecombobox.getSelectedItem());
            address.setZipCode(zipcodetxtfeild.getText());
            address.setCountry((String) countrycombobox.getSelectedItem());
            
            premiumPatient.setAddress(address);
            premiumPatient.setGender(malejRadioButton1.getText());
            premiumPatient.setMotherName(MOTHERNAMETXTFEILD.getText());
            premiumPatient.setFatherName(FATHERNAMEjTextField1.getText());
            premiumPatient.setRace((String) racecombobox.getSelectedItem());
            premiumPatient.setMmid(MMIDTXTFEILD.getText());
            premiumPatient.setInsuranceNo(insurancenumebr.getText());
            premiumPatient.setEmailId(Emailidtxtfeild.getText());
            premiumPatient.setSsn(SSNTXTFEILD.getText());
            premiumPatient.setPhoneNo(Phonetxtfeild.getText());
            premiumPatient.setLanguagesSpoken((String) languagespokencombobox.getSelectedItem());
            premiumPatient.setOccupation((String) occupationcombobox.getSelectedItem());
            premiumPatient.setImageIcon(ii);

            JOptionPane.showMessageDialog(null, "Profile Saved successfully!");
            displayDashBoard();
            editbutton.setEnabled(true);
            savebutton.setEnabled(false);

                }
            
        } else {
            JOptionPane.showMessageDialog(null, "Please correct the details entered");
        }
    }//GEN-LAST:event_savebuttonActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        imagePathJLabel.setText(fileName);
        try {
            ii = new ImageIcon(scaleImage(190, 120, ImageIO.read(new File(f.getAbsolutePath()))));//get the image from file chooser and scale it to match JLabel size
            imageLabel.setIcon(ii);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void malejRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_malejRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_malejRadioButton1ActionPerformed

    private void bloodgroupcomboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodgroupcomboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bloodgroupcomboxActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jButton7.setEnabled(false);
        jButton8.setEnabled(true);
        
         enametxtfeild.setEnabled(true);
        phonetxtfeild.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEditable(true);
        enameTextField2.setEnabled(true);
        phoneTextField.setEnabled(true);
        jTextField2.setEnabled(true);
        addressjTextField.setEnabled(true);
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void enametxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enametxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enametxtfeildActionPerformed

    private void SALUTAIONCOMBOBOX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX1ActionPerformed

    private void SALUTAIONCOMBOBOX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALUTAIONCOMBOBOX2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SALUTAIONCOMBOBOX2ActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // TODO add your handling code here:
        editbutton.setEnabled(false);
        savebutton.setEnabled(true);
    }//GEN-LAST:event_editbuttonActionPerformed

    private void patientBackjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientBackjButtonActionPerformed
        // TODO add your handling code here:
         userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

        
    }//GEN-LAST:event_patientBackjButtonActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        String text = jTextArea1.getText();
        WorkRequest wr;
        
                for (Network network : business.getNetworkList()) {
                ArrayList<Enterprise> user = network.getEnterpriseDirectory().getSpecificEnterpriseList(Enterprise.EnterpriseType.Hospital);
                for (Enterprise u : user) {
                    ArrayList<Organization> org = (u.getOrganizationDirectory().getOrganizationList());
                    for (Organization o : org) {
                        for (UserAccount ua: o.getUserAccountDirectory().getUserAccountList()) {
                            if(ua.getEmployee() instanceof Doctor)
                            if((Doctor)ua.getEmployee()==((Doctor)jComboBox1.getSelectedItem()))
                            {
                                 System.out.println("hi");
                                 wr = o.getWorkQueue().addWorkRequest();
                                 int wrCount = o.getWorkQueue().getWorkRequestList().size();
                                 wr.setMessage(text);
                                 wr.setReceiver(ua);
                                 wr.setSender(account);
                                 wr.setWorkOrderID("Req000"+String.valueOf(wrCount));
                                 JOptionPane.showMessageDialog(null, "Request sent to Finance team");
                                 break;
                                 
                            }
                            }
                    }
                }

            }
        

    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        jButton22.setEnabled(false);
        jButton23.setEnabled(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
        BufferedImage bi;
        bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();
        return bi;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AllergyTable;
    private javax.swing.JTextField BMItxtfeild;
    private javax.swing.JTextField Emailidtxtfeild;
    private javax.swing.JTextField FATHERNAMEjTextField1;
    private javax.swing.JTextField FirstNamejTextField;
    private javax.swing.JTextField Lastnametxtfeild;
    private javax.swing.JTable MEDICALHSTRYTABLE;
    private javax.swing.JTextField MMIDTXTFEILD;
    private javax.swing.JTextField MOTHERNAMETXTFEILD;
    private javax.swing.JTextField Middlenametxtfeild;
    private javax.swing.JTextField PhoneTextField;
    private javax.swing.JTextField Phonetxtfeild;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX1;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX10;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX12;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX13;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX14;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX15;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX2;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX3;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX4;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX5;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX6;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX7;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX8;
    private javax.swing.JComboBox SALUTAIONCOMBOBOX9;
    private javax.swing.JTextField SSNTXTFEILD;
    private javax.swing.JCheckBox SUCIDE;
    private javax.swing.JTable VitalSignsTable;
    private javax.swing.JTextField addressjTextField;
    private javax.swing.JTextField ageTextField;
    private javax.swing.JTextField apartmentNoTextField;
    private javax.swing.JComboBox bloodgroupcombox;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cellTextField;
    private javax.swing.JTextField cityjTextField;
    private javax.swing.JTextField citytxtfeild;
    private javax.swing.JTextField comapnyNamejTextField;
    private javax.swing.JTextField contactPersonjTextField;
    private javax.swing.JComboBox countrycombobox;
    private javax.swing.JTextField countryjTextField;
    private javax.swing.JTable documentJtable;
    private javax.swing.JComboBox documentTypeComboBox;
    private javax.swing.JComboBox documentTypeComboBox2;
    private javax.swing.JComboBox drinkingcombobox1;
    private javax.swing.JTable duesTable;
    private javax.swing.JButton editbutton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField emailjTextField;
    private javax.swing.JTabbedPane emergencyContactTabbedPane;
    private javax.swing.JTextField enameTextField2;
    private javax.swing.JTextField enametxtfeild;
    private javax.swing.JTextField faxtextField;
    private javax.swing.JRadioButton femalejRadioButton;
    private javax.swing.JTextField genderDTxtField;
    private javax.swing.JTextField heighttxtfeild;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel imagePathJLabel;
    private javax.swing.JTextField insuranceDTxtField;
    private javax.swing.JTextField insurancenumebr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox languagespokencombobox;
    private javax.swing.JRadioButton malejRadioButton1;
    private javax.swing.JTable medicationJTable;
    private javax.swing.JTextField mmidTextField;
    private javax.swing.JTextField namejTextField;
    private javax.swing.JRadioButton nojRadioButton5;
    private javax.swing.JComboBox occupationcombobox;
    private javax.swing.JRadioButton othersjRadioButton3;
    private javax.swing.JRadioButton othersjRadioButton6;
    public static javax.swing.JButton patientBackjButton;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JTextArea phonejTextArea;
    private javax.swing.JTextField phonenumbertxtfeild;
    private javax.swing.JTextField phonetxtfeild;
    private javax.swing.JTable prescriptionJTable;
    private javax.swing.JComboBox racecombobox;
    private javax.swing.JButton savebutton;
    private javax.swing.JComboBox smokingcombobox;
    private javax.swing.JComboBox statecombobox;
    private javax.swing.JTextField statejTextField;
    private javax.swing.JTextField streetNameTextField;
    private javax.swing.JTextField streetjTextField;
    private javax.swing.JTextField streetnotxtfeild;
    private javax.swing.JTable surgeryJTable;
    private javax.swing.JTextField weighttxtfeild;
    private javax.swing.JRadioButton yesjRadioButton4;
    private javax.swing.JTextField zipcodetxtfeild;
    private javax.swing.JTextField zipjTextField;
    // End of variables declaration//GEN-END:variables
}
