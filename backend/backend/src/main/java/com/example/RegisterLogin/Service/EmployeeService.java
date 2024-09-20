package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.Dto.*;
import com.example.RegisterLogin.Entity.Education;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.response.*;
import jakarta.transaction.Transactional;


import java.util.List;


public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO);

    UpdateEmployeeResponse updateEmployee(EmployeeDTO employeeDTO, Employee currentUser);

    LoginResponse loginEmployee(LoginDTO loginDTO);

    Employee authenticate(LoginDTO loginDTO);

    List<Employee> allUsers();

    @Transactional
        // This ensures the delete and save operations are performed in a transaction
    List<EducationResponse> addOrUpdateEducations(List<EducationDTO> educationDTOs, Employee currentUser);

    List<ExperienceResponse> addOrUpdateExperience(List<ExperienceDTO> experienceDTOs, Employee currentUser);

    List<InstitutionDTO> getAllInstitutions();

    List<SkillDTO> getAllSkills();


    List<ExperienceResponse> getAllExperienceForEmployee(Employee employee);
    List<SkillResponse> addOrUpdateSkills(List<Integer> skillIds, Employee currentUser);
    List<SkillResponse> getAllSkillsForEmployee(Employee currentUser);
    List<EducationResponse> getAllEducationsForEmployee(Employee employee);
}

