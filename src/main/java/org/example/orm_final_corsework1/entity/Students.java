package org.example.orm_final_corsework1.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Students {

    @Id
    private String studentID;

    private Date date;

    private String firstName;

    private String lastName;

    private String email;

    private int mobileNumber;

    private String address;

    private String selectedCourse;

    @ManyToOne (cascade = CascadeType.PERSIST)// owners end
    @JoinColumn(name = "Program_ID")
    private Culinary_Programs culinaryPrograms;

    @OneToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //inverse end to Admission Coordinator Entity
    private List<Admission_Coordinator> admissionCoordinators;

    @OneToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // inverse end to Admin Entity
    private List<Admin> admins;

    public Students() {
    }

    public Students(String studentID, Date date, String firstName, String lastName, String email, int mobileNumber, String address, String selectedCourse, Culinary_Programs culinaryPrograms, List<Admission_Coordinator> admissionCoordinators, List<Admin> admins) {
        this.studentID = studentID;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.selectedCourse = selectedCourse;
        this.culinaryPrograms = culinaryPrograms;
        this.admissionCoordinators = admissionCoordinators;
        this.admins = admins;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public Culinary_Programs getCulinaryPrograms() {
        return culinaryPrograms;
    }

    public void setCulinaryPrograms(Culinary_Programs culinaryPrograms) {
        this.culinaryPrograms = culinaryPrograms;
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
