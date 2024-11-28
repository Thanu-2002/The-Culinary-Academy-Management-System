package org.example.orm_final_corsework1.bo.custom;

import javafx.scene.layout.AnchorPane;
import org.example.orm_final_corsework1.bo.SuperBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminCoordinatorBO extends SuperBO {

    public boolean saveAdmissionCoordinator(String admissionCoUserID,String admissionCoUsername,String admissionCoPassword) throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdmissionCoordinatorID() throws SQLException;


    public void checkCredential(String admissionCoUsername, String admissionCoPassword, AnchorPane root) throws SQLException, IOException;


    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException;

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException;

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException;

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException;
}
