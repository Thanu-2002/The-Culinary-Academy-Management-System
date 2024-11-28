package org.example.orm_final_corsework1.dao.custom;

import org.example.orm_final_corsework1.dao.CrudDAO;
import org.example.orm_final_corsework1.entity.ResetUsernamePasswordAdmin;

import java.sql.SQLException;

public interface ResetUsernamePasswordAdminDAO extends CrudDAO<ResetUsernamePasswordAdmin> {
    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException;

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException;

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException;

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException;
}
