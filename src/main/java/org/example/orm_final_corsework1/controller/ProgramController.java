package org.example.orm_final_corsework1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.Culinary_programBO;
import org.example.orm_final_corsework1.dto.Culinary_ProgramsDTO;
import org.example.orm_final_corsework1.entity.Admin;
import org.example.orm_final_corsework1.entity.Admission_Coordinator;
import org.example.orm_final_corsework1.entity.Students;
import org.example.orm_final_corsework1.tm.CulinaryProgramsTM;
import org.example.orm_final_corsework1.util.Regex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.orm_final_corsework1.util.TextField.*;

public class ProgramController {
    public AnchorPane root;
    public TextField txtProgramID;
    public TextField txtProgramName;
    public TextField txtDuration;
    public TextField txtFee;
    public TableView <CulinaryProgramsTM>tblCulinaryPrograms;
    public TableColumn <?,?> colProgramID;
    public TableColumn <?,?> colProgramName;
    public TableColumn <?,?> colDuration;
    public TableColumn <?,?> colFee;

    Culinary_programBO culinaryProgramsBO = (Culinary_programBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);

    public void initialize(){
        txtProgramID.setEditable(false);
        setCellValueFactory();
        getCurrentProgramID();
        loadAllCulinaryPrograms();


        tblCulinaryPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);
            }
        });
    }

    private void populateFields(CulinaryProgramsTM selectedProgram) {
        txtProgramID.setText(selectedProgram.getProgramID());
        txtProgramName.setText(selectedProgram.getProgramName());
        txtDuration.setText(selectedProgram.getDuration());
        txtFee.setText(selectedProgram.getFee());
    }

    private void getCurrentProgramID() {
        try {

            String currentStudentID = culinaryProgramsBO.getCurrentProgramID();

            String nextStudentID = generateNextStudentID(currentStudentID);

            txtProgramID.setText(nextStudentID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextStudentID(String currentStudentID) {

        if (currentStudentID != null) {
            String[] split = currentStudentID.split("CA");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 999) {
                idNum++;
                return "CA" + String.format("%04d", idNum);
            } else {
                return "Error: Maximum bill ID reached (CA9999)";
            }
        }
        return "CA0001";

    }

    public void btnAddOnAction(ActionEvent event) {
        String programID = txtProgramID.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        String fee = txtFee.getText();

        List<Students> students = new ArrayList<>();
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin>admins = new ArrayList<>();

        if (isValidStudent()){
            try {
                Culinary_ProgramsDTO culinaryProgramsDTO = new Culinary_ProgramsDTO(programID,programName,duration,fee,students,admissionCoordinators,admins);

                boolean isSaved = culinaryProgramsBO.add(culinaryProgramsDTO);

                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Program details added!").show();
                    clearFields();
                    getCurrentProgramID();

                }else {
                    new Alert(Alert.AlertType.ERROR, "Error in add program details!").show();
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            loadAllCulinaryPrograms();


        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter All Fields !!").show();
        }

        getCurrentProgramID();


    }

    public void btnUpdateOnAction(ActionEvent event) {
        String programID = txtProgramID.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        String fee = txtFee.getText();

        List<Students> students = new ArrayList<>();
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin>admins = new ArrayList<>();

        try {
            Culinary_ProgramsDTO culinaryProgramsDTO = new Culinary_ProgramsDTO(programID,programName,duration,fee,students,admissionCoordinators,admins);

            boolean isUpdated = culinaryProgramsBO.update(culinaryProgramsDTO);

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Program details updated!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in update program details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllCulinaryPrograms();

        getCurrentProgramID();


    }

    public void btnDeleteOnAction(ActionEvent event) {
        String programID = txtProgramID.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        String fee = txtFee.getText();

        List<Students> students = new ArrayList<>();
        List<Admission_Coordinator> admissionCoordinators = new ArrayList<>();
        List<Admin>admins = new ArrayList<>();

        try {
            Culinary_ProgramsDTO culinaryProgramsDTO = new Culinary_ProgramsDTO(programID,programName,duration,fee,students,admissionCoordinators,admins);

            boolean isDeleted = culinaryProgramsBO.delete(culinaryProgramsDTO);

            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Program details deleted!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error in delete program details!").show();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        loadAllCulinaryPrograms();
        getCurrentProgramID();

    }

    public void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentProgramID();
loadAllCulinaryPrograms();
    }

    public void clearFields(){
        txtProgramID.setText("");
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
    }

    private void setCellValueFactory(){
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/homepage_admission_coordinator.fxml"));

        Stage stage = (Stage) this.root.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.setTitle("homepage admission coordinator");

        stage.centerOnScreen();
    }

    public void txtSearchOnAction(ActionEvent event) {
        String programID = txtProgramID.getText();

        try {
            Culinary_ProgramsDTO culinaryProgramsDTO = culinaryProgramsBO.searchByID(programID);
            if (culinaryProgramsDTO != null){
                txtProgramName.setText(culinaryProgramsDTO.getProgramName());
                txtDuration.setText(culinaryProgramsDTO.getDuration());
                txtFee.setText(culinaryProgramsDTO.getFee());
            } else {
                new Alert(Alert.AlertType.WARNING, "Culinary Program data not found!").show();
                clearFields();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void loadAllCulinaryPrograms(){
        ObservableList<CulinaryProgramsTM> obList = FXCollections.observableArrayList();

        try{
            List<Culinary_ProgramsDTO> culinaryProgramsDTOList = culinaryProgramsBO.getAll();

            for (Culinary_ProgramsDTO culinaryProgramsDTO: culinaryProgramsDTOList){
                CulinaryProgramsTM tm = new CulinaryProgramsTM(culinaryProgramsDTO.getProgramID(), culinaryProgramsDTO.getProgramName(), culinaryProgramsDTO.getDuration(), culinaryProgramsDTO.getFee());
                obList.add(tm);
            }

            tblCulinaryPrograms.setItems(obList);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtProgramIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(PROGRAMID,txtProgramID);

    }

    public void txtDurationOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(MONTH,txtDuration);
    }

    public void txtFeeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(PRICE,txtFee);
    }

    public boolean isValidStudent() {
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.PROGRAMID, txtProgramID)) return false;
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.PRICE,txtFee )) return false;
        if (!Regex.setTextColor(org.example.orm_final_corsework1.util.TextField.MONTH, txtDuration)) return false;

        return true;
    }
}
