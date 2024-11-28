package org.example.orm_final_corsework1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Culinary_Programs {

    @Id
    private String programID;

    private String programName;

    private String duration;

    private String fee;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) //inverse end
    private List<Students> students;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //inverse end to Admission Coordinator Entity
    private List<Admission_Coordinator> admissionCoordinators;

    @OneToMany(mappedBy = "culinaryPrograms", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // inverse end to Admin Entity
    private List<Admin> admins;

    public Culinary_Programs() {
    }

    public Culinary_Programs(String programID, String programName, String duration, String fee, List<Students> students, List<Admission_Coordinator> admissionCoordinators, List<Admin> admins) {
        this.programID = programID;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.students = students;
        this.admissionCoordinators = admissionCoordinators;
        this.admins = admins;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public List<Admission_Coordinator> getAdmissionCoordinators() {
        return admissionCoordinators;
    }

    public void setAdmissionCoordinators(List<Admission_Coordinator> admissionCoordinators) {
        this.admissionCoordinators = admissionCoordinators;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
}
