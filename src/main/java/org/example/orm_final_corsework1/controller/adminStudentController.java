package org.example.orm_final_corsework1.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.Culinary_programBO;
import org.example.orm_final_corsework1.bo.custom.StudentsBO;
import org.example.orm_final_corsework1.dto.Culinary_ProgramsDTO;
import org.example.orm_final_corsework1.dto.StudentsDTO;
import org.example.orm_final_corsework1.tm.StudentsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class adminStudentController {

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
    private AnchorPane root;

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
        setCellValueFactory();
        getProgramID();
        loadAllStudents();
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/adminDashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
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

    @FXML
    void txtStudentIDOnKeyReleased(KeyEvent event) {

    }

}