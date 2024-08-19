package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.Service.JwtService;
import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public EmployeeIMPL(
            EmployeeRepo employeeRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public Employee authenticate(LoginDTO input) {
        try {
            // Attempt to authenticate using the authenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
            );

            // Fetch the employee if authentication is successful
            return employeeRepo.findByEmail(input.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + input.getEmail()));

        } catch (AuthenticationException e) {
            // Handle the authentication failure case
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeRepo.findByEmail(employeeDTO.getEmail());

        if (existingEmployee.isPresent()) {
            return null;
        }

        Employee employee = new Employee(
                employeeDTO.getEmployeename(),
                employeeDTO.getEmployeelastname(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword()),
                employeeDTO.getPhone()
        );

        return employeeRepo.save(employee);

    }

    public List<Employee> allUsers() {
        List<Employee> users = new ArrayList<>();

        employeeRepo.findAll().forEach(users::add);

        return users;
    }


    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        try {
            // Authenticate using the authenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            // Retrieve the employee from the repository
            Employee employee = employeeRepo.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + loginDTO.getEmail()));

            // Generate JWT token (assuming you have a jwtService to handle this)
            String jwtToken = jwtService.generateToken(employee);
            long expirationTime = jwtService.getExpirationTime();

            // Return a successful LoginResponse with the token and expiration time
            return new LoginResponse("Login Success", true)
                    .setToken(jwtToken)
                    .setExpiresIn(expirationTime);

        } catch (AuthenticationException e) {
            // Handle the authentication failure case
            return new LoginResponse("Login Failed: " + e.getMessage(), false);
        }
    }
}
