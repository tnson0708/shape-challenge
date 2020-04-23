package tns.shapechallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tns.shapechallenge.model.SavedShape;
import tns.shapechallenge.model.Shape;
import tns.shapechallenge.repository.CategoryRepository;
import tns.shapechallenge.repository.SavedShapeRepository;
import tns.shapechallenge.repository.ShapeRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShapeService {
    @Autowired
    private SavedShapeRepository savedShapeRepository;
    @Autowired
    private ShapeRepository shapeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<SavedShape> getAllSavedShapes() {
        return savedShapeRepository.findAll();
    }

    public List<SavedShape> getSavedShapesForUser(Integer id) {
       return getAllSavedShapes().stream().filter(savedShape -> savedShape.getUser().getId() == id).collect(Collectors.toList());
    }

    public void saveShape(SavedShape savedShape) {
        savedShapeRepository.save(savedShape);
    }

    public SavedShape getSavedShapeById(Integer id) {
        return savedShapeRepository.findById(id).get();
    }

    public void deleteSavedShapeById(Integer id) {
        savedShapeRepository.deleteById(id);
    }

    public Shape getShapeById(Integer id) {
        return shapeRepository.findById(id).get();
    }
}
