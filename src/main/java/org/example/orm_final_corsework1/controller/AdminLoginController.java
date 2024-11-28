package org.example.orm_final_corsework1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.orm_final_corsework1.bo.BOFactory;
import org.example.orm_final_corsework1.bo.custom.AdminRegisterBo;

import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox chkShowPassword;

    @FXML
    private Hyperlink linkForgetPassword;

    @FXML
    private Hyperlink linkSignup;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public TextField txtPasswordVisible;


    @FXML
    private TextField txtUserName;

    AdminRegisterBo adminRegisterBo = (AdminRegisterBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMIN);


    public void initialize() {
        txtPasswordVisible.setVisible(false);
        if (txtPassword != null && txtPasswordVisible != null) {
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());
        }
    }


    @FXML
    void LinkforgetPasswordOnAction(ActionEvent event) {

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
    void btnLoginOnAction(ActionEvent event) {
        String adminUsername = txtUserName.getText();
        //String passwordWithoutEncrypt = txtPassword.getText();
        String adminPassword = txtPassword.getText();

        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String adminPassword =passwordEncoder.encode(passwordWithoutEncrypt);*/

        try{
            checkCredential(adminUsername,adminPassword,rootnode);
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void checkCredential(String adminUsername,String adminPassword,AnchorPane root) throws SQLException, IOException {
        adminRegisterBo.checkCredential(adminUsername,adminPassword,root);
    }

    @FXML
    void linkSignUpOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/AdminRegister_from.fxml"));
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Cordinator Login");
        stage.centerOnScreen();
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
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
