package tns.shapechallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tns.shapechallenge.model.User;
import tns.shapechallenge.repository.UserRepository;
import tns.shapechallenge.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    public List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUpDate() {
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("username" + i);
            user.setFirstname("FistName" + i);
            user.setLastname("LastName" + i);
            user.setAge(25 + i);
            user.setPassword("123");
            userList.add(user);
        }
        when(userRepository.findAll()).thenReturn(userList);
    }

    @Test
    public void testGetUsers() {
        assertEquals(3, userService.getAllUsers().size());
        assertEquals("username1", userService.findByUserName("username1").getUsername());
        assertEquals(null, userService.findByUserName("empty"));
    }

    @Test
    public void testDeleteUser() {
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById(isA(Integer.class));
    }

}
