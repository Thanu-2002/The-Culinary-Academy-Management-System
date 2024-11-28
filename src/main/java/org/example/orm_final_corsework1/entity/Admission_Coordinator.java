package org.example.orm_final_corsework1.entity;

import jakarta.persistence.*;

@Entity
public class Admission_Coordinator {

    @Id
    private String admissionCoUserID;

    private String admissionCoUsername;

    private String admissionCoPassword;

    @ManyToOne(cascade = CascadeType.PERSIST)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.PERSIST) // owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

    public Admission_Coordinator() {
    }

    public Admission_Coordinator(String admissionCoUserID, String admissionCoUsername, String admissionCoPassword, Students students, Culinary_Programs culinaryPrograms) {
        this.admissionCoUserID = admissionCoUserID;
        this.admissionCoUsername = admissionCoUsername;
        this.admissionCoPassword = admissionCoPassword;
        this.students = students;
        this.culinaryPrograms = culinaryPrograms;
    }

    public String getAdmissionCoUserID() {
        return admissionCoUserID;
    }

    public void setAdmissionCoUserID(String admissionCoUserID) {
        this.admissionCoUserID = admissionCoUserID;
    }

    public String getAdmissionCoUsername() {
        return admissionCoUsername;
    }

    public void setAdmissionCoUsername(String admissionCoUsername) {
        this.admissionCoUsername = admissionCoUsername;
    }

    public String getAdmissionCoPassword() {
        return admissionCoPassword;
    }

    public void setAdmissionCoPassword(String admissionCoPassword) {
        this.admissionCoPassword = admissionCoPassword;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Culinary_Programs getCulinaryPrograms() {
        return culinaryPrograms;
    }

    public void setCulinaryPrograms(Culinary_Programs culinaryPrograms) {
        this.culinaryPrograms = culinaryPrograms;
    }
}
