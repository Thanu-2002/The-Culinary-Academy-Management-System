package org.example.orm_final_corsework1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeFormController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnCoordinator;

    @FXML
    private AnchorPane rootnode;

    @FXML
    void AdmintbtnOnAction(ActionEvent event) throws IOException, IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/AdminLogin_form.fxml"));
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Admin Login");
        stage.centerOnScreen();
    }

    @FXML
    void CoordinatorbtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/CoordinatorLogin_form.fxml"));
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Cordinator Login");
        stage.centerOnScreen();
    }

}
