package com.example.RegisterLogin.Service.migration;
import com.example.RegisterLogin.Entity.Skill;
import com.example.RegisterLogin.Repo.InstitutionRepo;
import com.example.RegisterLogin.Entity.Institution;
import com.example.RegisterLogin.Repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class DataMigrationService {

    @Autowired
    private InstitutionRepo institutionRepository;


    @Autowired
    private SkillRepo skillRepository;

    @Transactional
    public void migrateData() {
        // Insert institutions if they don't exist
        insertInstitutionIfNotExists("Harvard University", "Cambridge, MA, USA");
        insertInstitutionIfNotExists("Stanford University", "Stanford, CA, USA");
        insertInstitutionIfNotExists("Massachusetts Institute of Technology", "Cambridge, MA, USA");
        insertInstitutionIfNotExists("University of Oxford", "Oxford, England");
        insertInstitutionIfNotExists("University of Cambridge", "Cambridge, England");
        insertInstitutionIfNotExists("California Institute of Technology", "Pasadena, CA, USA");
        insertInstitutionIfNotExists("Princeton University", "Princeton, NJ, USA");
        insertInstitutionIfNotExists("Yale University", "New Haven, CT, USA");
        insertInstitutionIfNotExists("University of Chicago", "Chicago, IL, USA");
        insertInstitutionIfNotExists("Columbia University", "New York, NY, USA");
        insertInstitutionIfNotExists("University of Pennsylvania", "Philadelphia, PA, USA");
        insertInstitutionIfNotExists("ETH Zurich", "Zurich, Switzerland");
        insertInstitutionIfNotExists("University of California, Berkeley", "Berkeley, CA, USA");
        insertInstitutionIfNotExists("University of Toronto", "Toronto, Canada");
        insertInstitutionIfNotExists("Imperial College London", "London, England");
        insertInstitutionIfNotExists("University College London", "London, England");
        insertInstitutionIfNotExists("University of Michigan", "Ann Arbor, MI, USA");
        insertInstitutionIfNotExists("National University of Singapore", "Singapore, Singapore");
        insertInstitutionIfNotExists("University of Tokyo", "Tokyo, Japan");
        insertInstitutionIfNotExists("Duke University", "Durham, NC, USA");
        insertInstitutionIfNotExists("Cornell University", "Ithaca, NY, USA");


        // Add more migration logic as needed
        // Insert skills if they don't exist
        insertSkillIfNotExists("Java");
        insertSkillIfNotExists("JavaScript");
        insertSkillIfNotExists("Python");
        insertSkillIfNotExists("C++");
        insertSkillIfNotExists("HTML");
        insertSkillIfNotExists("CSS");
        insertSkillIfNotExists("SQL");
        insertSkillIfNotExists("Node.js");
        insertSkillIfNotExists("React");
        insertSkillIfNotExists("Angular");
        insertSkillIfNotExists("Vue.js");
        insertSkillIfNotExists("Spring Boot");
        insertSkillIfNotExists("Django");
        insertSkillIfNotExists("Flask");
        insertSkillIfNotExists("Ruby on Rails");
        insertSkillIfNotExists("Kotlin");
        insertSkillIfNotExists("Swift");
        insertSkillIfNotExists("TypeScript");
        insertSkillIfNotExists("Docker");
        insertSkillIfNotExists("Kubernetes");
        insertSkillIfNotExists("AWS");
        insertSkillIfNotExists("Azure");
        insertSkillIfNotExists("Google Cloud Platform");
        insertSkillIfNotExists("Git");
        insertSkillIfNotExists("Jenkins");
        insertSkillIfNotExists("CI/CD Pipelines");
        insertSkillIfNotExists("RESTful APIs");
        insertSkillIfNotExists("GraphQL");
        insertSkillIfNotExists("MySQL");
        insertSkillIfNotExists("PostgreSQL");
        insertSkillIfNotExists("MongoDB");
        insertSkillIfNotExists("Redis");
        insertSkillIfNotExists("Apache Kafka");
        insertSkillIfNotExists("Elasticsearch");
        insertSkillIfNotExists("Nginx");
        insertSkillIfNotExists("Machine Learning");
        insertSkillIfNotExists("TensorFlow");
        insertSkillIfNotExists("Scikit-learn");
        insertSkillIfNotExists("Natural Language Processing");
        insertSkillIfNotExists("Computer Vision");

        // Soft Skills
        insertSkillIfNotExists("Problem-Solving");
        insertSkillIfNotExists("Communication");
        insertSkillIfNotExists("Teamwork");
        insertSkillIfNotExists("Time Management");
        insertSkillIfNotExists("Adaptability");
        insertSkillIfNotExists("Leadership");
        insertSkillIfNotExists("Conflict Resolution");
        insertSkillIfNotExists("Critical Thinking");
        insertSkillIfNotExists("Creativity");
        insertSkillIfNotExists("Emotional Intelligence");
    }

    private void insertInstitutionIfNotExists(String name, String location) {
        Optional<Institution> institutionOpt = institutionRepository.findByNameAndLocation(name, location);

        if (institutionOpt.isEmpty()) {
            Institution institution = new Institution();
            institution.setName(name);
            institution.setLocation(location);
            institutionRepository.save(institution);
        }
    }



    private void updateInstitutionName(String oldName, String newName) {
        Optional<Institution> institutionOpt = institutionRepository.findByName(oldName);

        if (institutionOpt.isPresent()) {
            Institution institution = institutionOpt.get();
            institution.setName(newName);
            institutionRepository.save(institution);
        }
    }

    private void insertSkillIfNotExists(String name) {
        Optional<Skill> skillOpt = skillRepository.findByName(name);

        if (skillOpt.isEmpty()) {
            Skill skill = new Skill();
            skill.setName(name);
            skillRepository.save(skill);
        }
    }
}