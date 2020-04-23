package tns.shapechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import tns.shapechallenge.model.Category;
import tns.shapechallenge.model.CategoryId;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, CategoryId> {
    @Override
    List<Category> findAll();
}
