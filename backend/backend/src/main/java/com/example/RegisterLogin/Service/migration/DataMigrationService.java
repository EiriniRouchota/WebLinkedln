package com.example.RegisterLogin.Service.migration;
import com.example.RegisterLogin.Repo.InstitutionRepo;
import com.example.RegisterLogin.Entity.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class DataMigrationService {

    @Autowired
    private InstitutionRepo institutionRepository;

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
}