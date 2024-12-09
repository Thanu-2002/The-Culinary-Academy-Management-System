package org.example.orm_final_corsework1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Admin {

    @Id
    private String adminUserID;

    private String adminUsername;

    private String adminEmail;

    private String adminPassword;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

    public Admin() {
    }

    public String getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(String adminUserID) {
        this.adminUserID = adminUserID;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
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

    public Admin(String adminUserID, String adminUsername, String adminEmail, String adminPassword, Students students, Culinary_Programs culinaryPrograms) {
        this.adminUserID = adminUserID;
        this.adminUsername = adminUsername;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.students = students;
        this.culinaryPrograms = culinaryPrograms;
    }
}