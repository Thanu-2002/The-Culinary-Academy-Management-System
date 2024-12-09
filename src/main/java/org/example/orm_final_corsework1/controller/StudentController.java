package org.example.orm_final_corsework1.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.Culinary_programBO;
import org.example.orm_final_corsework1.bo.custom.StudentsBO;
import org.example.orm_final_corsework1.dto.Culinary_ProgramsDTO;
import org.example.orm_final_corsework1.dto.StudentsDTO;
import org.example.orm_final_corsework1.entity.Admin;
import org.example.orm_final_corsework1.entity.Admission_Coordinator;
import org.example.orm_final_corsework1.entity.Culinary_Programs;
import org.example.orm_final_corsework1.tm.StudentsTM;
import org.example.orm_final_corsework1.util.Regex;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.orm_final_corsework1.util.TextField.*;

public class StudentController {

    @FXML
    private JFXComboBox cmbSelectedProgram;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstname;

    @FXML
    private TableColumn<?, ?> colLastname;

    @FXML
    private TableColumn<?, ?> colMobileNumber;

    @FXML
    private TableColumn<?, ?> colSelectedProgram;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblProgramName;

    @FXML
    private TableView<StudentsTM> tblStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtStudentID;


    Culinary_programBO culinaryProgramsBO = (Culinary_programBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);
    StudentsBO studentsBO = (StudentsBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENTS);

    public void initialize(){
        txtStudentID.setEditable(false);
        setCellValueFactory();
        setDate();
        getCurrentStudentID();
        getProgramID();
        loadAllStudents();

        // Add listener for row selection
        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }





// Method to populate fields when a row is selected
private void populateFields(StudentsTM selectedStudent) {
    txtStudentID.setText(selectedStudent.getStudentID());
    txtFirstname.setText(selectedStudent.getFirstName());
    txtLastname.setText(selectedStudent.getLastName());
    txtEmail.setText(selectedStudent.getEmail());
    txtMobileNumber.setText(String.valueOf(selectedStudent.getMobileNumber()));
    txtAddress.setText(selectedStudent.getAddress());
    lblProgramName.setText(selectedStudent.getSelectedCourse());
}

//    lasasasas

