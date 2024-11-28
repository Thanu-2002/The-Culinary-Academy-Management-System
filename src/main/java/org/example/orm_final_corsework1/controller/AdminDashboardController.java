package org.example.orm_final_corsework1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnStudent;

    @FXML
    private AnchorPane rootnode;

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/adminDashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.rootnode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/Welcome.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.rootnode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    @FXML
    void btnProgrameOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/adminProgram_form.fxml"));
        rootnode.getChildren().clear();
        rootnode.getChildren().add(anchorPane);
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Setting_form.fxml"));
        rootnode.getChildren().clear();
        rootnode.getChildren().add(anchorPane);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AdminStudent_form.fxml"));
        rootnode.getChildren().clear();
        rootnode.getChildren().add(anchorPane);
    }

}
