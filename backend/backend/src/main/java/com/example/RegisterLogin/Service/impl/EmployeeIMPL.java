package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.EducationDTO;
import com.example.RegisterLogin.Dto.InstitutionDTO;
import com.example.RegisterLogin.Dto.LoginDTO;
import com.example.RegisterLogin.Entity.Education;
import com.example.RegisterLogin.Entity.Institution;
import com.example.RegisterLogin.Repo.EducationRepo;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.InstitutionRepo;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.Service.JwtService;
import com.example.RegisterLogin.response.EducationResponse;
import com.example.RegisterLogin.response.LoginResponse;
import com.example.RegisterLogin.response.UpdateEmployeeResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeIMPL implements EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    private InstitutionRepo institutionRepo;

    private EducationRepo educationRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public EmployeeIMPL(
            EmployeeRepo employeeRepo,
            InstitutionRepo institutionRepo,
            EducationRepo educationRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.institutionRepo = institutionRepo;
        this.educationRepo = educationRepo;
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
    public UpdateEmployeeResponse updateEmployee(EmployeeDTO employeeDTO, Employee currentUser) {
        // Check if the current password is correct
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String manuallyHashedPassword = passwordEncoder.encode(employeeDTO.getPassword());
        System.out.println("Manually hashed password: " + manuallyHashedPassword);

        if (!passwordEncoder.matches(employeeDTO.getPassword(), currentUser.getPassword())) {


            return new UpdateEmployeeResponse("Your password is incorrect", null);
        }

        // Check if the new email is already in use by another user (excluding the current user)
        Optional<Employee> existingEmployee = employeeRepo.findByEmail(employeeDTO.getEmail());

        if (existingEmployee.isPresent() && !existingEmployee.get().getEmployeeid().equals(currentUser.getEmployeeid())) {
            return new UpdateEmployeeResponse("Email is already in use. Try again with a different email.", null);
        }



        currentUser.setEmail(employeeDTO.getEmail());
        // Update password if a new one is provided
        if (employeeDTO.getNewPassword() != null && !employeeDTO.getNewPassword().isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(employeeDTO.getNewPassword()));
        }


        // Save the updated employee back to the repository
        Employee updatedEmployee = employeeRepo.save(currentUser);

        return new UpdateEmployeeResponse("Profile updated successfully.", updatedEmployee);
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

    @Transactional
    @Override
    public List<EducationResponse> addOrUpdateEducations(List<EducationDTO> educationDTOs, Employee currentUser) {
        // Delete all existing educations for the current user
        educationRepo.deleteByEmployee(currentUser);

        List<EducationResponse> savedEducations = new ArrayList<>();

        // Iterate through the list of new education DTOs
        for (EducationDTO educationDTO : educationDTOs) {
            // Find the institution by ID
            Institution institution = institutionRepo.findById(educationDTO.getInstitutionId()).orElseThrow(
                    () -> new RuntimeException("Institution not found.")
            );

            // Create a new Education entity
            Education education = new Education();
            education.setDegree(educationDTO.getDegree());
            education.setStartDate(educationDTO.getStartDate());
            education.setEndDate(educationDTO.getEndDate());
            education.setPublic(educationDTO.isPublic());
            education.setInstitution(institution);
            education.setEmployee(currentUser);  // Associate with the current user

            // Save the education entity
            Education savedEducation = educationRepo.save(education);

            // Create and add a response for each saved education
            savedEducations.add(new EducationResponse(
                    savedEducation.isPublic(),
                    savedEducation.getStartDate(),
                    savedEducation.getEndDate(),
                    savedEducation.getDegree(),
                    savedEducation.getInstitution().getName()

            ));
        }

        // Return the list of saved educations
        return savedEducations;
    }


    public List<InstitutionDTO> getAllInstitutions() {
        return institutionRepo.findAll().stream()
                .map(institution -> new InstitutionDTO(
                        institution.getId(),
                        institution.getName(),
                        institution.getLocation()
                ))
                .collect(Collectors.toList());
    }

    public List<EducationResponse> getAllEducationsForEmployee(Employee employee) {
        // Fetch all education records for the current employee
        List<Education> educations = educationRepo.findByEmployee(employee);

        // Convert to a list of EducationResponse DTOs
        return educations.stream()
                .map(this::convertToEducationResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert Education entity to EducationResponse DTO
    private EducationResponse convertToEducationResponse(Education education) {
        EducationResponse response = new EducationResponse();
        response.setDegree(education.getDegree());
        response.setInstitutionId(education.getInstitution().getId());
        response.setInstitutionName(education.getInstitution().getName());
        response.setStartDate(education.getStartDate());
        response.setEndDate(education.getEndDate());
        response.setPublic(education.isPublic());
        return response;
    }
}