    private void getProgramID() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {

            List<String> idList = culinaryProgramsBO.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSelectedProgram.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setDate(){
        LocalDate now =LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();
        String selectedCourse = lblProgramName.getText();

        Culinary_Programs Program_ID =  new Culinary_Programs();
        Program_ID.setProgramID((String) cmbSelectedProgram.getValue());
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();


        if (isValidStudent()){
            try{
                StudentsDTO studentsDTO = new StudentsDTO(studentID,date,firstName,lastName,email,mobileNumber,address,selectedCourse,Program_ID,admissionCoordinators,admins);

                boolean isSaved = studentsBO.add(studentsDTO);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student details added!").show();
                    clearFields();
                    getCurrentStudentID();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Error in add student details!").show();
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            loadAllStudents();
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter All Fields !!").show();
        }



    }


    private void loadAllStudents(){
        ObservableList<StudentsTM> obList = FXCollections.observableArrayList();

        try{
            List<StudentsDTO> studentsDTOList = studentsBO.getAll();

            for (StudentsDTO studentsDTO: studentsDTOList){
                StudentsTM tm = new StudentsTM(studentsDTO.getStudentID(),studentsDTO.getDate(),studentsDTO.getFirstName(),studentsDTO.getLastName(),studentsDTO.getEmail(),studentsDTO.getMobileNumber(),studentsDTO.getAddress(),studentsDTO.getSelectedCourse());
                obList.add(tm);
            }

            tblStudents.setItems(obList);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory(){
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSelectedProgram.setCellValueFactory(new PropertyValueFactory<>("selectedCourse"));
    }

    private void getCurrentStudentID() {
        try {

            String currentStudentID = studentsBO.getCurrentStudentID();

            String nextStudentID = generateNextStudentID(currentStudentID);

            txtStudentID.setText(nextStudentID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private String generateNextStudentID(String currentStudentID) {

        if (currentStudentID != null) {
            String[] split = currentStudentID.split("ST");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 999) {
                idNum++;
                return "ST" + String.format("%04d", idNum);
            } else {
                return "Error: Maximum bill ID reached (ST9999)";
            }
        }
        return "ST0001";

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentStudentID();
    }

    public void clearFields(){
        txtStudentID.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtEmail.setText("");
        txtMobileNumber.setText("");
        txtAddress.setText("");
        lblProgramName.setText("");
        cmbSelectedProgram.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();
        String selectedCourse = lblProgramName.getText();

        Culinary_Programs Program_ID =  new Culinary_Programs();
        Program_ID.setProgramID((String) cmbSelectedProgram.getValue());
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin> admins = new ArrayList<>();

        try{
            StudentsDTO studentsDTO = new StudentsDTO(studentID,date,firstName,lastName,email,mobileNumber,address,selectedCourse,Program_ID,admissionCoordinators,admins);

            boolean isDeleted = studentsBO.delete(studentsDTO);

            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student details deleted!").show();
                clearFields();
                getCurrentStudentID();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in delete student details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllStudents();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String studentID = txtStudentID.getText();
        Date date = java.sql.Date.valueOf(lblDate.getText());
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText();
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String address = txtAddress.getText();

        // Create Culinary_Programs object for the selected program
        Culinary_Programs newProgram = new Culinary_Programs();
        newProgram.setProgramID((String) cmbSelectedProgram.getValue());

        // Retrieve the existing student data
            StudentsDTO existingStudent = studentsBO.searchByID(studentID);
            if (existingStudent != null) {
                // Combine existing selected course with the new one
                String existingCourses = existingStudent.getSelectedCourse();
                String newSelectedCourse = lblProgramName.getText();

                if (cmbSelectedProgram.getValue()==null){
                    StudentsDTO studentsDTO = new StudentsDTO(studentID, date, firstName, lastName, email,
                            mobileNumber, address, existingCourses,null, null, null);

                    boolean isUpdated = studentsBO.update(studentsDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student details updated!").show();
                        clearFields();
                        getCurrentStudentID();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Error in updating student details!").show();
                    }
                }else {
                    // Assuming courses are separated by commas, adjust as needed
                    String combinedCourses = existingCourses + ", " + newSelectedCourse;


                    StudentsDTO studentsDTO = new StudentsDTO(studentID, date, firstName, lastName, email,
                            mobileNumber, address, combinedCourses,
                            newProgram, null, null);

                    boolean isUpdated = studentsBO.update(studentsDTO);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student details updated!").show();
                        clearFields();
                        getCurrentStudentID();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Error in updating student details!").show();
                    }
                }
                loadAllStudents();
            } else {
                new Alert(Alert.AlertType.WARNING, "Student not found for update!").show();
            }

        loadAllStudents();
    }

    @FXML
    void cmbSelectedProgramOnAction(ActionEvent event) {
        String programID = (String) cmbSelectedProgram.getValue();

        try {

            Culinary_ProgramsDTO culinaryProgramsDTO = culinaryProgramsBO.searchByID(programID);

            lblProgramName.setText(culinaryProgramsDTO.getProgramName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(EMAIL,txtEmail);

    }

    @FXML
    void txtMobileNumberOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TEL,txtMobileNumber);

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String studentID = txtStudentID.getText();

        try {
            StudentsDTO studentsDTO = studentsBO.searchByID(studentID);
            if (studentsDTO != null) {

                txtFirstname.setText(studentsDTO.getFirstName());
                txtLastname.setText(studentsDTO.getLastName());
                txtEmail.setText(studentsDTO.getEmail());
                txtMobileNumber.setText(String.valueOf(studentsDTO.getMobileNumber()));
                txtAddress.setText(studentsDTO.getAddress());


                String selectedCourses = studentsDTO.getSelectedCourse();


                if (selectedCourses != null && !selectedCourses.isEmpty()) {

                    String[] programs = selectedCourses.split(",");


                    StringBuilder programNames = new StringBuilder();
                    for (String program : programs) {
                        if (programNames.length() > 0) {
                            programNames.append(", ");
                        }
                        programNames.append(program.trim());
                    }


                    lblProgramName.setText(programNames.toString());
                } else {
                    lblProgramName.setText("No programs selected");
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Student data not found!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtStudentIDOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(STUDENTID,txtStudentID);

    }


    public boolean isValidStudent() {
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.NAME, txtFirstname)) return false;
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.NAME, txtLastname)) return false;
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.TEL, txtMobileNumber)) return false;
        if (!Regex.setTextColor(EMAIL, txtEmail)) return false;

        return true;
    }

}