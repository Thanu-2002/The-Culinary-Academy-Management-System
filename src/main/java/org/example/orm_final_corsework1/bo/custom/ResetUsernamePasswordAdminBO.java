package org.example.orm_final_corsework1.bo.custom;

import org.example.orm_final_corsework1.bo.SuperBO;

import java.sql.SQLException;

public interface ResetUsernamePasswordAdminBO extends SuperBO {
    public boolean isValidUserPassword(String adminUserID, String adminUsername) throws SQLException;

    public boolean resetPassword(String adminUserID,String adminPassword) throws SQLException;

    public boolean isValidUserUsername(String adminUserID, String adminPassword) throws SQLException;

    public boolean resetUsername(String adminUserID,String adminUsername) throws SQLException;
}