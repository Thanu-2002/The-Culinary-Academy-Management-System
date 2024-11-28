package org.example.orm_final_corsework1.bo;

import org.example.orm_final_corsework1.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes{
        CULINARY_PROGRAM,STUDENTS,ADMISSION_COORDINATOR,ADMIN,ADMISSION_COORDINATOR_SIGN_IN,ADMIN_RESET,RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR
    }

    public SuperBO getBo(BOTypes types){
        switch (types){
            case ADMIN:
                return new AdminRegisterBOImpl();
            case ADMIN_RESET:
                return new ResetUsernamePasswordAdminBOImpl();
            case ADMISSION_COORDINATOR:
                return new AdminCoordinatorBOImpl();
            case CULINARY_PROGRAM:
                return new Culinary_programBOImpl();
            case STUDENTS:
                return new StudentsBOImpl();


            default:
                return null;
        }
    }

}
