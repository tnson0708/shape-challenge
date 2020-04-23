package tns.shapechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import tns.shapechallenge.model.SavedShape;

import java.util.List;

public interface SavedShapeRepository extends CrudRepository<SavedShape, Integer> {
    @Override
    List<SavedShape> findAll();
}
