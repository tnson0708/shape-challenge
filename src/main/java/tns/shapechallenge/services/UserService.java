package tns.shapechallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tns.shapechallenge.model.User;
import tns.shapechallenge.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveMyUser(User user, String isAdminRole, boolean isUpdateMode) {
        if (!isUpdateMode) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Set<String> roles = new HashSet<>();
        if ("ROLE_ADMIN".equalsIgnoreCase(isAdminRole)) {
            roles.add("ROLE_ADMIN");
        } else {
            roles.add("ROLE_USER");
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    public void deleteMyUser(int id) {
        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User findByUserName(String username) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                return user;
            }
        }
        return null;
    }
}
