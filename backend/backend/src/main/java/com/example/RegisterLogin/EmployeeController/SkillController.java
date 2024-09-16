package com.example.RegisterLogin.EmployeeController;

import com.example.RegisterLogin.Dto.SkillDTO;
import com.example.RegisterLogin.Service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/skills")
public class SkillController {

    private final EmployeeService employeeService;

    @Autowired
    public SkillController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<SkillDTO> getAllSkills() {
        return employeeService.getAllSkills();
    }
}
