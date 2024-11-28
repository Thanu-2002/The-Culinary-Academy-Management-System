package org.example.orm_final_corsework1.bo.custom;

import org.example.orm_final_corsework1.bo.SuperBO;
import org.example.orm_final_corsework1.dto.Culinary_ProgramsDTO;

import java.sql.SQLException;
import java.util.List;

public interface Culinary_programBO extends SuperBO {

    public boolean add(Culinary_ProgramsDTO dto) throws SQLException;

    public boolean update(Culinary_ProgramsDTO dto) throws SQLException;

    public boolean delete(Culinary_ProgramsDTO dto) throws SQLException;

    public List<Culinary_ProgramsDTO> getAll() throws SQLException;

    public Culinary_ProgramsDTO searchByID(String programID) throws SQLException;

    public List<String> getIds() throws SQLException;
}
