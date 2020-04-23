package tns.shapechallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tns.shapechallenge.model.Requirement;
import tns.shapechallenge.repository.RequirementRepository;
import tns.shapechallenge.services.RequirementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RequirementServiceTest {
    @Mock
    private RequirementRepository requirementRepository;
    @InjectMocks
    private RequirementService requirementService;
    public List<Requirement> requirements = new ArrayList<>();

    @BeforeEach
    public void iniData() {
        for (int i = 1; i <= 3; i++) {
            Requirement requirement = new Requirement();
            requirement.setId(i);
            requirement.setWidth(Boolean.TRUE);
            requirement.setHeight(Boolean.TRUE);
            requirement.setHigh(Boolean.TRUE);
            requirement.setName("rectangle" + i);
            requirements.add(requirement);
        }
        when(requirementRepository.findAll()).thenReturn(requirements);
    }
    @Test
    public void testGetAllRequirements(){
        assertEquals(3, requirementService.getAllRequirements().size());
    }
}
