package tns.shapechallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tns.shapechallenge.model.Requirement;
import tns.shapechallenge.repository.RequirementRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    public List<Requirement> getAllRequirements() {
        return requirementRepository.findAll();
    }

    public Requirement getRequirementById(Integer id) {
        return requirementRepository.findById(id).get();
    }

    public void saveRequirement(Requirement requirement) {
        requirementRepository.save(requirement);
    }
}
