package tns.shapechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import tns.shapechallenge.model.Requirement;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {
    @Override
    List<Requirement> findAll();
}
