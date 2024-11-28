package org.example.orm_final_corsework1.dao.custom;


import org.example.orm_final_corsework1.dao.CrudDAO;
import org.example.orm_final_corsework1.entity.Students;

import java.sql.SQLException;

public interface StudentsDAO extends CrudDAO<Students> {

    public Students searchByID(String studentID) throws SQLException;

    public  String getCurrentStudentID() throws SQLException;

}
