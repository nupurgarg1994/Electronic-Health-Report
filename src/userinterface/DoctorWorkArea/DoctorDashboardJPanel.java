/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorWorkArea;

import Business.EcoSystem;
import Business.Employee.Address;
import Business.Employee.Doctor;
import Business.Employee.PremiumPatient;
import Business.Employee.StandardPatient;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import Business.Prescription.Prescription;
import Business.UserAccount.UserAccount;
import Business.Validations.ValidationResult;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import static java.awt.geom.Rectangle2D.union;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
//import static jdk.management.resource.ResourceContextFactory.isEnabled;
//import static jdk.management.resource.internal.ResourceNatives.isEnabled;
//import static jdk.nashorn.internal.runtime.logging.DebugLogger.isEnabled;
import userinterface.PatientDetails.PatientDashboardJPanel;

/**
 *
 * @author nupur
 */
public class DoctorDashboardJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoctorDashboardJPanel
     */
    private JPanel userProcessContainer;
    private DoctorOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem system;
    private Doctor doctor;

    public DoctorDashboardJPanel(JPanel userProcessContainer, UserAccount account, DoctorOrganization organization, Enterprise enterprise, EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.system = system;
        groupButtonSelection();
        tabSettings();
        editbutton.setEnabled(true);
        savebutton.setEnabled(false);
        if (account.getEmployee() instanceof Doctor) {
            this.doctor = (Doctor) account.getEmployee();
        }
        displayCompleteDoctor();
        populateCountrycombobox();

    }

    public void displayCompleteDoctor() {
        FirstNamejTextField.setText(doctor.getFname());
        Lastnametxtfeild.setText(doctor.getLname());
//            jDateChooser1.setDate(doctor.getDob());
        Housenotxtfeild.setText(doctor.getAddress().getApartmentNo());
        zipcodetxtfeild.setText(doctor.getAddress().getStreetNo());
        streetNametxtfeild.setText(doctor.getAddress().getStreetName());
        cityTextField.setText(doctor.getAddress().getCity());

        if (doctor.getAddress().getCountry() != null) {
            for (int i = 0; i < countrycombobox.getModel().getSize(); i++) {
                if (doctor.getAddress().getCountry().equals(countrycombobox.getItemAt(i).toString())) {
                    countrycombobox.setSelectedIndex(i);
                    break;
                }
            }
        }

        if (doctor.getAddress().getState() != null) {
            for (int i = 0; i < statecombobox.getModel().getSize(); i++) {
                if (doctor.getAddress().getState().equals(statecombobox.getItemAt(i).toString())) {
                    statecombobox.setSelectedIndex(i);
                    break;
                }
            }
        }

        zipcodetxtfeild.setText(doctor.getAddress().getZipCode());
        if (malejRadioButton1.isSelected()) {
            doctor.setGender(malejRadioButton1.getText());
        } else if (femalejRadioButton2.isSelected()) {
            doctor.setGender(femalejRadioButton2.getText());
        }
        Emailidtxtfeild.setText(doctor.getEmailId());
        SSNTXTFEILD.setText(doctor.getSsn());
        Phonetxtfeild.setText(doctor.getPhoneNo());

        if (doctor.getLanguagesSpoken() != null) {
            for (int i = 0; i < languagespokencombobox.getModel().getSize(); i++) {
                if (doctor.getLanguagesSpoken().equals(languagespokencombobox.getItemAt(i).toString())) {
                    languagespokencombobox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void populateCountrycombobox() {
        countrycombobox = new JComboBox();
        countrycombobox.addItem("India");
        countrycombobox.addItem("United States of America");
    }

    public void tabSettings() {
        Prescription.setSelectedIndex(2);
    }

    public boolean validateData() //        isStringValidatios()
    {
        boolean b1 = ValidationResult.isnotnull(FirstNamejTextField, FirstNamejTextField.getText());
        boolean b2 = ValidationResult.isnotnull(Lastnametxtfeild, Lastnametxtfeild.getText());
        // boolean b3 = ValidationResult.isAString(FATHERNAMEjTextField1,FATHERNAMEjTextField1.getText());
        boolean b4 = ValidationResult.isemail(Emailidtxtfeild, Emailidtxtfeild.getText());
        //ValidationResult.isnotnull(FATHERNAMEjTextField1,FATHERNAMEjTextField1.getText());
        boolean b5 = ValidationResult.isLong(SSNTXTFEILD, SSNTXTFEILD.getText());
        //ValidationResult.isnotnull(statecombobox,statecombobox.getSelectedItem());
        boolean b7 = ValidationResult.isLong(Phonetxtfeild, Phonetxtfeild.getText());
        boolean b8 = ValidationResult.isLong(zipcodetxtfeild, zipcodetxtfeild.getText());
        boolean b9 = ValidationResult.isnotnull(streetNametxtfeild, streetNametxtfeild.getText());
        boolean b10 = ValidationResult.isnotnull(streetnotxtfeild, streetnotxtfeild.getText());

        //ValidationResult.isAString(enametxtfeild,enametxtfeild.getText());
        //ValidationResult.isAString(enameTextField2,enameTextField2.getText());
        if (b1 && b2 && b4 && b5 && b7 && b8 && b9 && b10) {
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

        Prescription = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        patientNameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        problemTextField = new javax.swing.JTextField();
        acceptButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        textField1 = new java.awt.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        medicinesJTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        prescriptionNumberjTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        datejTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        MMIDfeild = new javax.swing.JTextField();
        prescribedByjTextField = new javax.swing.JTextField();
        prescribeBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        FirstNamejTextField2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Middlenametxtfeild1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        addresstxtfeild1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        Phonetxtfeild1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        timingsTesxtfeild = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        mmidSerachTxtField = new javax.swing.JTextField();
        mmidSearchBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Lastnametxtfeild = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        malejRadioButton1 = new javax.swing.JRadioButton();
        femalejRadioButton2 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SSNTXTFEILD = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Emailidtxtfeild = new javax.swing.JTextField();
        Phonetxtfeild = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        languagespokencombobox = new javax.swing.JComboBox();
        statecombobox = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        streetNametxtfeild = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        Housenotxtfeild = new javax.swing.JTextField();
        countrycombobox = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        streetnotxtfeild = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        savebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        zipcodetxtfeild = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FirstNamejTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();

        jPanel2.setBackground(new java.awt.Color(8, 141, 165));

        jLabel2.setText("MMID");

        jLabel6.setText("Patient Name  :");

        jLabel10.setText("Problem :");

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        rejectButton.setText("Reject");

        jButton1.setText("Refresh");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MMID", "Patient Name", "Problem", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(acceptButton)
                        .addGap(72, 72, 72)
                        .addComponent(rejectButton)
                        .addGap(43, 43, 43)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(36, 36, 36)
                                .addComponent(patientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addComponent(jLabel10)
                        .addGap(39, 39, 39)
                        .addComponent(problemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(720, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(patientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(problemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton)
                    .addComponent(rejectButton))
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1546, Short.MAX_VALUE))
        );

        Prescription.addTab("Appointments", jPanel2);

        jPanel3.setBackground(new java.awt.Color(8, 141, 165));

        textField1.setText("                             MEDICINES");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        medicinesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "S No.", "Medicinde Names", "Num Of Time Per Day"
            }
        ));
        jScrollPane2.setViewportView(medicinesJTable);

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        jLabel13.setText("Prescription");

        jLabel1.setText("Prescription Number :");

        jLabel14.setText("Date :");

        jLabel15.setText("MMID :");

        jLabel17.setText("Prescribed By   :");

        prescribeBtn.setText("Prescribe");
        prescribeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescribeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane2)
                            .addComponent(textField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(prescribeBtn)
                                .addGap(777, 777, 777)))
                        .addGap(425, 425, 425))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(MMIDfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(prescriptionNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14))
                                .addGap(86, 86, 86)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prescribedByjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(892, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(4, 4, 4)
                        .addComponent(datejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MMIDfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(prescribedByjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(prescriptionNumberjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(48, 48, 48)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294)
                .addComponent(prescribeBtn)
                .addContainerGap(1377, Short.MAX_VALUE))
        );

        Prescription.addTab("Prescription", jPanel3);

        jPanel4.setBackground(new java.awt.Color(8, 141, 165));

        jLabel19.setText("NAME *");

        jLabel21.setText("SPECIALITY*");

        jLabel33.setText("ADDRESS  *");

        jLabel25.setText("PHONE NUMBER *");

        Phonetxtfeild1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Phonetxtfeild1ActionPerformed(evt);
            }
        });

        jLabel35.setText("CITY *");

        jLabel24.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 24)); // NOI18N
        jLabel24.setText("APPOINTMENTS:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MMID", "Patient Name ", "Date of Appointment"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        jLabel22.setText("MMID ");

        mmidSerachTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmidSerachTxtFieldActionPerformed(evt);
            }
        });

        mmidSearchBtn.setText("Search");
        mmidSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmidSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(39, 39, 39)
                                        .addComponent(FirstNamejTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)
                                        .addComponent(addresstxtfeild1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addGap(44, 44, 44)
                                            .addComponent(Middlenametxtfeild1))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel25)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Phonetxtfeild1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(83, 83, 83)
                                        .addComponent(timingsTesxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel22)
                        .addGap(41, 41, 41)
                        .addComponent(mmidSerachTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mmidSearchBtn)))
                .addContainerGap(761, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(FirstNamejTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(Middlenametxtfeild1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(addresstxtfeild1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(Phonetxtfeild1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(timingsTesxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(mmidSerachTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mmidSearchBtn))
                .addGap(60, 60, 60)
                .addComponent(jLabel24)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1497, Short.MAX_VALUE))
        );

        Prescription.addTab("Dashboard", jPanel4);

        jPanel5.setBackground(new java.awt.Color(8, 141, 165));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1498, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1972, Short.MAX_VALUE)
        );

        Prescription.addTab("Location", jPanel5);

        jPanel1.setBackground(new java.awt.Color(8, 141, 165));

        jLabel12.setText("GENDER *");

        jLabel4.setText("LAST NAME *");

        malejRadioButton1.setText("MALE");

        femalejRadioButton2.setText("FEMALE");

        jLabel7.setText("D.O.B *");

        jLabel9.setText("SSN *");

        jLabel16.setText("EMAIL ID *");

        Phonetxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhonetxtfeildActionPerformed(evt);
            }
        });

        jLabel18.setText("PHONE NUMBER *");

        jLabel20.setText("LANGUAGES SPOKEN *");

        languagespokencombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---SELECT---", "Afrikaans", "Albanian", "Amharic", "Arabic (Egyptian Spoken)", "Aramaic", "Armenian", "Assamese", "Aymara", "Azerbaijani", "Balochi", "Bamanankan", "Bashkort (Bashkir)", "Basque", "Belarusan", "Bengali", "Bhojpuri", "Bislama", "Bosnian", "Brahui", "Bulgarian", "Burmese", "Cantonese", "Catalan", "Cebuano", "Chechen", "Cherokee", "Croatian", "Czech", "Dakota", "Danish", "Dari", "Dholuo", "DutchEnglish", "Esperanto", "Estonian", "Éwé", "Finnish", "French", "Georgian", "German", "Gikuyu", "Greek", "Guarani", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hawaiian Creole", "Hebrew", "Hiligaynon", "Hindi", "Hungarian", "Icelandic", "Igbo", "Ilocano", "Indonesian (Bahasa Indonesia)", "Inuit/Inupiaq", "Irish Gaelic", "Italian", "Japanese", "Jarai", "Javanese", "K’iche’", "Kabyle", "Kannada", "Kashmiri", "Kazakh", "Khmer", "KhoekhoeKorean", "Kurdish", "Kyrgyz", "Lao", "Latin", "Latvian", "Lingala", "Lithuanian", "Macedonian", "Maithili", "Malagasy", "Malay (Bahasa Melayu)", "Malayalam", "Mandarin (Chinese)", "Marathi", "Mende", "Mongolian", "Nahuatl", "Navajo", "Nepali", "Norwegian", "Ojibwa", "Oriya", "Oromo", "Pashto", "Persian", "Polish", "Portuguese", "Punjabi", "Quechua", "Romani", "Romanian", "Russian", "Rwanda", "Samoan", "Sanskrit", "SerbianShona", "Sindhi", "Sinhala", "Slovak", "Slovene", "Somali", "Spanish", "Swahili", "Swedish", "Tachelhit", "Tagalog", "Tajiki", "Tamil", "Tatar", "Telugu", "Thai", "Tibetic languages", "Tigrigna", "Tok Pisin", "Turkish", "Turkmen", "Ukrainian", "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Warlpiri", "Welsh", "Wolof", "Xhosa", "Yakut", "Yiddish", "Yoruba", "Yucatec", "Zapotec", "Zulu" }));

        statecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statecomboboxActionPerformed(evt);
            }
        });

        jLabel27.setText("STREET NAME  *");

        streetNametxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                streetNametxtfeildActionPerformed(evt);
            }
        });

        jLabel30.setText("HOUSE NO");

        Housenotxtfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HousenotxtfeildActionPerformed(evt);
            }
        });

        countrycombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---PLEASE SELECT---", "India", "United States of America" }));
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

        jLabel31.setText("COUNTRY *");

        jLabel28.setText("STREET NO *");

        jLabel29.setText("STATE *");

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

        jLabel34.setText("ZIPCODE *");

        jLabel3.setText("FIRST NAME *");

        FirstNamejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstNamejTextFieldActionPerformed(evt);
            }
        });

        jLabel23.setText("IMAGE");

        jLabel5.setText("CITY *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel27)
                                .addComponent(jLabel28)
                                .addComponent(jLabel29)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(Lastnametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 692, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(malejRadioButton1)
                                            .addGap(41, 41, 41)
                                            .addComponent(femalejRadioButton2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addGap(75, 75, 75)
                                            .addComponent(Emailidtxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(statecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(streetNametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(streetnotxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel34))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(countrycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Phonetxtfeild, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                            .addComponent(SSNTXTFEILD, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                            .addComponent(languagespokencombobox, 0, 185, Short.MAX_VALUE)
                                            .addComponent(Housenotxtfeild, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                            .addComponent(zipcodetxtfeild)))
                                    .addGap(214, 214, 214))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addComponent(jLabel30))
                            .addGap(479, 479, 479)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(editbutton)
                        .addGap(39, 39, 39)
                        .addComponent(savebutton)
                        .addGap(526, 526, 526))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addGap(51, 51, 51)
                    .addComponent(FirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1222, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(Lastnametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(malejRadioButton1)
                                .addComponent(femalejRadioButton2)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(Emailidtxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SSNTXTFEILD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(streetNametxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Phonetxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(streetnotxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(languagespokencombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(statecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(zipcodetxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(Housenotxtfeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(countrycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editbutton)
                    .addComponent(savebutton))
                .addGap(1454, 1454, 1454))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FirstNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addContainerGap(1941, Short.MAX_VALUE)))
        );

        Prescription.addTab("Profile", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Prescription)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Prescription, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PhonetxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhonetxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhonetxtfeildActionPerformed

    private void statecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statecomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statecomboboxActionPerformed

    private void HousenotxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HousenotxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HousenotxtfeildActionPerformed

    private void countrycomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countrycomboboxActionPerformed

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

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void Phonetxtfeild1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Phonetxtfeild1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Phonetxtfeild1ActionPerformed

    public void groupButtonSelection() {
        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(malejRadioButton1);
        groupGender.add(femalejRadioButton2);

    }

    private void mmidSerachTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmidSerachTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mmidSerachTxtFieldActionPerformed

    public List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }

    private void mmidSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmidSearchBtnActionPerformed
        // TODO add your handling code here:
        boolean flag=false;
        if (mmidSerachTxtField.getText() != "" && mmidSerachTxtField.getText() != null) {
            String value = mmidSerachTxtField.getText();
            for (Network network : system.getNetworkList()) {
                ArrayList<Enterprise> user = network.getEnterpriseDirectory().getSpecificEnterpriseList(Enterprise.EnterpriseType.User);
                for (Enterprise u : user) {
                    ArrayList<Organization> org = (u.getOrganizationDirectory().getOrganizationList());
                    for (Organization o : org) {
                        for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                            if (ua.getEmployee() instanceof StandardPatient) {
                                StandardPatient s = (StandardPatient) ua.getEmployee();
                                if (s.getMmid() != null && s.getMmid().equals(value)) {
                                    flag=true;
                                    PatientDashboardJPanel pddjp = new PatientDashboardJPanel(userProcessContainer, ua, o, enterprise, system);
                                    userProcessContainer.add("PatientDashboardJPanel", pddjp);
                                    CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                                    layout.next(userProcessContainer);
                                    List<Component> comps = getAllComponents(pddjp);
                                    for (Component comp : comps) {
                                        if (!(comp instanceof JTabbedPane) || (comp instanceof JButton)) {
                                            System.out.println(comp);
                                            comp.setEnabled(false);
                                            PatientDashboardJPanel.patientBackjButton.setEnabled(true);
                                        }
                                    }
                                }
                            } else if (ua.getEmployee() instanceof PremiumPatient) {
                                PremiumPatient s = (PremiumPatient) ua.getEmployee();
                                if (s.getMmid() != null && s.getMmid().equals(value)) {
                                    flag=true;
                                    PatientDashboardJPanel pddjp = new PatientDashboardJPanel(userProcessContainer, ua, o, enterprise, system);
                                    userProcessContainer.add("PatientDashboardJPanel", pddjp);
                                    List<Component> comps = getAllComponents(pddjp);
                                    for (Component comp : comps) {
                                        if (!(comp instanceof JTabbedPane) || (comp instanceof JButton)) {
                                           // System.out.println(comp);
                                            comp.setEnabled(false);
                                            PatientDashboardJPanel.patientBackjButton.setEnabled(true);
                                        }
                                    }
                                    CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                                    layout.next(userProcessContainer);
                                }
                            } 
                                //else {
//                               JOptionPane.showMessageDialog(null, "Entered MMID does not exist");
//                            }
                        }
                    }
                }

            }
             if(!flag)
            {
                JOptionPane.showMessageDialog(null, "MMID not found! Please try again.");
            }

        }
 else
            JOptionPane.showMessageDialog(null, "The value cannot be null");

    }//GEN-LAST:event_mmidSearchBtnActionPerformed


    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttonActionPerformed
        // TODO add your handling code here:
        if (validateData()) {
            doctor.setFname(FirstNamejTextField.getText());
            doctor.setLname(Lastnametxtfeild.getText());
//            doctor.setDob(jDateChooser1.getDate());
            doctor.getAddress().setApartmentNo(Housenotxtfeild.getText());
            doctor.getAddress().setStreetNo(zipcodetxtfeild.getText());
            doctor.getAddress().setStreetName(streetNametxtfeild.getText());
            doctor.getAddress().setCity(cityTextField.getText());
            doctor.getAddress().setState((String) statecombobox.getSelectedItem());
            doctor.getAddress().setCountry((String) countrycombobox.getSelectedItem());
            doctor.getAddress().setZipCode(zipcodetxtfeild.getText());
            if (malejRadioButton1.isSelected()) {
                doctor.setGender(malejRadioButton1.getText());
            } else if (femalejRadioButton2.isSelected()) {
                doctor.setGender(femalejRadioButton2.getText());
            }
            doctor.setEmailId(Emailidtxtfeild.getText());
            doctor.setSsn(SSNTXTFEILD.getText());
            doctor.setPhoneNo(Phonetxtfeild.getText());
            doctor.setLanguagesSpoken((String) languagespokencombobox.getSelectedItem());
            JOptionPane.showMessageDialog(null, "Data Updated Successfully");

            editbutton.setEnabled(true);
            savebutton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please correct the data");
        }
    }//GEN-LAST:event_savebuttonActionPerformed

    private void FirstNamejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNamejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstNamejTextFieldActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // TODO add your handling code here:
        editbutton.setEnabled(false);
        savebutton.setEnabled(true);

    }//GEN-LAST:event_editbuttonActionPerformed

    private void streetNametxtfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_streetNametxtfeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_streetNametxtfeildActionPerformed

    private void prescribeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescribeBtnActionPerformed
        // TODO add your handling code here:
        StandardPatient stdPatient = null;
        PremiumPatient prePatient = null;
        if (MMIDfeild.getText() != null && MMIDfeild.getText() != "") {
            for (Network network : system.getNetworkList()) {
                ArrayList<Enterprise> user = network.getEnterpriseDirectory().getSpecificEnterpriseList(Enterprise.EnterpriseType.User);
                for (Enterprise u : user) {
                    ArrayList<Organization> org = (u.getOrganizationDirectory().getOrganizationList());
                    for (Organization o : org) {
                        for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                            if (ua.getEmployee() instanceof StandardPatient) {
                                StandardPatient s = (StandardPatient) ua.getEmployee();
                                if (s.getMmid() != null && s.getMmid().equals(MMIDfeild.getText())) {
                                    stdPatient = (StandardPatient) s;
                                }
                            } else if (ua.getEmployee() instanceof PremiumPatient) {
                                PremiumPatient s = (PremiumPatient) ua.getEmployee();
                                if (s.getMmid() != null && s.getMmid().equals(MMIDfeild.getText())) {
                                    prePatient = (PremiumPatient) s;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "The value cannot be null");
        }

        if (stdPatient != null) {
            Prescription p = new Prescription();
            ArrayList<Prescription> presList = stdPatient.getPrescriptions();
            p.setPrescriptionNum(Integer.parseInt(prescriptionNumberjTextField.getText()));
            p.setDate(datejTextField.getText());
            p.setPrescribedBy(this.userAccount.getEmployee().getName());
            DefaultTableModel model = (DefaultTableModel) medicinesJTable.getModel();
            if (model.getRowCount() > 0) {
                ArrayList<String> medName = new ArrayList<String>();
                ArrayList<Integer> numOfTimePerDay = new ArrayList<Integer>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 1) != null) {
                        medName.add(model.getValueAt(i, 1).toString());
                    }
                    if (model.getValueAt(i, 2) != null) {
                        numOfTimePerDay.add(Integer.parseInt(model.getValueAt(i, 2).toString()));
                    }
                }
                if (medName != null) {
                    p.setMedicineName(medName);
                }
                if (numOfTimePerDay != null) {
                    p.setNumOfTimesPerDay(numOfTimePerDay);
                }
            }
            if (presList != null && presList.size() > 0) {
                presList.add(p);
                stdPatient.setPrescriptions(presList);
                
                JOptionPane.showMessageDialog(null, "Prescription sent to Patient");
            } else {
                presList = new ArrayList<Prescription>();
                presList.add(p);
                stdPatient.setPrescriptions(presList);
                JOptionPane.showMessageDialog(null, "Prescription sent to Patient");
            }
        } else if (prePatient != null) {
            Prescription p = new Prescription();
            ArrayList<Prescription> presList = prePatient.getPrescriptions();
            p.setPrescriptionNum(Integer.parseInt(prescriptionNumberjTextField.getText()));
            p.setDate(datejTextField.getText());
            if (presList != null && presList.size() > 0) {
                presList.add(p);
                prePatient.setPrescriptions(presList);
                JOptionPane.showMessageDialog(null, "Prescription sent to Patient");
            } else {
                presList = new ArrayList<Prescription>();
                presList.add(p);
                prePatient.setPrescriptions(presList);
                JOptionPane.showMessageDialog(null, "Prescription sent to Patient");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Patient not found");
        }
        
    }//GEN-LAST:event_prescribeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Emailidtxtfeild;
    private javax.swing.JTextField FirstNamejTextField;
    private javax.swing.JTextField FirstNamejTextField2;
    private javax.swing.JTextField Housenotxtfeild;
    private javax.swing.JTextField Lastnametxtfeild;
    private javax.swing.JTextField MMIDfeild;
    private javax.swing.JTextField Middlenametxtfeild1;
    private javax.swing.JTextField Phonetxtfeild;
    private javax.swing.JTextField Phonetxtfeild1;
    private javax.swing.JTabbedPane Prescription;
    private javax.swing.JTextField SSNTXTFEILD;
    private javax.swing.JButton acceptButton;
    private javax.swing.JTextField addresstxtfeild1;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JComboBox countrycombobox;
    private javax.swing.JTextField datejTextField;
    private javax.swing.JButton editbutton;
    private javax.swing.JRadioButton femalejRadioButton2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox languagespokencombobox;
    private javax.swing.JRadioButton malejRadioButton1;
    private javax.swing.JTable medicinesJTable;
    private javax.swing.JButton mmidSearchBtn;
    private javax.swing.JTextField mmidSerachTxtField;
    private javax.swing.JTextField patientNameTextField;
    private javax.swing.JButton prescribeBtn;
    private javax.swing.JTextField prescribedByjTextField;
    private javax.swing.JTextField prescriptionNumberjTextField;
    private javax.swing.JTextField problemTextField;
    private javax.swing.JButton rejectButton;
    private javax.swing.JButton savebutton;
    private javax.swing.JComboBox statecombobox;
    private javax.swing.JTextField streetNametxtfeild;
    private javax.swing.JTextField streetnotxtfeild;
    private java.awt.TextField textField1;
    private javax.swing.JTextField timingsTesxtfeild;
    private javax.swing.JTextField zipcodetxtfeild;
    // End of variables declaration//GEN-END:variables
}
