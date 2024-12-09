package org.example.orm_final_corsework1.dao.custom;

import org.example.orm_final_corsework1.dao.CrudDAO;
import org.example.orm_final_corsework1.entity.Culinary_Programs;

import java.sql.SQLException;

public interface
Culinary_ProgramsDAO extends CrudDAO<Culinary_Programs> {

    public Culinary_Programs searchByID(String programID) throws SQLException;
    public  String getCurrentProgramID() throws SQLException;


}
