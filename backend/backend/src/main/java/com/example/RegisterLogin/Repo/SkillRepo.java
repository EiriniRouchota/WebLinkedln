package com.example.RegisterLogin.Repo;
import com.example.RegisterLogin.Entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.RegisterLogin.Entity.Skill;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository // This annotation is sufficient
public interface SkillRepo  extends JpaRepository<Skill, Integer> {
    Optional<Skill> findByName(String name);
}
