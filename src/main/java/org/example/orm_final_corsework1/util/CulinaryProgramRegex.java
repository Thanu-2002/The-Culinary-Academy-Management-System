package org.example.orm_final_corsework1.util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CulinaryProgramRegex {
    public static boolean setTextColour(CulinaryProgramTextField location, TextField textField){


        if (CulinaryProgramRegex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-background-color: SpringGreen");
            // Set unfocus color to white
            textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) { // When the TextField loses focus
                    textField.setStyle("-fx-background-color: White");
                }
            });
            return true;
        } else {
            textField.setStyle("-fx-background-color: Tomato");
            // Set unfocus color to white
            textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) { // When the TextField loses focus
                    textField.setStyle("-fx-background-color: White");
                }
            });
            return false;
        }


    }

    private static boolean isTextFieldValid(CulinaryProgramTextField textField, String text) {

        String filled = "";

        switch (textField){

            case PROGRAM_ID :
                filled = "^CA[1-9]\\d{3}$";
                break;

            case DURATION:
                filled = "^(1[0-2]|[1-9]) (month|months|year|years)$";
                break;

            case FEE:
                filled = "^\\d{1,3}(,\\d{3})*(\\.\\d{2})?$";

        }

        Pattern pattern = Pattern.compile(filled);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }

        return false;


    }
}
