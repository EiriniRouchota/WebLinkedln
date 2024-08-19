package com.example.RegisterLogin.EmployeeController;

import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.Service.JwtService;
import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")


public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private final JwtService jwtService;

    public EmployeeController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {

      Employee registeredUser  = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> logingEmployee(@RequestBody LoginDTO loginDTO)
    {
        Employee authenticatedUser = employeeService.authenticate(loginDTO);

        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);

        return ResponseEntity.ok(loginResponse);
    }


    @GetMapping(path= "/users")
    public ResponseEntity<List<Employee>> allUsers() {
        List<Employee> users = employeeService.allUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping(path="/users/me")
    public ResponseEntity<Employee> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Employee currentUser = (Employee) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
}
