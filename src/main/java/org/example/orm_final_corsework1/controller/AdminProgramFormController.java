package org.example.orm_final_corsework1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.example.orm_final_corsework1.tm.CulinaryProgramsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminProgramFormController {

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CulinaryProgramsTM> tblCulinaryPrograms;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramID;

    @FXML
    private TextField txtProgramName;

    Culinary_programBO culinaryProgramsBO = (Culinary_programBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.CULINARY_PROGRAM);

    public void initialize(){
        setCellValueFactory();
        loadAllCulinaryPrograms();
    }

    private void setCellValueFactory(){
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
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

    public void clearFields(){
        txtProgramID.setText("");
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
    }


    @FXML
    void txtProgramIDOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
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

}
