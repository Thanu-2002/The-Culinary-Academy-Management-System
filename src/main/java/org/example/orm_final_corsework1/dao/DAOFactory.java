package org.example.orm_final_corsework1.dao;

import org.example.orm_final_corsework1.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        CULINARY_PROGRAM,STUDENTS,ADMISSION_COORDINATOR,ADMIN,ADMIN_RESET
    }

    public SuperDAO getDao(DAOTypes types){
        switch (types){
            case CULINARY_PROGRAM:
                return new Culinary_ProgramsDAOImpl();

            case ADMIN:
                return new AdminDAOImpl();

            case ADMIN_RESET:
                return new ResetUsernamePasswordAdminDAOImpl();

            case ADMISSION_COORDINATOR:
                return new AdminCoordinatorDAOImpl();

            case STUDENTS:
                return new StudentsDAOImpl();
            default:
                return null;
        }
    }

}
