package com.example.RegisterLogin.response;

import com.example.RegisterLogin.Entity.Employee;

public class UpdateEmployeeResponse {
    private String message;
    private Employee employee;

    // Constructor
    public UpdateEmployeeResponse(String message, Employee employee) {
        this.message = message;
        this.employee = employee;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
