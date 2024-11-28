package org.example.orm_final_corsework1.bo.custom;

import javafx.scene.layout.AnchorPane;
import org.example.orm_final_corsework1.bo.SuperBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminRegisterBo extends SuperBO {

    public boolean saveAdmin(String adminUserID,String adminUsername,String Email,String adminPassword)throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentAdminID() throws SQLException;

    public void checkCredential(String adminUsername, String adminPassword, AnchorPane root) throws SQLException, IOException;

}
