package com.example.RegisterLogin.Repo;
import com.example.RegisterLogin.Entity.Experience;
import com.example.RegisterLogin.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository // This annotation is sufficient
public interface ExperienceRepo extends JpaRepository<Experience, Integer> {
    // Automatically generated query to find all experience records by employeeId
    List<Experience> findByEmployee_Employeeid(int employeeid);
    List<Experience> findByEmployee(Employee employee);
    void deleteByEmployee(Employee employee);
}