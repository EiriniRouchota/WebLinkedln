package com.example.RegisterLogin.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class EmployeeDTO {

    private int employeeid;
    private String employeename;
    private String email;
    private String password;
    private String employeelastname;
    private String phone;
    private String newPassword;
    private List<EducationDTO> educations;  // Optional, if you want to include education details    // Constructor with parameters
    public EmployeeDTO(int employeeid, String employeename, String email, String password, String employeelastname, String phone,List<EducationDTO> educations) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.password = password;
        this.employeelastname = employeelastname;
        this.phone = phone;
        this.educations = educations;
    }


    public EmployeeDTO() {}

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeelastname() {
        return employeelastname;
    }

    public void setEmployeelastname(String employeelastname) {
        this.employeelastname = employeelastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", employeelastname='" + employeelastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
