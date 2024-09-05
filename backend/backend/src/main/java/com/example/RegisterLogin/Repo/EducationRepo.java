package com.example.RegisterLogin.Repo;
import com.example.RegisterLogin.Entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository // This annotation is sufficient
public interface EducationRepo extends JpaRepository<Education, Integer> {
    // Automatically generated query to find all Education records by employeeId
    List<Education> findByEmployee_Employeeid(int employeeid);
}