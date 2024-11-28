package org.example.orm_final_corsework1.bo.custom;


import org.example.orm_final_corsework1.bo.SuperBO;
import org.example.orm_final_corsework1.dto.StudentsDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentsBO extends SuperBO {

    public boolean add(StudentsDTO dto) throws SQLException;

    public boolean update(StudentsDTO dto) throws SQLException;

    public boolean delete(StudentsDTO dto) throws SQLException;

    public List<StudentsDTO> getAll() throws SQLException;

    public StudentsDTO searchByID(String studentID) throws SQLException;

    public List<String> getIds() throws SQLException;

    public  String getCurrentStudentID() throws SQLException;

}
