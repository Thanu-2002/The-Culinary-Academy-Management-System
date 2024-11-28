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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

public class AdminRegisterFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    private AnchorPane node;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblAdminId;

    @FXML
    private TextField txtUserName;

    AdminRegisterBo adminRegisterBo = (AdminRegisterBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        getCurrentAdminID();
    }

    private void getCurrentAdminID() {
        try {

            String currentAdminID = adminRegisterBo.getCurrentAdminID();

            String nextAdminID = generateNextAdminID(currentAdminID);

            lblAdminId.setText(nextAdminID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextAdminID(String currentAdminID) {

        if (currentAdminID != null) {
            String[] split = currentAdminID.split("A");
            int idNum = Integer.parseInt(split[1]);
            if (idNum < 99) {
                idNum++;
                return "A" + String.format("%03d", idNum);
            } else {
                return "Error: Maximum User ID reached (AC999)";
            }
        }
        return "A001";

    }

    @FXML
    void RegisterbtnOnAction(ActionEvent event) {
        String adminId = lblAdminId.getText();
        String userName = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String adminPassword = passwordEncoder.encode(password);

        try{
            boolean isSaved =saveAdmin(adminId,userName,email,adminPassword);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Admin Registration Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Admin Registration Failed").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    private boolean saveAdmin(String adminUserID,String adminUsername,String Email,String adminPassword) throws SQLException {
        return adminRegisterBo.saveAdmin(adminUserID,adminUsername, Email,adminPassword);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/AdminLogin_form.fxml"));
        Stage stage =(Stage) this.node.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Admin Login");
        stage.centerOnScreen();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
