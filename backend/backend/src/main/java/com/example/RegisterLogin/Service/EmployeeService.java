package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.response.LoginResponse;

import java.util.List;


public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO);

    LoginResponse loginEmployee(LoginDTO loginDTO);

    Employee authenticate(LoginDTO loginDTO);

    List<Employee> allUsers();
}
