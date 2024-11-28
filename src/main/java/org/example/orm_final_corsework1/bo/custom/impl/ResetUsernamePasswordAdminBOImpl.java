package org.example.orm_final_corsework1.bo.custom.impl;

import org.example.orm_final_corsework1.bo.custom.ResetUsernamePasswordAdminBO;
import org.example.orm_final_corsework1.dao.DAOFactory;
import org.example.orm_final_corsework1.dao.custom.ResetUsernamePasswordAdminDAO;

import java.sql.SQLException;

public class ResetUsernamePasswordAdminBOImpl implements ResetUsernamePasswordAdminBO {

    ResetUsernamePasswordAdminDAO resetUsernamePasswordAdminDAO = (ResetUsernamePasswordAdminDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ADMIN_RESET);



    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminDAO.isValidUserPassword(adminUserID,adminUsername);

    }

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminDAO.resetPassword(adminUserID, adminPassword);

    }

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException {

        return resetUsernamePasswordAdminDAO.isValidUserUsername(adminUserID,adminPassword);

    }

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException {

        return resetUsernamePasswordAdminDAO.resetUsername(adminUserID, adminUsername);

    }

}
