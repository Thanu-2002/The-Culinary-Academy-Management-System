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

import java.io.IOException;
import java.sql.SQLException;

public class CoordinatorLoginController {

    @FXML
    private Hyperlink LinkSignup;

    @FXML
    private TextField txtPasswordVisible;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkForgetPassword;

    @FXML
    private CheckBox chkShowPassword;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    AdminCoordinatorBO admissionCoordinatorBO = (AdminCoordinatorBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMISSION_COORDINATOR);

    public void initialize() {
        txtPasswordVisible.setVisible(false);
        if (txtPassword != null && txtPasswordVisible != null) {
            txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());
        }else {
            txtPasswordVisible.setVisible(false);
            txtPassword.setVisible(true);
        }
    }

    @FXML
    void togglePasswordVisibility(ActionEvent event) {
        if (chkShowPassword.isSelected()) {
            txtPasswordVisible.setVisible(true);
            txtPassword.setVisible(false);
        } else {
            txtPasswordVisible.setVisible(false);
            txtPassword.setVisible(true);
        }
    }

    @FXML
    void LinkSignupOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/CoordinatorRegister_form.fxml"));
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Coordinator Signup");
        stage.centerOnScreen();
    }

    @FXML
    void LoginbtnOnAction(ActionEvent event)throws IOException {
        String admissionCoUsername = txtUserName.getText();
        String admissionCoPassword = txtPassword.getText();

        try{
            checkCredential(admissionCoUsername,admissionCoPassword,rootnode);
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void checkCredential(String admissionCoUsername,String admissionCoPassword,AnchorPane root) throws SQLException, IOException {
        admissionCoordinatorBO.checkCredential(admissionCoUsername,admissionCoPassword,root);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/Welcome.fxml"));
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Welcome");
        stage.centerOnScreen();
    }

    @FXML
    void linkforgetPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
