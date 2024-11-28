package org.example.orm_final_corsework1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.AdminCoordinatorBO;
import org.example.orm_final_corsework1.bo.custom.ResetUsernamePasswordAdminBO;

import java.io.IOException;
import java.sql.SQLException;

public class CoordinatorSettingForm_Controller {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnResetPassword;

    @FXML
    private Button btnResetUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUsername;
    @FXML
    private AnchorPane root;

    AdminCoordinatorBO adminCoordinatorBO= (AdminCoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMISSION_COORDINATOR);


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) {
        String adminUserIDs = txtUserID.getText();
        String adminUsernames = txtUsername.getText();
        String adminPasswords = txtPassword.getText();

        try {
            if (isValidUserPassword(adminUserIDs,adminUsernames)){
                boolean isReset = resetPassword(adminUserIDs,adminPasswords);
                if (isReset){
                    new Alert(Alert.AlertType.CONFIRMATION,"Password reset Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Password reset Unsuccessful!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid User ID or Username!").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Error occurred while resetting password"+e.getMessage()).show();
        }

    }

    private boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {

        return adminCoordinatorBO.isValidUserPassword(adminUserID,adminUsername);

    }

    private boolean resetPassword(String adminUserID,String adminPassword) throws SQLException {

        return adminCoordinatorBO.resetPassword(adminUserID, adminPassword);

    }


    @FXML
    void btnResetUserNameOnAction(ActionEvent event) {
        String adminUserID = txtUserID.getText();
        String adminUsername = txtUsername.getText();
        String adminPassword = txtPassword.getText();

        try {
            if (isValidUserUsername(adminUserID,adminPassword)){
                boolean isReset = resetUsername(adminUserID,adminUsername);
                if (isReset){
                    new Alert(Alert.AlertType.CONFIRMATION,"Username reset Successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Username reset Unsuccessful!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid User ID or Password!").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Error occurred while resetting username"+e.getMessage()).show();
        }
    }

    private boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {

        return adminCoordinatorBO.isValidUserUsername(adminUserID,adminPassword);

    }

    private boolean resetUsername(String adminUserID,String adminUsername) throws SQLException {

        return adminCoordinatorBO.resetUsername(adminUserID, adminUsername);

    }


}
