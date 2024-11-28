package org.example.orm_final_corsework1.bo.custom.impl;

import org.example.orm_final_corsework1.bo.custom.Culinary_programBO;
import org.example.orm_final_corsework1.dao.DAOFactory;
import org.example.orm_final_corsework1.dao.custom.Culinary_ProgramsDAO;
import org.example.orm_final_corsework1.dto.Culinary_ProgramsDTO;
import org.example.orm_final_corsework1.entity.Culinary_Programs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Culinary_programBOImpl implements Culinary_programBO {


    Culinary_ProgramsDAO culinaryProgramsDAO = (Culinary_ProgramsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.CULINARY_PROGRAM);

    public boolean add(Culinary_ProgramsDTO dto) throws SQLException {

        Culinary_Programs culinaryPrograms = new Culinary_Programs(dto.getProgramID(), dto.getProgramName(), dto.getDuration(), dto.getFee(),dto.getStudents(),dto.getAdmissionCoordinators(),dto.getAdmins());
        return culinaryProgramsDAO.add(culinaryPrograms);

    }

    public boolean update(Culinary_ProgramsDTO dto) throws SQLException {

        Culinary_Programs culinaryPrograms = new Culinary_Programs(dto.getProgramID(), dto.getProgramName(), dto.getDuration(), dto.getFee(),dto.getStudents(),dto.getAdmissionCoordinators(),dto.getAdmins());
        return culinaryProgramsDAO.update(culinaryPrograms);

    }

    public boolean delete(Culinary_ProgramsDTO dto) throws SQLException {

        Culinary_Programs culinaryPrograms = new Culinary_Programs(dto.getProgramID(), dto.getProgramName(), dto.getDuration(), dto.getFee(),dto.getStudents(),dto.getAdmissionCoordinators(),dto.getAdmins());
        return culinaryProgramsDAO.delete(culinaryPrograms);

    }

    public List<Culinary_ProgramsDTO> getAll() throws SQLException {


        List<Culinary_ProgramsDTO>culinaryProgramsDTOList=new ArrayList<>();

        List<Culinary_Programs>culinaryProgramsList=culinaryProgramsDAO.getAll();

        for(Culinary_Programs culinaryPrograms:culinaryProgramsList){
            culinaryProgramsDTOList.add(new Culinary_ProgramsDTO(culinaryPrograms.getProgramID(), culinaryPrograms.getProgramName(), culinaryPrograms.getDuration(), culinaryPrograms.getFee(), culinaryPrograms.getStudents(),culinaryPrograms.getAdmissionCoordinators(),culinaryPrograms.getAdmins()));
        }

        return culinaryProgramsDTOList;

    }

    public Culinary_ProgramsDTO searchByID(String programID) throws SQLException {
        Culinary_Programs culinaryPrograms = culinaryProgramsDAO.searchByID(programID);
        if (culinaryPrograms != null) {

            return new Culinary_ProgramsDTO(culinaryPrograms.getProgramID(), culinaryPrograms.getProgramName(), culinaryPrograms.getDuration(), culinaryPrograms.getFee(), culinaryPrograms.getStudents(),culinaryPrograms.getAdmissionCoordinators(),culinaryPrograms.getAdmins());
        }
        return null;
    }

    public List<String> getIds() throws SQLException {
        List<String> idList = new ArrayList<>();
        List<String> programIDs = culinaryProgramsDAO.getIds();

        for (String id : programIDs) {
            idList.add(id);
        }

        return idList;
    }

}
