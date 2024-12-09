package org.example.orm_final_corsework1.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = switch (textField) {
            case NAME -> "^[A-Za-z]+(?: [A-Za-z]+)*$";
            case ADDRESS -> "^[A-Za-z0-9\\s,.-]+$";
            case TEL -> "^[0]([1-9]{2})([0-9]){7}$";
            case MONTH -> "^[0-9]{1,5}$";
            case PRICE -> "^\\d+(\\.\\d{1,2})?$";
            case STUDENTID -> "^ST\\d{4}$";
            case PROGRAMID -> "^CA\\d{4}$";
            case EMAIL -> "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        };

        Pattern pattern = Pattern.compile(filed);

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
    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-border-color: transparent; -fx-border-radius: 0; -fx-border-width: 0; -fx-focus-color: transparent;");
            return true;
        } else {
            textField.setStyle("-fx-border-color: red; -fx-border-radius: 5; -fx-border-width: 3;");
            return false;
        }
    }

}
