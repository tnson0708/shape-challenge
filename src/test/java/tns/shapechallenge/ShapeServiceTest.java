package tns.shapechallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tns.shapechallenge.model.SavedShape;
import tns.shapechallenge.model.User;
import tns.shapechallenge.repository.SavedShapeRepository;
import tns.shapechallenge.services.ShapeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShapeServiceTest {
    @Mock
    private SavedShapeRepository savedShapeRepository;
    @InjectMocks
    private ShapeService shapeService;
    public List<SavedShape> shapes = new ArrayList<>();

    @BeforeEach
    public void initData(){
        for(int i = 1; i <= 3; i++){
            SavedShape shape = new SavedShape();
            shape.setId(i);
            shape.setWidth(new Double(3));
            shape.setHeight(new Double(5));
            shape.setWidth(new Double(4));
            shape.setName("triangle" + i);
            User user = new User();
            user.setId(i);
            user.setAge(25 + i);
            user.setUsername("username"+1);
            user.setLastname("LastName"+i);
            user.setFirstname("FirstName"+i);
            user.setPassword("123");
            shape.setUser(user);
            shape.setShapeId(1);
            shapes.add(shape);
        }
        when(savedShapeRepository.findAll()).thenReturn(shapes);
    }

    @Test
    public void testGetAllShapes(){
        assertEquals(3,shapeService.getAllSavedShapes().size());
    }

}
