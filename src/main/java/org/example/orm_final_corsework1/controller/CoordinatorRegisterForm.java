package org.example.orm_final_corsework1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.AdminCoordinatorBO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

public class CoordinatorRegisterForm {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblAdminId;

    @FXML
    private PasswordField txtPassword;


    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUserName;

    AdminCoordinatorBO admissionCoordinatorBO = (AdminCoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMISSION_COORDINATOR);


    public void initialize(){
        getCurrentAdmissionCoordinatorID();
    }

    private void getCurrentAdmissionCoordinatorID() {
        try {

            String currentAdmissionCoordinatorID = admissionCoordinatorBO.getCurrentAdmissionCoordinatorID();

            String nextAdmissionCoordinatorID = generateNextAdmissionCoordinatorID(currentAdmissionCoordinatorID);

            lblAdminId.setText(nextAdmissionCoordinatorID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextAdmissionCoordinatorID(String currentAdmissionCoordinatorID) {

        if (currentAdmissionCoordinatorID != null) {
            String[] split = currentAdmissionCoordinatorID.split("AC");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 99) {
                idNum++;
                return "AC" + String.format("%03d", idNum);
            } else {
                return "Error: Maximum User ID reached (AC999)";
            }
        }
        return "AC001";

    }



    @FXML
    void RegisterbtnOnAction(ActionEvent event) throws IOException {
        String admissionCoUserID = lblAdminId.getText();
        String admissionCoUsername = txtUserName.getText();
        String passwordWithoutEncrypt = txtPassword.getText();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admissionCoPassword =passwordEncoder.encode(passwordWithoutEncrypt);

        try{
            boolean isSaved = saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "AdmissionCoordinator Account Saved! Before exit from this page Make sure you have saved or write down these credentials! ").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword) throws SQLException {

        return admissionCoordinatorBO.saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);

    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/CoordinatorLogin_form.fxml"));
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Welcome");
        stage.centerOnScreen();
    }



    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
