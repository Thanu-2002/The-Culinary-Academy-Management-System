package org.example.orm_final_corsework1.dao.custom;

import javafx.scene.layout.AnchorPane;
import org.example.orm_final_corsework1.dao.CrudDAO;
import org.example.orm_final_corsework1.entity.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin> {
    public boolean saveAdmin(String adminUserID,String adminUsername,String Email,String adminPassword)throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdminID() throws SQLException;

    public void checkCredential(String adminUsername, String adminPassword,AnchorPane root) throws SQLException, IOException;

}
