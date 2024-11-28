package org.example.orm_final_corsework1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/welcome.fxml"))));
        stage.setTitle("Welcome Page");
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[]args){
        launch(args);
    }

}
