package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.response.UpdateEmployeeResponse;

import java.util.List;


public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO);

    UpdateEmployeeResponse updateEmployee(EmployeeDTO employeeDTO, Employee currentUser);

    LoginResponse loginEmployee(LoginDTO loginDTO);

    Employee authenticate(LoginDTO loginDTO);

    List<Employee> allUsers();

}
