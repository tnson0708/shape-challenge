package tns.shapechallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tns.shapechallenge.model.Category;
import tns.shapechallenge.model.CategoryId;
import tns.shapechallenge.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteShapeCategory(CategoryId categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
