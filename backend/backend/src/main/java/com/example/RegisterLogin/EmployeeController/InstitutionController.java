package com.example.RegisterLogin.EmployeeController;

import com.example.RegisterLogin.Entity.Institution;
import com.example.RegisterLogin.Repo.InstitutionRepo;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/institutions")
public class InstitutionController {

    private final EmployeeService employeeService;
    private final JwtService jwtService;
    private final InstitutionRepo institutionRepository;

    @Autowired
    public InstitutionController(EmployeeService employeeService, JwtService jwtService, InstitutionRepo institutionRepository) {
        this.employeeService = employeeService;
        this.jwtService = jwtService;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping(path="/all")
    public ResponseEntity<List<Institution>> getAllInstitutions() {
        List<Institution> institutions = institutionRepository.findAll();
        return ResponseEntity.ok(institutions);
    }
}
