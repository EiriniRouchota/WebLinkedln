package com.example.RegisterLogin.Repo;
import com.example.RegisterLogin.Entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository // This annotation is sufficient
public interface InstitutionRepo extends JpaRepository<Institution, Integer> {
    List<Institution> findByNameIgnoreCase(String name);
    Optional<Institution> findByNameAndLocation(String name, String location);
    Optional<Institution> findByName(String name);
}