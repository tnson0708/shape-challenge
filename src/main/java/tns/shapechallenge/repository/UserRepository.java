package tns.shapechallenge.repository;

import org.springframework.data.repository.CrudRepository;
import tns.shapechallenge.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsernameAndPassword(String username, String password);

}
