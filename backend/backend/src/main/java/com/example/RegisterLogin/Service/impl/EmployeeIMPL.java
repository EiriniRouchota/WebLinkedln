package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.*;
import com.example.RegisterLogin.Entity.*;
import com.example.RegisterLogin.Repo.*;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.Service.JwtService;
import com.example.RegisterLogin.response.*;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeIMPL implements EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    private InstitutionRepo institutionRepo;

    private ExperienceRepo experienceRepo;

    private EducationRepo educationRepo;

    private AdsRepo adsRepo;

    private SkillRepo skillRepo;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    private final AuthenticationManager authenticationManager;

    public EmployeeIMPL(
            EmployeeRepo employeeRepo,
            InstitutionRepo institutionRepo,
            EducationRepo educationRepo,
            ExperienceRepo experienceRepo,
            SkillRepo skillRepo,
            AdsRepo adsRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.institutionRepo = institutionRepo;
        this.skillRepo = skillRepo;
        this.experienceRepo = experienceRepo;
        this.educationRepo = educationRepo;
        this.adsRepo=adsRepo;
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

    @Transactional
    public List<ExperienceResponse> addOrUpdateExperience(List<ExperienceDTO> experienceDTOS, Employee currentUser){

        experienceRepo.deleteByEmployee(currentUser);

        // List to store the responses
        List<ExperienceResponse> experienceResponses = new ArrayList<>();

        // Iterate through the list of new education DTOs
        for (ExperienceDTO experienceDTO : experienceDTOS) {

            // Create a new Experience entity
            Experience experience = new Experience();
            experience.setJobtitle(experienceDTO.getJobtitle());
            experience.setCompanyname(experienceDTO.getCompanyname());
            experience.setStartDate(experienceDTO.getStartDate());
            experience.setEndDate(experienceDTO.getEndDate());
            experience.setPublic(experienceDTO.isPublic());
            experience.setDescription(experienceDTO.getDescription());
            experience.setEmployee(currentUser);  // Associate with the current user

            // Save the experience entity
            Experience savedExperience = experienceRepo.save(experience);

            // Create a response for the saved experience
            ExperienceResponse experienceResponse = new ExperienceResponse(
                    savedExperience.isPublic(),
                    savedExperience.getStartDate(),
                    savedExperience.getEndDate(),
                    savedExperience.getDescription(),
                    savedExperience.getCompanyname(),
                    savedExperience.getJobtitle()
            );

            // Add the response to the list
            experienceResponses.add(experienceResponse);
        }

        // Return the list of ExperienceResponse objects
        return experienceResponses;
    }



    public List<ExperienceResponse> getAllExperienceForEmployee(Employee employee) {
        // Fetch all education records for the current employee
        List<Experience> experiences = experienceRepo.findByEmployee(employee);

        // Convert to a list of EducationResponse DTOs
        return experiences.stream()
                .map(this::convertToExperienceResponse)
                .collect(Collectors.toList());
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


    private ExperienceResponse convertToExperienceResponse(Experience experience) {
        ExperienceResponse response = new ExperienceResponse();
        response.setJobtitle(experience.getJobtitle());
        response.setCompanyname(experience.getCompanyname());
        response.setDescription(experience.getDescription());
        response.setStartDate(experience.getStartDate());
        response.setEndDate(experience.getEndDate());
        response.setPublic(experience.isPublic());
        return response;
    }


    ///Skills

    // Method to get all skills and convert them to SkillDTO
    public List<SkillDTO> getAllSkills() {
        return skillRepo.findAll().stream()
                .map(skill -> new SkillDTO(skill.getId(), skill.getName()))
                .collect(Collectors.toList());
    }



    public List<SkillResponse> addOrUpdateSkills(List<Integer> skillIds, Employee currentUser) {
        // Fetch skills by their IDs
        Set<Skill> skills = skillRepo.findAllById(skillIds).stream().collect(Collectors.toSet());

        // Associate the skills with the current user
        currentUser.setSkills(skills);
        employeeRepo.save(currentUser);  // Save the employee with updated skills

        // Return the updated skill list as responses
        return skills.stream()
                .map(skill -> new SkillResponse(skill.getId(), skill.getName()))
                .collect(Collectors.toList());
    }


    public List<SkillResponse> getAllSkillsForEmployee(Employee currentUser) {
        // Get the skills associated with the employee
        Set<Skill> skills = currentUser.getSkills();

        // Map the Skill entities to SkillResponse DTOs
        return skills.stream()
                .map(skill -> new SkillResponse(skill.getId(), skill.getName()))
                .collect(Collectors.toList());
    }

    public AdsDTO createJobAd(AdsDTO adsDTO, Employee currentUser) {

        Ads newAd = new Ads();
        newAd.setCompanyname(adsDTO.getCompanyname());
        newAd.setDescription(adsDTO.getDescription());
        newAd.setRemote(adsDTO.isRemote());
        newAd.setFulltime(adsDTO.isFulltime());
        newAd.setPostedDate(new Date()); // Set the current date
        newAd.setStatus(adsDTO.isStatus()); // Assuming status is true when it's created
        newAd.setEmployee(currentUser); // Link the ad with the current user

        // Save the new ad to the database
        Ads savedAd = adsRepo.save(newAd);

        // Map savedAd back to AdsDTO
        AdsDTO savedAdDTO = new AdsDTO();
        savedAdDTO.setCompanyname(savedAd.getCompanyname());
        savedAdDTO.setDescription(savedAd.getDescription());
        savedAdDTO.setRemote(savedAd.isRemote());
        savedAdDTO.setFulltime(savedAd.isFulltime());
        savedAdDTO.setPostedDate((savedAd.getPostedDate()));
        savedAdDTO.setStatus(savedAd.isStatus());

        return savedAdDTO;
    }


}
