package org.example.orm_final_corsework1.bo.custom.impl;

import javafx.scene.layout.AnchorPane;
import org.example.orm_final_corsework1.bo.custom.AdminCoordinatorBO;
import org.example.orm_final_corsework1.dao.DAOFactory;
import org.example.orm_final_corsework1.dao.custom.AdminCoordinatorDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCoordinatorBOImpl implements AdminCoordinatorBO {
    AdminCoordinatorDAO admissionCoordinatorDAO = (AdminCoordinatorDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMISSION_COORDINATOR);

    @Override
    public boolean saveAdmissionCoordinator(String admissionCoUserID, String admissionCoUsername, String admissionCoPassword) throws SQLException {
        return admissionCoordinatorDAO.saveAdmissionCoordinator(admissionCoUserID,admissionCoUsername,admissionCoPassword);

    }

    @Override
    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> userIDs = admissionCoordinatorDAO.getIds();

        for (String id : userIDs) {
            idList.add(id);
        }

        return idList;
    }

    @Override
    public String getCurrentAdmissionCoordinatorID() throws SQLException {
        return  admissionCoordinatorDAO.getCurrentAdmissionCoordinatorID();

    }

    @Override
    public void checkCredential(String admissionCoUsername, String admissionCoPassword, AnchorPane root) throws SQLException, IOException {
        admissionCoordinatorDAO.checkCredential(admissionCoUsername,admissionCoPassword,root);

    }

    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {

        return admissionCoordinatorDAO.isValidUserPassword(adminUserID,adminUsername);

    }

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException {

        return admissionCoordinatorDAO.resetPassword(adminUserID, adminPassword);

    }

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {

        return admissionCoordinatorDAO.isValidUserUsername(adminUserID,adminPassword);

    }

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException {

        return admissionCoordinatorDAO.resetUsername(adminUserID, adminUsername);

    }
}
