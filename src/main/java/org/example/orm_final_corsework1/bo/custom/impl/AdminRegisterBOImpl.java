package org.example.orm_final_corsework1.bo.custom.impl;

import javafx.scene.layout.AnchorPane;
import org.example.orm_final_corsework1.bo.custom.AdminRegisterBo;
import org.example.orm_final_corsework1.dao.DAOFactory;
import org.example.orm_final_corsework1.dao.custom.AdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRegisterBOImpl implements AdminRegisterBo {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMIN);

    public boolean saveAdmin(String adminUserID,String adminUsername,String Email,String adminPassword)throws SQLException {
        return adminDAO.saveAdmin(adminUserID,adminUsername,Email,adminPassword);
    }

    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> userIDs = adminDAO.getIds();

        for (String id : userIDs) {
            idList.add(id);
        }

        return idList;
    }

    public  String getCurrentAdminID() throws SQLException {

        return  adminDAO.getCurrentAdminID();

    }

    public void checkCredential(String adminUsername, String adminPassword, AnchorPane root) throws SQLException, IOException {
        adminDAO.checkCredential(adminUsername,adminPassword,root);
    }


}
